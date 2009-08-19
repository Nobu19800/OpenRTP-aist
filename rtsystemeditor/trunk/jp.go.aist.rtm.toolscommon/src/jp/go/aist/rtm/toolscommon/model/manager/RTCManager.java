/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.manager;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

import org.eclipse.emf.common.util.EList;

import RTM.ManagerProfile;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RTC Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile <em>Manager Profile</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId <em>Path Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager()
 * @model
 * @generated
 */
public interface RTCManager extends CorbaWrapperObject {
	/**
	 * Returns the value of the '<em><b>Manager Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager Profile</em>' attribute.
	 * @see #setManagerProfile(ManagerProfile)
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_ManagerProfile()
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMManagerProfile" transient="true"
	 * @generated
	 */
	ManagerProfile getManagerProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getManagerProfile <em>Manager Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager Profile</em>' attribute.
	 * @see #getManagerProfile()
	 * @generated
	 */
	void setManagerProfile(ManagerProfile value);

	/**
	 * Returns the value of the '<em><b>Instance Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Name L</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Name L</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_InstanceNameL()
	 * @model changeable="false"
	 * @generated
	 */
	String getInstanceNameL();

	/**
	 * Returns the value of the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Id</em>' attribute.
	 * @see #setPathId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage#getRTCManager_PathId()
	 * @model
	 * @generated
	 */
	String getPathId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.manager.RTCManager#getPathId <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Id</em>' attribute.
	 * @see #getPathId()
	 * @generated
	 */
	void setPathId(String value);

	/**
	 * <!-- begin-user-doc -->
	 * RT�R���|�[�l���g��V�K�ɍ쐬����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Component createComponentR(String compName);

	/**
	 * <!-- begin-user-doc -->
	 * ���݂͖��g�p
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int deleteComponentR(String instanceName);

	/**
	 * <!-- begin-user-doc -->
	 * ���݂͖��g�p
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getComponentsR();

	/**
	 * <!-- begin-user-doc -->
	 * ���݂͖��g�p
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getComponentProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * ���W���[�������[�h����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int loadModuleR(String pathname, String initfunc);

	/**
	 * <!-- begin-user-doc -->
	 * ���W���[�����A�����[�h����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int unloadModuleR(String pathname);

	/**
	 * <!-- begin-user-doc -->
	 * LoadableModule�̃v���t�@�C���ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getLoadableModuleProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * LoadedModule�̃v���t�@�C���ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getLoadedModuleProfilesR();

	/**
	 * <!-- begin-user-doc -->
	 * RT�R���|�[�l���g���t�H�[�N����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int forkR();

	/**
	 * <!-- begin-user-doc -->
	 * �}�l�[�W�����V���b�g�_�E������
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int shutdownR();

	/**
	 * <!-- begin-user-doc -->
	 * �Ǘ�����RTC�̈ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getComponentInstanceNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * LoadableModule�̃t�@�C�����ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getLoadableModuleFileNameR();

	/**
	 * <!-- begin-user-doc -->
	 * LoadedModule�̃t�@�C�����ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getLoadedModuleFileNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * �t�@�N�g���̈ꗗ��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getFactoryProfileTypeNamesR();

	/**
	 * <!-- begin-user-doc -->
	 * ManagerProfile��Ԃ�
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.manager.RTMManagerProfile"
	 * @generated
	 */
	ManagerProfile getProfileR();

	/**
	 * �������蓮�Ŏ��s����
	 */
	void synchronizeManually();

} // RTCManager