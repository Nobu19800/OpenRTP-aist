package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import org.eclipse.emf.common.util.EMap;

/**
 * �|�[�g�Ԑڑ���\������N���X
 * 
 * @model
 */
public interface PortConnector extends WrapperObject {

	/**
	 * �ڑ�����Ԃ�
	 * @model containment="true"
	 */
	public ConnectorProfile getConnectorProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getConnectorProfile <em>Connector Profile</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Profile</em>' containment reference.
	 * @see #getConnectorProfile()
	 * @generated
	 */
	void setConnectorProfile(ConnectorProfile value);

	/**
	 * Returns the value of the '<em><b>Routing Constraint</b></em>' map.
	 * The key is of type {@link java.lang.Integer},
	 * and the value is of type {@link jp.go.aist.rtm.toolscommon.model.core.Point},
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R�l�N�^�̃x���h�|�C���g�̏W�����}�b�v�ŕԂ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routing Constraint</em>' map.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_RoutingConstraint()
	 * @model mapType="jp.go.aist.rtm.toolscommon.model.component.EIntegerObjectToPointMapEntry" keyType="java.lang.Integer" valueType="jp.go.aist.rtm.toolscommon.model.core.Point"
	 * @generated
	 */
	EMap getRoutingConstraint();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R�l�N�^�̐ڑ����ł���|�[�g��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Port)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_Source()
	 * @model transient="true"
	 * @generated
	 */
	Port getSource();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Port value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R�l�N�^�̐ڑ���ł���|�[�g��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Port)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortConnector_Target()
	 * @model
	 * @generated
	 */
	Port getTarget();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortConnector#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Port value);

	/**
	 * <!-- begin-user-doc -->
	 * �R�l�N�^���쐬���郁�b�Z�[�W�������[�g�ɑ΂��đ��M����
	 * �I�t���C���̏ꍇ�́A�ΏۂƂȂ�|�[�g�ɃR�l�N�^�v���t�@�C����ǉ�����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean createConnectorR();

	/**
	 * <!-- begin-user-doc -->
	 * �R�l�N�^���폜���郁�b�Z�[�W�������[�g�ɑ΂��đ��M����
	 * �I�t���C���̏ꍇ�́A�ΏۂƂȂ�|�[�g����R�l�N�^�v���t�@�C�����폜����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean deleteConnectorR();

}
