package com.example.treeview.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.treeview.R;
import com.example.treeview.utils.annotation.TreeNodeId;
import com.example.treeview.utils.annotation.TreeNodeLabel;
import com.example.treeview.utils.annotation.TreeNodePid;

public class TreeHelper {
	
	/**
	 * ���û�������ת��Ϊ��������
	 * @param datas
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalArgumentException, IllegalAccessException{
		
		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for (T t : datas) {
			int id = -1;
			int pid = -1;
			String label = null;
			Class clazz = t.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if(field.getAnnotation(TreeNodeId.class) != null){
					field.setAccessible(true);
					id = field.getInt(t);
				}
				
				if(field.getAnnotation(TreeNodePid.class) != null){
					field.setAccessible(true);
					pid = field.getInt(t);
				}
				
				if(field.getAnnotation(TreeNodeLabel.class) != null){
					field.setAccessible(true);
					label = (String) field.get(t);
				}
			}
			node = new Node(id, pid, label);
			nodes.add(node);
		}
		
		/**
		 * ����Node��Ľڵ��ϵ
		 */
		for (int i = 0; i < nodes.size(); i++) {
			Node n = nodes.get(i);
			
			for (int j = i+1; j < nodes.size(); j++) {
				Node m = nodes.get(j);
				
				if(m.getpId() == n.getId()){
					n.getChildren().add(m);
					m.setParent(n);
				}else if(m.getId() == n.getpId()){
					m.getChildren().add(n);
					n.setParent(m);
				}
			}
		}
		
		for (Node n : nodes) {
			setNodeIcon(n);
		}
		
		return nodes;
	}
	
	public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalArgumentException, IllegalAccessException{
		List<Node> result = new ArrayList<Node>();
		List<Node> nodes = convertDatas2Nodes(datas);
		
		// ������ĸ��ڵ�
		List<Node> rootNodes = getRootNodes(nodes);
		
		for (Node node : rootNodes) {
			addNode(result, node, defaultExpandLevel, 1);
		}
		
		return result;
	}
	
	/**
	 * ��һ���ڵ�����к��ӽڵ����result
	 * @param result
	 * @param node
	 * @param defaultExpandLevel
	 * @param i
	 */
	private static void addNode(List<Node> result, Node node,
			int defaultExpandLevel, int currentLevel) {
		result.add(node);
		
		if(defaultExpandLevel >= currentLevel){
			node.setExpand(true);
		}
		
		if(node.isLeaf())
			return;
		for (int i = 0; i < node.getChildren().size(); i++) {
			addNode(result, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
		}
	}
	
	/**
	 * ���˳��ɼ��ڵ�
	 * @param nodes
	 * @return
	 */
	public static List<Node> filterVisibleNodes(List<Node> nodes){
		List<Node> result = new ArrayList<Node>();
		
		for (Node node : nodes) {
			if(node.isRoot() || node.isParentExpand()){
				setNodeIcon(node);
				result.add(node);
			}
		}
		
		return result;
	}

	/**
	 * �����нڵ��й��˳����ڵ�
	 * @param nodes
	 * @return
	 */
	private static List<Node> getRootNodes(List<Node> nodes) {
		List<Node> root = new ArrayList<Node>();
		
		for (Node node : nodes) {
			if(node.isRoot()){
				root.add(node);
			}
		}
		return root;
	}

	/**
	 * Ϊnode����ͼ��
	 * @param n
	 */
	private static void setNodeIcon(Node n) {
		if(n.getChildren().size() > 0 && n.isExpand()){
			n.setIcon(R.drawable.coll);
		}else if(n.getChildren().size() > 0 && !n.isExpand()){
			n.setIcon(R.drawable.expand);
		}else {
			n.setIcon(-1);
		}
	}
}
