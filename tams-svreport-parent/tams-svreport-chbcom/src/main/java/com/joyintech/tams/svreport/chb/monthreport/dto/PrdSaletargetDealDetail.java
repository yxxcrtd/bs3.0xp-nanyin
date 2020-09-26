package com.joyintech.tams.svreport.chb.monthreport.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class PrdSaletargetDealDetail {
	
	private String prodid; //产品代码
	private String dealdate;//统计日期
	private String prodtype;//产品类型1-个人 2-高资产净值 3-私人银行 4-机构 5-金融同业
	private BigDecimal perprinqty;//一般个人客户份额
	private BigDecimal hnacorinqty;//高资产净值客户份额
	private BigDecimal pbcrinqty;//私人银行客户份额
	private BigDecimal bisrinqty;//银行类机构份额
	private BigDecimal oficrinqty;//其他金融机构客户份额
	private BigDecimal nficrinqty;//非金融机构客户份额
	private BigDecimal amprodrinqty;//资产管理产品份额
	private BigDecimal otherqty;//其他份额
	private Date lstmntdate;//最后入库时间
	private BigDecimal naturalpersonqty ;//自然人份额 包含：一般个人客户份额，高资产净值客户份额，私人银行客户份额
	private BigDecimal nonfinancialinstitution ;//非金融机构份额  包含：非金融机构客户份额，资产管理产品份额，其他份额
	private String rateflag;//汇率系统参数
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getDealdate() {
		return dealdate;
	}
	public void setDealdate(String dealdate) {
		this.dealdate = dealdate;
	}
	public String getProdtype() {
		return prodtype;
	}
	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}
	public BigDecimal getPerprinqty() {
		return perprinqty;
	}
	public void setPerprinqty(BigDecimal perprinqty) {
		this.perprinqty = perprinqty;
	}
	public BigDecimal getHnacorinqty() {
		return hnacorinqty;
	}
	public void setHnacorinqty(BigDecimal hnacorinqty) {
		this.hnacorinqty = hnacorinqty;
	}
	public BigDecimal getPbcrinqty() {
		return pbcrinqty;
	}
	public void setPbcrinqty(BigDecimal pbcrinqty) {
		this.pbcrinqty = pbcrinqty;
	}
	public BigDecimal getBisrinqty() {
		return bisrinqty;
	}
	public void setBisrinqty(BigDecimal bisrinqty) {
		this.bisrinqty = bisrinqty;
	}
	public BigDecimal getOficrinqty() {
		return oficrinqty;
	}
	public void setOficrinqty(BigDecimal oficrinqty) {
		this.oficrinqty = oficrinqty;
	}
	public BigDecimal getNficrinqty() {
		return nficrinqty;
	}
	public void setNficrinqty(BigDecimal nficrinqty) {
		this.nficrinqty = nficrinqty;
	}
	public BigDecimal getAmprodrinqty() {
		return amprodrinqty;
	}
	public void setAmprodrinqty(BigDecimal amprodrinqty) {
		this.amprodrinqty = amprodrinqty;
	}
	public BigDecimal getOtherqty() {
		return otherqty;
	}
	public void setOtherqty(BigDecimal otherqty) {
		this.otherqty = otherqty;
	}
	public Date getLstmntdate() {
		return lstmntdate;
	}
	public void setLstmntdate(Date lstmntdate) {
		this.lstmntdate = lstmntdate;
	}
	public BigDecimal getNaturalpersonqty() {
		return naturalpersonqty;
	}
	public void setNaturalpersonqty(BigDecimal naturalpersonqty) {
		this.naturalpersonqty = naturalpersonqty;
	}
	public BigDecimal getNonfinancialinstitution() {
		return nonfinancialinstitution;
	}
	public void setNonfinancialinstitution(BigDecimal nonfinancialinstitution) {
		this.nonfinancialinstitution = nonfinancialinstitution;
	}
	public String getRateflag() {
		return rateflag;
	}
	public void setRateflag(String rateflag) {
		this.rateflag = rateflag;
	}
	
	
}