package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstQdiiStructSub extends ChbAstQdiiStructSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所  参照数据字典
     */
    private String tradeMarket;

    /**
     * 所属国家或地区 参照数据字典
     */
    private String countryArea;

    /**
     * 合约名称
     */
    private String contractName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 票面利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal paperRate;

    /**
     * 付息频率(个月/次)
     */
    private Short intpayCycle;

    /**
     * 固定收益部分所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeRatio;

    /**
     * 衍生金融工具所占比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal derInstrRatio;

    /**
     * 衍生金融工具具体投资方式
     */
    private String derInstrInvType;

    /**
     * 衍生金融工具挂钩标的资产
     */
    private String derInstrTarget;

    /**
     * 利息结算方式
     */
    private String acctMode;

    /**
     * 含权情况说明
     */
    private String specialCmt;

    /**
     * 结构性票据最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxYield;

    /**
     * 结构性票据最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minYield;

    /**
     * 挂钩标的资产基准价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicPrice;

    /**
     * 挂钩标的资产登记日价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal regDatePrice;

    /**
     * 交易费
     */
    private String tradeFee;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所  参照数据字典
     * @return 交易流通场所  参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所  参照数据字典
     * @param tradeMarket 交易流通场所  参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 所属国家或地区 参照数据字典
     * @return 所属国家或地区 参照数据字典
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 设置 所属国家或地区 参照数据字典
     * @param countryArea 所属国家或地区 参照数据字典
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea == null ? null : countryArea.trim();
    }

    /**
     * 取得 合约名称
     * @return 合约名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置 合约名称
     * @param contractName 合约名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
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
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public BigDecimal getPaperRate() {
        return paperRate;
    }

    /**
     * 设置 票面利率
     * @param paperRate 票面利率
     */
    public void setPaperRate(BigDecimal paperRate) {
        this.paperRate = paperRate;
    }

    /**
     * 取得 付息频率(个月/次)
     * @return 付息频率(个月/次)
     */
    public Short getIntpayCycle() {
        return intpayCycle;
    }

    /**
     * 设置 付息频率(个月/次)
     * @param intpayCycle 付息频率(个月/次)
     */
    public void setIntpayCycle(Short intpayCycle) {
        this.intpayCycle = intpayCycle;
    }

    /**
     * 取得 固定收益部分所占比例
     * @return 固定收益部分所占比例
     */
    public BigDecimal getIncomeRatio() {
        return incomeRatio;
    }

    /**
     * 设置 固定收益部分所占比例
     * @param incomeRatio 固定收益部分所占比例
     */
    public void setIncomeRatio(BigDecimal incomeRatio) {
        this.incomeRatio = incomeRatio;
    }

    /**
     * 取得 衍生金融工具所占比例
     * @return 衍生金融工具所占比例
     */
    public BigDecimal getDerInstrRatio() {
        return derInstrRatio;
    }

    /**
     * 设置 衍生金融工具所占比例
     * @param derInstrRatio 衍生金融工具所占比例
     */
    public void setDerInstrRatio(BigDecimal derInstrRatio) {
        this.derInstrRatio = derInstrRatio;
    }

    /**
     * 取得 衍生金融工具具体投资方式
     * @return 衍生金融工具具体投资方式
     */
    public String getDerInstrInvType() {
        return derInstrInvType;
    }

    /**
     * 设置 衍生金融工具具体投资方式
     * @param derInstrInvType 衍生金融工具具体投资方式
     */
    public void setDerInstrInvType(String derInstrInvType) {
        this.derInstrInvType = derInstrInvType == null ? null : derInstrInvType.trim();
    }

    /**
     * 取得 衍生金融工具挂钩标的资产
     * @return 衍生金融工具挂钩标的资产
     */
    public String getDerInstrTarget() {
        return derInstrTarget;
    }

    /**
     * 设置 衍生金融工具挂钩标的资产
     * @param derInstrTarget 衍生金融工具挂钩标的资产
     */
    public void setDerInstrTarget(String derInstrTarget) {
        this.derInstrTarget = derInstrTarget == null ? null : derInstrTarget.trim();
    }

    /**
     * 取得 利息结算方式
     * @return 利息结算方式
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 利息结算方式
     * @param acctMode 利息结算方式
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode == null ? null : acctMode.trim();
    }

    /**
     * 取得 含权情况说明
     * @return 含权情况说明
     */
    public String getSpecialCmt() {
        return specialCmt;
    }

    /**
     * 设置 含权情况说明
     * @param specialCmt 含权情况说明
     */
    public void setSpecialCmt(String specialCmt) {
        this.specialCmt = specialCmt == null ? null : specialCmt.trim();
    }

    /**
     * 取得 结构性票据最高收益率
     * @return 结构性票据最高收益率
     */
    public BigDecimal getMaxYield() {
        return maxYield;
    }

    /**
     * 设置 结构性票据最高收益率
     * @param maxYield 结构性票据最高收益率
     */
    public void setMaxYield(BigDecimal maxYield) {
        this.maxYield = maxYield;
    }

    /**
     * 取得 结构性票据最低收益率
     * @return 结构性票据最低收益率
     */
    public BigDecimal getMinYield() {
        return minYield;
    }

    /**
     * 设置 结构性票据最低收益率
     * @param minYield 结构性票据最低收益率
     */
    public void setMinYield(BigDecimal minYield) {
        this.minYield = minYield;
    }

    /**
     * 取得 挂钩标的资产基准价格
     * @return 挂钩标的资产基准价格
     */
    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    /**
     * 设置 挂钩标的资产基准价格
     * @param basicPrice 挂钩标的资产基准价格
     */
    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    /**
     * 取得 挂钩标的资产登记日价格
     * @return 挂钩标的资产登记日价格
     */
    public BigDecimal getRegDatePrice() {
        return regDatePrice;
    }

    /**
     * 设置 挂钩标的资产登记日价格
     * @param regDatePrice 挂钩标的资产登记日价格
     */
    public void setRegDatePrice(BigDecimal regDatePrice) {
        this.regDatePrice = regDatePrice;
    }

    /**
     * 取得 交易费
     * @return 交易费
     */
    public String getTradeFee() {
        return tradeFee;
    }

    /**
     * 设置 交易费
     * @param tradeFee 交易费
     */
    public void setTradeFee(String tradeFee) {
        this.tradeFee = tradeFee == null ? null : tradeFee.trim();
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
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}