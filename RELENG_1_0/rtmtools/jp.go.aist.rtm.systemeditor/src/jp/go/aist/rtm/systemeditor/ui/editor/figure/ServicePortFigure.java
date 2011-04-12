package jp.go.aist.rtm.systemeditor.ui.editor.figure;

import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.PointList;

/**
 * ServicePort��Figure
 */
public class ServicePortFigure extends PortFigure {

	/**
	 * �R���X�g���N�^
	 * 
	 * @param servicePort
	 *            ���f��
	 */
	public ServicePortFigure(ServicePort servicePort) {
		setScale(1.0, 1.0);
		setFill(true);

		PointList pointList = new PointList(5);
		pointList.addPoint(-5, -5);
		pointList.addPoint(-5, 5);
		pointList.addPoint(5, 5);
		pointList.addPoint(5, -5);

		setTemplate(pointList);
		setSize(20 + 1, 20 + 1);

		setBackgroundColor(ColorConstants.lightBlue);
		setForegroundColor(ColorConstants.red);

		setToolTip(getServicePortToolTip(servicePort));

	}

}
