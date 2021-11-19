/*
 * 文件名：CfetsBondDealDialogueQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.bankuplink.bond.dto;

import com.joyintech.tams.common.base.entity.RegFinProductDealTotal;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 对话报价(登记)
 */
public class CfetsBondDealRegisterQuoteDto extends RegFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6517441367865558290L;
    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 要素id
     */
    private String elementId;
    /**
     * 汇总登记编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String totleTradeId;

    /**
     *实际行权日
     */
    private Date actSetteDate;

    /**
     * 交易手续费
     */
    private BigDecimal  dealFeeAmt;

    /**
     * 结算服务费
     */
    private BigDecimal  settFeeamt;
    /**
     * 组合分配信息
     */
    private List<PtlAllocationDto> ptlAllocatios;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public String getTotleTradeId() {
        return totleTradeId;
    }

    @Override
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public Date getActSetteDate() {
        return actSetteDate;
    }

    public void setActSetteDate(Date actSetteDate) {
        this.actSetteDate = actSetteDate;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public BigDecimal getSettFeeamt() {
        return settFeeamt;
    }

    public void setSettFeeamt(BigDecimal settFeeamt) {
        this.settFeeamt = settFeeamt;
    }

    public List<PtlAllocationDto> getPtlAllocatios() {
        return ptlAllocatios;
    }

    public void setPtlAllocatios(List<PtlAllocationDto> ptlAllocatios) {
        this.ptlAllocatios = ptlAllocatios;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
}
