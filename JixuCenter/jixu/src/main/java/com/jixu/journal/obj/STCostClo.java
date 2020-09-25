package com.jixu.journal.obj;

import java.util.ArrayList;
import java.util.List;

import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		STCostClo
 * @author  		Pan Wei 
 * @Date 			2018年4月16日 下午5:04:10 	
 * @Description		[]  
 */

public class STCostClo {
	private String cusiplot;
	
	private String cusip;
	
	private TranClo lastClo;;
	
	private TranClo todayClo;
	
	private List<Tran> todayTrans;
	
	public STCostClo() {
		todayTrans=new ArrayList<Tran>();
	}

	public String getCusiplot() {
		return cusiplot;
	}

	public void setCusiplot(String cusiplot) {
		this.cusiplot = cusiplot;
	}

	public List<Tran> getTodayTrans() {
		return todayTrans;
	}

	public void setTodayTrans(List<Tran> todayTrans) {
		this.todayTrans = todayTrans;
	}

	public TranClo getLastClo() {
		return lastClo;
	}

	public void setLastClo(TranClo lastClo) {
		this.lastClo = lastClo;
	}

	public TranClo getTodayClo() {
		return todayClo;
	}

	public void setTodayClo(TranClo todayClo) {
		this.todayClo = todayClo;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
}
