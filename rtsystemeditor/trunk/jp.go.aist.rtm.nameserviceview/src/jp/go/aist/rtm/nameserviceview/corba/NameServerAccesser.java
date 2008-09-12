package jp.go.aist.rtm.nameserviceview.corba;

import jp.go.aist.rtm.nameserviceview.manager.NameServiceViewPreferenceManager;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 * �l�[���T�[�o�ɃA�N�Z�X���郆�[�e�B���e�B
 */
public class NameServerAccesser {
	/**
	 * �V���O���g���C���X�^���X
	 */
	private static NameServerAccesser __instance = new NameServerAccesser();

	/**
	 * �V���O���g���ւ̃A�N�Z�T
	 * 
	 * @return �V���O���g��
	 */
	public static NameServerAccesser getInstance() {
		return __instance;
	}

	/**
	 * �l�[���T�[�o�Ƃ��đΏۃA�h���X�ɃA�N�Z�X�\�ł��邩�ǂ����m�F����
	 * 
	 * @param address
	 *            �����Ώۂ̃A�h���X
	 * @return �l�[���T�[�o�Ƃ��ăA�N�Z�X�\���ǂ���
	 */
	public boolean validateNameServerAddress(String address) {
		boolean result = false;
		try {
			if (getNameServerRootContext(address) != null) {
				result = true;
			}
		} catch (Exception e) {
			// void
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * �A�h���X�������Ɏ��A�l�[���T�[�o�̃��[�g��NamingContextExt��Ԃ�
	 * <p>
	 * �`���́A�uaddress:port�v�ƂȂ�B�|�[�g���w�肳��Ă��Ȃ��ꍇ�ɂ́A���[�U�ݒ�|�[�g���g�p����
	 * 
	 * @param address
	 *            �l�[���T�[�o�̃A�h���X
	 * @return �l�[���T�[�o�̃��[�g��NamingContextExt
	 */
	public NamingContextExt getNameServerRootContext(String address) {
		if ("".equals(address)) {
			return null;
		}

		if (address.indexOf(":") == -1) {
			// address = address + ":2809";
			address = address
					+ ":"
					+ NameServiceViewPreferenceManager.getInstance().getDefaultPort(
							NameServiceViewPreferenceManager.DEFAULT_CONNECTION_PORT);
		}

		NamingContextExt result = NamingContextExtHelper.narrow(CorbaUtil
				.getOrb().string_to_object(
						"corbaloc:iiop:1.2@" + address + "/NameService"));

		return result;
	}

}
