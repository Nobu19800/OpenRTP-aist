package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ChangeConstraintCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * �V�X�e���_�C�A�O�����Ɋւ���EditPolicy�N���X
 */
public class SystemXYLayoutEditPolicy extends XYLayoutEditPolicy {

	CreateRequest request;

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		ChangeConstraintCommand command = new ChangeConstraintCommand();
		command.setModel((ModelElement) child.getModel());
		command.setConstraint((Rectangle) constraint);

		return command;
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * �I�����C���̏ꍇ�ɂ́A���ɑ��݂���I�u�W�F�N�g�͓o�^�ł��Ȃ��B
	 */
	protected Command getCreateCommand(CreateRequest request) {
		this.request = request;
		Component newObject2 = (Component)request.getNewObject();
		if (newObject2 == null) return null;
		
		boolean isExist = isExist(newObject2);

		if (isExist) return null;
		
		CreateCommand command = new CreateCommand();
		command.setParent(getHost().getModel());
		command.setTarget(newObject2);
		if (request.getLocation() != null) {
			newObject2.setConstraint(Draw2dUtil
					.toRtcLinkRectangle((Rectangle) getConstraintFor(request)));
		}

		return command;
	}

	@SuppressWarnings("unchecked")
	private boolean isExist(Component newObject2) {
		if (newObject2 == null) return false;
		SystemDiagram rootSystemDiagram = getHost().getModel().getRootDiagram();
		if(newObject2 instanceof ComponentSpecification) {
			ensureSpec((ComponentSpecification) newObject2, rootSystemDiagram);
			return false;
		}
		Component localComponent =  findComponentInDiagram(newObject2, rootSystemDiagram);
		// CompositeComponent�_�u���N���b�N�ŊJ�����G�f�B�^(SystemDiagram)�̏ꍇ�A
		// combine���ꂽComponent�͓o�^���Ȃ��B
		if (localComponent != null) {
			MessageDialog.openInformation(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(),
					Messages.getString("SystemXYLayoutEditPolicy.6"), Messages.getString("SystemXYLayoutEditPolicy.7") //$NON-NLS-1$ //$NON-NLS-2$
							+ localComponent.getInstanceNameL()
							+ Messages.getString("SystemXYLayoutEditPolicy.8")); //$NON-NLS-1$
			return true;
		} else if (newObject2.isCompositeComponent()
					&& !newObject2.getAllComponents().isEmpty()) {
			for (Iterator iterator = newObject2.getAllComponents().iterator(); iterator.hasNext();) {
				Component component = (Component) iterator.next();
				localComponent = findComponentInDiagram(component, rootSystemDiagram);
				if (localComponent != null) {
					MessageDialog.openInformation(PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getShell(),
							Messages.getString("SystemXYLayoutEditPolicy.6"), Messages.getString("SystemXYLayoutEditPolicy.7") //$NON-NLS-1$ //$NON-NLS-2$
									+ localComponent.getInstanceNameL()
									+ Messages.getString("SystemXYLayoutEditPolicy.8")); //$NON-NLS-1$
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * �I�����C���G�f�B�^�̃_�C�A�O��������R���|�[�l���g���������܂��B
	 */
	private Component findComponentInDiagram(
			Component component, SystemDiagram diagram) {
		if (component instanceof CorbaComponent) {
			org.omg.CORBA.Object obj = ((CorbaComponent)component).getCorbaObject();
			if (obj != null) {
				return findByCorbaObject(obj, diagram);
			}
		}
		return null;
	}

	private Component findByCorbaObject(org.omg.CORBA.Object obj, SystemDiagram diagram) {
		if (obj == null || diagram == null) {
			return null;
		}
		for (Component tempComponent : diagram.getRegisteredComponents()) {
			if (!(tempComponent instanceof CorbaComponent)) continue;
			CorbaComponent c = (CorbaComponent)tempComponent;
			if (obj.equals(c.getCorbaObject())) return c;
		}
		return null;
	}

	private void ensureSpec(ComponentSpecification newObject2,
			SystemDiagram rootSystemDiagram) {
		int compCount =	ComponentUtil.getComponentNumberByPathId(
				newObject2, rootSystemDiagram);
		//�P��d�l���畡���C���X�^���X�𐶐����邽��
		//Port�Ԑڑ����ɃR���|�[�l���g�̋�ʂ�PathID��p���čs���Ă��邽��
		//PathID�̐ݒ���K�v
		newObject2.setInstanceNameL(
				newObject2.getInstanceNameL() + "_" + Integer.valueOf(compCount+1).toString());
		String basePathId = newObject2.getPathId();
		int index = basePathId.lastIndexOf(":");
		if (index >=0) basePathId = basePathId.substring(0, index);
		newObject2.setPathId(
				basePathId + ":" + Integer.valueOf(compCount+1).toString() ); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	public SystemDiagramEditPart getHost() {
		return (SystemDiagramEditPart) super.getHost();
	}

}
