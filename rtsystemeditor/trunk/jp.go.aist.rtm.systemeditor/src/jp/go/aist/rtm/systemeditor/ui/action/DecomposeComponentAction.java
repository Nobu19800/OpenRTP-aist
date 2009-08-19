package jp.go.aist.rtm.systemeditor.ui.action;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrappedJob;
import jp.go.aist.rtm.systemeditor.ui.util.TimeoutWrapper;
import jp.go.aist.rtm.toolscommon.manager.ToolsCommonPreferenceManager;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.CorbaComponent;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.jface.action.Action;

/**
 * �����R���|�[�l���g����������A�N�V����
 *
 */
public class DecomposeComponentAction extends Action {

	private Component target;
	private SystemDiagram parent;

	public void setTarget(Component compositeComponent) {
		target = compositeComponent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		if (!CompositeComponentHelper.openConfirm(target, Messages.getString("DecomposeComponentAction.0"))) //$NON-NLS-1$
			return;
		ComponentUtil.closeCompositeComponent(target);
		
		// �q�R���|�[�l���g���_�C�A�O�����ɒǉ�����
		for (Object o : target.getComponents()) {
			Component c = (Component)o;
			parent.addComponent(c);
		}
		
		// �����R���|�[�l���g���_�C�A�O�����������
		parent.removeComponent(target);
		
		// �����R���|�[�l���g�ɂȂ����Ă����ڑ�������
		removeConnections();
		
		// �l�X�g���Ă���ꍇ�̓����o�[�̍Đݒ肪�K�v
		if (parent.getCompositeComponent() != null) {
			parent.getCompositeComponent().setComponentsR(parent.getComponents());
		}
		
		// �I�����C���̕����R���|�[�l���g�́Aexit����
		if (target instanceof CorbaComponent) {
//			((CorbaComponent) target).exitR();
			int defaultTimeout = ToolsCommonPreferenceManager.getInstance().getDefaultTimeout(
					ToolsCommonPreferenceManager.DEFAULT_TIMEOUT_PERIOD);
			TimeoutWrapper wrapper = new TimeoutWrapper(defaultTimeout);
			wrapper.setJob(new TimeoutWrappedJob(){
				@Override
				protected Object executeCommand() {
					return ((CorbaComponent) target).exitR();
				}});
			wrapper.start();
		}
	}

	private void removeConnections() {
		for (Object o2 : target.getPorts()) {
			Port p = (Port)o2;
			for (Object o3 :p.getConnectorProfiles()) {
				ConnectorProfile cp = (ConnectorProfile)o3;
				parent.getConnectorMap().remove(cp.getConnectorId());
			}
		}
	}

	public void setParent(SystemDiagram diagram) {
		parent = diagram;
	}

}
