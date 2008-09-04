package jp.go.aist.rtm.repositoryView.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;

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

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �f�t�H���g�̌x����
	 */
	public static final int defaultCautionCount = 500;

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
