/*
 * 文件名：FinProductPayInt002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.validator.FinProductPayInt002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品中间付息确认用dto
 *
 * @author etliu
 * @version 1.0 2019年1月6日
 * @see FinProductPayInt002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = FinProductPayInt002DtoValidator.class)
public class FinProductPayInt002Dto extends FinProductPayInt001Dto implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -9127258249482796931L;

    /**
     * 获取 finprodId
     *
     * @return finprodId.
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    // /**
    // * 获取 payintEndDate
    // *
    // * @return payintEndDate.
    // */
    // @Override
    // @PrimaryKey(PrimaryKey.Field.PK_3)
    // public Date getPayintEndDate() {
    // return super.getPayintEndDate();
    // }

    /**
     * 业务类型
     */
    @PrimaryKey(PrimaryKey.Field.PK_2)
    private String busiType;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 付息方式
     */
    private String payIntRule;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 产品到期日
     */
    private Date prodMdate;

    /**
     * 本次计息天数
     */
    private long thisIntAccrDay;

    /**
     * 应付客户收益
     */
    private BigDecimal theoryCusProfit;

    /**
     * 实付客户收益
     */
    private BigDecimal actCusProfit;

    /**
     * 支付日
     */
    @NotEmpty(fieldName = "FinProductPayInt002.payDate")
    private Date payDate;

    /**
     * 交易备注
     */
    private String tradeRemark;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 利息明细信息数据
     */
    private List<ReduceInfoDto> intDetailDataList;

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 金融产品交易相关表dto列表
     */
    private List<FinProductPayInt002TradeDto> tradeDtoList;

    /**
     * 金融产品交易汇总Entity
     */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 金融产品交易汇总扩展表Entity
     */
    private PaTrdFinProductDealTotal paTrdFinProductDealTotal;

    /**
     * 存续期报送信息Entity
     */
    private RepDuration repDuration;

    /**
     * 组合现金流水Entity
     */
    private PtlCashPositionFlow ptlCashPositionFlow;

    /**
     * 组合持仓流水Entity
     */
    private PtlSecPositionFlow ptlSecPositionFlow;

    // 一体化对应etliu start
    /**
     * 现金流代码
     */
    private String cashId;
    // 一体化对应etliu end

    /**
     * 获取 prodName
     *
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     *
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 profitType
     *
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 payIntRule
     *
     * @return payIntRule.
     */
    public String getPayIntRule() {
        return payIntRule;
    }

    /**
     * 设置 payIntRule
     *
     * @param payIntRule payIntRule
     */
    public void setPayIntRule(String payIntRule) {
        this.payIntRule = payIntRule;
    }

    /**
     * 获取 prodVdate
     *
     * @return prodVdate.
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 prodVdate
     *
     * @param prodVdate prodVdate
     */
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }

    /**
     * 获取 prodMdate
     *
     * @return prodMdate.
     */
    public Date getProdMdate() {
        return prodMdate;
    }

    /**
     * 设置 prodMdate
     *
     * @param prodMdate prodMdate
     */
    public void setProdMdate(Date prodMdate) {
        this.prodMdate = prodMdate;
    }

    /**
     * 获取 thisIntAccrDay
     *
     * @return thisIntAccrDay.
     */
    public long getThisIntAccrDay() {
        return thisIntAccrDay;
    }

    /**
     * 设置 thisIntAccrDay
     *
     * @param thisIntAccrDay thisIntAccrDay
     */
    public void setThisIntAccrDay(long thisIntAccrDay) {
        this.thisIntAccrDay = thisIntAccrDay;
    }

    /**
     * 获取 theoryCusProfit
     *
     * @return theoryCusProfit.
     */
    @Override
    public BigDecimal getTheoryCusProfit() {
        return theoryCusProfit;
    }

    /**
     * 设置 theoryCusProfit
     *
     * @param theoryCusProfit theoryCusProfit
     */
    @Override
    public void setTheoryCusProfit(BigDecimal theoryCusProfit) {
        this.theoryCusProfit = theoryCusProfit;
    }

    /**
     * 获取 actCusProfit
     *
     * @return actCusProfit.
     */
    @Override
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    /**
     * 设置 actCusProfit
     *
     * @param actCusProfit actCusProfit
     */
    @Override
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    /**
     * 获取 payDate
     *
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     *
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 tradeRemark
     *
     * @return tradeRemark.
     */
    public String getTradeRemark() {
        return tradeRemark;
    }

    /**
     * 设置 tradeRemark
     *
     * @param tradeRemark tradeRemark
     */
    public void setTradeRemark(String tradeRemark) {
        this.tradeRemark = tradeRemark;
    }

    /**
     * 获取 intDetailDataList
     *
     * @return intDetailDataList.
     */
    public List<ReduceInfoDto> getIntDetailDataList() {
        return intDetailDataList;
    }

    /**
     * 设置 intDetailDataList
     *
     * @param intDetailDataList intDetailDataList
     */
    public void setIntDetailDataList(List<ReduceInfoDto> intDetailDataList) {
        this.intDetailDataList = intDetailDataList;
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
     * 获取 layType
     *
     * @return layType.
     */
    public String getLayType() {
        return layType;
    }

    /**
     * 设置 layType
     *
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    /**
     * 获取 prodRegistCode
     *
     * @return prodRegistCode.
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 prodRegistCode
     *
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
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
     * 获取 pageModel
     *
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 workflowStatus
     *
     * @return workflowStatus.
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 workflowStatus
     *
     * @param workflowStatus workflowStatus
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 获取 tradeDtoList
     *
     * @return tradeDtoList.
     */
    public List<FinProductPayInt002TradeDto> getTradeDtoList() {
        return tradeDtoList;
    }

    /**
     * 设置 tradeDtoList
     *
     * @param tradeDtoList tradeDtoList
     */
    public void setTradeDtoList(List<FinProductPayInt002TradeDto> tradeDtoList) {
        this.tradeDtoList = tradeDtoList;
    }

    /**
     * 获取 trdFinProductDealTotal
     *
     * @return trdFinProductDealTotal.
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 trdFinProductDealTotal
     *
     * @param trdFinProductDealTotal trdFinProductDealTotal
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 获取 paTrdFinProductDealTotal
     *
     * @return paTrdFinProductDealTotal.
     */
    public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
        return paTrdFinProductDealTotal;
    }

    /**
     * 设置 paTrdFinProductDealTotal
     *
     * @param paTrdFinProductDealTotal paTrdFinProductDealTotal
     */
    public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
        this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
    }

    /**
     * 获取 repDuration
     *
     * @return repDuration.
     */
    public RepDuration getRepDuration() {
        return repDuration;
    }

    /**
     * 设置 repDuration
     *
     * @param repDuration repDuration
     */
    public void setRepDuration(RepDuration repDuration) {
        this.repDuration = repDuration;
    }

    /**
     * 获取 ptlCashPositionFlow
     *
     * @return ptlCashPositionFlow.
     */
    public PtlCashPositionFlow getPtlCashPositionFlow() {
        return ptlCashPositionFlow;
    }

    /**
     * 设置 ptlCashPositionFlow
     *
     * @param ptlCashPositionFlow ptlCashPositionFlow
     */
    public void setPtlCashPositionFlow(PtlCashPositionFlow ptlCashPositionFlow) {
        this.ptlCashPositionFlow = ptlCashPositionFlow;
    }

    /**
     * 获取 ptlSecPositionFlow
     *
     * @return ptlSecPositionFlow.
     */
    public PtlSecPositionFlow getPtlSecPositionFlow() {
        return ptlSecPositionFlow;
    }

    /**
     * 设置 ptlSecPositionFlow
     *
     * @param ptlSecPositionFlow ptlSecPositionFlow
     */
    public void setPtlSecPositionFlow(PtlSecPositionFlow ptlSecPositionFlow) {
        this.ptlSecPositionFlow = ptlSecPositionFlow;
    }

    /**
     * 获取 busiType
     *
     * @return busiType.
     */
    public String getBusiType() {
        busiType = DictCodeList.共通业务类型_收益型产品中间付息管理.getValue();
        return busiType;
    }

    /**
     * 设置 busiType
     *
     * @param busiType busiType
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    /**
     * 获取 payintEndDateStr
     * 
     * @return payintEndDateStr.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPayintEndDateStr() {
        return DateUtil.format(getPayintEndDate());
    }
    /**
     * 获取 PayintEndDateStr
     * 
     * @return PayintEndDateStr.
     */
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPayintStartDateStr() {
        return DateUtil.format(getPayintStartDate());
    }

    /**
     * 获取 cashId
     * 
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * 
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    /**
     * 组合交易 add by mhw 20190610
     */
    private PtlTrade ptlTrade;

	public PtlTrade getPtlTrade() {
		return ptlTrade;
	}

	public void setPtlTrade(PtlTrade ptlTrade) {
		this.ptlTrade = ptlTrade;
	}
    
}
