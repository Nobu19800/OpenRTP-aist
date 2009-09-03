package jp.go.aist.rtm.rtcbuilder.manager;

import java.util.List;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.ui.editors.LanguageEditorSection;

public abstract class GenerateManager {

	//�����Ώی���
	public abstract String getManagerKey();
	//�X�P���g���R�[�h�̐���
	public abstract List<GeneratedResult> generateTemplateCode(RtcParam rtcParam);
	//�����Ώی���p�J���v���O�C�����̎擾
	public LanguageProperty getLanguageProperty(RtcParam rtcParam) {
		return null;
	}
	//�����Ώی���p���̓y�[�W�̎擾
	public LanguageEditorSection getLanguageEditorSection() {
		return null;
	}

}
