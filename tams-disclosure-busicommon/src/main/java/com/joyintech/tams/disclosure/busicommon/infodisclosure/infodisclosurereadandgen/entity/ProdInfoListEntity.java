package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

public class ProdInfoListEntity {
	//披露截止日
	private Date revDeadDate;
	//募集起始日期
	private Date startDate;
	//基准日
	private Date basisDate;
	
	/**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品登记编码
     */
    private String prodRe;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品简称
     */
    private String prodAbbr;

    /**
     * 募集下限
     */
    private String raiseMin;

    /**
     * 募集上限
     */
    private String raiseMax;

    /**
     * 募集开始日
     */
    private Date raiseStrdate;

    /**
     * 募集结束日
     */
    private Date raiseEnddate;

    /**
     * 募集币种
     */
    private String raiseCcy;

    /**
     * 产品成立日
     */
    private Date setDate;

    /**
     * 产品起息日
     */
    private Date vdate;

    /**
     * 首次开放日
     */
    private Date firstOpenDay;

    /**
     * 产品到期日
     */
    private Date mdate;

    /**
     * 实际到期日
     */
    private Date actMdate;

    /**
     * 产品期限
     */
    private String term;

    /**
     * 产品清盘日
     */
    private Date windupDate;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 发行状态
     */
    private String issueStatus;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 产品风险等级
     */
    private String riskLevel;
    
    /**
     * 披露渠道
     */
    private String revealChannel;
    /**
     * 开始日
     */
    private Date strDate;
    /**
     * 结束日
     */
    private Date endDate;
    

    public String getRevealChannel() {
        return revealChannel;
    }

    
    public void setRevealChannel(String revealChannel) {
        this.revealChannel = revealChannel;
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
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getProdRe() {
        return prodRe;
    }

    /**
     * 设置 产品登记编码
     * @param prodRe 产品登记编码
     */
    public void setProdRe(String prodRe) {
        this.prodRe = prodRe == null ? null : prodRe.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品简称
     * @return 产品简称
     */
    public String getProdAbbr() {
        return prodAbbr;
    }

    /**
     * 设置 产品简称
     * @param prodAbbr 产品简称
     */
    public void setProdAbbr(String prodAbbr) {
        this.prodAbbr = prodAbbr == null ? null : prodAbbr.trim();
    }

    /**
     * 取得 募集下限
     * @return 募集下限
     */
    public String getRaiseMin() {
        return raiseMin;
    }

    /**
     * 设置 募集下限
     * @param raiseMin 募集下限
     */
    public void setRaiseMin(String raiseMin) {
        this.raiseMin = raiseMin == null ? null : raiseMin.trim();
    }

    /**
     * 取得 募集上限
     * @return 募集上限
     */
    public String getRaiseMax() {
        return raiseMax;
    }

    /**
     * 设置 募集上限
     * @param raiseMax 募集上限
     */
    public void setRaiseMax(String raiseMax) {
        this.raiseMax = raiseMax == null ? null : raiseMax.trim();
    }

    /**
     * 取得 募集开始日
     * @return 募集开始日
     */
    public Date getRaiseStrdate() {
        return raiseStrdate;
    }

    /**
     * 设置 募集开始日
     * @param raiseStrdate 募集开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseStrdate(Date raiseStrdate) {
        this.raiseStrdate = raiseStrdate;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 设置 募集结束日
     * @param raiseEnddate 募集结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 募集币种
     * @return 募集币种
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 募集币种
     * @param raiseCcy 募集币种
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 产品成立日
     * @return 产品成立日
     */
    public Date getSetDate() {
        return setDate;
    }

    /**
     * 设置 产品成立日
     * @param setDate 产品成立日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 产品起息日
     * @param vdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 首次开放日
     * @return 首次开放日
     */
    public Date getFirstOpenDay() {
        return firstOpenDay;
    }

    /**
     * 设置 首次开放日
     * @param firstOpenDay 首次开放日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstOpenDay(Date firstOpenDay) {
        this.firstOpenDay = firstOpenDay;
    }

    /**
     * 取得 产品到期日
     * @return 产品到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 产品到期日
     * @param mdate 产品到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 实际到期日
     * @return 实际到期日
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 实际到期日
     * @param actMdate 实际到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 产品期限
     * @param term 产品期限
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 产品清盘日
     * @return 产品清盘日
     */
    public Date getWindupDate() {
        return windupDate;
    }

    /**
     * 设置 产品清盘日
     * @param windupDate 产品清盘日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setWindupDate(Date windupDate) {
        this.windupDate = windupDate;
    }

    /**
     * 取得 发行方式
     * @return 发行方式
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 发行方式
     * @param issueMode 发行方式
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode == null ? null : issueMode.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品类型
     * @return 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 产品类型
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }


    
    public String getIssueStatus() {
        return issueStatus;
    }


    
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus  == null ? null : issueStatus.trim();
    }


    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 产品风险等级
     * @return 产品风险等级
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * 设置 产品风险等级
     * @param riskLevel 产品风险等级
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }
	
	public Date getRevDeadDate() {
		return revDeadDate;
	}
	public void setRevDeadDate(Date revDeadDate) {
		this.revDeadDate = revDeadDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getBasisDate() {
		return basisDate;
	}
	public void setBasisDate(Date basisDate) {
		this.basisDate = basisDate;
	}


    
    public Date getStrDate() {
        return strDate;
    }


    
    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }


    
    public Date getEndDate() {
        return endDate;
    }


    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
	
}
