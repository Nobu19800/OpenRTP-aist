package jp.go.aist.rtm.logview.preferences;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.logview.LogviewPlugin;

public class PreferenceManager {
	private static PreferenceManager __instance = new PreferenceManager();

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static PreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * �f�t�H���g�`�掞�Ԏ���
	 */
	public static final int defaultRedrawPeriod = 200;

	/**
	 * �f�t�H���g�E�R���t�B�M�����[�V�����E�t�@�C��
	 */
	public static final String defaultConfigFile = "rtc.conf";

	/**
	 * �R���t�B�M�����[�V�����E�t�@�C���̃L�[
	 */
	public static final String CONFIGURATION_FILE = PreferenceManager.class
			.getName()
			+ "CONFIGURATION_FILE";

	/**
	 * �`�掞�Ԏ����̃L�[
	 */
	public static final String REDRAW_RERIOD = PreferenceManager.class
			.getName()
			+ "REDRAW_PERIOD";

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �R���t�B�M�����[�V�����E�t�@�C����ݒ肷��
	 * 
	 * @param key
	 *            �L�[
	 * @param configFile
	 *            �R���t�B�M�����[�V�����E�t�@�C��
	 */
	public void setConfiguratiolFile(String key, String configFile) {
		String oldConfigFile = getConfigurationFile(key);

		LogviewPlugin.getDefault().getPreferenceStore().setValue(key, configFile);

		propertyChangeSupport.firePropertyChange(key, oldConfigFile, configFile);
	}

	/**
	 * �R���t�B�M�����[�V�����E�t�@�C�����擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �R���t�B�M�����[�V�����E�t�@�C��
	 */
	public String getConfigurationFile(String key) {
		LogviewPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		String result = LogviewPlugin.getDefault().getPreferenceStore().getString(key);
		if (result.equals("")) { // defaultvalue
			result = defaultConfigFile;
		}

		return result;
	}

	/**
	 * �`�掞�Ԏ�����ݒ肷��
	 * 
	 * @param key
	 *            �L�[
	 * @param configFile
	 *            �`�掞�Ԏ���
	 */
	public void setRedrawPeriod(String key, int interval) {
		int oldPeriod = getRedrawPeriod(key);

		LogviewPlugin.getDefault().getPreferenceStore().setValue(key, interval);

		propertyChangeSupport.firePropertyChange(key, oldPeriod, interval);
	}

	/**
	 * �`�掞�Ԏ������擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �`�掞�Ԏ���
	 */
	public int getRedrawPeriod(String key) {
		LogviewPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		int result = LogviewPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == 0) { // defaultvalue
			result = defaultRedrawPeriod;
		}

		return result;
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
}
