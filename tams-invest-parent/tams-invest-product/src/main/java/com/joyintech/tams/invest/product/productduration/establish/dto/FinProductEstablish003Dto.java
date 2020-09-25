/*
 * 文件名：FinProductEstablish002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.establish.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 收益型产品认申购新建
 * 
 * @author zhangliang
 * @version 1.0 2018年12月25日
 * @see FinProductEstablish002Dto
 * @since 1.0
 */
@PageId("FinProductEstablish003")
public class FinProductEstablish003Dto extends TrdFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5562418617863995085L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 产品名称
     */
    private String finprodName;
    
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 是否分层
     */
    private String isLay;
    
    /**
     * 组合代码
     */
    private String ptlId;

    /**
     * 产品成立日
     */
    private Date vdate;
    
    /**
     * 产品到期日
     */
    private Date mdate;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 是否保本
     */
    private String isGuarantee;
    
    /**
     * 本期认购
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currentSub;

    /**
     * 上期余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastAmt;

    /**
     * 上期赎回
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastRed;

    /**
     * 上期滚入金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastAmtSub;

    /**
     * 到账资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accountingAmt;

    /**
     * 分层金额明细
     */
    @Valid
    private transient List<FinProductEstablishDealTotal> finProductEstablishDealTotalList;

    /**
     * 划款指令
     */
    private StlPayOrders stlPayOrders;
    
    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 交易备注
     */
    private String remark;

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
     * 获取 ptlId
     * @return ptlId.
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 currentSub
     * @return currentSub.
     */
    public BigDecimal getCurrentSub() {
        return currentSub;
    }

    /**
     * 设置 currentSub
     * @param currentSub currentSub
     */
    public void setCurrentSub(BigDecimal currentSub) {
        this.currentSub = currentSub;
    }

    /**
     * 获取 lastAmt
     * @return lastAmt.
     */
    public BigDecimal getLastAmt() {
        return lastAmt;
    }

    /**
     * 设置 lastAmt
     * @param lastAmt lastAmt
     */
    public void setLastAmt(BigDecimal lastAmt) {
        this.lastAmt = lastAmt;
    }

    /**
     * 获取 lastRed
     * @return lastRed.
     */
    public BigDecimal getLastRed() {
        return lastRed;
    }

    /**
     * 设置 lastRed
     * @param lastRed lastRed
     */
    public void setLastRed(BigDecimal lastRed) {
        this.lastRed = lastRed;
    }

    /**
     * 获取 lastAmtSub
     * @return lastAmtSub.
     */
    public BigDecimal getLastAmtSub() {
        return lastAmtSub;
    }

    /**
     * 设置 lastAmtSub
     * @param lastAmtSub lastAmtSub
     */
    public void setLastAmtSub(BigDecimal lastAmtSub) {
        this.lastAmtSub = lastAmtSub;
    }

    /**
     * 获取 accountingAmt
     * @return accountingAmt.
     */
    public BigDecimal getAccountingAmt() {
        return accountingAmt;
    }

    /**
     * 设置 accountingAmt
     * @param accountingAmt accountingAmt
     */
    public void setAccountingAmt(BigDecimal accountingAmt) {
        this.accountingAmt = accountingAmt;
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
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 finProductEstablishDealTotalList
     * @return finProductEstablishDealTotalList.
     */
    public List<FinProductEstablishDealTotal> getFinProductEstablishDealTotalList() {
        return finProductEstablishDealTotalList;
    }

    /**
     * 设置 finProductEstablishDealTotalList
     * @param finProductEstablishDealTotalList finProductEstablishDealTotalList
     */
    public void setFinProductEstablishDealTotalList(
            List<FinProductEstablishDealTotal> finProductEstablishDealTotalList) {
        this.finProductEstablishDealTotalList = finProductEstablishDealTotalList;
    }

    /**
     * 获取 bussTrdTransferOrder
     * 
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     * 
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 isGuarantee
     * 
     * @return isGuarantee.
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置 isGuarantee
     * 
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
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
     * 获取 tradeId
     * 
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 tradeId
     * 
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取交易类型
     * 
     * @return 交易类型
     */
    @PrimaryKey(Field.PK_2)
    public String getDealType() {
        return DictCodeList.共通业务类型_收益型产品认申购管理.getValue();
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    @PrimaryKey(Field.PK_3)
    public String getVdateStr() {
        return DateUtil.format(this.getVdate());
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 stlPayOrders
     * @return stlPayOrders.
     */
    public StlPayOrders getStlPayOrders() {
        return stlPayOrders;
    }

    /**
     * 设置 stlPayOrders
     * @param stlPayOrders stlPayOrders
     */
    public void setStlPayOrders(StlPayOrders stlPayOrders) {
        this.stlPayOrders = stlPayOrders;
    }

    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

}
