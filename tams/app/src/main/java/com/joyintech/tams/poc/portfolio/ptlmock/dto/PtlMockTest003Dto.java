package com.joyintech.tams.poc.portfolio.ptlmock.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCode;

/**
 * 模拟试算可选资产
 * 
 * @author wl
 * @version 1.0 2020/04/20
 * @see PtlMockTest003Dto
 * @since 1.0
 */
@PageId("PtlMockTest003")
public class PtlMockTest003Dto extends PtlPortfolio {

    private static final long serialVersionUID = 8942353503575241251L;

    // 组合名称
    private String portfolioNamep;

    // 组合到期日
    private Date mdatep;

    // 资产类型
    private String type8;

    // 资产代码
    private String finprodId;

    // 资产名称
    private String finprodAbbr;

    // 投资目的
    private String invAim;

    // 持仓数量
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amountp;

    // 试算占用数量
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    // 净价
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    // 应计利息
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    // 到期收益率
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    // 行权收益率
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 资产币种
     */
    private String assetCcy;

    public String getType8Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getType8());
    }

    public String getInvAimStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资目的, this.getInvAim());
    }

    public String getProfitTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, this.getProfitType());
    }

    public String getPortfolioNamep() {
        return portfolioNamep;
    }

    public void setPortfolioNamep(String portfolioNamep) {
        this.portfolioNamep = portfolioNamep;
    }

    public Date getMdatep() {
        return mdatep;
    }

    public void setMdatep(Date mdatep) {
        this.mdatep = mdatep;
    }

    public String getType8() {
        return type8;
    }

    public void setType8(String type8) {
        this.type8 = type8;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public BigDecimal getAmountp() {
        return amountp;
    }

    public void setAmountp(BigDecimal amountp) {
        this.amountp = amountp;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    /**
     * 获取 assetCcy
     * 
     * @return assetCcy.
     */
    public String getAssetCcy() {
        return assetCcy;
    }

    /**
     * 设置 assetCcy
     * 
     * @param assetCcy assetCcy
     */
    public void setAssetCcy(String assetCcy) {
        this.assetCcy = assetCcy;
    }

}
