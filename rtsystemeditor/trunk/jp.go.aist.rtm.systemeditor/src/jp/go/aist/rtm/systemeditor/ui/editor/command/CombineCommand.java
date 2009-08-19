package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

/**
 * �V�X�e���_�C�A�O�����ɕ���Rtc��ǉ�����R�}���h
 */
public class CombineCommand extends Command {
	private SystemDiagram parent;

	// ����RTC
	private Component target;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		// �����R���|�[�l���g�̎q�E�B���h�E�ɂĕ����R���|�[�l���g���쐬�����Ƃ��̏���
		adjustParentDiagram(target.getComponents());
		
		// �qRTC���_�C�A�O�����������
		parent.removeComponents(target.getComponents());
		
		// �qRTC�̃|�[�g�ɂȂ����Ă����ڑ�������
		removeConnections(target.getComponents());
		
		// �����R���|�[�l���g���_�C�A�O�����ɒǉ�����
		parent.addComponent(target);
	}

	@SuppressWarnings("unchecked")
	private void removeConnections(EList components) {
		for (Object o : components) {
			Component c = (Component)o;
			for (Object o2 :c.getPorts()) {
				Port p = (Port)o2;
				for (Object o3 :p.getConnectorProfiles()) {
					ConnectorProfile cp = (ConnectorProfile)o3;
					parent.getConnectorMap().remove(cp.getConnectorId());
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void adjustParentDiagram(List<Component> selectedComponents) {
		Component parentCompositeComponent = parent.getCompositeComponent();
		if (parentCompositeComponent == null) return;
		
		// �T�[�o�ɑ΂��āAset_members���Ăяo��
		parentCompositeComponent.setComponentsR(getTargets());
	}

	private List<Component> getTargets() {
		List<Component> targets = new ArrayList<Component>();
		for (Object o : parent.getComponents()) {
			if (target.getComponents().contains(o)) continue;
			targets.add((Component)o);
		}
		targets.add(target);
		return targets;
	}

	/**
	 * �e�ƂȂ�V�X�e���_�C�A�O������ݒ肷��
	 * 
	 * @param parent
	 *            �e�ƂȂ�V�X�e���_�C�A�O����
	 */
	public void setParent(SystemDiagram parent) {
		this.parent = parent;
	}

	/**
	 * �����R���|�[�l���g��ݒ肷��
	 * 
	 * @param target
	 *            �����R���|�[�l���g
	 */
	public void setTarget(Component target) {
		this.target = target;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
	}
}
