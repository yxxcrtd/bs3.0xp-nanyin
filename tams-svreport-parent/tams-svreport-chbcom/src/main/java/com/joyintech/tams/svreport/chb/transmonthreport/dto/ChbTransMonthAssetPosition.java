package com.joyintech.tams.svreport.chb.transmonthreport.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ChbTransMonthAssetPosition {
	/**
     * 报表开始日期
     */
    private Date startdate;

    /**
     * 报表结束日期
     */
    private Date enddate;
    /**
     * 产品类型
     */
    private String prodtype;
    /**
     * 资产负债二级分类
     */
    private String assettype2;
    /**
     * 评级结果
     */
    private String rating;
    /**
     * 是否属于政府投资基金 
     */
    private String isgovernfund1;
    /**
     * 商业银行二级资本债
     */
    private String bankseccapital;
    /**
     * 商业银行永续债
     */
    private String banksecperpetual;
    /**
     * 商业银行优先股
     */
    private String banksecprefer;
    /**
     * 债券性质
     */
    private String repsecnature;
    /**
     * 穿透后期末余额
     */
    private BigDecimal assetvalueB;
    /**
     * 剩余期限
     */
    private BigDecimal remainterm;
    /**
     * 所属机构
     * @return
     */
    private List<String> institutionlist;
    //add by mashukai 20201020 增加发行方式,产品代码 start
	/**
	 * 发行方式
	 * @return
	 */
	private String issueMode;

	/**
	 * 产品代码
	 * @return
	 */
	private String prodId;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getIssueMode() {
		return issueMode;
	}

	public void setIssueMode(String issueMode) {
		this.issueMode = issueMode;
	}
	//add by mashukai 20201020 增加发行方式 end
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public BigDecimal getAssetvalueB() {
		return assetvalueB;
	}

	public void setAssetvalueB(BigDecimal assetvalueB) {
		this.assetvalueB = assetvalueB;
	}

	public BigDecimal getRemainterm() {
		return remainterm;
	}

	public void setRemainterm(BigDecimal remainterm) {
		this.remainterm = remainterm;
	}

	public String getAssettype2() {
		return assettype2;
	}

	public void setAssettype2(String assettype2) {
		this.assettype2 = assettype2;
	}
	/**
     * 评级结果
     * @return RATING 评级结果
     */
    public String getRating() {
        return rating;
    }

    /**
     * 评级结果
     * @param rating 评级结果
     */
    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

	public String getIsgovernfund1() {
		return isgovernfund1;
	}

	public void setIsgovernfund1(String isgovernfund1) {
		this.isgovernfund1 = isgovernfund1 == null ? null : isgovernfund1.trim();
	}

	public String getBankseccapital() {
		return bankseccapital;
	}

	public void setBankseccapital(String bankseccapital) {
		this.bankseccapital = bankseccapital == null ? null : bankseccapital.trim();
	}

	public String getBanksecperpetual() {
		return banksecperpetual;
	}

	public void setBanksecperpetual(String banksecperpetual) {
		this.banksecperpetual = banksecperpetual == null ? null : banksecperpetual.trim();
	}

	public String getBanksecprefer() {
		return banksecprefer;
	}

	public void setBanksecprefer(String banksecprefer) {
		this.banksecprefer = banksecprefer == null ? null : banksecprefer.trim();
	}

	public String getRepsecnature() {
		return repsecnature;
	}

	public void setRepsecnature(String repsecnature) {
		this.repsecnature = repsecnature == null ? null : repsecnature.trim();
	}

	public List<String> getInstitutionlist() {
		return institutionlist;
	}

	public void setInstitutionlist(List<String> institutionlist) {
		this.institutionlist = institutionlist;
	}
    
}
