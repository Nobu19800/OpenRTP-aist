package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.ecore.EReference;

/**
 * �P�Q�Ƃ̃}�b�s���O���`����N���X
 */
public abstract class OneReferenceMapping extends ReferenceMapping {

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localFeature
	 *            ���[�J���I�u�W�F�N�g�̃t�B�[�`��
	 */
	public OneReferenceMapping(EReference localFeature) {
		this(localFeature, false);
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localFeature
	 *            ���[�J���I�u�W�F�N�g�̃t�B�[�`��
	 * @param allowZombie
	 *            �]���r�i�����[�g�I�u�W�F�N�g�����񂾏�ԁj�ł����݂����邩
	 */
	public OneReferenceMapping(EReference localFeature, boolean allowZombie) {
		super(localFeature, allowZombie);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void syncronizeLocal(LocalObject localObject) {
//		long start = System.currentTimeMillis();
		
		Object remoteLink = getNewRemoteLink(localObject, localObject
				.getSynchronizationSupport().getRemoteObjects());
		Object localLink = getOldRemoteLink(localObject);

		if (isLinkEquals(remoteLink, localLink) == false) {
			Object[] remoteObjectByRemoteLink = null;
			if (remoteLink != null) {
				remoteObjectByRemoteLink = getRemoteObjectByRemoteLink(
						localObject, localObject.getSynchronizationSupport()
								.getRemoteObjects(), remoteLink);
			}

			LocalObject loadLocalObjectByRemoteObject = null;
			if (remoteObjectByRemoteLink != null) {
				if (isAllowZombie()
						|| SynchronizationSupport
								.ping(remoteObjectByRemoteLink)) {
					loadLocalObjectByRemoteObject = loadLocalObjectByRemoteObject(
							localObject, localObject
									.getSynchronizationSupport()
									.getSynchronizationManager(), remoteLink,
							remoteObjectByRemoteLink);
				}
			}

			if ((localObject.eGet(getLocalFeature()) == null && loadLocalObjectByRemoteObject != null)
					|| (localObject.eGet(getLocalFeature()) != null && loadLocalObjectByRemoteObject == null)) { //������
				localObject.eSet(getLocalFeature(),
						loadLocalObjectByRemoteObject);
			}
		}
//		System.out.println(System.currentTimeMillis() -start);
	}

	/**
	 * �ŐV�̃����[�g�I�u�W�F�N�g�̃����N��Ԃ��悤�ɁA�I�[�o�[���C�h����邱�Ƃ��Ӑ}�����
	 * 
	 * @param localObject
	 * 
	 * @param remoteObjects
	 *            �����[�g�I�u�W�F�N�g
	 * @return �ŐV�̃����[�g�I�u�W�F�N�g�̃����N
	 */
	public abstract Object getNewRemoteLink(LocalObject localObject,
			Object[] remoteObjects);

	/**
	 * ���ݎg�p���Ă���A�����[�g�I�u�W�F�N�g�̃����N��Ԃ�
	 * <p>
	 * �K�v�ɉ����āA�I�[�o�[���C�h���邱�� �f�t�H���g�����́A���[�J���I�u�W�F�N�g�ɑ΂��āA�����[�g�I�u�W�F�N�g���P�ł���ꍇ�̎����B // *
	 * �֘A�I�u�W�F�N�g�Ȃǂ̕����̃����[�g�I�u�W�F�N�g�����݂���ꍇ�ɂ́A�I�[�o�[���C�h���Ȃ���΂Ȃ�Ȃ��B
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @return ���ݎg�p���Ă���A�����[�g�I�u�W�F�N�g�̃����N
	 */
	public Object getOldRemoteLink(LocalObject localObject) {
		LocalObject elem = ((LocalObject) localObject.eGet(getLocalFeature()));

		Object result = null;
		if (elem != null) {
			if (elem.getSynchronizationSupport().getRemoteObjects().length != 1) {
				throw new UnsupportedOperationException();
			}
			result = elem.getSynchronizationSupport().getRemoteObjects()[0];
		}

		return result;
	}
}
