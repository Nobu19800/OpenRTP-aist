/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import RTC.PortProfile;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

/**
 * <!-- begin-user-doc -->
 * Corba�R���|�[�l���g�̃|�[�g�p�̓����������`����N���X
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer#getRTCPortProfile <em>RTC Port Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaPortSynchronizer()
 * @model
 * @generated
 */
public interface CorbaPortSynchronizer extends CorbaWrapperObject, PortSynchronizer {
	/**
	 * Returns the value of the '<em><b>RTC Port Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RTC Port Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RTC Port Profile</em>' attribute.
	 * @see #setRTCPortProfile(PortProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getCorbaPortSynchronizer_RTCPortProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.RTCPortProfile"
	 * @generated
	 */
	PortProfile getRTCPortProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.CorbaPortSynchronizer#getRTCPortProfile <em>RTC Port Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RTC Port Profile</em>' attribute.
	 * @see #getRTCPortProfile()
	 * @generated
	 */
	void setRTCPortProfile(PortProfile value);

	RTC.PortService getCorbaObjectInterface();
} // CorbaPortSynchronizer
