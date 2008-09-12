package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;

/**
 * ���Q�Ƃ̃}�b�s���O���`���邽�߂̃N���X
 */
public abstract class ManyReferenceMapping extends ReferenceMapping {

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localFeature
	 *            ���[�J���I�u�W�F�N�g�̃t�B�[�`��
	 */
	public ManyReferenceMapping(EReference localFeature) {
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
	public ManyReferenceMapping(EReference localFeature, boolean allowZombie) {
		super(localFeature, allowZombie);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void syncronizeLocal(LocalObject localObject) {
		List newRemoteLinkList = getNewRemoteLinkList(localObject
				.getSynchronizationSupport().getRemoteObjects());

		List oldRemoteLinkList = getOldRemoteLinkList(localObject);

		List deleteRemoteLinkList = new ArrayList(oldRemoteLinkList);
		removeAll(deleteRemoteLinkList, newRemoteLinkList, this, localObject);

		for (Object remoteLink : deleteRemoteLinkList) {
			((EList) localObject.eGet(getLocalFeature()))
					.remove(getLocalObjectByRemoteLink(localObject, remoteLink));
		}

		List addRemoteLinkList = new ArrayList(newRemoteLinkList);
		removeAll(addRemoteLinkList, oldRemoteLinkList, this, localObject);

		for (java.lang.Object link : addRemoteLinkList) {
			Object[] remoteObjectByRemoteLink = getRemoteObjectByRemoteLink(
					localObject, localObject.getSynchronizationSupport()
							.getRemoteObjects(), link);
			if (remoteObjectByRemoteLink != null) {
				if (isAllowZombie()
						|| SynchronizationSupport
								.ping(remoteObjectByRemoteLink)) {
					LocalObject childNC = loadLocalObjectByRemoteObject(
							localObject, localObject
									.getSynchronizationSupport()
									.getSynchronizationManager(), link,
							remoteObjectByRemoteLink);
					if (childNC != null) {
						((EList) localObject.eGet(getLocalFeature()))
								.add(childNC);
					}
				}
			}
		}
	}

	/**
	 * �ŐV�̃����[�g�I�u�W�F�N�g�̃����N��Ԃ��悤�ɁA�I�[�o�[���C�h����邱�Ƃ��Ӑ}�����
	 * 
	 * @param parentRemoteObjects
	 *            �����[�g�I�u�W�F�N�g
	 * @return �ŐV�̃����[�g�I�u�W�F�N�g�̃����N
	 */
	public abstract List getNewRemoteLinkList(Object[] parentRemoteObjects);

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
	public List getOldRemoteLinkList(LocalObject localObject) {
		List result = new ArrayList<Object[]>();
		for (Iterator iter = ((EList) localObject.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();
			if (elem.getSynchronizationSupport().getRemoteObjects().length != 1) {
				throw new UnsupportedOperationException();
			}
			result.add(elem.getSynchronizationSupport().getRemoteObjects()[0]);
		}

		return result;
	}

	/**
	 * �����[�g�I�u�W�F�N�g�̃����N����A���[�J���I�u�W�F�N�g���擾����
	 * 
	 * @param parent
	 *            �e�̃��[�J���I�u�W�F�N�g
	 * @param link
	 *            �����[�g�I�u�W�F�N�g�̃����N
	 * @return ���[�J���I�u�W�F�N�g
	 */
	public LocalObject getLocalObjectByRemoteLink(LocalObject parent,
			java.lang.Object link) {
		LocalObject result = null;
		for (Iterator iter = ((EList) parent.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();

			if (link
					.equals(elem.getSynchronizationSupport().getRemoteObjects())) {
				result = elem;
				break;
			}
		}

		return result;
	}

	private static void removeAll(List target, List deleteList,
			ReferenceMapping referenceMapping, LocalObject localObject) {

		List tempDeleteList = new ArrayList();
		for (Iterator iter = deleteList.iterator(); iter.hasNext();) {
			java.lang.Object temp1 = iter.next();

			for (Iterator iterator = target.iterator(); iterator.hasNext();) {
				java.lang.Object temp2 = iterator.next();

				if (referenceMapping.isLinkEquals(temp1, temp2)) {
					tempDeleteList.add(temp2);
				}
			}
		}

		for (java.lang.Object deleteBinding : tempDeleteList) {
			target.remove(deleteBinding);
		}
	}
}
