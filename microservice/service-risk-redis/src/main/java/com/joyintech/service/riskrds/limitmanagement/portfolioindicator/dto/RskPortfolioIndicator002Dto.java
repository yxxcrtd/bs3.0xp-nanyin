/*
 * 文件名：RskPortfolioIndicator002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RskPortfolioIndicator;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;

/**
 * 
 * 组合关联监督指标
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskPortfolioIndicator002Dto
 * @since 1.0
 */
@PageId("RskPortfolioIndicator002")
public class RskPortfolioIndicator002Dto  extends RskPortfolioIndicator {

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
    /**
     * 选择数据
     */
    private List<RskSupervisionIndicator002Dto> rskSupervisionIndicator002DtoList;
    
    private String portfolioName;
    
    private String indName;
    
    /**
     * 组合成分
     */
    private String portfolioMix;
    /**
     * 指标类型
     */
    private String indType;


    /**
     * 监督类型
     */
    private String supType;


    /**
     * 预警条件
     */
    private String warnCond;

    /**
     * 审批条件
     */
    private String approveCond;

    /**
     * 违规条件
     */
    private String foulCond;

    /**
     * 是否启用
     */
    private String isEnabled;
    
    @Override
    public String getIndId() {
        return StringUtil.isNotEmpty(super.getIndId())
            ?super.getIndId():null;
    }

    @Override
    public String getPortfolioId() {
        return StringUtil.isNotEmpty(super.getPortfolioId())
            ?super.getPortfolioId():null;
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


	public List<RskSupervisionIndicator002Dto> getRskSupervisionIndicator002DtoList() {
		return rskSupervisionIndicator002DtoList;
	}

	public void setRskSupervisionIndicator002DtoList(
			List<RskSupervisionIndicator002Dto> rskSupervisionIndicator002DtoList) {
		this.rskSupervisionIndicator002DtoList = rskSupervisionIndicator002DtoList;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getIndName() {
		return indName;
	}

	public void setIndName(String indName) {
		this.indName = indName;
	}

	public String getPortfolioMix() {
		return portfolioMix;
	}

	public void setPortfolioMix(String portfolioMix) {
		this.portfolioMix = portfolioMix;
	}

	public String getIndType() {
		return indType;
	}

	public void setIndType(String indType) {
		this.indType = indType;
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public String getWarnCond() {
		return warnCond;
	}

	public void setWarnCond(String warnCond) {
		this.warnCond = warnCond;
	}

	public String getApproveCond() {
		return approveCond;
	}

	public void setApproveCond(String approveCond) {
		this.approveCond = approveCond;
	}

	public String getFoulCond() {
		return foulCond;
	}

	public void setFoulCond(String foulCond) {
		this.foulCond = foulCond;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	
}
