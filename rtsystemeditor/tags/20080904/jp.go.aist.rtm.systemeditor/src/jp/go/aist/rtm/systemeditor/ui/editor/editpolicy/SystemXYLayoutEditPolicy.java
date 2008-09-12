package jp.go.aist.rtm.systemeditor.ui.editor.editpolicy;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.editor.OfflineSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.command.ChangeConstraintCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.command.CreateCommand;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

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
		Command command = null;
		if (SystemDiagramEditor.SYSTEM_DIAGRAM_EDITOR_ID.equals(getHost()
				.getModel().getEditorId())
				&& request.getNewObject() instanceof Component) {
			if (!ComponentUtil
					.isSystemDiagramSynchronized(getHost().getModel())) {
				MessageDialog.openInformation(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), "Information",
						"�����R���|�[�l���g�y"
								+ ((OpenCompositeComponentAction) (getHost()
										.getModel())
										.getOpenCompositeComponentAction())
										.getCompositeComponent()
										.getInstanceNameL()
								+ "�z����������Ă��Ȃ��ׁA�o�^�ł��܂���B");
				return null;
			}
			command = createCommand();
		} else if (OfflineSystemDiagramEditor.OFFLINE_SYSTEM_DIAGRAM_EDITOR_ID
				.equals(getHost().getModel().getEditorId())
				&& request.getNewObject() instanceof ComponentSpecification) {
			command = createCommand();
		}
		return command;
	}

	private Command createCommand() {
		Object newObject = request.getNewObject();
		boolean isExist = true;
		if (request.getLocation() != null) {
			((AbstractComponent) newObject).setConstraint(Draw2dUtil
					.toRtcLinkRectangle((Rectangle) getConstraintFor(request)));
		}
		SystemDiagram rootSystemDiagram = ComponentUtil
				.getRootSystemDiagram(getHost().getModel());
		AbstractComponent localComponent = null;
		if (((AbstractComponent) newObject).getCorbaBaseObject() != null) {
			localComponent = (AbstractComponent) SynchronizationSupport
					.findLocalObjectByRemoteObject(
							new Object[] { ((AbstractComponent) newObject)
									.getCorbaBaseObject() }, rootSystemDiagram);
		} else {
			localComponent = ComponentUtil.findComponentByPathId(
					(AbstractComponent) newObject, rootSystemDiagram);
		}

		// CompositeComponent�_�u���N���b�N�ŊJ�����G�f�B�^(SystemDiagram)�̏ꍇ�A
		// combine���ꂽComponent�͓o�^���Ȃ��B
		if (localComponent != null) {
			if (getHost().getModel().getOpenCompositeComponentAction() != null) {
				if (localComponent.getCompositeComponent() != null) {
					AbstractComponent lo = null;
					if ( ((AbstractComponent) newObject).getCorbaBaseObject() != null) {
						lo = (AbstractComponent) SynchronizationSupport.findLocalObjectByRemoteObject(
								new Object[] { ((AbstractComponent) newObject)
										.getCorbaBaseObject() }, getHost()
										.getModel());
					}else{
						lo = ComponentUtil.findComponentByPathId((AbstractComponent) newObject, getHost()
										.getModel());
					}
					if (lo == null) {
						MessageDialog
								.openInformation(
										((OpenCompositeComponentAction) ((SystemDiagram) getHost()
												.getModel())
												.getOpenCompositeComponentAction())
												.getParentSystemDiagramEditor()
												.getSite().getShell(),
										"Information",
										"���ɕ����R���|�[�l���g�y"
												+ localComponent
														.getCompositeComponent()
														.getInstanceNameL()
												+ "�z�ɓo�^����Ă���̂ŁA�o�^�ł��܂���B");
					} else {
						//
					}

				} else {
					OpenCompositeComponentAction action = (OpenCompositeComponentAction) getHost()
							.getModel().getOpenCompositeComponentAction();
					AbstractComponent parentLocalObject = null;
					isExist = false;
					while (action != null) {
						if (action.getCompositeComponent().getCorbaBaseObject() != null) {
							parentLocalObject = (AbstractComponent) SynchronizationSupport
									.findLocalObjectByRemoteObject(
											new Object[] { action
													.getCompositeComponent()
													.getCorbaBaseObject() },
											rootSystemDiagram);
						} else {
							parentLocalObject = ComponentUtil
									.findComponentByPathId(action
											.getCompositeComponent(),
											rootSystemDiagram);
						}

						if (localComponent == parentLocalObject) {
							isExist = true;
							break;
						}
						action = (OpenCompositeComponentAction) action
								.getParentSystemDiagramEditor()
								.getSystemDiagram()
								.getOpenCompositeComponentAction();
					}
				}
			}
		} else {
			if (((AbstractComponent) newObject).isCompositeComponent()
					&& !((AbstractComponent) newObject).getAllComponents().isEmpty()) {
				for (Iterator iterator = ((AbstractComponent) newObject)
						.getAllComponents().iterator(); iterator.hasNext();) {
					AbstractComponent component = (AbstractComponent) iterator
							.next();
					if (component.getCorbaBaseObject() != null) {
						localComponent = (AbstractComponent) SynchronizationSupport
								.findLocalObjectByRemoteObject(
										new Object[] { component
												.getCorbaBaseObject() },
										ComponentUtil
												.getRootSystemDiagram(getHost()
														.getModel()));
					}
					if (localComponent != null) {
						MessageDialog.openInformation(PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getShell(),
								"Information", "���Y�����R���|�[�l���g�ɓo�^����Ă���R���|�[�l���g�y"
										+ localComponent.getInstanceNameL()
										+ "�z�͊��ɃG�f�B�^�ɑ��݂��Ă��܂��B");
					} else {
						isExist = false;
					}
				}
			} else {
				isExist = false;
			}
		}
		if( localComponent instanceof ComponentSpecification || newObject instanceof ComponentSpecification) {
			isExist = false;
			int compCount =	ComponentUtil.getComponentNumberByPathId(
					(ComponentSpecification) newObject, rootSystemDiagram);
			((AbstractComponent)newObject).setInstanceNameL(
					((AbstractComponent)newObject).getInstanceNameL() + "_" + Integer.valueOf(compCount+1).toString() );
			((AbstractComponent)newObject).setPathId(
					((ComponentSpecification)newObject).getPathURI() + ":" + Integer.valueOf(compCount+1).toString() );
		}

		CreateCommand command = null;
		if (isExist == false) {
			command = new CreateCommand();
			command.setParent(getHost().getModel());
			command.setTarget(((AbstractComponent) newObject));
		}

		return command;
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
