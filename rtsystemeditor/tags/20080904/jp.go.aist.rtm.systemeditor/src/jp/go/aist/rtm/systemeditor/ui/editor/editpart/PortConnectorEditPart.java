package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.ui.editor.editpart.router.EditableManhattanConnectorRouter;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorBendpointEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.PortConnectorEndpointEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.Connector;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * �R�l�N�^��EditPart�N���X
 */
public class PortConnectorEditPart extends AbstractConnectionEditPart {

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	/**
	 * �R���X�g���N�^
	 * 
	 * @param actionRegistry
	 */
	public PortConnectorEditPart(ActionRegistry actionRegistry) {
		super();
	}

	/**
	 * ���f���ւ̃��X�i
	 */
	protected Adapter modelListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			getViewer().getControl().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (isActive()) {
						refreshBendPoint();
					}
				}
			});
		}
	};

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new PortConnectorEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new PortConnectorEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new PortConnectorBendpointEditPolicy());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		PolylineConnection result = new PolylineConnection();
		result.setLineWidth(1);
		result.setConnectionRouter(new EditableManhattanConnectorRouter());

		// �����̕\��
		// PolylineDecoration decoration = new PolylineDecoration();
		// decoration.setFill(true);
		// result.setTargetDecoration(decoration);

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();
		((ModelElement) getModel()).eAdapters().add(modelListener);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		super.deactivate();
		((ModelElement) getModel()).eAdapters().remove(modelListener);
	}

	/**
	 * �x���h�|�C���g���Đݒ肷��
	 */
	protected void refreshBendPoint() {
		Map routingConstraint = Draw2dUtil.toDraw2dPointMap(((EMap) getModel()
				.getRoutingConstraint()).map());
		if (routingConstraint == null) {
			routingConstraint = Collections.EMPTY_MAP;
		}

		getConnectionFigure().setRoutingConstraint(routingConstraint);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Connector getModel() {
		return (Connector) super.getModel();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshBendPoint();
	}
	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * ���f���̃I�u�W�F�N�g�ɈϏ����Ă���
	 */
	public Object getAdapter(Class key) {
		Object result = AdapterUtil.getAdapter(getModel(), key);
		if (result == null) {
			result = super.getAdapter(key);
		}

		return result;
	}
	/**
	 * �R���|�[�l���gFigure�̕ύX�̒ʒm���s�����X�i��o�^����
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * �R���|�[�l���gFigure�̕ύX�̒ʒm���s�����X�i���폜����
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
