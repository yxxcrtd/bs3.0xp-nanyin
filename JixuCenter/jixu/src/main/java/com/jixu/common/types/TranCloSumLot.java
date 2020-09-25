package com.jixu.common.types;

import java.util.ArrayList;
import java.util.List;

import com.orm.obj.Tran;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		TranCloLotType
 * @author  		Pan Wei 
 * @Date 			2018年4月9日 上午10:16:40 	
 * @Description		[用在计算头寸时]  
 */

public class TranCloSumLot {
	
	private String cusip;
	
	private int lotnum;
	
	private TranClo tclo;
	
	private TranSum tsum;
	
	private List<Tran> tranList;
	
	private List<Tran> tranSettleList;
	
	public TranCloSumLot(String cusip, int lotnum) {
		this.setCusip(cusip);
		this.setLotnum(lotnum);
		this.setTranList(new ArrayList<Tran>());
		this.setTranSettleList(new ArrayList<Tran>());
	}
	
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	public TranClo getTclo() {
		return tclo;
	}

	public void setTclo(TranClo tclo) {
		this.tclo = tclo;
	}

	public TranSum getTsum() {
		return tsum;
	}

	public void setTsum(TranSum tsum) {
		this.tsum = tsum;
	}

	public List<Tran> getTranList() {
		return tranList;
	}

	public void setTranList(List<Tran> tranList) {
		this.tranList = tranList;
	}

	public List<Tran> getTranSettleList() {
		return tranSettleList;
	}

	public void setTranSettleList(List<Tran> tranSettleList) {
		this.tranSettleList = tranSettleList;
	}
}
