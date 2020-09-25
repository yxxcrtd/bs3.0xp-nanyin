package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAstQdiiStruct extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 所属国家或地区
     */
    private String repCountryArea;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repPaperRate;

    /**
     * 付息频率(个月/次)
     */
    private Short repIntpayCycle;

    /**
     * 固定收益部分所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repIncomeRatio;

    /**
     * 衍生金融工具所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repDerInstrRatio;

    /**
     * 衍生金融工具具体投资方式
     */
    private String repDerInstrInvType;

    /**
     * 衍生金融工具挂钩标的资产
     */
    private String repDerInstrTarget;

    /**
     * 利息结算方式
     */
    private String repAcctMode;

    /**
     * 含权情况说明
     */
    private String repSpecialCmt;

    /**
     * 结构性票据最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMaxYield;

    /**
     * 结构性票据最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMinYield;

    /**
     * 挂钩标的资产基准价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBasicPrice;

    /**
     * 挂钩标的资产登记日价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRegDatePrice;

    /**
     * 交易费
     */
    private String repTradeFee;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 所属国家或地区
     * @return 所属国家或地区
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区
     * @param repCountryArea 所属国家或地区
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getRepVdate() {
        return repVdate;
    }

    /**
     * 设置 起息日
     * @param repVdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepVdate(Date repVdate) {
        this.repVdate = repVdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepMdate() {
        return repMdate;
    }

    /**
     * 设置 到期日
     * @param repMdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepMdate(Date repMdate) {
        this.repMdate = repMdate;
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getRepPaperRate() {
        return repPaperRate;
    }

    /**
     * 设置 票面利率
     * @param repPaperRate 票面利率
     */
    public void setRepPaperRate(BigDecimal repPaperRate) {
        this.repPaperRate = repPaperRate;
    }

    /**
     * 取得 付息频率(个月/次)
     * @return 付息频率(个月/次)
     */
    public Short getRepIntpayCycle() {
        return repIntpayCycle;
    }

    /**
     * 设置 付息频率(个月/次)
     * @param repIntpayCycle 付息频率(个月/次)
     */
    public void setRepIntpayCycle(Short repIntpayCycle) {
        this.repIntpayCycle = repIntpayCycle;
    }

    /**
     * 取得 固定收益部分所占比例
     * @return 固定收益部分所占比例
     */
    public BigDecimal getRepIncomeRatio() {
        return repIncomeRatio;
    }

    /**
     * 设置 固定收益部分所占比例
     * @param repIncomeRatio 固定收益部分所占比例
     */
    public void setRepIncomeRatio(BigDecimal repIncomeRatio) {
        this.repIncomeRatio = repIncomeRatio;
    }

    /**
     * 取得 衍生金融工具所占比例
     * @return 衍生金融工具所占比例
     */
    public BigDecimal getRepDerInstrRatio() {
        return repDerInstrRatio;
    }

    /**
     * 设置 衍生金融工具所占比例
     * @param repDerInstrRatio 衍生金融工具所占比例
     */
    public void setRepDerInstrRatio(BigDecimal repDerInstrRatio) {
        this.repDerInstrRatio = repDerInstrRatio;
    }

    /**
     * 取得 衍生金融工具具体投资方式
     * @return 衍生金融工具具体投资方式
     */
    public String getRepDerInstrInvType() {
        return repDerInstrInvType;
    }

    /**
     * 设置 衍生金融工具具体投资方式
     * @param repDerInstrInvType 衍生金融工具具体投资方式
     */
    public void setRepDerInstrInvType(String repDerInstrInvType) {
        this.repDerInstrInvType = repDerInstrInvType == null ? null : repDerInstrInvType.trim();
    }

    /**
     * 取得 衍生金融工具挂钩标的资产
     * @return 衍生金融工具挂钩标的资产
     */
    public String getRepDerInstrTarget() {
        return repDerInstrTarget;
    }

    /**
     * 设置 衍生金融工具挂钩标的资产
     * @param repDerInstrTarget 衍生金融工具挂钩标的资产
     */
    public void setRepDerInstrTarget(String repDerInstrTarget) {
        this.repDerInstrTarget = repDerInstrTarget == null ? null : repDerInstrTarget.trim();
    }

    /**
     * 取得 利息结算方式
     * @return 利息结算方式
     */
    public String getRepAcctMode() {
        return repAcctMode;
    }

    /**
     * 设置 利息结算方式
     * @param repAcctMode 利息结算方式
     */
    public void setRepAcctMode(String repAcctMode) {
        this.repAcctMode = repAcctMode == null ? null : repAcctMode.trim();
    }

    /**
     * 取得 含权情况说明
     * @return 含权情况说明
     */
    public String getRepSpecialCmt() {
        return repSpecialCmt;
    }

    /**
     * 设置 含权情况说明
     * @param repSpecialCmt 含权情况说明
     */
    public void setRepSpecialCmt(String repSpecialCmt) {
        this.repSpecialCmt = repSpecialCmt == null ? null : repSpecialCmt.trim();
    }

    /**
     * 取得 结构性票据最高收益率
     * @return 结构性票据最高收益率
     */
    public BigDecimal getRepMaxYield() {
        return repMaxYield;
    }

    /**
     * 设置 结构性票据最高收益率
     * @param repMaxYield 结构性票据最高收益率
     */
    public void setRepMaxYield(BigDecimal repMaxYield) {
        this.repMaxYield = repMaxYield;
    }

    /**
     * 取得 结构性票据最低收益率
     * @return 结构性票据最低收益率
     */
    public BigDecimal getRepMinYield() {
        return repMinYield;
    }

    /**
     * 设置 结构性票据最低收益率
     * @param repMinYield 结构性票据最低收益率
     */
    public void setRepMinYield(BigDecimal repMinYield) {
        this.repMinYield = repMinYield;
    }

    /**
     * 取得 挂钩标的资产基准价格
     * @return 挂钩标的资产基准价格
     */
    public BigDecimal getRepBasicPrice() {
        return repBasicPrice;
    }

    /**
     * 设置 挂钩标的资产基准价格
     * @param repBasicPrice 挂钩标的资产基准价格
     */
    public void setRepBasicPrice(BigDecimal repBasicPrice) {
        this.repBasicPrice = repBasicPrice;
    }

    /**
     * 取得 挂钩标的资产登记日价格
     * @return 挂钩标的资产登记日价格
     */
    public BigDecimal getRepRegDatePrice() {
        return repRegDatePrice;
    }

    /**
     * 设置 挂钩标的资产登记日价格
     * @param repRegDatePrice 挂钩标的资产登记日价格
     */
    public void setRepRegDatePrice(BigDecimal repRegDatePrice) {
        this.repRegDatePrice = repRegDatePrice;
    }

    /**
     * 取得 交易费
     * @return 交易费
     */
    public String getRepTradeFee() {
        return repTradeFee;
    }

    /**
     * 设置 交易费
     * @param repTradeFee 交易费
     */
    public void setRepTradeFee(String repTradeFee) {
        this.repTradeFee = repTradeFee == null ? null : repTradeFee.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}