package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("repoLendPost")
public class PostRepoLend extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		return postTranUtil(tranAdvice, LongShortType.longTerm, false, container);
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		tSum=caltSumUtil(tran, tSum);

		TranBasic tranBasic=tran.getTanum();
		tSum.setAltRate(tranBasic.getAltRate());
		tSum.setAltDate(tranBasic.getAltDate());

		return tSum;
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return unpostCaltsumUtil(tran, tSum);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
