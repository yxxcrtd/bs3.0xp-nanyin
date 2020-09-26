package com.joyintech.tams.svreport.interf.dis.tradeinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class DisRepTreadInfo extends BaseEntity {
    /**
     * id
     */
    private String id;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 交易对手
     */
    private String tradeCounty;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 买入方
     */
    private String buyPart;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 资产一级分类
     */
    private String assetTypeOne;

    /**
     * 资产二级分类
     */
    private String assetTypeTwo;

    /**
     * 资产三级分类
     */
    private String assetTypeThree;

    /**
     * 是否关联交易
     */
    private String isRelationTrade;

    /**
     * 是否外部交易
     */
    private String isOutTrade;

    /**
     * 卖出方
     */
    private String salePart;

    /**
     * 剩余融资期限
     */
    private Long surplusFinaTrem;

    /**
     * 到期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireRate;

    /**
     * 投资比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investRation;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 本金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 取得 id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 id
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 交易对手
     * @return 交易对手
     */
    public String getTradeCounty() {
        return tradeCounty;
    }

    /**
     * 设置 交易对手
     * @param tradeCounty 交易对手
     */
    public void setTradeCounty(String tradeCounty) {
        this.tradeCounty = tradeCounty == null ? null : tradeCounty.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * @param tradeDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额
     * @param tradeAmt 交易金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 交易类型
     * @return 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 交易类型
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 买入方
     * @return 买入方
     */
    public String getBuyPart() {
        return buyPart;
    }

    /**
     * 设置 买入方
     * @param buyPart 买入方
     */
    public void setBuyPart(String buyPart) {
        this.buyPart = buyPart == null ? null : buyPart.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 资产一级分类
     * @return 资产一级分类
     */
    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    /**
     * 设置 资产一级分类
     * @param assetTypeOne 资产一级分类
     */
    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne == null ? null : assetTypeOne.trim();
    }

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getAssetTypeTwo() {
        return assetTypeTwo;
    }

    /**
     * 设置 资产二级分类
     * @param assetTypeTwo 资产二级分类
     */
    public void setAssetTypeTwo(String assetTypeTwo) {
        this.assetTypeTwo = assetTypeTwo == null ? null : assetTypeTwo.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getAssetTypeThree() {
        return assetTypeThree;
    }

    /**
     * 设置 资产三级分类
     * @param assetTypeThree 资产三级分类
     */
    public void setAssetTypeThree(String assetTypeThree) {
        this.assetTypeThree = assetTypeThree == null ? null : assetTypeThree.trim();
    }

    /**
     * 取得 是否关联交易
     * @return 是否关联交易
     */
    public String getIsRelationTrade() {
        return isRelationTrade;
    }

    /**
     * 设置 是否关联交易
     * @param isRelationTrade 是否关联交易
     */
    public void setIsRelationTrade(String isRelationTrade) {
        this.isRelationTrade = isRelationTrade == null ? null : isRelationTrade.trim();
    }

    /**
     * 取得 是否外部交易
     * @return 是否外部交易
     */
    public String getIsOutTrade() {
        return isOutTrade;
    }

    /**
     * 设置 是否外部交易
     * @param isOutTrade 是否外部交易
     */
    public void setIsOutTrade(String isOutTrade) {
        this.isOutTrade = isOutTrade == null ? null : isOutTrade.trim();
    }

    /**
     * 取得 卖出方
     * @return 卖出方
     */
    public String getSalePart() {
        return salePart;
    }

    /**
     * 设置 卖出方
     * @param salePart 卖出方
     */
    public void setSalePart(String salePart) {
        this.salePart = salePart == null ? null : salePart.trim();
    }

    /**
     * 取得 剩余融资期限
     * @return 剩余融资期限
     */
    public Long getSurplusFinaTrem() {
        return surplusFinaTrem;
    }

    /**
     * 设置 剩余融资期限
     * @param surplusFinaTrem 剩余融资期限
     */
    public void setSurplusFinaTrem(Long surplusFinaTrem) {
        this.surplusFinaTrem = surplusFinaTrem;
    }

    /**
     * 取得 到期收益率
     * @return 到期收益率
     */
    public BigDecimal getExpireRate() {
        return expireRate;
    }

    /**
     * 设置 到期收益率
     * @param expireRate 到期收益率
     */
    public void setExpireRate(BigDecimal expireRate) {
        this.expireRate = expireRate;
    }

    /**
     * 取得 投资比例
     * @return 投资比例
     */
    public BigDecimal getInvestRation() {
        return investRation;
    }

    /**
     * 设置 投资比例
     * @param investRation 投资比例
     */
    public void setInvestRation(BigDecimal investRation) {
        this.investRation = investRation;
    }

    /**
     * 取得 合同号
     * @return 合同号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置 合同号
     * @param contractNo 合同号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

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
     * 取得 本金金额
     * @return 本金金额
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 本金金额
     * @param prinAmt 本金金额
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }
}