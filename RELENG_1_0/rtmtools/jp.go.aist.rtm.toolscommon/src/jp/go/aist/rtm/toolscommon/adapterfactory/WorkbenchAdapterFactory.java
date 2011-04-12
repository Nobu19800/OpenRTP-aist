package jp.go.aist.rtm.toolscommon.adapterfactory;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.ComponentWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.ContextHandlerWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.ExecutionContextWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.InPortWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.OutPortWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.PortConnectorWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.PortInterfaceProfileWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.RTCManagerWorkbenchAdapter;
import jp.go.aist.rtm.toolscommon.ui.workbenchadapter.ServicePortWorkbenchAdapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * RTCLink�̃A�_�v�^�t�@�N�g��
 */
public class WorkbenchAdapterFactory implements IAdapterFactory {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptable, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class) {
			if (adaptable instanceof Component) {
				return new ComponentWorkbenchAdapter();
			} else if (adaptable instanceof InPort) {
				return new InPortWorkbenchAdapter();
			} else if (adaptable instanceof OutPort) {
				return new OutPortWorkbenchAdapter();
			} else if (adaptable instanceof ServicePort) {
				return new ServicePortWorkbenchAdapter();
			} else if (adaptable instanceof PortInterfaceProfile) {
				return new PortInterfaceProfileWorkbenchAdapter();
			} else if (adaptable instanceof PortConnector) {
				return new PortConnectorWorkbenchAdapter();
			} else if (adaptable instanceof ExecutionContext) {
				return new ExecutionContextWorkbenchAdapter();
			} else if (adaptable instanceof ContextHandler) {
				return new ContextHandlerWorkbenchAdapter();
			} else if (adaptable instanceof RTCManager) {
				return new RTCManagerWorkbenchAdapter();
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}
}
