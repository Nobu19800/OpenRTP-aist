package jp.go.aist.rtm.toolscommon.model.core;

/**
 * ���f�ł��Ȃ��I�u�W�F�N�g
 * 
 * @model
 */
public interface UnknownObject extends WrapperObject{

	/**
	 * �Ώۂ̃I�u�W�F�N�g
	 * 
	 * @model
	 */
	public org.omg.CORBA.Object getTargetObject();
	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.core.UnknownObject#getTargetObject <em>Target Object</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Object</em>' attribute.
	 * @see #getTargetObject()
	 * @generated
	 */
	void setTargetObject(org.omg.CORBA.Object value);

}
