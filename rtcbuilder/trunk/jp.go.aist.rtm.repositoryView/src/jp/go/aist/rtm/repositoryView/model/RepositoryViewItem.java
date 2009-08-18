package jp.go.aist.rtm.repositoryView.model;

import java.util.ArrayList;
import java.util.List;


/**
 * ���|�W�g���r���[�̃m�[�h��\������I�u�W�F�N�g
 * name�Ń��j�[�N
 *
 */
public class RepositoryViewItem {
	
	public static final int ROOT_ITEM = 1;
	public static final int CATEGORY_ITEM = 2;
	public static final int LEAF_ITEM = 3;
	
	private String name = null;
	private int type = 0;
	private Object parent;
	private List<RepositoryViewItem> children;

	/**
	 * @param name		�m�[�h��
	 * @param type		�m�[�h�̌^�ienum�̕����悢�����j
	 * @param parent	�e�m�[�h
	 * @param children	�q�m�[�h
	 */
	public RepositoryViewItem(String name,int type, Object parent, List<RepositoryViewItem>  children){
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.children = children;
	}
	public RepositoryViewItem(String name,int type){
		this(name,type,null,new ArrayList<RepositoryViewItem>());
	}
	public RepositoryViewItem(String name,int type, Object parent){
		this(name,type,parent,new ArrayList<RepositoryViewItem> ());
	}
	//
	public String getName(){
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getParent(){
		return this.parent;
	}
	public void setParent(RepositoryViewItem parent) {
		this.parent = parent;
	}
	public List<RepositoryViewItem> getChildren() {
		return children;
	}
	public void setChildren(List<RepositoryViewItem>  children) {
		this.children = children;
	}
	//
	public void addChild(RepositoryViewItem child) {
		this.children.add(child);
	}
	
	/**
	 * @param target	�ΏۂƂȂ�m�[�h�̖��O
	 * @return			�݂������m�[�h
	 */
	public RepositoryViewItem getChild(String target) {
		for (RepositoryViewItem item : children) {
			if( item.getName().equals(target) ) {
				return item;
			}
		}
		return null;
	}

	public String toString(){
		return this.name;
	}
}
