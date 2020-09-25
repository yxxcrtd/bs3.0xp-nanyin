package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.LotType;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostFutureLongBuy")
public class PostFutureLongBuy extends PostServiceImpl {
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//判断是单lot还是多lot
		if(tranAdvice.getHiloCode()==LotType.singleLot)
			return postTranUtil(tranAdvice, LongShortType.longTerm, true, container);
		else
			return postTranUtil(tranAdvice, LongShortType.longTerm, false, container);
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
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
