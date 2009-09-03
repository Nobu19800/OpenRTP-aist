package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * �|�[�g�A���J�[�N���X
 */
public class PortAnchor extends ChopboxAnchor {
	/**
	 * �R���X�g���N�^
	 * 
	 * @param figure
	 *            �A���J�[�Ώۂ̃|�[�gFigure
	 */
	public PortAnchor(PortFigure figure) {
		super(figure);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Point getLocation(Point reference) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBox());
		r.translate(-1, -1);
		r.resize(1, 1);

		getOwner().translateToAbsolute(r);
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;

		return new Point(centerX, centerY);
	}

	/**
	 * �������擾����
	 * 
	 * @return ����
	 */
	public String getDirection() {
		return getOwner().getDirection();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public PortFigure getOwner() {
		return (PortFigure) super.getOwner();
	}

}
