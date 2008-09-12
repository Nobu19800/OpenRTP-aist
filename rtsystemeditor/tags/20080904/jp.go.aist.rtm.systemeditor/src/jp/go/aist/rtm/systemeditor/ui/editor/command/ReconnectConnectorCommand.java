package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.Connector;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorSource;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorTarget;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^��ڑ����Ȃ����R�}���h
 * <p>
 * �����ł́A�̂̐ڑ���j�����ĐV�����ڑ����s���B
 */
public class ReconnectConnectorCommand extends Command {
	private ConnectorSource newSource;

	private ConnectorTarget newTarget;

	private ClearLineConstraintCommand clearLineConstraintCommand = new ClearLineConstraintCommand();

	private Connector connector;

	private ConnectorCreateManager manager;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param connector
	 *            �R�l�N�^
	 * @param manager
	 *            ConnectorCreateManager
	 */
	public ReconnectConnectorCommand(Connector connector,
			ConnectorCreateManager profileCreater) {
		this.connector = connector;
		this.manager = profileCreater;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean canExecute() {
		if (newSource != null) {
			manager.setFirst((Port) newSource);
			manager.setSecond((Port) connector.getTarget());
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

	private void reconnectSource(ConnectorSource source) {
		boolean result = connector.deleteConnectorR();
		if (result == false) {
			return;
		}

		connector.dettachSource();
		connector.setSource(source);

		result = connector.createConnectorR();
		if (result == false) {
			return;
		}

		connector.attachSource();
	}

	private void reconnectTarget(ConnectorTarget target) {
		boolean result = connector.deleteConnectorR();
		if (result == false) {
			return;
		}

		connector.dettachTarget();
		connector.setTarget(target);

		result = connector.createConnectorR();
		if (result == false) {
			return;
		}

		connector.attachTarget();
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
	public void setNewSource(ConnectorSource source) {
		this.newSource = source;
	}

	/**
	 * �V�����ڑ����ݒ肷��
	 * 
	 * @param source
	 *            �V�����ڑ���
	 */
	public void setNewTarget(ConnectorTarget target) {
		this.newTarget = target;
	}

}
