package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.valid.AgencyDetailsInfValidator;

@PageId("AgencyDetailsInf002")
@ValidLogic(targetClass = AgencyDetailsInfValidator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class SeniorExecutiveInfDto extends BaseEntity implements ProcessEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型 - 工作流参数
	 */
	private String operateType;
	
	 /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    
	/**
	 * 选择数据
	 */
	private List<SeniorExecutiveInfDto> selectedItem;

	private String rowNumber;

	private String seqno;

	private String orgNo;

	private String corClass;

	private String source;

	private String executiveName;

	private String title;

	private String corOrgtype;

	private String corOrgid;

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}


	@PrimaryKey(value = Field.PK_1)
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

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public List<SeniorExecutiveInfDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<SeniorExecutiveInfDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
}
