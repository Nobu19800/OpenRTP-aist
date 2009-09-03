/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager;

import jp.go.aist.rtm.nameserviceview.manager.impl.NameServerManagerImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Server Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see jp.go.aist.rtm.nameserviceview.manager.ManagerPackage#getNameServerManager()
 * @model
 * @generated
 */
public interface NameServerManager extends Node {
	NameServerManager eInstance = NameServerManagerImpl.getInstance();

	/**
	 * <!-- begin-user-doc -->
	 * �܂�ł��邷�ׂẴm�[�h����������ؒf���A�ēx�ڑ�����B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void refreshAll();

	/**
	 * <!-- begin-user-doc -->
	 * �w�肵���ڑ�������̃m�[�h���܂�ł��邩��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean isExist(String nameServer);

	/**
	 * <!-- begin-user-doc -->
	 * �w�肵���ڑ�������̃m�[�h��ǉ�����B�ǉ�����NameServerContext��Ԃ��B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	NameServerContext addNameServer(String nameServer);

	/**
	 * �w�肳�ꂽ�l�[���T�[�o��j������
	 * @param nameServerNamingContext
	 */
	void removeNode(NameServerContext nameServerNamingContext);
	
	/**
	 * <!-- begin-user-doc -->
	 * ���~���b�����Ɋ܂�ł���m�[�h���X�V���邩��ݒ肷��B
	 * �V�X�e���G�f�B�^�̋N�����A��~���A�l�[���T�[�r�X�r���[�̐ݒ��ʂŐڑ�������ύX�����Ƃ��ɌĂяo�����B
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setSynchronizeInterval(long milliSecond);

} // NameServerManager
