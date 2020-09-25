package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InfChinamutualfunddescription extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String fInfoWindcode;

    /**
     * 前端代码
     */
    private String fInfoFrontCode;

    /**
     * 后端代码
     */
    private String fInfoBackendCode;

    /**
     * 名称
     */
    private String fInfoFullname;

    /**
     * 简称
     */
    private String fInfoName;

    /**
     * 管理人
     */
    private String fInfoCorpFundmanagementcomp;

    /**
     * 托管人
     */
    private String fInfoCustodianbank;

    /**
     * 投资类型
     */
    private String fInfoFirstinvesttype;

    /**
     * 成立日期
     */
    private String fInfoSetupdate;

    /**
     * 到期日期
     */
    private String fInfoMaturitydate;

    /**
     * 发行份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fIssueTotalunit;

    /**
     * 管理费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoManagementfeeratio;

    /**
     * 托管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoCustodianfeeratio;

    /**
     * 货币代码
     */
    private String crnyCode;

    /**
     * 存续期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoPtmyear;

    /**
     * 推广起始日期
     */
    private String fIssueOefStartdateinst;

    /**
     * 推广截止日期
     */
    private String fIssueOefDnddateinst;

    /**
     * 面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoParvalue;

    /**
     * 信托类别
     */
    private String fInfoTrusttype;

    /**
     * 受托人
     */
    private String fInfoTrustee;

    /**
     * 上市日期/申购首日
     */
    private String fPchredmPchstartdate;

    /**
     * 日常赎回起始日
     */
    private String fInfoRedmstartdate;

    /**
     * 起点金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoMinbuyamount;

    /**
     * 预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoExpectedrateofreturn;

    /**
     * 发行地
     */
    private String fInfoIssuingplace;

    /**
     * 业绩比较基准
     */
    private String fInfoBenchmark;

    /**
     * 存续状态
     */
    private Integer fInfoStatus;

    /**
     * 限定类型
     */
    private String fInfoRestrictedornot;

    /**
     * 是否结构化产品
     */
    private Short fInfoStructuredornot;

    /**
     * 交易所
     */
    private String fInfoExchmarket;

    /**
     * 投资风格
     */
    private String fInfoFirstinveststyle;

    /**
     * 发行日期
     */
    private String fInfoIssuedate;

    /**
     * 基金类型
     */
    private String fInfoType;

    /**
     * 是否为初始基金
     */
    private Integer fInfoIsinitial;

    /**
     * 简称拼音
     */
    private String fInfoPinyin;

    /**
     * 投资范围
     */
    private String fInfoInvestscope;

    /**
     * 投资目标
     */
    private String fInfoInvestobject;

    /**
     * 投资理念
     */
    private String fInfoInvestconception;

    /**
     * 决策依据
     */
    private String fInfoDecisionBasis;

    /**
     * 是否指数基金
     */
    private Integer isIndexfund;

    /**
     * 退市日期
     */
    private String fInfoDelistdate;

    /**
     * 基金管理人ID
     */
    private String fInfoCorpFundmanagementid;

    /**
     * 托管人id
     */
    private String fInfoCustodianbankid;

    /**
     * 单一投资者持有份额上限(亿份)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxNumHolder;

    /**
     * 封闭期目标募集数量上限(亿份)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxNumColtarget;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * @return Wind代码
     */
    public String getfInfoWindcode() {
        return fInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * @param fInfoWindcode Wind代码
     */
    public void setfInfoWindcode(String fInfoWindcode) {
        this.fInfoWindcode = fInfoWindcode == null ? null : fInfoWindcode.trim();
    }

    /**
     * 取得 前端代码
     * @return 前端代码
     */
    public String getfInfoFrontCode() {
        return fInfoFrontCode;
    }

    /**
     * 设置 前端代码
     * @param fInfoFrontCode 前端代码
     */
    public void setfInfoFrontCode(String fInfoFrontCode) {
        this.fInfoFrontCode = fInfoFrontCode == null ? null : fInfoFrontCode.trim();
    }

    /**
     * 取得 后端代码
     * @return 后端代码
     */
    public String getfInfoBackendCode() {
        return fInfoBackendCode;
    }

    /**
     * 设置 后端代码
     * @param fInfoBackendCode 后端代码
     */
    public void setfInfoBackendCode(String fInfoBackendCode) {
        this.fInfoBackendCode = fInfoBackendCode == null ? null : fInfoBackendCode.trim();
    }

    /**
     * 取得 名称
     * @return 名称
     */
    public String getfInfoFullname() {
        return fInfoFullname;
    }

    /**
     * 设置 名称
     * @param fInfoFullname 名称
     */
    public void setfInfoFullname(String fInfoFullname) {
        this.fInfoFullname = fInfoFullname == null ? null : fInfoFullname.trim();
    }

    /**
     * 取得 简称
     * @return 简称
     */
    public String getfInfoName() {
        return fInfoName;
    }

    /**
     * 设置 简称
     * @param fInfoName 简称
     */
    public void setfInfoName(String fInfoName) {
        this.fInfoName = fInfoName == null ? null : fInfoName.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getfInfoCorpFundmanagementcomp() {
        return fInfoCorpFundmanagementcomp;
    }

    /**
     * 设置 管理人
     * @param fInfoCorpFundmanagementcomp 管理人
     */
    public void setfInfoCorpFundmanagementcomp(String fInfoCorpFundmanagementcomp) {
        this.fInfoCorpFundmanagementcomp = fInfoCorpFundmanagementcomp == null ? null : fInfoCorpFundmanagementcomp.trim();
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getfInfoCustodianbank() {
        return fInfoCustodianbank;
    }

    /**
     * 设置 托管人
     * @param fInfoCustodianbank 托管人
     */
    public void setfInfoCustodianbank(String fInfoCustodianbank) {
        this.fInfoCustodianbank = fInfoCustodianbank == null ? null : fInfoCustodianbank.trim();
    }

    /**
     * 取得 投资类型
     * @return 投资类型
     */
    public String getfInfoFirstinvesttype() {
        return fInfoFirstinvesttype;
    }

    /**
     * 设置 投资类型
     * @param fInfoFirstinvesttype 投资类型
     */
    public void setfInfoFirstinvesttype(String fInfoFirstinvesttype) {
        this.fInfoFirstinvesttype = fInfoFirstinvesttype == null ? null : fInfoFirstinvesttype.trim();
    }

    /**
     * 取得 成立日期
     * @return 成立日期
     */
    public String getfInfoSetupdate() {
        return fInfoSetupdate;
    }

    /**
     * 设置 成立日期
     * @param fInfoSetupdate 成立日期
     */
    public void setfInfoSetupdate(String fInfoSetupdate) {
        this.fInfoSetupdate = fInfoSetupdate == null ? null : fInfoSetupdate.trim();
    }

    /**
     * 取得 到期日期
     * @return 到期日期
     */
    public String getfInfoMaturitydate() {
        return fInfoMaturitydate;
    }

    /**
     * 设置 到期日期
     * @param fInfoMaturitydate 到期日期
     */
    public void setfInfoMaturitydate(String fInfoMaturitydate) {
        this.fInfoMaturitydate = fInfoMaturitydate == null ? null : fInfoMaturitydate.trim();
    }

    /**
     * 取得 发行份额
     * @return 发行份额
     */
    public BigDecimal getfIssueTotalunit() {
        return fIssueTotalunit;
    }

    /**
     * 设置 发行份额
     * @param fIssueTotalunit 发行份额
     */
    public void setfIssueTotalunit(BigDecimal fIssueTotalunit) {
        this.fIssueTotalunit = fIssueTotalunit;
    }

    /**
     * 取得 管理费
     * @return 管理费
     */
    public BigDecimal getfInfoManagementfeeratio() {
        return fInfoManagementfeeratio;
    }

    /**
     * 设置 管理费
     * @param fInfoManagementfeeratio 管理费
     */
    public void setfInfoManagementfeeratio(BigDecimal fInfoManagementfeeratio) {
        this.fInfoManagementfeeratio = fInfoManagementfeeratio;
    }

    /**
     * 取得 托管费
     * @return 托管费
     */
    public BigDecimal getfInfoCustodianfeeratio() {
        return fInfoCustodianfeeratio;
    }

    /**
     * 设置 托管费
     * @param fInfoCustodianfeeratio 托管费
     */
    public void setfInfoCustodianfeeratio(BigDecimal fInfoCustodianfeeratio) {
        this.fInfoCustodianfeeratio = fInfoCustodianfeeratio;
    }

    /**
     * 取得 货币代码
     * @return 货币代码
     */
    public String getCrnyCode() {
        return crnyCode;
    }

    /**
     * 设置 货币代码
     * @param crnyCode 货币代码
     */
    public void setCrnyCode(String crnyCode) {
        this.crnyCode = crnyCode == null ? null : crnyCode.trim();
    }

    /**
     * 取得 存续期
     * @return 存续期
     */
    public BigDecimal getfInfoPtmyear() {
        return fInfoPtmyear;
    }

    /**
     * 设置 存续期
     * @param fInfoPtmyear 存续期
     */
    public void setfInfoPtmyear(BigDecimal fInfoPtmyear) {
        this.fInfoPtmyear = fInfoPtmyear;
    }

    /**
     * 取得 推广起始日期
     * @return 推广起始日期
     */
    public String getfIssueOefStartdateinst() {
        return fIssueOefStartdateinst;
    }

    /**
     * 设置 推广起始日期
     * @param fIssueOefStartdateinst 推广起始日期
     */
    public void setfIssueOefStartdateinst(String fIssueOefStartdateinst) {
        this.fIssueOefStartdateinst = fIssueOefStartdateinst == null ? null : fIssueOefStartdateinst.trim();
    }

    /**
     * 取得 推广截止日期
     * @return 推广截止日期
     */
    public String getfIssueOefDnddateinst() {
        return fIssueOefDnddateinst;
    }

    /**
     * 设置 推广截止日期
     * @param fIssueOefDnddateinst 推广截止日期
     */
    public void setfIssueOefDnddateinst(String fIssueOefDnddateinst) {
        this.fIssueOefDnddateinst = fIssueOefDnddateinst == null ? null : fIssueOefDnddateinst.trim();
    }

    /**
     * 取得 面值
     * @return 面值
     */
    public BigDecimal getfInfoParvalue() {
        return fInfoParvalue;
    }

    /**
     * 设置 面值
     * @param fInfoParvalue 面值
     */
    public void setfInfoParvalue(BigDecimal fInfoParvalue) {
        this.fInfoParvalue = fInfoParvalue;
    }

    /**
     * 取得 信托类别
     * @return 信托类别
     */
    public String getfInfoTrusttype() {
        return fInfoTrusttype;
    }

    /**
     * 设置 信托类别
     * @param fInfoTrusttype 信托类别
     */
    public void setfInfoTrusttype(String fInfoTrusttype) {
        this.fInfoTrusttype = fInfoTrusttype == null ? null : fInfoTrusttype.trim();
    }

    /**
     * 取得 受托人
     * @return 受托人
     */
    public String getfInfoTrustee() {
        return fInfoTrustee;
    }

    /**
     * 设置 受托人
     * @param fInfoTrustee 受托人
     */
    public void setfInfoTrustee(String fInfoTrustee) {
        this.fInfoTrustee = fInfoTrustee == null ? null : fInfoTrustee.trim();
    }

    /**
     * 取得 上市日期/申购首日
     * @return 上市日期/申购首日
     */
    public String getfPchredmPchstartdate() {
        return fPchredmPchstartdate;
    }

    /**
     * 设置 上市日期/申购首日
     * @param fPchredmPchstartdate 上市日期/申购首日
     */
    public void setfPchredmPchstartdate(String fPchredmPchstartdate) {
        this.fPchredmPchstartdate = fPchredmPchstartdate == null ? null : fPchredmPchstartdate.trim();
    }

    /**
     * 取得 日常赎回起始日
     * @return 日常赎回起始日
     */
    public String getfInfoRedmstartdate() {
        return fInfoRedmstartdate;
    }

    /**
     * 设置 日常赎回起始日
     * @param fInfoRedmstartdate 日常赎回起始日
     */
    public void setfInfoRedmstartdate(String fInfoRedmstartdate) {
        this.fInfoRedmstartdate = fInfoRedmstartdate == null ? null : fInfoRedmstartdate.trim();
    }

    /**
     * 取得 起点金额
     * @return 起点金额
     */
    public BigDecimal getfInfoMinbuyamount() {
        return fInfoMinbuyamount;
    }

    /**
     * 设置 起点金额
     * @param fInfoMinbuyamount 起点金额
     */
    public void setfInfoMinbuyamount(BigDecimal fInfoMinbuyamount) {
        this.fInfoMinbuyamount = fInfoMinbuyamount;
    }

    /**
     * 取得 预期收益率
     * @return 预期收益率
     */
    public BigDecimal getfInfoExpectedrateofreturn() {
        return fInfoExpectedrateofreturn;
    }

    /**
     * 设置 预期收益率
     * @param fInfoExpectedrateofreturn 预期收益率
     */
    public void setfInfoExpectedrateofreturn(BigDecimal fInfoExpectedrateofreturn) {
        this.fInfoExpectedrateofreturn = fInfoExpectedrateofreturn;
    }

    /**
     * 取得 发行地
     * @return 发行地
     */
    public String getfInfoIssuingplace() {
        return fInfoIssuingplace;
    }

    /**
     * 设置 发行地
     * @param fInfoIssuingplace 发行地
     */
    public void setfInfoIssuingplace(String fInfoIssuingplace) {
        this.fInfoIssuingplace = fInfoIssuingplace == null ? null : fInfoIssuingplace.trim();
    }

    /**
     * 取得 业绩比较基准
     * @return 业绩比较基准
     */
    public String getfInfoBenchmark() {
        return fInfoBenchmark;
    }

    /**
     * 设置 业绩比较基准
     * @param fInfoBenchmark 业绩比较基准
     */
    public void setfInfoBenchmark(String fInfoBenchmark) {
        this.fInfoBenchmark = fInfoBenchmark == null ? null : fInfoBenchmark.trim();
    }

    /**
     * 取得 存续状态
     * @return 存续状态
     */
    public Integer getfInfoStatus() {
        return fInfoStatus;
    }

    /**
     * 设置 存续状态
     * @param fInfoStatus 存续状态
     */
    public void setfInfoStatus(Integer fInfoStatus) {
        this.fInfoStatus = fInfoStatus;
    }

    /**
     * 取得 限定类型
     * @return 限定类型
     */
    public String getfInfoRestrictedornot() {
        return fInfoRestrictedornot;
    }

    /**
     * 设置 限定类型
     * @param fInfoRestrictedornot 限定类型
     */
    public void setfInfoRestrictedornot(String fInfoRestrictedornot) {
        this.fInfoRestrictedornot = fInfoRestrictedornot == null ? null : fInfoRestrictedornot.trim();
    }

    /**
     * 取得 是否结构化产品
     * @return 是否结构化产品
     */
    public Short getfInfoStructuredornot() {
        return fInfoStructuredornot;
    }

    /**
     * 设置 是否结构化产品
     * @param fInfoStructuredornot 是否结构化产品
     */
    public void setfInfoStructuredornot(Short fInfoStructuredornot) {
        this.fInfoStructuredornot = fInfoStructuredornot;
    }

    /**
     * 取得 交易所
     * @return 交易所
     */
    public String getfInfoExchmarket() {
        return fInfoExchmarket;
    }

    /**
     * 设置 交易所
     * @param fInfoExchmarket 交易所
     */
    public void setfInfoExchmarket(String fInfoExchmarket) {
        this.fInfoExchmarket = fInfoExchmarket == null ? null : fInfoExchmarket.trim();
    }

    /**
     * 取得 投资风格
     * @return 投资风格
     */
    public String getfInfoFirstinveststyle() {
        return fInfoFirstinveststyle;
    }

    /**
     * 设置 投资风格
     * @param fInfoFirstinveststyle 投资风格
     */
    public void setfInfoFirstinveststyle(String fInfoFirstinveststyle) {
        this.fInfoFirstinveststyle = fInfoFirstinveststyle == null ? null : fInfoFirstinveststyle.trim();
    }

    /**
     * 取得 发行日期
     * @return 发行日期
     */
    public String getfInfoIssuedate() {
        return fInfoIssuedate;
    }

    /**
     * 设置 发行日期
     * @param fInfoIssuedate 发行日期
     */
    public void setfInfoIssuedate(String fInfoIssuedate) {
        this.fInfoIssuedate = fInfoIssuedate == null ? null : fInfoIssuedate.trim();
    }

    /**
     * 取得 基金类型
     * @return 基金类型
     */
    public String getfInfoType() {
        return fInfoType;
    }

    /**
     * 设置 基金类型
     * @param fInfoType 基金类型
     */
    public void setfInfoType(String fInfoType) {
        this.fInfoType = fInfoType == null ? null : fInfoType.trim();
    }

    /**
     * 取得 是否为初始基金
     * @return 是否为初始基金
     */
    public Integer getfInfoIsinitial() {
        return fInfoIsinitial;
    }

    /**
     * 设置 是否为初始基金
     * @param fInfoIsinitial 是否为初始基金
     */
    public void setfInfoIsinitial(Integer fInfoIsinitial) {
        this.fInfoIsinitial = fInfoIsinitial;
    }

    /**
     * 取得 简称拼音
     * @return 简称拼音
     */
    public String getfInfoPinyin() {
        return fInfoPinyin;
    }

    /**
     * 设置 简称拼音
     * @param fInfoPinyin 简称拼音
     */
    public void setfInfoPinyin(String fInfoPinyin) {
        this.fInfoPinyin = fInfoPinyin == null ? null : fInfoPinyin.trim();
    }

    /**
     * 取得 投资范围
     * @return 投资范围
     */
    public String getfInfoInvestscope() {
        return fInfoInvestscope;
    }

    /**
     * 设置 投资范围
     * @param fInfoInvestscope 投资范围
     */
    public void setfInfoInvestscope(String fInfoInvestscope) {
        this.fInfoInvestscope = fInfoInvestscope == null ? null : fInfoInvestscope.trim();
    }

    /**
     * 取得 投资目标
     * @return 投资目标
     */
    public String getfInfoInvestobject() {
        return fInfoInvestobject;
    }

    /**
     * 设置 投资目标
     * @param fInfoInvestobject 投资目标
     */
    public void setfInfoInvestobject(String fInfoInvestobject) {
        this.fInfoInvestobject = fInfoInvestobject == null ? null : fInfoInvestobject.trim();
    }

    /**
     * 取得 投资理念
     * @return 投资理念
     */
    public String getfInfoInvestconception() {
        return fInfoInvestconception;
    }

    /**
     * 设置 投资理念
     * @param fInfoInvestconception 投资理念
     */
    public void setfInfoInvestconception(String fInfoInvestconception) {
        this.fInfoInvestconception = fInfoInvestconception == null ? null : fInfoInvestconception.trim();
    }

    /**
     * 取得 决策依据
     * @return 决策依据
     */
    public String getfInfoDecisionBasis() {
        return fInfoDecisionBasis;
    }

    /**
     * 设置 决策依据
     * @param fInfoDecisionBasis 决策依据
     */
    public void setfInfoDecisionBasis(String fInfoDecisionBasis) {
        this.fInfoDecisionBasis = fInfoDecisionBasis == null ? null : fInfoDecisionBasis.trim();
    }

    /**
     * 取得 是否指数基金
     * @return 是否指数基金
     */
    public Integer getIsIndexfund() {
        return isIndexfund;
    }

    /**
     * 设置 是否指数基金
     * @param isIndexfund 是否指数基金
     */
    public void setIsIndexfund(Integer isIndexfund) {
        this.isIndexfund = isIndexfund;
    }

    /**
     * 取得 退市日期
     * @return 退市日期
     */
    public String getfInfoDelistdate() {
        return fInfoDelistdate;
    }

    /**
     * 设置 退市日期
     * @param fInfoDelistdate 退市日期
     */
    public void setfInfoDelistdate(String fInfoDelistdate) {
        this.fInfoDelistdate = fInfoDelistdate == null ? null : fInfoDelistdate.trim();
    }

    /**
     * 取得 基金管理人ID
     * @return 基金管理人ID
     */
    public String getfInfoCorpFundmanagementid() {
        return fInfoCorpFundmanagementid;
    }

    /**
     * 设置 基金管理人ID
     * @param fInfoCorpFundmanagementid 基金管理人ID
     */
    public void setfInfoCorpFundmanagementid(String fInfoCorpFundmanagementid) {
        this.fInfoCorpFundmanagementid = fInfoCorpFundmanagementid == null ? null : fInfoCorpFundmanagementid.trim();
    }

    /**
     * 取得 托管人id
     * @return 托管人id
     */
    public String getfInfoCustodianbankid() {
        return fInfoCustodianbankid;
    }

    /**
     * 设置 托管人id
     * @param fInfoCustodianbankid 托管人id
     */
    public void setfInfoCustodianbankid(String fInfoCustodianbankid) {
        this.fInfoCustodianbankid = fInfoCustodianbankid == null ? null : fInfoCustodianbankid.trim();
    }

    /**
     * 取得 单一投资者持有份额上限(亿份)
     * @return 单一投资者持有份额上限(亿份)
     */
    public BigDecimal getMaxNumHolder() {
        return maxNumHolder;
    }

    /**
     * 设置 单一投资者持有份额上限(亿份)
     * @param maxNumHolder 单一投资者持有份额上限(亿份)
     */
    public void setMaxNumHolder(BigDecimal maxNumHolder) {
        this.maxNumHolder = maxNumHolder;
    }

    /**
     * 取得 封闭期目标募集数量上限(亿份)
     * @return 封闭期目标募集数量上限(亿份)
     */
    public BigDecimal getMaxNumColtarget() {
        return maxNumColtarget;
    }

    /**
     * 设置 封闭期目标募集数量上限(亿份)
     * @param maxNumColtarget 封闭期目标募集数量上限(亿份)
     */
    public void setMaxNumColtarget(BigDecimal maxNumColtarget) {
        this.maxNumColtarget = maxNumColtarget;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 null
     * @param opdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 null
     * @param opmode null
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}