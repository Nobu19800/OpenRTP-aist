package jp.go.aist.rtm.toolscommon.model.component;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.toolscommon.model.core.ModelElement;

import org.eclipse.emf.common.util.EList;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

/**
 * �V�X�e���_�C�A�O�����i�G�f�B�^�j��\������N���X
 * @model
 */
public interface SystemDiagram extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link jp.go.aist.rtm.toolscommon.model.component.Component}. <!--
	 * begin-user-doc -->
	 * <p>
	 * �_�C�A�O�����Ɋ܂܂��R���|�[�l���g�̃��X�g��Ԃ��B
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Components</em>' containment reference
	 *         list.
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_Components()
	 * @model type="jp.go.aist.rtm.toolscommon.model.component.Component"
	 *        containment="true" resolveProxies="false"
	 * @generated
	 */
	EList getComponents();

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �_�C�A�O�����̎�ނ�Ԃ��B�I�����C�����A�I�t���C���̂����ꂩ�ł���B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @see #setKind(SystemDiagramKind)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_Kind()
	 * @model
	 * @generated
	 */
	SystemDiagramKind getKind();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �_�C�A�O�����̎�ނ�ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(SystemDiagramKind value);

	/**
	 * <!-- begin-user-doc -->
	 * ���~���b�����Ɋ܂�ł���R���|�[�l���g���X�V���邩��ݒ肷��B
	 * �I�����C���V�X�e���G�f�B�^�̍쐬���A�j�����A�V�X�e���G�f�B�^�̐ݒ��ʂŐڑ�������ύX�����Ƃ��ɌĂяo�����B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setSynchronizeInterval(long milliSecond);

	/**
	 * �R���|�[�l���c�ύX�̒ʒm���s�����X�i��o�^����
	 * ���݂́A����RTC�̍폜���ɁA����RTC�̓����\���E�B���h�E�����ۂɎg�p����Ă���B
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * �R���|�[�l���c�ύX�̒ʒm���s�����X�i���폜����
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * @return �R�l�N�^�`�揈�����ł��邩
	 */
	public boolean isConnectorProcessing();
	
	/**
	 * @param connectorProcessing �R�l�N�^�`�揈�����ł��邩
	 */
	public void setConnectorProcessing(boolean connectorProcessing);
	/**
	 * Returns the value of the '<em><b>System Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �V�X�e��ID���擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Id</em>' attribute.
	 * @see #setSystemId(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_SystemId()
	 * @model
	 * @generated
	 */
	String getSystemId();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getSystemId <em>System Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �V�X�e��ID��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Id</em>' attribute.
	 * @see #getSystemId()
	 * @generated
	 */
	void setSystemId(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �쐬�������擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_CreationDate()
	 * @model
	 * @generated
	 */
	String getCreationDate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �쐬������ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(String value);

	/**
	 * Returns the value of the '<em><b>Update Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �ŏI�X�V�������擾����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Update Date</em>' attribute.
	 * @see #setUpdateDate(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_UpdateDate()
	 * @model
	 * @generated
	 */
	String getUpdateDate();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getUpdateDate <em>Update Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �ŏI�X�V������ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Date</em>' attribute.
	 * @see #getUpdateDate()
	 * @generated
	 */
	void setUpdateDate(String value);

	/**
	 * Returns the value of the '<em><b>Parent System Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ����RTC�̓�����\������_�C�A�O�����̏ꍇ�A���̕���RTC���܂܂�Ă��錳�̃_�C�A�O������Ԃ��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent System Diagram</em>' reference.
	 * @see #setParentSystemDiagram(SystemDiagram)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_ParentSystemDiagram()
	 * @model
	 * @generated
	 */
	SystemDiagram getParentSystemDiagram();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getParentSystemDiagram <em>Parent System Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * ����RTC�̓�����\������_�C�A�O�����ɑ΂��A����RTC���܂܂�Ă��錳�̃_�C�A�O������ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent System Diagram</em>' reference.
	 * @see #getParentSystemDiagram()
	 * @generated
	 */
	void setParentSystemDiagram(SystemDiagram value);

	/**
	 * Returns the value of the '<em><b>Composite Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * ����RTC�̓�����\������_�C�A�O�����̏ꍇ�A���̕���RTC��Ԃ��B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Component</em>' reference.
	 * @see #setCompositeComponent(Component)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getSystemDiagram_CompositeComponent()
	 * @model
	 * @generated
	 */
	Component getCompositeComponent();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.SystemDiagram#getCompositeComponent <em>Composite Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * ����RTC�̓�����\������_�C�A�O�����ɑ΂��A����RTC��ݒ肷��B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite Component</em>' reference.
	 * @see #getCompositeComponent()
	 * @generated
	 */
	void setCompositeComponent(Component value);

	/**
	 * @param profile �ǂݍ���RtsProfileExt
	 */
	void setProfile(RtsProfileExt profile);

	/**
	 * @return �ǂݍ���RtsProfileExt
	 */
	RtsProfileExt getProfile();

	/**
	 * @return ����RTC�̓�����\������_�C�A�O�����łȂ��ART�V�X�e�����̂��̂�\������_�C�A�O����
	 */
	SystemDiagram getRootDiagram();

	/**
	 * @return �_�C�A�O�������Ɋ܂܂��R�l�N�^���R�l�N�^ID��Connector�̃}�b�v�ŕԂ��B
	 */
	Map<String, PortConnector> getConnectorMap();

	/**
	 * @param component	�폜����R���|�[�l���g
	 */
	void removeComponent(Component component);

	/**
	 * @param components�@�폜����R���|�[�l���g
	 */
	void removeComponents(List<Component> components);

	/**
	 * @param component	�ǉ�����R���|�[�l���g
	 */
	void addComponent(Component component);

	/**
	 * @param pos		�ǉ�����ʒu
	 * @param component	�ǉ�����R���|�[�l���g
	 */
	void addComponent(int pos, Component component);

	/**
	 * @param ��omponents	�ǉ�����R���|�[�l���g
	 */
	void addComponents(List<Component> ��omponents);

	/**
	 * �q�R���|�[�l���g���N���A����
	 */
	void clearComponents();

	/**
	 * �V�X�e���_�C�A�O�������̑S�R���|�[�l���g���蓮�ōX�V����
	 */
	boolean synchronizeManually();

	/**
	 * ���[�g�̃V�X�e���_�C�A�O�����Ɋ܂܂��S�R���|�[�l���g�����X�g�ɂ��ĕԂ�
	 */
	List<Component> getRegisteredComponents();

}
