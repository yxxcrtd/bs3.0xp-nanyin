package com.jixu.job.obj;

/** 
 * @ClassName 		YGZTotalObj
 * @author  		Pan Wei 
 * @Date 			2018年5月7日 下午11:29:52 	
 * @Description		[]  
 */

public class YGZCalObj {
	//当日税费基准余额
	private double drsfjzye;
	
	//当日税费基准发生额
	private double drsfjzfse;
	
	//当日税费发生额
	private double drsffse;
	
	//当日抵扣金额
	private double drdkje;
	
	public YGZCalObj() {
	}

	public YGZCalObj(double drsfjzye, double drsfjzfse) {
		this.drsfjzye=drsfjzye;
		this.drsfjzfse=drsfjzfse;
	}
	
	public YGZCalObj(double drsfjzye, double drsfjzfse, double drsffse, double drdkje) {
		this.drsfjzye=drsfjzye;
		this.drsfjzfse=drsfjzfse;
		this.drsffse=drsffse;
		this.drdkje=drdkje;
	}

	public double getDrsfjzye() {
		return drsfjzye;
	}

	public void setDrsfjzye(double drsfjzye) {
		this.drsfjzye = drsfjzye;
	}

	public double getDrsfjzfse() {
		return drsfjzfse;
	}

	public void setDrsfjzfse(double drsfjzfse) {
		this.drsfjzfse = drsfjzfse;
	}

	public double getDrsffse() {
		return drsffse;
	}

	public void setDrsffse(double drsffse) {
		this.drsffse = drsffse;
	}

	public double getDrdkje() {
		return drdkje;
	}

	public void setDrdkje(double drdkje) {
		this.drdkje = drdkje;
	}
}
