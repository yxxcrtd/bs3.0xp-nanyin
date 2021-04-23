/*
 * 文件名：SecInfoModelDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.bankuplink.calc.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 债券基本信息
 * 
 * @author zhangliang
 * @version 1.0 2020年5月20日
 * @see SecInfoModelDto
 * @since 1.0
 */
public class SecInfoModelDto {

    /**
     * 债券起息日
     */
    private Date startDate;

    /**
     * 债券到期日
     */
    private Date endDate;

    /**
     * 债券首次付息日
     */
    private Date firstPayDate;

    /**
     * 发行价
     */
    private BigDecimal issuePrice;

    /**
     * 债券类型
     */
    private String bondsType;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 利率类型
     */
    private String intRateType;

    /**
     * 利息分配方式
     */
    private String allocationType;

    /**
     * 付息计划推算方式
     */
    private String calMethod;

    /**
     * 付息频率
     */
    private String yearIntRate;

    /**
     * 下一行权日
     */
    private Date nextExeDate;

    /**
     * 下一行权价
     */
    private BigDecimal nextExercisePrice;
    
    /**
     * 登记托管机构---add by 裴家俊
     */
    private String registOrg;
    
    /**
     * 面值
     */
    private BigDecimal faceValue;
    
    /**
     * 票面利率
     */
    private BigDecimal paperir;

