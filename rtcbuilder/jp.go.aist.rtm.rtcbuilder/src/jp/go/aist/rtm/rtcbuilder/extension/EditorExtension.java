package jp.go.aist.rtm.rtcbuilder.extension;

import java.util.List;

public abstract class EditorExtension {
	
	public static String RTC_PROFILE_PARAMETERS_INAPPLICABLE = "RTC_PROFILE_PARAMETERS_INAPPLICABLE";

	public static String RTC_PROFILE_DATA_PORTS_INAPPLICABLE = "RTC_PROFILE_DATA_PORTS_INAPPLICABLE";
	public static String RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE = "RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE";

	public static String GENERATE_BUTTON_SECTION_INAPPLICABLE = "GENERATE_BUTTON_SECTION_INAPPLICABLE";

	//�����Ώی���
	public abstract String getManagerKey();

	// ����̃^�O��K�p�ΏۊO�Ƃ��邩�ۂ�
	public List<String> getInapplicables(){
		// �K�p�ΏۊO�Ƃ���ꍇ�́A�I�[�o�[���C�h���ď��
		// ��`����Ă���萔��List�Ƃ��ĕԂ�
		return null;
	}

}
