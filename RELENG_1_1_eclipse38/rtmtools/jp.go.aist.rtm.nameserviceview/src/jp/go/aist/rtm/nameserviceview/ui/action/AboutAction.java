package jp.go.aist.rtm.nameserviceview.ui.action;

import jp.go.aist.rtm.nameserviceview.ui.dialog.AboutDialog;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * RT Name Service ViewのAboutを表示するアクション
 */
public class AboutAction implements IWorkbenchWindowActionDelegate {

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
		AboutDialog dialog = new AboutDialog(window.getShell());
		dialog.open();
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
