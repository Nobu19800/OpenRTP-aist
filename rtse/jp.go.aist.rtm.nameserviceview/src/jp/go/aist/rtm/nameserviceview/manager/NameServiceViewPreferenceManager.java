package jp.go.aist.rtm.nameserviceview.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;


/**
 * �ݒ���Ǘ�����}�l�[�W��
 * <p>
 * �ݒ���ɃA�N�Z�X����ɂ͂��̃C���X�^���X���g�p����
 */
public class NameServiceViewPreferenceManager {
	private static NameServiceViewPreferenceManager __instance = new NameServiceViewPreferenceManager();

	/**
	 * �����Ԋu�̃L�[�F �l�[���T�[�o
	 */
	public static final String SYNC_NAMESERVER_INTERVAL = NameServiceViewPreferenceManager.class
			.getName()
			+ "SYNC_NAMESERVICE_INTERVAL";

	/**
	 * �f�t�H���g�ڑ��|�[�g
	 */
	public static final String DEFAULT_CONNECTION_PORT = NameServiceViewPreferenceManager.class
			.getName()
			+ "DEFAULT_CONNECTION_PORT";

	/**
	 * �^�C���A�E�g���莞��
	 */
	public static final String DEFAULT_TIMEOUT_PERIOD = NameServiceViewPreferenceManager.class
			.getName()
			+ "DEFAULT_TIMEOUT_PERIOD";

	/**
	 * �f�t�H���g�̓����Ԋu���Ǘ�����}�b�v
	 */
	public static final Map<String, Integer> defaultInvervalMap = new HashMap<String, Integer>();
	static {
		defaultInvervalMap.put(SYNC_NAMESERVER_INTERVAL, 1000);
	}

	/**
	 * �f�t�H���g�ڑ��|�[�g
	 */
	public static final String defaultConnectionPort = new String("2809");


	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static NameServiceViewPreferenceManager getInstance() {
		return __instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �Ԋu���擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �Ԋu
	 */
	public int getInterval(String key) {
		NameServiceViewPlugin.getDefault().getPreferenceStore().setDefault(key, -1);

		int result = NameServiceViewPlugin.getDefault().getPreferenceStore().getInt(key);
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

		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(key, interval);

		propertyChangeSupport.firePropertyChange(key, oldInterval, interval);
	}

	/**
	 * �f�t�H���g�|�[�g���擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �f�t�H���g�|�[�g
	 */
	public String getDefaultPort(String key) {
		if (NameServiceViewPlugin.getDefault() == null){
			return defaultConnectionPort;
		}
		NameServiceViewPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		String result = NameServiceViewPlugin.getDefault().getPreferenceStore().getString(
				key);
		if (result.equals("")) { // defaultvalue
			result = defaultConnectionPort;
		}

		return result;
	}

	/**
	 * �f�t�H���g�ڑ��|�[�g��ݒ肷��
	 * 
	 * @param key
	 *            �L�[
	 * @param interval
	 *            �Ԋu
	 */
	public void setDefaultPort(String key, String defaultPort) {
		String oldDefaultPort = getDefaultPort(key);

		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(key, defaultPort);

		propertyChangeSupport.firePropertyChange(key, oldDefaultPort,
				defaultPort);
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
	 * �f�t�H���g�Ԋu�̃}�b�v���擾����
	 * 
	 * @return �f�t�H���g�Ԋu�̃}�b�v
	 */
	public Map<String, Integer> getDefaultIntervalMap() {
		return defaultInvervalMap;
	}

}
