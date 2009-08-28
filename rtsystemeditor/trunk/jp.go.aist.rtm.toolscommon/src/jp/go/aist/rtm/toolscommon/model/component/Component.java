/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getConfigurationSets <em>Configuration Sets</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet <em>Active Configuration Set</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPorts <em>Ports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInports <em>Inports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutports <em>Outports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getServiceports <em>Serviceports</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponents <em>Components</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getExecutionContexts <em>Execution Contexts</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram <em>Child System Diagram</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL <em>Instance Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL <em>Vender L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL <em>Description L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL <em>Category L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL <em>Type Name L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL <em>Version L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPathId <em>Path Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getOutportDirection <em>Outport Direction</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCompositeTypeL <em>Composite Type L</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId <em>Component Id</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.Component#isRequired <em>Required</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent()
 * @model abstract="true"
 * @generated
 */
public interface Component extends WrapperObject {
	public static final String CATEGORY = "CATEGORY";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String PATH_URI = "PATH_URI";
	public static final String INSTANCE_NAME = "INSTANCE_NAME";
	public static final String STATE = "STATE";
	public static final String TYPE_NAME = "TYPE_NAME";
	public static final String VENDER = "VENDER";
	public static final String VERSION = "VERSION";
	
	public static final int CHANGE_HORIZON_DIRECTION = 1;
	public static final int CHANGE_VERTICAL_DIRECTION = 2;
	
	public static final String OUTPORT_DIRECTION_RIGHT_LITERAL = "RIGHT";
	public static final String OUTPORT_DIRECTION_LEFT_LITERAL = "LEFT";
	public static final String OUTPORT_DIRECTION_UP_LITERAL = "UP";
	public static final String OUTPORT_DIRECTION_DOWN_LITERAL = "DOWN";

	public static final String COMPOSITETYPE_NONE = "None";
	public static final String COMPOSITETYPE_PERIODIC_EC_SHARED = "PeriodicECShared";
	public static final String COMPOSITETYPE_PERIODIC_STATE_SHARED = "PeriodicStateShared";
	public static final String COMPOSITETYPE_GROUPING = "Grouping";
	
	/**
	 * Returns the value of the '<em><b>Configuration Sets</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ���|�[�l���g���ێ�����ConfigurationSet�̃��X�g���擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Sets</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ConfigurationSets()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet" containment="true"
	 * @generated
	 */
	EList getConfigurationSets();

