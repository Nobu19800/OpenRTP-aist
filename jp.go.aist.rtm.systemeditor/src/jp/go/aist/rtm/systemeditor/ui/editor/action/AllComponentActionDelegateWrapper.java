package jp.go.aist.rtm.systemeditor.ui.editor.action;

import jp.go.aist.rtm.systemeditor.ui.action.AllComponentActionDelegate;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.ComponentEditPart;
import jp.go.aist.rtm.systemeditor.ui.editor.editpart.SystemDiagramEditPart;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ���ׂẴR���|�[�l���g�ɑ΂��ăA�N�V�������s���ۂɎg�p���郆�[�e�B���e�B�N���X�B
 * ���݂͖��g�p
 */
public class AllComponentActionDelegateWrapper extends Action implements
		ISelectionListener {
	private ISelection selection;

	private AllComponentActionDelegate delegate;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param id
	 *            �A�N�V������Id
	 * @param text
	 *            ���x��
	 */
	public AllComponentActionDelegateWrapper(String id, String text) {
		setId(id);
		setText(text);
		setToolTipText(text);
		this.delegate = new AllComponentActionDelegate();
	}

	@Override
	public void run() {
		delegate.selectionChanged(this, convertSelection(selection));
		delegate.run(this);
	}

	/**
	 * �Z���N�V�������ύX���ꂽ�ۂɎ��s����郁�\�b�h
	 * 
	 * @param selection
	 */
	public void selectionChanged(ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection)
					.getFirstElement();
			if (firstElement instanceof SystemDiagramEditPart
					|| firstElement instanceof ComponentEditPart) {
				this.selection = selection;
			}
		}

		boolean isEnable = false;
		if (this.selection != null) {
			isEnable = true;
		}

		setEnabled(isEnable);
	}

	private ISelection convertSelection(ISelection selection) {
		Object firstElement = ((IStructuredSelection) selection)
				.getFirstElement();

		SystemDiagramEditPart diagram = null;
		if (firstElement instanceof SystemDiagramEditPart) {
			diagram = (SystemDiagramEditPart) firstElement;
		} else if (firstElement instanceof ComponentEditPart) {
			diagram = (SystemDiagramEditPart) ((ComponentEditPart) firstElement)
					.getParent();
		}

		return new StructuredSelection(new Object[] { diagram.getModel() });
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		selectionChanged(selection);
	}

}
