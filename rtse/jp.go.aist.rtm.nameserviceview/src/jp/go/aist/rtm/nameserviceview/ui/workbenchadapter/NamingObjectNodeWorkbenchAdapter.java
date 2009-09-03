package jp.go.aist.rtm.nameserviceview.ui.workbenchadapter;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * NamingObjectNode��WorkbenchAdapter
 */
public class NamingObjectNodeWorkbenchAdapter extends NamingContextNodeWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		if (o instanceof NamingObjectNode && ((NamingObjectNode) o).isZombie()) {
			return NameServiceViewPlugin.getImageDescriptor("icons/Zombie.gif");
		}
		return NameServiceViewPlugin.getImageDescriptor("icons/Question.gif");
	}

}
