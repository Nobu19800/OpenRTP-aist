/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * �A�����[�g�ɂ���RTC��̃|�[�g�������Ƃ̓�������邽�߂̃C���^�[�t�F�[�X
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString <em>Original Port String</em>}</li>
 * </ul>
 * </p>
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortSynchronizer()
 * @model
 * @generated
 */
public interface PortSynchronizer extends EObject {

	/**
	 * Returns the value of the '<em><b>Original Port String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �|�[�g�����ʂ��邽�߂̕������Ԃ��B
	 * Corba�R���|�[�l���g�̏ꍇ�́A�|�[�g��Corba�C���^�[�t�F�[�X���g�p�����B
	 * �I�t���C���R���|�[�l���g�̏ꍇ�́A����RTC�łȂ��R���|�[�l���g��ID�A�C���X�^���X���A�|�[�g���̑g�ݍ��킹���g�p�����B
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Port String</em>' attribute.
	 * @see #setOriginalPortString(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getPortSynchronizer_OriginalPortString()
	 * @model
	 * @generated
	 */
	String getOriginalPortString();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortSynchronizer#getOriginalPortString <em>Original Port String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * �|�[�g�����ʂ��邽�߂̕������ݒ肷��B���݂̓I�t���C���̂Ƃ��̂ݎg�p�B
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Port String</em>' attribute.
	 * @see #getOriginalPortString()
	 * @generated
	 */
	void setOriginalPortString(String value);

	/**
	 * <!-- begin-user-doc -->
	 * ���Y�|�[�g��̐ڑ������ׂĐؒf����B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void disconnectAll();

	String getDataflowType();

	String getDataType();

	String getInterfaceType();

	String getSubscriptionType();

	List<NameValue> getProperties();

	String getProperty(String name);


} // PortSynchronizer
