package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;

/**
 * �����R���|�[�l���g�̏����Ɋւ���w���p�[�N���X
 *
 */
public class CompositeComponentHelper {

	private static final String CONFIRM_TITLE = Messages.getString("CompositeComponentHelper.confirm.title"); // Confirm

	/**
	 * �q�E�B���h�E���J���Ă���Ƃ��ɍ폜�܂��͉������s�������m�F����
	 * @param target
	 * @param msg
	 * @return
	 */
	public static boolean openConfirm(Component target, String msg) {
		if (!target.isCompositeComponent()) return true;
		SystemDiagram childDiagram = target.getChildSystemDiagram();
		if (childDiagram == null) return true;
		IEditorPart editor = ComponentUtil.findEditor(childDiagram);
		if (editor == null) return true;
		
		return MessageDialog.openConfirm(editor.getSite()
						.getShell(), CONFIRM_TITLE, msg);
	}

	/**
	 * �����X���b�h����~���Ă���Ƃ��Ɏ蓮�œ��������s����
	 * @param parent	���[�g�̃V�X�e���_�C�A�O����
	 */
	public static void synchronizeAll(SystemDiagram parent) {
		if (parent == null) return;
		parent.synchronizeManually();
	}

	/**
	 * �����X���b�h����~���Ă���Ƃ��Ƀ��[�g�̃V�X�e���_�C�A�O��������蓮�œ��������s����
	 * @param parent	�ύX�̂������R���|�[�l���g
	 */
	public static void synchronizeAll(Component parent) {
		SystemDiagram childDiagram = parent.getChildSystemDiagram();
		synchronizeAll(childDiagram.getRootDiagram());
	}
	

	/**
	 * �����X���b�h����~���Ă���Ƃ��Ɏ蓮�œ��������s����
	 * @param source �����Ώۂ̃|�[�g
	 */
	public static void synchronizeManually(Port source) {
		if (source.eContainer() == null) return;
		if (!(source.eContainer().eContainer() instanceof SystemDiagram)) return;
		SystemDiagram diagram = (SystemDiagram) source.eContainer().eContainer();
		diagram.synchronizeManually();
	}

}
