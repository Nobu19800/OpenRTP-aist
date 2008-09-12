package jp.go.aist.rtm.toolscommon.synchronizationframework;

import java.util.Iterator;

import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.eclipse.emf.ecore.EObject;

/**
 * �����}�l�[�W��
 * <p>
 * �}�b�s���O���[���̃��X�g��ێ�����i�����@�\�̃R���e�N�X�g�Ƃ�������j
 * 
 */
public class SynchronizationManager {
	private MappingRule[] mappingRules;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param mappingRules
	 *            �}�b�s���O���[���̃��X�g
	 */
	public SynchronizationManager(MappingRule[] mappingRules) {
		this.mappingRules = mappingRules;
	}

	/**
	 * �����[�g�I�u�W�F�N�g�c���[����A���[�J���I�u�W�F�N�g�c���[���쐬����B
	 * 
	 * @param remoteObject
	 *            �����[�g�I�u�W�F�N�g���[�g
	 * @return �쐬�������[�J���I�u�W�F�N�g
	 */
	public LocalObject createLocalObject(Object[] remoteObjects) {
		return createLocalObject(null, remoteObjects, null);
	}

	/**
	 * �e�̃��[�J���I�u�W�F�N�g�A�����[�g�I�u�W�F�N�g���[�g����у����[�g�I�u�W�F�N�g�̃����N����A���[�J���I�u�W�F�N�g�c���[���쐬����
	 * 
	 * @param parent
	 *            �e�̃��[�J���I�u�W�F�N�g
	 * @param remoteObject
	 *            �����[�g�I�u�W�F�N�g���[�g
	 * @param link
	 *            �����[�g�I�u�W�F�N�g�̃����N
	 * @return �쐬�������[�J���I�u�W�F�N�g
	 */
	public LocalObject createLocalObject(LocalObject parent,
			Object[] remoteObjects, java.lang.Object link) {
		boolean ping = SynchronizationSupport.ping(remoteObjects);

		MappingRule rule = null;
		for (MappingRule temp : mappingRules) {
			try {
				if ((ping || temp.getClassMapping().allowZombie())
						&& (temp.getClassMapping()
								.getConstructorParamMappings().length == remoteObjects.length && temp
								.getClassMapping().isTarget(parent,
										remoteObjects, link))) {
					rule = temp;
					break;
				}
			} catch (Exception e) {
				// void
			}
		}

		LocalObject result = null;
		if (rule != null) {
			try {
				Object[] narrowedRemoteObjects = rule.getClassMapping().narrow(
						remoteObjects);
				result = rule.getClassMapping().createLocalObject(parent,
						narrowedRemoteObjects, link);
				assignSynchonizationSupport(result);
			} catch (Exception e) {
				e.printStackTrace(); // system error
			}
		}
		//
		if (result != null) {
			// for (Iterator iter = result.eAllContents(); iter.hasNext();) {
			// EObject element = (EObject) iter.next();
			// if (element instanceof LocalObject) {
			// ((LocalObject) element).getSynchronizationSupport()
			// .synchronizeLocal();
			// }
			// }

			result.getSynchronizationSupport().synchronizeLocal();
		}

		return result;
	}

	/**
	 * �����T�|�[�g���쐬����
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @param remoteObject
	 *            �����[�g�I�u�W�F�N�g
	 * @param rule
	 *            �}�b�s���O���[��
	 * @return
	 */
	private SynchronizationSupport createSynchronizeSupport(
			LocalObject localObject, MappingRule rule) {
		return new SynchronizationSupport(localObject, rule, this);
	}

	/**
	 * ��܎Q�Ƃ����ǂ�A���ׂẴ��[�J���I�u�W�F�N�g�ɑ΂��āA�����T�|�[�g�𕜌�����
	 * 
	 * @param eobj
	 *            EObject
	 */
	public void assignSynchonizationSupport(EObject eobj) {
		if (eobj instanceof LocalObject) {
			LocalObject localObject = (LocalObject) eobj;
			MappingRule rule = null;
			for (MappingRule temp : mappingRules) {
				if (temp.getClassMapping().getLocalClass().equals(
						localObject.getClass())) {
					rule = temp;
					break;
				}
			}

			if (rule != null) {
				localObject.setSynchronizationSupport(createSynchronizeSupport(
						localObject, rule));
			}
		}

		for (Iterator iter = eobj.eContents().iterator(); iter.hasNext();) {
			EObject obj = (EObject) iter.next();
			assignSynchonizationSupport((obj));
		}
	}

	/**
	 * �}�b�s���O���[�����擾����
	 * @return
	 */
	public MappingRule[] getMappingRules() {
		return mappingRules;
	}

	/**
	 * �}�b�s���O���[����ݒ肷��
	 * @param mappingRules
	 */
	public void setMappingRules(MappingRule[] mappingRules) {
		this.mappingRules = mappingRules;
	}
}
