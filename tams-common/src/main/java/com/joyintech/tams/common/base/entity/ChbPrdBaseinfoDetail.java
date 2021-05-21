package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdBaseinfoDetail extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 机构代码
     */
    private String issuePartyId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 行内标识码
     */
    private String prodInsId;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodStage;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    private String profitType;

    /**
     * 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    private String termtype;

    /**
     * 是否金融同业专属
     */
    private String excFinSector;

    /**
     * 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    private String operateMode;

    /**
     * 是否设置最短持有期限
     */
    private String isShortTerm;

    /**
     * 最短持有期限(天)
     */
    private Long shortTerm;

    /**
     * 是否现金管理类
     */
    private String cashManaCate;

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    private String contMode;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 兑付本金币种
     */
    private String cashPrinCcy;

    /**
     * 兑付收益币种
     */
    private String cashProfCcy;

    /**
     * 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    private String investArae;

    /**
     * 境内托管机构名称
     */
    private String inDepName;

    /**
     * 境内托管机构代码
     */
    private String inDepCode;

    /**
     * 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    private String riskLevel;

    /**
     * 发行机构提前终止权标识
     */
    private String termRightFlag;

    /**
     * 客户赎回权标识
     */
    private String cusRedeemFlag;

    /**
     * 产品增信标识
     */
    private String creditFlag;

    /**
     * 产品增信机构类型
     */
    private String creditType;

    /**
     * 产品定价方式
     */
    private String pricedMode;

    /**
     * 计息基础 A/360, A/365
     */
    private String basic;

    /**
     * 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    private String prodtype;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 提前终止日
     */
    private Date terminatedate;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 是否结构化分层产品
     */
    private String isConstracte;

    /**
     * 预计最低收益率-发行
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYieldIssue;

    /**
     * 预计最高收益率-发行
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYieldIssue;

    /**
     * 业绩比较基准下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldLow;

    /**
     * 业绩比较基准上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldHigh;

    /**
     * 开放模式01 有规律开放/02 无规律开放
     */
    private String openMode;

    /**
     * 规律开放周期
     */
    private String openCircle;

    /**
     * 其他规律开放周期（天）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openCircleDays;

    /**
     * 首次开放日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放
     */
    private String holidayOnFlag;

    /**
     * 平均开放次数（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgOpenCount;

    /**
     * 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    private String openBuss;

    /**
     * 资金托管账号
     */
    private String busiAcctNo;

    /**
     * 资金托管账户
     */
    private String acctName;

    /**
     * 受托职责
     */
    private String fiduciaryDuty;

    /**
     * 转让类型
     */
    private String transferType;

    /**
     * 货币市场基金标识
     */
    private String isMarketFund;

    /**
     * 管理方式
     */
    private String manageType;

    /**
     * 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    private String customerType;

    /**
     * 交易日历
     */
    private String calendarId;

    /**
     * 是否母行划转产品
     */
    private String isTransfer;

    /**
     * 现金管理类产品-是否完全符合
     */
    private String isFullyCompliant;

    /**
     * 是否养老理财产品
     */
    private String isYllccp;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 机构代码
     * @param issuePartyId 机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 设置 产品登记编码
     * @param regCode 产品登记编码
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    /**
     * 取得 行内标识码
     * @return 行内标识码
     */
    public String getProdInsId() {
        return prodInsId;
    }

    /**
     * 设置 行内标识码
     * @param prodInsId 行内标识码
     */
    public void setProdInsId(String prodInsId) {
        this.prodInsId = prodInsId == null ? null : prodInsId.trim();
    }

    /**
     * 取得 产品品牌
     * @return 产品品牌
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 设置 产品品牌
     * @param prodBrand 产品品牌
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand == null ? null : prodBrand.trim();
    }

    /**
     * 取得 产品期次
     * @return 产品期次
     */
    public BigDecimal getProdStage() {
        return prodStage;
    }

    /**
     * 设置 产品期次
     * @param prodStage 产品期次
     */
    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @return 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     * @param profitType 产品收益类型  01 保证收益/02 保本浮动收益/03 非保本浮动收益
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     * @return 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    public String getTermtype() {
        return termtype;
    }

    /**
     * 设置 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     * @param termtype 产品期限01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/08 1-3年（含）/09 3年以上
     */
    public void setTermtype(String termtype) {
        this.termtype = termtype == null ? null : termtype.trim();
    }

    /**
     * 取得 是否金融同业专属
     * @return 是否金融同业专属
     */
    public String getExcFinSector() {
        return excFinSector;
    }

    /**
     * 设置 是否金融同业专属
     * @param excFinSector 是否金融同业专属
     */
    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector == null ? null : excFinSector.trim();
    }

    /**
     * 取得 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @return 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 设置 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     * @param operateMode 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 是否设置最短持有期限
     * @return 是否设置最短持有期限
     */
    public String getIsShortTerm() {
        return isShortTerm;
    }

    /**
     * 设置 是否设置最短持有期限
     * @param isShortTerm 是否设置最短持有期限
     */
    public void setIsShortTerm(String isShortTerm) {
        this.isShortTerm = isShortTerm == null ? null : isShortTerm.trim();
    }

    /**
     * 取得 最短持有期限(天)
     * @return 最短持有期限(天)
     */
    public Long getShortTerm() {
        return shortTerm;
    }

    /**
     * 设置 最短持有期限(天)
     * @param shortTerm 最短持有期限(天)
     */
    public void setShortTerm(Long shortTerm) {
        this.shortTerm = shortTerm;
    }

    /**
     * 取得 是否现金管理类
     * @return 是否现金管理类
     */
    public String getCashManaCate() {
        return cashManaCate;
    }

    /**
     * 设置 是否现金管理类
     * @param cashManaCate 是否现金管理类
     */
    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate == null ? null : cashManaCate.trim();
    }

    /**
     * 取得 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 设置 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 取得 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     * @return 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    public String getContMode() {
        return contMode;
    }

    /**
     * 设置 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     * @param contMode 合作模式  01 独立运作/02 银信/03 银保/04 银基/05 银证/07 混合类/99 其他
     */
    public void setContMode(String contMode) {
        this.contMode = contMode == null ? null : contMode.trim();
    }

    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 募集币种
     * @param raiseCcy 募集币种
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 兑付本金币种
     * @return 兑付本金币种
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 设置 兑付本金币种
     * @param cashPrinCcy 兑付本金币种
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy == null ? null : cashPrinCcy.trim();
    }

    /**
     * 取得 兑付收益币种
     * @return 兑付收益币种
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 设置 兑付收益币种
     * @param cashProfCcy 兑付收益币种
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy == null ? null : cashProfCcy.trim();
    }

    /**
     * 取得 资金投向地区  01 境内/02 境外/03 境内和境外
     * @return 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    public String getInvestArae() {
        return investArae;
    }

    /**
     * 设置 资金投向地区  01 境内/02 境外/03 境内和境外
     * @param investArae 资金投向地区  01 境内/02 境外/03 境内和境外
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae == null ? null : investArae.trim();
    }

    /**
     * 取得 境内托管机构名称
     * @return 境内托管机构名称
     */
    public String getInDepName() {
        return inDepName;
    }

    /**
     * 设置 境内托管机构名称
     * @param inDepName 境内托管机构名称
     */
    public void setInDepName(String inDepName) {
        this.inDepName = inDepName == null ? null : inDepName.trim();
    }

    /**
     * 取得 境内托管机构代码
     * @return 境内托管机构代码
     */
    public String getInDepCode() {
        return inDepCode;
    }

    /**
     * 设置 境内托管机构代码
     * @param inDepCode 境内托管机构代码
     */
    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode == null ? null : inDepCode.trim();
    }

    /**
     * 取得 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     * @return 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     * @param riskLevel 产品风险等级  01 一级（低）/02 二级（中低）/03 三级（中）/04 四级（中高）/05 五级（高）
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 发行机构提前终止权标识
     * @return 发行机构提前终止权标识
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 设置 发行机构提前终止权标识
     * @param termRightFlag 发行机构提前终止权标识
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag == null ? null : termRightFlag.trim();
    }

    /**
     * 取得 客户赎回权标识
     * @return 客户赎回权标识
     */
    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    /**
     * 设置 客户赎回权标识
     * @param cusRedeemFlag 客户赎回权标识
     */
    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag == null ? null : cusRedeemFlag.trim();
    }

    /**
     * 取得 产品增信标识
     * @return 产品增信标识
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    /**
     * 设置 产品增信标识
     * @param creditFlag 产品增信标识
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag == null ? null : creditFlag.trim();
    }

    /**
     * 取得 产品增信机构类型
     * @return 产品增信机构类型
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 产品增信机构类型
     * @param creditType 产品增信机构类型
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 产品定价方式
     * @return 产品定价方式
     */
    public String getPricedMode() {
        return pricedMode;
    }

    /**
     * 设置 产品定价方式
     * @param pricedMode 产品定价方式
     */
    public void setPricedMode(String pricedMode) {
        this.pricedMode = pricedMode == null ? null : pricedMode.trim();
    }

    /**
     * 取得 计息基础 A/360, A/365
     * @return 计息基础 A/360, A/365
     */
    public String getBasic() {
        return basic;
    }

    /**
     * 设置 计息基础 A/360, A/365
     * @param basic 计息基础 A/360, A/365
     */
    public void setBasic(String basic) {
        this.basic = basic == null ? null : basic.trim();
    }

    /**
     * 取得 标识哪种产品，净值N，货币M，利率R，客户周期C
     * @return 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    public String getProdtype() {
        return prodtype;
    }

    /**
     * 设置 标识哪种产品，净值N，货币M，利率R，客户周期C
     * @param prodtype 标识哪种产品，净值N，货币M，利率R，客户周期C
     */
    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 提前终止日
     * @return 提前终止日
     */
    public Date getTerminatedate() {
        return terminatedate;
    }

    /**
     * 设置 提前终止日
     * @param terminatedate 提前终止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTerminatedate(Date terminatedate) {
        this.terminatedate = terminatedate;
    }

    /**
     * 取得 募集起始日期
     * @return 募集起始日期
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    /**
     * 设置 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    /**
     * 取得 募集结束日期
     * @return 募集结束日期
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日期
     * @param raiseEnddate 募集结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 是否结构化分层产品
     * @return 是否结构化分层产品
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 是否结构化分层产品
     * @param isConstracte 是否结构化分层产品
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 取得 预计最低收益率-发行
     * @return 预计最低收益率-发行
     */
    public BigDecimal getLowYieldIssue() {
        return lowYieldIssue;
    }

    /**
     * 设置 预计最低收益率-发行
     * @param lowYieldIssue 预计最低收益率-发行
     */
    public void setLowYieldIssue(BigDecimal lowYieldIssue) {
        this.lowYieldIssue = lowYieldIssue;
    }

    /**
     * 取得 预计最高收益率-发行
     * @return 预计最高收益率-发行
     */
    public BigDecimal getHighYieldIssue() {
        return highYieldIssue;
    }

    /**
     * 设置 预计最高收益率-发行
     * @param highYieldIssue 预计最高收益率-发行
     */
    public void setHighYieldIssue(BigDecimal highYieldIssue) {
        this.highYieldIssue = highYieldIssue;
    }

    /**
     * 取得 业绩比较基准下限
     * @return 业绩比较基准下限
     */
    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    /**
     * 设置 业绩比较基准下限
     * @param yieldLow 业绩比较基准下限
     */
    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    /**
     * 取得 业绩比较基准上限
     * @return 业绩比较基准上限
     */
    public BigDecimal getYieldHigh() {
        return yieldHigh;
    }

    /**
     * 设置 业绩比较基准上限
     * @param yieldHigh 业绩比较基准上限
     */
    public void setYieldHigh(BigDecimal yieldHigh) {
        this.yieldHigh = yieldHigh;
    }

    /**
     * 取得 开放模式01 有规律开放/02 无规律开放
     * @return 开放模式01 有规律开放/02 无规律开放
     */
    public String getOpenMode() {
        return openMode;
    }

    /**
     * 设置 开放模式01 有规律开放/02 无规律开放
     * @param openMode 开放模式01 有规律开放/02 无规律开放
     */
    public void setOpenMode(String openMode) {
        this.openMode = openMode == null ? null : openMode.trim();
    }

    /**
     * 取得 规律开放周期
     * @return 规律开放周期
     */
    public String getOpenCircle() {
        return openCircle;
    }

    /**
     * 设置 规律开放周期
     * @param openCircle 规律开放周期
     */
    public void setOpenCircle(String openCircle) {
        this.openCircle = openCircle == null ? null : openCircle.trim();
    }

    /**
     * 取得 其他规律开放周期（天）
     * @return 其他规律开放周期（天）
     */
    public BigDecimal getOpenCircleDays() {
        return openCircleDays;
    }

    /**
     * 设置 其他规律开放周期（天）
     * @param openCircleDays 其他规律开放周期（天）
     */
    public void setOpenCircleDays(BigDecimal openCircleDays) {
        this.openCircleDays = openCircleDays;
    }

    /**
     * 取得 首次开放日
     * @return 首次开放日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放日
     * @param firstOpenDay 首次开放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 节假日是否开放
     * @return 节假日是否开放
     */
    public String getHolidayOnFlag() {
        return holidayOnFlag;
    }

    /**
     * 设置 节假日是否开放
     * @param holidayOnFlag 节假日是否开放
     */
    public void setHolidayOnFlag(String holidayOnFlag) {
        this.holidayOnFlag = holidayOnFlag == null ? null : holidayOnFlag.trim();
    }

    /**
     * 取得 平均开放次数（年化）
     * @return 平均开放次数（年化）
     */
    public BigDecimal getAvgOpenCount() {
        return avgOpenCount;
    }

    /**
     * 设置 平均开放次数（年化）
     * @param avgOpenCount 平均开放次数（年化）
     */
    public void setAvgOpenCount(BigDecimal avgOpenCount) {
        this.avgOpenCount = avgOpenCount;
    }

    /**
     * 取得 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     * @return 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 设置 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     * @param openBuss 开放期业务01申赎皆可/02 仅可申购无限制/ 03仅可赎回无限制 /04仅可申购有限制/05仅可赎回有限制 /99其他
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss == null ? null : openBuss.trim();
    }

    /**
     * 取得 资金托管账号
     * @return 资金托管账号
     */
    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    /**
     * 设置 资金托管账号
     * @param busiAcctNo 资金托管账号
     */
    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo == null ? null : busiAcctNo.trim();
    }

    /**
     * 取得 资金托管账户
     * @return 资金托管账户
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金托管账户
     * @param acctName 资金托管账户
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    /**
     * 取得 受托职责
     * @return 受托职责
     */
    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    /**
     * 设置 受托职责
     * @param fiduciaryDuty 受托职责
     */
    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty == null ? null : fiduciaryDuty.trim();
    }

    /**
     * 取得 转让类型
     * @return 转让类型
     */
    public String getTransferType() {
        return transferType;
    }

    /**
     * 设置 转让类型
     * @param transferType 转让类型
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    /**
     * 取得 货币市场基金标识
     * @return 货币市场基金标识
     */
    public String getIsMarketFund() {
        return isMarketFund;
    }

    /**
     * 设置 货币市场基金标识
     * @param isMarketFund 货币市场基金标识
     */
    public void setIsMarketFund(String isMarketFund) {
        this.isMarketFund = isMarketFund == null ? null : isMarketFund.trim();
    }

    /**
     * 取得 管理方式
     * @return 管理方式
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式
     * @param manageType 管理方式
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 客户类型:单选，1-单位，2-个人，3-个人及单位
     * @return 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置 客户类型:单选，1-单位，2-个人，3-个人及单位
     * @param customerType 客户类型:单选，1-单位，2-个人，3-个人及单位
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 是否母行划转产品
     * @return 是否母行划转产品
     */
    public String getIsTransfer() {
        return isTransfer;
    }

    /**
     * 设置 是否母行划转产品
     * @param isTransfer 是否母行划转产品
     */
    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer == null ? null : isTransfer.trim();
    }

    /**
     * 取得 现金管理类产品-是否完全符合
     * @return 现金管理类产品-是否完全符合
     */
    public String getIsFullyCompliant() {
        return isFullyCompliant;
    }

    /**
     * 设置 现金管理类产品-是否完全符合
     * @param isFullyCompliant 现金管理类产品-是否完全符合
     */
    public void setIsFullyCompliant(String isFullyCompliant) {
        this.isFullyCompliant = isFullyCompliant == null ? null : isFullyCompliant.trim();
    }

    /**
     * 取得 是否养老理财产品
     * @return 是否养老理财产品
     */
    public String getIsYllccp() {
        return isYllccp;
    }

    /**
     * 设置 是否养老理财产品
     * @param isYllccp 是否养老理财产品
     */
    public void setIsYllccp(String isYllccp) {
        this.isYllccp = isYllccp == null ? null : isYllccp.trim();
    }
}
