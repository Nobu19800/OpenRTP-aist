package jp.go.aist.rtm.systemeditor.ui.editor.command;

import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^���쐬����R�}���h
 */
public class CreateConnectorCommand extends Command {
	private ConnectorCreateManager manager;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param connector
	 *            �R�l�N�^
	 * @param manager
	 *            manager
	 */
	public CreateConnectorCommand(ConnectorCreateManager profileCreater) {
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (manager.getFirst() == null || manager.getSecond() == null
				|| manager.validate() == false) {
			return false;
		}

		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		manager.createProfileAndConnector(); //���������s���͕Ԃ��Ȃ����A�����K�v�Ȃ�Ԃ��悤�ɏC�����邱��
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

	public ConnectorCreateManager getManager() {
		return manager;
	}

}
