package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;

import org.eclipse.emf.ecore.EReference;

/**
 * �Q�ƃ}�b�s���O���`����N���X
 */
public abstract class ReferenceMapping {
	private EReference localFeature;

	private boolean allowZombie;

	/**
	 * �R���X�g���N�^
	 */
	public ReferenceMapping(EReference localFeature, boolean allowZombie) {
		this.localFeature = localFeature;
		this.allowZombie = allowZombie;
	}

	/**
	 * ���[�J���𓯊������ƂȂ郁�\�b�h
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @param synchronizationManager
	 *            �����}�l�[�W��
	 */
	public abstract void syncronizeLocal(LocalObject localObject);

	/**
	 * ���[�J���I�u�W�F�N�g�̃t�B�[�`�����擾����
	 * 
	 * @return ���[�J���I�u�W�F�N�g�̃t�B�[�`��
	 */
	public EReference getLocalFeature() {
		return localFeature;
	}

	/**
	 * �����N���������̂��ǂ���
	 */
	public boolean isLinkEquals(java.lang.Object link1, java.lang.Object link2) {
		boolean result = false;
		if (link1 == null) {
			result = link2 == null;
		} else {
			result = link1.equals(link2);
		}

		return result;
	}

	/**
	 * �����[�g�����N����A�����[�g�I�u�W�F�N�g���擾����
	 * <p>
	 * ���̃��\�b�h���I�[�o�[���C�h�����ꍇ�ɂ́AgetLocalObjectByRemoteLink���I�[�o�[���C�h����\�����������Ƃɒ��ӂ��邱��
	 * 
	 * @param localObject
	 *            TODO
	 * @param remoteObjects
	 *            �e�̃����[�g�I�u�W�F�N�g
	 * @param link
	 *            �����N
	 * 
	 * 
	 * @return
	 */
	public Object[] getRemoteObjectByRemoteLink(LocalObject localObject,
			Object[] remoteObjects, java.lang.Object link) {
		return new Object[] { link };
	}

	/**
	 * �����[�g�I�u�W�F�N�g�����[�h����
	 * <p>
	 * �����̃I�u�W�F�N�g�O���t�ɃI�u�W�F�N�g�����݂���΂����Ԃ��A���݂��Ȃ���΍쐬���Ă����Ԃ��B
	 * 
	 * @param localObject
	 * @param synchronizationManager
	 * @param link
	 * @param remoteObject
	 * @return
	 */
	public LocalObject loadLocalObjectByRemoteObject(LocalObject localObject,
			SynchronizationManager synchronizationManager,
			java.lang.Object link, Object[] remoteObject) {
		LocalObject result;

		// result = SynchronizationSupport
		// .findLocalObjectByRemoteObject(remoteObject, localObject);
		// if (result == null) {
		result = synchronizationManager.createLocalObject(localObject,
				remoteObject, link);
		// }
		return result;
	}

	/**
	 * �]���r���������ǂ���
	 * 
	 * @return
	 */
	public boolean isAllowZombie() {
		return allowZombie;
	}
}
