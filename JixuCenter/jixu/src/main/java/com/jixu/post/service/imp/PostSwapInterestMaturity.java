package com.jixu.post.service.imp;

import org.springframework.stereotype.Service;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service
public class PostSwapInterestMaturity extends PostServiceImpl{
	
	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//利率互换开仓生成两笔头寸自动到期一笔tranbasic生成一笔tran和头寸    手动到期是一笔tranbasic生成两笔tran和头寸
		double amout=tranAdvice.getAmount();
		double principal=tranAdvice.getPrincipal();
		double cash=tranAdvice.getCash();
		double intatmat=tranAdvice.getIntatmat();
		double principal2=tranAdvice.getPrincipal2();
		double intatmat2=tranAdvice.getIntatmat2();
		double cash2=tranAdvice.getCash2();
		
		if(tranAdvice.getPrincipal2()!=0){
			tranAdvice.setAmount(principal2);
			tranAdvice.setPrincipal(principal2);
			tranAdvice.setCash(cash2);
			tranAdvice.setIntatmat(intatmat2);
			postTranSaleUtil(tranAdvice, LongShortType.ShortTerm, container);
		}
		
		tranAdvice.setAmount(amout);
		tranAdvice.setPrincipal(principal);
		tranAdvice.setCash(cash);
		tranAdvice.setIntatmat(intatmat);
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
		//利率互换开仓生成两笔头寸自动到期一笔tranbasic生成一笔tran和头寸    手动到期是一笔tranbasic生成两笔tran和头寸
		double principal=tranAdvice.getPrincipal();
		double cash=tranAdvice.getCash();
		String currency=tranAdvice.getFxcurrency();
		double intatmat=tranAdvice.getIntatmat();
		double fxrate=tranAdvice.getFxrate();
		double principal2=tranAdvice.getPrincipal2();
		double cash2=tranAdvice.getCash2();
		String currency2=tranAdvice.getFxcurrency2();
		double intatmat2=tranAdvice.getIntatmat2();
		double fxrate2=tranAdvice.getFxrate2();
		
		if(tranAdvice.getPrincipal2()!=0){
			tranAdvice.setAmount(principal2);
			tranAdvice.setPrincipal(principal2);
			tranAdvice.setFxcurrency(currency2);
			tranAdvice.setFxrate(fxrate2);
			tranAdvice.setCash(cash2);
			tranAdvice.setIntatmat(intatmat2);
			reverseTranUtil(tranAdvice, container);
		}
		
		tranAdvice.setAmount(principal);
		tranAdvice.setPrincipal(principal);
		tranAdvice.setFxcurrency(currency);
		tranAdvice.setFxrate(fxrate);
		tranAdvice.setCash(cash);
		tranAdvice.setIntatmat(intatmat);
		return reverseTranUtil(tranAdvice, container);
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
