package jp.go.aist.rtm.systemeditor.ui.editor.action;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * RTC���ړ�����A�N�V����
 */
public class MoveComponentAction extends SelectionAction {

	/**
	 * �傫���ړ�����ꍇ�̃C���^�[�o��
	 */
	public static final int MOVE_L_SIZE = 10;

	/**
	 * ��������Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_UP_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_UP";

	/**
	 * ���������Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_DOWN_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_DOWN";

	/**
	 * �������E�Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_RIGHT_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_RIGHT";

	/**
	 * ���������Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_LEFT_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_LEFT";

	/**
	 * �傫����Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_UP_L_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_UP_L";

	/**
	 * �傫�����Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_DOWN_L_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_DOWN_L";

	/**
	 * �傫���E�Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_RIGHT_L_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_RIGHT_L";

	/**
	 * �傫�����Ɉړ�����A�N�V������ID�F����ID��plugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String MOVE_LEFT_L_ACTION_ID = MoveComponentAction.class
			.getName()
			+ "_LEFT_L";

	/**
	 * �R���X�g���N�^
	 * @param part IWorkbenchPart
	 * @param id�@�A�N�V������ID
	 */
	public MoveComponentAction(IWorkbenchPart part, String id) {
		super(part);
		setId(id);
		setText("Move");
	}

	/**
	 * {@inheritDoc}
	 */
	protected boolean calculateEnabled() {
		Command cmd = createMoveCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * �ړ��̃R�}���h���쐬����
	 * @param selectedEditParts �Ώۂ�EditPart
	 * @return �쐬�����R�}���h
	 */
	protected Command createMoveCommand(List selectedEditParts) {
		if (selectedEditParts.isEmpty())
			return null;
		if (!(selectedEditParts.get(0) instanceof EditPart))
			return null;

		ChangeBoundsRequest request = new ChangeBoundsRequest();
		request.setType(RequestConstants.REQ_MOVE);

		Point moveDeltaPoint = new Point();
		if (MOVE_UP_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(0, -1);
		} else if (MOVE_DOWN_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(0, 1);
		} else if (MOVE_RIGHT_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(1, 0);
		} else if (MOVE_LEFT_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(-1, 0);
		} else if (MOVE_UP_L_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(0, -MOVE_L_SIZE);
		} else if (MOVE_DOWN_L_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(0, MOVE_L_SIZE);
		} else if (MOVE_RIGHT_L_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(MOVE_L_SIZE, 0);
		} else if (MOVE_LEFT_L_ACTION_ID.equals(getId())) {
			moveDeltaPoint = new Point(-MOVE_L_SIZE, 0);
		}

		request.setMoveDelta(moveDeltaPoint);

		CompoundCommand compoundCmd = new CompoundCommand();
		for (int i = 0; i < selectedEditParts.size(); i++) {
			EditPart editPart = (EditPart) selectedEditParts.get(i);
			if (editPart.understandsRequest(request)) {
				Command cmd = editPart.getCommand(request);
				if (cmd != null)
					compoundCmd.add(cmd);
			}
		}

		return compoundCmd;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run() {
		execute(createMoveCommand(getSelectedObjects()));
	}

	/**
	 * {@inheritDoc}
	 */
	protected void init() {
		super.init();
		setEnabled(false);
	}

}
