package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.InPort;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * InPort��Figure
 */
public class InPortFigure extends PortFigure {

	/**
	 * �R���X�g���N�^
	 * 
	 * @param inport
	 *            ���f��
	 */
	public InPortFigure(InPort inport) {
		setScale(1.0, 1.0);
		setFill(true);

		PointList pointList = new PointList(5);
		pointList.addPoint(-5, -5);
		pointList.addPoint(-5, 5);
		pointList.addPoint(5, 5);
		pointList.addPoint(0, 0);
		pointList.addPoint(5, -5);
		
		setTemplate(pointList);
		setSize(20 + 1, 20 + 1);

		setBackgroundColor(ColorConstants.darkBlue);
		setForegroundColor(ColorConstants.red);

	}

}
