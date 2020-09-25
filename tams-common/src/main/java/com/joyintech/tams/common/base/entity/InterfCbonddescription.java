package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbonddescription extends BaseEntity {
    /**
     * 对象 ID
     */
    private String objectId;

    /**
     * Wind 代码
     */
    private String sInfoWindcode;

    /**
     * 债券名称
     */
    private String bInfoFullname;

    /**
     * 发行人
     */
    private String bInfoIssuer;

    /**
     * 发行公告日
     */
    private String bIssueAnnouncement;

    /**
     * 发行起始日
     */
    private String bIssueFirstissue;

    /**
     * 发行截止日
     */
    private String bIssueLastissue;

    /**
     * 计划发行总量(亿元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bIssueAmountplan;

    /**
     * 实际发行总量(亿元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bIssueAmountact;

    /**
     * 发行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoIssueprice;

    /**
     * 面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoPar;

    /**
     * 发行票面利率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoCouponrate;

    /**
     * 利差(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoSpread;

    /**
     * 计息起始日
     */
    private String bInfoCarrydate;

    /**
     * 计息截止日
     */
    private String bInfoEnddate;

    /**
     * 到期日
     */
    private String bInfoMaturitydate;

    /**
     * 债券期限(年)

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoTermYear;

    /**
     * 债券期限(天)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoTermDay;

    /**
     * 兑付日
     */
    private String bInfoPaymentdate;

    /**
     * 计息方式
     */
    private Integer bInfoPaymenttype;

    /**
     * 付息频率

     */
    private String bInfoInterestfrequency;

    /**
     * 债券形式
     */
    private String bInfoForm;

    /**
     * 息票品种

     */
    private Integer bInfoCoupon;

    /**
     * 附息利率品种
     */
    private Integer bInfoInteresttype;

    /**
     * 特殊年计息天数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoAct;

    /**
     * 发行手续费率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bIssueFee;

    /**
     * 兑付手续费率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bRedemptionFeeration;

    /**
     * 所得税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoTaxrate;

    /**
     * 货币代码
     */
    private String crncyCode;

    /**
     * 债券简称
     */
    private String sInfoName;

    /**
     * 交易所
     */
    private String sInfoExchmarket;

    /**
     * 担保人
     */
    private String bInfoGuarantor;

    /**
     * 担保方式
     */
    private Integer bInfoGuartype;

    /**
     * 上市日期
     */
    private String bInfoListdate;

    /**
     * 年内序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoYearsnumber;

    /**
     * 兑付登记起始日
     */
    private String sDivRecorddate;

    /**
     * 上网发行认购代码
     */
    private String bInfoCodebyplacing;

    /**
     * 退市日期
     */
    private String bInfoDelistdate;

    /**
     * 发行方式
     */
    private Integer bInfoIssuetype;

    /**
     * 担保简介
     */
    private String bInfoGuarintroduction;

    /**
     * 上网发行起始日期
     */
    private String bInfoBgndbyplacing;

    /**
     * 上网发行截止日期
     */
    private String bInfoEnddbyplacing;

    /**
     * 上网发行数量(亿元)

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoAmountbyplacing;

    /**
     * 承销方式代码
     */
    private Integer bInfoUnderwritingcode;

    /**
     * 发行人编号
     */
    private String bInfoIssuercode;

    /**
     * 原债券代码
     */
    private String bInfoFormercode;

    /**
     * 利率说明
     */
    private String bInfoCoupontxt;

    /**
     * 是否发行失败
     */
    private Integer isFailure;

    /**
     * 是否跨市场
     */
    private Integer isCrossmarket;

    /**
     * 付息日说明
     */
    private String bInfoCoupondatetxt;

    /**
     * 是否次级债或混合资本债

     */
    private Integer bInfoSubordinateornot;

    /**
     * 参考收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bTendrstReferyield;

    /**
     * 最新面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoCurpar;

    /**
     * 原Wind代码
     */
    private String sInfoFormerwindcode;

    /**
     * 操作日期
     */
    private Date opdate;

    /**
     * 操作类型
     */
    private String opmode;

    /**
     * 取得 对象 ID
     * @return 对象 ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象 ID
     * @param objectId 对象 ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind 代码
     * @return Wind 代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind 代码
     * @param sInfoWindcode Wind 代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getbInfoFullname() {
        return bInfoFullname;
    }

    /**
     * 设置 债券名称
     * @param bInfoFullname 债券名称
     */
    public void setbInfoFullname(String bInfoFullname) {
        this.bInfoFullname = bInfoFullname == null ? null : bInfoFullname.trim();
    }

    /**
     * 取得 发行人
     * @return 发行人
     */
    public String getbInfoIssuer() {
        return bInfoIssuer;
    }

    /**
     * 设置 发行人
     * @param bInfoIssuer 发行人
     */
    public void setbInfoIssuer(String bInfoIssuer) {
        this.bInfoIssuer = bInfoIssuer == null ? null : bInfoIssuer.trim();
    }

    /**
     * 取得 发行公告日
     * @return 发行公告日
     */
    public String getbIssueAnnouncement() {
        return bIssueAnnouncement;
    }

    /**
     * 设置 发行公告日
     * @param bIssueAnnouncement 发行公告日
     */
    public void setbIssueAnnouncement(String bIssueAnnouncement) {
        this.bIssueAnnouncement = bIssueAnnouncement == null ? null : bIssueAnnouncement.trim();
    }

    /**
     * 取得 发行起始日
     * @return 发行起始日
     */
    public String getbIssueFirstissue() {
        return bIssueFirstissue;
    }

    /**
     * 设置 发行起始日
     * @param bIssueFirstissue 发行起始日
     */
    public void setbIssueFirstissue(String bIssueFirstissue) {
        this.bIssueFirstissue = bIssueFirstissue == null ? null : bIssueFirstissue.trim();
    }

    /**
     * 取得 发行截止日
     * @return 发行截止日
     */
    public String getbIssueLastissue() {
        return bIssueLastissue;
    }

    /**
     * 设置 发行截止日
     * @param bIssueLastissue 发行截止日
     */
    public void setbIssueLastissue(String bIssueLastissue) {
        this.bIssueLastissue = bIssueLastissue == null ? null : bIssueLastissue.trim();
    }

    /**
     * 取得 计划发行总量(亿元)
     * @return 计划发行总量(亿元)
     */
    public BigDecimal getbIssueAmountplan() {
        return bIssueAmountplan;
    }

    /**
     * 设置 计划发行总量(亿元)
     * @param bIssueAmountplan 计划发行总量(亿元)
     */
    public void setbIssueAmountplan(BigDecimal bIssueAmountplan) {
        this.bIssueAmountplan = bIssueAmountplan;
    }

    /**
     * 取得 实际发行总量(亿元)
     * @return 实际发行总量(亿元)
     */
    public BigDecimal getbIssueAmountact() {
        return bIssueAmountact;
    }

    /**
     * 设置 实际发行总量(亿元)
     * @param bIssueAmountact 实际发行总量(亿元)
     */
    public void setbIssueAmountact(BigDecimal bIssueAmountact) {
        this.bIssueAmountact = bIssueAmountact;
    }

    /**
     * 取得 发行价格
     * @return 发行价格
     */
    public BigDecimal getbInfoIssueprice() {
        return bInfoIssueprice;
    }

    /**
     * 设置 发行价格
     * @param bInfoIssueprice 发行价格
     */
    public void setbInfoIssueprice(BigDecimal bInfoIssueprice) {
        this.bInfoIssueprice = bInfoIssueprice;
    }

    /**
     * 取得 面值
     * @return 面值
     */
    public BigDecimal getbInfoPar() {
        return bInfoPar;
    }

    /**
     * 设置 面值
     * @param bInfoPar 面值
     */
    public void setbInfoPar(BigDecimal bInfoPar) {
        this.bInfoPar = bInfoPar;
    }

    /**
     * 取得 发行票面利率(%)
     * @return 发行票面利率(%)
     */
    public BigDecimal getbInfoCouponrate() {
        return bInfoCouponrate;
    }

    /**
     * 设置 发行票面利率(%)
     * @param bInfoCouponrate 发行票面利率(%)
     */
    public void setbInfoCouponrate(BigDecimal bInfoCouponrate) {
        this.bInfoCouponrate = bInfoCouponrate;
    }

    /**
     * 取得 利差(%)
     * @return 利差(%)
     */
    public BigDecimal getbInfoSpread() {
        return bInfoSpread;
    }

    /**
     * 设置 利差(%)
     * @param bInfoSpread 利差(%)
     */
    public void setbInfoSpread(BigDecimal bInfoSpread) {
        this.bInfoSpread = bInfoSpread;
    }

    /**
     * 取得 计息起始日
     * @return 计息起始日
     */
    public String getbInfoCarrydate() {
        return bInfoCarrydate;
    }

    /**
     * 设置 计息起始日
     * @param bInfoCarrydate 计息起始日
     */
    public void setbInfoCarrydate(String bInfoCarrydate) {
        this.bInfoCarrydate = bInfoCarrydate == null ? null : bInfoCarrydate.trim();
    }

    /**
     * 取得 计息截止日
     * @return 计息截止日
     */
    public String getbInfoEnddate() {
        return bInfoEnddate;
    }

    /**
     * 设置 计息截止日
     * @param bInfoEnddate 计息截止日
     */
    public void setbInfoEnddate(String bInfoEnddate) {
        this.bInfoEnddate = bInfoEnddate == null ? null : bInfoEnddate.trim();
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public String getbInfoMaturitydate() {
        return bInfoMaturitydate;
    }

    /**
     * 设置 到期日
     * @param bInfoMaturitydate 到期日
     */
    public void setbInfoMaturitydate(String bInfoMaturitydate) {
        this.bInfoMaturitydate = bInfoMaturitydate == null ? null : bInfoMaturitydate.trim();
    }

    /**
     * 取得 债券期限(年)

     * @return 债券期限(年)

     */
    public BigDecimal getbInfoTermYear() {
        return bInfoTermYear;
    }

    /**
     * 设置 债券期限(年)

     * @param bInfoTermYear 债券期限(年)

     */
    public void setbInfoTermYear(BigDecimal bInfoTermYear) {
        this.bInfoTermYear = bInfoTermYear;
    }

    /**
     * 取得 债券期限(天)
     * @return 债券期限(天)
     */
    public BigDecimal getbInfoTermDay() {
        return bInfoTermDay;
    }

    /**
     * 设置 债券期限(天)
     * @param bInfoTermDay 债券期限(天)
     */
    public void setbInfoTermDay(BigDecimal bInfoTermDay) {
        this.bInfoTermDay = bInfoTermDay;
    }

    /**
     * 取得 兑付日
     * @return 兑付日
     */
    public String getbInfoPaymentdate() {
        return bInfoPaymentdate;
    }

    /**
     * 设置 兑付日
     * @param bInfoPaymentdate 兑付日
     */
    public void setbInfoPaymentdate(String bInfoPaymentdate) {
        this.bInfoPaymentdate = bInfoPaymentdate == null ? null : bInfoPaymentdate.trim();
    }

    /**
     * 取得 计息方式
     * @return 计息方式
     */
    public Integer getbInfoPaymenttype() {
        return bInfoPaymenttype;
    }

    /**
     * 设置 计息方式
     * @param bInfoPaymenttype 计息方式
     */
    public void setbInfoPaymenttype(Integer bInfoPaymenttype) {
        this.bInfoPaymenttype = bInfoPaymenttype;
    }

    /**
     * 取得 付息频率

     * @return 付息频率

     */
    public String getbInfoInterestfrequency() {
        return bInfoInterestfrequency;
    }

    /**
     * 设置 付息频率

     * @param bInfoInterestfrequency 付息频率

     */
    public void setbInfoInterestfrequency(String bInfoInterestfrequency) {
        this.bInfoInterestfrequency = bInfoInterestfrequency == null ? null : bInfoInterestfrequency.trim();
    }

    /**
     * 取得 债券形式
     * @return 债券形式
     */
    public String getbInfoForm() {
        return bInfoForm;
    }

    /**
     * 设置 债券形式
     * @param bInfoForm 债券形式
     */
    public void setbInfoForm(String bInfoForm) {
        this.bInfoForm = bInfoForm == null ? null : bInfoForm.trim();
    }

    /**
     * 取得 息票品种

     * @return 息票品种

     */
    public Integer getbInfoCoupon() {
        return bInfoCoupon;
    }

    /**
     * 设置 息票品种

     * @param bInfoCoupon 息票品种

     */
    public void setbInfoCoupon(Integer bInfoCoupon) {
        this.bInfoCoupon = bInfoCoupon;
    }

    /**
     * 取得 附息利率品种
     * @return 附息利率品种
     */
    public Integer getbInfoInteresttype() {
        return bInfoInteresttype;
    }

    /**
     * 设置 附息利率品种
     * @param bInfoInteresttype 附息利率品种
     */
    public void setbInfoInteresttype(Integer bInfoInteresttype) {
        this.bInfoInteresttype = bInfoInteresttype;
    }

    /**
     * 取得 特殊年计息天数
     * @return 特殊年计息天数
     */
    public BigDecimal getbInfoAct() {
        return bInfoAct;
    }

    /**
     * 设置 特殊年计息天数
     * @param bInfoAct 特殊年计息天数
     */
    public void setbInfoAct(BigDecimal bInfoAct) {
        this.bInfoAct = bInfoAct;
    }

    /**
     * 取得 发行手续费率(%)
     * @return 发行手续费率(%)
     */
    public BigDecimal getbIssueFee() {
        return bIssueFee;
    }

    /**
     * 设置 发行手续费率(%)
     * @param bIssueFee 发行手续费率(%)
     */
    public void setbIssueFee(BigDecimal bIssueFee) {
        this.bIssueFee = bIssueFee;
    }

    /**
     * 取得 兑付手续费率(%)
     * @return 兑付手续费率(%)
     */
    public BigDecimal getbRedemptionFeeration() {
        return bRedemptionFeeration;
    }

    /**
     * 设置 兑付手续费率(%)
     * @param bRedemptionFeeration 兑付手续费率(%)
     */
    public void setbRedemptionFeeration(BigDecimal bRedemptionFeeration) {
        this.bRedemptionFeeration = bRedemptionFeeration;
    }

    /**
     * 取得 所得税率
     * @return 所得税率
     */
    public BigDecimal getbInfoTaxrate() {
        return bInfoTaxrate;
    }

    /**
     * 设置 所得税率
     * @param bInfoTaxrate 所得税率
     */
    public void setbInfoTaxrate(BigDecimal bInfoTaxrate) {
        this.bInfoTaxrate = bInfoTaxrate;
    }

    /**
     * 取得 货币代码
     * @return 货币代码
     */
    public String getCrncyCode() {
        return crncyCode;
    }

    /**
     * 设置 货币代码
     * @param crncyCode 货币代码
     */
    public void setCrncyCode(String crncyCode) {
        this.crncyCode = crncyCode == null ? null : crncyCode.trim();
    }

    /**
     * 取得 债券简称
     * @return 债券简称
     */
    public String getsInfoName() {
        return sInfoName;
    }

    /**
     * 设置 债券简称
     * @param sInfoName 债券简称
     */
    public void setsInfoName(String sInfoName) {
        this.sInfoName = sInfoName == null ? null : sInfoName.trim();
    }

    /**
     * 取得 交易所
     * @return 交易所
     */
    public String getsInfoExchmarket() {
        return sInfoExchmarket;
    }

    /**
     * 设置 交易所
     * @param sInfoExchmarket 交易所
     */
    public void setsInfoExchmarket(String sInfoExchmarket) {
        this.sInfoExchmarket = sInfoExchmarket == null ? null : sInfoExchmarket.trim();
    }

    /**
     * 取得 担保人
     * @return 担保人
     */
    public String getbInfoGuarantor() {
        return bInfoGuarantor;
    }

    /**
     * 设置 担保人
     * @param bInfoGuarantor 担保人
     */
    public void setbInfoGuarantor(String bInfoGuarantor) {
        this.bInfoGuarantor = bInfoGuarantor == null ? null : bInfoGuarantor.trim();
    }

    /**
     * 取得 担保方式
     * @return 担保方式
     */
    public Integer getbInfoGuartype() {
        return bInfoGuartype;
    }

    /**
     * 设置 担保方式
     * @param bInfoGuartype 担保方式
     */
    public void setbInfoGuartype(Integer bInfoGuartype) {
        this.bInfoGuartype = bInfoGuartype;
    }

    /**
     * 取得 上市日期
     * @return 上市日期
     */
    public String getbInfoListdate() {
        return bInfoListdate;
    }

    /**
     * 设置 上市日期
     * @param bInfoListdate 上市日期
     */
    public void setbInfoListdate(String bInfoListdate) {
        this.bInfoListdate = bInfoListdate == null ? null : bInfoListdate.trim();
    }

    /**
     * 取得 年内序号
     * @return 年内序号
     */
    public BigDecimal getbInfoYearsnumber() {
        return bInfoYearsnumber;
    }

    /**
     * 设置 年内序号
     * @param bInfoYearsnumber 年内序号
     */
    public void setbInfoYearsnumber(BigDecimal bInfoYearsnumber) {
        this.bInfoYearsnumber = bInfoYearsnumber;
    }

    /**
     * 取得 兑付登记起始日
     * @return 兑付登记起始日
     */
    public String getsDivRecorddate() {
        return sDivRecorddate;
    }

    /**
     * 设置 兑付登记起始日
     * @param sDivRecorddate 兑付登记起始日
     */
    public void setsDivRecorddate(String sDivRecorddate) {
        this.sDivRecorddate = sDivRecorddate == null ? null : sDivRecorddate.trim();
    }

    /**
     * 取得 上网发行认购代码
     * @return 上网发行认购代码
     */
    public String getbInfoCodebyplacing() {
        return bInfoCodebyplacing;
    }

    /**
     * 设置 上网发行认购代码
     * @param bInfoCodebyplacing 上网发行认购代码
     */
    public void setbInfoCodebyplacing(String bInfoCodebyplacing) {
        this.bInfoCodebyplacing = bInfoCodebyplacing == null ? null : bInfoCodebyplacing.trim();
    }

    /**
     * 取得 退市日期
     * @return 退市日期
     */
    public String getbInfoDelistdate() {
        return bInfoDelistdate;
    }

    /**
     * 设置 退市日期
     * @param bInfoDelistdate 退市日期
     */
    public void setbInfoDelistdate(String bInfoDelistdate) {
        this.bInfoDelistdate = bInfoDelistdate == null ? null : bInfoDelistdate.trim();
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public Integer getbInfoIssuetype() {
        return bInfoIssuetype;
    }

    /**
     * 设置 发行方式
     * @param bInfoIssuetype 发行方式
     */
    public void setbInfoIssuetype(Integer bInfoIssuetype) {
        this.bInfoIssuetype = bInfoIssuetype;
    }

    /**
     * 取得 担保简介
     * @return 担保简介
     */
    public String getbInfoGuarintroduction() {
        return bInfoGuarintroduction;
    }

    /**
     * 设置 担保简介
     * @param bInfoGuarintroduction 担保简介
     */
    public void setbInfoGuarintroduction(String bInfoGuarintroduction) {
        this.bInfoGuarintroduction = bInfoGuarintroduction == null ? null : bInfoGuarintroduction.trim();
    }

    /**
     * 取得 上网发行起始日期
     * @return 上网发行起始日期
     */
    public String getbInfoBgndbyplacing() {
        return bInfoBgndbyplacing;
    }

    /**
     * 设置 上网发行起始日期
     * @param bInfoBgndbyplacing 上网发行起始日期
     */
    public void setbInfoBgndbyplacing(String bInfoBgndbyplacing) {
        this.bInfoBgndbyplacing = bInfoBgndbyplacing == null ? null : bInfoBgndbyplacing.trim();
    }

    /**
     * 取得 上网发行截止日期
     * @return 上网发行截止日期
     */
    public String getbInfoEnddbyplacing() {
        return bInfoEnddbyplacing;
    }

    /**
     * 设置 上网发行截止日期
     * @param bInfoEnddbyplacing 上网发行截止日期
     */
    public void setbInfoEnddbyplacing(String bInfoEnddbyplacing) {
        this.bInfoEnddbyplacing = bInfoEnddbyplacing == null ? null : bInfoEnddbyplacing.trim();
    }

    /**
     * 取得 上网发行数量(亿元)

     * @return 上网发行数量(亿元)

     */
    public BigDecimal getbInfoAmountbyplacing() {
        return bInfoAmountbyplacing;
    }

    /**
     * 设置 上网发行数量(亿元)

     * @param bInfoAmountbyplacing 上网发行数量(亿元)

     */
    public void setbInfoAmountbyplacing(BigDecimal bInfoAmountbyplacing) {
        this.bInfoAmountbyplacing = bInfoAmountbyplacing;
    }

    /**
     * 取得 承销方式代码
     * @return 承销方式代码
     */
    public Integer getbInfoUnderwritingcode() {
        return bInfoUnderwritingcode;
    }

    /**
     * 设置 承销方式代码
     * @param bInfoUnderwritingcode 承销方式代码
     */
    public void setbInfoUnderwritingcode(Integer bInfoUnderwritingcode) {
        this.bInfoUnderwritingcode = bInfoUnderwritingcode;
    }

    /**
     * 取得 发行人编号
     * @return 发行人编号
     */
    public String getbInfoIssuercode() {
        return bInfoIssuercode;
    }

    /**
     * 设置 发行人编号
     * @param bInfoIssuercode 发行人编号
     */
    public void setbInfoIssuercode(String bInfoIssuercode) {
        this.bInfoIssuercode = bInfoIssuercode == null ? null : bInfoIssuercode.trim();
    }

    /**
     * 取得 原债券代码
     * @return 原债券代码
     */
    public String getbInfoFormercode() {
        return bInfoFormercode;
    }

    /**
     * 设置 原债券代码
     * @param bInfoFormercode 原债券代码
     */
    public void setbInfoFormercode(String bInfoFormercode) {
        this.bInfoFormercode = bInfoFormercode == null ? null : bInfoFormercode.trim();
    }

    /**
     * 取得 利率说明
     * @return 利率说明
     */
    public String getbInfoCoupontxt() {
        return bInfoCoupontxt;
    }

    /**
     * 设置 利率说明
     * @param bInfoCoupontxt 利率说明
     */
    public void setbInfoCoupontxt(String bInfoCoupontxt) {
        this.bInfoCoupontxt = bInfoCoupontxt == null ? null : bInfoCoupontxt.trim();
    }

    /**
     * 取得 是否发行失败
     * @return 是否发行失败
     */
    public Integer getIsFailure() {
        return isFailure;
    }

    /**
     * 设置 是否发行失败
     * @param isFailure 是否发行失败
     */
    public void setIsFailure(Integer isFailure) {
        this.isFailure = isFailure;
    }

    /**
     * 取得 是否跨市场
     * @return 是否跨市场
     */
    public Integer getIsCrossmarket() {
        return isCrossmarket;
    }

    /**
     * 设置 是否跨市场
     * @param isCrossmarket 是否跨市场
     */
    public void setIsCrossmarket(Integer isCrossmarket) {
        this.isCrossmarket = isCrossmarket;
    }

    /**
     * 取得 付息日说明
     * @return 付息日说明
     */
    public String getbInfoCoupondatetxt() {
        return bInfoCoupondatetxt;
    }

    /**
     * 设置 付息日说明
     * @param bInfoCoupondatetxt 付息日说明
     */
    public void setbInfoCoupondatetxt(String bInfoCoupondatetxt) {
        this.bInfoCoupondatetxt = bInfoCoupondatetxt == null ? null : bInfoCoupondatetxt.trim();
    }

    /**
     * 取得 是否次级债或混合资本债

     * @return 是否次级债或混合资本债

     */
    public Integer getbInfoSubordinateornot() {
        return bInfoSubordinateornot;
    }

    /**
     * 设置 是否次级债或混合资本债

     * @param bInfoSubordinateornot 是否次级债或混合资本债

     */
    public void setbInfoSubordinateornot(Integer bInfoSubordinateornot) {
        this.bInfoSubordinateornot = bInfoSubordinateornot;
    }

    /**
     * 取得 参考收益率
     * @return 参考收益率
     */
    public BigDecimal getbTendrstReferyield() {
        return bTendrstReferyield;
    }

    /**
     * 设置 参考收益率
     * @param bTendrstReferyield 参考收益率
     */
    public void setbTendrstReferyield(BigDecimal bTendrstReferyield) {
        this.bTendrstReferyield = bTendrstReferyield;
    }

    /**
     * 取得 最新面值
     * @return 最新面值
     */
    public BigDecimal getbInfoCurpar() {
        return bInfoCurpar;
    }

    /**
     * 设置 最新面值
     * @param bInfoCurpar 最新面值
     */
    public void setbInfoCurpar(BigDecimal bInfoCurpar) {
        this.bInfoCurpar = bInfoCurpar;
    }

    /**
     * 取得 原Wind代码
     * @return 原Wind代码
     */
    public String getsInfoFormerwindcode() {
        return sInfoFormerwindcode;
    }

    /**
     * 设置 原Wind代码
     * @param sInfoFormerwindcode 原Wind代码
     */
    public void setsInfoFormerwindcode(String sInfoFormerwindcode) {
        this.sInfoFormerwindcode = sInfoFormerwindcode == null ? null : sInfoFormerwindcode.trim();
    }

    /**
     * 取得 操作日期
     * @return 操作日期
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 操作日期
     * @param opdate 操作日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 操作类型
     * @return 操作类型
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 操作类型
     * @param opmode 操作类型
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}