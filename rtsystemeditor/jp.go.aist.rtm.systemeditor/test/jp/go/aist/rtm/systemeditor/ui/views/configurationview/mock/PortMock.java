package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.omg.CORBA.Object;

import RTC.PortProfile;
import RTC.PortService;

public class PortMock implements Port {

	private PortProfile profile;

	public PortService getCorbaObjectInterface() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getOriginalPortString() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public PortProfile getPortProfile() {
		return this.profile;
	}

	public void setOriginalPortString(String value) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void setOriginalPortString(String componentId, String instanceName,
			String portName) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void setPortProfile(PortProfile value) {
		this.profile = value;
	}

	public EList getSourceConnectors() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Object getCorbaBaseObject() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Object getCorbaObject() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public boolean ping() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public void setCorbaObject(Object value) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void accept(Visiter visiter) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void dispose() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public Rectangle getConstraint() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void setConstraint(Rectangle rectangle) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@SuppressWarnings("unchecked")
	public TreeIterator eAllContents() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EClass eClass() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EObject eContainer() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EStructuralFeature eContainingFeature() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EReference eContainmentFeature() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eContents() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	@SuppressWarnings("unchecked")
	public EList eCrossReferences() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public java.lang.Object eGet(EStructuralFeature feature, boolean resolve) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public boolean eIsProxy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public boolean eIsSet(EStructuralFeature feature) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public Resource eResource() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void eSet(EStructuralFeature feature, java.lang.Object newValue) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void eUnset(EStructuralFeature feature) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@SuppressWarnings("unchecked")
	public EList eAdapters() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public boolean eDeliver() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public void eNotify(Notification notification) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void eSetDeliver(boolean deliver) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public SynchronizationSupport getSynchronizationSupport() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

//	@Override
	public PortSynchronizer getSynchronizer() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setSynchronizer(PortSynchronizer value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void disconnectAll() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList findAllEquivalentPorts() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Port findPort(String originalPortString) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getNameL() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setNameL(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public List<String> getDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getDataflowTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getInterfaceTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public List<String> getSubscriptionTypes() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean isAllowAnyDataType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnyDataflowType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnyInterfaceType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean isAllowAnySubscriptionType() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getConnectorProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public EList getInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public boolean validateSourceConnector(Port source) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean validateTargetConnector(Port target) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public String getDataType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getDataflowType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getInterfaceType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public String getSubscriptionType() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public void setDataType(String type) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setDataflowType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setInterfaceType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void setSubscriptionType(String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void synchronizeLocalReference() {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public Port proxy() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public Port findPort(SystemDiagram diagram, String originalPortString) {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
public String getProperty(String name) {
	// TODO Auto-generated method stub
	return null;
}

}
