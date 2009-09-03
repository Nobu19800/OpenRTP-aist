package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * �|�[�g�Ɋւ��郆�[�e�B���e�B�N���X
 *
 */
public class PortHelper {

	/**
	 * �w�肵���|�[�g�ɃR�l�N�^�����݂��邩��Ԃ�
	 * @param port
	 * @return
	 */
	public static boolean isConnected(Port port) {
		if (port == null) return false;
		return !port.getConnectorProfiles().isEmpty();
	}

	/**
	 * �I������Ă���|�[�g��Ԃ�
	 * @param selection
	 * @return
	 */
	public static Port getPort(ISelection selection) {
		PortEditPart part = getPortPart(selection);
		if (part == null) return null;
		return part.getModel();
	}

	/**
	 * �|�[�g�����J����Ă��邩��Ԃ�
	 * @param port
	 * @return
	 */
	public static boolean isExported(Port port) {
		if (port == null) return false;
		if (port.eContainer() == null) return false;
		if (!(port.eContainer().eContainer() instanceof SystemDiagram)) return false;
		SystemDiagram diagram = (SystemDiagram) port.eContainer().eContainer();
		Component component = diagram.getCompositeComponent();
		if (component == null) return false;
		for (Object element : component.getPorts()) {
			Port temp = (Port) element;
			if (temp.getOriginalPortString().equals(port.getOriginalPortString()))
				return true;
		}
		return false;
	}

	/**
	 * �I������Ă���|�[�g��EditPart��Ԃ�
	 * @param selection
	 * @return
	 */
	public static PortEditPart getPortPart(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		Object part = ((IStructuredSelection) selection).getFirstElement();
		if (!(part instanceof PortEditPart)) return null;
		return (PortEditPart) part;
	}

}
