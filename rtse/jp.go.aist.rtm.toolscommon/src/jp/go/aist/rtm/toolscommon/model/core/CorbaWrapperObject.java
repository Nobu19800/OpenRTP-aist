package jp.go.aist.rtm.toolscommon.model.core;

/**
 * Corba�̃��b�p�[�I�u�W�F�N�g��\���N���X
 * 
 * @model
 */
public interface CorbaWrapperObject extends WrapperObject{
	/**
	 * @model
	 * @return ���b�s���O����Corba�I�u�W�F�N�g
	 */
	public org.omg.CORBA.Object getCorbaObject();
	
	/**
	 * @return�@getCorbaObject()��corba�I�u�W�F�N�g��narrow���ĕԂ�
	 */
	public org.omg.CORBA.Object getCorbaObjectInterface();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject#getCorbaObject <em>Corba Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corba Object</em>' attribute.
	 * @see #getCorbaObject()
	 * @generated
	 */
	void setCorbaObject(org.omg.CORBA.Object value);

}
