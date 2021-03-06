/**
 */
package org.openhab.binding.tinkerforge.internal.model.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.openhab.binding.tinkerforge.internal.LoggerConstants;
import org.openhab.binding.tinkerforge.internal.TinkerforgeErrorHandler;
import org.openhab.binding.tinkerforge.internal.model.Ecosystem;
import org.openhab.binding.tinkerforge.internal.model.MBaseDevice;
import org.openhab.binding.tinkerforge.internal.model.MBrickd;
import org.openhab.binding.tinkerforge.internal.model.MDevice;
import org.openhab.binding.tinkerforge.internal.model.MSubDevice;
import org.openhab.binding.tinkerforge.internal.model.MSubDeviceHolder;
import org.openhab.binding.tinkerforge.internal.model.ModelFactory;
import org.openhab.binding.tinkerforge.internal.model.ModelPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerforge.AlreadyConnectedException;
import com.tinkerforge.BrickDC;
import com.tinkerforge.BrickServo;
import com.tinkerforge.BrickletAmbientLight;
import com.tinkerforge.BrickletBarometer;
import com.tinkerforge.BrickletDistanceIR;
import com.tinkerforge.BrickletDualRelay;
import com.tinkerforge.BrickletHumidity;
import com.tinkerforge.BrickletLCD20x4;
import com.tinkerforge.BrickletTemperature;
import com.tinkerforge.IPConnection;
import com.tinkerforge.NotConnectedException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MBrickd</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getLogger <em>Logger</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getIpConnection <em>Ip Connection</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getHost <em>Host</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#isIsConnected <em>Is Connected</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#isAutoReconnect <em>Auto Reconnect</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getMdevices <em>Mdevices</em>}</li>
 *   <li>{@link org.openhab.binding.tinkerforge.internal.model.impl.MBrickdImpl#getEcosystem <em>Ecosystem</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MBrickdImpl extends MinimalEObjectImpl.Container implements MBrickd
{
  /**
   * The default value of the '{@link #getLogger() <em>Logger</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogger()
   * @generated
   * @ordered
   */
  protected static final Logger LOGGER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLogger() <em>Logger</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogger()
   * @generated
   * @ordered
   */
  protected Logger logger = LOGGER_EDEFAULT;

  /**
   * The default value of the '{@link #getIpConnection() <em>Ip Connection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIpConnection()
   * @generated
   * @ordered
   */
  protected static final IPConnection IP_CONNECTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIpConnection() <em>Ip Connection</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIpConnection()
   * @generated
   * @ordered
   */
  protected IPConnection ipConnection = IP_CONNECTION_EDEFAULT;

  /**
   * The default value of the '{@link #getHost() <em>Host</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHost()
   * @generated
   * @ordered
   */
  protected static final String HOST_EDEFAULT = "localhost";

  /**
   * The cached value of the '{@link #getHost() <em>Host</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHost()
   * @generated
   * @ordered
   */
  protected String host = HOST_EDEFAULT;

  /**
   * The default value of the '{@link #getPort() <em>Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPort()
   * @generated
   * @ordered
   */
  protected static final int PORT_EDEFAULT = 4223;

  /**
   * The cached value of the '{@link #getPort() <em>Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPort()
   * @generated
   * @ordered
   */
  protected int port = PORT_EDEFAULT;

  /**
   * The default value of the '{@link #isIsConnected() <em>Is Connected</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIsConnected()
   * @generated
   * @ordered
   */
  protected static final boolean IS_CONNECTED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isIsConnected() <em>Is Connected</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isIsConnected()
   * @generated
   * @ordered
   */
  protected boolean isConnected = IS_CONNECTED_EDEFAULT;

  /**
   * The default value of the '{@link #isAutoReconnect() <em>Auto Reconnect</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAutoReconnect()
   * @generated
   * @ordered
   */
  protected static final boolean AUTO_RECONNECT_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isAutoReconnect() <em>Auto Reconnect</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAutoReconnect()
   * @generated
   * @ordered
   */
  protected boolean autoReconnect = AUTO_RECONNECT_EDEFAULT;

  /**
   * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimeout()
   * @generated
   * @ordered
   */
  protected static final int TIMEOUT_EDEFAULT = 2500;

  /**
   * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimeout()
   * @generated
   * @ordered
   */
  protected int timeout = TIMEOUT_EDEFAULT;

  /**
   * The cached value of the '{@link #getMdevices() <em>Mdevices</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMdevices()
   * @generated
   * @ordered
   */
  protected EList<MDevice<?>> mdevices;

  private Thread connectThread;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MBrickdImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModelPackage.Literals.MBRICKD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Logger getLogger()
  {
    return logger;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLogger(Logger newLogger)
  {
    Logger oldLogger = logger;
    logger = newLogger;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__LOGGER, oldLogger, logger));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPConnection getIpConnection()
  {
    return ipConnection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIpConnection(IPConnection newIpConnection)
  {
    IPConnection oldIpConnection = ipConnection;
    ipConnection = newIpConnection;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__IP_CONNECTION, oldIpConnection, ipConnection));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getHost()
  {
    return host;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHost(String newHost)
  {
    String oldHost = host;
    host = newHost;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__HOST, oldHost, host));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getPort()
  {
    return port;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPort(int newPort)
  {
    int oldPort = port;
    port = newPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__PORT, oldPort, port));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isIsConnected()
  {
    return isConnected;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIsConnected(boolean newIsConnected)
  {
    boolean oldIsConnected = isConnected;
    isConnected = newIsConnected;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__IS_CONNECTED, oldIsConnected, isConnected));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAutoReconnect()
  {
    return autoReconnect;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAutoReconnect(boolean newAutoReconnect)
  {
    boolean oldAutoReconnect = autoReconnect;
    autoReconnect = newAutoReconnect;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__AUTO_RECONNECT, oldAutoReconnect, autoReconnect));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getTimeout()
  {
    return timeout;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTimeout(int newTimeout)
  {
    int oldTimeout = timeout;
    timeout = newTimeout;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__TIMEOUT, oldTimeout, timeout));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<MDevice<?>> getMdevices()
  {
    if (mdevices == null)
    {
      mdevices = new EObjectContainmentWithInverseEList<MDevice<?>>(MDevice.class, this, ModelPackage.MBRICKD__MDEVICES, ModelPackage.MDEVICE__BRICKD);
    }
    return mdevices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecosystem getEcosystem()
  {
    if (eContainerFeatureID() != ModelPackage.MBRICKD__ECOSYSTEM) return null;
    return (Ecosystem)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEcosystem(Ecosystem newEcosystem, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newEcosystem, ModelPackage.MBRICKD__ECOSYSTEM, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcosystem(Ecosystem newEcosystem)
  {
    if (newEcosystem != eInternalContainer() || (eContainerFeatureID() != ModelPackage.MBRICKD__ECOSYSTEM && newEcosystem != null))
    {
      if (EcoreUtil.isAncestor(this, newEcosystem))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newEcosystem != null)
        msgs = ((InternalEObject)newEcosystem).eInverseAdd(this, ModelPackage.ECOSYSTEM__MBRICKDS, Ecosystem.class, msgs);
      msgs = basicSetEcosystem(newEcosystem, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.MBRICKD__ECOSYSTEM, newEcosystem, newEcosystem));
  }

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void connect() {
		// Create connection to brickd
		final IPConnection ipcon = new IPConnection();
		setIpConnection(ipcon);

		ipcon.setTimeout(timeout);
		ipcon.setAutoReconnect(false);
		ipcon.addConnectedListener(new ConnectedListener(ipcon));
		//ipcon.addDisconnectedListener(new DisconnectedListener());
		makeConnect();
		logger.trace("{} After connect call", LoggerConstants.TFINIT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void makeConnect() {
		try {
			logger.debug(
					"trying to establish connection to {}:{}", host, port);
			ipConnection.connect(getHost(), getPort());
		} catch (AlreadyConnectedException e) {
			logger.debug("connect successful: {}:{}", host,
					port);
		} catch (ConnectException e) {
			// lets try it endless: don't set connected to true
			logger.debug(
					"connect failed with ConnectionException: {}:{}", host,
					port);
		} catch (UnknownHostException e) {
			logger.error("fatal error: {}", e);
		} catch (IOException e) {
			logger.error("connect failed with IOException {}", e);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private class ConnectedListener implements IPConnection.ConnectedListener {
		IPConnection ipcon;

		public ConnectedListener(IPConnection ipcon) {
			super();
			this.ipcon = ipcon;
		}

		@Override
		public void connected(short connectReason) {
			logger.debug("{} Connected listener was called.", LoggerConstants.TFINIT);
			setIsConnected(true);
			//ipcon.addDisconnectedListener(new DisconnectedListener());
			ipcon.addEnumerateListener(new EnumerateListener());
			try {
				ipcon.enumerate();
			} catch (NotConnectedException e) {
				logger.error("{} enumeration failed with NotConnectedException", LoggerConstants.TFINIT);
			}
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private class DisconnectedListener implements
			IPConnection.DisconnectedListener {

		@Override
		public void disconnected(short connectReason) {
			logger.trace("disconnected listener was called.", LoggerConstants.TFINIT);
			setIsConnected(false);
			ArrayList<String> deviceUidList = new ArrayList<String>();
			for (MDevice<?> mDevice: mdevices){
				deviceUidList.add(mDevice.getUid());
			}
			for (String uid : deviceUidList)
			{
				removeDevice(uid);
			}
			makeConnect();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private class EnumerateListener implements IPConnection.EnumerateListener {

		@Override
		public void enumerate(String uid, String connectedUid, char position,
				short[] hardwareVersion, short[] firmwareVersion,
				int deviceIdentifier, short enumerationType) {
			logger.debug("{} EnumerateListener was called.", LoggerConstants.TFINIT);
			if (enumerationType == IPConnection.ENUMERATION_TYPE_DISCONNECTED)
				removeDevice(uid);
			else
				addDevice(uid, connectedUid, deviceIdentifier);
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	private void removeDevice(String uid) {
		MBaseDevice device = getDevice(uid);
		getMdevices().remove(device);
		logger.debug("{} removeDevice called", LoggerConstants.TFINIT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	private void addDevice(String uid, String connectedUid, int deviceIdentifier) {
		if (getDevice(uid) != null) {
			logger.debug("{} device already exists. uid: {}", LoggerConstants.TFINIT, uid);
		} else {
			ModelFactory factory = ModelFactory.eINSTANCE;
			MDevice<?> mDevice = null;
			logger.debug("{} addDevice called", LoggerConstants.TFINIT);
			if (deviceIdentifier == BrickletTemperature.DEVICE_IDENTIFIER) {
				logger.debug("{} addDevice temperature", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickletTemperature();
				mDevice.setDeviceIdentifier(BrickletTemperature.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletHumidity.DEVICE_IDENTIFIER) {
				logger.debug("{} addDevice humidity", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickletHumidity();
				mDevice.setDeviceIdentifier(BrickletHumidity.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickServo.DEVICE_IDENTIFIER){
				logger.debug("{} addDevice BrickServo", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickServo();
				mDevice.setDeviceIdentifier(BrickServo.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletDualRelay.DEVICE_IDENTIFIER){
				logger.debug("{} addDevice BrickletDualRelayBricklet", LoggerConstants.TFINIT);
				mDevice = factory.createMDualRelayBricklet();
				mDevice.setDeviceIdentifier(BrickletDualRelay.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletBarometer.DEVICE_IDENTIFIER){
				logger.debug("{} addDevice BrickletBarometer", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickletBarometer();
				mDevice.setDeviceIdentifier(BrickletBarometer.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletAmbientLight.DEVICE_IDENTIFIER){
				logger.debug("{} addDevice AmbientLight", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickletAmbientLight();
				mDevice.setDeviceIdentifier(BrickletAmbientLight.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletDistanceIR.DEVICE_IDENTIFIER){
				logger.debug("{} addDevice DistanceIR", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickletDistanceIR();
				mDevice.setDeviceIdentifier(BrickletDistanceIR.DEVICE_IDENTIFIER);		
			} else if (deviceIdentifier == BrickDC.DEVICE_IDENTIFIER) {
				logger.debug("{} addDevice BrickDC", LoggerConstants.TFINIT);
				mDevice = factory.createMBrickDC();
				mDevice.setDeviceIdentifier(BrickDC.DEVICE_IDENTIFIER);
			} else if (deviceIdentifier == BrickletLCD20x4.DEVICE_IDENTIFIER){
				logger.debug("addDevice BrickletLCD20x4");
				mDevice = factory.createMBrickletLCD20x4();
				mDevice.setDeviceIdentifier(BrickletLCD20x4.DEVICE_IDENTIFIER);
			}
		
			if (mDevice != null) {
				mDevice.setIpConnection(getIpConnection());
				logger.debug("{} addDevice uid: {}", LoggerConstants.TFINIT, uid);
				mDevice.setUid(uid);
				mDevice.setConnectedUid(connectedUid);
				mDevice.init();
				mDevice.setBrickd(this); // be aware this triggers the notifier
				if (mDevice instanceof MSubDeviceHolder){
					logger.debug("{} initSubDevices uid: {}", LoggerConstants.TFINIT, uid);
					((MSubDeviceHolder<MSubDevice<?>>)mDevice).initSubDevices();
				}
			}
		}
	}

	/**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated NOT
   */
	public void disconnect() {
		try {
			ipConnection.disconnect();
			if (connectThread != null)
				connectThread.interrupt();
		} catch (NotConnectedException e) {
			TinkerforgeErrorHandler.handleError(logger,
					TinkerforgeErrorHandler.TF_NOT_CONNECTION_EXCEPTION, e);
		}
	}

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void init()
  {
	  logger = LoggerFactory.getLogger(MBrickdImpl.class);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public MBaseDevice getDevice(String uid)
  {
    EList<MDevice<?>> _mdevices = getMdevices();
    for (final MBaseDevice mdevice : _mdevices)
    {
      String _uid = mdevice.getUid();
      if (_uid.equals(uid))
      {
        return mdevice;
      }
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__MDEVICES:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getMdevices()).basicAdd(otherEnd, msgs);
      case ModelPackage.MBRICKD__ECOSYSTEM:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetEcosystem((Ecosystem)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__MDEVICES:
        return ((InternalEList<?>)getMdevices()).basicRemove(otherEnd, msgs);
      case ModelPackage.MBRICKD__ECOSYSTEM:
        return basicSetEcosystem(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case ModelPackage.MBRICKD__ECOSYSTEM:
        return eInternalContainer().eInverseRemove(this, ModelPackage.ECOSYSTEM__MBRICKDS, Ecosystem.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__LOGGER:
        return getLogger();
      case ModelPackage.MBRICKD__IP_CONNECTION:
        return getIpConnection();
      case ModelPackage.MBRICKD__HOST:
        return getHost();
      case ModelPackage.MBRICKD__PORT:
        return getPort();
      case ModelPackage.MBRICKD__IS_CONNECTED:
        return isIsConnected();
      case ModelPackage.MBRICKD__AUTO_RECONNECT:
        return isAutoReconnect();
      case ModelPackage.MBRICKD__TIMEOUT:
        return getTimeout();
      case ModelPackage.MBRICKD__MDEVICES:
        return getMdevices();
      case ModelPackage.MBRICKD__ECOSYSTEM:
        return getEcosystem();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__LOGGER:
        setLogger((Logger)newValue);
        return;
      case ModelPackage.MBRICKD__IP_CONNECTION:
        setIpConnection((IPConnection)newValue);
        return;
      case ModelPackage.MBRICKD__HOST:
        setHost((String)newValue);
        return;
      case ModelPackage.MBRICKD__PORT:
        setPort((Integer)newValue);
        return;
      case ModelPackage.MBRICKD__IS_CONNECTED:
        setIsConnected((Boolean)newValue);
        return;
      case ModelPackage.MBRICKD__AUTO_RECONNECT:
        setAutoReconnect((Boolean)newValue);
        return;
      case ModelPackage.MBRICKD__TIMEOUT:
        setTimeout((Integer)newValue);
        return;
      case ModelPackage.MBRICKD__MDEVICES:
        getMdevices().clear();
        getMdevices().addAll((Collection<? extends MDevice<?>>)newValue);
        return;
      case ModelPackage.MBRICKD__ECOSYSTEM:
        setEcosystem((Ecosystem)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__LOGGER:
        setLogger(LOGGER_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__IP_CONNECTION:
        setIpConnection(IP_CONNECTION_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__HOST:
        setHost(HOST_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__PORT:
        setPort(PORT_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__IS_CONNECTED:
        setIsConnected(IS_CONNECTED_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__AUTO_RECONNECT:
        setAutoReconnect(AUTO_RECONNECT_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__TIMEOUT:
        setTimeout(TIMEOUT_EDEFAULT);
        return;
      case ModelPackage.MBRICKD__MDEVICES:
        getMdevices().clear();
        return;
      case ModelPackage.MBRICKD__ECOSYSTEM:
        setEcosystem((Ecosystem)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelPackage.MBRICKD__LOGGER:
        return LOGGER_EDEFAULT == null ? logger != null : !LOGGER_EDEFAULT.equals(logger);
      case ModelPackage.MBRICKD__IP_CONNECTION:
        return IP_CONNECTION_EDEFAULT == null ? ipConnection != null : !IP_CONNECTION_EDEFAULT.equals(ipConnection);
      case ModelPackage.MBRICKD__HOST:
        return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
      case ModelPackage.MBRICKD__PORT:
        return port != PORT_EDEFAULT;
      case ModelPackage.MBRICKD__IS_CONNECTED:
        return isConnected != IS_CONNECTED_EDEFAULT;
      case ModelPackage.MBRICKD__AUTO_RECONNECT:
        return autoReconnect != AUTO_RECONNECT_EDEFAULT;
      case ModelPackage.MBRICKD__TIMEOUT:
        return timeout != TIMEOUT_EDEFAULT;
      case ModelPackage.MBRICKD__MDEVICES:
        return mdevices != null && !mdevices.isEmpty();
      case ModelPackage.MBRICKD__ECOSYSTEM:
        return getEcosystem() != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
  {
    switch (operationID)
    {
      case ModelPackage.MBRICKD___CONNECT:
        connect();
        return null;
      case ModelPackage.MBRICKD___DISCONNECT:
        disconnect();
        return null;
      case ModelPackage.MBRICKD___INIT:
        init();
        return null;
      case ModelPackage.MBRICKD___GET_DEVICE__STRING:
        return getDevice((String)arguments.get(0));
    }
    return super.eInvoke(operationID, arguments);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (logger: ");
    result.append(logger);
    result.append(", ipConnection: ");
    result.append(ipConnection);
    result.append(", host: ");
    result.append(host);
    result.append(", port: ");
    result.append(port);
    result.append(", isConnected: ");
    result.append(isConnected);
    result.append(", autoReconnect: ");
    result.append(autoReconnect);
    result.append(", timeout: ");
    result.append(timeout);
    result.append(')');
    return result.toString();
  }

} //MBrickdImpl
