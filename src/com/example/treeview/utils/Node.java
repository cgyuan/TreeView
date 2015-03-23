package com.example.treeview.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(int id, int pId, String name) {
		this.id = id;
		this.pId = pId;
		this.name = name;
	}
	
	private int id;
	/**
	 * ���ڵ�pid = 0
	 */
	private int pId;

	private String name;
	/**
	 * ���Ĳ㼶
	 */
	private int level;
	/**
	 * �Ƿ�չ��
	 */
	private boolean isExpand = false;
	private int icon;
	
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
	/**
	 * �Ƿ��Ǹ��ڵ�
	 * @return
	 */
	public boolean isRoot(){
		return parent == null;
	}
	
	/**
	 * �жϵ�ǰ���ڵ������״̬
	 * @return
	 */
	public boolean isParentExpand(){
		if(parent == null)return false;
		return parent.isExpand;
	}
	
	/**
	 * �Ƿ���Ҷ�ڵ�
	 * @return
	 */
	public boolean isLeaf(){
		return children.size() == 0;
	}
	
	/**
	 * �õ���ǰ�ڵ�Ĳ㼶
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 0 : parent.getLevel() + 1;
	}
	
	public boolean isExpand() {
		return isExpand;
	}
	
	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
	}
	public void setLevel(int level) {
		this.level = level;
		
		if(!isExpand){
			for (Node node : children) {
				node.setExpand(false);
			}
		}
	}
	
	
}
