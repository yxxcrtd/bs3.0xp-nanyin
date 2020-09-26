package com.joyintech.tams.svreport.chb.transmonthreport.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:CHB_TRANS_MONTH_PRD_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间:
 */
public class ChbTransMonthPrdPosition {
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
     * 募集方式
     */
    private String raisemode;
    /**
     * 投资性质
     */
    private String ivstorientation;
    /**
     * 运行方式
     */
    private String operateMode;
    /**
     * 是否现金管理类
     */
    private String cashmanacate;
    /**
     * 开放期限
     */
    private String openduration;
    /**
     * 期限品种
     */
    private String termtype;

    /**
     * 期末余额
     */
    private BigDecimal endamtsumOth;
    
    /**
     * 产品价格
     * 
     */
    private BigDecimal prodprice;
    /**
     * 所属机构
     * @return
     */
    private List<String> institutionlist;
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

	public String getRaisemode() {
		return raisemode;
	}

	public void setRaisemode(String raisemode) {
		 this.raisemode = raisemode == null ? null : raisemode.trim();
	}

	public BigDecimal getEndamtsumOth() {
		return endamtsumOth;
	}

	public void setEndamtsumOth(BigDecimal endamtsumOth) {
		this.endamtsumOth = endamtsumOth;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public BigDecimal getProdprice() {
		return prodprice;
	}

	public void setProdprice(BigDecimal prodprice) {
		this.prodprice = prodprice;
	}

	public String getIvstorientation() {
		return ivstorientation;
	}

	public void setIvstorientation(String ivstorientation) {
		  this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
	}

	public String getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
	}

	public String getCashmanacate() {
		return cashmanacate;
	}

	public void setCashmanacate(String cashmanacate) {
		this.cashmanacate = cashmanacate;
	}

	public String getOpenduration() {
		return openduration;
	}

	public void setOpenduration(String openduration) {
		this.openduration = openduration;
	}

	public String getTermtype() {
		return termtype;
	}

	public void setTermtype(String termtype) {
		  this.termtype = termtype == null ? null : termtype.trim();
	}

	public List<String> getInstitutionlist() {
		return institutionlist;
	}

	public void setInstitutionlist(List<String> institutionlist) {
		this.institutionlist = institutionlist;
	}


    
}
