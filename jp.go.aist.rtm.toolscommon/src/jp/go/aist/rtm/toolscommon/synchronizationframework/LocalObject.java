package jp.go.aist.rtm.toolscommon.synchronizationframework;

import org.eclipse.emf.ecore.EObject;


/**
 * �����̑ΏۂƂȂ郍�[�J���I�u�W�F�N�g���������ׂ��C���^�t�F�[�X
 */
public interface LocalObject extends EObject {
	/**
	 * �����T�|�[�g���擾����
	 * 
	 * @return �����T�|�[�g
	 */
	public SynchronizationSupport getSynchronizationSupport();

	/**
	 * �����T�|�[�g��ݒ肷��
	 * 
	 * @param synchronizationSupport
	 *            �����T�|�[�g
	 */
	public void setSynchronizationSupport(
			SynchronizationSupport synchronizationSupport);
}
