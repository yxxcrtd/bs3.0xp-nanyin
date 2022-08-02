/**
 * 
 */
package com.joyintech.tams.disclosure.busicommon.rptdata.dto;

import java.util.Date;

/**
 * 产品类报表基类
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see ReportDto
 * @since 1.0
 */

public class ReportDto {
	
	private String repProdType;  //产品分类：信披产品类别：01 母产品/02 普通产品/03子产品
	private String prodId;         //产品代码
	private String prodRe;  // 登记编码
	private String prodName;  // 产品名称
	private Date eDate;  //成立日期
	private Date mDate;  //到期日
	private String prodManager; //管理人
	private String prodTruster; //托管人
	
	private Date reportDate;  //报告的数据统计日期
	private Date genDate;  //报告生成日期/签字日期
	private Date disDate;  //报告披露日期
	
	public String getRepProdType() {
		return repProdType;
	}
	public void setRepProdType(String repProdType) {
		this.repProdType = repProdType;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdRe() {
		return prodRe;
	}
	public void setProdRe(String prodRe) {
		this.prodRe = prodRe;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Date getGenDate() {
		return genDate;
	}
	public void setGenDate(Date genDate) {
		this.genDate = genDate;
	}
	public Date getDisDate() {
		return disDate;
	}
	public void setDisDate(Date disDate) {
		this.disDate = disDate;
	}
	public String getProdTruster() {
		return prodTruster;
	}
	public void setProdTruster(String prodTruster) {
		this.prodTruster = prodTruster;
	}
	public String getProdManager() {
		return prodManager;
	}
	public void setProdManager(String prodManager) {
		this.prodManager = prodManager;
	}

}
