package jp.go.aist.rtm.repositoryView.model;

import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;

/**
 * RepositoryViewItemのツリーを構築するファクトリ
 *
 */
public class RepositoryViewFactory {

	private static RepositoryViewItem makeChild(RepositoryViewItem itemParent, String name, int type) {
		RepositoryViewItem itemChild = new RepositoryViewItem(name,type);
		itemParent.addChild(itemChild);
		itemChild.setParent(itemParent);
		return itemChild;
	}

//	public static RepositoryViewItem makeLeaf(RepositoryViewItem itemParent, String name, ComponentSpecification component, String leafType) {
//		return makeLeaf(itemParent, name, component, leafType, false);
//	}
	
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
	 * RepositoryViewItemのツリーを構築する
	 * @param itemFirst  　ルートのRepositoryViewItem
	 * @param module　　　　　ロードするコンポーネント仕様
	 * @param leafType	　リーフの型（現状はRTCのみ）
	 */
	public static void buildTree(RepositoryViewItem itemFirst, ComponentSpecification module, String leafType) {
		buildTree(itemFirst, module, leafType, false);
	}
	
	/**
	 * RepositoryViewItemのツリーを構築する
	 * @param itemFirst		ルートのRepositoryViewItem
	 * @param module		ロードするコンポーネント仕様
	 * @param leafType		リーフの型
	 * @param isRepository	レポジトリであるかを示すフラグ
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
