package jp.go.aist.rtm.toolscommon.model.component.util;

import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer;
import jp.go.aist.rtm.toolscommon.model.component.impl.CorbaPortConnectorImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortConnectorSpecificationImpl;

/**
 * PortConnector�̃t�@�N�g���N���X
 *
 */
public class PortConnectorFactory {

	/**
	 * @return	�I�t���C���p��PortConnector
	 */
	public static PortConnector createPortConnectorSpecification() {
		return new PortConnectorSpecificationImpl();
	}

	private static PortConnector createCorbaPortConnector() {
		return new CorbaPortConnectorImpl();
	}

	/**
	 * @param profile
	 * @return	�R�l�N�^�v���t�@�C���ɑ�����PortConnector
	 */
	public static PortConnector createPortConnector(ConnectorProfile profile) {
		if (profile instanceof CorbaConnectorProfile) {
			return createCorbaPortConnector();
		} else {
			return createPortConnectorSpecification();
		}
	}

	/**
	 * @param corbaObjectInterface
	 * @return �I�����C�����ǂ����ɑ�����PortConnector
	 * ����̓I�����C���̏ꍇ��CORBA�Œ�
	 */
	public static PortConnector createPortConnector(boolean online) {
		if (online) {
			return createCorbaPortConnector();
		} else {
			return createPortConnectorSpecification();
		}
	}

	/**
	 * @param port	�R�l�N�^�̐ڑ����܂��͐ڑ���ƂȂ�|�[�g
	 * @return�@�@�@�@�@�|�[�g�ɑ������R�l�N�^�i�����CORBA�I�u�W�F�N�g�������ǂ��������Ŕ��f�j
	 */
	public static PortConnector createPortConnector(Port port) {
		return createPortConnector(getCorbaObjectInterface(port) != null);
	}

	private static Object getCorbaObjectInterface(Port port) {
		PortSynchronizer synchronizer = port.getSynchronizer();
		if (!(synchronizer instanceof CorbaPortSynchronizer)) return null;
		return ((CorbaPortSynchronizer)synchronizer).getCorbaObjectInterface();
	}

}
