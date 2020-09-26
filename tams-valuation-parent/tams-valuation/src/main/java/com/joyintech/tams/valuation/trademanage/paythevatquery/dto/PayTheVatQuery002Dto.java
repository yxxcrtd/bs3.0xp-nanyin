/*
 * 文件名：PayTheVatQuery002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.paythevatquery.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.trademanage.paythevatquery.validator.PayTheVatQuery002CheckValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 纳税增值税新建
 * 
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Dto
 * @since 1.0
 */
@SuppressWarnings("serial")
@ValidLogic(targetClass = PayTheVatQuery002CheckValidator.class)
public class PayTheVatQuery002Dto extends BaseEntity implements ProcessEntity {

    /** 交易编号 */
    
    private String tradeId;
    /**
     * 产品代码
     */
    @PrimaryKey(value=Field.PK_1)
    private String booksetId;

    /**
     * 产品名称
     */
    private String booksetName;

    /**
     * 产品开始日
     */
    private Date startDate;

    /**
     * 缴税开始日
     */
    private String taxStartDate;

    /**
     * 退税开始日
     */
    private String taxRefundStartDate;

    /**
     * 利息收入
     */
    private BigDecimal interestIncome;

    /**
     * 红利收入
     */
    private BigDecimal dividendIncome;

    /**
     * 价差收入
     */
    private BigDecimal difbetIncome;

    /**
     * 增值税
     */
    private BigDecimal appreciationTax;

    /**
     * 城建税
     */
    private BigDecimal constructionTax;

    /**
     * 教育费
     */
    private BigDecimal educationTax;

    /**
     * 地方教育费
     */
    private BigDecimal placeeducationTax;

    /**
     * 增值税附加
     */
    private BigDecimal additionalTax;

    /**
     * 汇总行
     */
    private BigDecimal sumLine;

    /**
     * 缴税/退税
     */
    @PrimaryKey(value=Field.PK_2)
    private String taxidenType;

    /**
     * 缴税/退税日期
     */
    @NotEmpty(fieldName="PayTheVatQuery002.payment2")
    @PrimaryKey(value=Field.PK_3)
    private String payment2;

    /**
     * 缴税/退税截止日期
     */
    @PrimaryKey(value=Field.PK_4)
    private String payment1;

    /**
     * 缴税退税日期
     */
    private String paymentStart;

    /**
     * 缴税日期
     */
    private String payTaxVDate;

    /**
     * 退税日期
     */
    private String refundTaxVDate;

    /**
     * 退税截止日期
     */
    private String payTaxMDate;

    /**
     * 退税截止日期
     */
    private String refundTaxMDate;

    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;

    /** 费用金额 */
    private BigDecimal feeAmt;

    /** 费用类型 */
    private String feeType;

    

    /** 业务类型 */
    private String busiType;

    /** 金融产品类型 */
    private String finprodType;

    /** 金融产品代码 */
    private String finprodId;

    /** 交易币种 */
    private String cny;

    /** 现金流代码 */
    private String cashId;

    /** 税金额 */
    private BigDecimal tradeAmt;

    /** 账务明细代码 */
    private String bokDetailid;

    /** 指令编号生成 */
    private String orderId;

    /** 本方资金账户 */
    private String acctNo;

    /** 投资组合代码 */
    private String portfolioId;

    /** 理财产品代码 */
    private String prodId;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 指令类型
     */
    private String orderType;

    /**
     * 缴税/退税明细数据
     */
    private List<PayTheVatQuery002TableDto> listpayTheVatQuery002TableDto;
    
