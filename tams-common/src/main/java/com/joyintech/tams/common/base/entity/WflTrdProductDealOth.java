package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class WflTrdProductDealOth extends WflTrdProductDealOthKey {
    /**
     * 计息基础，回购拆借的计息基础
     */
    private String basis;

    /**
     * 利率，回购拆借的利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 期限(天)
     */
    private Long term;

    /**
     * 到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mTradeAmt;

    /**
     * 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    private String repoType;

    /**
     * 拆借类型，普通拆借、特殊目的载体
     */
    private String dlType;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 标的金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 标的金融产品类型
     */
    private String pledgeFinprodType;

    /**
     * 标的券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue;

    /**
     * 外汇交易方向
     */
    private String excPs;

    /**
     * 掉期期限类型
     */
    private String excFxsTermType;

    /**
     * 远端到期日
     */
    private Date fMdate;

    /**
     * 远端掉期点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fwdPoint;

    /**
     * 远端全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fwdFprice;

    /**
     * 远端交易货币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fDealAmt;

    /**
     * 远端对应货币金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fCorAmt;

    /**
     * 远端折美元金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fUsdAmt;

    /**
     * 交易货币
     */
    private String dealCur;

    /**
     * 互换方式
     */
    private String irsType;

    /**
     * 行情代码
     */
    private String intRateId;

    /**
     * 固定利率付息频率
     */
    private String payCycleR;

    /**
     * 固定利率首次支付日
     */
    private Date firstPayDateR;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRateF;

    /**
     * 浮动利率付息频率
     */
    private String payCycleF;

    /**
     * 浮动利率首次支付日
     */
    private Date firstPayDateF;

    /**
     * 取得 计息基础，回购拆借的计息基础
     * @return 计息基础，回购拆借的计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础，回购拆借的计息基础
     * @param basis 计息基础，回购拆借的计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 利率，回购拆借的利率
     * @return 利率，回购拆借的利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率，回购拆借的利率
     * @param rate 利率，回购拆借的利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 期限(天)
     * @return 期限(天)
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     * @param term 期限(天)
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 取得 到期金额
     * @return 到期金额
     */
    public BigDecimal getmTradeAmt() {
        return mTradeAmt;
    }

    /**
     * 设置 到期金额
     * @param mTradeAmt 到期金额
     */
    public void setmTradeAmt(BigDecimal mTradeAmt) {
        this.mTradeAmt = mTradeAmt;
    }

    /**
     * 取得 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     * @return 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    public String getRepoType() {
        return repoType;
    }

    /**
     * 设置 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     * @param repoType 回购类型，质押式协议回购、质押式竞价回购、买断式回购
     */
    public void setRepoType(String repoType) {
        this.repoType = repoType == null ? null : repoType.trim();
    }

    /**
     * 取得 拆借类型，普通拆借、特殊目的载体
     * @return 拆借类型，普通拆借、特殊目的载体
     */
    public String getDlType() {
        return dlType;
    }

    /**
     * 设置 拆借类型，普通拆借、特殊目的载体
     * @param dlType 拆借类型，普通拆借、特殊目的载体
     */
    public void setDlType(String dlType) {
        this.dlType = dlType == null ? null : dlType.trim();
    }

    /**
     * 取得 期限品种
     * @return 期限品种
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 标的金融产品代码
     * @return 标的金融产品代码
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 标的金融产品代码
     * @param pledgeFinprodId 标的金融产品代码
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId == null ? null : pledgeFinprodId.trim();
    }

    /**
     * 取得 标的金融产品类型
     * @return 标的金融产品类型
     */
    public String getPledgeFinprodType() {
        return pledgeFinprodType;
    }

    /**
     * 设置 标的金融产品类型
     * @param pledgeFinprodType 标的金融产品类型
     */
    public void setPledgeFinprodType(String pledgeFinprodType) {
        this.pledgeFinprodType = pledgeFinprodType == null ? null : pledgeFinprodType.trim();
    }

    /**
     * 取得 标的券面总额
     * @return 标的券面总额
     */
    public BigDecimal getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    /**
     * 设置 标的券面总额
     * @param pledgeFaceValue 标的券面总额
     */
    public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    /**
     * 取得 外汇交易方向
     * @return 外汇交易方向
     */
    public String getExcPs() {
        return excPs;
    }

    /**
     * 设置 外汇交易方向
     * @param excPs 外汇交易方向
     */
    public void setExcPs(String excPs) {
        this.excPs = excPs == null ? null : excPs.trim();
    }

    /**
     * 取得 掉期期限类型
     * @return 掉期期限类型
     */
    public String getExcFxsTermType() {
        return excFxsTermType;
    }

    /**
     * 设置 掉期期限类型
     * @param excFxsTermType 掉期期限类型
     */
    public void setExcFxsTermType(String excFxsTermType) {
        this.excFxsTermType = excFxsTermType == null ? null : excFxsTermType.trim();
    }

    /**
     * 取得 远端到期日
     * @return 远端到期日
     */
    public Date getfMdate() {
        return fMdate;
    }

    /**
     * 设置 远端到期日
     * @param fMdate 远端到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setfMdate(Date fMdate) {
        this.fMdate = fMdate;
    }

    /**
     * 取得 远端掉期点
     * @return 远端掉期点
     */
    public BigDecimal getFwdPoint() {
        return fwdPoint;
    }

    /**
     * 设置 远端掉期点
     * @param fwdPoint 远端掉期点
     */
    public void setFwdPoint(BigDecimal fwdPoint) {
        this.fwdPoint = fwdPoint;
    }

    /**
     * 取得 远端全价
     * @return 远端全价
     */
    public BigDecimal getFwdFprice() {
        return fwdFprice;
    }

    /**
     * 设置 远端全价
     * @param fwdFprice 远端全价
     */
    public void setFwdFprice(BigDecimal fwdFprice) {
        this.fwdFprice = fwdFprice;
    }

    /**
     * 取得 远端交易货币金额
     * @return 远端交易货币金额
     */
    public BigDecimal getfDealAmt() {
        return fDealAmt;
    }

    /**
     * 设置 远端交易货币金额
     * @param fDealAmt 远端交易货币金额
     */
    public void setfDealAmt(BigDecimal fDealAmt) {
        this.fDealAmt = fDealAmt;
    }

    /**
     * 取得 远端对应货币金额
     * @return 远端对应货币金额
     */
    public BigDecimal getfCorAmt() {
        return fCorAmt;
    }

    /**
     * 设置 远端对应货币金额
     * @param fCorAmt 远端对应货币金额
     */
    public void setfCorAmt(BigDecimal fCorAmt) {
        this.fCorAmt = fCorAmt;
    }

    /**
     * 取得 远端折美元金额
     * @return 远端折美元金额
     */
    public BigDecimal getfUsdAmt() {
        return fUsdAmt;
    }

    /**
     * 设置 远端折美元金额
     * @param fUsdAmt 远端折美元金额
     */
    public void setfUsdAmt(BigDecimal fUsdAmt) {
        this.fUsdAmt = fUsdAmt;
    }

    /**
     * 取得 交易货币
     * @return 交易货币
     */
    public String getDealCur() {
        return dealCur;
    }

    /**
     * 设置 交易货币
     * @param dealCur 交易货币
     */
    public void setDealCur(String dealCur) {
        this.dealCur = dealCur == null ? null : dealCur.trim();
    }

    /**
     * 取得 互换方式
     * @return 互换方式
     */
    public String getIrsType() {
        return irsType;
    }

    /**
     * 设置 互换方式
     * @param irsType 互换方式
     */
    public void setIrsType(String irsType) {
        this.irsType = irsType == null ? null : irsType.trim();
    }

    /**
     * 取得 行情代码
     * @return 行情代码
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 行情代码
     * @param intRateId 行情代码
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 固定利率付息频率
     * @return 固定利率付息频率
     */
    public String getPayCycleR() {
        return payCycleR;
    }

    /**
     * 设置 固定利率付息频率
     * @param payCycleR 固定利率付息频率
     */
    public void setPayCycleR(String payCycleR) {
        this.payCycleR = payCycleR == null ? null : payCycleR.trim();
    }

    /**
     * 取得 固定利率首次支付日
     * @return 固定利率首次支付日
     */
    public Date getFirstPayDateR() {
        return firstPayDateR;
    }

    /**
     * 设置 固定利率首次支付日
     * @param firstPayDateR 固定利率首次支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstPayDateR(Date firstPayDateR) {
        this.firstPayDateR = firstPayDateR;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRateF() {
        return spreadRateF;
    }

    /**
     * 设置 利差
     * @param spreadRateF 利差
     */
    public void setSpreadRateF(BigDecimal spreadRateF) {
        this.spreadRateF = spreadRateF;
    }

    /**
     * 取得 浮动利率付息频率
     * @return 浮动利率付息频率
     */
    public String getPayCycleF() {
        return payCycleF;
    }

    /**
     * 设置 浮动利率付息频率
     * @param payCycleF 浮动利率付息频率
     */
    public void setPayCycleF(String payCycleF) {
        this.payCycleF = payCycleF == null ? null : payCycleF.trim();
    }

    /**
     * 取得 浮动利率首次支付日
     * @return 浮动利率首次支付日
     */
    public Date getFirstPayDateF() {
        return firstPayDateF;
    }

    /**
     * 设置 浮动利率首次支付日
     * @param firstPayDateF 浮动利率首次支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstPayDateF(Date firstPayDateF) {
        this.firstPayDateF = firstPayDateF;
    }
}