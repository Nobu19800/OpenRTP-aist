package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.SystemXYLayoutEditPolicy;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * �V�X�e���_�C�A�O������EditPart�N���X
 */
public class SystemDiagramEditPart extends AbstractEditPart {
	/**
	 * �R���X�g���N�^
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public SystemDiagramEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);

	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		FreeformLayer result = new FreeformLayer();
		result.setLayoutManager(new FreeformLayout());
		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new SystemXYLayoutEditPolicy());
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public List getModelChildren() {
		return getModel().getComponents();
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(Notification notification) {
		refreshSystemDiagram();
	}

	public void refreshSystemDiagram() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (isActive()) {
					refreshChildren();
				}
			}
		});
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public SystemDiagram getModel() {
		return (SystemDiagram) super.getModel();
	}
}
