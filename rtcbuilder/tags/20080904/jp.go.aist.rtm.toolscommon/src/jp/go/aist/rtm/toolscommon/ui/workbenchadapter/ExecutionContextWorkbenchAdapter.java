package jp.go.aist.rtm.toolscommon.ui.workbenchadapter;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * ExecutionContext��WorkbenchAdapter
 */
public class ExecutionContextWorkbenchAdapter extends ModelElementWorkbenchAdapter {

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return ToolsCommonPlugin.getImageDescriptor("icons/ExecutionContext.gif");
	}

	@Override
	public String getLabel(Object o) {
		return "ExecutionContext";
	}
	
	@Override
	public Object[] getChildren(Object o) {
		return null;
	}
}
