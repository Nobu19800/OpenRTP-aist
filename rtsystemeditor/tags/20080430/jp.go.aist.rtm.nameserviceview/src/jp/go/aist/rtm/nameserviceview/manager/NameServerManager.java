package jp.go.aist.rtm.nameserviceview.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jp.go.aist.rtm.nameserviceview.corba.NameServerAccesser;
import jp.go.aist.rtm.nameserviceview.factory.NameServiceViewWrapperFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServerNamingContext;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingContextNodeImpl;

import org.omg.CosNaming.NamingContextExt;

/**
 * �l�[���T�[�o�̃��X�g���Ǘ�����}�l�[�W��
 */
public class NameServerManager extends NamingContextNodeImpl {

	/**
	 * �l�[���T�[�o�̃V���O���g���C���X�^���X
	 */
	private static NameServerManager __instance = new NameServerManager();

	/**
	 * �l�[���T�[�o�̃V���O���g���C���X�^���X���擾����
	 */
	public static NameServerManager getInstance() {
		return __instance;
	}

	/**
	 * ���t���b�V���̃^�C�}
	 */
	private Timer refreshTimer;

	/**
	 * �l�[���T�[�o��ǉ�����B
	 * 
	 * @param nameServer
	 * @return �ǉ������l�[���T�[�o
	 */
	public NameServerNamingContext addNameServer(String nameServer) {
		if (isExist(nameServer)) {
			return null;
		}

		NamingContextExt namingContext = NameServerAccesser.getInstance()
				.getNameServerRootContext(nameServer);

		NameServerNamingContext result = NameServiceViewWrapperFactory.getInstance()
				.getNameServiceContextCorbaWrapper(namingContext, nameServer);

		if (result != null) {
			this.getNodes().add(result);
		}

		return result;
	}

	/**
	 * ���ׂẴl�[���T�[�o��j�����A�č\�z����B
	 */
	public void refreshAll() {
		List<NameServerNamingContext> unModifyList = new ArrayList<NameServerNamingContext>(
				getNameServerNamingContextList()); // �R�s�[
		for (NameServerNamingContext nameServerNamingContext : unModifyList) {
			refresh(nameServerNamingContext);
		}
	}

	/**
	 * �w�肳�ꂽ�l�[���T�[�o��j�����A�č\�z����B
	 * 
	 * @param nameServerNamingContext
	 */
	public void refresh(NameServerNamingContext nameServerNamingContext) {
		String nameServerName = nameServerNamingContext
				.getNameServiceReference().getNameServerName();
		getNameServerNamingContextList().remove(nameServerNamingContext);
		addNameServer(nameServerName);
	}

	/**
	 * ���ׂẴl�[���T�[�o�ɂ��āA�����[�g���𐳂Ƃ��ē�������B
	 */
	public void synchronizeAll() {
		List<NameServerNamingContext> unModifyList = new ArrayList<NameServerNamingContext>(
				getNameServerNamingContextList());
		for (NameServerNamingContext nameServerNamingContext : unModifyList) {
			nameServerNamingContext.getSynchronizationSupport()
					.synchronizeLocal();
		}
	}

	/**
	 * �Ώۂ̃l�[���T�[�r�X�����A���݂��邩�ǂ����B
	 * 
	 * @param nameService
	 *            �m�F����l�[���T�[�r�X��
	 * @return �l�[���T�[�r�X�������݂��邩�ǂ���
	 */
	public boolean isExist(String nameServer) {
		boolean result = false;
		for (NamingContextNode namingContext : getNameServerNamingContextList()) {
			if (namingContext.getNameServiceReference().getNameServerName()
					.equals(nameServer)) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * ���ׂẴl�[���T�[�o�̃��[�g�R���e�N�X�g���擾����
	 * 
	 * @return
	 */
	public List<NameServerNamingContext> getNameServerNamingContextList() {
		return (List<NameServerNamingContext>) getNodes();
	}

	/**
	 * ��莞�Ԃ��Ƃɓ������s���悤�ɂ���B
	 * <p>
	 * �u0�v��ݒ肵���ꍇ�ɂ́A�������Ȃ��B
	 * 
	 * @param milliSecond
	 *            ��������
	 */
	public synchronized void setSynchronizeInterval(final long milliSecond) {

		if (refreshTimer != null) {
			refreshTimer.cancel();
		}
		refreshTimer = null;

		if (milliSecond > 0) {
			refreshTimer = new Timer(true);

			refreshTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						synchronizeAll();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 0, milliSecond);
		}
	}
}
