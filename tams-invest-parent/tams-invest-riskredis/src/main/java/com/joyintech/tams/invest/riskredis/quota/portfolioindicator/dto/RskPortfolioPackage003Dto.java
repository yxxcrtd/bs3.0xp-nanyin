/*
 * 文件名：RskPortfolioPackage003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.portfolioindicator.dto;

import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.RskPortfolioPackage;
import com.joyintech.tams.invest.riskredis.quota.portfolioindicator.validator.RskPortfolioPackage003Validator;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 组合包明细
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskPortfolioIndicator002Dto.java
 * @since 1.0
 */
@PageId("RskSupervisionPortfPoint003")
@ValidLogic(targetClass = RskPortfolioPackage003Validator.class,groups = {
	    ValidateGroupEdit.class,ValidateGroupDelete.class})
public class RskPortfolioPackage003Dto  extends RskPortfolioPackage implements ITableRow,ProcessEntity{

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
    
    @Override
    @Length(fieldName = "RskSupervisionPortfPoint003.portfolioBagName", max = 60,groups={ValidateGroupEdit.class})
    @NotEmpty(fieldName="RskSupervisionPortfPoint003.portfolioBagName",groups = {ValidateGroupEdit.class})
    public String getPtlBagName() {
        return super.getPtlBagName();
    }
    
    /**
     * 组合包明细信息
     */
    @Valid
    private RskPortfolioPackageDtlDto rskPortfolioPackageDtlDto;

    /**
     * 组合包入池配置
     */
    @Valid
    private RskPortfolioPoolConfigDto rskPortfolioPoolConfigDto;
    
    /**
     * 选择数据
     */
    private List<RskPortfolioPackage003Dto> selectedItem;
    
    @PrimaryKey(value=Field.PK_1)
    public String getPtlBagId(){
    	return super.getPtlBagId();
    }
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

	
}
