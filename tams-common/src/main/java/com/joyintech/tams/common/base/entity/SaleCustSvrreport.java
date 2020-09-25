package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class SaleCustSvrreport extends BaseEntity {
    /**
     * 下发日期
     */
    private Date senddate;

    /**
     * 产品代码
     */
    private String finprodId;


    /**
     * 法人行
     */
    private String issuemember;

    /**
     * 境内境外
     */
    private String inoraboard;

    /**
     * 客户组别代码
     */
    private String cusCode;

    /**
     * 行政区域代码
     */
    private String areaCode;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 认购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 认购金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtRmb;

    /**
     * 认购确认份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtConfirm;

    /**
     * 申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgAmt;

    /**
     * 申购金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgAmtRmb;

    /**
     * 申购确认份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgConfirmAmt;

    /**
     * 赎回兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shAmt;

    /**
     * 赎回兑付金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shAmtRmb;

    /**
     * 赎回兑付本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shPrincipal;

    /**
     * 赎回兑付本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shPrincipalRmb;

    /**
     * 赎回兑付份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shQty;

    /**
     * 到期兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmt;

    /**
     * 到期兑付金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtRmb;

    /**
     * 到期兑付本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtPrincipal;

    /**
     * 到期兑付本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtPrincipalRmb;

    /**
     * 到期兑付份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtQty;

    /**
     * 红利再投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmt;

    /**
     * 红利再投资金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmtRmb;

    /**
     * 红利再投资份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmtQty;

    /**
     * 现金分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusAmt;

    /**
     * 现金分红金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusAmtRmb;

    /**
     * 份额强增金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAddAmt;

    /**
     * 份额强增金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAddAmtRmb;

    /**
     * 强增份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAdd;

    /**
     * 强减本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinusPrincipal;

    /**
     * 强减本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinusPrincipalRmb;

    /**
     * 强减份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinus;

    /**
     * 保有量金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveAmt;

    /**
     * 保有量客户数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveCusNum;

    /**
     * 保有量金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveAmtRmb;

    /**
     * 保有量份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveQty;

    /**
     * 汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchangeRate;

    /**
     * 客户类型
     */
    private String cusType;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 取得 下发日期
     * @return 下发日期
     */
    public Date getSenddate() {
        return senddate;
    }

    /**
     * 设置 下发日期
     * @param senddate 下发日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 产品代码
     * @param finprodId 产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 法人行
     * @return 法人行
     */
    public String getIssuemember() {
        return issuemember;
    }

    /**
     * 设置 法人行
     * @param issuemember 法人行
     */
    public void setIssuemember(String issuemember) {
        this.issuemember = issuemember == null ? null : issuemember.trim();
    }

    /**
     * 取得 境内境外
     * @return 境内境外
     */
    public String getInoraboard() {
        return inoraboard;
    }

    /**
     * 设置 境内境外
     * @param inoraboard 境内境外
     */
    public void setInoraboard(String inoraboard) {
        this.inoraboard = inoraboard == null ? null : inoraboard.trim();
    }

    /**
     * 取得 客户组别代码
     * @return 客户组别代码
     */
    public String getCusCode() {
        return cusCode;
    }

    /**
     * 设置 客户组别代码
     * @param cusCode 客户组别代码
     */
    public void setCusCode(String cusCode) {
        this.cusCode = cusCode == null ? null : cusCode.trim();
    }

    /**
     * 取得 行政区域代码
     * @return 行政区域代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置 行政区域代码
     * @param areaCode 行政区域代码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
     * 取得 认购金额
     * @return 认购金额
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 认购金额
     * @param prinamt 认购金额
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    /**
     * 取得 认购金额转人民币
     * @return 认购金额转人民币
     */
    public BigDecimal getPrinamtRmb() {
        return prinamtRmb;
    }

    /**
     * 设置 认购金额转人民币
     * @param prinamtRmb 认购金额转人民币
     */
    public void setPrinamtRmb(BigDecimal prinamtRmb) {
        this.prinamtRmb = prinamtRmb;
    }

    /**
     * 取得 认购确认份额
     * @return 认购确认份额
     */
    public BigDecimal getPrinamtConfirm() {
        return prinamtConfirm;
    }

    /**
     * 设置 认购确认份额
     * @param prinamtConfirm 认购确认份额
     */
    public void setPrinamtConfirm(BigDecimal prinamtConfirm) {
        this.prinamtConfirm = prinamtConfirm;
    }

    /**
     * 取得 申购金额
     * @return 申购金额
     */
    public BigDecimal getSgAmt() {
        return sgAmt;
    }

    /**
     * 设置 申购金额
     * @param sgAmt 申购金额
     */
    public void setSgAmt(BigDecimal sgAmt) {
        this.sgAmt = sgAmt;
    }

    /**
     * 取得 申购金额转人民币
     * @return 申购金额转人民币
     */
    public BigDecimal getSgAmtRmb() {
        return sgAmtRmb;
    }

    /**
     * 设置 申购金额转人民币
     * @param sgAmtRmb 申购金额转人民币
     */
    public void setSgAmtRmb(BigDecimal sgAmtRmb) {
        this.sgAmtRmb = sgAmtRmb;
    }

    /**
     * 取得 申购确认份额
     * @return 申购确认份额
     */
    public BigDecimal getSgConfirmAmt() {
        return sgConfirmAmt;
    }

    /**
     * 设置 申购确认份额
     * @param sgConfirmAmt 申购确认份额
     */
    public void setSgConfirmAmt(BigDecimal sgConfirmAmt) {
        this.sgConfirmAmt = sgConfirmAmt;
    }

    /**
     * 取得 赎回兑付金额
     * @return 赎回兑付金额
     */
    public BigDecimal getShAmt() {
        return shAmt;
    }

    /**
     * 设置 赎回兑付金额
     * @param shAmt 赎回兑付金额
     */
    public void setShAmt(BigDecimal shAmt) {
        this.shAmt = shAmt;
    }

    /**
     * 取得 赎回兑付金额转人民币
     * @return 赎回兑付金额转人民币
     */
    public BigDecimal getShAmtRmb() {
        return shAmtRmb;
    }

    /**
     * 设置 赎回兑付金额转人民币
     * @param shAmtRmb 赎回兑付金额转人民币
     */
    public void setShAmtRmb(BigDecimal shAmtRmb) {
        this.shAmtRmb = shAmtRmb;
    }

    /**
     * 取得 赎回兑付本金
     * @return 赎回兑付本金
     */
    public BigDecimal getShPrincipal() {
        return shPrincipal;
    }

    /**
     * 设置 赎回兑付本金
     * @param shPrincipal 赎回兑付本金
     */
    public void setShPrincipal(BigDecimal shPrincipal) {
        this.shPrincipal = shPrincipal;
    }

    /**
     * 取得 赎回兑付本金转人民币
     * @return 赎回兑付本金转人民币
     */
    public BigDecimal getShPrincipalRmb() {
        return shPrincipalRmb;
    }

    /**
     * 设置 赎回兑付本金转人民币
     * @param shPrincipalRmb 赎回兑付本金转人民币
     */
    public void setShPrincipalRmb(BigDecimal shPrincipalRmb) {
        this.shPrincipalRmb = shPrincipalRmb;
    }

    /**
     * 取得 赎回兑付份额
     * @return 赎回兑付份额
     */
    public BigDecimal getShQty() {
        return shQty;
    }

    /**
     * 设置 赎回兑付份额
     * @param shQty 赎回兑付份额
     */
    public void setShQty(BigDecimal shQty) {
        this.shQty = shQty;
    }

    /**
     * 取得 到期兑付金额
     * @return 到期兑付金额
     */
    public BigDecimal getExpireAmt() {
        return expireAmt;
    }

    /**
     * 设置 到期兑付金额
     * @param expireAmt 到期兑付金额
     */
    public void setExpireAmt(BigDecimal expireAmt) {
        this.expireAmt = expireAmt;
    }

    /**
     * 取得 到期兑付金额转人民币
     * @return 到期兑付金额转人民币
     */
    public BigDecimal getExpireAmtRmb() {
        return expireAmtRmb;
    }

    /**
     * 设置 到期兑付金额转人民币
     * @param expireAmtRmb 到期兑付金额转人民币
     */
    public void setExpireAmtRmb(BigDecimal expireAmtRmb) {
        this.expireAmtRmb = expireAmtRmb;
    }

    /**
     * 取得 到期兑付本金
     * @return 到期兑付本金
     */
    public BigDecimal getExpireAmtPrincipal() {
        return expireAmtPrincipal;
    }

    /**
     * 设置 到期兑付本金
     * @param expireAmtPrincipal 到期兑付本金
     */
    public void setExpireAmtPrincipal(BigDecimal expireAmtPrincipal) {
        this.expireAmtPrincipal = expireAmtPrincipal;
    }

    /**
     * 取得 到期兑付本金转人民币
     * @return 到期兑付本金转人民币
     */
    public BigDecimal getExpireAmtPrincipalRmb() {
        return expireAmtPrincipalRmb;
    }

    /**
     * 设置 到期兑付本金转人民币
     * @param expireAmtPrincipalRmb 到期兑付本金转人民币
     */
    public void setExpireAmtPrincipalRmb(BigDecimal expireAmtPrincipalRmb) {
        this.expireAmtPrincipalRmb = expireAmtPrincipalRmb;
    }

    /**
     * 取得 到期兑付份额
     * @return 到期兑付份额
     */
    public BigDecimal getExpireAmtQty() {
        return expireAmtQty;
    }

    /**
     * 设置 到期兑付份额
     * @param expireAmtQty 到期兑付份额
     */
    public void setExpireAmtQty(BigDecimal expireAmtQty) {
        this.expireAmtQty = expireAmtQty;
    }

    /**
     * 取得 红利再投资金额
     * @return 红利再投资金额
     */
    public BigDecimal getRebonusAmt() {
        return rebonusAmt;
    }

    /**
     * 设置 红利再投资金额
     * @param rebonusAmt 红利再投资金额
     */
    public void setRebonusAmt(BigDecimal rebonusAmt) {
        this.rebonusAmt = rebonusAmt;
    }

    /**
     * 取得 红利再投资金额转人民币
     * @return 红利再投资金额转人民币
     */
    public BigDecimal getRebonusAmtRmb() {
        return rebonusAmtRmb;
    }

    /**
     * 设置 红利再投资金额转人民币
     * @param rebonusAmtRmb 红利再投资金额转人民币
     */
    public void setRebonusAmtRmb(BigDecimal rebonusAmtRmb) {
        this.rebonusAmtRmb = rebonusAmtRmb;
    }

    /**
     * 取得 红利再投资份额
     * @return 红利再投资份额
     */
    public BigDecimal getRebonusAmtQty() {
        return rebonusAmtQty;
    }

    /**
     * 设置 红利再投资份额
     * @param rebonusAmtQty 红利再投资份额
     */
    public void setRebonusAmtQty(BigDecimal rebonusAmtQty) {
        this.rebonusAmtQty = rebonusAmtQty;
    }

    /**
     * 取得 现金分红金额
     * @return 现金分红金额
     */
    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    /**
     * 设置 现金分红金额
     * @param bonusAmt 现金分红金额
     */
    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    /**
     * 取得 现金分红金额转人民币
     * @return 现金分红金额转人民币
     */
    public BigDecimal getBonusAmtRmb() {
        return bonusAmtRmb;
    }

    /**
     * 设置 现金分红金额转人民币
     * @param bonusAmtRmb 现金分红金额转人民币
     */
    public void setBonusAmtRmb(BigDecimal bonusAmtRmb) {
        this.bonusAmtRmb = bonusAmtRmb;
    }

    /**
     * 取得 份额强增金额
     * @return 份额强增金额
     */
    public BigDecimal getQtyAddAmt() {
        return qtyAddAmt;
    }

    /**
     * 设置 份额强增金额
     * @param qtyAddAmt 份额强增金额
     */
    public void setQtyAddAmt(BigDecimal qtyAddAmt) {
        this.qtyAddAmt = qtyAddAmt;
    }

    /**
     * 取得 份额强增金额转人民币
     * @return 份额强增金额转人民币
     */
    public BigDecimal getQtyAddAmtRmb() {
        return qtyAddAmtRmb;
    }

    /**
     * 设置 份额强增金额转人民币
     * @param qtyAddAmtRmb 份额强增金额转人民币
     */
    public void setQtyAddAmtRmb(BigDecimal qtyAddAmtRmb) {
        this.qtyAddAmtRmb = qtyAddAmtRmb;
    }

    /**
     * 取得 强增份额
     * @return 强增份额
     */
    public BigDecimal getQtyAdd() {
        return qtyAdd;
    }

    /**
     * 设置 强增份额
     * @param qtyAdd 强增份额
     */
    public void setQtyAdd(BigDecimal qtyAdd) {
        this.qtyAdd = qtyAdd;
    }

    /**
     * 取得 强减本金
     * @return 强减本金
     */
    public BigDecimal getQtyMinusPrincipal() {
        return qtyMinusPrincipal;
    }

    /**
     * 设置 强减本金
     * @param qtyMinusPrincipal 强减本金
     */
    public void setQtyMinusPrincipal(BigDecimal qtyMinusPrincipal) {
        this.qtyMinusPrincipal = qtyMinusPrincipal;
    }

    /**
     * 取得 强减本金转人民币
     * @return 强减本金转人民币
     */
    public BigDecimal getQtyMinusPrincipalRmb() {
        return qtyMinusPrincipalRmb;
    }

    /**
     * 设置 强减本金转人民币
     * @param qtyMinusPrincipalRmb 强减本金转人民币
     */
    public void setQtyMinusPrincipalRmb(BigDecimal qtyMinusPrincipalRmb) {
        this.qtyMinusPrincipalRmb = qtyMinusPrincipalRmb;
    }

    /**
     * 取得 强减份额
     * @return 强减份额
     */
    public BigDecimal getQtyMinus() {
        return qtyMinus;
    }

    /**
     * 设置 强减份额
     * @param qtyMinus 强减份额
     */
    public void setQtyMinus(BigDecimal qtyMinus) {
        this.qtyMinus = qtyMinus;
    }

    /**
     * 取得 保有量金额
     * @return 保有量金额
     */
    public BigDecimal getSaveAmt() {
        return saveAmt;
    }

    /**
     * 设置 保有量金额
     * @param saveAmt 保有量金额
     */
    public void setSaveAmt(BigDecimal saveAmt) {
        this.saveAmt = saveAmt;
    }

    /**
     * 取得 保有量客户数
     * @return 保有量客户数
     */
    public BigDecimal getSaveCusNum() {
        return saveCusNum;
    }

    /**
     * 设置 保有量客户数
     * @param saveCusNum 保有量客户数
     */
    public void setSaveCusNum(BigDecimal saveCusNum) {
        this.saveCusNum = saveCusNum;
    }

    /**
     * 取得 保有量金额转人民币
     * @return 保有量金额转人民币
     */
    public BigDecimal getSaveAmtRmb() {
        return saveAmtRmb;
    }

    /**
     * 设置 保有量金额转人民币
     * @param saveAmtRmb 保有量金额转人民币
     */
    public void setSaveAmtRmb(BigDecimal saveAmtRmb) {
        this.saveAmtRmb = saveAmtRmb;
    }

    /**
     * 取得 保有量份额
     * @return 保有量份额
     */
    public BigDecimal getSaveQty() {
        return saveQty;
    }

    /**
     * 设置 保有量份额
     * @param saveQty 保有量份额
     */
    public void setSaveQty(BigDecimal saveQty) {
        this.saveQty = saveQty;
    }

    /**
     * 取得 汇率
     * @return 汇率
     */
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    /**
     * 设置 汇率
     * @param exchangeRate 汇率
     */
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * 取得 客户类型
     * @return 客户类型
     */
    public String getCusType() {
        return cusType;
    }

    /**
     * 设置 客户类型
     * @param cusType 客户类型
     */
    public void setCusType(String cusType) {
        this.cusType = cusType == null ? null : cusType.trim();
    }

    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置 更新时间
     * @param updatetime 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    
    
}