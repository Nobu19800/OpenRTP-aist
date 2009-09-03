package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ProfileInformationDialogTest {

	/**
	 * RTS�ۑ��_�C�A���O�p�̃e�X�g
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
	    shell.pack();
	    shell.open();
//	    List<AbstractComponent> list = setupSelectedComponents();
	    ProfileInformationDialog dialog = new ProfileInformationDialog(shell);
	    List<Component> setupSelectedComponents = NewCompositeComponentDialogTest.setupSelectedComponents();
		dialog.setComponets(setupSelectedComponents);
	    if( dialog.open() == IDialogConstants.OK_ID ) {
	    	for (Component component : setupSelectedComponents) {
				System.out.println(component.getInstanceNameL() + ":" + component.isRequired());
			}
	    }
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
