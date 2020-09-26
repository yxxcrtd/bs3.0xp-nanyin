package com.joyintech.tams.svreport.chb.datacheck.beforesub.dto;

import java.math.BigDecimal;

/**
 * 描述:产品终止登记报送前检查实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-17
 */
public class ChbProdEndRegCheckDto {

    private String prodid;
    
    /**
     * 卖出投资的交易数据
     */
    private int sellinvesttradcounts;
    
    /**
     * 出清率差值
     */
    private BigDecimal diffclearrate;
    
    /**
     * 持仓资产数量
     */
    private BigDecimal assetqty;
    
    /**
     * 产品剩余现金
     */
    private BigDecimal prodcashamt;
    
    /**
     * 银行实际收入偏差：终止登记时登记的银行收益与存续期登记的银行收益之和，存在的偏差值
     */
    private BigDecimal diffbankprofit;
    
    /**
     * 实际年化收益率与产品登记的年化收益率，存在的差值
     */
    private BigDecimal diffyield;
    
    /**
     * 产品收益与投资收益，存在的差值
     */
    private BigDecimal diffprofit;
    
    /**
     * 客户收益值
     */
    private BigDecimal custprofit;

    
    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
    
    public int getSellinvesttradcounts() {
        return sellinvesttradcounts;
    }
    
    public void setSellinvesttradcounts(int sellinvesttradcounts) {
        this.sellinvesttradcounts = sellinvesttradcounts;
    }

    public BigDecimal getDiffclearrate() {
        return diffclearrate;
    }
    
    public void setDiffclearrate(BigDecimal diffclearrate) {
        this.diffclearrate = diffclearrate;
    }
    
    public BigDecimal getAssetqty() {
        return assetqty;
    }

    public void setAssetqty(BigDecimal assetqty) {
        this.assetqty = assetqty;
    }
    
    public BigDecimal getProdcashamt() {
        return prodcashamt;
    }
    
    public void setProdcashamt(BigDecimal prodcashamt) {
        this.prodcashamt = prodcashamt;
    }

    public BigDecimal getDiffbankprofit() {
        return diffbankprofit;
    }
    
    public void setDiffbankprofit(BigDecimal diffbankprofit) {
        this.diffbankprofit = diffbankprofit;
    }
    
    public BigDecimal getDiffyield() {
        return diffyield;
    }
    
    public void setDiffyield(BigDecimal diffyield) {
        this.diffyield = diffyield;
    }
    
    public BigDecimal getDiffprofit() {
        return diffprofit;
    }
    
    public void setDiffprofit(BigDecimal diffprofit) {
        this.diffprofit = diffprofit;
    }
    
    public BigDecimal getCustprofit() {
        return custprofit;
    }
    
    public void setCustprofit(BigDecimal custprofit) {
        this.custprofit = custprofit;
    }
    
}