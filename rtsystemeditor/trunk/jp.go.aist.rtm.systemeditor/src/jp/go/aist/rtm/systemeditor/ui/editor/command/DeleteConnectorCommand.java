package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.action.CompositeComponentHelper;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^���폜����R�}���h
 */
public class DeleteConnectorCommand extends Command {
	private PortConnector connector;

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

		CompositeComponentHelper.synchronizeManually(connector.getSource());
	}

	/**
	 * �R�l�N�^��ݒ肷��
	 * 
	 * @param connector
	 *            �R�l�N�^
	 */
	public void setConnector(PortConnector connector) {
		this.connector = connector;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		boolean result = connector.createConnectorR();
		if (result == false) {
			return;
		}
	}
}
