package jp.go.aist.rtm.toolscommon.synchronizationframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortSynchronizerImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * ���[�J���I�u�W�F�N�g�ɑ΂��āA�����@�\��񋟂���N���X
 */
public class SynchronizationSupport {
	private LocalObject localObject;

	private MappingRule mappingRule;

	private SynchronizationManager synchronizationManager;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @param mappingRule
	 *            �}�b�s���O���[��
	 * @param synchronizationManager
	 *            �����}�l�[�W��
	 */
	public SynchronizationSupport(LocalObject localObject,
			MappingRule mappingRule,
			SynchronizationManager synchronizationManager) {
		this.localObject = localObject;
		this.mappingRule = mappingRule;
		this.synchronizationManager = synchronizationManager;
	}

	/**
	 * �����}�l�[�W�����擾����
	 * 
	 * @return �����}�l�[�W��
	 */
	public SynchronizationManager getSynchronizationManager() {
		return synchronizationManager;
	}

	/**
	 * ���[�J���I�u�W�F�N�g�𓯊�����B
	 * <p>
	 * ��܎Q�Ƃ����ǂ�A���ׂẴ��[�J���I�u�W�F�N�g�𓯊�����
	 */
	public synchronized void synchronizeLocal() {
		Object[] remoteObjects = null;
		try {
			remoteObjects = getRemoteObjects(); // ��O���������邱�Ƃ�����
		} catch (Exception e) {
			// void
		}

		if (!mappingRule.getClassMapping().allowZombie()
				&& (remoteObjects == null || !ping(remoteObjects))) {
			remove();
			return;
		}
		synchronizeLocalAttribute();
		synchronizeLocalReference();
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject).synchronizeChildComponents();
			}
			return;
		}
		for (Object content : localObject.eContents()) {
			if (content instanceof LocalObject) {
				LocalObject lo = (LocalObject) content;
				if (lo.getSynchronizationSupport() != null) {
					lo.getSynchronizationSupport().synchronizeLocal();
				}
			}
		}
	}

	private void remove() {
		EObject container = localObject.eContainer();
		if (container == null) {
			EcoreUtil.remove(localObject);
			return;
		}
		synchronized (container) {
			EcoreUtil.remove(localObject);
		}
	}

	private void synchronizeLocalAttribute() {
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject).synchronizeLocalAttribute(null);
			} 
		}
		for (AttributeMapping attibuteMapping : mappingRule
				.getAllAttributeMappings()) {
			try {
				attibuteMapping.syncronizeLocal(localObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void synchronizeLocalReference() {
		if (localObject.eContainer() instanceof SystemDiagram) {
			if (localObject instanceof Component) {
				((Component) localObject)
						.synchronizeLocalReference();
			} 
		}
		for (ReferenceMapping referenceMapping : mappingRule
				.getAllReferenceMappings()) {
			try {
				referenceMapping.syncronizeLocal(localObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �I�u�W�F�N�g�ɑ΂��Đڑ��ł��邩�m�F����
	 * 
	 * @param remoteObject
	 * @return �ڑ��ł��邩�ǂ���
	 */
	public static boolean ping(Object[] remoteObject) {
		if (remoteObject != null) {
			for (Object object : remoteObject) {
				if (ping(object) == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * �I�u�W�F�N�g�ɑ΂��Đڑ��ł��邩�m�F����
	 * 
	 * @param remoteObject
	 * @return �ڑ��ł��邩�ǂ���
	 */
	public static boolean ping(Object remoteObject) {
		try {
			if (remoteObject instanceof org.omg.CORBA.Object) {
				return((org.omg.CORBA.Object) remoteObject)._non_existent() == false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * �I�u�W�F�N�g�O���t������A���[�J���������[�g�I�u�W�F�N�g�ɂ���Č������܂��B
	 * 
	 * @param remoteObjects
	 * @param graphPart
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static LocalObject findLocalObjectByRemoteObject(
			Object[] remoteObjects, EObject graphPart) {
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject result = findLocalObject(obj, remoteObjects);
			if (result != null) return result;
		}

		return null;
	}
	
	private static LocalObject findLocalObject(Object obj, Object[] remoteObjects){
		if (!(obj instanceof LocalObject)) return null;
		LocalObject result = (LocalObject) obj;
		Object[] tmp;
		if (obj instanceof Port) {
			tmp = new Object[]{CorbaPortSynchronizerImpl.getRemoteObjectsForSync(result)};
		} else {
			if (result.getSynchronizationSupport() == null) return null;
			tmp = result.getSynchronizationSupport().getRemoteObjects();
		}
		if (remoteObjects.length != tmp.length) return null;
		for (int i = 0; i < remoteObjects.length; ++i) {
			if (!remoteObjects[i].equals(tmp[i])) return null;
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static Collection<LocalObject> findLocalObjectByRemoteObjects(
			Object[] remoteObjects, LocalObject graphPart) {
		Collection<LocalObject> result = new ArrayList<LocalObject>();
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject temp = findLocalObject(obj, remoteObjects);
			if (temp != null) result.add(temp);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void poulateLocalObjectByRemoteObjects(EList list,
			Object[] remoteObjects, LocalObject graphPart) {
		EObject graphRoot = EcoreUtil.getRootContainer(graphPart);

		for (Iterator iter = graphRoot.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			LocalObject temp = findLocalObject(obj, remoteObjects);
			if (temp != null) list.add(temp);
		}
	}

	/**
	 * �����[�g�I�u�W�F�N�g���擾����
	 * 
	 * @return �����[�g�I�u�W�F�N�g
	 */
	public Object[] getRemoteObjects() {
		List<Object> result = new ArrayList<Object>();
		for (ConstructorParamMapping mapping : mappingRule.getClassMapping()
				.getConstructorParamMappings()) {
			result.add(localObject.eGet(mapping.getFeature()));
		}

		return result.toArray();
	}

}
