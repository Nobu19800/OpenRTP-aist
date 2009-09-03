package jp.go.aist.rtm.systemeditor.ui.editor.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.util.ComponentUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;

import org.eclipse.gef.commands.Command;

/**
 * �V�X�e���_�C�A�O������Rtc��ǉ�����R�}���h
 */
public class CreateCommand extends Command {
	private SystemDiagram parent;

	private Component target;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		parent.addComponent(target);
		setComponentsConstraint(target);
		ComponentUtil.findEditor(parent).refresh();
		Component compositeComponent = parent.getCompositeComponent();
		if (compositeComponent != null) {
			// ����RTC���̃G�f�B�^�̏ꍇ
			// �����R���|�[�l���g�Ɏq��ǉ�
			List<Component> list = new ArrayList<Component>();
			list.add(target);
			compositeComponent.addComponentsR(list);
		}
	}

	@SuppressWarnings("unchecked")
	private void setComponentsConstraint(Component component) {
		List<Integer> counts = new ArrayList<Integer>();
		int count = 0;
		counts.add(count);
		Integer temp = counts.get(counts.size() - 1);
		for (Iterator iterator = component.getAllComponents().iterator(); iterator
				.hasNext();) {
			Component tempComponent = (Component) iterator.next();
			if (tempComponent.getConstraint() == null) {
				tempComponent.setConstraint(ComponentUtil
						.getNewComponentConstraint(component.getConstraint(), temp));
				temp++;
				counts.set(counts.size() - 1, temp);
			}
		}
		counts.remove(counts.size() - 1);
		if (counts.size() > 1) {
			temp = counts.get(counts.size() - 1);
		}		
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
	 * �쐬�Ώۂ�Rtc��ݒ肷��
	 * 
	 * @param target
	 *            �쐬�Ώۂ�Rtc
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
