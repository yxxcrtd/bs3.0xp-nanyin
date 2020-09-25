/*
 * 文件名：RskPortfolioPackage003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskPortfolioPackage;

/**
 * 组合包明细
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskPortfolioIndicator002Dto.java
 * @since 1.0
 */
public class RskPortfolioPackage003Dto  extends RskPortfolioPackage {

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
     * 组合包明细信息
     */
    private RskPortfolioPackageDtlDto rskPortfolioPackageDtlDto;

    /**
     * 组合包入池配置
     */
    private RskPortfolioPoolConfigDto rskPortfolioPoolConfigDto;
    
    /**
     * 选择数据
     */
    private List<RskPortfolioPackage003Dto> selectedItem;
    
    
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

	public List<RskPortfolioPackage003Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RskPortfolioPackage003Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public RskPortfolioPackageDtlDto getRskPortfolioPackageDtlDto() {
		return rskPortfolioPackageDtlDto;
	}

	public void setRskPortfolioPackageDtlDto(RskPortfolioPackageDtlDto rskPortfolioPackageDtlDto) {
		this.rskPortfolioPackageDtlDto = rskPortfolioPackageDtlDto;
	}

	public RskPortfolioPoolConfigDto getRskPortfolioPoolConfigDto() {
		return rskPortfolioPoolConfigDto;
	}

	public void setRskPortfolioPoolConfigDto(RskPortfolioPoolConfigDto rskPortfolioPoolConfigDto) {
		this.rskPortfolioPoolConfigDto = rskPortfolioPoolConfigDto;
	}


}
