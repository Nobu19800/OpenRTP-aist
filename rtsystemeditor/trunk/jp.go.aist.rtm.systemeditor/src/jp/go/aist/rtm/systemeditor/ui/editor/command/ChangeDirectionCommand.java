package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;

import org.eclipse.gef.commands.Command;

/**
 * ������ύX����R�}���h
 */
public class ChangeDirectionCommand extends Command {

	private AbstractComponent model;

	private int direction;

	private int oldDirection;

	private ClearLineConstraintCommand clearLineConstraintCommand = new ClearLineConstraintCommand();

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		model.setOutportDirection(direction);

		clearLineConstraintCommand.setModel(model);
		clearLineConstraintCommand.execute();
	}

	/**
	 * ������ݒ肷��
	 * 
	 * @param direction
	 *            ����
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * �ύX�Ώۂ̃��f����ݒ肷��
	 * 
	 * @param model
	 *            �ύX�Ώۂ̃��f��
	 */
	public void setModel(AbstractComponent model) {
		this.model = model;
		this.oldDirection = model.getOutportDirection();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		clearLineConstraintCommand.undo();
		model.setOutportDirection(oldDirection);
	}
}
