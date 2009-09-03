package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialog;
import jp.go.aist.rtm.systemeditor.ui.dialog.NewCompositeComponentDialogData;
import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.toolscommon.model.component.Component;

public class CreateCompositeComponentJob1 extends TimeoutWrappedJob {
	private NewCompositeComponentDialog dialog;
	private AbstractSystemDiagramEditor targetEditor;

	@Override
	protected Object executeCommand() {
		return 	createCompositeComponent();
	}

	private Object createCompositeComponent() {
		if (!targetEditor.isOnline()) {
			// �I�t���C���̏ꍇ
			return NewCompositeComponentDialogData
					.createCompositeComponentSpecification(dialog);
		} else if (dialog.getCompositeType().equals(
				Component.COMPOSITETYPE_GROUPING)) {
			// �R���|�W�b�g��ʂ�Grouping�̏ꍇ 2009.01.06
			return NewCompositeComponentDialogData
					.createCompositeComponentSpecification(dialog);
		} else {
			// �I�����C���̏ꍇ
			// �}�l�[�W���Ń����[�g�I�u�W�F�N�g�����i���݂�CORBA�ɂ����Ή��j
			return dialog.createComponentR();
		}
	}

	public void setDialog(NewCompositeComponentDialog dialog) {
		this.dialog = dialog;
	}

	public void setTargetEditor(AbstractSystemDiagramEditor targetEditor) {
		this.targetEditor = targetEditor;
	}

}
