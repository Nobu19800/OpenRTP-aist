package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

/**
 * ConnectorProfile��\������N���X
 * <p>
 * 
 * ���̃I�u�W�F�N�g�́A�o�����[�I�u�W�F�N�g�ł��邱�Ƃɒ��ӂ��邱�ƁB<br>
 * ���̃I�u�W�F�N�g���͓̂������s���Ȃ����߁A���̃I�u�W�F�N�g�̎Q�Ƃ�ێ��������邱�Ƃ́A�댯�ł���B<br>
 * �����������A�Q�ƌ��̃I�u�W�F�N�g���Q�Ƃ��āA�K�v�ɂȂ邽�тɂ��������ɓ���邱�ƁB
 * 
 * @model
 */
public interface ConnectorProfile extends WrapperObject{

	public static final String ANY = "Any";

	public static final String PERIODIC = "Periodic";

	public static final String PUSH = "Push";

	/**
	 * ���Y�R�l�N�^�Ŏg�p�����f�[�^�t���[�^��Ԃ��B
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataflowType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataflowType <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * ���Y�R�l�N�^�Ŏg�p�����f�[�^�t���[�^��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dataflow Type</em>' attribute.
	 * @see #getDataflowType()
	 * @generated
	 */
	void setDataflowType(String value);

	/**
	 * ���Y�R�l�N�^�Ŏg�p�����T�u�X�N���v�V�����^��Ԃ�
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getSubscriptionType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSubscriptionType <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * ���Y�R�l�N�^�Ŏg�p�����T�u�X�N���v�V�����^��ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Type</em>' attribute.
	 * @see #getSubscriptionType()
	 * @generated
	 */
	void setSubscriptionType(String value);

	/**
	 * �T�u�X�N���v�V�����^���g�p�\���i�f�[�^�t���[�^��PUSH�ł��邩�j��Ԃ�
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isSubscriptionTypeAvailable();

	/**
	 * PUSH�Ԋu���g�p�\���i�T�u�X�N���v�V�����^���g�p�\�ŃT�u�X�N���v�V�����^��PERIODIC�j��Ԃ�
	 * @model changeable="false" transient="true" volatile="true"
	 * @return
	 */
	public boolean isPushIntervalAvailable();

	/**
	 * Outport����Inport�ɗ����f�[�^�̌^��Ԃ�
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getDataType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * Outport����Inport�ɗ����f�[�^�̌^��ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 *  ���Y�R�l�N�^�Ŏg�p�����C���^�[�t�F�[�X�^��Ԃ�
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public String getInterfaceType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getInterfaceType <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * ���Y�R�l�N�^�Ŏg�p�����C���^�[�t�F�[�X�^��ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Type</em>' attribute.
	 * @see #getInterfaceType()
	 * @generated
	 */
	void setInterfaceType(String value);

	/**
	 * ���Y�R�l�N�^�Ŏg�p�����f�[�^���M������Ԃ�
	 * @model changeable="true" transient="true" volatile="true"
	 * @return
	 */
	public Double getPushRate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getPushRate <em>Push Rate</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * ���Y�R�l�N�^�Ŏg�p�����f�[�^���M������ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Push Rate</em>' attribute.
	 * @see #getPushRate()
	 * @generated
	 */
	void setPushRate(Double value);

	/**
	 * Returns the value of the '<em><b>Source String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R�l�N�^�̐ڑ����ł���|�[�g�̎��ʕ������Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source String</em>' attribute.
	 * @see #setSourceString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_SourceString()
	 * @model
	 * @generated
	 */
	String getSourceString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getSourceString <em>Source String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R�l�N�^�̐ڑ����ł���|�[�g�̎��ʕ������ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source String</em>' attribute.
	 * @see #getSourceString()
	 * @generated
	 */
	void setSourceString(String value);

	/**
	 * Returns the value of the '<em><b>Target String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R�l�N�^�̐ڑ���ł���|�[�g�̎��ʕ������Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target String</em>' attribute.
	 * @see #setTargetString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getConnectorProfile_TargetString()
	 * @model
	 * @generated
	 */
	String getTargetString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getTargetString <em>Target String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R�l�N�^�̐ڑ���ł���|�[�g�̎��ʕ������ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target String</em>' attribute.
	 * @see #getTargetString()
	 * @generated
	 */
	void setTargetString(String value);

	/**
	 * @model
	 * @return	�ڑ����̖���
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> 
	 * �ڑ����̖��̂�ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model
	 * @return�@�ڑ����̈�ӎ��ʎq
	 */
	public String getConnectorId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile#getConnectorId <em>Connector Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �ڑ����̈�ӎ��ʎq��ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Id</em>' attribute.
	 * @see #getConnectorId()
	 * @generated
	 */
	void setConnectorId(String value);

}
