package jp.go.aist.rtm.repositoryView.model;

import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;

/**
 * RepositoryViewItem�̃c���[���\�z����t�@�N�g��
 *
 */
public class RepositoryViewFactory {

	private static RepositoryViewItem makeChild(RepositoryViewItem itemParent, String name, int type) {
		RepositoryViewItem itemChild = new RepositoryViewItem(name,type);
		itemParent.addChild(itemChild);
		itemChild.setParent(itemParent);
		return itemChild;
	}

	private static RepositoryViewItem makeLeaf(RepositoryViewItem itemParent, String name, 
			ComponentSpecification component, String leafType, boolean isRepository) {
		RepositoryViewLeafItem itemChild = null;
		if(leafType.equals(RepositoryViewLeafItem.RTModel_LEAF)) {
			itemChild = new RTModelRVLeafItem();
		} else if(leafType.equals(RepositoryViewLeafItem.RTSenario_LEAF)) {
			itemChild = new RTSenarioRVLeafItem();
		} else if(leafType.equals(RepositoryViewLeafItem.RTSystem_LEAF)) {
			itemChild = new RTSystemRVLeafItem();
		} else {
			itemChild = new RTCRVLeafItem();
		}
		itemChild.setName(name);
		itemChild.setRepositoryitem(isRepository);
		itemParent.addChild(itemChild);
		itemChild.setParent(itemParent);
		itemChild.setComponent(component);
		return itemChild;
	}

	/**
	 * RepositoryViewItem�̃c���[���\�z����
	 * @param itemFirst  �@���[�g��RepositoryViewItem
	 * @param module�@�@�@�@�@���[�h����R���|�[�l���g�d�l
	 * @param leafType	�@���[�t�̌^�i�����RTC�̂݁j
	 */
	public static void buildTree(RepositoryViewItem itemFirst, ComponentSpecification module, String leafType) {
		buildTree(itemFirst, module, leafType, false);
	}
	
	/**
	 * RepositoryViewItem�̃c���[���\�z����
	 * @param itemFirst		���[�g��RepositoryViewItem
	 * @param module		���[�h����R���|�[�l���g�d�l
	 * @param leafType		���[�t�̌^
	 * @param isRepository	���|�W�g���ł��邩�������t���O
	 */
	public static void buildTree(RepositoryViewItem itemFirst, ComponentSpecification module, String leafType, boolean isRepository) {
		RepositoryViewItem itemSecond = itemFirst.getChild(module.getCategoryL());
		if( itemSecond == null ) {
			itemSecond = RepositoryViewFactory.makeChild(itemFirst, module.getCategoryL(), RepositoryViewItem.CATEGORY_ITEM);
		}
		if( itemSecond.getChild(module.getAliasName()) == null ){
			RepositoryViewFactory.makeLeaf(itemSecond, module.getAliasName(), module, leafType, isRepository);
		}
	}

}
