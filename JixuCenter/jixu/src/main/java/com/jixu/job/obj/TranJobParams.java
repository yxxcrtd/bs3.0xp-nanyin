package com.jixu.job.obj;

import java.util.List;

import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

public class TranJobParams {
	
	public TranJobParams(GLCOOABase pursaleCooa, TranBasic tranBasic, Tran tran, List<Tran> tranList, 
			double totalFeeFunc, String chartnum, int groupnum) {
		this.pursaleCooa=pursaleCooa;
		this.tranBasic=tranBasic;
		this.tran=tran;
		this.tranList=tranList;
		this.totalFeeFunc=totalFeeFunc;
		this.chartnum=chartnum;
		this.groupnum=groupnum;
	}
	
	private GLCOOABase pursaleCooa;
	
	//输入的交易
	private TranBasic tranBasic;
	
	//出凭证的交易
	private Tran tran;
	
	//该lot当天所有的交易
	private List<Tran> tranList;
	
	//总费用
	private double totalFeeFunc;
	
	//科目表
	private String chartnum;
	
	//总账栏
	private int groupnum;

	public GLCOOABase getPursaleCooa() {
		return pursaleCooa;
	}

	public void setPursaleCooa(GLCOOABase pursaleCooa) {
		this.pursaleCooa = pursaleCooa;
	}

	public TranBasic getTranBasic() {
		return tranBasic;
	}

	public void setTranBasic(TranBasic tranBasic) {
		this.tranBasic = tranBasic;
	}

	public Tran getTran() {
		return tran;
	}

	public void setTran(Tran tran) {
		this.tran = tran;
	}

	public List<Tran> getTranList() {
		return tranList;
	}

	public void setTranList(List<Tran> tranList) {
		this.tranList = tranList;
	}

	public double getTotalFeeFunc() {
		return totalFeeFunc;
	}

	public void setTotalFeeFunc(double totalFeeFunc) {
		this.totalFeeFunc = totalFeeFunc;
	}

	public String getChartnum() {
		return chartnum;
	}

	public void setChartnum(String chartnum) {
		this.chartnum = chartnum;
	}

	public int getGroupnum() {
		return groupnum;
	}

	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
}
