package com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto;

import java.io.Serializable;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

public class SeniorExecutiveInfDto extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private String rowNumber;

	private String seqno;

	private String orgNo;

	private String corClass;

	private String source;

	private String executiveName;

	private String title;

	private String corOrgtype;

	private String corOrgid;
	/**
	 * 选择数据
	 */
	private List<SeniorExecutiveInfDto> selectedItem;

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getCorClass() {
		return corClass;
	}

	public void setCorClass(String corClass) {
		this.corClass = corClass;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getExecutiveName() {
		return executiveName;
	}

	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCorOrgtype() {
		return corOrgtype;
	}

	public void setCorOrgtype(String corOrgtype) {
		this.corOrgtype = corOrgtype;
	}

	public String getCorOrgid() {
		return corOrgid;
	}

	public void setCorOrgid(String corOrgid) {
		this.corOrgid = corOrgid;
	}
	
	public List<SeniorExecutiveInfDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<SeniorExecutiveInfDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
}
