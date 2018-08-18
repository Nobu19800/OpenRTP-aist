package jp.go.aist.rtm.rtcbuilder.ui.figure;

import jp.go.aist.rtm.rtcbuilder.model.component.ServicePort;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

public class ServicePortBaseFigure extends PortFigureBase {

	private ServicePortFigure servicePortFig;

	public ServicePortBaseFigure(ServicePort servicePort, int direction) {
		servicePortFig = new ServicePortFigure(servicePort, direction,
				new Color(PlatformUI.getWorkbench().getDisplay(), RTCUtil.defaultRGBMap.get(RTCUtil.COLOR_SERVICEPORT)));
		setLayoutManager(new XYLayout());
		add(servicePortFig);
	}
	
	public ServicePortFigure getInnerFigure() {
		return this.servicePortFig;
	}

	public void setInnerFigure(ServicePortFigure figure) {
		this.servicePortFig = figure;
	}
}
