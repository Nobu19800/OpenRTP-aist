package jp.go.aist.rtm.nameserviceview.adapterfactory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.CategoryNamingContextWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.HostNamingContextWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.ManagerNamingContextWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.ModuleNamingContextWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.NameServerContextWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.NamingContextNodeWorkbenchAdapter;
import jp.go.aist.rtm.nameserviceview.ui.workbenchadapter.NamingObjectNodeWorkbenchAdapter;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * RTCLink�̃A�_�v�^�t�@�N�g��
 * IAdapterFactory��Generic�ɑΉ����Ă��Ȃ����߁A@SuppressWarnings("unchecked")���g�p���Ă���
 * �����adaptable��CORBA��p�̃I�u�W�F�N�g�ł���
 */
public class WorkbenchAdapterFactory implements IAdapterFactory {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptable, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class) {
			if (adaptable instanceof NamingObjectNode) {
				return new NamingObjectNodeWorkbenchAdapter();
			} else if (adaptable instanceof NamingContextNode) {
				NamingContextNode node = (NamingContextNode) adaptable;
				if (NamingContextNode.KIND_CATEGORY.equals(node.getKind()))
					return new CategoryNamingContextWorkbenchAdapter();
				if (NamingContextNode.KIND_HOST.equals(node.getKind()))
					return new HostNamingContextWorkbenchAdapter();
				if (NamingContextNode.KIND_MANAGER.equals(node.getKind()))
					return new ManagerNamingContextWorkbenchAdapter();
				if (NamingContextNode.KIND_MODULE.equals(node.getKind()))
					return new ModuleNamingContextWorkbenchAdapter();
				if (NamingContextNode.KIND_SERVER.equals(node.getKind()))
					return new NameServerContextWorkbenchAdapter();
				return new NamingContextNodeWorkbenchAdapter();
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
