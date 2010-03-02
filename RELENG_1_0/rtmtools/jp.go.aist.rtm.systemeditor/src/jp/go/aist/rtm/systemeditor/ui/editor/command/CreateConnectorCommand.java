package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^���쐬����R�}���h
 */
public class CreateConnectorCommand extends Command {
	private GraphicalConnectorCreateManager manager;
	private EditPartViewer viewer;
	private boolean result;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param connector
	 *            �R�l�N�^
	 * @param manager
	 *            manager
	 */
	public CreateConnectorCommand(GraphicalConnectorCreateManager profileCreater) {
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (manager.getFirst() == null) return false;
		if (manager.getSecond() == null) return false;
		return manager.validate();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		viewer.deselectAll();
		result = manager.createProfileAndConnector(); //���������s���͕Ԃ��Ȃ����A�����K�v�Ȃ�Ԃ��悤�ɏC�����邱��
	}

	public boolean getResult() {
		return result;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		if (true) {
			// �R�}���h���s����profileCreater�ɂ��A
			// �����������Ƃ��ăL�����Z������ƁAexecute�i�j���Ɏ��s������߂邱�ƂɂȂ邪�A
			// �������Ď���߂��ꍇ�ɂ��A�R�}���h�����s���ꂽ���̂Ƃ���Undo���L���ɂȂ��Ă��܂��B
			// ��������܂�������悤�ɂȂ�܂ł́AUNDO�͓���B�B�B

			throw new RuntimeException();
		}
	}

	public GraphicalConnectorCreateManager getManager() {
		return manager;
	}
	public void setViewer(EditPartViewer viewer) {
		this.viewer = viewer;
	}

}