    /**
     * 获取 busiType
     * 
     * @return busiType.
     */
    public String getBusiType() {
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
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
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
     * 获取 cny
     * 
     * @return cny.
     */
    public String getCny() {
        return cny;
    }

    /**
     * 设置 cny
     * 
     * @param cny cny
     */
    public void setCny(String cny) {
        this.cny = cny;
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
     * 获取 tradeAmt
     * 
     * @return tradeAmt.
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 tradeAmt
     * 
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 bokDetailid
     * 
     * @return bokDetailid.
     */
    public String getBokDetailid() {
        return bokDetailid;
    }

    /**
     * 设置 bokDetailid
     * 
     * @param bokDetailid bokDetailid
     */
    public void setBokDetailid(String bokDetailid) {
        this.bokDetailid = bokDetailid;
    }

    /**
     * 获取 orderId
     * 
     * @return orderId.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 orderId
     * 
     * @param orderId orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 acctNo
     * 
     * @return acctNo.
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 acctNo
     * 
     * @param acctNo acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 prodId
     * 
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     * 
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 acctSubjectId
     * 
     * @return acctSubjectId.
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 acctSubjectId
     * 
     * @param acctSubjectId acctSubjectId
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }

    /**
     * 获取 payment1
     * 
     * @return payment1.
     */
    public String getPayment1() {
        return payment1;
    }

    /**
     * 设置 payment1
     * 
     * @param payment1 payment1
     */
    public void setPayment1(String payment1) {
        this.payment1 = payment1;
    }

    /**
     * 获取 payTaxVDate
     * 
     * @return payTaxVDate.
     */
    public String getPayTaxVDate() {
        return payTaxVDate;
    }

    /**
     * 设置 payTaxVDate
     * 
     * @param payTaxVDate payTaxVDate
     */
    public void setPayTaxVDate(String payTaxVDate) {
        this.payTaxVDate = payTaxVDate;
    }

    /**
     * 获取 refundTaxVDate
     * 
     * @return refundTaxVDate.
     */
    public String getRefundTaxVDate() {
        return refundTaxVDate;
    }

    /**
     * 设置 refundTaxVDate
     * 
     * @param refundTaxVDate refundTaxVDate
     */
    public void setRefundTaxVDate(String refundTaxVDate) {
        this.refundTaxVDate = refundTaxVDate;
    }

    /**
     * 获取 payTaxMDate
     * 
     * @return payTaxMDate.
     */
    public String getPayTaxMDate() {
        return payTaxMDate;
    }

    /**
     * 设置 payTaxMDate
     * 
     * @param payTaxMDate payTaxMDate
     */
    public void setPayTaxMDate(String payTaxMDate) {
        this.payTaxMDate = payTaxMDate;
    }

    /**
     * 获取 refundTaxMDate
     * 
     * @return refundTaxMDate.
     */
    public String getRefundTaxMDate() {
        return refundTaxMDate;
    }

    /**
     * 设置 refundTaxMDate
     * 
     * @param refundTaxMDate refundTaxMDate
     */
    public void setRefundTaxMDate(String refundTaxMDate) {
        this.refundTaxMDate = refundTaxMDate;
    }

    /**
     * 获取 acctSubjectType
     * 
     * @return acctSubjectType.
     */
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    /**
     * 设置 acctSubjectType
     * 
     * @param acctSubjectType acctSubjectType
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    /**
     * 获取 standardCcy
     * 
     * @return standardCcy.
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 standardCcy
     * 
     * @param standardCcy standardCcy
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 booksetName
     * 
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 booksetName
     * 
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 startDate
     * 
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 startDate
     * 
     * @param startDate startDate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 taxStartDate
     * 
     * @return taxStartDate.
     */
    public String getTaxStartDate() {
        return taxStartDate;
    }

    /**
     * 设置 taxStartDate
     * 
     * @param taxStartDate taxStartDate
     */
    public void setTaxStartDate(String taxStartDate) {
        this.taxStartDate = taxStartDate;
    }

    /**
     * 获取 taxRefundStartDate
     * 
     * @return taxRefundStartDate.
     */
    public String getTaxRefundStartDate() {
        return taxRefundStartDate;
    }

    /**
     * 设置 taxRefundStartDate
     * 
     * @param taxRefundStartDate taxRefundStartDate
     */
    public void setTaxRefundStartDate(String taxRefundStartDate) {
        this.taxRefundStartDate = taxRefundStartDate;
    }

    /**
     * 获取 interestIncome
     * 
     * @return interestIncome.
     */
    public BigDecimal getInterestIncome() {
        return interestIncome;
    }

    /**
     * 设置 interestIncome
     * 
     * @param interestIncome interestIncome
     */
    public void setInterestIncome(BigDecimal interestIncome) {
        this.interestIncome = interestIncome;
    }

    /**
     * 获取 dividendIncome
     * 
     * @return dividendIncome.
     */
    public BigDecimal getDividendIncome() {
        return dividendIncome;
    }

    /**
     * 设置 dividendIncome
     * 
     * @param dividendIncome dividendIncome
     */
    public void setDividendIncome(BigDecimal dividendIncome) {
        this.dividendIncome = dividendIncome;
    }

    /**
     * 获取 difbetIncome
     * 
     * @return difbetIncome.
     */
    public BigDecimal getDifbetIncome() {
        return difbetIncome;
    }

    /**
     * 设置 difbetIncome
     * 
     * @param difbetIncome difbetIncome
     */
    public void setDifbetIncome(BigDecimal difbetIncome) {
        this.difbetIncome = difbetIncome;
    }

    /**
     * 获取 appreciationTax
     * 
     * @return appreciationTax.
     */
    public BigDecimal getAppreciationTax() {
        return appreciationTax;
    }

    /**
     * 设置 appreciationTax
     * 
     * @param appreciationTax appreciationTax
     */
    public void setAppreciationTax(BigDecimal appreciationTax) {
        this.appreciationTax = appreciationTax;
    }

    /**
     * 获取 constructionTax
     * 
     * @return constructionTax.
     */
    public BigDecimal getConstructionTax() {
        return constructionTax;
    }

    /**
     * 设置 constructionTax
     * 
     * @param constructionTax constructionTax
     */
    public void setConstructionTax(BigDecimal constructionTax) {
        this.constructionTax = constructionTax;
    }

    /**
     * 获取 educationTax
     * 
     * @return educationTax.
     */
    public BigDecimal getEducationTax() {
        return educationTax;
    }

    /**
     * 设置 educationTax
     * 
     * @param educationTax educationTax
     */
    public void setEducationTax(BigDecimal educationTax) {
        this.educationTax = educationTax;
    }

    /**
     * 获取 placeeducationTax
     * 
     * @return placeeducationTax.
     */
    public BigDecimal getPlaceeducationTax() {
        return placeeducationTax;
    }

    /**
     * 设置 placeeducationTax
     * 
     * @param placeeducationTax placeeducationTax
     */
    public void setPlaceeducationTax(BigDecimal placeeducationTax) {
        this.placeeducationTax = placeeducationTax;
    }

    /**
     * 获取 additionalTax
     * 
     * @return additionalTax.
     */
    public BigDecimal getAdditionalTax() {
        return additionalTax;
    }

    /**
     * 设置 additionalTax
     * 
     * @param additionalTax additionalTax
     */
    public void setAdditionalTax(BigDecimal additionalTax) {
        this.additionalTax = additionalTax;
    }

    /**
     * 获取 sumLine
     * 
     * @return sumLine.
     */
    public BigDecimal getSumLine() {
        return sumLine;
    }

    /**
     * 设置 sumLine
     * 
     * @param sumLine sumLine
     */
    public void setSumLine(BigDecimal sumLine) {
        this.sumLine = sumLine;
    }

    /**
     * 获取 taxidenType
     * 
     * @return taxidenType.
     */
    public String getTaxidenType() {
        return taxidenType;
    }

    /**
     * 设置 taxidenType
     * 
     * @param taxidenType taxidenType
     */
    public void setTaxidenType(String taxidenType) {
        this.taxidenType = taxidenType;
    }

    /**
     * 获取 payment2
     * 
     * @return payment2.
     */
    public String getPayment2() {
        return payment2;
    }

    /**
     * 设置 payment2
     * 
     * @param payment2 payment2
     */
    public void setPayment2(String payment2) {
        this.payment2 = payment2;
    }

    /**
     * 获取 listpayTheVatQuery002TableDto
     * 
     * @return listpayTheVatQuery002TableDto.
     */
    public List<PayTheVatQuery002TableDto> getListpayTheVatQuery002TableDto() {
        return listpayTheVatQuery002TableDto;
    }

    /**
     * 设置 listpayTheVatQuery002TableDto
     * 
     * @param listpayTheVatQuery002TableDto listpayTheVatQuery002TableDto
     */
    public void setListpayTheVatQuery002TableDto(List<PayTheVatQuery002TableDto> listpayTheVatQuery002TableDto) {
        this.listpayTheVatQuery002TableDto = listpayTheVatQuery002TableDto;
    }

    /**
     * 获取 feeAmt
     * 
     * @return feeAmt.
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 feeAmt
     * 
     * @param feeAmt feeAmt
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 获取 feeType
     * 
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 feeType
     * 
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
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
     * 获取 tradeStatus
     * 
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 tradeStatus
     * 
     * @param tradeStatus tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取 orderType
     * 
     * @return orderType.
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置 orderType
     * 
     * @param orderType orderType
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取 paymentStart
     * 
     * @return paymentStart.
     */
    public String getPaymentStart() {
        return paymentStart;
    }

    /**
     * 设置 paymentStart
     * 
     * @param paymentStart paymentStart
     */
    public void setPaymentStart(String paymentStart) {
        this.paymentStart = paymentStart;
    }

}
