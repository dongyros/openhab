/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.binding.http.internal;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openhab.binding.http.internal.HttpGenericBindingProvider.HttpBindingConfig;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.Item;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;
import org.openhab.model.item.binding.BindingConfigParseException;


/**
 * @author Thomas.Eichstaedt-Engelen
 * @since 0.6.0
 */
public class HttpGenericBindingProviderTest {
	
	private HttpGenericBindingProvider provider;
	private Item testItem;
	
	@Before
	public void init() {
		provider = new HttpGenericBindingProvider();
		testItem = new StringTestItem();
	}
	
	@Test(expected=BindingConfigParseException.class)
	public void testParseBindingConfig_wrongDirection() throws BindingConfigParseException {
		provider.parseBindingConfig(testItem, "?");
	}

	@Test
	public void testParseBindingConfig() throws BindingConfigParseException {
		
		String bindingConfig = ">[ON:POST:http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"] >[OFF:GET:http://www.domain.org:1234/home/lights/23871/?status=off] <[http://www.google.com:1234/ig/api?weather=Krefeld+Germany&hl=de:60000:REGEX(.*?<current_conditions>.*?<temp_c data=\\\"(.*?)\\\".*)] >[CHANGED:POST:http://www.domain.org:1234/home/lights/23871/?value=%2$s] >[*:POST:http://www.domain.org:1234/home/lights?value=%2$s]";
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.IN_BINDING_KEY));
		Assert.assertEquals(null, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).httpMethod);
		Assert.assertEquals("http://www.google.com:1234/ig/api?weather=Krefeld+Germany&hl=de", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).url);
		Assert.assertEquals(60000, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).refreshInterval);
		Assert.assertEquals("REGEX(.*?<current_conditions>.*?<temp_c data=\"(.*?)\".*)", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).transformation);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(StringType.valueOf("ON")));
		Assert.assertEquals("POST", config.get(StringType.valueOf("ON")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"", config.get(StringType.valueOf("ON")).url);
		
		Assert.assertEquals(true, config.containsKey(StringType.valueOf("OFF")));
		Assert.assertEquals("GET", config.get(StringType.valueOf("OFF")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=off", config.get(StringType.valueOf("OFF")).url);
		
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.CHANGED_COMMAND_KEY));
		Assert.assertEquals("POST", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?value=%2$s", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).url);
		
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY));
		Assert.assertEquals("POST", config.get(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights?value=%2$s", config.get(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY).url);
	}
	
	@Test
	public void testParseBindingConfigWithHTTPHeaders() throws BindingConfigParseException {
		
		String bindingConfig = ">[ON:POST:http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"] >[OFF:GET:http://www.domain.org:1234/home/lights/23871/?status=off{header1=value1&header2=value2}] <[http://www.google.com:1234/ig/api?weather=Krefeld+Germany&hl=de{h=v}:60000:REGEX(.*?<current_conditions>.*?<temp_c data=\\\"(.*?)\\\".*)] >[CHANGED:POST:http://www.domain.org:1234/home/lights/23871/?value=%2$s] >[*:POST:http://www.domain.org:1234/home/lights?value=%2$s]";
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.IN_BINDING_KEY));
		Assert.assertEquals(null, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).httpMethod);
		Assert.assertEquals("http://www.google.com:1234/ig/api?weather=Krefeld+Germany&hl=de", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).url);
		Assert.assertEquals("{h=v}", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.toString());
		Assert.assertEquals(60000, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).refreshInterval);
		Assert.assertEquals("REGEX(.*?<current_conditions>.*?<temp_c data=\"(.*?)\".*)", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).transformation);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(StringType.valueOf("ON")));
		Assert.assertEquals("POST", config.get(StringType.valueOf("ON")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"", config.get(StringType.valueOf("ON")).url);
		
		
		Assert.assertEquals(true, config.containsKey(StringType.valueOf("OFF")));
		Assert.assertEquals("GET", config.get(StringType.valueOf("OFF")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=off", config.get(StringType.valueOf("OFF")).url);
		Assert.assertNotNull(config.get(StringType.valueOf("OFF")).headers);
		Assert.assertEquals("{header2=value2, header1=value1}", config.get(StringType.valueOf("OFF")).headers.toString());
		
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.CHANGED_COMMAND_KEY));
		Assert.assertEquals("POST", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?value=%2$s", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).url);
		
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY));
		Assert.assertEquals("POST", config.get(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights?value=%2$s", config.get(HttpGenericBindingProvider.WILDCARD_COMMAND_KEY).url);
	}
	
	@Test
	public void testParseBindingConfigWithNumbers() throws BindingConfigParseException {
		
		String bindingConfig = ">[1:POST:http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"] >[0:GET:http://www.domain.org:1234/home/lights/23871/?status=off]";
		Item testItem = new DecimalTestItem();
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(DecimalType.valueOf("1")));
		Assert.assertEquals("POST", config.get(DecimalType.valueOf("1")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=on&type=\"text\"", config.get(DecimalType.valueOf("1")).url);
		
		Assert.assertEquals(true, config.containsKey(DecimalType.valueOf("0")));
		Assert.assertEquals("GET", config.get(DecimalType.valueOf("0")).httpMethod);
		Assert.assertEquals("http://www.domain.org:1234/home/lights/23871/?status=off", config.get(DecimalType.valueOf("0")).url);
	}
	
	@Test
	public void testParseBindingConfigWithXPATH() throws BindingConfigParseException {
		
		String bindingConfig = "<[http://www.wetter-vista.de:7970/api/xml.php?q=Berlin:60000:XPATH(/wettervorhersage/tag[1]/tmax)]";
		Item testItem = new DecimalTestItem();
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.IN_BINDING_KEY));
		Assert.assertEquals(null, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).httpMethod);
		Assert.assertEquals("http://www.wetter-vista.de:7970/api/xml.php?q=Berlin", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).url);
		Assert.assertEquals(60000, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).refreshInterval);
		Assert.assertEquals("XPATH(/wettervorhersage/tag[1]/tmax)", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).transformation);
	}
	
	@Test
	public void testParseIncomingBindingConfigWithHTTPHeaders() throws BindingConfigParseException{
		
		String bindingConfig = "<[http://www.wetter-vista.de/api/xml.php?q=Berlin{header1=value1&header2=value2}:60000:XPATH(/wettervorhersage/tag[1]/tmax)]";
		Item testItem = new DecimalTestItem();
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.IN_BINDING_KEY));
		Assert.assertEquals(null, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).httpMethod);
		Assert.assertEquals("http://www.wetter-vista.de/api/xml.php?q=Berlin", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).url);
		Assert.assertEquals(60000, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).refreshInterval);
		Assert.assertEquals("XPATH(/wettervorhersage/tag[1]/tmax)", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).transformation);
		Assert.assertNotNull(config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers);
		Assert.assertEquals("{header2=value2, header1=value1}", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.toString());
		Assert.assertTrue(config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.containsKey("header1"));
		Assert.assertTrue(config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.containsKey("header2"));
		Assert.assertTrue(config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.contains("value1"));
		Assert.assertTrue(config.get(HttpGenericBindingProvider.IN_BINDING_KEY).headers.contains("value2"));

	}
	
	@Test
	public void testParseBindingConfig_Roland() throws BindingConfigParseException {
		
		String bindingConfig = "<[http://localhost:42111/valueget.html?q=4-SWITCHMULTILEVEL-user-byte-1-0:60000:XSLT(value.xsl)] >[CHANGED:POST:http://localhost:42111/valuepost.html?id=4-SWITCHMULTILEVEL-user-byte-1-0&v=39]";
		testItem = new DimmerTestItem();
		
		// method under test
		HttpBindingConfig config = provider.parseBindingConfig(testItem, bindingConfig);
		
		// asserts
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.IN_BINDING_KEY));
		Assert.assertEquals(null, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).httpMethod);
		Assert.assertEquals("http://localhost:42111/valueget.html?q=4-SWITCHMULTILEVEL-user-byte-1-0", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).url);
		Assert.assertEquals(60000, config.get(HttpGenericBindingProvider.IN_BINDING_KEY).refreshInterval);
		Assert.assertEquals("XSLT(value.xsl)", config.get(HttpGenericBindingProvider.IN_BINDING_KEY).transformation);
		
		Assert.assertEquals(true, config.containsKey(HttpGenericBindingProvider.CHANGED_COMMAND_KEY));
		Assert.assertEquals("POST", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).httpMethod);
		Assert.assertEquals("http://localhost:42111/valuepost.html?id=4-SWITCHMULTILEVEL-user-byte-1-0&v=39", config.get(HttpGenericBindingProvider.CHANGED_COMMAND_KEY).url);
	}
	

	
	class StringTestItem extends GenericItem {
		
		public StringTestItem() {
			super("TEST");
		}
		
		public StringTestItem(String name) {
			super(name);
		}
		
		public List<Class<? extends State>> getAcceptedDataTypes() {
			List<Class<? extends State>> list = new ArrayList<Class<? extends State>>();
			list.add(StringType.class);
			return list;
		}
		
		public List<Class<? extends Command>> getAcceptedCommandTypes() {
			List<Class<? extends Command>> list = new ArrayList<Class<? extends Command>>();
			list.add(StringType.class);
			return list;
		}

		@Override
		public State getStateAs(Class<? extends State> typeClass) {
			return null;
		}
		
	};

	class DecimalTestItem extends GenericItem {
		
		public DecimalTestItem() {
			super("TEST");
		}
		
		public DecimalTestItem(String name) {
			super(name);
		}
		
		public List<Class<? extends State>> getAcceptedDataTypes() {
			List<Class<? extends State>> list = new ArrayList<Class<? extends State>>();
			list.add(DecimalType.class);
			return list;
		}
		
		public List<Class<? extends Command>> getAcceptedCommandTypes() {
			List<Class<? extends Command>> list = new ArrayList<Class<? extends Command>>();
			list.add(DecimalType.class);
			return list;
		}

		@Override
		public State getStateAs(Class<? extends State> typeClass) {
			return null;
		}
		
	};
	
	class DimmerTestItem extends GenericItem {
		
		public DimmerTestItem() {
			super("TEST");
		}
		
		public DimmerTestItem(String name) {
			super(name);
		}
		
		public List<Class<? extends State>> getAcceptedDataTypes() {
			List<Class<? extends State>> list = new ArrayList<Class<? extends State>>();
				list.add(OnOffType.class);
				list.add(PercentType.class);
				list.add(UnDefType.class);
			return list;
		}
		
		public List<Class<? extends Command>> getAcceptedCommandTypes() {
			List<Class<? extends Command>> list = new ArrayList<Class<? extends Command>>();
				list.add(OnOffType.class);
				list.add(IncreaseDecreaseType.class);
				list.add(PercentType.class);
			return list;
		}

		@Override
		public State getStateAs(Class<? extends State> typeClass) {
			return null;
		}
		
	};
	
}
