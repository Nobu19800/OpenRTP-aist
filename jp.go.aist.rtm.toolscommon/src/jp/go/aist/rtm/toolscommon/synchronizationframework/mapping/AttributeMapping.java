package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * �����̃}�b�s���O���`���邽�߂̃N���X
 */
public abstract class AttributeMapping {
	private EStructuralFeature localFeature;

	private boolean once;

	/**
	 * �R���X�g���N�^
	 * <p>
	 * once�́Afalse�ƂȂ�
	 * 
	 * @param localFeature
	 *            ���[�J���I�u�W�F�N�g�̃t�B�[�`���[
	 */
	public AttributeMapping(EStructuralFeature localFeature) {
		this(localFeature, false);
	}

	/**
	 * �R���X�g���N�^
	 * <p>
	 * once�́A���ۂɂ͈�x�����擾���Ȃ��̂ł͂Ȃ��A�f�t�H���g�l�łȂ���Ύ擾����Ƃ�������������Ȃ��B
	 * ���̂��߁A�f�t�H���g�l�ɗ�O�I�Ȓl���܂܂�Ă��邱�Ƃ����߂���B
	 * 
	 * @param localFeature
	 *            ���[�J���I�u�W�F�N�g�̃t�B�[�`���[
	 * @param once
	 *            ��x�����l���擾���Ȃ����ǂ���
	 */
	public AttributeMapping(EStructuralFeature localFeature, boolean once) {
		this.localFeature = localFeature;
		this.once = once;
	}

	/**
	 * ���[�J���𓯊������ƂȂ郁�\�b�h
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @param synchronizationManager
	 *            �����}�l�[�W��
	 */
	public void syncronizeLocal(LocalObject localObject) {
		if (once == false
				|| (once && localObject.eIsSet(localFeature) == false)) {
			Object oldValue = getLocalAttributeValue(localObject);
			Object newValue = convert2LocalValue(localObject,
					getRemoteAttributeValue(localObject));

			if (newValue == null)
				return; // EMF��NullPointerException���N���Ȃ��悤�ɂ��Ă��� 2008.12.11

			boolean updated = false;

			if (!isEquals(newValue, oldValue)) {
				localObject.eSet(getLocalFeature(), newValue);
				updated = true;
			}
			if (updated) {
				// �ύX���������ꍇ�̎��㏈��
				postSynchronizeLocal(localObject);
			}
		}
	}

	/**
	 * �����[�g�I�u�W�F�N�g�̑����̒l��Ԃ��悤�ɁA�I�[�o�[���C�h����邱�Ƃ��Ӑ}�����B
	 * <p>
	 * ���̃��\�b�h���I�[�o�[���[�h���ꂽ�ALocalObject�̃��\�b�h��K���I�[�o�[���C�h���邱��
	 * @param localObject TODO
	 * @param remoteObjects
	 *            �����[�g�I�u�W�F�N�g
	 * 
	 * @return �����[�g�I�u�W�F�N�g�̑����l
	 */
	public Object getRemoteAttributeValue(LocalObject localObject, Object[] remoteObjects) {
		throw new UnsupportedOperationException();
	}

	/**
	 * �����[�g�I�u�W�F�N�g�̑����̒l��Ԃ��B
	 * <p>
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @return �����[�g�I�u�W�F�N�g�̑����l
	 */
	public Object getRemoteAttributeValue(LocalObject localObject) {
		return getRemoteAttributeValue(localObject, localObject.getSynchronizationSupport()
				.getRemoteObjects());
	}

	/**
	 * ���[�J���I�u�W�F�N�g�̑����̒l��Ԃ��B
	 * <p>
	 * �K�v�ɉ����āA�I�[�o�[���C�h���邱�ƁB
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @return ���[�J���I�u�W�F�N�g�̑����̒l
	 */
	public Object getLocalAttributeValue(LocalObject localObject) {
		return localObject.eGet(localFeature);
	}

	/**
	 * 2�̒l�������ł��邩�ǂ���
	 * <p>
	 * ������null�̏ꍇ�ɂ�true�ƂȂ�
	 * 
	 * @param value1
	 *            1�߂̒l
	 * @param value2
	 *            2�߂̒l
	 * @return 2�̒l�������ł��邩�ǂ���
	 */
	public boolean isEquals(Object value1, Object value2) {
		boolean result = false;
		if (value1 == null) {
			result = (value2 == null);
		} else {
			result = value1.equals(value2);
		}

		return result;
	}

	/**
	 * ���[�J���I�u�W�F�N�g�̃t�B�[�`���[
	 * 
	 * @return ���[�J���I�u�W�F�N�g�̃t�B�[�`���[
	 */
	public EStructuralFeature getLocalFeature() {
		return localFeature;
	}

	/**
	 * �����[�g�̒l���烍�[�J���̒l�ɕϊ�����
	 * <p>
	 * �K�v�ɉ����ăI�[�o�[���C�h���邱��
	 * 
	 * @param localObject
	 *            ���[�J���I�u�W�F�N�g
	 * @param remoteAttributeValue
	 *            �����[�g�̒l
	 * 
	 * @return ���[�J���̒l
	 */
	public Object convert2LocalValue(LocalObject localObject,
			Object remoteAttributeValue) {
		return remoteAttributeValue;
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
