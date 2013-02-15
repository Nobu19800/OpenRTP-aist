package jp.go.aist.rtm.toolscommon.model.core;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;


/**
 * RTCLinkでの、
 * @model
 */
public interface ModelElement extends EObject, IAdaptable {

	/**
	 * @model
	 * @param visiter
	 */
	public void accept(Visiter visiter);

	/**
	 * @model
	 * @return
	 */
	public Rectangle getConstraint();

	public void setConstraint(Rectangle rectangle);

}