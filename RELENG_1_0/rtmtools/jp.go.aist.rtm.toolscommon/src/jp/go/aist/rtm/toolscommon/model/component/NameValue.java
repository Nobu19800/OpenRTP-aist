package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

/**
 * SDO��NameValue�����b�v����N���X
 * 
 * @model
 */
public interface NameValue extends WrapperObject{
	/**
	 * @model
	 * @return�@�v���p�e�B��
	 */
	public String getName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model
	 * @return�@�v���p�e�B�̒l
	 */
	public String getValue();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * �l��������ȊO�̏ꍇ�ɁA�^����Ԃ�
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see jp.go.aist.rtm.toolscommon.model.component.ComponentPackage#getNameValue_TypeName()
	 * @model default=""
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.NameValue#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * @return�@�l��������̏ꍇ�͒l�A�����łȂ��ꍇ�͌^��
	 */
	public String getValueAsString();

	/**
	 * @return�@�J���}��؂�̒l���A�l�̃��X�g�ɂ��ĕԂ�
	 */
	public List<String> getValueAsStringList();

}
