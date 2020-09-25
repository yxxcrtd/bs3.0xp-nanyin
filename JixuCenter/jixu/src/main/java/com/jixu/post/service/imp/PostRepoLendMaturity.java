package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;

import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("repoLendMaturityPost")
public class PostRepoLendMaturity extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//在消息交易(AssembleTARepoLendMaturity)时，tranbasic原始记录设置了setHiloCode单lot，指定lot setSpecificLotID
		return postTranSaleUtil(tranAdvice, LongShortType.longTerm, container);
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum){
		return unpostCaltsumSaleUtil(tran, tSum);
	}
	
	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return caltSumSaleUtil(tran, tSum);
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
