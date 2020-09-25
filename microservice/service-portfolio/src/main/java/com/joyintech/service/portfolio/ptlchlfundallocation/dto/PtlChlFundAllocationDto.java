/*
 * 文件名：PtlDvpFundAllocationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlchlfundallocation.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;

/**
 * 通道资金划拨dto
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlChlFundAllocationDto
 * @since 1.0
 */
public class PtlChlFundAllocationDto extends StlPayOrders {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1364417242674282995L;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 实体组合代码
     */
    private String actPortfolioId;

    /**
     * 节点名称
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 组合现金流水
     */
    private List<PtlCashPositionFlow> ptlCashPositionFlow;

    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 金融产品交易
     */
    private TrdProductDeal trdProductDeal;

    /**
     * 金融产品交易附属
     */
    private TrdProductDealAdd trdProductDealAdd;
    
    /**
     * 金融产品交易扩展表
     */
    private PaTrdProductDeal paTrdProductDeal;

    /**
     * 组合现金余额
     */
    private List<PtlCashPosition> ptlCashPosition;

    /**
     * 划款指令表
     */
    private List<StlPayOrdersDto> stlPayOrdersList;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 通道名称
     */
    private String chlName;

    /**
     * 本金
     */
    private BigDecimal prinAmt;

    /**
     * 收益
     */
    private BigDecimal intAmt;

    /**
     * 获取 prinAmt
     * 
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     * 
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 stlPayOrdersList
     * 
     * @return stlPayOrdersList.
     */
    public List<StlPayOrdersDto> getStlPayOrdersList() {
        return stlPayOrdersList;
    }

    /**
     * 设置 stlPayOrdersList
     * 
     * @param stlPayOrdersList stlPayOrdersList
     */
    public void setStlPayOrdersList(List<StlPayOrdersDto> stlPayOrdersList) {
        this.stlPayOrdersList = stlPayOrdersList;
    }

    /**
     * 获取 ptlCashPositionFlow
     * 
     * @return ptlCashPositionFlow.
     */
    public List<PtlCashPositionFlow> getPtlCashPositionFlow() {
        return ptlCashPositionFlow;
    }

    /**
     * 设置 ptlCashPositionFlow
     * 
     * @param ptlCashPositionFlow ptlCashPositionFlow
     */
    public void setPtlCashPositionFlow(List<PtlCashPositionFlow> ptlCashPositionFlow) {
        this.ptlCashPositionFlow = ptlCashPositionFlow;
    }

    /**
     * 获取 ptlTrade
     * 
     * @return ptlTrade.
     */
    public List<PtlTrade> getPtlTrade() {
        return ptlTrade;
    }

    /**
     * 设置 ptlTrade
     * 
     * @param ptlTrade ptlTrade
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 trdProductDeal
     * 
     * @return trdProductDeal.
     */
    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    /**
     * 设置 trdProductDeal
     * 
     * @param trdProductDeal trdProductDeal
     */
    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    /**
     * 获取 ptlCashPosition
     * 
     * @return ptlCashPosition.
     */
    public List<PtlCashPosition> getPtlCashPosition() {
        return ptlCashPosition;
    }

    /**
     * 设置 ptlCashPosition
     * 
     * @param ptlCashPosition ptlCashPosition
     */
    public void setPtlCashPosition(List<PtlCashPosition> ptlCashPosition) {
        this.ptlCashPosition = ptlCashPosition;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 trdProductDealAdd
     * 
     * @return trdProductDealAdd.
     */
    public TrdProductDealAdd getTrdProductDealAdd() {
        return trdProductDealAdd;
    }

    /**
     * 设置 trdProductDealAdd
     * 
     * @param trdProductDealAdd trdProductDealAdd
     */
    public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
        this.trdProductDealAdd = trdProductDealAdd;
    }

    /**
     * 获取 revokeReason
     * 
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     * 
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 chlId
     * 
     * @return chlId.
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 chlId
     * 
     * @param chlId chlId
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 chlName
     * 
     * @return chlName.
     */
    public String getChlName() {
        return chlName;
    }

    /**
     * 设置 chlName
     * 
     * @param chlName chlName
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

    /**
     * 获取 paTrdProductDeal
     * @return paTrdProductDeal.
     */
    public PaTrdProductDeal getPaTrdProductDeal() {
        return paTrdProductDeal;
    }

    /**
     * 设置 paTrdProductDeal
     * @param paTrdProductDeal paTrdProductDeal
     */
    public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
        this.paTrdProductDeal = paTrdProductDeal;
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
