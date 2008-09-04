package jp.go.aist.rtm.systemeditor.restoration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.LifeCycleState;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentImpl;
import jp.go.aist.rtm.toolscommon.model.component.impl.ConfigurationSetImpl;
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

		processAllConnect(result, systemDiagram);

		//processAllStart(result, systemDiagram);
	}

	/**
	 * RtcLink��XML�Ɋ܂܂�邷�ׂẴR���t�B�O���[�V�����𕜌�����B
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	private static void processAllRestoreConfigurationSet(Result result,
			SystemDiagram systemDiagram) {
		
		List remoteConfigurationSets = new ArrayList();
		
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			if (obj instanceof Component) {
//				Component component = ((Component) obj);
				ComponentImpl component = ((ComponentImpl) obj);

				boolean isOk = false;
				try {
					Configuration configuration = component.getCorbaObjectInterface()
						.	get_configuration();
					_SDOPackage.ConfigurationSet[] remoteConfigurationSet = configuration
							.get_configuration_sets();	
					for (_SDOPackage.ConfigurationSet remote : remoteConfigurationSet) {
						ConfigurationSetImpl configSet = new ConfigurationSetImpl();
						configSet.setId(remote.id);
						configSet.setSDOConfigurationSetForRestore(remote);

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
					result.putResult("RTC�̃R���t�B�O���[�V�����̕����Ɏ��s���܂���:["
							+ ((Component) obj).getInstanceNameL() + " : "
							+ ((Component) obj).getPathId() + "]");
					result.setSuccess(false);
				}
			}
		}
	}

	/**
	 * RtcLink��XML�Ɋ܂܂�邷�ׂẴR�l�N�V������ڑ�����B
	 * <p>
	 * ���ɂ��Ȃ�ID�����݂��Ă���ꍇ�ɂ́A�ڑ����s��Ȃ�
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	private static void processAllConnect(Result result,
			SystemDiagram systemDiagram) {
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			if (obj instanceof PortConnector) {
				PortConnector connector = ((PortConnector) obj);
				boolean isOk = false;
				try {
					if (connector.getTarget().getCorbaObjectInterface()
							.get_connector_profile(
									connector.getConnectorProfile()
											.getConnectorId()).connector_id
							.equals(connector.getConnectorProfile()
									.getConnectorId()) == false
							|| connector.getSource().getCorbaObjectInterface()
									.get_connector_profile(
											connector.getConnectorProfile()
													.getConnectorId()).connector_id
									.equals(connector.getConnectorProfile()
											.getConnectorId()) == false) {
						isOk = connector.createConnectorR();
					} else {
						isOk = true;
					}
				} catch (Exception e) {
					result.setSuccess(false);
				}
				if (isOk == false) {
					result.putResult("�ڑ��Ɏ��s���܂����B:["
							+ ((Component) connector.getSource().eContainer())
									.getPathId()
							+ ":"
							+ connector.getSource().getPortProfile().getNameL()
							+ "�|�[�g �` "
							+ ((Component) connector.getTarget().eContainer())
									.getPathId() + ":"
							+ connector.getTarget().getPortProfile().getNameL()
							+ "�|�[�g]");
					result.setSuccess(false);
				}
			}
		}
	}

	/**
	 * RtcLink��XML�Ɋ܂܂�邷�ׂĂ�RTC�ɑ΂��āAStart�v���𑗐M���܂��B
	 * 
	 * @param result
	 * @param systemDiagram
	 */
	private static void processAllStart(Result result,
			SystemDiagram systemDiagram) {
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			if (obj instanceof Component) {
				for (Iterator iterator = ((Component) obj).getLifeCycleStates()
						.iterator(); iterator.hasNext();) {
					try {
						LifeCycleState state = (LifeCycleState) iterator.next();
						state.activateR();
					} catch (Exception e) {
						result.putResult("RTC��Activate�Ɏ��s���܂����B["
								+ ((Component) obj).getInstanceNameL() + " : "
								+ ((Component) obj).getPathId() + "]");
						result.setSuccess(false);
					}
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
	private static void processAllPing(Result result,
			SystemDiagram systemDiagram) {
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = (Object) iter.next();
			if (obj instanceof Component) {
				boolean isOk = false;
				try {
					if (((Component) obj).ping()) {
						isOk = true;
					}
				} catch (Exception e) {
					// void
				}
				if (isOk == false) {
					result.putResult("RTC�ɃA�N�Z�X�ł��܂���ł���:["
							+ ((Component) obj).getInstanceNameL() + " : "
							+ ((Component) obj).getPathId() + "]");
					result.setSuccess(false);
				}
			}
		}
	}
}
