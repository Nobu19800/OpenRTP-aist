package jp.go.aist.rtm.rtcbuilder.util;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

/**
 * org.eclipse.core.runtime.IAdaptable���������[�e�B���e�B�N���X
 */
public class AdapterUtil {

	/**
	 * �A�_�v�^���擾����
	 * <p>
	 * �Ώۂ̃I�u�W�F�N�g���A�Ώۂ̃N���X���p�����Ă���ꍇ�ɂ́A���̃I�u�W�F�N�g��Ԃ��A
	 * �p�����Ă��Ȃ��ꍇ�ɂ́AIAdaptable#getAdapter���g�p���ăA�_�v�^�̎擾�����݂�
	 * ����ł���������Ȃ��ꍇ�ɂ́AEclipse��AdapterManager���g�p����
	 * 
	 * @param obj
	 *            �Ώۂ̃I�u�W�F�N�g
	 * @param clazz
	 *            �Ώۂ̃N���X
	 * @return �A�_�v�^
	 */
	@SuppressWarnings("unchecked")
	public static Object getAdapter(Object obj, Class clazz) {
		if (obj == null) {
			return null;
		}

		Object result = null;
		if (clazz.isAssignableFrom(obj.getClass())) {
			result = obj;
		} else if (obj instanceof IAdaptable) {
			result = ((IAdaptable) obj).getAdapter(clazz);
		}

		if (result == null) {
			result = Platform.getAdapterManager().getAdapter(obj, clazz);
		}

		return result;
	}
}
