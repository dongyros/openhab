package org.openhab.binding.enocean.internal.bus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.enocean.java.address.EnoceanId;
import org.enocean.java.address.EnoceanParameterAddress;
import org.enocean.java.common.values.ButtonState;
import org.enocean.java.eep.RockerSwitch;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.OnOffType;

@Ignore("Not yet implemented")
public class RockerSwitchInDimmerSteppingProfileTest extends BasicBindingTest {

    private static final String CHANNEL = "B";

    @Before
    public void setUpDefaultDevice() {
        parameterAddress = new EnoceanParameterAddress(EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, null);
        provider.setParameterAddress(parameterAddress);
        provider.setItem(new DimmerItem("dummie"));
        provider.setEep(RockerSwitch.EEP_ID_1);
        binding.addBindingProvider(provider);
    }

    @Test
    public void increaseLightBy30OnShortButtonPressDown() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, RockerSwitch.BUTTON_I);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertEquals("Update State", null, publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertEquals("Update State", new DecimalType(30), publisher.popLastCommand());
    }

    @Test
    public void doNothingOnWrongChannel() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), "A", RockerSwitch.BUTTON_I);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertNull("Update State", publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertNull("Update State", publisher.popLastCommand());
    }

    @Test
    public void switchOffLightOnShortButtonPressUp() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, RockerSwitch.BUTTON_O);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertEquals("Update State", IncreaseDecreaseType.DECREASE, publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertEquals("Update State", OnOffType.OFF, publisher.popLastCommand());
    }

    @Test
    public void lightenUpDuringLongButtonPressDown() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, RockerSwitch.BUTTON_I);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertEquals("Update State", IncreaseDecreaseType.INCREASE, publisher.popLastCommand());
        waitFor(300);
        assertEquals("Update State", IncreaseDecreaseType.INCREASE, publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertNull("Update State", publisher.popLastCommand());
    }

    @Test
    public void lightenUpDuringVeryLongButtonPressDown() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, RockerSwitch.BUTTON_I);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertEquals("Update State", IncreaseDecreaseType.INCREASE, publisher.popLastCommand());
        waitFor(300);
        assertEquals("Update State", IncreaseDecreaseType.INCREASE, publisher.popLastCommand());
        waitFor(300);
        assertEquals("Update State", IncreaseDecreaseType.INCREASE, publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertNull("Update State", publisher.popLastCommand());
    }

    @Test
    public void dimmLightDuringLongButtonPressUp() {
        EnoceanParameterAddress valueParameterAddress = new EnoceanParameterAddress(
                EnoceanId.fromString(EnoceanBindingProviderMock.DEVICE_ID), CHANNEL, RockerSwitch.BUTTON_O);
        binding.valueChanged(valueParameterAddress, ButtonState.PRESSED);
        waitFor(10);
        assertEquals("Update State", IncreaseDecreaseType.DECREASE, publisher.popLastCommand());
        waitFor(300);
        assertEquals("Update State", IncreaseDecreaseType.DECREASE, publisher.popLastCommand());
        binding.valueChanged(valueParameterAddress, ButtonState.RELEASED);
        waitFor(10);
        assertNull("Update State", publisher.popLastCommand());
    }

    private void waitFor(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
