package com.joyintech.tams.disclosure.information.config.reporttree.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepTree;

public class ReportTreeMenuConfiguration001Dto extends RepTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String spflag;

	private String paraTreeName;

	private String repOrder;
	
	
	private String object;

	/**
	 * 是否展示，默认为展示true
	 */
	private boolean show = true;

	private List<String> roleIdList;
	/**
	 * 子节点个数
	 */
	private Integer childNum;

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public String getSpflag() {
		return spflag;
	}

	public void setSpflag(String spflag) {
		this.spflag = spflag;
	}

	public String getParaTreeName() {
		return paraTreeName;
	}

	public void setParaTreeName(String paraTreeName) {
		this.paraTreeName = paraTreeName;
	}

	public String getRepOrder() {
		return repOrder;
	}

	public void setRepOrder(String repOrder) {
		this.repOrder = repOrder;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	
	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "RepTree [flag=" + this.getFlag() + ", paraTreeName=" + paraTreeName + ", show=" + show + "]";
	}

}
