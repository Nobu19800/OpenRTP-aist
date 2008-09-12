package jp.go.aist.rtm.systemeditor.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

/**
 * �ݒ���Ǘ�����}�l�[�W��
 * <p>
 * �ݒ���ɃA�N�Z�X����ɂ͂��̃C���X�^���X���g�p����
 */
public class SystemEditorPreferenceManager {
	private static final String Separator = ":";
	private static SystemEditorPreferenceManager __instance = new SystemEditorPreferenceManager();

	/**
	 * RTC��ԐF�̃L�[�F Start
	 */
	public static final String COLOR_RTC_STATE_CREATED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_CREATED";

	/**
	 * RTC��ԐF�̃L�[�F InActive
	 */
	public static final String COLOR_RTC_STATE_INACTIVE = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_INACTIVE";

	/**
	 * RTC��ԐF�̃L�[�F Active
	 */
	public static final String COLOR_RTC_STATE_ACTIVE = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_ACTIVE";

	/**
	 * RTC��ԐF�̃L�[�F Error
	 */
	public static final String COLOR_RTC_STATE_ERROR = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_ERROR";

	/**
	 * RTC��ԐF�̃L�[�F UnKnown
	 */
	public static final String COLOR_RTC_STATE_UNKNOWN = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_UNKNOWN";

	/**
	 * RTCExecutionContext�F�̃L�[�F Running
	 */
	public static final String COLOR_RTC_EXECUTION_CONTEXT_RUNNING = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_EXECUTION_CONTEXT_RUNNING";

	/**
	 * RTCExecutionContext�F�̃L�[�F Stopped
	 */
	public static final String COLOR_RTC_EXECUTION_CONTEXT_STOPPED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_EXECUTION_CONTEXT_STOPPED";

	/**
	 * DataPort�F�̃L�[�F ���ڑ�
	 */
	public static final String COLOR_DATAPORT_NO_CONNECT = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_DATAPORT_NO_CONNECT";

	/**
	 * DataPort�F�̃L�[�F �ڑ���
	 */
	public static final String COLOR_DATAPORT_CONNECTED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_DATAPORT_CONNECTED";

	/**
	 * ServicePort�F�̃L�[�F ���ڑ�
	 */
	public static final String COLOR_SERVICEPORT_NO_CONNECT = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_SERVICEPORT_NO_CONNECT";

	/**
	 * ServicePort�F�̃L�[�F �ڑ���
	 */
	public static final String COLOR_SERVICEPORT_CONNECTED = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_SERVICEPORT_CONNECTED";

	/**
	 * �����Ԋu�̃L�[�F �V�X�e���G�f�B�^
	 */
	public static final String SYNC_SYSTEMEDITOR_INTERVAL = SystemEditorPreferenceManager.class
			.getName()
			+ "SYNC_SYSTEMEDITOR_INTERVAL";

	//�ڑ����
	/**
	 * Interface Type�̃L�[
	 */
	private static final String CONNECT_INTERFACE_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_INTERFACE_TYPE";

	/**
	 * DataFlow Type�̃L�[
	 */
	private static final String CONNECT_DATAFLOW_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_DATAFLOW_TYPE";

	/**
	 * Subscription Type�̃L�[
	 */
	private static final String CONNECT_SUBSCRIPTION_TYPE = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_SUBSCRIPTION_TYPE";

	/**
	 * �f�t�H���g�̐F���Ǘ�����}�b�v
	 */
	public static final Map<String, RGB> defaultRGBMap = new HashMap<String, RGB>();
	static {
		defaultRGBMap.put(COLOR_RTC_STATE_CREATED, new RGB(255, 255, 255));
		defaultRGBMap.put(COLOR_RTC_STATE_INACTIVE, new RGB(0, 0, 255));
		defaultRGBMap.put(COLOR_RTC_STATE_ACTIVE, new RGB(0, 255, 0));
		defaultRGBMap.put(COLOR_RTC_STATE_ERROR, new RGB(255, 0, 0));
		defaultRGBMap.put(COLOR_RTC_STATE_UNKNOWN, new RGB(0, 0, 0));
		defaultRGBMap.put(COLOR_RTC_EXECUTION_CONTEXT_RUNNING, new RGB(128,
				128, 128));
		defaultRGBMap
				.put(COLOR_RTC_EXECUTION_CONTEXT_STOPPED, new RGB(0, 0, 0));
		defaultRGBMap.put(COLOR_DATAPORT_NO_CONNECT, new RGB(0, 0, 255));
		defaultRGBMap.put(COLOR_DATAPORT_CONNECTED, new RGB(96, 255, 96));
		defaultRGBMap.put(COLOR_SERVICEPORT_NO_CONNECT, new RGB(127, 127, 255));
		defaultRGBMap.put(COLOR_SERVICEPORT_CONNECTED, new RGB(0, 255, 255));
	}

	/**
	 * �f�t�H���g�̓����Ԋu���Ǘ�����}�b�v
	 */
	public static final Map<String, Integer> defaultInvervalMap = new HashMap<String, Integer>();
	static {
		defaultInvervalMap.put(SYNC_SYSTEMEDITOR_INTERVAL, 1000);
	}

	/**
	 * �L���b�V�������F�i���\�[�X�j���Ǘ�����}�b�v
	 */
	private static transient final Map<String, Color> cachedColorMap = new HashMap<String, Color>();

