package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.Connector;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Rtc���폜����폜�R�}���h
 */
public class DeleteCommand extends Command {
	private SystemDiagram parent;

	private AbstractComponent target;

	private AbstractComponent targetCompositeComponent;

	private List<Connector> saveSourceConnectors = new ArrayList<Connector>();

	private List<Connector> saveTargetConnectors = new ArrayList<Connector>();

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		boolean confirm = true;
		if (target.isCompositeComponent()
				&& target.getOpenCompositeComponentAction() != null) {
			IEditorPart editor = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().findEditor(
							((OpenCompositeComponentAction) target
									.getOpenCompositeComponentAction())
									.getCompositeComponentEditor()
									.getEditorInput());
			if (editor != null) {
				confirm = MessageDialog.openConfirm(
						((OpenCompositeComponentAction) target
								.getOpenCompositeComponentAction())
								.getParentSystemDiagramEditor().getSite()
								.getShell(), "�m�F", "�ʂ̃G�f�B�^�ŊJ���Ă��܂��A�폜���Ă��ǂ��ł����H");
			}
		}
		if (confirm) {
			OpenCompositeComponentAction openAction = (OpenCompositeComponentAction) parent
					.getOpenCompositeComponentAction();
			if (openAction != null) {
				// �q�G�f�B�^�̏ꍇ�e�G�f�B�^�ɑ��݂���target���폜����B
				OpenCompositeComponentAction parentAction = openAction;
				while (parentAction != null) {
					AbstractComponent localObject = null;
					if (target.getCorbaBaseObject() != null) {
						localObject = (AbstractComponent) SynchronizationSupport
						.findLocalObjectByRemoteObject(
								new Object[] { target.getCorbaBaseObject() },
								parentAction.getParentSystemDiagramEditor()
										.getSystemDiagram());
					} else {
						localObject = ComponentUtil
							.findComponentByPathId(target, parentAction
								.getParentSystemDiagramEditor()
								.getSystemDiagram());
					}

					if (localObject != null) {
						for (Iterator iter = localObject.eAllContents(); iter
								.hasNext();) {
							EObject element = (EObject) iter.next();
							if (element instanceof Port) {
								saveTargetConnectors.addAll(((Port) element)
										.getTargetConnectors());
								saveSourceConnectors.addAll(((Port) element)
										.getSourceConnectors());
							}
						}

						for (Connector source : saveSourceConnectors) {
							source.dettachSource();
							source.dettachTarget();
						}

						for (Connector target : saveTargetConnectors) {
							target.dettachSource();
							target.dettachTarget();
						}
					}

					parentAction = (OpenCompositeComponentAction) parentAction
							.getParentSystemDiagramEditor().getSystemDiagram()
							.getOpenCompositeComponentAction();
				}
			}
			for (Iterator iter = target.eAllContents(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element instanceof Port) {
					saveTargetConnectors.addAll(((Port) element)
							.getTargetConnectors());
					saveSourceConnectors.addAll(((Port) element)
							.getSourceConnectors());
				}
			}

			for (Connector source : saveSourceConnectors) {
				source.dettachSource();
				source.dettachTarget();
			}

			for (Connector target : saveTargetConnectors) {
				target.dettachSource();
				target.dettachTarget();
			}
			targetCompositeComponent = target.getCompositeComponent();
			ComponentUtil.removeCompositeComponent(parent, target);
		}
	}

	/**
	 * �e�̃V�X�e���_�C�A�O������ݒ肷��
	 * 
	 * @param parent
	 *            �e�̃V�X�e���_�C�A�O����
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * �ύX�Ώۂ̃R���|�[�l���g
	 * 
	 * @param target
	 *            �R���|�[�l���g
	 */
	public void setTarget(AbstractComponent target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDocs}
	 */
	public void undo() {
		for (Connector source : saveSourceConnectors) {
			source.attachSource();
			source.attachTarget();
		}

		for (Connector target : saveTargetConnectors) {
			target.attachSource();
			target.attachTarget();
		}
		SystemDiagram systemDiagram = parent;
		OpenCompositeComponentAction parentAction = (OpenCompositeComponentAction) parent
				.getOpenCompositeComponentAction();
		AbstractComponent copyTarget = null;
		do {
			copyTarget = (AbstractComponent) SystemEditorWrapperFactory.getInstance()
					.copy(target);
			systemDiagram.getComponents().add(copyTarget);
			ComponentUtil.copieAndSetCompositeComponents(systemDiagram, target);
			AbstractComponent compositeComponent = null;

			if (targetCompositeComponent != null) {
				if (targetCompositeComponent.getCorbaBaseObject() != null) {
					compositeComponent = (AbstractComponent) SynchronizationSupport
							.findLocalObjectByRemoteObject(
									new Object[] { targetCompositeComponent
											.getCorbaBaseObject() },
									systemDiagram);
				} else {
					compositeComponent = ComponentUtil
					.findComponentByPathId(targetCompositeComponent,
							systemDiagram);
				}
				if (compositeComponent != null) {
					compositeComponent.getComponents().add(copyTarget);
				} else {
					throw new RuntimeException();
				}
			}
			systemDiagram = parentAction.getParentSystemDiagramEditor()
					.getSystemDiagram();
			parentAction = (OpenCompositeComponentAction) parentAction
					.getParentSystemDiagramEditor().getSystemDiagram()
					.getOpenCompositeComponentAction();
		} while (parentAction != null);

		saveSourceConnectors.clear();
		saveTargetConnectors.clear();
	}
}
