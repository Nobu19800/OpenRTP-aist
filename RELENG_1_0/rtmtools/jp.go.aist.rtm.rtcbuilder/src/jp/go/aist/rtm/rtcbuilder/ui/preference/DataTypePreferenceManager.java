package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class DataTypePreferenceManager {
	private static DataTypePreferenceManager __instance = new DataTypePreferenceManager();

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static DataTypePreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * IDL File�̃L�[
	 */
	public static final String IDLFILE_DIRECTORIES = DataTypePreferenceManager.class.getName()
			+ "IDLFILE_DIRECTORIES";
	
	public static List<String> defaultIdlFileDirectories = new ArrayList<String>();
	
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * DataType �pIDL�t�@�C���i�[�f�B���N�g���� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return IDLFile Directories �f�t�H���g�l
	 */
	public List<String> getIdlFileDirectories() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(IDLFILE_DIRECTORIES, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(IDLFILE_DIRECTORIES);
		List<String> result = Arrays.asList(resultTemp.split(File.pathSeparator));
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultIdlFileDirectories;
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
