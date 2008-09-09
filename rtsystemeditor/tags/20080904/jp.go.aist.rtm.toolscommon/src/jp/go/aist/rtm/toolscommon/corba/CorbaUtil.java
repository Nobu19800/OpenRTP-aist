package jp.go.aist.rtm.toolscommon.corba;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NamingContextExt;

/**
 * CORBA�Ɋւ��郆�[�e�B���e�B
 */
public class CorbaUtil {

	/**
	 * �Ώۂ�NamingContextExt����q����Binding��List�Ƃ��ĕԂ�
	 * 
	 * @param target
	 *            �Ώۂ�NamingContextExt
	 * @return �q����Binding��List
	 */
	public static List<Binding> getBindingList(NamingContextExt target) {
		BindingListHolder bindingListHolder = new BindingListHolder();
		BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();

		try {
			target.list(9999, bindingListHolder, bindingIteratorHolder);
		} catch (Exception e) {
			// void 
		}

		List<Binding> result = new ArrayList<Binding>();
		for (int i = 0; i < bindingListHolder.value.length; i++) {
			Binding binding = bindingListHolder.value[i];

			result.add(binding);
		}

		return result;
	}

	/**
	 * ORB�I�u�W�F�N�g
	 */
//	private static ORB orb = ORB.init(new String[] {
//			"-ORBclientCallTimeOutPeriod", "3000" }, null);
	private static ORB orb = ORB.init(new String[] {
			"-ORBclientCallTimeOutPeriod", 
				String.valueOf(ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
					ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD)) }, null);
	static {
		try {
			if (orb instanceof com.sun.corba.se.spi.orb.ORB) {
				Logger logger = ((com.sun.corba.se.spi.orb.ORB) orb)
						.getLogger("");
				logger.setLevel(Level.SEVERE); // log 
			}
		} catch (Exception e) {
			e.printStackTrace(); // system error
		}
	}

	/**
	 * ORB�I�u�W�F�N�g���擾����
	 * 
	 * @return ORB
	 */
	public static ORB getOrb() {
		return orb;
	}

	/**
	 * CORBA�I�u�W�F�N�g�i�v���L�V�j���V���A���C�Y���������񂩂�ACORBA�I�u�W�F�N�g�֕ϊ�����
	 * 
	 * @param str
	 *            CORBA�I�u�W�F�N�g�i�v���L�V�j���V���A���C�Y����������
	 * @return CORBA�I�u�W�F�N�g
	 */
	public static org.omg.CORBA.Object stringToObject(String str) {
		return orb.string_to_object(str);
	}
}
