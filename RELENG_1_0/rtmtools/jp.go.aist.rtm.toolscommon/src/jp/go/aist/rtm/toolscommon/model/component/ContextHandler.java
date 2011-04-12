/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Context Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getContextHandler()
 * @model
 * @generated
 */
public interface ContextHandler extends EObject, IAdaptable {
	/**
	 * <!-- begin-user-doc -->
	 * ID�ɑΉ��t����ExecutionContext��o�^���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext setContext(String id, ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * ID�ɑΉ�����ExecutionContext���擾���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext getContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContext����ID���t�������ĕԂ��܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getId(ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * ID�ɑΉ�����ExecutionContext���폜���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ExecutionContext removeContext(String id);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContext�Ɋ֘A����ID���폜���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String removeId(ExecutionContext ec);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void sync();

	/**
	 * <!-- begin-user-doc -->
	 * �n���h���ɓo�^����Ă���ExecutionContext�̃��X�g��Ԃ��܂��B
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated NOT
	 */
	List<ExecutionContext> values();

	/**
	 * <!-- begin-user-doc -->
	 * �n���h���ɓo�^����Ă���ExecutionContext��ID���X�g��Ԃ��܂��B
	 * <!-- end-user-doc -->
	 * @model dataType="jp.go.aist.rtm.toolscommon.model.component.List" many="false"
	 * @generated NOT
	 */
	List<String> keys();

	/**
	 * <!-- begin-user-doc -->
	 * �n���h���ɓo�^���ꂽExecutionContext���N���A���܂��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void clear();

	/** �n���h���̎�ʂ�Ԃ��܂� (owned/participate) */
	String getType();

	/** �n���h�������L����RTC����EC�̃��X�g���擾���܂��B */
	List<ExecutionContext> getOwnerContexts();

} // ContextHandler
