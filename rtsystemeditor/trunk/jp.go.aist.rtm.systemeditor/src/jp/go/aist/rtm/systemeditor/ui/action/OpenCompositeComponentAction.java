package jp.go.aist.rtm.systemeditor.ui.action;

import java.util.Iterator;

import jp.go.aist.rtm.systemeditor.ui.editor.AbstractSystemDiagramEditor;
import jp.go.aist.rtm.systemeditor.ui.editor.NullEditorInput;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * �����R���|�[�l���g���J���A�N�V����
 */
public class OpenCompositeComponentAction extends Action {

	private IWorkbenchPart parentSystemDiagramEditor;

	private Component compositeComponent;

	public static final String ACTION_ID = OpenCompositeComponentAction.class
			.getName()
			+ "_ACTION_ID";

	private AbstractSystemDiagramEditor compositeComponentEditor;

	public OpenCompositeComponentAction(IWorkbenchPart parentPart) {
		setId(ACTION_ID);
		this.parentSystemDiagramEditor = parentPart;
	}

	@Override
	/**
	 * {@inheritDoc}
	 * 
	 */
	public void run() {
		try {
			// �q�E�B���h�E���J����Ă��Ȃ�����ΊJ���inotExist = true�j
			// �q�E�B���h�E���J����Ă���΃A�N�e�B�u�ɂ���inotExist = false�j
			boolean notExist = activateCompositeComponentEditor();
			if (!notExist) return;

			// �q�E�B���h�E�ɃR���|�[�l���g���Z�b�g����
			SystemDiagram childDiagram = compositeComponentEditor.getSystemDiagram();
			compositeComponent2Editor(childDiagram);
			childDiagram.setParentSystemDiagram(getParentSystemDiagram());
			childDiagram.setCompositeComponent(compositeComponent);
			compositeComponent.setChildSystemDiagram(childDiagram);
			compositeComponentEditor.changeFile(null);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	// �q�E�B���h�E���J����Ă��Ȃ�����ΊJ���inotExist = true�j
	// �q�E�B���h�E���J����Ă���΃A�N�e�B�u�ɂ���inotExist = false�j
	private boolean activateCompositeComponentEditor() throws PartInitException {
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		compositeComponentEditor = ComponentUtil.findEditor(compositeComponent.getChildSystemDiagram());
		if (compositeComponentEditor == null) {
			compositeComponentEditor = (AbstractSystemDiagramEditor) activePage.openEditor(
							new NullEditorInput(),
							getParentSystemDiagramEditor().getEditorId());
			return true;
		} else {
			IEditorPart oldIEditorPart = activePage.findEditor(
							compositeComponentEditor.getEditorInput());
			if (oldIEditorPart == null) {
				compositeComponentEditor = (AbstractSystemDiagramEditor) activePage
						.openEditor(
								compositeComponentEditor.getEditorInput(),
								getParentSystemDiagramEditor()
										.getEditorId());
				return true;
			} else {
				activePage.activate(compositeComponentEditor);
				return false;
			}
		}
	}

	public void setCompositeComponent(Component component) {
		this.compositeComponent = component;
	}

	@SuppressWarnings("unchecked")
	private void compositeComponent2Editor(SystemDiagram childDiagram) {
		// �V�X�e���_�C�A�O�����������Z�b�g����
		SystemDiagram psd = getParentSystemDiagram();
		childDiagram.setSystemId(psd.getSystemId());
		childDiagram.setCreationDate(psd.getCreationDate());
		childDiagram.setUpdateDate(psd.getUpdateDate());
		
		// �q�R���|�[�l���g��Constraint��ݒ肷��
		int count = 0;
		for (Iterator iterator = compositeComponent.getAllComponents()
				.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			if (component.getConstraint() == null) {
				component.setConstraint(ComponentUtil
						.getNewComponentConstraint(compositeComponent
								.getConstraint(), count));
				count++;
			}
		}
		// �q�_�C�A�O�����ɃR���|�[�l���g���Z�b�g����
		childDiagram.clearComponents();
		for (Object  o : compositeComponent.getComponents()) {
			Component component = (Component) o;
			childDiagram.addComponent(component);	// ���[�g�_�C�A�O�����̃R���|�[�l���g�̎q�������̂܂܎g���܂킷
		}
	}

	public AbstractSystemDiagramEditor getParentSystemDiagramEditor() {
		return (AbstractSystemDiagramEditor) this.parentSystemDiagramEditor;
	}

	private SystemDiagram getParentSystemDiagram() {
		return getParentSystemDiagramEditor().getSystemDiagram();
	}
}
