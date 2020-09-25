/*
 * 文件名：PtlCashFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlcashflow.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;

/**
 * 自定义现金流
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月26日
 * @see PtlCashFlowDto
 * @since 1.0
 */
public class PtlCashFlowDto extends TrdProductDeal{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 业务编号
     */
    private String busiNo;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 实体组合代码
     */
    private String actPortfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     *资产类型
     */
    private String finprodType2;
    /**
     *资产代码
     */
    private String finprodId;
    /**
     *市场代码
     */
    private String finprodMarketId;
    /**
     * 资产名称
     */
    private String finprodAbbr;
    /**
     * 类型
     */
    private String customCashType;
    /**
     * 界面ID
     */
    private String elementId;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * pageModel
     */
    private String pageModel;
    /**
     * 选择项
     */
    private List<PtlCashFlowDto> selectedItem;
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderBase> bussTrdTransferOrder;
    public String getBusiNo() {
        return busiNo;
    }
    
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }
    
    public String getPortfolioId() {
        return portfolioId;
    }
    
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    public String getPortfolioName() {
        return portfolioName;
    }
    
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    public String getFinprodType2() {
        return finprodType2;
    }
    
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
    
    public String getFinprodId() {
        return finprodId;
    }
    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getCustomCashType() {
        return customCashType;
    }
    
    public void setCustomCashType(String customCashType) {
        this.customCashType = customCashType;
    }
    
    public String getElementId() {
        return elementId;
    }
    
    public void setElementId(String elementId) {
        this.elementId = elementId;
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

    
    public List<PtlCashFlowDto> getSelectedItem() {
        return selectedItem;
    }

    
    public void setSelectedItem(List<PtlCashFlowDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    
    public List<BussTrdTransferOrderBase> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderBase> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 actPortfolioId
     *
     * @return actPortfolioId
     */
    public String getActPortfolioId() {
        return actPortfolioId;
    }

    /**
     * 设置 actPortfolioId
     *
     * @param actPortfolioId actPortfolioId
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }
}