	/**
	 * Returns the value of the '<em><b>Active Configuration Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �A�N�e�B�u��ConfigurationSet���擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Configuration Set</em>' reference.
	 * @see #setActiveConfigurationSet(ConfigurationSet)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ActiveConfigurationSet()
	 * @model
	 * @generated
	 */
	ConfigurationSet getActiveConfigurationSet();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getActiveConfigurationSet <em>Active Configuration Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * �A�N�e�B�u��ConfigurationSet��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Configuration Set</em>' reference.
	 * @see #getActiveConfigurationSet()
	 * @generated
	 */
	void setActiveConfigurationSet(ConfigurationSet value);

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.Port}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g���ێ�����Port�̃��X�g���擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Ports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Port" containment="true"
	 * @generated
	 */
	EList getPorts();

	/**
	 * Returns the value of the '<em><b>Inports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.InPort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g���ێ�����InPort�̃��X�g���擾����B(getPorts()�̃T�u�Z�b�g���Ԃ����)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Inports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.InPort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getInports();

	/**
	 * Returns the value of the '<em><b>Outports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.OutPort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g���ێ�����OutPort�̃��X�g���擾����B(getPorts()�̃T�u�Z�b�g���Ԃ����)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Outports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.OutPort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getOutports();

	/**
	 * Returns the value of the '<em><b>Serviceports</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ServicePort}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g���ێ�����ServicePort�̃��X�g���擾����B(getPorts()�̃T�u�Z�b�g���Ԃ����)
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Serviceports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Serviceports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ServicePort" transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	EList getServiceports();

	/**
	 * Returns the value of the '<em><b>Execution Contexts</b></em>' containment reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Contexts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Contexts</em>' containment reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ExecutionContexts()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ExecutionContext" containment="true"
	 * @generated
	 */
	EList getExecutionContexts();

	/**
	 * Returns the value of the '<em><b>Instance Name L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̃C���X�^���X�����擾����
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Name L</em>' attribute.
	 * @see #setInstanceNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_InstanceNameL()
	 * @model default=""
	 * @generated
	 */
	String getInstanceNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getInstanceNameL <em>Instance Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g�̃C���X�^���X����ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance Name L</em>' attribute.
	 * @see #getInstanceNameL()
	 * @generated
	 */
	void setInstanceNameL(String value);

	/**
	 * Returns the value of the '<em><b>Vender L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̍쐬�x���_�����擾����B�x���_���̓R���|�[�l���gId�̈ꕔ�Ƃ��Ďg�p�����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vender L</em>' attribute.
	 * @see #setVenderL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_VenderL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getVenderL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVenderL <em>Vender L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g�̍쐬�x���_����ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vender L</em>' attribute.
	 * @see #getVenderL()
	 * @generated
	 */
	void setVenderL(String value);

	/**
	 * Returns the value of the '<em><b>Description L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̊T�v�����擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description L</em>' attribute.
	 * @see #setDescriptionL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_DescriptionL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getDescriptionL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getDescriptionL <em>Description L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g�̊T�v����ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description L</em>' attribute.
	 * @see #getDescriptionL()
	 * @generated
	 */
	void setDescriptionL(String value);

	/**
	 * Returns the value of the '<em><b>Category L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̃J�e�S�����擾����B�J�e�S���̓R���|�[�l���gId�̈ꕔ�Ƃ��Ďg�p�����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category L</em>' attribute.
	 * @see #setCategoryL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_CategoryL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getCategoryL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getCategoryL <em>Category L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̃J�e�S����ݒ肷��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category L</em>' attribute.
	 * @see #getCategoryL()
	 * @generated
	 */
	void setCategoryL(String value);

	/**
	 * Returns the value of the '<em><b>Type Name L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC�d�l�̖��̂��擾����BRTC�d�l���̂̓R���|�[�l���gId�̈ꕔ�Ƃ��Ďg�p�����B
	 * </p>
	 * @return the value of the '<em>Serviceports</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Serviceports()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.ServicePort" transient="true" changeable="false" volatile="true"
	 * @return the value of the '<em>Type Name L</em>' attribute.
	 * @see #setTypeNameL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_TypeNameL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getTypeNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getTypeNameL <em>Type Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC�d�l�̖��̂�ݒ肷��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name L</em>' attribute.
	 * @see #getTypeNameL()
	 * @generated
	 */
	void setTypeNameL(String value);

	/**
	 * Returns the value of the '<em><b>Version L</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * RTC�d�l�̃o�[�W�����ԍ����擾����B�o�[�W�����ԍ��̓R���|�[�l���gId�̈ꕔ�Ƃ��Ďg�p�����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version L</em>' attribute.
	 * @see #setVersionL(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_VersionL()
	 * @model default="" transient="true"
	 * @generated
	 */
	String getVersionL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getVersionL <em>Version L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * RTC�d�l�̃o�[�W�����ԍ���ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version L</em>' attribute.
	 * @see #getVersionL()
	 * @generated
	 */
	void setVersionL(String value);

	/**
	 * Returns the value of the '<em><b>Path Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g��URI���擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Id</em>' attribute.
	 * @see #setPathId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_PathId()
	 * @model
	 * @generated
	 */
	String getPathId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getPathId <em>Path Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g��URI��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Id</em>' attribute.
	 * @see #getPathId()
	 * @generated
	 */
	void setPathId(String value);

	/**
	 * Returns the value of the '<em><b>Outport Direction</b></em>' attribute.
	 * The default value is <code>"RIGHT"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̕����iRIGHT/LEFT/UP/DOWN�j��Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outport Direction</em>' attribute.
	 * @see #setOutportDirection(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_OutportDirection()
	 * @model default="RIGHT"
	 * @generated
	 */
	String getOutportDirection();

	/**
	 * @param value �R���|�[�l���g�̕����iRIGHT/LEFT/UP/DOWN�j
	 */
	void setOutportDirection(String value);

	/**
	 * Returns the value of the '<em><b>Composite Type L</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g�̃R���|�W�b�g�^�C�v���擾����B�R���|�W�b�g�^�C�v�̓J�e�S�����瓱�o�����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Type L</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_CompositeTypeL()
	 * @model transient="true" changeable="false"
	 * @generated
	 */
	String getCompositeTypeL();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' reference list.
	 * The list contents are of type {@link jp.go.aist.rtm.toolscommon.model.component.Component}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �qRTC�Ƃ��Ċ܂ރR���|�[�l���g�̃��X�g��Ԃ��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' reference list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Components()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Component"
	 * @generated
	 */
	EList getComponents();

	/**
	 * Returns the value of the '<em><b>Component Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g��ID���擾����B�R���|�[�l���gID��
	 * "RTC:" + getVenderL() + "." + getCategoryL () + "."+ getTypeNameL() + ":"+ getVersionL()
	 * �Őݒ肳���B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Id</em>' attribute.
	 * @see #setComponentId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ComponentId()
	 * @model
	 * @generated
	 */
	String getComponentId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getComponentId <em>Component Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g��ID��ݒ肷��B��{�I�ɂ̓L���b�V���̖�ڂ����Ȃ��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Id</em>' attribute.
	 * @see #getComponentId()
	 * @generated
	 */
	void setComponentId(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �R���|�[�l���g���K�{�ł��邩��Ԃ��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_Required()
	 * @model default="false"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g���K�{�ł��邩��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Child System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ����RTC�̓�����\������_�C�A�O�������擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child System Diagram</em>' reference.
	 * @see #setChildSystemDiagram(SystemDiagram)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getComponent_ChildSystemDiagram()
	 * @model
	 * @generated
	 */
	SystemDiagram getChildSystemDiagram();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.Component#getChildSystemDiagram <em>Child System Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * ����RTC�̓�����\������_�C�A�O������ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child System Diagram</em>' reference.
	 * @see #getChildSystemDiagram()
	 * @generated
	 */
	void setChildSystemDiagram(SystemDiagram value);

	/**
	 * <!-- begin-user-doc -->
	 * �R���|�[�l���g��ConfigurationSet���X�V����B
	 * �K�v�ɉ����āAConfigurationSet�̒ǉ��^�C���^�폜�ƃA�N�e�B�u��ConfigurationSet�̕ύX���s���B
	 * <!-- end-user-doc -->
	 * @model listDataType="jp.go.aist.rtm.toolscommon.model.component.List" listMany="false" originallistDataType="jp.go.aist.rtm.toolscommon.model.component.List" originallistMany="false"
	 * @generated
	 */
	boolean updateConfigurationSetListR(List list, ConfigurationSet activeConfigurationSet, List originallist);

	/**
	 * <!-- begin-user-doc -->
	 * �qRTC�Ƃ��Ċ܂ރR���|�[�l���g���ċA�I�ɂ��ׂĎ擾���A���X�g�Ƃ��ĕԂ��B
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated
	 */
	List getAllComponents();

	/**
	 * <!-- begin-user-doc -->
	 * ���YRTC�������R���|�[�l���g�ł��邩��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isCompositeComponent();

	/**
	 * <!-- begin-user-doc -->
	 * ���YRTC���uGrouping�v����RTC�i�O���[�s���O�����̕����R���|�[�l���g�j�ł��邩��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isGroupingCompositeComponent();

	/**
	 * <!-- begin-user-doc -->
	 * ���YRTC���I�����C���̃V�X�e���_�C�A�O�����Ɋ܂܂�邩��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean inOnlineSystemDiagram();

	/**
	 * <!-- begin-user-doc -->
	 * ����RTC�ɎqRTC��ǉ�����B
	 * <!-- end-user-doc -->
	 * @model componentListDataType="jp.go.aist.rtm.toolscommon.model.component.List" componentListMany="false"
	 * @generated NOT
	 */
	boolean addComponentsR(List<Component> componentList);

	/**
	 * <!-- begin-user-doc -->
	 * ����RTC����qRTC���폜����
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean removeComponentR(Component component);

	/**
	 * <!-- begin-user-doc -->
	 * ����RTC�����J���Ă���|�[�g�̖��́i�qRTC�̃C���X�^���X���@+�@"." + �|�[�g���j�̃��X�g��Ԃ��B
	 * <!-- end-user-doc -->
	 */
	List<String> getExportedPorts();

	/**
	 * <!-- begin-user-doc -->
	 * ����RTC�����J���Ă���|�[�g�̖��́i�qRTC�̃C���X�^���X���@+�@"." + �|�[�g���j�̃��X�g��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @model valuesType="java.lang.String" valuesMany="true"
	 * @generated
	 */
	boolean setExportedPorts(EList values);

	/**
	 * <!-- begin-user-doc -->
	 * ���YRTC��ConfigurationSet���X�V����BisActive��true�̏ꍇ�́A�A�N�e�B�x�[�g���s���B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive);

	/**
	 * <!-- begin-user-doc -->
	 * ����RTC�̐V�K�쐬���ɎqRTC�̃��X�g��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @model componentListDataType="jp.go.aist.rtm.toolscommon.model.component.List" componentListMany="false"
	 * @generated NOT
	 */
	boolean setComponentsR(List<Component> componentList);
	
	/**
	 * <!-- begin-user-doc -->
	 * �V�K�����R���|�[�l���g�쐬�_�C�A���O�ɕ\������p�X��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getPath();

	/**
	 * <!-- begin-user-doc -->
	 * ID�ɑΉ��t����ExecutionContext��o�^���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext setExecutionContext(String id, ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * ID�ɑΉ�����ExecutionContext���擾���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext getExecutionContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContext����ID���t�������ĕԂ��܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getExecutionContextId(ExecutionContext ec);

	/**
	 * �S�����ƎQ�Ƃ̓������蓮�ōs��
	 */
	void synchronizeManually();

	/**
	 * �q�R���|�[�l���g�̓������蓮�ōs��
	 */
	void synchronizeChildComponents();
	
	/**
	 * �w�肵�������inull�̏ꍇ�͑S�����j�̓��������s����
	 * @param reference
	 */
	void synchronizeLocalAttribute(EStructuralFeature reference);

	/**
	 * �Q�Ƃ̓��������s����
	 */
	void synchronizeLocalReference();

	/**
	 * �q�R���|�[�l���g��ǉ����� 
	 */
	void addComponent(Component component);

	/**
	 * �R���|�[�l���g�̃f�B�[�v�R�s�[������ĕԂ�
	 */
	Component copy();

	/**
	 * ping�ɉ������Ȃ��qRTC��components�����菜��
	 */
	void removeDeadChild();

	/**
	 * @return �R���|�[�l���g�������Ă��邩
	 */
	boolean isDead();

} // Component
