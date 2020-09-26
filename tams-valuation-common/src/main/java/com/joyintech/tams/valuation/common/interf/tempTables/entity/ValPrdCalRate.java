package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdCalRate extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 映射开放形式
     */
    private String issueType;

    /**
     * 预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal proprate;

    /**
     * 是否分层
     */
    private String isLayer;

    /**
     * 分层类型
     */
    private String layerType;

    /**
     * 利率规则
     */
    private String rateRule;

    /**
     * 挂钩利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal linkRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 重置频率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal resetCycle;

    /**
     * 首次重置日
     */
    private Date firstReset;

    /**
     * 分层结构设置
     */
    private String layerSet;

    /**
     * 产品款型
     */
    private String prdType;

    /**
     * 持有上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdMax;

    /**
     * 持有下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdMin;

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
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 映射开放形式
     * @return 映射开放形式
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * 设置 映射开放形式
     * @param issueType 映射开放形式
     */
    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? null : issueType.trim();
    }

    /**
     * 取得 预期收益率
     * @return 预期收益率
     */
    public BigDecimal getProprate() {
        return proprate;
    }

    /**
     * 设置 预期收益率
     * @param proprate 预期收益率
     */
    public void setProprate(BigDecimal proprate) {
        this.proprate = proprate;
    }

    /**
     * 取得 是否分层
     * @return 是否分层
     */
    public String getIsLayer() {
        return isLayer;
    }

    /**
     * 设置 是否分层
     * @param isLayer 是否分层
     */
    public void setIsLayer(String isLayer) {
        this.isLayer = isLayer == null ? null : isLayer.trim();
    }

    /**
     * 取得 分层类型
     * @return 分层类型
     */
    public String getLayerType() {
        return layerType;
    }

    /**
     * 设置 分层类型
     * @param layerType 分层类型
     */
    public void setLayerType(String layerType) {
        this.layerType = layerType == null ? null : layerType.trim();
    }

    /**
     * 取得 利率规则
     * @return 利率规则
     */
    public String getRateRule() {
        return rateRule;
    }

    /**
     * 设置 利率规则
     * @param rateRule 利率规则
     */
    public void setRateRule(String rateRule) {
        this.rateRule = rateRule == null ? null : rateRule.trim();
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
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 重置频率
     * @return 重置频率
     */
    public BigDecimal getResetCycle() {
        return resetCycle;
    }

    /**
     * 设置 重置频率
     * @param resetCycle 重置频率
     */
    public void setResetCycle(BigDecimal resetCycle) {
        this.resetCycle = resetCycle;
    }

    /**
     * 取得 首次重置日
     * @return 首次重置日
     */
    public Date getFirstReset() {
        return firstReset;
    }

    /**
     * 设置 首次重置日
     * @param firstReset 首次重置日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstReset(Date firstReset) {
        this.firstReset = firstReset;
    }

    /**
     * 取得 分层结构设置
     * @return 分层结构设置
     */
    public String getLayerSet() {
        return layerSet;
    }

    /**
     * 设置 分层结构设置
     * @param layerSet 分层结构设置
     */
    public void setLayerSet(String layerSet) {
        this.layerSet = layerSet == null ? null : layerSet.trim();
    }

    /**
     * 取得 产品款型
     * @return 产品款型
     */
    public String getPrdType() {
        return prdType;
    }

    /**
     * 设置 产品款型
     * @param prdType 产品款型
     */
    public void setPrdType(String prdType) {
        this.prdType = prdType == null ? null : prdType.trim();
    }

    /**
     * 取得 持有上限
     * @return 持有上限
     */
    public BigDecimal getHoldMax() {
        return holdMax;
    }

    /**
     * 设置 持有上限
     * @param holdMax 持有上限
     */
    public void setHoldMax(BigDecimal holdMax) {
        this.holdMax = holdMax;
    }

    /**
     * 取得 持有下限
     * @return 持有下限
     */
    public BigDecimal getHoldMin() {
        return holdMin;
    }

    /**
     * 设置 持有下限
     * @param holdMin 持有下限
     */
    public void setHoldMin(BigDecimal holdMin) {
        this.holdMin = holdMin;
    }
}