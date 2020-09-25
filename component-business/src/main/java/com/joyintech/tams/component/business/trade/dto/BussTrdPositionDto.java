/*
 * 文件名：BussTrdPositionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;

import java.util.List;

/**
 * 产品端组合现金持仓dto
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月13日
 * @see BussTrdPositionDto
 * @since 1.0
 */
public class BussTrdPositionDto {
    /**
     * 流程状态
     */
    private String workflowStatus;
    
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 分红方式 
     */
    private String bonusMode;
    
    /**
     * 金融产品交易Entity集合
     */
    private List<TrdProductDeal> trdProductDealList;
    
    /**
     * 划款指令list
     */
    private List<StlPayOrdersDto> stlPayOrders;

    
    /**
     * 获取 workflowStatus
     * @return workflowStatus.
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    
    /**
     * 设置 workflowStatus
     * @param workflowStatus workflowStatus
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    
    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    
    /**
     * 获取 bonusMode
     * @return bonusMode.
     */
    public String getBonusMode() {
        return bonusMode;
    }

    
    /**
     * 设置 bonusMode
     * @param bonusMode bonusMode
     */
    public void setBonusMode(String bonusMode) {
        this.bonusMode = bonusMode;
    }

    
    /**
     * 获取 trdProductDealList
     * @return trdProductDealList.
     */
    public List<TrdProductDeal> getTrdProductDealList() {
        return trdProductDealList;
    }

    
    /**
     * 设置 trdProductDealList
     * @param trdProductDealList trdProductDealList
     */
    public void setTrdProductDealList(List<TrdProductDeal> trdProductDealList) {
        this.trdProductDealList = trdProductDealList;
    }

    
    /**
     * 获取 stlPayOrders
     * @return stlPayOrders.
     */
    public List<StlPayOrdersDto> getStlPayOrders() {
        return stlPayOrders;
    }

    
    /**
     * 设置 stlPayOrders
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(List<StlPayOrdersDto> stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }
    
}
