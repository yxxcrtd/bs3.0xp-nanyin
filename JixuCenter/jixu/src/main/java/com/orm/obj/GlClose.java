package com.orm.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		ExchangeCfg
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[投资组合的总账关帐表。记录每天每个科目的借贷余额]  
 */

@Entity 
@Table(name="JXJ_Gl_CLOSE") 
public class GlClose extends BasicEntity{
	private int glcloseID;
	
	private PortfolioHdr portfolio;
	
	private String acctnum;
	
	private double dr_func;
	
	private double cr_func;
	
	private double dr_local;
	
	private double cr_local;
	
	private Date closeDate;
	
	/**
	 * 分级基金代码
	 */
	private int classid;
	
	private String currencycode;

	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="GlClose_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="glcloseID")
	public int getGlcloseID() {
		return glcloseID;
	}

	public void setGlcloseID(int glcloseID) {
		this.glcloseID = glcloseID;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name="acctnum")
	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	@Column(name="dr_func")
	public double getDr_func() {
		return dr_func;
	}

	public void setDr_func(double dr_func) {
		this.dr_func = dr_func;
	}

	@Column(name="cr_func")
	public double getCr_func() {
		return cr_func;
	}

	public void setCr_func(double cr_func) {
		this.cr_func = cr_func;
	}

	@Column(name="dr_local")
	public double getDr_local() {
		return dr_local;
	}

	public void setDr_local(double dr_local) {
		this.dr_local = dr_local;
	}

	@Column(name="cr_local")
	public double getCr_local() {
		return cr_local;
	}

	public void setCr_local(double cr_local) {
		this.cr_local = cr_local;
	}

	@Column(name="closeDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name="classid")
	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	@Column(name="CURRENCYCODE")
	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
}
