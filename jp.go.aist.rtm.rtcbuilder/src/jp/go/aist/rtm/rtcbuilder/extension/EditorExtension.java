package jp.go.aist.rtm.rtcbuilder.extension;

import java.util.ArrayList;
import java.util.List;

public abstract class EditorExtension {

	public static String RTC_PROFILE_PARAMETERS_INAPPLICABLE = "RTC_PROFILE_PARAMETERS_INAPPLICABLE";

	public static String RTC_PROFILE_DATA_PORTS_INAPPLICABLE = "RTC_PROFILE_DATA_PORTS_INAPPLICABLE";

	public static String RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE = "RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE";

	public static String GENERATE_BUTTON_SECTION_INAPPLICABLE = "GENERATE_BUTTON_SECTION_INAPPLICABLE";

	// �����Ώی���
	public abstract String getManagerKey();

	// ����̃^�O��K�p�ΏۊO�Ƃ��邩�ۂ�
	@Deprecated
	public List<String> getInapplicables() {
		// �K�p�ΏۊO�Ƃ���ꍇ�́A�I�[�o�[���C�h���ď��
		// ��`����Ă���萔��List�Ƃ��ĕԂ�
		return null;
	}

	/**
	 * �������������t�H�[���̗v�f(Widget)�̖��O���X�g��Ԃ��܂��B
	 * 
	 * �u�t�H�[����.�Z�N�V������.�v�f���v�Ŏw�肵�܂��B
	 * 
	 * �Z�N�V�����S�̂��w�肷��ꍇ�́A�v�f���Ɂu*�v���w�肵�܂��B
	 * 
	 * ex) basic.profile.moduleName
	 * 
	 * dateport.inPort.table
	 * 
	 * dataport.outPort.*
	 * 
	 * @return �v�f(Widget)�̖��O���X�g�B�f�t�H���g�͋�̃��X�g�B
	 */
	public List<String> getDisableFormWidgets() {
		return new ArrayList<String>();
	}

}
