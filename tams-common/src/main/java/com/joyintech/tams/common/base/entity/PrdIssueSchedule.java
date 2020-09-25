package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PrdIssueSchedule extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    private String finprodMarketId;

    /**
     * 产品简称
     */
    private String finprodAbbr;

    /**
     * 产品名称
     */
    private String finprodName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 募集起始日
     */
    private Date raiseStrdate;

    /**
     * 募集结束日
     */
    private Date raiseEnddate;

    /**
     * 认购起点金额（底层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal toffStartLow;

    /**
     * 客户收益率（底层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldLow;

    /**
     * 认购起点金额（中层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal toffStartMid;

    /**
     * 客户收益率（中层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldMid;

    /**
     * 认购起点金额（高层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal toffStartHig;

    /**
     * 客户收益率（高层）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldHig;

    /**
     * 追加金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowestAmt;

    /**
     * 募集上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseMax;

    /**
     * 销售费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleFeeRate;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trustFeeRate;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 付息周期
     */
    private String intpayCyc;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 分行
     */
    private String branchBank;

    /**
     * 规律开放周期
     */
    private String openCycle;

    /**
     * 其它规律开放周期
     */
    private Long openCycleOth;

    /**
     * 无规律开放说明
     */
    private String openRemark;

    /**
     * 首次开放周期起始日
     */
    private Date firstOpenDay;

    /**
     * 节假日是否开放
     */
    private String hdayOpenFlag;

    /**
     * 平均开放次数（年化）
     */
    private Long aveOpenTime;

    /**
     * 开放期业务,仅申购、仅赎回、申赎皆可
     */
    private String openBuss;

    /**
     * 开放期业务说明
     */
    private String openBussRemark;

    /**
     * 申报附件方案
     */
    private String schemeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 市场代码，可以按规则生成，或者手输，不校验唯一
     * @return 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 市场代码，可以按规则生成，或者手输，不校验唯一
     * @param finprodMarketId 市场代码，可以按规则生成，或者手输，不校验唯一
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId == null ? null : finprodMarketId.trim();
    }

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 产品简称
     * @param finprodAbbr 产品简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr == null ? null : finprodAbbr.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 产品名称
     * @param finprodName 产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
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
     * 取得 募集起始日
     * @return 募集起始日
     */
    public Date getRaiseStrdate() {
        return raiseStrdate;
    }

    /**
     * 设置 募集起始日
     * @param raiseStrdate 募集起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseStrdate(Date raiseStrdate) {
        this.raiseStrdate = raiseStrdate;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日
     * @param raiseEnddate 募集结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 认购起点金额（底层）
     * @return 认购起点金额（底层）
     */
    public BigDecimal getToffStartLow() {
        return toffStartLow;
    }

    /**
     * 设置 认购起点金额（底层）
     * @param toffStartLow 认购起点金额（底层）
     */
    public void setToffStartLow(BigDecimal toffStartLow) {
        this.toffStartLow = toffStartLow;
    }

    /**
     * 取得 客户收益率（底层）
     * @return 客户收益率（底层）
     */
    public BigDecimal getYieldLow() {
        return yieldLow;
    }

    /**
     * 设置 客户收益率（底层）
     * @param yieldLow 客户收益率（底层）
     */
    public void setYieldLow(BigDecimal yieldLow) {
        this.yieldLow = yieldLow;
    }

    /**
     * 取得 认购起点金额（中层）
     * @return 认购起点金额（中层）
     */
    public BigDecimal getToffStartMid() {
        return toffStartMid;
    }

    /**
     * 设置 认购起点金额（中层）
     * @param toffStartMid 认购起点金额（中层）
     */
    public void setToffStartMid(BigDecimal toffStartMid) {
        this.toffStartMid = toffStartMid;
    }

    /**
     * 取得 客户收益率（中层）
     * @return 客户收益率（中层）
     */
    public BigDecimal getYieldMid() {
        return yieldMid;
    }

    /**
     * 设置 客户收益率（中层）
     * @param yieldMid 客户收益率（中层）
     */
    public void setYieldMid(BigDecimal yieldMid) {
        this.yieldMid = yieldMid;
    }

    /**
     * 取得 认购起点金额（高层）
     * @return 认购起点金额（高层）
     */
    public BigDecimal getToffStartHig() {
        return toffStartHig;
    }

    /**
     * 设置 认购起点金额（高层）
     * @param toffStartHig 认购起点金额（高层）
     */
    public void setToffStartHig(BigDecimal toffStartHig) {
        this.toffStartHig = toffStartHig;
    }

    /**
     * 取得 客户收益率（高层）
     * @return 客户收益率（高层）
     */
    public BigDecimal getYieldHig() {
        return yieldHig;
    }

    /**
     * 设置 客户收益率（高层）
     * @param yieldHig 客户收益率（高层）
     */
    public void setYieldHig(BigDecimal yieldHig) {
        this.yieldHig = yieldHig;
    }

    /**
     * 取得 追加金额
     * @return 追加金额
     */
    public BigDecimal getLowestAmt() {
        return lowestAmt;
    }

    /**
     * 设置 追加金额
     * @param lowestAmt 追加金额
     */
    public void setLowestAmt(BigDecimal lowestAmt) {
        this.lowestAmt = lowestAmt;
    }

    /**
     * 取得 募集上限
     * @return 募集上限
     */
    public BigDecimal getRaiseMax() {
        return raiseMax;
    }

    /**
     * 设置 募集上限
     * @param raiseMax 募集上限
     */
    public void setRaiseMax(BigDecimal raiseMax) {
        this.raiseMax = raiseMax;
    }

    /**
     * 取得 销售费率
     * @return 销售费率
     */
    public BigDecimal getSaleFeeRate() {
        return saleFeeRate;
    }

    /**
     * 设置 销售费率
     * @param saleFeeRate 销售费率
     */
    public void setSaleFeeRate(BigDecimal saleFeeRate) {
        this.saleFeeRate = saleFeeRate;
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getTrustFeeRate() {
        return trustFeeRate;
    }

    /**
     * 设置 托管费率
     * @param trustFeeRate 托管费率
     */
    public void setTrustFeeRate(BigDecimal trustFeeRate) {
        this.trustFeeRate = trustFeeRate;
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 发行方式
     * @param issueMode 发行方式
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 取得 付息周期
     * @return 付息周期
     */
    public String getIntpayCyc() {
        return intpayCyc;
    }

    /**
     * 设置 付息周期
     * @param intpayCyc 付息周期
     */
    public void setIntpayCyc(String intpayCyc) {
        this.intpayCyc = intpayCyc == null ? null : intpayCyc.trim();
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
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prodRegistCode 产品登记编码
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode == null ? null : prodRegistCode.trim();
    }

    /**
     * 取得 分行
     * @return 分行
     */
    public String getBranchBank() {
        return branchBank;
    }

    /**
     * 设置 分行
     * @param branchBank 分行
     */
    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank == null ? null : branchBank.trim();
    }

    /**
     * 取得 规律开放周期
     * @return 规律开放周期
     */
    public String getOpenCycle() {
        return openCycle;
    }

    /**
     * 设置 规律开放周期
     * @param openCycle 规律开放周期
     */
    public void setOpenCycle(String openCycle) {
        this.openCycle = openCycle == null ? null : openCycle.trim();
    }

    /**
     * 取得 其它规律开放周期
     * @return 其它规律开放周期
     */
    public Long getOpenCycleOth() {
        return openCycleOth;
    }

    /**
     * 设置 其它规律开放周期
     * @param openCycleOth 其它规律开放周期
     */
    public void setOpenCycleOth(Long openCycleOth) {
        this.openCycleOth = openCycleOth;
    }

    /**
     * 取得 无规律开放说明
     * @return 无规律开放说明
     */
    public String getOpenRemark() {
        return openRemark;
    }

    /**
     * 设置 无规律开放说明
     * @param openRemark 无规律开放说明
     */
    public void setOpenRemark(String openRemark) {
        this.openRemark = openRemark == null ? null : openRemark.trim();
    }

    /**
     * 取得 首次开放周期起始日
     * @return 首次开放周期起始日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放周期起始日
     * @param firstOpenDay 首次开放周期起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 节假日是否开放
     * @return 节假日是否开放
     */
    public String getHdayOpenFlag() {
        return hdayOpenFlag;
    }

    /**
     * 设置 节假日是否开放
     * @param hdayOpenFlag 节假日是否开放
     */
    public void setHdayOpenFlag(String hdayOpenFlag) {
        this.hdayOpenFlag = hdayOpenFlag == null ? null : hdayOpenFlag.trim();
    }

    /**
     * 取得 平均开放次数（年化）
     * @return 平均开放次数（年化）
     */
    public Long getAveOpenTime() {
        return aveOpenTime;
    }

    /**
     * 设置 平均开放次数（年化）
     * @param aveOpenTime 平均开放次数（年化）
     */
    public void setAveOpenTime(Long aveOpenTime) {
        this.aveOpenTime = aveOpenTime;
    }

    /**
     * 取得 开放期业务,仅申购、仅赎回、申赎皆可
     * @return 开放期业务,仅申购、仅赎回、申赎皆可
     */
    public String getOpenBuss() {
        return openBuss;
    }

    /**
     * 设置 开放期业务,仅申购、仅赎回、申赎皆可
     * @param openBuss 开放期业务,仅申购、仅赎回、申赎皆可
     */
    public void setOpenBuss(String openBuss) {
        this.openBuss = openBuss == null ? null : openBuss.trim();
    }

    /**
     * 取得 开放期业务说明
     * @return 开放期业务说明
     */
    public String getOpenBussRemark() {
        return openBussRemark;
    }

    /**
     * 设置 开放期业务说明
     * @param openBussRemark 开放期业务说明
     */
    public void setOpenBussRemark(String openBussRemark) {
        this.openBussRemark = openBussRemark == null ? null : openBussRemark.trim();
    }

    /**
     * 取得 申报附件方案
     * @return 申报附件方案
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 申报附件方案
     * @param schemeId 申报附件方案
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
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
}