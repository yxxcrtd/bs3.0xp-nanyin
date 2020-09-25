package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskIsupFixRecode;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.validator.RskSupervisionIndicatorParam006Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("RskSupervisionIndicatorParam006")
@ValidLogic(targetClass = RskSupervisionIndicatorParam006Validator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class RskSupervisionIndicatorParam006Dto extends RskIsupFixRecode implements ITableRow,ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 操作类型
     */
    private String operateType;
    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 参数名称
     */
    private String paraName;
    private String pageId;
    
    private List<RskSupervisionIndicatorParam006Dto> selectedItem;
    
    private String updateFlag;
    
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
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	@PrimaryKey(value=Field.PK_1)
	@NotEmpty(fieldName = "RskSupervisionIndicatorParam003.paraName",groups = {ValidateGroupEdit.class})
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	@PrimaryKey(value = Field.PK_2)
    public String getParaCode(){
    	return super.getParaCode();
    }
	public Date getValidDate(){
		return super.getValidDate();
	}
	
	public String getContent(){
		return super.getContent();
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public List<RskSupervisionIndicatorParam006Dto> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(List<RskSupervisionIndicatorParam006Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getUpdateFlag() {
		if(this.getValidDate()!=null&&DateUtil.compareDate(this.getValidDate(),
				DateUtil.parse(DateUtil.format(DateUtil.platDateFromCache())))<0){
			return "02";
		}
		return "01";
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	/**
     * 机构名称
     */
    private String institutionName;

    /**
     * 获取 institutionName
     * @return institutionName.
     */
    public String getInstitutionName() {
        return institutionName;
    }
    
    /**
     * 设置 institutionName
     * @param institutionName institutionName
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    
    
}
