package jp.go.aist.rtm.systemeditor.ui.views.configurationview.mock;

import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

public class ConfigurationSetMock implements ConfigurationSet {

	public String id;
	public BasicEList configurationData = new BasicEList();

	public EList getConfigurationData() {
		return this.configurationData;
	}

	public String getId() {
		return this.id;
	}

	public _SDOPackage.ConfigurationSet getSDOConfigurationSet() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void setId(String value) {
		this.id = value;
	}

	public void setSDOConfigurationSet(_SDOPackage.ConfigurationSet value) {
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

	public Object eGet(EStructuralFeature feature) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Object eGet(EStructuralFeature feature, boolean resolve) {
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

	public void eSet(EStructuralFeature feature, Object newValue) {
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
	public Object getAdapter(Class adapter) {
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

}
