package com.joyintech.service.valuation.lossmodel.pddefaultmanager.pdfactorconfig.dto;

import java.util.List;

/**
 * 前瞻因子对象
 * @author zhanfgei
 * @version 1.0 2021年6月24日
 * @see ModPdFactorConfigInfoDto
 * @since 1.0
 */
public class ModPdFactorConfigInfoDto extends ModPdFactorConfig{
	
	private static final long serialVersionUID =0L;
	
	 /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 选中的数据
     */
    private transient List<ModPdFactorConfigInfoDto> selectedItem;
	

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

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<ModPdFactorConfigInfoDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ModPdFactorConfigInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
	
}
