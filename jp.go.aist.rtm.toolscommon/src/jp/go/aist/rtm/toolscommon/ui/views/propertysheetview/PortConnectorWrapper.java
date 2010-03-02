package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import org.eclipse.emf.ecore.EObject;

/**
 * �R�l�N�^�̃��b�p�N���X
 */
public class PortConnectorWrapper {
	private EObject connector;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param component
	 *            �h���C�����f��
	 */
	public PortConnectorWrapper(EObject connector) {
		this.connector = connector;
	}

	/**
	 * �R�l�N�^���擾����
	 * 
	 * @return �R�l�N�^
	 */
	public EObject getConnector() {
		return connector;
	}

	/**
	 * �R�l�N�^��ݒ肷��
	 * 
	 * @param component
	 *            �R�l�N�^
	 */
	public void setConnector(EObject connector) {
		this.connector = connector;
	}
}
