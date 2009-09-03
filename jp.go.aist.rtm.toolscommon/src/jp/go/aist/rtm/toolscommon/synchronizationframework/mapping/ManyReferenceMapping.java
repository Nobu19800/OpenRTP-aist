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
	private static class LinkHolder {
		@SuppressWarnings("unchecked")
		private List deleteRemoteLinkList;
		@SuppressWarnings("unchecked")
		private List addRemoteLinkList;
	}
	
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

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void syncronizeLocal(LocalObject localObject) {
		LinkHolder holder = setupTargetList(getNewRemoteLinkList(localObject),
				getOldRemoteLinkList(localObject));

		boolean updated = false;

		for (Object remoteLink : holder.deleteRemoteLinkList) {
			((EList) localObject.eGet(getLocalFeature()))
					.remove(getLocalObjectByRemoteLink(localObject, remoteLink));
			updated = true;
		}

		for (java.lang.Object link : holder.addRemoteLinkList) {
			Object[] remoteObjectByRemoteLink = getRemoteObjectByRemoteLink(
					localObject, getRemoteObjects(localObject), link);
			if (remoteObjectByRemoteLink != null) {
				if (isAllowZombie()
						|| SynchronizationSupport
								.ping(remoteObjectByRemoteLink)) {
					LocalObject childNC = loadLocalObjectByRemoteObject(
							localObject, localObject
									.getSynchronizationSupport()
									.getSynchronizationManager(), link,
							remoteObjectByRemoteLink);
					dumpLoadResultForPort(localObject, childNC);
					if (childNC != null) {
						((EList) localObject.eGet(getLocalFeature()))
								.add(childNC);
						updated = true;
					}
				}
			}
		}

		if (updated) {
			// �ύX���������ꍇ�̎��㏈��
			postSynchronizeLocal(localObject);
		}
	}

	@SuppressWarnings("unchecked")
	private LinkHolder setupTargetList(List newRemoteLinkList, List oldRemoteLinkList) {
		LinkHolder holder = new LinkHolder();
		holder.deleteRemoteLinkList = new ArrayList(oldRemoteLinkList);
		holder.addRemoteLinkList = new ArrayList(newRemoteLinkList);
		
		for (int i = 0; i < holder.addRemoteLinkList.size();) {
			int existIndex = findExistLisk(holder.addRemoteLinkList.get(i), holder.deleteRemoteLinkList);
			if (existIndex < 0) {
				i++;
			} else {
				holder.addRemoteLinkList.remove(i);
				holder.deleteRemoteLinkList.remove(existIndex);
			}
		}
		return holder;
	}

	@SuppressWarnings("unchecked")
	private int findExistLisk(Object newLink, List deleteRemoteLinkList) {
		for (int i = 0; i < deleteRemoteLinkList.size(); i++) {
			if (isLinkEquals(newLink, deleteRemoteLinkList.get(i))) return i;
		}
		return -1;
	}

	protected void dubugPrint(LocalObject localObject) {
	}

	protected Object[] getRemoteObjects(LocalObject localObject) {
		return localObject
				.getSynchronizationSupport().getRemoteObjects();
	}

	private void dumpLoadResultForPort(LocalObject localObject,
			LocalObject childNC) {
	}

	/**
	 * �ŐV�̃����[�g�I�u�W�F�N�g�̃����N��Ԃ��悤�ɁA�I�[�o�[���C�h����邱�Ƃ��Ӑ}�����
	 * 
	 * @param parentRemoteObjects
	 *            �����[�g�I�u�W�F�N�g
	 * @return �ŐV�̃����[�g�I�u�W�F�N�g�̃����N
	 */
	@SuppressWarnings("unchecked")
	protected List getNewRemoteLinkList(Object[] parentRemoteObjects){return null;};


	@SuppressWarnings("unchecked")
	protected List getNewRemoteLinkList(LocalObject localObject) {
		return getNewRemoteLinkList(getRemoteObjects(localObject));
	}

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
	@SuppressWarnings("unchecked")
	public List getOldRemoteLinkList(LocalObject localObject) {
		List result = new ArrayList<Object[]>();
		for (Iterator iter = ((EList) localObject.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();
			try {
				if (getRemoteObjects(elem).length == 1) {
					result.add(getRemoteObjects(elem)[0]);
				}
			} catch (Exception e) {
				iter.remove();
			}
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
	@SuppressWarnings("unchecked")
	public LocalObject getLocalObjectByRemoteLink(LocalObject parent,
			java.lang.Object link) {
		LocalObject result = null;
		java.lang.Object[] links = null;
		if (link instanceof java.lang.Object[]) {
			links = (java.lang.Object[]) link;
		} else {
			links = new java.lang.Object[] { link };
		}
		for (Iterator iter = ((EList) parent.eGet(getLocalFeature()))
				.iterator(); iter.hasNext();) {
			LocalObject elem = (LocalObject) iter.next();
			java.lang.Object[] remotes = getRemoteObjects(elem);
			if (links.length != remotes.length) {
				continue;
			}
			boolean hit = true;
			for (int i = 0; i < links.length; i++) {
				if (!links[i].equals(remotes[i])) {
					hit = false;
					break;
				}
			}
			if (hit) {
				result = elem;
				break;
			}
		}
		return result;
	}

	/**
	 * ������̎��㏈�����`���܂��B
	 * 
	 * @param lo
	 *            �����Ώۂ̃��[�J���I�u�W�F�N�g
	 */
	public void postSynchronizeLocal(LocalObject lo) {
	}
}
