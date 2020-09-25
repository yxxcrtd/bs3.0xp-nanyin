package com.jixu.post.service.imp;

import com.orm.obj.Tran;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		PostServiceModel
 * @author  		Pan Wei 
 * @Date 			2017年1月12日 下午5:26:50 	
 * @Description		[]  
 */

public class PostServiceModel {
	private Tran tran;
	private TranSum tranSum;
	
	public Tran getTran() {
		return tran;
	}
	public void setTran(Tran tran) {
		this.tran = tran;
	}
	public TranSum getTranSum() {
		return tranSum;
	}
	public void setTranSum(TranSum tranSum) {
		this.tranSum = tranSum;
	}
}
