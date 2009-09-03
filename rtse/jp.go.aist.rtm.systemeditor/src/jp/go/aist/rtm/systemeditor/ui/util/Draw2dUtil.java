package jp.go.aist.rtm.systemeditor.ui.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * ���[�e�B���e�B�N���X
 */
public class Draw2dUtil {
	public static Rectangle toDraw2dRectangle(
			jp.go.aist.rtm.toolscommon.model.core.Rectangle constraint) {
		return new Rectangle(constraint.getX(), constraint.getY(), constraint
				.getWidth(), constraint.getHeight());
	}

	/**
	 * Draw2D��Rectangle���ARtcLink���f����Rectangle�ɕϊ�����
	 * 
	 * @param constraint
	 *            Draw2D��Rectangle
	 * @return RtcLink���f����Rectangle
	 */
	public static jp.go.aist.rtm.toolscommon.model.core.Rectangle toRtcLinkRectangle(
			Rectangle constraint) {
		jp.go.aist.rtm.toolscommon.model.core.Rectangle result = new jp.go.aist.rtm.toolscommon.model.core.Rectangle();
		result.setX(constraint.x);
		result.setY(constraint.y);
		result.setWidth(constraint.width);
		result.setHeight(constraint.height);

		return result;
	}

	/**
	 * RtcLink���f����Rectangle���ADraw2D��Rectangle�ɕϊ�����
	 * 
	 * @param constraint
	 *            RtcLink���f����Rectangle
	 * @return Draw2D��Rectangle
	 */
	public static Map toRtcLinkPointMap(Map draw2dPointMap) {
		Map result = new HashMap();
		for (Iterator iter = draw2dPointMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			result.put(entry.getKey(), Draw2dUtil.toRtcLinkPoint(((Point) entry
					.getValue())));
		}

		return result;
	}

	/**
	 * RtcLink���f����Point�̃}�b�v���ADraw2D��Point�̃}�b�v�ɕϊ�����
	 * 
	 * @param rtcLinkPointMap
	 *            RtcLink���f����Rectangle
	 * @return Draw2D��Rectangle
	 */
	public static Map toDraw2dPointMap(Map rtcLinkPointMap) {
		Map result = new TreeMap();
		for (Iterator iter = rtcLinkPointMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			result
					.put(
							entry.getKey(),
							Draw2dUtil
									.toDraw2dPoint(((jp.go.aist.rtm.toolscommon.model.core.Point) entry
											.getValue())));
		}

		return result;
	}

	/**
	 * Draw2D��Point���ARtcLink���f����Point�ɕϊ�����
	 * 
	 * @param constraint
	 *            Draw2D��Point
	 * @return RtcLink���f����Point
	 */
	public static jp.go.aist.rtm.toolscommon.model.core.Point toRtcLinkPoint(
			Point draw2dPoint) {
		jp.go.aist.rtm.toolscommon.model.core.Point result = null;
		if (draw2dPoint != null) {
			result = new jp.go.aist.rtm.toolscommon.model.core.Point();
			result.setX(draw2dPoint.x);
			result.setY(draw2dPoint.y);
		}

		return result;
	}

	/**
	 * RtcLink���f����Point���ADraw2D��Point�ɕϊ�����
	 * 
	 * @param constraint
	 *            RtcLink���f����Point
	 * @return Draw2D��Point
	 */
	public static Point toDraw2dPoint(
			jp.go.aist.rtm.toolscommon.model.core.Point rtcLinkPoint) {
		Point result = null;
		if (rtcLinkPoint != null) {
			result = new Point(rtcLinkPoint.getX(), rtcLinkPoint.getY());
		}

		return result;
	}
}
