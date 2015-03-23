package com.example.treeview.bean;

import com.example.treeview.utils.annotation.TreeNodeId;
import com.example.treeview.utils.annotation.TreeNodeLabel;
import com.example.treeview.utils.annotation.TreeNodePid;

public class FileBean {
	@TreeNodeId
	private int id;
	@TreeNodePid
	private int pId;
	@TreeNodeLabel
	private String label;
	
	private String desc;

	
	
	public FileBean(int id, int pId, String label) {
		this.id = id;
		this.pId = pId;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pId;
	}

	public void setPid(int pid) {
		this.pId = pid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
