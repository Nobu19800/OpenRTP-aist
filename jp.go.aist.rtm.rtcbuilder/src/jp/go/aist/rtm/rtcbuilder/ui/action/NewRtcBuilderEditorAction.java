package jp.go.aist.rtm.rtcbuilder.ui.action;

import jp.go.aist.rtm.rtcbuilder.ui.NewWizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * 新しいRtcBuilderエディタを作成するアクション
 */
public class NewRtcBuilderEditorAction implements
		IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run(IAction action) {
		WizardDialog dialog = new WizardDialog(window.getShell(), new NewWizard());
		dialog.open();
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
