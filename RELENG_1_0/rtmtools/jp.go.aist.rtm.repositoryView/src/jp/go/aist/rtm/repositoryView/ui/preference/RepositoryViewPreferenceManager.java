package jp.go.aist.rtm.repositoryView.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;

/**
 * ���|�W�g���r���[�̊e��ݒ���Ǘ�����N���X
 *
 */
public class RepositoryViewPreferenceManager {
	private static RepositoryViewPreferenceManager __instance = new RepositoryViewPreferenceManager();

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static RepositoryViewPreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * �x�����̃L�[
	 */
	public static final String CAUTION_COUNT = RepositoryViewPreferenceManager.class.getName()
			+ "CAUTION_COUNT";
	/**
	 * �v���p�e�B�t�@�C���ʒu�̃L�[
	 */
	public static final String PROPERTY_FILE_LOCATION = RepositoryViewPreferenceManager.class.getName()
			+ "PROPERTY_FILE_LOCATION";

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �f�t�H���g�̌x����
	 */
	public static final int defaultCautionCount = 500;
	/**
	 * �f�t�H���g�̃v���p�e�B�t�@�C���ʒu
	 */
	public static final String defaultPropertyFileLocation = "";

	/**
	 * �x������ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaulCount �ݒ�l
	 */
	public void setCaution_Count(int defaulCount) {
		int oldCautionCount = defaultCautionCount;

		RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(CAUTION_COUNT, defaulCount);

		propertyChangeSupport.firePropertyChange(CAUTION_COUNT, oldCautionCount, defaulCount);
	}
	
	/**
	 * �x�������擾����
	 * 
	 * @param key �L�[
	 * @return cautionCount �ݒ�l
	 */
	public int getCaution_Count() {
		RepositoryViewPlugin.getDefault().getPreferenceStore().setDefault(CAUTION_COUNT, "");

		int resultTemp = RepositoryViewPlugin.getDefault().getPreferenceStore().getInt(CAUTION_COUNT);
		int result;
		if (resultTemp == 0 ) { // defaultvalue
			result = defaultCautionCount;
		} else {
			result = resultTemp;
		}
		return result;
	}

	/**
	 * Property File �ʒu��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaulLocation �t�@�C���ʒu
	 */
	public void setPropertyFile_Location(String defaulLocation) {
		String oldPropertyLocation = defaultPropertyFileLocation;

		RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(PROPERTY_FILE_LOCATION, defaulLocation);

		propertyChangeSupport.firePropertyChange(PROPERTY_FILE_LOCATION, oldPropertyLocation, defaulLocation);
	}
	
	/**
	 * Property File �ʒu���擾����
	 * 
	 * @param key �L�[
	 * @return propertyLocation �v���p�e�B�t�@�C���ʒu
	 */
	public String getPropertyFile_Location() {
		RepositoryViewPlugin.getDefault().getPreferenceStore().setDefault(PROPERTY_FILE_LOCATION, "");

		String resultTemp = RepositoryViewPlugin.getDefault().getPreferenceStore().getString(PROPERTY_FILE_LOCATION);
		String result;
		if( resultTemp.equals("") ) { // defaultvalue
			result = defaultPropertyFileLocation;
		} else {
			result = resultTemp;
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
