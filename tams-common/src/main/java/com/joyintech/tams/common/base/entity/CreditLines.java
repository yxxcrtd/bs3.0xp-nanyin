package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class CreditLines extends BaseEntity {
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 投产期限不超过（年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal term;

    /**
     * 批复流水号
     */
    private String pflsh;

    /**
     * 授信额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;

    /**
     * 批复生效日
     */
    private Date vdate;

    /**
     * 批复到期日
     */
    private Date mdate;

    /**
     * 内部评级（1A、1B、1C、2A、2B、2C、3A、3B、3C、4A、4B、4C、5A、5B、5C、6A、6B、6C、7A、7B、7C、8、9、10、DDD）
     */
    private String internalRating;

    /**
     * 证券打分
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal securitiesRating;

    /**
     * 外部评级（AAA+、AAA、AAA-、AA+、AA、AA-、A+、A、A-、A-1、A-2、A-3、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D）
     */
    private String externalRating;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否占用主体授信
     */
    private String creditFlag;

    /**
     * 主体客户编号（是否占用机构编号）
     */
    private String parentCustomerId;

    /**
     * 已占用额度，初始值为0
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hapCreditAmt;

    /**
     * 批复号名称
     */
    private String pflshName;

    /**
     * 授信使用条件
     */
    private String creditCondition;

    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 单债占机构限额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal singerSecRatio;

    /**
     * 授信类型
     */
    private String creditType;

    /**
     * 叠加授信额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmtAdd;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 是否同业
     */
    private String isInterbank;

    /**
     * 是否循环额度
     */
    private String isLoopAmt;

    /**
     * 授信对象
     */
    private String creditObj;

    /**
     * 审批开始日
     */
    private Date apprVdate;

    /**
     * 审批结束日
     */
    private Date apprMdate;

    /**
     * 首次开贷开始日
     */
    private Date creditVdate;

    /**
     * 首次开贷结束日
     */
    private Date creditMdate;

    /**
     * 是否持有至到期
     */
    private String holdMaturity;
    /**
     * 授信价格
     */
    private BigDecimal creditPrice;
    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 授信配置代码
     * @return 授信配置代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信配置代码
     * @param creditMstId 授信配置代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     * @return 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     * @param customerId 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 投产期限不超过（年）
     * @return 投产期限不超过（年）
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 设置 投产期限不超过（年）
     * @param term 投产期限不超过（年）
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 取得 批复流水号
     * @return 批复流水号
     */
    public String getPflsh() {
        return pflsh;
    }

    /**
     * 设置 批复流水号
     * @param pflsh 批复流水号
     */
    public void setPflsh(String pflsh) {
        this.pflsh = pflsh == null ? null : pflsh.trim();
    }

    /**
     * 取得 授信额度
     * @return 授信额度
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * 设置 授信额度
     * @param creditAmt 授信额度
     */
    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    /**
     * 取得 批复生效日
     * @return 批复生效日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 批复生效日
     * @param vdate 批复生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 批复到期日
     * @return 批复到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 批复到期日
     * @param mdate 批复到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 内部评级（1A、1B、1C、2A、2B、2C、3A、3B、3C、4A、4B、4C、5A、5B、5C、6A、6B、6C、7A、7B、7C、8、9、10、DDD）
     * @return 内部评级（1A、1B、1C、2A、2B、2C、3A、3B、3C、4A、4B、4C、5A、5B、5C、6A、6B、6C、7A、7B、7C、8、9、10、DDD）
     */
    public String getInternalRating() {
        return internalRating;
    }

    /**
     * 设置 内部评级（1A、1B、1C、2A、2B、2C、3A、3B、3C、4A、4B、4C、5A、5B、5C、6A、6B、6C、7A、7B、7C、8、9、10、DDD）
     * @param internalRating 内部评级（1A、1B、1C、2A、2B、2C、3A、3B、3C、4A、4B、4C、5A、5B、5C、6A、6B、6C、7A、7B、7C、8、9、10、DDD）
     */
    public void setInternalRating(String internalRating) {
        this.internalRating = internalRating == null ? null : internalRating.trim();
    }

    /**
     * 取得 证券打分
     * @return 证券打分
     */
    public BigDecimal getSecuritiesRating() {
        return securitiesRating;
    }

    /**
     * 设置 证券打分
     * @param securitiesRating 证券打分
     */
    public void setSecuritiesRating(BigDecimal securitiesRating) {
        this.securitiesRating = securitiesRating;
    }

    /**
     * 取得 外部评级（AAA+、AAA、AAA-、AA+、AA、AA-、A+、A、A-、A-1、A-2、A-3、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D）
     * @return 外部评级（AAA+、AAA、AAA-、AA+、AA、AA-、A+、A、A-、A-1、A-2、A-3、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D）
     */
    public String getExternalRating() {
        return externalRating;
    }

    /**
     * 设置 外部评级（AAA+、AAA、AAA-、AA+、AA、AA-、A+、A、A-、A-1、A-2、A-3、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D）
     * @param externalRating 外部评级（AAA+、AAA、AAA-、AA+、AA、AA-、A+、A、A-、A-1、A-2、A-3、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D）
     */
    public void setExternalRating(String externalRating) {
        this.externalRating = externalRating == null ? null : externalRating.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 是否占用主体授信
     * @return 是否占用主体授信
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 设置 是否占用主体授信
     * @param creditFlag 是否占用主体授信
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag == null ? null : creditFlag.trim();
    }

    /**
     * 取得 主体客户编号（是否占用机构编号）
     * @return 主体客户编号（是否占用机构编号）
     */
    public String getParentCustomerId() {
        return parentCustomerId;
    }

    /**
     * 设置 主体客户编号（是否占用机构编号）
     * @param parentCustomerId 主体客户编号（是否占用机构编号）
     */
    public void setParentCustomerId(String parentCustomerId) {
        this.parentCustomerId = parentCustomerId == null ? null : parentCustomerId.trim();
    }

    /**
     * 取得 已占用额度，初始值为0
     * @return 已占用额度，初始值为0
     */
    public BigDecimal getHapCreditAmt() {
        return hapCreditAmt;
    }

    /**
     * 设置 已占用额度，初始值为0
     * @param hapCreditAmt 已占用额度，初始值为0
     */
    public void setHapCreditAmt(BigDecimal hapCreditAmt) {
        this.hapCreditAmt = hapCreditAmt;
    }

    /**
     * 取得 批复号名称
     * @return 批复号名称
     */
    public String getPflshName() {
        return pflshName;
    }

    /**
     * 设置 批复号名称
     * @param pflshName 批复号名称
     */
    public void setPflshName(String pflshName) {
        this.pflshName = pflshName == null ? null : pflshName.trim();
    }

    /**
     * 取得 授信使用条件
     * @return 授信使用条件
     */
    public String getCreditCondition() {
        return creditCondition;
    }

    /**
     * 设置 授信使用条件
     * @param creditCondition 授信使用条件
     */
    public void setCreditCondition(String creditCondition) {
        this.creditCondition = creditCondition == null ? null : creditCondition.trim();
    }

    /**
     * 取得 单债占机构限额
     * @return 单债占机构限额
     */
    public BigDecimal getSingerSecRatio() {
        return singerSecRatio;
    }

    /**
     * 设置 单债占机构限额
     * @param singerSecRatio 单债占机构限额
     */
    public void setSingerSecRatio(BigDecimal singerSecRatio) {
        this.singerSecRatio = singerSecRatio;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 机构代码
     * @param institutionCode 机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 授信类型
     * @return 授信类型
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 授信类型
     * @param creditType 授信类型
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 叠加授信额度
     * @return 叠加授信额度
     */
    public BigDecimal getCreditAmtAdd() {
        return creditAmtAdd;
    }

    /**
     * 设置 叠加授信额度
     * @param creditAmtAdd 叠加授信额度
     */
    public void setCreditAmtAdd(BigDecimal creditAmtAdd) {
        this.creditAmtAdd = creditAmtAdd;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 是否同业
     * @return 是否同业
     */
    public String getIsInterbank() {
        return isInterbank;
    }

    /**
     * 设置 是否同业
     * @param isInterbank 是否同业
     */
    public void setIsInterbank(String isInterbank) {
        this.isInterbank = isInterbank == null ? null : isInterbank.trim();
    }

    /**
     * 取得 是否循环额度
     * @return 是否循环额度
     */
    public String getIsLoopAmt() {
        return isLoopAmt;
    }

    /**
     * 设置 是否循环额度
     * @param isLoopAmt 是否循环额度
     */
    public void setIsLoopAmt(String isLoopAmt) {
        this.isLoopAmt = isLoopAmt == null ? null : isLoopAmt.trim();
    }

    /**
     * 取得 授信对象
     * @return 授信对象
     */
    public String getCreditObj() {
        return creditObj;
    }

    /**
     * 设置 授信对象
     * @param creditObj 授信对象
     */
    public void setCreditObj(String creditObj) {
        this.creditObj = creditObj == null ? null : creditObj.trim();
    }

    /**
     * 取得 审批开始日
     * @return 审批开始日
     */
    public Date getApprVdate() {
        return apprVdate;
    }

    /**
     * 设置 审批开始日
     * @param apprVdate 审批开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setApprVdate(Date apprVdate) {
        this.apprVdate = apprVdate;
    }

    /**
     * 取得 审批结束日
     * @return 审批结束日
     */
    public Date getApprMdate() {
        return apprMdate;
    }

    /**
     * 设置 审批结束日
     * @param apprMdate 审批结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setApprMdate(Date apprMdate) {
        this.apprMdate = apprMdate;
    }

    /**
     * 取得 首次开贷开始日
     * @return 首次开贷开始日
     */
    public Date getCreditVdate() {
        return creditVdate;
    }

    /**
     * 设置 首次开贷开始日
     * @param creditVdate 首次开贷开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreditVdate(Date creditVdate) {
        this.creditVdate = creditVdate;
    }

    /**
     * 取得 首次开贷结束日
     * @return 首次开贷结束日
     */
    public Date getCreditMdate() {
        return creditMdate;
    }

    /**
     * 设置 首次开贷结束日
     * @param creditMdate 首次开贷结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreditMdate(Date creditMdate) {
        this.creditMdate = creditMdate;
    }

    /**
     * 取得 是否持有至到期
     * @return 是否持有至到期
     */
    public String getHoldMaturity() {
        return holdMaturity;
    }

    /**
     * 设置 是否持有至到期
     * @param holdMaturity 是否持有至到期
     */
    public void setHoldMaturity(String holdMaturity) {
        this.holdMaturity = holdMaturity == null ? null : holdMaturity.trim();
    }

    public BigDecimal getCreditPrice() {
        return creditPrice;
    }

    public void setCreditPrice(BigDecimal creditPrice) {
        this.creditPrice = creditPrice;
    }
}