package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.gef.commands.Command;

/**
 * ���C���̐�����N���A����R�}���h
 */
public class ClearLineConstraintCommand extends Command {
	private ModelElement model;

	@SuppressWarnings("unchecked")
	private Map<PortConnector, Map> oldRoutingConstraint = new IdentityHashMap<PortConnector, Map>();

	/**
	 * �ύX�Ώۂ̃��f����ݒ肷��
	 * 
	 * @param model
	 *            �ύX�Ώۂ̃��f��
	 */
	public void setModel(ModelElement model) {
		this.model = model;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		model.accept(new Visiter() {
			@SuppressWarnings("unchecked")
			public void visit(ModelElement element) {
				List<PortConnector> connnectionList = new ArrayList<PortConnector>();
				if (element instanceof PortConnector) {
					connnectionList.add((PortConnector) element);
				}

				for (PortConnector connection : connnectionList) {
					Object routingConstraint = connection
							.getRoutingConstraint();
					if (routingConstraint instanceof EMap) {
						oldRoutingConstraint.put(connection, new TreeMap(
								((EMap) routingConstraint).map()));

						connection.getRoutingConstraint().clear();
					}
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		for (PortConnector connection : oldRoutingConstraint.keySet()) {
			Map constraint = oldRoutingConstraint.get(connection);
			connection.getRoutingConstraint().clear();
			connection.getRoutingConstraint().putAll(constraint);
		}

		oldRoutingConstraint.clear();
	}
}
