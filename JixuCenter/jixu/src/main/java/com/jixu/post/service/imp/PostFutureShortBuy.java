package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostFutureShortBuy")
public class PostFutureShortBuy extends PostFutureLongBuy {
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		return postTranUtil(tranAdvice, LongShortType.ShortTerm, true, container);
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		return unpostCaltsumUtil(tran, tSum);
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum){
		return caltSumUtil(tran, tSum);
	}

	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}
}
