package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.GraphicalConnectorCreateManager;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^��ڑ����Ȃ����R�}���h
 * <p>
 * �����ł́A�̂̐ڑ���j�����ĐV�����ڑ����s���B
 */
public class ReconnectConnectorCommand extends Command {
	private Port newSource;

	private Port newTarget;

	private ClearLineConstraintCommand clearLineConstraintCommand = new ClearLineConstraintCommand();

	private PortConnector connector;

	private GraphicalConnectorCreateManager manager;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param connector
	 *            �R�l�N�^
	 * @param manager
	 *            ConnectorCreateManager
	 */
	public ReconnectConnectorCommand(PortConnector connector,
			GraphicalConnectorCreateManager profileCreater) {
		this.connector = connector;
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (newSource != null) {
			manager.setFirst(newSource);
			manager.setSecond(connector.getTarget());
			if (manager.validate() == false) {
				return false;
			}
		}

		if (newTarget != null) {
			manager.setFirst((Port) connector.getSource());
			manager.setSecond((Port) newTarget);
			if (manager.validate() == false) {
				return false;
			}
		}

		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		if (newSource != null) {
			manager.setFirst((Port) newSource);
			manager.setSecond((Port) connector.getTarget());

			ConnectorProfile profile = manager.getConnectorProfile();
			if (profile != null) {
				connector.deleteConnectorR();
				manager.connectR(profile);
			}
		}

		if (newTarget != null) {
			manager.setFirst((Port) connector.getSource());
			manager.setSecond((Port) newTarget);
			
			ConnectorProfile profile = manager.getConnectorProfile();
			if (profile != null) {
				connector.deleteConnectorR();
				manager.connectR(profile);
			}
		}

		clearLineConstraintCommand.setModel(connector);
		clearLineConstraintCommand.execute();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}

	/**
	 * �V�����ڑ�����ݒ肷��
	 * 
	 * @param source
	 *            �V�����ڑ���
	 */
	public void setNewSource(Port source) {
		this.newSource = source;
	}

	/**
	 * �V�����ڑ����ݒ肷��
	 * 
	 * @param source
	 *            �V�����ڑ���
	 */
	public void setNewTarget(Port target) {
		this.newTarget = target;
	}

}