	/**
	 * Interface Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectInterfaceType = {"CORBA_Any", "TCP_Any"};

	/**
	 * DataFlow Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectDataFlowType = {"PUSH", "PULL"};
	
	/**
	 * subscription Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectSubscriptionType = {"Flush", "New", "Periodic"};

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static SystemEditorPreferenceManager getInstance() {
		return __instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �L�[����F��Ԃ�
	 * <p>
	 * �F�͂�\�[�X�ł��邽�߁A�L���b�V�����Ďg�p���Ă���B
	 * 
	 * @param key
	 * @return �F
	 */
	public synchronized Color getColor(String key) {
		RGB rgb = getRGB(key);

		Color result = cachedColorMap.get(key);
		if (result == null || rgb.equals(result.getRGB()) == false) {
			if (result != null) {
				result.dispose();
			}
			result = new Color(PlatformUI.getWorkbench().getDisplay(), rgb);
			cachedColorMap.put(key, result);
		}

		return result;
	}

	/**
	 * �L�[����RGB���擾����
	 * 
	 * @param key
	 * @return RGB
	 */
	public RGB getRGB(String key) {
		RGB result = PreferenceConverter.getColor(RTSystemEditorPlugin.getDefault()
				.getPreferenceStore(), key);
		if (result == PreferenceConverter.COLOR_DEFAULT_DEFAULT) { // caution
			// "=="
			result = defaultRGBMap.get(key);
		}

		return result;
	}

	/**
	 * �L�[�ɁARGB���֘A�t����
	 * 
	 * @param key
	 *            �L�[
	 * @param newRGB
	 *            �֘A�t����RGB
	 */
	public void setRGB(String key, RGB newRGB) {
		RGB oldRgb = getRGB(key);

		PreferenceConverter.setValue(RTSystemEditorPlugin.getDefault()
				.getPreferenceStore(), key, newRGB);

		propertyChangeSupport.firePropertyChange(key, oldRgb, newRGB);
	}

	/**
	 * �Ԋu���擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �Ԋu
	 */
	public int getInterval(String key) {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(key, -1);

		int result = RTSystemEditorPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultInvervalMap.get(key);
		}

		return result;
	}

	/**
	 * �Ԋu��ݒ肷��
	 * 
	 * @param key
	 *            �L�[
	 * @param interval
	 *            �Ԋu
	 */
	public void setInterval(String key, int interval) {
		int oldInterval = getInterval(key);

		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(key, interval);

		propertyChangeSupport.firePropertyChange(key, oldInterval, interval);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * �f�t�H���g�F�̃}�b�v���擾����
	 * 
	 * @return �f�t�H���g�F�̃}�b�v
	 */
	public Map<String, RGB> getDefaultRGBMap() {
		return defaultRGBMap;
	}

	/**
	 * �f�t�H���g�Ԋu�̃}�b�v���擾����
	 * 
	 * @return �f�t�H���g�Ԋu�̃}�b�v
	 */
	public Map<String, Integer> getDefaultIntervalMap() {
		return defaultInvervalMap;
	}

	/**
	 * Interface Type���擾����
	 * 
	 * @return Interface Type���X�g
	 */
	public String[] getInterfaceTypes() {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(CONNECT_INTERFACE_TYPE, "");

		String resultTemp = RTSystemEditorPlugin.getDefault().getPreferenceStore().getString(CONNECT_INTERFACE_TYPE);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultConnectInterfaceType;
		} else {
			result = resultTemp.split(Separator);
		}
		return result;
	}
	/**
	 * Interface Type��ݒ肷��
	 * 
	 * @param interfaceType
	 *            Interface Type���X�g
	 */
	public void setInterfaceTypes(ArrayList<String> interfaceType) {
		String[] oldInterfaceType = getInterfaceTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_INTERFACE_TYPE, convertList2String(interfaceType));
		propertyChangeSupport.firePropertyChange(CONNECT_INTERFACE_TYPE, oldInterfaceType, interfaceType);
	}

	/**
	 * Data Flow Type���擾����
	 * 
	 * @return Data Flow Type���X�g
	 */
	public String[] getDataFlowTypes() {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(CONNECT_DATAFLOW_TYPE, "");

		String resultTemp = RTSystemEditorPlugin.getDefault().getPreferenceStore().getString(CONNECT_DATAFLOW_TYPE);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultConnectDataFlowType;
		} else {
			result = resultTemp.split(Separator);
		}
		return result;
	}
	/**
	 * Data Flow Type��ݒ肷��
	 * 
	 * @param dataFlowType
	 *            Data Flow Type���X�g
	 */
	public void setDataFlowTypes(ArrayList<String> dataflowType) {
		String[] oldDataFlowType = getDataFlowTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_DATAFLOW_TYPE, convertList2String(dataflowType));
		propertyChangeSupport.firePropertyChange(CONNECT_DATAFLOW_TYPE, oldDataFlowType, dataflowType);
	}

	/**
	 * Subscription Type���擾����
	 * 
	 * @return Subscription Type���X�g
	 */
	public String[] getSubscriptionTypes() {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(CONNECT_SUBSCRIPTION_TYPE, "");

		String resultTemp = RTSystemEditorPlugin.getDefault().getPreferenceStore().getString(CONNECT_SUBSCRIPTION_TYPE);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultConnectSubscriptionType;
		} else {
			result = resultTemp.split(Separator);
		}
		return result;
	}
	/**
	 * subscription Type��ݒ肷��
	 * 
	 * @param subscriptionType
	 *            subscription Type���X�g
	 */
	public void setSubscriptionTypes(ArrayList<String> subscriptionType) {
		String[] oldSubscriptionType = getSubscriptionTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_SUBSCRIPTION_TYPE, convertList2String(subscriptionType));
		propertyChangeSupport.firePropertyChange(CONNECT_SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType);
	}

	private static String convertList2String(ArrayList<String> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for(String target : source) {
			resultTemp.append(target);
			resultTemp.append(Separator);
		}
		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}

}
