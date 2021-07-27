package com.joyintech.tams.invest.entrust.etrastconfigtransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.asset.annotation.Number;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@PageId("EtrAssetConfigTransfer002")
public class EtrAssetConfigDto extends EtrEntrust {
    private static final long serialVersionUID = 9222570679406156110L;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 买入成本
     */
    private BigDecimal buyCost;

    /**
     * 估价净价
     */
    private BigDecimal netCprice;

    /**
     * 行权日期
     */
    private Date exerciseDate;

    /**
     * 券商
     */
    private String acctName;

    /**
     * 债券一级分类
     */
    private String type6;


    /**
     * 收益率(债券票面利率)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 持仓券面面额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;


    /**
     * 价格偏离度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal radio;

    /**
     * 转入组合信息
     */
    private List<PtlPortfolioValuation> portfolioValuationList;

    /**
     * 试算列表
     */
    private List<EtrAssetConfigDto> selectedItem;

    /**
     * 序号
     */
    private String indexId;

    /**
     * 证券账户
     */
    private String secAcctId;


    @Number(3)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    @Number(4)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    @Number(5)
    public String getPs() {
        return super.getPs();
    }

    @Number(14)
    public String getInvAim() {
        return super.getInvAim();
    }

    @Number(15)
    public String getTradeMarket() {
        return super.getTradeMarket();
    }

    public BigDecimal getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(BigDecimal buyCost) {
        this.buyCost = buyCost;
    }

    public BigDecimal getNetCprice() {
        return netCprice;
    }

    public void setNetCprice(BigDecimal netCprice) {
        this.netCprice = netCprice;
    }

    @Number(9)
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeviation() {
        return deviation;
    }

    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    @Number(value=7,unit = DictCodeListExt1.单位_万元)
    public BigDecimal getEntrustNum() {
        return super.getEntrustNum();
    }

    public BigDecimal getRadio() {
        return radio;
    }

    public void setRadio(BigDecimal radio) {
        this.radio = radio;
    }

    @Number(16)
    public String getTradePlat() {
        return super.getTradePlat();
    }

    @Number(18)
    public String getChlId() {
        return super.getChlId();
    }

    @Number(19)
    public String getCounterId() {
        return super.getCounterId();
    }

    @Number(20)
    public String getRemark() {
        return super.getRemark();
    }

    public List<PtlPortfolioValuation> getPortfolioValuationList() {
        return portfolioValuationList;
    }

    public void setPortfolioValuationList(List<PtlPortfolioValuation> portfolioValuationList) {
        this.portfolioValuationList = portfolioValuationList;
    }

    public List<EtrAssetConfigDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtrAssetConfigDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Number(0)
    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    @Number(17)
    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getTradeMarketStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所,this.getTradeMarket());
    }

    public String getType6Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等,this.getType6());
    }
}

