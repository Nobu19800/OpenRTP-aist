package jp.go.aist.rtm.systemeditor.restoration;

/**
 * �R���\�[�����[�h�A�v���P�[�V�����ŁA���ʂ����W����I�u�W�F�N�g
 */
public interface Result {
	/**
	 * �������ďI���������ǂ���
	 * 
	 * @return ����������
	 */
	public boolean isSuccess();

	/**
	 * �������ǂ����ݒ肷��
	 * 
	 * @param success
	 *            �������ǂ���
	 */
	public void setSuccess(boolean success);

	/**
	 * ���ʂ�ǉ�����
	 * 
	 * @param resultPart
	 *            ����
	 */
	public void putResult(String resultPart);
}
