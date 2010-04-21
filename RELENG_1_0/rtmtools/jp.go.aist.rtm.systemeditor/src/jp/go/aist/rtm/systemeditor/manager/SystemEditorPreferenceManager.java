package jp.go.aist.rtm.systemeditor.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;

import org.eclipse.jface.preference.IPreferenceStore;
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

	private static SystemEditorPreferenceManager __instance;
	static {
		__instance = new SystemEditorPreferenceManager();
	}

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
	 * RTC��ԐF�̃L�[�F UnKnown
	 */
	public static final String COLOR_RTC_STATE_UNCERTAIN = SystemEditorPreferenceManager.class
			.getName()
			+ "COLOR_RTC_STATE_UNCERTAIN";

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
	 * Push Policy�̃L�[
	 */
	private static final String CONNECT_PUSH_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_PUSH_POLICY";
	
	/**
	 * Buffer Full Policy�̃L�[
	 */
	private static final String CONNECT_BUFFER_FULL_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_BUFFER_FULL_POLICY";
	
	/**
	 * Buffer Empty Policy�̃L�[
	 */
	private static final String CONNECT_BUFFER_EMPTY_POLICY = SystemEditorPreferenceManager.class.getName()
			+ "CONNECT_BUFFER_EMPTY_POLICY";
	
	// �I�����C���G�f�B�^
	/** �R���|�[�l���g�A�N�V�����̎��s�m�F */
	public static final String CONFIRM_COMPONENT_ACTION = SystemEditorPreferenceManager.class
			.getName()
			+ ".CONFIRM_COMPONENT_ACTION";

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
		defaultRGBMap.put(COLOR_RTC_STATE_UNCERTAIN, new RGB(127, 127, 127));
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
		defaultInvervalMap.put(SYNC_SYSTEMEDITOR_INTERVAL, Integer.valueOf(1000));
	}

	/**
	 * �L���b�V�������F�i���\�[�X�j���Ǘ�����}�b�v
	 */
	private static transient final Map<String, Color> cachedColorMap = new HashMap<String, Color>();

	/**
	 * Interface Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectInterfaceType = {"corba_cdr"};

	/**
	 * DataFlow Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectDataFlowType = {"push", "pull"};
	
	/**
	 * subscription Type�̃f�t�H���g�l
	 */
	public static String[] defaultConnectSubscriptionType = {"flush", "new", "periodic"};

	/**
	 * Push Policy�̃f�t�H���g�l
	 */
	public static String[] defaultConnectPushPolicy = {"all", "fifo", "skip", "new"};
	
	/**
	 * Buffer Full Policy�̃f�t�H���g�l
	 */
	public static String[] defaultConnectBufferFullPolicy = {"overerite", "do_nothing", "block"};
	
	/**
	 * Buffer Empty Policy�̃f�t�H���g�l
	 */
	public static String[] defaultConnectBufferEmptyPolicy = {"readback", "do_nothing", "block"};
	
	IPreferenceStore store;

	public SystemEditorPreferenceManager() {
		this.store = RTSystemEditorPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static SystemEditorPreferenceManager getInstance() {
		return __instance;
	}
	/**
	 * �V���O���g�����Z�b�g����B�i��{�I�Ɏg�p���Ă͂Ȃ�Ȃ��B���j�b�g�e�X�g����̎��s�̂��߂ɒǉ��j
	 * 
	 */
	public static void setInstance(SystemEditorPreferenceManager instance) {
		__instance = instance;
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
			result = defaultInvervalMap.get(key).intValue();
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
		return getStringListStoreValue(CONNECT_INTERFACE_TYPE, defaultConnectInterfaceType);
	}
	/**
	 * Interface Type��ݒ肷��
	 * 
	 * @param interfaceType
	 *            Interface Type���X�g
	 */
	public void setInterfaceTypes(List<String> interfaceType) {
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
		return getStringListStoreValue(CONNECT_DATAFLOW_TYPE, defaultConnectDataFlowType);
	}
	/**
	 * Data Flow Type��ݒ肷��
	 * 
	 * @param dataFlowType
	 *            Data Flow Type���X�g
	 */
	public void setDataFlowTypes(List<String> dataflowType) {
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
		return getStringListStoreValue(CONNECT_SUBSCRIPTION_TYPE, defaultConnectSubscriptionType);
	}
	/**
	 * subscription Type��ݒ肷��
	 * 
	 * @param subscriptionType
	 *            subscription Type���X�g
	 */
	public void setSubscriptionTypes(List<String> subscriptionType) {
		String[] oldSubscriptionType = getSubscriptionTypes();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_SUBSCRIPTION_TYPE, convertList2String(subscriptionType));
		propertyChangeSupport.firePropertyChange(CONNECT_SUBSCRIPTION_TYPE, oldSubscriptionType, subscriptionType);
	}

	/**
	 * Push Policy���擾����
	 * 
	 * @return Push Policy���X�g
	 */
	public String[] getPushPolicies() {
		return getStringListStoreValue(CONNECT_PUSH_POLICY, defaultConnectPushPolicy);
	}
	/**
	 * Push Policy��ݒ肷��
	 * 
	 * @param pushPolicies
	 *            Push Policy���X�g
	 */
	public void setPushPolicies(List<String> pushPolicies) {
		String[] oldPushPolicies = getPushPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_PUSH_POLICY, convertList2String(pushPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_SUBSCRIPTION_TYPE, oldPushPolicies, pushPolicies);
	}

	/**
	 * Buffer Full Policy���擾����
	 * 
	 * @return Buffer Full Policy���X�g
	 */
	public String[] getBufferFullPolicies() {
		return getStringListStoreValue(CONNECT_BUFFER_FULL_POLICY, defaultConnectBufferFullPolicy);
	}
	/**
	 * Buffer Full Policy��ݒ肷��
	 * 
	 * @param bufferFullPolicies
	 *            Buffer Full Policy���X�g
	 */
	public void setBufferFullPolicies(List<String> bufferFullPolicies) {
		String[] oldPushPolicies = getBufferFullPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_BUFFER_FULL_POLICY, convertList2String(bufferFullPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_BUFFER_FULL_POLICY, oldPushPolicies, bufferFullPolicies);
	}
	
	/**
	 * Buffer Empty Policy���擾����
	 * 
	 * @return Buffer Empty Policy���X�g
	 */
	public String[] getBufferEmptyPolicies() {
		return getStringListStoreValue(CONNECT_BUFFER_EMPTY_POLICY, defaultConnectBufferEmptyPolicy);
	}
	/**
	 * Buffer Empty Policy��ݒ肷��
	 * 
	 * @param bufferEmptyPolicies
	 *            Buffer Empty Policy���X�g
	 */
	public void setBufferEmptyPolicies(List<String> bufferEmptyPolicies) {
		String[] oldPushPolicies = getBufferEmptyPolicies();
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setValue(CONNECT_BUFFER_EMPTY_POLICY, convertList2String(bufferEmptyPolicies));
		propertyChangeSupport.firePropertyChange(CONNECT_BUFFER_EMPTY_POLICY, oldPushPolicies, bufferEmptyPolicies);
	}
	private static String convertList2String(List<String> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for(String target : source) {
			resultTemp.append(target);
			resultTemp.append(Separator);
		}
		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}

	/**
	 * �R���|�[�l���g�A�N�V�����̎��s�m�F����
	 * 
	 * @return ���s�m�F������ꍇ�� true
	 */
	public boolean isConfirmComponentAction() {
		store.setDefault(CONFIRM_COMPONENT_ACTION, false);
		return store.getBoolean(CONFIRM_COMPONENT_ACTION);
	}

	/**
	 * �R���|�[�l���g�A�N�V�����̎��s�m�F�ݒ�
	 * 
	 * @param b
	 *            ���s�m�F������ꍇ�� true
	 */
	public void setConfirmComponentAction(boolean b) {
		store.setValue(CONFIRM_COMPONENT_ACTION, b);
	}

	/**
	 * �R���|�[�l���g�A�N�V�����̎��s�m�F������
	 */
	public void resetConfirmComponentAction() {
		setConfirmComponentAction(false);
	}

	private String[] getStringListStoreValue(String key, String[] defaultValue) {
		RTSystemEditorPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		String resultTemp = RTSystemEditorPlugin.getDefault().getPreferenceStore().getString(key);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultValue;
		} else {
			result = resultTemp.split(Separator);
		}
		return result;
	}
}
