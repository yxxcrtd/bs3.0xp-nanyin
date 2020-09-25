package com.jixu.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.common.func.SystemSelect;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.VoucherDetails;

public abstract class PrintJNL {
	
	@Autowired
	protected SystemSelect systemSelect;
	
	public abstract ResultContainer printJNL(List<GLCOOABase> pursaleJnlList);
	
	protected List<VoucherDetails> mergetSameAcctJNL(List<VoucherDetails> jnlobjList) {
		List<VoucherDetails> newJnlList = new ArrayList<VoucherDetails>();
		int index = 0;
		//double gap = 0.0;
		for (VoucherDetails obj : jnlobjList) {
			index = findExistingJNL(obj.getAccountNum(), newJnlList);
			if (index >=0 ) {
				newJnlList.get(index).setCr_func(RoundUtil.roundMoney2Dec(newJnlList.get(index).getCr_func() + obj.getCr_func()));
				newJnlList.get(index).setCr_local(RoundUtil.roundMoney2Dec(newJnlList.get(index).getCr_local() + obj.getCr_local()));
				newJnlList.get(index).setDr_func(RoundUtil.roundMoney2Dec(newJnlList.get(index).getDr_func() + obj.getDr_func()));
				newJnlList.get(index).setDr_local(RoundUtil.roundMoney2Dec(newJnlList.get(index).getDr_local() + obj.getDr_local()));
			} else {
				newJnlList.add(obj);
			}
		}
		
		VoucherDetails obj = null;
		for (int i = 0; i < newJnlList.size(); i++) {
			double gap=0;
			obj = newJnlList.get(i);
			gap = RoundUtil.roundMoney2Dec(obj.getDr_func() - obj.getCr_func());
			if (gap > 0) {
				obj.setDr_func(RoundUtil.roundMoney2Dec(gap));
				obj.setCr_func(0);
				
				gap = RoundUtil.roundMoney2Dec(obj.getDr_local() - obj.getCr_local());
				obj.setDr_local(RoundUtil.roundMoney2Dec(Math.abs(gap)));
				obj.setCr_local(0);
			} else if (gap < 0) {
				obj.setCr_func(RoundUtil.roundMoney2Dec(Math.abs(gap)));
				obj.setDr_func(0);
				
				gap = RoundUtil.roundMoney2Dec(obj.getDr_local() - obj.getCr_local());
				obj.setCr_local(RoundUtil.roundMoney2Dec(Math.abs(gap)));
				obj.setDr_local(0);
			}else{
				newJnlList.remove(i);
				i--;
			}
		}
		
		return newJnlList;
	}
	
	protected List<VoucherDetails> removeZeroJNL(List<VoucherDetails> jnlobjList) {
		for(int i=0;i<jnlobjList.size();i++){
			VoucherDetails vd=jnlobjList.get(i);
			if (Math.abs(vd.getCr_local() + vd.getDr_local() + vd.getDr_func() + vd.getCr_func()) < 0.000001) {
				jnlobjList.remove(i);
				i=i-1;
			}
		}
		
		return jnlobjList;
	}
	
	private int findExistingJNL(String acctnum, List<VoucherDetails> jnlList) {
		int position = -1;
		for (int i = 0; i < jnlList.size(); i++) {
			if (jnlList.get(i).getAccountNum() != null && jnlList.get(i).getAccountNum().equals(acctnum)) {
				position = i;
				break;
			}
		}
		return position;
	}
	
	public boolean checkJournalRunable(String portNum, Date journalDate, int journalType){
		return false;
	}
}
