package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class TrdMinpriceConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 交易场所，上交所、深交所等
     */
    private String tradeMarket;

    /**
     * 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    private String tradePlat;

    /**
     * 资产分类一
     */
    private String assetClassify1;

    /**
     * 资产分类二
     */
    private String assetClassify2;

    /**
     * 资产分类三
     */
    private String assetClassify3;

    /**
     * 是否分档
     */
    private String isGrade;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 批次号
     */
    private String batchSeq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 交易场所，上交所、深交所等
     * @return 交易场所，上交所、深交所等
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所，上交所、深交所等
     * @param tradeMarket 交易场所，上交所、深交所等
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     * @return 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    public String getTradePlat() {
        return tradePlat;
    }

    /**
     * 设置 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     * @param tradePlat 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat == null ? null : tradePlat.trim();
    }

    /**
     * 取得 资产分类一
     * @return 资产分类一
     */
    public String getAssetClassify1() {
        return assetClassify1;
    }

    /**
     * 设置 资产分类一
     * @param assetClassify1 资产分类一
     */
    public void setAssetClassify1(String assetClassify1) {
        this.assetClassify1 = assetClassify1 == null ? null : assetClassify1.trim();
    }

    /**
     * 取得 资产分类二
     * @return 资产分类二
     */
    public String getAssetClassify2() {
        return assetClassify2;
    }

    /**
     * 设置 资产分类二
     * @param assetClassify2 资产分类二
     */
    public void setAssetClassify2(String assetClassify2) {
        this.assetClassify2 = assetClassify2 == null ? null : assetClassify2.trim();
    }

    /**
     * 取得 资产分类三
     * @return 资产分类三
     */
    public String getAssetClassify3() {
        return assetClassify3;
    }

    /**
     * 设置 资产分类三
     * @param assetClassify3 资产分类三
     */
    public void setAssetClassify3(String assetClassify3) {
        this.assetClassify3 = assetClassify3 == null ? null : assetClassify3.trim();
    }

    /**
     * 取得 是否分档
     * @return 是否分档
     */
    public String getIsGrade() {
        return isGrade;
    }

    /**
     * 设置 是否分档
     * @param isGrade 是否分档
     */
    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade == null ? null : isGrade.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getBatchSeq() {
        return batchSeq;
    }

    /**
     * 设置 批次号
     * @param batchSeq 批次号
     */
    public void setBatchSeq(String batchSeq) {
        this.batchSeq = batchSeq == null ? null : batchSeq.trim();
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