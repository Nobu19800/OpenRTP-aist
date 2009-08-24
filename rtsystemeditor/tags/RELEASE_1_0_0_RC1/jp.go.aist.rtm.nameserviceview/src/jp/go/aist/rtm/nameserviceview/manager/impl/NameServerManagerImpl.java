/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.manager.impl;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.nameserviceview.factory.NameServiceViewWrapperFactory;
import jp.go.aist.rtm.nameserviceview.manager.AlreadyExistException;
import jp.go.aist.rtm.nameserviceview.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.manager.NameServerManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.RefreshThread;

import org.eclipse.emf.ecore.EClass;
import org.omg.CosNaming.NamingContextExt;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Server Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NameServerManagerImpl extends NodeImpl implements NameServerManager {

	/**
	 * �l�[���T�[�o�̃V���O���g���C���X�^���X
	 */
	private static NameServerManager __instance = ManagerFactoryImpl.eINSTANCE.createNameServerManager();

	/**
	 * ���t���b�V���̃X���b�h�N���X
	 */
	private RefreshThread refreshThread; 

	/**
	 * �l�[���T�[�o�̃V���O���g���C���X�^���X���擾����
	 */
	public static NameServerManager getInstance() {
		return __instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NameServerManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ManagerPackage.Literals.NAME_SERVER_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * ���ׂẴl�[���T�[�o��j�����A�č\�z����B
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void refreshAll() {
		List<NameServerContext> unModifyList = getUnModifiedNodes();
		for (NameServerContext nameServerNamingContext : unModifyList) {
			refresh(nameServerNamingContext);
		}
	}
	/**
	 * �w�肳�ꂽ�l�[���T�[�o��j�����A�č\�z����B
	 * 
	 * @param nameServerNamingContext
	 */
	private void refresh(NameServerContext nameServerNamingContext) {
		String nameServerName = nameServerNamingContext.getNameServerName();
		removeNode(nameServerNamingContext);
		addNameServer(nameServerName);
	}

	/**
	 * �w�肳�ꂽ�l�[���T�[�o��j������
	 * @param nameServerNamingContext
	 */
	public synchronized void removeNode(NameServerContext nameServerNamingContext) {
		getNodes().remove(nameServerNamingContext);
	}

	/**
	 * <!-- begin-user-doc -->
	 * �Ώۂ̃l�[���T�[�r�X�����A���݂��邩�ǂ����B
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isExist(String nameServer) {
		for (Object obj : getNodes()) {
			NameServerContext namingContext = (NameServerContext) obj;
			if (namingContext.getNameServerName()
					.equals(nameServer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * �l�[���T�[�o��ǉ�����
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public synchronized NameServerContext addNameServer(String nameServer) {
		if (isExist(nameServer)) {
			throw new AlreadyExistException();
		}

		return addCorbaNameServer(nameServer);	
	}

	@SuppressWarnings("unchecked")
	private NameServerContext addCorbaNameServer(String nameServer) {
//		long start = System.currentTimeMillis();
		NamingContextExt namingContext = NameServerAccesser.getInstance()
				.getNameServerRootContext(nameServer);
		
		if (namingContext == null) return null;

		NameServerContext result = NameServiceViewWrapperFactory.getInstance()
				.getNameServiceContextCorbaWrapper(namingContext, nameServer);

		if (result != null) {
			this.getNodes().add(result);
			result.getSynchronizationSupport().synchronizeLocal();
		}
//		System.out.println(System.currentTimeMillis() -start);

		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * ��莞�Ԃ��Ƃɓ������s���悤�ɂ���B
	 * �u0�v��ݒ肵���ꍇ�ɂ́A�������Ȃ��B
	 * 
	 * UI�X���b�h����̃l�[���T�[�o�ǉ��A�X�V���N�G�X�g�ւ̑Ή���getNodes()�Ăяo��������
	 * synchronize�������OK�̂͂��B
	 * NameServerContext�͕ʃC���X�^���X�Ȃ̂ŋ����͔������Ȃ��͂��B
	 * 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public synchronized void setSynchronizeInterval(final long milliSecond) {
		if (refreshThread == null) {
			refreshThread = new RefreshThread(milliSecond){
				@Override
				protected void executeCommand() {
					try {
						synchronizeAll();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}};
			refreshThread.setDaemon(true);
			refreshThread.start();
		} else {
			refreshThread.setSynchronizeInterval(milliSecond);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * ���ׂẴl�[���T�[�o�ɂ��āA�����[�g���𐳂Ƃ��ē�������B
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private void synchronizeAll() {
		List<NameServerContext> unModifyList = getUnModifiedNodes();
		for (NameServerContext nameServerNamingContext : unModifyList) {
			nameServerNamingContext.getSynchronizationSupport()
					.synchronizeLocal();
		}
	}
	
	@SuppressWarnings("unchecked")
	private synchronized List<NameServerContext> getUnModifiedNodes() {
		return new ArrayList<NameServerContext>(getNodes());
	}

} //NameServerManagerImpl
