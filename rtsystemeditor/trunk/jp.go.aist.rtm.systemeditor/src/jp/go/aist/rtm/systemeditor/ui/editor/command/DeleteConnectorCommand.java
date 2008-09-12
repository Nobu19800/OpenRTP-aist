package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.Connector;

import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^���폜����R�}���h
 */
public class DeleteConnectorCommand extends Command {
	private Connector connector;

	public DeleteConnectorCommand() {
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		boolean result = connector.deleteConnectorR();
		if (result == false) {
			return;
		}

		connector.dettachSource();
		connector.dettachTarget();
	}

	/**
	 * �R�l�N�^��ݒ肷��
	 * 
	 * @param connector
	 *            �R�l�N�^
	 */
	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		connector.attachSource();
		connector.attachTarget();

		boolean result = connector.createConnectorR();
		if (result == false) {
			return;
		}
	}
}
