package com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.RiskWeight;
import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 风险权数管理
 * @author huwenbin
 *
 */
public class CreditRightsManagementDto extends RiskWeight implements ITableRow{
	/**
	 * 修改前生效日
	 */
	private Date effDateOld;
	
	/**
	 * 被选中的数据
	 */
    private List<CreditRightsManagementDto> selectedItem;
	
	/**
	 * 操作类型
	 */
    private String operateType;
	
	/**
     * 页面提交模式
     */
    private String pageModel;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    public List<CreditRightsManagementDto> getSelectedItem() {
        return selectedItem;
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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public void setSelectedItem(List<CreditRightsManagementDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Date getEffDateOld() {
		return effDateOld;
	}

	public void setEffDateOld(Date effDateOld) {
		this.effDateOld = effDateOld;
	}

        
}
