package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.PortProfile;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;

/**
 * Port��Figure
 */
public class PortFigure extends PolygonDecoration {

	private int direction;

	/**
	 * ������ݒ肷��
	 * 
	 * @param direction
	 *            ����
	 */
	public void setDirection(int direction) {

		double rotation = 0;
		if (direction == Component.RIGHT_DIRECTION) {
			rotation = 0;
		} else if (direction == Component.LEFT_DIRECTION) {
			rotation = Math.PI;
		} else if (direction == Component.UP_DIRECTION) {
			rotation = Math.PI * 3 / 2;
		} else if (direction == Component.DOWN_DIRECTION) {
			rotation = Math.PI / 2;
		}

		this.direction = direction;
		this.setRotation(rotation);
	}

	/**
	 * �������擾����
	 * 
	 * @return ����
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * �f�[�^�|�[�g�̃c�[���`�b�v���擾����
	 * 
	 * @param profile
	 *            ���f��
	 * @return �c�[���`�b�v
	 */
	public static Panel getServicePortToolTip(PortProfile profile) {

		Panel tooltip = new Panel();
		tooltip.setLayoutManager(new StackLayout());

		String labelString = "";
		try {
			if (profile != null) {
				labelString = labelString
						+ (profile.getNameL() == null ? "<unknown>" : profile
								.getNameL()) + ""; // \r\n�͍Ō�͂���Ȃ�
			}
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
