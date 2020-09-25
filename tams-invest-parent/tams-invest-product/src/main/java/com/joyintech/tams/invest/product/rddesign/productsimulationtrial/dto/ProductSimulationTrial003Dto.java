package com.joyintech.tams.invest.product.rddesign.productsimulationtrial.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品试算历史数据dto
 * 
 * @author jiaobo
 * @version 1.0 2019年11月2日
 * @see ProductSimulationTrial003Dto
 * @since 1.0
 */
public class ProductSimulationTrial003Dto extends BaseEntity implements ProcessEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 查询日
     */
    private Date checkDate;

    /**
     * 资产大类
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
     * 资产四级分类
     */
    private String assetTypeFour;

    /**
     * 资产五级分类
     */
    private String assetTypeFive;

    /**
     *  最小剩余期限（月）
     */
    private String lastMinPeriod;

    /**
     *  最大剩余期限（月）
     */
    private String lastMaxPeriod;

    /**
     * 加权收益率（%）
     */
    private BigDecimal additionalEquity;

    /**
     *  外部债项评级（不低于）
     */
    private String debtRating;

    /**
     *   外部主体评级（不低于）
     */
    private String fcrGradeOut;

    /**
     *  机构类型（经济类）
     */
    private String partyType;

    /**
     *  资产代码
     */
    private String assetId;

    /**
     *  资产名称
     */
    private String assetAbbr;

    /**
     *  买入日
     */
    private Date buyDate;

    /**
     *  买入持仓
     */
    private String buyHolding;

    /**
     *  到期收益率
     */
    private String mYield;

    /**
     *  资产五级分类名称
     */
    private String assetTypeFiveName;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetAbbr() {
        return assetAbbr;
    }

    public void setAssetAbbr(String assetAbbr) {
        this.assetAbbr = assetAbbr;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getBuyHolding() {
        return buyHolding;
    }

    public void setBuyHolding(String buyHolding) {
        this.buyHolding = buyHolding;
    }

    public String getmYield() {
       return this.mYield;
    }

    public String getmYieldStr() {
        return  ArithmeticUtil.multiply(new BigDecimal(this.mYield), new BigDecimal(100)).toString();
    }
    public void setmYield(String mYield) {
        this.mYield = mYield;
    }

    public String getAssetTypeFiveName() {
        return assetTypeFiveName;
    }

    public void setAssetTypeFiveName(String assetTypeFiveName) {
        this.assetTypeFiveName = assetTypeFiveName;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne;
    }

    public String getAssetTypeThree() {
        return assetTypeThree;
    }

    public void setAssetTypeThree(String assetTypeThree) {
        this.assetTypeThree = assetTypeThree;
    }

    public String getAssetTypeFour() {
        return assetTypeFour;
    }

    public void setAssetTypeFour(String assetTypeFour) {
        this.assetTypeFour = assetTypeFour;
    }

    public String getAssetTypeFive() {
        return assetTypeFive;
    }

    public void setAssetTypeFive(String assetTypeFive) {
        this.assetTypeFive = assetTypeFive;
    }

    public String getLastMinPeriod() {
        return lastMinPeriod;
    }

    public void setLastMinPeriod(String lastMinPeriod) {
        this.lastMinPeriod = lastMinPeriod;
    }

    public String getLastMaxPeriod() {
        return lastMaxPeriod;
    }

    public void setLastMaxPeriod(String lastMaxPeriod) {
        this.lastMaxPeriod = lastMaxPeriod;
    }

    public BigDecimal getAdditionalEquity() {
        return additionalEquity;
    }

    public void setAdditionalEquity(BigDecimal additionalEquity) {
        this.additionalEquity = additionalEquity;
    }

    public String getDebtRating() {
        return debtRating;
    }

    public void setDebtRating(String debtRating) {
        this.debtRating = debtRating;
    }

    public String getFcrGradeOut() {
        return fcrGradeOut;
    }

    public void setFcrGradeOut(String fcrGradeOut) {
        this.fcrGradeOut = fcrGradeOut;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getAssetTypeTwo() {
        return assetTypeTwo;
    }

    public void setAssetTypeTwo(String assetTypeTwo) {
        this.assetTypeTwo = assetTypeTwo;
    }
}
