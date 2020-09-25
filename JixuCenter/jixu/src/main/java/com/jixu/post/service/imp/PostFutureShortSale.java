package com.jixu.post.service.imp;


import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostFutureShortSale")
public class PostFutureShortSale extends PostFutureLongSale {
	public int longshort=LongShortType.ShortTerm;

	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum){
		return caltSumSaleUtil(tran, tSum);
	}

	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		return unpostCaltsumSaleUtil(tran, tSum);
	}

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		return postTranSaleUtil(tranAdvice, longshort, container);
	}
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		getPresaledTSums(tranBasic, longshort, container);
	}
	
}
