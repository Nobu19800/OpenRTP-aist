package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import jp.go.aist.rtm.systemeditor.ui.editor.command.DeleteConnectorCommand;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * �R�l�N�^��EditPolicy�N���X
 */
public class PortConnectorEditPolicy extends ConnectionEditPolicy {
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command getDeleteCommand(GroupRequest request) {
		DeleteConnectorCommand result = new DeleteConnectorCommand();
		result.setConnector((PortConnector) getHost().getModel());

		return result;
	}
}
