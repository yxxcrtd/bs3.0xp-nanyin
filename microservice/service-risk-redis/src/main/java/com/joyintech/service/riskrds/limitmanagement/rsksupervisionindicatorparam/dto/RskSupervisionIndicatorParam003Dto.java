package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskIsupFixRecode;

public class RskSupervisionIndicatorParam003Dto extends RskIsupFixRecode{

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
    private List<RskSupervisionIndicatorParam003Dto> selectedItem;
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
	public String getParaName() {
		return paraName;
	}
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	public List<RskSupervisionIndicatorParam003Dto> getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(List<RskSupervisionIndicatorParam003Dto> selectedItem) {
		this.selectedItem = selectedItem;
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
