package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

public class STCostWrapObj implements Serializable {

	private static final long serialVersionUID = 4387460824927556201L;
	
	//这里的两个clo是只有cost, marketvalue等值，没有port,security等信息
	private List<TranClo> priordayCost = new ArrayList<TranClo>(); //用List是为了计算多lot.
	private List<TranClo> asofdayCost = new ArrayList<TranClo>();  //用List是为了计算多lot.
	private TranClo tranClo;
	private String cusip;
	private IssueInfo sec;
	private int glgroupnum; //总账组ID
	private List<Tran> tranList=new ArrayList<Tran>();

	public int getGlgroupnum() {
		return glgroupnum;
	}

	public void setGlgroupnum(int glgroupnum) {
		this.glgroupnum = glgroupnum;
	}
	
	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public List<TranClo> getPriordayCost() {
		return priordayCost;
	}

	public void setPriordayCost(List<TranClo> priordayCost) {
		this.priordayCost = priordayCost;
	}

	public List<TranClo> getAsofdayCost() {
		return asofdayCost;
	}

	public void setAsofdayCost(List<TranClo> asofdayCost) {
		this.asofdayCost = asofdayCost;
	}

	public TranClo getTranClo() {
		return tranClo;
	}

	public void setTranClo(TranClo tranClo) {
		this.tranClo = tranClo;
	}

	public List<Tran> getTranList() {
		return tranList;
	}

	public void setTranList(List<Tran> tranList) {
		this.tranList = tranList;
	}

	public IssueInfo getSec() {
		return sec;
	}

	public void setSec(IssueInfo sec) {
		this.sec = sec;
	}
}
