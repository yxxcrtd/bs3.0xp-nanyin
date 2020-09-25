package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

public class TransactionWrapObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7998200370514220989L;
	
	//交易基础记录
	private TranBasic tranBasic;
	
	/*交易记录*/
	private List<Tran> tranList = new ArrayList<Tran>();
	
	/*交易费用*/
	private List<TranBasicFee> transFeeList = null;
	
	/**
	 * 该lot当天所有的交易
	 */
	private List<Tran> todayTranList=new ArrayList<Tran>();
	
	/*当天汇率*/
	private double fxrate;

	public double getFxrate() {
		return fxrate;
	}

	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	public List<TranBasicFee> getTransFeeList() {
		return transFeeList;
	}

	public void setTransFeeList(List<TranBasicFee> transFeeList) {
		this.transFeeList = transFeeList;
	}

	public List<Tran> getTrandataList() {
		return tranList;
	}

	public void setTrandataList(List<Tran> tranList) {
		this.tranList = tranList;
	}

	public TranBasic getTranBasic() {
		return tranBasic;
	}

	public void setTranBasic(TranBasic tranBasic) {
		this.tranBasic = tranBasic;
	}

	public List<Tran> getTodayTranList() {
		return todayTranList;
	}

	public void setTodayTranList(List<Tran> todayTranList) {
		this.todayTranList = todayTranList;
	}
}
