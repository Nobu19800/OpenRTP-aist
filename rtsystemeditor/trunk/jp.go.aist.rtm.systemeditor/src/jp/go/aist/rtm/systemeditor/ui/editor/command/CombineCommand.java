package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.gef.commands.Command;

/**
 * �V�X�e���_�C�A�O������Rtc��ǉ�����R�}���h
 */
public class CombineCommand extends Command {
	private SystemDiagram parent;

	private AbstractComponent target;

	private AbstractComponent targetcopy;

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		List<AbstractComponent> selectedComponents = new ArrayList<AbstractComponent>();
		selectedComponents.addAll(target.getComponents());
		AbstractComponent copyCompositeComponent = null;
		OpenCompositeComponentAction openAction = null;
		openAction = (OpenCompositeComponentAction) parent
				.getOpenCompositeComponentAction();
		if (openAction != null) {
			OpenCompositeComponentAction parentAction = openAction;
			AbstractComponent parentCompositeComponent = parentAction
					.getCompositeComponent();
			// �e�G�f�B�^���̕����R���|�[�l���g���R�s�[���A
			// ���̐e�G�f�B�^�ɑ��݂��錋���Ώ�(selectedComponent)��T���āA
			// �����R���|�[�l���g�ɐݒ肷��B
			while (parentAction != null) {
				copyCompositeComponent = (AbstractComponent) SystemEditorWrapperFactory
						.getInstance().copy(target);
				for (AbstractComponent selectedComponent : selectedComponents) {
					AbstractComponent tmp = null;
					if (selectedComponent.getCorbaBaseObject() != null) {
						tmp = (AbstractComponent) SynchronizationSupport
								.findLocalObjectByRemoteObject(
										new Object[] { selectedComponent
												.getCorbaBaseObject() },
										parentAction
												.getParentSystemDiagramEditor()
												.getSystemDiagram());
					} else {
						tmp = ComponentUtil.findComponentByPathId(
								selectedComponent, parentAction
										.getParentSystemDiagramEditor()
										.getSystemDiagram());
					}
					if (tmp != null) {
						copyCompositeComponent.getComponents().add(tmp);
					} else {
						throw new RuntimeException();
					}

				}
				if (parentCompositeComponent.getCorbaObject() != null) {
					copyCompositeComponent
							.setCompositeComponent((AbstractComponent) SynchronizationSupport
									.findLocalObjectByRemoteObject(
											new Object[] { parentCompositeComponent
													.getCorbaBaseObject() },
											parentAction
													.getParentSystemDiagramEditor()
													.getSystemDiagram()));
				} else {
					copyCompositeComponent.setCompositeComponent(ComponentUtil
							.findComponentByPathId(parentCompositeComponent,
									parentAction.getParentSystemDiagramEditor()
											.getSystemDiagram()));
				}
				parentAction.getParentSystemDiagramEditor().getSystemDiagram()
						.getComponents().add(copyCompositeComponent);
				parentAction = (OpenCompositeComponentAction) parentAction
						.getParentSystemDiagramEditor().getSystemDiagram()
						.getOpenCompositeComponentAction();
			}
		}
		parent.getComponents().add(target);
	}

	/**
	 * �e�ƂȂ�V�X�e���_�C�A�O������ݒ肷��
	 * 
	 * @param parent
	 *            �e�ƂȂ�V�X�e���_�C�A�O����
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * �����R���|�[�l���g��ݒ肷��
	 * 
	 * @param target
	 *            �����R���|�[�l���g
	 */
	public void setTarget(AbstractComponent target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		SystemDiagram systemDiagram = parent;
		OpenCompositeComponentAction parentAction = (OpenCompositeComponentAction) parent
				.getOpenCompositeComponentAction();
		do {
			AbstractComponent tmp = null;
			if (target.getCorbaBaseObject() != null) {
				tmp = (AbstractComponent) SynchronizationSupport
						.findLocalObjectByRemoteObject(new Object[] { target
								.getCorbaBaseObject() }, systemDiagram);
			} else {
				tmp = ComponentUtil.findComponentByPathId(target,
						systemDiagram);
			}
			if (tmp != null) {
				systemDiagram.getComponents().remove(tmp);
				tmp.getComponents().clear();
				tmp.setOpenCompositeComponentAction(null);
			} else {
				throw new RuntimeException();
			}
			systemDiagram = parentAction.getParentSystemDiagramEditor()
					.getSystemDiagram();
			parentAction = (OpenCompositeComponentAction) parentAction
					.getParentSystemDiagramEditor().getSystemDiagram()
					.getOpenCompositeComponentAction();
		} while (parentAction != null);
	}
}
