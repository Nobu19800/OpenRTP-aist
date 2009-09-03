package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.Port;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;

/**
 * Port��Figure
 */
public class PortFigure extends PolygonDecoration {

	private String direction;

	/**
	 * ������ݒ肷��
	 * 
	 * @param direction
	 *            ����
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		setRotation(getRotation(direction));
	}

	private double getRotation(String direction) {
		if (direction.equals(Component.OUTPORT_DIRECTION_RIGHT_LITERAL)) {
			return 0;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_LEFT_LITERAL)) {
			return Math.PI;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_UP_LITERAL)) {
			return Math.PI * 3 / 2;
		} else if (direction.equals(Component.OUTPORT_DIRECTION_DOWN_LITERAL)) {
			return Math.PI / 2;
		}
		return 0;
	}

	/**
	 * �������擾����
	 * 
	 * @return ����
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * �f�[�^�|�[�g�̃c�[���`�b�v���擾����
	 * 
	 * @param profile
	 *            ���f��
	 * @return �c�[���`�b�v
	 */
	public static Panel getServicePortToolTip(Port port) {
		Panel tooltip = new Panel();
		tooltip.setLayoutManager(new StackLayout());

		String labelString = "";
		try {
			labelString = labelString
					+ (port.getNameL() == null ? "<unknown>" : port
							.getNameL()) + ""; // \r\n�͍Ō�͂���Ȃ�
		} catch (RuntimeException e) {
			// void
		}

		Label label1 = new Label(labelString);
		tooltip.add(label1);
		return tooltip;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void setLocation(Point p) {
		super.setLocation(p);
		fireFigureMoved();
	}

}
