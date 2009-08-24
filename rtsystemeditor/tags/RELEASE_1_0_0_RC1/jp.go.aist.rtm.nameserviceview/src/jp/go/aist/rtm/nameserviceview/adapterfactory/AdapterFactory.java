package jp.go.aist.rtm.nameserviceview.adapterfactory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.core.runtime.IAdapterFactory;

/**
 * RTCLink�̃A�_�v�^�t�@�N�g��
 * IAdapterFactory��Generic�ɑΉ����Ă��Ȃ����߁A@SuppressWarnings("unchecked")���g�p���Ă���
 * NamingObjectNode�͌���CORBA��p�̃I�u�W�F�N�g�ł���
 */
public class AdapterFactory implements IAdapterFactory {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptable, Class adapterType) {
		if (adaptable instanceof NamingObjectNode) {
			NamingObjectNode namingObjectNode = (NamingObjectNode) adaptable;
			Object entry = namingObjectNode.getEntry();
			if (adapterType == Component.class && entry instanceof Component) {
				return ping(namingObjectNode) ? entry : null;
			}
			if (adapterType == RTCManager.class && entry instanceof RTCManager) {
				return ping(namingObjectNode) ? entry : null;
			}
		} 
		return null;
	}

	private boolean ping(NamingObjectNode namingObjectNode) {
		if (SynchronizationSupport.ping(namingObjectNode.getCorbaObject())) return true;
		namingObjectNode.setEntry(null);
//		System.out.println("zombi deteced");
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Class[] getAdapterList() {
		return new Class[] { Component.class, RTCManager.class };
	}
}
