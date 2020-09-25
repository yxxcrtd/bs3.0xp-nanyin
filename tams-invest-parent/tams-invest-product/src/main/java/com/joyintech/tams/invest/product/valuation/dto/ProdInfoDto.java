package com.joyintech.tams.invest.product.valuation.dto;

import java.util.Date;

public class ProdInfoDto {
	/**
	 * 主键
	 */
	private String prodid;
	/**
	 * 产品代码
	 */
	private String prodcode;
	/**
	 * 组合代码
	 */
	private String ptlid;
	/**
	 * 资金账户
	 */
	private String acctno;
	/**
	 * 分支序号
	 */
	private String branch;
	/**
	 * 产品名称
	 */
	private String prodname;
	/**
	 * 收益类型
	 */
	private String profittype;
	/**
	 * 起息日
	 */
	private Date vdate;

	/**
	 * 到期日
	 */
	private Date mdate;
	/**
	 * 计息基础
	 */
	private String basis;

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getProdcode() {
		return prodcode;
	}

	public void setProdcode(String prodcode) {
		this.prodcode = prodcode;
	}

	public String getPtlid() {
		return ptlid;
	}

	public void setPtlid(String ptlid) {
		this.ptlid = ptlid;
	}

	public String getAcctno() {
		return acctno;
	}

	public void setAcctno(String acctno) {
		this.acctno = acctno;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public String getProfittype() {
		return profittype;
	}

	public void setProfittype(String profittype) {
		this.profittype = profittype;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

}
