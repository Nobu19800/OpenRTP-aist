package jp.go.aist.rtm.repositoryView.manager;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.repository.RTRepositoryAccesser;


/**
 * RT���|�W�g���̃��X�g���Ǘ�����}�l�[�W��
 */
public class RTRepositoryManager {

	/**
	 * RT���|�W�g���̃V���O���g���C���X�^���X
	 */
	private static RTRepositoryManager __instance = new RTRepositoryManager();

	/**
	 * �ǉ��ς݃��|�W�g���E�T�[�o�̃��X�g
	 */
	private List<String> repositoryList = new ArrayList<String>();

	/**
	 * RT���|�W�g���̃V���O���g���C���X�^���X���擾����
	 */
	public static RTRepositoryManager getInstance() {
		return __instance;
	}

	/**
	 * RT���|�W�g����ǉ�����B
	 * 
	 * @param repositoryServer
	 * @return �ǉ�����RT���|�W�g��
	 */
	public RepositoryViewItem addRepository(String repositoryServer) {
		if( isExist(repositoryServer) ) return null;

		RepositoryViewItem rootItem = RTRepositoryAccesser.getInstance().getRepositoryServerRoot(repositoryServer);
		repositoryList.add(repositoryServer);
		return rootItem;
	}

	/**
	 * �Ώۂ�RT���|�W�g�������A���݂��邩�ǂ����B
	 * 
	 * @param RTRepository
	 *            �m�F����RT���|�W�g��
	 * @return RT���|�W�g���������݂��邩�ǂ���
	 */
	public boolean isExist(String RTRepository) {
		return false;
	}
}
