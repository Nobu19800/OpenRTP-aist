package jp.go.aist.rtm.nameserviceview.model.nameservice;

import jp.go.aist.rtm.nameserviceview.manager.NameServerContext;

import org.eclipse.emf.common.util.EList;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * �l�[�~���O�R���e�N�X�g��\������N���X�iCORBA��p�j
 * 
 * @model
 */
public interface NamingContextNode extends CorbaNode, NameServerContext {

	String KIND_CATEGORY = "cate_cxt";
	String KIND_HOST = "host_cxt";
	String KIND_MANAGER = "mgr_cxt";
	String KIND_MODULE = "mod_cxt";
	String KIND_SERVER = "server_cxt";

	public NamingContext getCorbaObjectInterface();

	/**
	 * @model type="jp.go.aist.rtm.nameserviceview.model.nameservice.Node"
	 *        containment="true" resolveProxies="false"
	 * @return�@�q�Ƃ��Ċ܂ރm�[�h
	 */
	public EList getNodes();

	/**
	 * @model changeable="false" transient="true" volatile="true"
	 * �]���r�ł��邩��Ԃ�
	 */
	public boolean isZombie();

	
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage#getNamingContextNode_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * �R���e�N�X�g���l�[���T�[�o��ɍ쐬����
	 * @param path
	 * @throws NotFound
	 * @throws AlreadyBound
	 * @throws CannotProceed
	 * @throws InvalidName
	 */
	public void createContextR (NameComponent[] path) throws NotFound, AlreadyBound, CannotProceed, InvalidName;
	
	/**
	 * �w�肵���R���e�L�X�g�ŁA�I�u�W�F�N�g���l�[���T�[�o��ɓo�^����
	 * @param path
	 * @param object
	 * @throws NotFound
	 * @throws AlreadyBound
	 * @throws CannotProceed
	 * @throws InvalidName
	 */
	public void createNamingObjectR(NameComponent[] path, org.omg.CORBA.Object object) throws NotFound, AlreadyBound, CannotProceed, InvalidName;
}
