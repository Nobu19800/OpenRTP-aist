package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import java.util.List;

import org.eclipse.emf.common.util.EList;


/**
 * Port��\������N���X
 * @model
 */
public interface Port extends WrapperObject {

	/**
	 * Returns the value of the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �|�[�g�����ʂ��邽�߂̕������Ԃ��B
	 * ���ۂ̏�����PortSynchronizer�ɈϏ������
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Port String</em>' attribute.
	 * @see #setOriginalPortString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_OriginalPortString()
	 * @model
	 * @generated
	 */
	String getOriginalPortString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getOriginalPortString <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �|�[�g�����ʂ��邽�߂̕������ݒ肷��B
	 * ���ۂ̏�����PortSynchronizer�ɈϏ������
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Port String</em>' attribute.
	 * @see #getOriginalPortString()
	 * @generated
	 */
	void setOriginalPortString(String value);

	/**
	 * Returns the value of the '<em><b>Synchronizer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ���������p��PortSynchronizer���擾����
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronizer</em>' containment reference.
	 * @see #setSynchronizer(PortSynchronizer)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_Synchronizer()
	 * @model containment="true"
	 * @generated
	 */
	PortSynchronizer getSynchronizer();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSynchronizer <em>Synchronizer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * ���������p��PortSynchronizer��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronizer</em>' containment reference.
	 * @see #getSynchronizer()
	 * @generated
	 */
	void setSynchronizer(PortSynchronizer value);

	/**
	 * Returns the value of the '<em><b>Name L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �|�[�g�̖��̂�Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name L</em>' attribute.
	 * @see #setNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_NameL()
	 * @model
	 * @generated
	 */
	String getNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getNameL <em>Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �|�[�g�̖��̂�ݒ肷��
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name L</em>' attribute.
	 * @see #getNameL()
	 * @generated
	 */
	void setNameL(String value);

	/**
	 * Returns the value of the '<em><b>Allow Any Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �J���}��؂�Ŏw�肳���|�[�g�̃f�[�^�^�̒���Any�����݂��邩��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Data Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyDataType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyDataType();

	/**
	 * Returns the value of the '<em><b>Allow Any Interface Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �J���}��؂�Ŏw�肳���|�[�g�̃C���^�[�t�F�[�X�^�̒���Any�����݂��邩��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Interface Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyInterfaceType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyInterfaceType();

	/**
	 * Returns the value of the '<em><b>Allow Any Dataflow Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �J���}��؂�Ŏw�肳���|�[�g�̃f�[�^�t���[�^�̒���Any�����݂��邩��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Dataflow Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnyDataflowType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnyDataflowType();

	/**
	 * Returns the value of the '<em><b>Allow Any Subscription Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �J���}��؂�Ŏw�肳���|�[�g�̃T�u�X�N���v�V�����^�̒���Any�����݂��邩��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Any Subscription Type</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_AllowAnySubscriptionType()
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	boolean isAllowAnySubscriptionType();

	/**
	 * Returns the value of the '<em><b>Connector Profiles</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �|�[�g���ێ�����R�l�N�^�v���t�@�C���̃��X�g��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Profiles</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_ConnectorProfiles()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile" transient="true"
	 * @generated
	 */
	EList getConnectorProfiles();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' attribute list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile}.
	 * <!-- begin-user-doc -->
	 * <p>
	 *  �T�[�r�X�|�[�g���ێ�����C���^�[�t�F�[�X�̃��X�g��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' attribute list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPort_Interfaces()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile" dataType="jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile"
	 * @generated
	 */
	EList getInterfaces();

	/**
	 * �J���}��؂�Ŏw�肳���|�[�g�̃f�[�^�t���[�^���f�[�^�t���[�^�̃��X�g�ɂ��ĕԂ�
	 */
	public List<String> getDataflowTypes();

	String getDataflowType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getDataflowType <em>Dataflow Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dataflow Type</em>' attribute.
	 * @see #getDataflowType()
	 * @generated
	 */
	void setDataflowType(String value);

	/**
	 *  �J���}��؂�Ŏw�肳���|�[�g�̃f�[�^�^���f�[�^�^�̃��X�g�ɂ��ĕԂ�
	 */
	public List<String> getDataTypes();

	String getDataType();

	void setDataType(String type);

	/**
	 * �J���}��؂�Ŏw�肳���|�[�g�̃C���^�[�t�F�[�X�^���C���^�[�t�F�[�X�^�̃��X�g�ɂ��ĕԂ�
	 */
	public List<String> getInterfaceTypes();

	String getInterfaceType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getInterfaceType <em>Interface Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface Type</em>' attribute.
	 * @see #getInterfaceType()
	 * @generated
	 */
	void setInterfaceType(String value);

	/**
	 * �J���}��؂�Ŏw�肳���|�[�g�̃T�u�X�N���v�V�����^���T�u�X�N���v�V�����^�̃��X�g�ɂ��ĕԂ�
	 */
	public List<String> getSubscriptionTypes();

	String getSubscriptionType();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Port#getSubscriptionType <em>Subscription Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subscription Type</em>' attribute.
	 * @see #getSubscriptionType()
	 * @generated
	 */
	void setSubscriptionType(String value);

	/**
	 * <!-- begin-user-doc -->
	 * ���Y�|�[�g��̐ڑ������ׂĐؒf����B
	 * ���ۂ̏�����PortSynchronizer�ɈϏ������
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void disconnectAll();

	/**
	 * <!-- begin-user-doc -->
	 * ���Y�|�[�g�������郋�[�g�̃V�X�e���_�C�A�O��������|�[�g���ʗp�̕�����iOriginalPortString)�ƈ�v����
	 * �|�[�g��Ԃ��B���̂Ƃ��A����RTC�ł͂Ȃ��R���|�[�l���g�̃|�[�g���Ԃ����B
	 * <!-- end-user-doc -->
	 * @model originalPortStringUnique="false"
	 * @generated
	 */
	Port findPort(SystemDiagram diagram, String originalPortString);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validateTargetConnector(Port target);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean validateSourceConnector(Port source);

	/**
	 * @return	�v���L�V�ƂȂ�|�[�g
	 */
	Port proxy();

	/**
	 * @return DataType/InterfaceType/DataflowType/SUBSCRIPTION_TYPE�ȊO�̃v���p�e�B
	 */
	List<NameValue> getProperties();

	/**
	 * @param name	�v���p�e�B��
	 * @return		�v���p�e�B�̒l
	 */
	String getProperty(String name);
}
