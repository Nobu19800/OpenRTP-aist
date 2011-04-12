package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

public class BuilderViewPreferenceManager {
	private static BuilderViewPreferenceManager __instance = new BuilderViewPreferenceManager();

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static BuilderViewPreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * Component Color�̃L�[
	 */
	public static final String COLOR_COMPONENT = BuilderViewPreferenceManager.class.getName()
			+ "COMPONENT_COLOR";
	/**
	 * DataInPort Color�̃L�[
	 */
	public static final String COLOR_DATAINPORT = BuilderViewPreferenceManager.class.getName()
			+ "DATA_INPORT_COLOR";

	/**
	 * DataOutPort Color�̃L�[
	 */
	public static final String COLOR_DATAOUTPORT = BuilderViewPreferenceManager.class.getName()
			+ "DATA_OUTPORT_COLOR";
	/**
	 * ServicePort Color�̃L�[
	 */
	public static final String COLOR_SERVICEPORT = BuilderViewPreferenceManager.class.getName()
			+ "SERVICE_PORT_COLOR";

	/**
	 * Service I/F Color�̃L�[
	 */
	public static final String COLOR_SERVICEIF = BuilderViewPreferenceManager.class.getName()
			+ "SERVICE_IF_COLOR";

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �f�t�H���g�̐F���Ǘ�����}�b�v
	 */
	public static final Map<String, RGB> defaultRGBMap = new HashMap<String, RGB>();
	static {
		defaultRGBMap.put(COLOR_COMPONENT, new RGB(136, 190, 240));
		defaultRGBMap.put(COLOR_DATAINPORT, new RGB(55, 97, 217));
		defaultRGBMap.put(COLOR_DATAOUTPORT, new RGB(55, 97, 217));
		defaultRGBMap.put(COLOR_SERVICEPORT, new RGB(192, 192, 192));
		defaultRGBMap.put(COLOR_SERVICEIF, new RGB(101, 136, 22));
	}

	/**
	 * �L���b�V�������F�i���\�[�X�j���Ǘ�����}�b�v
	 */
	private static transient final Map<String, Color> cachedColorMap = new HashMap<String, Color>();

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
		RGB result = PreferenceConverter.getColor(RtcBuilderPlugin.getDefault()
				.getPreferenceStore(), key);
		if (result == PreferenceConverter.COLOR_DEFAULT_DEFAULT) { // caution
			// "=="
			result = defaultRGBMap.get(key);
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