    /**
     * 获取 startDate
     *
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 startDate
     *
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 endDate
     *
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 endDate
     *
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 firstPayDate
     *
     * @return firstPayDate
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 firstPayDate
     *
     * @param firstPayDate firstPayDate
     */
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }

    /**
     * 获取 issuePrice
     *
     * @return issuePrice
     */
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    /**
     * 设置 issuePrice
     *
     * @param issuePrice issuePrice
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    /**
     * 获取 bondsType
     *
     * @return bondsType
     */
    public String getBondsType() {
        return bondsType;
    }

    /**
     * 设置 bondsType
     *
     * @param bondsType bondsType
     */
    public void setBondsType(String bondsType) {
        this.bondsType = bondsType;
    }

    /**
     * 获取 basis
     *
     * @return basis
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 intRateType
     *
     * @return intRateType
     */
    public String getIntRateType() {
        return intRateType;
    }

    /**
     * 设置 intRateType
     *
     * @param intRateType intRateType
     */
    public void setIntRateType(String intRateType) {
        this.intRateType = intRateType;
    }

    /**
     * 获取 allocationType
     *
     * @return allocationType
     */
    public String getAllocationType() {
        return allocationType;
    }

    /**
     * 设置 allocationType
     *
     * @param allocationType allocationType
     */
    public void setAllocationType(String allocationType) {
        this.allocationType = allocationType;
    }

    /**
     * 获取 calMethod
     *
     * @return calMethod
     */
    public String getCalMethod() {
        return calMethod;
    }

    /**
     * 设置 calMethod
     *
     * @param calMethod calMethod
     */
    public void setCalMethod(String calMethod) {
        this.calMethod = calMethod;
    }

    /**
     * 获取 yearIntRate
     *
     * @return yearIntRate
     */
    public String getYearIntRate() {
        return yearIntRate;
    }

    /**
     * 设置 yearIntRate
     *
     * @param yearIntRate yearIntRate
     */
    public void setYearIntRate(String yearIntRate) {
        this.yearIntRate = yearIntRate;
    }

    /**
     * 获取 nextExeDate
     *
     * @return nextExeDate
     */
    public Date getNextExeDate() {
        return nextExeDate;
    }

    /**
     * 设置 nextExeDate
     *
     * @param nextExeDate nextExeDate
     */
    public void setNextExeDate(Date nextExeDate) {
        this.nextExeDate = nextExeDate;
    }

    /**
     * 获取 nextExercisePrice
     *
     * @return nextExercisePrice
     */
    public BigDecimal getNextExercisePrice() {
        return nextExercisePrice;
    }

    /**
     * 设置 nextExercisePrice
     *
     * @param nextExercisePrice nextExercisePrice
     */
    public void setNextExercisePrice(BigDecimal nextExercisePrice) {
        this.nextExercisePrice = nextExercisePrice;
    }

	public String getRegistOrg() {
		return registOrg;
	}

	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}
    
    
    /**
     * 获取 faceValue
     * @return faceValue.
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    
    /**
     * 设置 faceValue
     * @param faceValue faceValue
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    
    
    
    /**
     * 获取 paperir
     * @return paperir.
     */
    public BigDecimal getPaperir() {
        return paperir;
    }

    
    /**
     * 设置 paperir
     * @param paperir paperir
     */
    public void setPaperir(BigDecimal paperir) {
        this.paperir = paperir;
    }



    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 券面总额
     */
    private BigDecimal prinAmt;

    /**
     * 交割日
     */
    private Date settleDate;

    /**
     * 计算类型
     */
    private String calculateField;
    
    
    /**
     * 净价
     */
    private BigDecimal cleanprice;
    
    /**
     * 全价
     */
    private BigDecimal dirtyprice;
    
    /**
     * 净价总额(2位小数)
     */
    private BigDecimal cpriceamt;
    
    /**
     * 全价总额(2位小数)
     */
    private BigDecimal dpriceamt;
    
    /**
     * 应计利息
     */
    private BigDecimal accuir;
    
    /**
     * 应计利息总额(2位小数)
     */
    private BigDecimal accuiramt;
    
    /**
     * 到期收益率
     */
    private BigDecimal matureyield;
    
    /**
     * 行权收益率
     */
    private BigDecimal exerciseyield;
    
    /**
     * 交易金额(2位小数) 交易金额=净价总额
     */
    private BigDecimal dealamt;
    
    /**
     * 结算金额(2位小数) 结算金额=交易金额+应计利息总额
     */
    private BigDecimal settleamt;
    
    /**
     * 每百元金额
     */
    private BigDecimal absPrinAmtHun;
    
    /**
     * 本金额
     */
    private BigDecimal absPrinAmt;
    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 prinAmt
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    
    /**
     * 设置 prinAmt
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    
    /**
     * 获取 settleDate
     * @return settleDate.
     */
    public Date getSettleDate() {
        return settleDate;
    }

    
    /**
     * 设置 settleDate
     * @param settleDate settleDate
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    
    /**
     * 获取 calculateField
     * @return calculateField.
     */
    public String getCalculateField() {
        return calculateField;
    }

    
    /**
     * 设置 calculateField
     * @param calculateField calculateField
     */
    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }

    
    /**
     * 获取 cleanprice
     * @return cleanprice.
     */
    public BigDecimal getCleanprice() {
        return cleanprice;
    }

    
    /**
     * 设置 cleanprice
     * @param cleanprice cleanprice
     */
    public void setCleanprice(BigDecimal cleanprice) {
        this.cleanprice = cleanprice;
    }

    
    /**
     * 获取 dirtyprice
     * @return dirtyprice.
     */
    public BigDecimal getDirtyprice() {
        return dirtyprice;
    }

    
    /**
     * 设置 dirtyprice
     * @param dirtyprice dirtyprice
     */
    public void setDirtyprice(BigDecimal dirtyprice) {
        this.dirtyprice = dirtyprice;
    }

    
    /**
     * 获取 cpriceamt
     * @return cpriceamt.
     */
    public BigDecimal getCpriceamt() {
        return cpriceamt;
    }

    
    /**
     * 设置 cpriceamt
     * @param cpriceamt cpriceamt
     */
    public void setCpriceamt(BigDecimal cpriceamt) {
        this.cpriceamt = cpriceamt;
    }

    
    /**
     * 获取 dpriceamt
     * @return dpriceamt.
     */
    public BigDecimal getDpriceamt() {
        return dpriceamt;
    }

    
    /**
     * 设置 dpriceamt
     * @param dpriceamt dpriceamt
     */
    public void setDpriceamt(BigDecimal dpriceamt) {
        this.dpriceamt = dpriceamt;
    }

    
    /**
     * 获取 accuir
     * @return accuir.
     */
    public BigDecimal getAccuir() {
        return accuir;
    }

    
    /**
     * 设置 accuir
     * @param accuir accuir
     */
    public void setAccuir(BigDecimal accuir) {
        this.accuir = accuir;
    }

    
    /**
     * 获取 accuiramt
     * @return accuiramt.
     */
    public BigDecimal getAccuiramt() {
        return accuiramt;
    }

    
    /**
     * 设置 accuiramt
     * @param accuiramt accuiramt
     */
    public void setAccuiramt(BigDecimal accuiramt) {
        this.accuiramt = accuiramt;
    }

    
    /**
     * 获取 matureyield
     * @return matureyield.
     */
    public BigDecimal getMatureyield() {
        return matureyield;
    }

    
    /**
     * 设置 matureyield
     * @param matureyield matureyield
     */
    public void setMatureyield(BigDecimal matureyield) {
        this.matureyield = matureyield;
    }

    
    /**
     * 获取 exerciseyield
     * @return exerciseyield.
     */
    public BigDecimal getExerciseyield() {
        return exerciseyield;
    }

    
    /**
     * 设置 exerciseyield
     * @param exerciseyield exerciseyield
     */
    public void setExerciseyield(BigDecimal exerciseyield) {
        this.exerciseyield = exerciseyield;
    }

    
    /**
     * 获取 dealamt
     * @return dealamt.
     */
    public BigDecimal getDealamt() {
        return dealamt;
    }

    
    /**
     * 设置 dealamt
     * @param dealamt dealamt
     */
    public void setDealamt(BigDecimal dealamt) {
        this.dealamt = dealamt;
    }

    
    /**
     * 获取 settleamt
     * @return settleamt.
     */
    public BigDecimal getSettleamt() {
        return settleamt;
    }

    
    /**
     * 设置 settleamt
     * @param settleamt settleamt
     */
    public void setSettleamt(BigDecimal settleamt) {
        this.settleamt = settleamt;
    }

    
    /**
     * 获取 absPrinAmtHun
     * @return absPrinAmtHun.
     */
    public BigDecimal getAbsPrinAmtHun() {
        return absPrinAmtHun;
    }

    
    /**
     * 设置 absPrinAmtHun
     * @param absPrinAmtHun absPrinAmtHun
     */
    public void setAbsPrinAmtHun(BigDecimal absPrinAmtHun) {
        this.absPrinAmtHun = absPrinAmtHun;
    }

    
    /**
     * 获取 absPrinAmt
     * @return absPrinAmt.
     */
    public BigDecimal getAbsPrinAmt() {
        return absPrinAmt;
    }

    
    /**
     * 设置 absPrinAmt
     * @param absPrinAmt absPrinAmt
     */
    public void setAbsPrinAmt(BigDecimal absPrinAmt) {
        this.absPrinAmt = absPrinAmt;
    }
    
    
}
