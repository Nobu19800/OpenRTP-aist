package jp.go.aist.rtm.toolscommon.corba;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NamingContext;

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
	public static List<Binding> getBindingList(NamingContext target) {
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
//			"-ORBTCPReadTimeouts",	"1:60000:300:1" }
//		, null);
	
	// omniORB�̃I�v�V�������낤. JDK��CORBA�����ɂ͂Ȃ�
//	"-ORBclientCallTimeOutPeriod", 
//	String.valueOf(ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
//		ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD)
	private static ORB orb = ORB.init(new String[] {}, createProps());
	static {
		try {
			Method declaredMethod = orb.getClass().getMethod("getLogger", String.class);
			Logger logger = (Logger) declaredMethod.invoke(orb, "");
			logger.setLevel(Level.SEVERE);
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

	private static Properties createProps() {
		setConnectionTimeout();
		
		ToolsCommonPreferenceManager.getInstance().addPropertyChangeListener(createListner());
		
		Properties props = new Properties();
		props.put("com.sun.CORBA.transport.ORBSocketFactoryClass"
				, "jp.go.aist.rtm.toolscommon.corba.TimeoutCorbaORBSocketFactory");
		return props;
	}

	private static void setConnectionTimeout() {
		int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
				ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
		TimeoutCorbaORBSocketFactory.setConnectionTimeout(defaultTimeout);
	}

	private static PropertyChangeListener createListner() {
		return new PropertyChangeListener(){
//			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setConnectionTimeout();
			}};
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
