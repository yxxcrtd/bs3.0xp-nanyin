package com.jixu.common.types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		TranCloType
 * @author  		Pan Wei 
 * @Date 			2018年4月8日 下午11:19:00 	
 * @Description		[]  
 */

public class TranCloType {
	private String portnum;
	
	private String cusip;
	
	//关帐日
	private Date closeDate;
	
	//上一个关帐日的tranclo记录
	private List<TranClo> lastClo;
	
	//上一个关帐日到今天的该股票的交易
	private List<Tran> periodTrans;
	
	private List<Tran> periodSettleTrans;
	
	private boolean cloJobFlag;
	
	/**
	 * @param portnum	投资组合
	 * @param cusip		证券
	 * @param closeDate	关帐日期
	 */
	public TranCloType(String portnum, String cusip, Date closeDate) {
		this.setPortnum(portnum);
		this.setCusip(cusip);
		this.setCloseDate(closeDate);
		this.setLastClo(new ArrayList<TranClo>());
		this.setPeriodTrans(new ArrayList<Tran>());
		this.setPeriodSettleTrans(new ArrayList<Tran>());
		this.setCloJobFlag(false);
	}
	
	/**
	 * @param portnum	投资组合
	 * @param cusip		证券
	 * @param closeDate	关帐日期
	 */
	public TranCloType(String portnum, String cusip, Date closeDate, List<TranClo> cloList, List<Tran> tranList) {
		this.setPortnum(portnum);
		this.setCusip(cusip);
		this.setCloseDate(closeDate);
		this.setLastClo(cloList);
		this.setPeriodTrans(tranList);
		this.setCloJobFlag(true);
	}
	
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public List<TranClo> getLastClo() {
		return lastClo;
	}

	public void setLastClo(List<TranClo> lastClo) {
		this.lastClo = lastClo;
	}

	public List<Tran> getPeriodTrans() {
		return periodTrans;
	}

	public void setPeriodTrans(List<Tran> periodTrans) {
		this.periodTrans = periodTrans;
	}

	public boolean isCloJobFlag() {
		return cloJobFlag;
	}

	public void setCloJobFlag(boolean cloJobFlag) {
		this.cloJobFlag = cloJobFlag;
	}

	public List<Tran> getPeriodSettleTrans() {
		return periodSettleTrans;
	}

	public void setPeriodSettleTrans(List<Tran> periodSettleTrans) {
		this.periodSettleTrans = periodSettleTrans;
	}
}
