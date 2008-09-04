package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorSource;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorTarget;
import jp.go.aist.rtm.toolscommon.model.component.Port;

/**
 * �R�l�N�^�[�v���t�@�C�����쐬����������������C���^�t�F�[�X
 */
public interface ConnectorCreateManager {

	/**
	 * �R�l�N�^�v���t�@�C�����擾����
	 * 
	 * @param port
	 *            �|�[�g1
	 * @param port
	 *            �|�[�g2
	 * 
	 * @return �R�l�N�^�v���t�@�C��
	 */
	public ConnectorProfile getConnectorProfile();

	/**
	 * 1�Ԗڂ̃|�[�g���擾����
	 * 
	 * @return 1�Ԗڂ̃|�[�g
	 */
	public ConnectorSource getFirst();

	/**
	 * 1�Ԗڂ̃|�[�g��ݒ肷��
	 * 
	 * @param first
	 *            1�Ԗڂ̃|�[�g
	 */
	public void setFirst(Port first);

	/**
	 * 2�Ԗڂ̃|�[�g���擾����
	 * 
	 * @return 2�Ԗڂ̃|�[�g
	 */
	public ConnectorTarget getSecond();

	/**
	 * 2�Ԗڂ̃|�[�g��ݒ肷��
	 * 
	 * @param second
	 *            2�Ԗڂ̃|�[�g
	 */
	public void setSecond(Port second);

	/**
	 * �ڑ��\�ł��邩�m�F����
	 * 
	 * @return �ڑ��\�ł��邩
	 */
	public boolean validate();

	/**
	 * �v���t�@�C�����쐬���A�R�l�N�^�쐬����
	 */
	public void createProfileAndConnector();

	/**
	 * �v���t�@�C���ɂ���āA�R�l�N�^���쐬����
	 * 
	 * @param connectorProfile
	 * @return
	 */
	public boolean connectR(ConnectorProfile connectorProfile);

}
