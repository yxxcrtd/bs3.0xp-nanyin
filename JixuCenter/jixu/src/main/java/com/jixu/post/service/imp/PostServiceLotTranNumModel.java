package com.jixu.post.service.imp;

import com.orm.obj.TranSum;

public class PostServiceLotTranNumModel {
	private TranSum tsum;
	
	private int lotnum;
	
	private int trannum;

	public TranSum getTsum() {
		return tsum;
	}

	public void setTsum(TranSum tsum) {
		this.tsum = tsum;
	}

	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	public int getTrannum() {
		return trannum;
	}

	public void setTrannum(int trannum) {
		this.trannum = trannum;
	}
}
