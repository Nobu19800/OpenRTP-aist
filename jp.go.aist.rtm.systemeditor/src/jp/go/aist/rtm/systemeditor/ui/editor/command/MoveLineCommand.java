package jp.go.aist.rtm.systemeditor.ui.editor.command;

import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.PortConnector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gef.commands.Command;

/**
 * �R�l�N�^�̃��C�����ړ�����R�}���h
 */
public class MoveLineCommand extends Command {
	private PortConnector model;

	private int index;

	private Point point;

	private Point oldpoint;

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		this.oldpoint = getPoint();
		setPoint(point);
	}

	@SuppressWarnings("unchecked")
	private void setPoint(Point p) {
		EMap routingConstraint = model.getRoutingConstraint();
		if (p == null) {
			routingConstraint.remove(new Integer(index));
		} else {
			// routingConstraint�ɂ́A���ɑ��݂���key��Put���s�����ꍇ�ɁA�ύX�̒ʒm���s���Ȃ��o�O������B
			// ���̂��߁A�ȉ��̕��@�ŕύX���s���B
			routingConstraint.put(new Integer(index), Draw2dUtil
					.toRtcLinkPoint(p));

			routingConstraint.removeKey(new Integer(Integer.MAX_VALUE));
			routingConstraint.put(new Integer(Integer.MAX_VALUE), null);
			routingConstraint.removeKey(new Integer(Integer.MAX_VALUE));
		}
	}

	@SuppressWarnings("unchecked")
	private Point getPoint() {
		Point result = null;
		if (model.getRoutingConstraint() instanceof EMap) {
			result = Draw2dUtil
					.toDraw2dPoint((jp.go.aist.rtm.toolscommon.model.core.Point) ((EMap) model
							.getRoutingConstraint()).get(new Integer(index)));
		}

		return result;
	}

	/**
	 * �ύX�Ώۂ̃��f����ݒ肷��
	 * 
	 * @param model
	 *            �ύX�Ώۂ̃��f��
	 */
	public void setModel(PortConnector model) {
		this.model = model;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void undo() {
		setPoint(oldpoint);
		oldpoint = null;
	}

	/**
	 * �C���f�b�N�X��ݒ肷��
	 * @param index �C���f�b�N�X
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * ���P�[�V������ݒ肷��
	 * @param point ���P�[�V����
	 */
	public void setLocation(Point point) {
		this.point = point;
	}
}
