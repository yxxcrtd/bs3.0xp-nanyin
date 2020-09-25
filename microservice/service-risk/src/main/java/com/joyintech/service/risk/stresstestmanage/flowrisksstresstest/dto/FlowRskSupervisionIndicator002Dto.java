/*
 * 文件名：RskSupervisionIndicator002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto;

import java.util.List;

/**
 * 
 * 监督指标管理基本信息
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see FlowRskSupervisionIndicator002Dto
 * @since 1.0
 */
public class FlowRskSupervisionIndicator002Dto  extends FlowRskSupervisionIndicatorDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 画面处理模式
     */
    private String pageModel;

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

    private FlowRskSupervisionIndicatorDto rskSupervisionIndicatorDto;
    /**
     * 组合关联指标信息
     */
    private FlowRskPortfolioIndicatorDto rskPortfolioIndicatorDto;
    
    /**
     * 选择数据
     */
    private List<FlowRskSupervisionIndicator002Dto> selectedItem;
    
    /**
     * 选择组合包或组合数据
     */
    private List<FlowRskPortfolioIndicatorDto> selectedPortfIndItem;

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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


	public FlowRskSupervisionIndicatorDto getRskSupervisionIndicatorDto() {
		return rskSupervisionIndicatorDto;
	}

	public void setRskSupervisionIndicatorDto(FlowRskSupervisionIndicatorDto rskSupervisionIndicatorDto) {
		this.rskSupervisionIndicatorDto = rskSupervisionIndicatorDto;
	}

	public FlowRskPortfolioIndicatorDto getRskPortfolioIndicatorDto() {
		return rskPortfolioIndicatorDto;
	}

	public void setRskPortfolioIndicatorDto(FlowRskPortfolioIndicatorDto rskPortfolioIndicatorDto) {
		this.rskPortfolioIndicatorDto = rskPortfolioIndicatorDto;
	}

	public List<FlowRskSupervisionIndicator002Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<FlowRskSupervisionIndicator002Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<FlowRskPortfolioIndicatorDto> getSelectedPortfIndItem() {
		return selectedPortfIndItem;
	}

	public void setSelectedPortfIndItem(List<FlowRskPortfolioIndicatorDto> selectedPortfIndItem) {
		this.selectedPortfIndItem = selectedPortfIndItem;
	}
    
    
}
