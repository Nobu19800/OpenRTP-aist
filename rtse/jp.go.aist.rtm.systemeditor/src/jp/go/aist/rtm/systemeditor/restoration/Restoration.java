package jp.go.aist.rtm.systemeditor.restoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationSupport;
import _SDOPackage.Configuration;

/**
 * ���[�h���ɕ������s���N���X
 */
public class Restoration {
	/**
	 * ���s���C��
	 * 
	 * @param systemDiagram
	 *            �V�X�e���_�C�A�O����
	 * @param result
	 *            ���ʊi�[
	 */
	public static void execute(SystemDiagram systemDiagram, Result result) {
		result.setSuccess(true);

		processAllPing(result, systemDiagram);

		processAllRestoreConfigurationSet(result, systemDiagram);

//		processAllConnect(result, systemDiagram);

		//processAllStart(result, systemDiagram);
	}

	/**
	 * RtcLink��XML�Ɋ܂܂�邷�ׂẴR���t�B�O���[�V�����𕜌�����B
	 * �����Corba�R���|�[�l���g�݂̂ɑΉ�
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	@SuppressWarnings("unchecked")
	public static void processAllRestoreConfigurationSet(Result result,
			SystemDiagram systemDiagram) {
		
		List<CorbaConfigurationSet> remoteConfigurationSets = new ArrayList<CorbaConfigurationSet>();
		
		for (Component c: systemDiagram.getRegisteredComponents()) {
			if (c instanceof CorbaComponent) {
				CorbaComponent component = (CorbaComponent) c;

				boolean isOk = false;
				try {
					Configuration configuration = component.getCorbaObjectInterface()
						.	get_configuration();
					_SDOPackage.ConfigurationSet[] remoteConfigurationSet = configuration
							.get_configuration_sets();	
					for (_SDOPackage.ConfigurationSet remote : remoteConfigurationSet) {
						CorbaConfigurationSet configSet = (CorbaConfigurationSet) CorbaWrapperFactory.getInstance()
																.createWrapperObject(remote);
						remoteConfigurationSets.add(configSet);
					}
					
					isOk = component.updateConfigurationSetListR(component
							.getConfigurationSets(), component
							.getActiveConfigurationSet(),
							remoteConfigurationSets);
				} catch (Exception e) {
					e.printStackTrace();
					
					// void
				}
				if (isOk == false) {
					result.putResult(Messages.getString("Restoration.0") //$NON-NLS-1$
							+ c.getInstanceNameL() + " : " //$NON-NLS-1$
							+ c.getPathId() + "]"); //$NON-NLS-1$
					result.setSuccess(false);
				}
			}
		}
	}

	/**
	 * RtcLink��XML�Ɋ܂܂�邷�ׂĂ�RTC�ɃA�N�Z�X�\�ł��邩�m�F����B
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	@SuppressWarnings("unchecked")
	private static void processAllPing(Result result,
			SystemDiagram systemDiagram) {
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof CorbaComponent) {
				CorbaComponent c = (CorbaComponent) obj;
				boolean isOk = SynchronizationSupport.ping(c.getSynchronizationSupport()
						.getRemoteObjects());;
				if (isOk == false) {
					result.putResult(Messages.getString("Restoration.7") //$NON-NLS-1$
							+ ((Component) obj).getInstanceNameL() + " : " //$NON-NLS-1$
							+ ((Component) obj).getPathId() + "]"); //$NON-NLS-1$
					result.setSuccess(false);
				}
			}
		}
	}
}
