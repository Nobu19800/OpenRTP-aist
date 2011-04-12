package jp.go.aist.rtm.repositoryView.model;

import jp.go.aist.rtm.repositoryView.nl.Messages;


/**
 * ���[�g�ƂȂ�RepositoryViewItem
 * RepositoryViewItem�̃T�u�N���X�Ƃ��Ē�`����Ӗ��͂��܂�Ȃ�
 *
 */
public class RepositoryViewRootItem extends RepositoryViewItem {

	public static final String LOCAL_ROOT = Messages.getString("RepositoryViewRootItem.0");
	public static final String SERVER_ROOT = Messages.getString("RepositoryViewRootItem.1");

	public RepositoryViewRootItem(String name) {
		super(name, RepositoryViewItem.ROOT_ITEM);
	}
}
