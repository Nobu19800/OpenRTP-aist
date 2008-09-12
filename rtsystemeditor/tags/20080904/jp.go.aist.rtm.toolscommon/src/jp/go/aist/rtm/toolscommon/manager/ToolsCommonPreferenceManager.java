package jp.go.aist.rtm.toolscommon.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

/**
 * �ݒ���Ǘ�����}�l�[�W��
 * <p>
 * �ݒ���ɃA�N�Z�X����ɂ͂��̃C���X�^���X���g�p����
 */
public class ToolsCommonPreferenceManager {
	private static ToolsCommonPreferenceManager __instance = new ToolsCommonPreferenceManager();

	/**
	 * �^�C���A�E�g���莞��
	 */
	public static final String DEFAULT_TIMEOUT_PERIOD = ToolsCommonPreferenceManager.class
			.getName()
			+ "DEFAULT_TIMEOUT_PERIOD";
	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static ToolsCommonPreferenceManager getInstance() {
		return __instance;
	}
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �f�t�H���g�^�C���A�E�g���莞��
	 */
	public static final int defaultTimeoutPeriod = 3000;
	
	/**
	 * �f�t�H���g�^�C���A�E�g���莞�Ԃ��擾����
	 * 
	 * @param key
	 *            �L�[
	 * @return �f�t�H���g�|�[�g
	 */
	public int getDefaultTimeout(String key) {
		// �R���\�[������N�����ꂽ�ꍇ�ɂ́Aplugin�͑��݂��Ȃ��ׁAnull�ƂȂ�B
		if (ToolsCommonPlugin.getDefault() == null){
			return defaultTimeoutPeriod;
		}
		
		ToolsCommonPlugin.getDefault().getPreferenceStore().setDefault(key, -1);

		int result = ToolsCommonPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultTimeoutPeriod;
		}

		return result;
	}

	/**
	 * �f�t�H���g�^�C���A�E�g���莞�Ԃ�ݒ肷��
	 * 
	 * @param key
	 *            �L�[
	 * @param interval
	 *            �Ԋu
	 */
	public void setDefaultTimeout(String key, int defaultTimeout) {
		int oldDefaultTimeout = getDefaultTimeout(key);

		ToolsCommonPlugin.getDefault().getPreferenceStore().setValue(key,
				defaultTimeout);

		propertyChangeSupport.firePropertyChange(key, oldDefaultTimeout,
				defaultTimeout);
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
