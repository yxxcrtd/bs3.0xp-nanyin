package com.jixu.journal.obj;

public class CashCooa extends GLCOOABase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8344998135947794091L;
	
	private int cashtype; //股票购买，售出，回购，换汇。。。

	public int getCashtype() {
		return cashtype;
	}

	public void setCashtype(int cashtype) {
		this.cashtype = cashtype;
	}
}
