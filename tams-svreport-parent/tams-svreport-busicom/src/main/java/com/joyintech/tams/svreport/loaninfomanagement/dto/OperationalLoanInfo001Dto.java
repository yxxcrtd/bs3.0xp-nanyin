package com.joyintech.tams.svreport.loaninfomanagement.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import com.joyintech.tams.svreport.loaninfomanagement.validator.OperationalLoanInfo001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 贷款明细信息维护dto
 * @author hpp
 *
 */
@PageId("OperationalLoanInfo002")
@ValidLogic(targetClass = OperationalLoanInfo001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class OperationalLoanInfo001Dto extends ChbLoanDetailInfo implements ProcessEntity{
	//计划主键
	private String filterid;
	//业务数据维护待维护表ID
	private String basetableid;

	public String getFilterid() {
		return filterid;
	}

	public void setFilterid(String filterid) {
		this.filterid = filterid;
	}

	public String getBasetableid() {
		return basetableid;
	}

	public void setBasetableid(String basetableid) {
		this.basetableid = basetableid;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    /**
     * 选中的数据
     */
    private List<OperationalLoanInfo001Dto > selectedItem;
    
    @PrimaryKey(PrimaryKey.Field.PK_1)
	@Override
	public String getAssetUuid() {
        return super.getAssetUuid();
    }
    
	//金融产品代码
	private String finprodId;
    
	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<OperationalLoanInfo001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<OperationalLoanInfo001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

}
