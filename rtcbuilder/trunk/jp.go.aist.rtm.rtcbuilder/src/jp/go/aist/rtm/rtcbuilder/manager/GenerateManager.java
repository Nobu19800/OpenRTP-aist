package jp.go.aist.rtm.rtcbuilder.manager;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;

public abstract class GenerateManager {
	public static String RTC_PROFILE_PARAMETERS_INAPPLICABLE = "RTC_PROFILE_PARAMETERS_INAPPLICABLE";
	public static String RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE = "RTC_PROFILE_SERVICE_PORTS_INAPPLICABLE";

	//�����Ώی���
	public abstract String getManagerKey();
	//�����Ώی��ꖼ��(�����p)
	public abstract String getLangArgList();
	//�X�P���g���R�[�h�̐���
	public abstract List<GeneratedResult> generateTemplateCode(RtcParam rtcParam);
	//�����Ώی���p�J���v���O�C�����̎擾
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		return null;
	}
	//�����Ώی��ꖼ��(�����p)
	public String getTargetVersion() {
		return IRtcBuilderConstants.RTM_VERSION_042;
	}
	// ����̃^�O��K�p�ΏۊO�Ƃ��邩�ۂ�
	public List<String> getInapplicables(){
		// �K�p�ΏۊO�Ƃ���ꍇ�́A�I�[�o�[���C�h���ď��
		// ��`����Ă���萔��List�Ƃ��ĕԂ�
		return null;
	}
}
