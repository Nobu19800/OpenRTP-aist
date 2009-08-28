package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.action.CompositeComponentHelper;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.gef.commands.Command;

/**
 * Rtc���폜����폜�R�}���h
 */
public class DeleteCommand extends Command {
	private SystemDiagram parent;

	private Component target;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DeleteCommand.1"))) //$NON-NLS-1$
			return;
		
		disconnectAll();

		// �J�����G�f�B�^�����
		ComponentUtil.closeCompositeComponent(target);
		
		// �폜���s
		if (parent.getCompositeComponent()!= null) {
			// ���g�������R���|�[�l���g�̎q�̏ꍇ�͕����R���|�[�l���g����O��
			parent.getCompositeComponent().removeComponentR(target);
			// �e�G�f�B�^�Ɏ��g��ǉ�����
			SystemDiagram parentSystemDiagram = parent.getParentSystemDiagram();
			parentSystemDiagram.addComponent(target);
			// �l�X�g���Ă���ꍇ�̓����o�[�̍Đݒ肪�K�v
			if (parentSystemDiagram.getCompositeComponent() != null) {
				parentSystemDiagram.getCompositeComponent().setComponentsR(parentSystemDiagram.getComponents());
			}
			// ��ʍX�V
			ComponentUtil.findEditor(parentSystemDiagram).refresh();
		} else {		
			// �_�C�A�O��������R���|�[�l���g���폜����
			parent.removeComponent(target);
		}
		
		// ��ʍX�V
		ComponentUtil.findEditor(parent).refresh();
	}

	/**
	 * �I�t���C���G�f�B�^�̏ꍇ�ɂ����A�ڑ������ׂĉ�������
	 */
	private void disconnectAll() {
		if (target.inOnlineSystemDiagram()) return;
		for (Object obj: target.getPorts()) {
			Port port = (Port) obj;
			port.disconnectAll();
		}
	}

	/**
	 * �e�̃V�X�e���_�C�A�O������ݒ肷��
	 * 
	 * @param parent
	 *            �e�̃V�X�e���_�C�A�O����
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * �ύX�Ώۂ̃R���|�[�l���g
	 * 
	 * @param target
	 *            �R���|�[�l���g
	 */
	public void setTarget(Component target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDocs}
	 */
	public void undo() {
	}
}
