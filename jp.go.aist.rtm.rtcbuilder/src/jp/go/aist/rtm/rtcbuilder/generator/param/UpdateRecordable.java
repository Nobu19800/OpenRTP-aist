package jp.go.aist.rtm.rtcbuilder.generator.param;

public interface UpdateRecordable {

	/**
	 * @return �X�V����Ă���ꍇ��true
	 */
	boolean isUpdated();

	/**
	 * �X�V��Ԃ����������܂��B
	 */
	void resetUpdated();

}
