package jp.go.aist.rtm.nameserviceview.model.nameservice;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;



/**
 * �l�[�~���O�I�u�W�F�N�g��\���N���X�iCORBA��p�j
 * @model
 */
public interface NamingObjectNode extends CorbaNode {
	/**
	 * @model containment="true"
	 */
	public abstract WrapperObject getEntry();


	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode#getEntry <em>Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * �l�[�~���O�I�u�W�F�N�g��RT�R���|�[�l���g�܂���RT�}�l�[�W���Ƃ��ĕێ�����
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry</em>' reference.
	 * @see #getEntry()
	 * @generated
	 */
	void setEntry(WrapperObject value);

	/**
	 * �]���r�ł��邩��Ԃ�
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isZombie();

}
