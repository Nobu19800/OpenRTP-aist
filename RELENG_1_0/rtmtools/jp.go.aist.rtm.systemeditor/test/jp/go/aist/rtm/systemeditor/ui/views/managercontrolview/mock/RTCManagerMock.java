package jp.go.aist.rtm.systemeditor.ui.views.managercontrolview.mock;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.TCKind;

import RTC.ComponentProfile;
import RTC.RTObject;
import RTM.ManagerProfile;
import RTM.ModuleProfile;
import _SDOPackage.NameValue;

public class RTCManagerMock implements RTCManager {

	static ORB orb;

	public static RTCManagerMock mock1;
	static {
		orb = ORB.init();

		// mock1
		mock1 = new RTCManagerMock();

		ModuleProfile mp;
		mp = new ModuleProfile();
		NameValue nv = newNV("file_path", "lib/module1.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module2.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module3.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module4.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);
		mp = new ModuleProfile();
		nv = newNV("file_path", "lib/module5.so");
		mp.properties = new NameValue[] { nv };
		mock1.loadableModuleList.add(mp);

		ComponentProfile cp;
		cp = new ComponentProfile();
		cp.instance_name = "component_instance1";
		mock1.componentProfileList.add(cp);
		cp = new ComponentProfile();
		cp.instance_name = "component_instance2";
		mock1.componentProfileList.add(cp);
		cp = new ComponentProfile();
		cp.instance_name = "component_instance3";
		mock1.componentProfileList.add(cp);
	};

	public ManagerProfile managerProfile;

	public BasicEList<ModuleProfile> loadableModuleList = new BasicEList<ModuleProfile>();

	public BasicEList<ModuleProfile> loadedModuleList = new BasicEList<ModuleProfile>();

	public BasicEList<ComponentProfile> componentProfileList = new BasicEList<ComponentProfile>();

	public static NameValue newNV(String name, String value) {
		NameValue nv = new NameValue();
		nv.name = name;
		nv.value = newAny(value);
		return nv;
	}

	public static Any newAny(String value) {
		Any any = orb.create_any();
		if (StringUtils.isAsciiPrintable(value)) {
			any.insert_string(value);
		} else {
			any.insert_wstring(value);
		}
		return any;
	}

	private static Any findValue(NameValue[] nameValue, String name) {
		Any result = null;
		if (nameValue != null) {
			for (NameValue elem : nameValue) {
				if (elem != null && name.equals(elem.name)) {
					result = elem.value;
					break;
				}
			}
		}
		return result;
	}

	public static String findStringValue(NameValue[] nameValue, String name) {
		Any any = findValue(nameValue, name);
		String result = null;
		if (any != null) {
			if (any.type().kind() == TCKind.tk_wstring) {
				result = any.extract_wstring();
			} else {
				result = any.extract_string();
			}
		}
		return result;
	}

	public Component createComponentR(String compName) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public int forkR() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public int shutdownR() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public int deleteComponentR(String instanceName) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public EList<RTObject> getComponentsR() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public ManagerProfile getProfileR() {
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

	public org.omg.CORBA.Object getCorbaObjectInterface() {
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

	public EList<EObject> eContents() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EList<EObject> eCrossReferences() {
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

	public EList<Adapter> eAdapters() {
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

	public EList<String> getComponentInstanceNamesR() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EList<ComponentProfile> getComponentProfilesR() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public EList<String> getLoadableModuleFileNamesR() {
		BasicEList<String> result = new BasicEList<String>();
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value != null) {
					result.add(value);
				}
			}
		}
		return result;
	}

	public EList<ModuleProfile> getLoadableModuleProfilesR() {
		BasicEList<ModuleProfile> result = new BasicEList<ModuleProfile>();
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				result.add(mp);
			}
		}
		return result;
	}

	public EList<String> getLoadedModuleFileNamesR() {
		BasicEList<String> result = new BasicEList<String>();
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value != null) {
					result.add(value);
				}
			}
		}
		return result;
	}

	public EList<ModuleProfile> getLoadedModuleProfilesR() {
		BasicEList<ModuleProfile> result = new BasicEList<ModuleProfile>();
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				result.add(mp);
			}
		}
		return result;
	}

	public int loadModuleR(String pathname, String initfunc) {
		for (java.lang.Object o : this.loadableModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value.equals(pathname)) {
					this.loadedModuleList.add(mp);
					this.loadableModuleList.remove(mp);
					return 1;
				}
			}
		}
		return 0;
	}

	public int unloadModuleR(String pathname) {
		for (java.lang.Object o : this.loadedModuleList) {
			if (o instanceof ModuleProfile) {
				ModuleProfile mp = (ModuleProfile) o;
				String value = findStringValue(mp.properties, "file_path");
				if (value.equals(pathname)) {
					this.loadableModuleList.add(mp);
					this.loadedModuleList.remove(mp);
					return 1;
				}
			}
		}
		return 0;
	}

	public String getInstanceNameL() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public ManagerProfile getManagerProfile() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public void setManagerProfile(ManagerProfile value) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	public EList<String> getFactoryProfileTypeNamesR() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPathId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPathId(String value) {
		// TODO Auto-generated method stub
		
	}

	public void synchronizeLocalAttribute(EReference reference) {
		// TODO Auto-generated method stub
		
	}

	public void synchronizeLocalReference() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchronizeManually() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EList<String> getComponentInstanceNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<ComponentProfile> getComponentProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<ModuleProfile> getFactoryModuleProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<ModuleProfile> getFactoryModuleProfilesR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getFactoryTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getLoadableModuleFileNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<ModuleProfile> getLoadableModuleProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<String> getLoadedModuleFileNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EList<ModuleProfile> getLoadedModuleProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
