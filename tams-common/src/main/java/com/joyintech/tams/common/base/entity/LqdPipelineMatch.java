package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class LqdPipelineMatch extends BaseEntity {
    /**
     * 匹配流水号
     */
    private String matchSeqno;

    /**
     * 匹配组编号
     */
    private String groupId;

    /**
     * 匹配备注
     */
    private String remark;

    /**
     * 托管流水号
     */
    private String truSeqNo;

    /**
     * 托管流水账号
     */
    private String truAccount;

    /**
     * 托管流水借贷方向
     */
    private String truPs;

    /**
     * 托管流水金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal truPrinamt;

    /**
     * 托管流水对手账户
     */
    private String truSeller;

    /**
     * 托管流水对手账户名称
     */
    private String truSellername;

    /**
     * 托管流水用途
     */
    private String truUsage;

    /**
     * 系统流水业务编号
     */
    private String sysTradeId;

    /**
     * 系统流水是否汇总交易
     */
    private String sysIsTotalTrade;

    /**
     * 系统流水现金流日期
     */
    private Date sysCdate;

    /**
     * 系统流水业务类型
     */
    private String sysBussType;

    /**
     * 系统流水组合代码
     */
    private String sysPortfolioId;

    /**
     * 系统流水组合名称
     */
    private String sysPortfolioName;

    /**
     * 系统流水产品/资产代码
     */
    private String sysFinprodId;

    /**
     * 系统流水产品/资产名称
     */
    private String sysFinprodName;

    /**
     * 系统流水金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sysPrinamt;

    /**
     * 系统流水币种
     */
    private String sysCcy;

    /**
     * 取得 匹配流水号
     * @return 匹配流水号
     */
    public String getMatchSeqno() {
        return matchSeqno;
    }

    /**
     * 设置 匹配流水号
     * @param matchSeqno 匹配流水号
     */
    public void setMatchSeqno(String matchSeqno) {
        this.matchSeqno = matchSeqno == null ? null : matchSeqno.trim();
    }

    /**
     * 取得 匹配组编号
     * @return 匹配组编号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 匹配组编号
     * @param groupId 匹配组编号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 取得 匹配备注
     * @return 匹配备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 匹配备注
     * @param remark 匹配备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 托管流水号
     * @return 托管流水号
     */
    public String getTruSeqNo() {
        return truSeqNo;
    }

    /**
     * 设置 托管流水号
     * @param truSeqNo 托管流水号
     */
    public void setTruSeqNo(String truSeqNo) {
        this.truSeqNo = truSeqNo == null ? null : truSeqNo.trim();
    }

    /**
     * 取得 托管流水账号
     * @return 托管流水账号
     */
    public String getTruAccount() {
        return truAccount;
    }

    /**
     * 设置 托管流水账号
     * @param truAccount 托管流水账号
     */
    public void setTruAccount(String truAccount) {
        this.truAccount = truAccount == null ? null : truAccount.trim();
    }

    /**
     * 取得 托管流水借贷方向
     * @return 托管流水借贷方向
     */
    public String getTruPs() {
        return truPs;
    }

    /**
     * 设置 托管流水借贷方向
     * @param truPs 托管流水借贷方向
     */
    public void setTruPs(String truPs) {
        this.truPs = truPs == null ? null : truPs.trim();
    }

    /**
     * 取得 托管流水金额
     * @return 托管流水金额
     */
    public BigDecimal getTruPrinamt() {
        return truPrinamt;
    }

    /**
     * 设置 托管流水金额
     * @param truPrinamt 托管流水金额
     */
    public void setTruPrinamt(BigDecimal truPrinamt) {
        this.truPrinamt = truPrinamt;
    }

    /**
     * 取得 托管流水对手账户
     * @return 托管流水对手账户
     */
    public String getTruSeller() {
        return truSeller;
    }

    /**
     * 设置 托管流水对手账户
     * @param truSeller 托管流水对手账户
     */
    public void setTruSeller(String truSeller) {
        this.truSeller = truSeller == null ? null : truSeller.trim();
    }

    /**
     * 取得 托管流水对手账户名称
     * @return 托管流水对手账户名称
     */
    public String getTruSellername() {
        return truSellername;
    }

    /**
     * 设置 托管流水对手账户名称
     * @param truSellername 托管流水对手账户名称
     */
    public void setTruSellername(String truSellername) {
        this.truSellername = truSellername == null ? null : truSellername.trim();
    }

    /**
     * 取得 托管流水用途
     * @return 托管流水用途
     */
    public String getTruUsage() {
        return truUsage;
    }

    /**
     * 设置 托管流水用途
     * @param truUsage 托管流水用途
     */
    public void setTruUsage(String truUsage) {
        this.truUsage = truUsage == null ? null : truUsage.trim();
    }

    /**
     * 取得 系统流水业务编号
     * @return 系统流水业务编号
     */
    public String getSysTradeId() {
        return sysTradeId;
    }

    /**
     * 设置 系统流水业务编号
     * @param sysTradeId 系统流水业务编号
     */
    public void setSysTradeId(String sysTradeId) {
        this.sysTradeId = sysTradeId == null ? null : sysTradeId.trim();
    }

    /**
     * 取得 系统流水是否汇总交易
     * @return 系统流水是否汇总交易
     */
    public String getSysIsTotalTrade() {
        return sysIsTotalTrade;
    }

    /**
     * 设置 系统流水是否汇总交易
     * @param sysIsTotalTrade 系统流水是否汇总交易
     */
    public void setSysIsTotalTrade(String sysIsTotalTrade) {
        this.sysIsTotalTrade = sysIsTotalTrade == null ? null : sysIsTotalTrade.trim();
    }

    /**
     * 取得 系统流水现金流日期
     * @return 系统流水现金流日期
     */
    public Date getSysCdate() {
        return sysCdate;
    }

    /**
     * 设置 系统流水现金流日期
     * @param sysCdate 系统流水现金流日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSysCdate(Date sysCdate) {
        this.sysCdate = sysCdate;
    }

    /**
     * 取得 系统流水业务类型
     * @return 系统流水业务类型
     */
    public String getSysBussType() {
        return sysBussType;
    }

    /**
     * 设置 系统流水业务类型
     * @param sysBussType 系统流水业务类型
     */
    public void setSysBussType(String sysBussType) {
        this.sysBussType = sysBussType == null ? null : sysBussType.trim();
    }

    /**
     * 取得 系统流水组合代码
     * @return 系统流水组合代码
     */
    public String getSysPortfolioId() {
        return sysPortfolioId;
    }

    /**
     * 设置 系统流水组合代码
     * @param sysPortfolioId 系统流水组合代码
     */
    public void setSysPortfolioId(String sysPortfolioId) {
        this.sysPortfolioId = sysPortfolioId == null ? null : sysPortfolioId.trim();
    }

    /**
     * 取得 系统流水组合名称
     * @return 系统流水组合名称
     */
    public String getSysPortfolioName() {
        return sysPortfolioName;
    }

    /**
     * 设置 系统流水组合名称
     * @param sysPortfolioName 系统流水组合名称
     */
    public void setSysPortfolioName(String sysPortfolioName) {
        this.sysPortfolioName = sysPortfolioName == null ? null : sysPortfolioName.trim();
    }

    /**
     * 取得 系统流水产品/资产代码
     * @return 系统流水产品/资产代码
     */
    public String getSysFinprodId() {
        return sysFinprodId;
    }

    /**
     * 设置 系统流水产品/资产代码
     * @param sysFinprodId 系统流水产品/资产代码
     */
    public void setSysFinprodId(String sysFinprodId) {
        this.sysFinprodId = sysFinprodId == null ? null : sysFinprodId.trim();
    }

    /**
     * 取得 系统流水产品/资产名称
     * @return 系统流水产品/资产名称
     */
    public String getSysFinprodName() {
        return sysFinprodName;
    }

    /**
     * 设置 系统流水产品/资产名称
     * @param sysFinprodName 系统流水产品/资产名称
     */
    public void setSysFinprodName(String sysFinprodName) {
        this.sysFinprodName = sysFinprodName == null ? null : sysFinprodName.trim();
    }

    /**
     * 取得 系统流水金额
     * @return 系统流水金额
     */
    public BigDecimal getSysPrinamt() {
        return sysPrinamt;
    }

    /**
     * 设置 系统流水金额
     * @param sysPrinamt 系统流水金额
     */
    public void setSysPrinamt(BigDecimal sysPrinamt) {
        this.sysPrinamt = sysPrinamt;
    }

    /**
     * 取得 系统流水币种
     * @return 系统流水币种
     */
    public String getSysCcy() {
        return sysCcy;
    }

    /**
     * 设置 系统流水币种
     * @param sysCcy 系统流水币种
     */
    public void setSysCcy(String sysCcy) {
        this.sysCcy = sysCcy == null ? null : sysCcy.trim();
    }
}