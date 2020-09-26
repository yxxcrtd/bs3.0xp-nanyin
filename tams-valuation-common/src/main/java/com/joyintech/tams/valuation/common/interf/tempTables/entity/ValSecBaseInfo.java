package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValSecBaseInfo extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 托管资产代码:邮储银行用
     */
    private String trustAssetCode;

    /**
     * 债券简称
     */
    private String secAbbr;

    /**
     * 债券名称
     */
    private String secName;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 发行价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal publishPrice;

    /**
     * 发行总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal publishTotal;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 债券类型
     */
    private String bondType;

    /**
     * 境内外
     */
    private String inOut;

    /**
     * 交易场所
     */
    private String tradePlace;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 发行人
     */
    private String publisher;

    /**
     * 是否通道
     */
    private String isChl;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 息票品种
     */
    private String couponSpecies;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利息分配方式
     */
    private String intpayRule;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 票面利率
     */
    private String nomRate;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMax;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateMin;

    /**
     * 浮动利率编号
     */
    private String intRateId;

    /**
     * 挂钩利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal linkRate;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal initRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitSub;

    /**
     * 是否含有债权
     */
    private String isInright;

    /**
     * 本金收益特征
     */
    private String capiProfFeature;

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码
     * @param secId 债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 托管资产代码:邮储银行用
     * @return 托管资产代码:邮储银行用
     */
    public String getTrustAssetCode() {
        return trustAssetCode;
    }

    /**
     * 设置 托管资产代码:邮储银行用
     * @param trustAssetCode 托管资产代码:邮储银行用
     */
    public void setTrustAssetCode(String trustAssetCode) {
        this.trustAssetCode = trustAssetCode == null ? null : trustAssetCode.trim();
    }

    /**
     * 取得 债券简称
     * @return 债券简称
     */
    public String getSecAbbr() {
        return secAbbr;
    }

    /**
     * 设置 债券简称
     * @param secAbbr 债券简称
     */
    public void setSecAbbr(String secAbbr) {
        this.secAbbr = secAbbr == null ? null : secAbbr.trim();
    }

    /**
     * 取得 债券名称
     * @return 债券名称
     */
    public String getSecName() {
        return secName;
    }

    /**
     * 设置 债券名称
     * @param secName 债券名称
     */
    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 发行价格
     * @return 发行价格
     */
    public BigDecimal getPublishPrice() {
        return publishPrice;
    }

    /**
     * 设置 发行价格
     * @param publishPrice 发行价格
     */
    public void setPublishPrice(BigDecimal publishPrice) {
        this.publishPrice = publishPrice;
    }

    /**
     * 取得 发行总额
     * @return 发行总额
     */
    public BigDecimal getPublishTotal() {
        return publishTotal;
    }

    /**
     * 设置 发行总额
     * @param publishTotal 发行总额
     */
    public void setPublishTotal(BigDecimal publishTotal) {
        this.publishTotal = publishTotal;
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
     * 取得 债券类型
     * @return 债券类型
     */
    public String getBondType() {
        return bondType;
    }

    /**
     * 设置 债券类型
     * @param bondType 债券类型
     */
    public void setBondType(String bondType) {
        this.bondType = bondType == null ? null : bondType.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getInOut() {
        return inOut;
    }

    /**
     * 设置 境内外
     * @param inOut 境内外
     */
    public void setInOut(String inOut) {
        this.inOut = inOut == null ? null : inOut.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradePlace() {
        return tradePlace;
    }

    /**
     * 设置 交易场所
     * @param tradePlace 交易场所
     */
    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace == null ? null : tradePlace.trim();
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
     * 取得 发行人
     * @return 发行人
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置 发行人
     * @param publisher 发行人
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    /**
     * 取得 是否通道
     * @return 是否通道
     */
    public String getIsChl() {
        return isChl;
    }

    /**
     * 设置 是否通道
     * @param isChl 是否通道
     */
    public void setIsChl(String isChl) {
        this.isChl = isChl == null ? null : isChl.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 息票品种
     * @return 息票品种
     */
    public String getCouponSpecies() {
        return couponSpecies;
    }

    /**
     * 设置 息票品种
     * @param couponSpecies 息票品种
     */
    public void setCouponSpecies(String couponSpecies) {
        this.couponSpecies = couponSpecies == null ? null : couponSpecies.trim();
    }

    /**
     * 取得 计息基础
     * @return 计息基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 利息分配方式
     * @return 利息分配方式
     */
    public String getIntpayRule() {
        return intpayRule;
    }

    /**
     * 设置 利息分配方式
     * @param intpayRule 利息分配方式
     */
    public void setIntpayRule(String intpayRule) {
        this.intpayRule = intpayRule == null ? null : intpayRule.trim();
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置 利率类型
     * @param intType 利率类型
     */
    public void setIntType(String intType) {
        this.intType = intType == null ? null : intType.trim();
    }

    /**
     * 取得 票面利率
     * @return 票面利率
     */
    public String getNomRate() {
        return nomRate;
    }

    /**
     * 设置 票面利率
     * @param nomRate 票面利率
     */
    public void setNomRate(String nomRate) {
        this.nomRate = nomRate == null ? null : nomRate.trim();
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getRateMax() {
        return rateMax;
    }

    /**
     * 设置 利率上限
     * @param rateMax 利率上限
     */
    public void setRateMax(BigDecimal rateMax) {
        this.rateMax = rateMax;
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getRateMin() {
        return rateMin;
    }

    /**
     * 设置 利率下限
     * @param rateMin 利率下限
     */
    public void setRateMin(BigDecimal rateMin) {
        this.rateMin = rateMin;
    }

    /**
     * 取得 浮动利率编号
     * @return 浮动利率编号
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 浮动利率编号
     * @param intRateId 浮动利率编号
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }

    /**
     * 取得 挂钩利率
     * @return 挂钩利率
     */
    public BigDecimal getLinkRate() {
        return linkRate;
    }

    /**
     * 设置 挂钩利率
     * @param linkRate 挂钩利率
     */
    public void setLinkRate(BigDecimal linkRate) {
        this.linkRate = linkRate;
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getInitRate() {
        return initRate;
    }

    /**
     * 设置 初始利率
     * @param initRate 初始利率
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 系数
     * @param ratio 系数
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getProfitSub() {
        return profitSub;
    }

    /**
     * 设置 利差
     * @param profitSub 利差
     */
    public void setProfitSub(BigDecimal profitSub) {
        this.profitSub = profitSub;
    }

    /**
     * 取得 是否含有债权
     * @return 是否含有债权
     */
    public String getIsInright() {
        return isInright;
    }

    /**
     * 设置 是否含有债权
     * @param isInright 是否含有债权
     */
    public void setIsInright(String isInright) {
        this.isInright = isInright == null ? null : isInright.trim();
    }

    /**
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiProfFeature() {
        return capiProfFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiProfFeature 本金收益特征
     */
    public void setCapiProfFeature(String capiProfFeature) {
        this.capiProfFeature = capiProfFeature == null ? null : capiProfFeature.trim();
    }
}