/*
 * 文件名：RskSupervisionIndicator002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.riskredis.quota.superviseindicator.dto;

import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.RskIsupIndicator;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.superviseindicator.validator.RskSupervisionIndicatorValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 监督指标管理基本信息
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicator002Dto
 * @since 1.0
 */
@PageId("RskSupervisionIndicator002")
@ValidLogic(targetClass = RskSupervisionIndicatorValidator.class , groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class RskSupervisionIndicator002Dto  extends RskIsupIndicator implements ITableRow,ProcessEntity{

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
    
    private String startWfType;
    
    /**
     * 筛选单组合的条件
     */
    private String singlePortfCond;
    /**
     * 监督指标基本信息
     */
    @Valid
    private RskSupervisionIndicatorDto rskSupervisionIndicatorDto;

    /**
     * 组合关联指标信息
     */
    @Valid
    private RskPortfolioIndicatorDto rskPortfolioIndicatorDto;
    
    /**
     * 选择数据
     */
    private List<RskSupervisionIndicator002Dto> selectedItem;
    
    /**
     * 选择组合包或组合数据
     */
    private List<RskPortfolioIndicatorDto> selectedPortfIndItem;
    
    private String portfolioCodeConst;
    
    /**
     * 组合代码，包括组合包代码
     */
    private String portfolioId;

    @Override
    @PrimaryKey(value=Field.PK_2)
    public String getIndId() {
        return StringUtil.isNotEmpty(getRskSupervisionIndicatorDto())?StringUtil.isNotEmpty(getRskSupervisionIndicatorDto().getIndId())?getRskSupervisionIndicatorDto().getIndId():StringUtil.isNotEmpty(super.getIndId())
            ?super.getIndId():StringUtil.EMPTY:StringUtil.isNotEmpty(super.getIndId())
                    ?super.getIndId():StringUtil.EMPTY;
    }

    @Override
    @PrimaryKey(value=Field.PK_1)
    public String getIndName() {
        return StringUtil.isNotEmpty(getRskSupervisionIndicatorDto())?StringUtil.isNotEmpty(getRskSupervisionIndicatorDto().getIndName())?getRskSupervisionIndicatorDto().getIndName():StringUtil.isNotEmpty(super.getIndName())
            ?super.getIndName():StringUtil.EMPTY : StringUtil.isNotEmpty(super.getIndName())
                    ?super.getIndName():StringUtil.EMPTY;
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

	public String getStartWfType() {
		return startWfType;
	}

	public void setStartWfType(String startWfType) {
		this.startWfType = startWfType;
	}

	public RskPortfolioIndicatorDto getRskPortfolioIndicatorDto() {
		return rskPortfolioIndicatorDto;
	}

	public RskSupervisionIndicatorDto getRskSupervisionIndicatorDto() {
		return rskSupervisionIndicatorDto;
	}

	public void setRskSupervisionIndicatorDto(RskSupervisionIndicatorDto rskSupervisionIndicatorDto) {
		this.rskSupervisionIndicatorDto = rskSupervisionIndicatorDto;
	}

	public void setRskPortfolioIndicatorDto(RskPortfolioIndicatorDto rskPortfolioIndicatorDto) {
		this.rskPortfolioIndicatorDto = rskPortfolioIndicatorDto;
	}

	public List<RskSupervisionIndicator002Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<RskSupervisionIndicator002Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<RskPortfolioIndicatorDto> getSelectedPortfIndItem() {
		return selectedPortfIndItem;
	}
	public void setSelectedPortfIndItem(List<RskPortfolioIndicatorDto> selectedPortfIndItem) {
		this.selectedPortfIndItem = selectedPortfIndItem;
	}
	
	public String getPortfolioCodeConst() {
   		return portfolioCodeConst;
   	}
    
 	public void setPortfolioCodeConst(String portfolioCodeConst) {
   		this.portfolioCodeConst = portfolioCodeConst;
   	}
 	
 	public String getSinglePortfCond() {
		return singlePortfCond;
	}

	public void setSinglePortfCond(String singlePortfCond) {
		this.singlePortfCond = singlePortfCond;
	}

	public String getIndTypeStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标类型, this.getIndType());
	}
	 public String getSupTypeStr(){
    	 return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.监督类型, this.getSupType());
    }
	 public String getIsEnabledStr(){
    	 return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.使用状态, this.getIsEnabled());
	 }

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	  /**
     * 用户可更改标识
     */
    private String updateFlg;

    
    /**
     * 获取 updateFlg
     * @return updateFlg.
     */
    public String getUpdateFlg() {
        return updateFlg;
    }

    
    /**
     * 设置 updateFlg
     * @param updateFlg updateFlg
     */
    public void setUpdateFlg(String updateFlg) {
        this.updateFlg = updateFlg;
    }
     
}
