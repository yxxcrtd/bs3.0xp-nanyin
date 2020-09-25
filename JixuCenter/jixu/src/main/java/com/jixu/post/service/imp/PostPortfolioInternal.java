package com.jixu.post.service.imp;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.post.service.PostService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

@Service("PostPortfolioInternal")
public class PostPortfolioInternal extends PostServiceImpl{
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;

	@Override
	public PostServiceModel postTran(TranBasic tranAdvice, ResultContainer container) {
		//通过资产类型反查交易类型
		IssueInfo ii=tranAdvice.getCusip();
		List<Integer> tranType=TransTypes.getTranTypes(ii.getIssueType());
		
		//long  买    对应界面的到投资组合   portfolio2
		TranBasic longTranbBasic=new TranBasic();
		longTranbBasic.setId(tranAdvice.getId());
		longTranbBasic.setCusip(tranAdvice.getCusip());
		longTranbBasic.setTradeMarket(tranAdvice.getTradeMarket());
		longTranbBasic.setManager(tranAdvice.getManager());
		longTranbBasic.setTrantype(tranType.get(0));
		longTranbBasic.setPrice(tranAdvice.getPrice());
		longTranbBasic.setPortfolio(tranAdvice.getPortfolio2());
		longTranbBasic.setTradedate(tranAdvice.getTradedate());
		longTranbBasic.setSettledate(tranAdvice.getSettledate());
		longTranbBasic.setLedgerdate(tranAdvice.getLedgerdate());
		longTranbBasic.setBkCashacctNo(tranAdvice.getBkCashacctNo2());
		longTranbBasic.setSecacct(tranAdvice.getSecacct2());
		longTranbBasic.setFxcurrency(tranAdvice.getFxcurrency2());
		longTranbBasic.setAmount(tranAdvice.getAmount2());
		longTranbBasic.setCash(tranAdvice.getCash2());
		longTranbBasic.setPrincipal(tranAdvice.getPrincipal2());
		longTranbBasic.setIntatmat(tranAdvice.getIntatmat());
		longTranbBasic.setBroker(tranAdvice.getBroker());
		longTranbBasic.setPostStatus(PostStatusType.unpost);
		longTranbBasic.setSettleStatus(PostStatusType.unpost);
		longTranbBasic.setContract(tranAdvice.getContract());
		longTranbBasic.setValuationType(tranAdvice.getValuationType());
		longTranbBasic.setAltDate(tranAdvice.getAltDate());
		longTranbBasic.setAltRate(tranAdvice.getAltRate());
		longTranbBasic.setFxrate(tranAdvice.getFxrate2());
		//调用该资产类别下的买进行post
		SecurityBasic secBasic1=secCoreFactory.createSecTranTypeBean(longTranbBasic.getTrantype());
		PostService postService1 = secBasic1.createPostService();
		postService1.post(longTranbBasic,container);
		
		
		//short 卖    对应界面的从投资组合   portfolio
		TranBasic shortTranbBasic=new TranBasic();
		shortTranbBasic.setId(tranAdvice.getId());
		shortTranbBasic.setCusip(tranAdvice.getCusip());
		shortTranbBasic.setTradeMarket(tranAdvice.getTradeMarket());
		shortTranbBasic.setManager(tranAdvice.getManager());
		shortTranbBasic.setTrantype(tranType.get(1));
		shortTranbBasic.setPrice(tranAdvice.getPrice());
		shortTranbBasic.setPortfolio(tranAdvice.getPortfolio());
		shortTranbBasic.setTradedate(tranAdvice.getTradedate());
		shortTranbBasic.setSettledate(tranAdvice.getSettledate());
		shortTranbBasic.setLedgerdate(tranAdvice.getLedgerdate());
		shortTranbBasic.setBkCashacctNo(tranAdvice.getBkCashacctNo());
		shortTranbBasic.setSecacct(tranAdvice.getSecacct());
		shortTranbBasic.setFxcurrency(tranAdvice.getFxcurrency());
		shortTranbBasic.setAmount(tranAdvice.getAmount());
		shortTranbBasic.setCash(tranAdvice.getCash());
		shortTranbBasic.setPrincipal(tranAdvice.getPrincipal());
		shortTranbBasic.setIntatmat(tranAdvice.getIntatmat());
		shortTranbBasic.setBroker(tranAdvice.getBroker());
		shortTranbBasic.setPostStatus(PostStatusType.unpost);
		shortTranbBasic.setSettleStatus(PostStatusType.unpost);
		shortTranbBasic.setContract(tranAdvice.getContract());
		shortTranbBasic.setValuationType(tranAdvice.getValuationType());
		shortTranbBasic.setAltDate(tranAdvice.getAltDate());
		shortTranbBasic.setAltRate(tranAdvice.getAltRate());
		shortTranbBasic.setFxrate(tranAdvice.getFxrate());
		shortTranbBasic.setHiloCode(tranAdvice.getHiloCode());
		shortTranbBasic.setSpecificLotID(tranAdvice.getSpecificLotID());
		//调用该资产类别下的卖进行post
		SecurityBasic secBasic2=secCoreFactory.createSecTranTypeBean(shortTranbBasic.getTrantype());
		PostService postService2 = secBasic2.createPostService();
		postService2.post(shortTranbBasic,container);
		return null;
	}
	
	
	@Override
	public PostServiceModel reverseTran(TranBasic tranAdvice, ResultContainer container) {
		String portfolio=tranAdvice.getPortfolio();
		String tanum = tranAdvice.getId();
		IssueInfo ii=tranAdvice.getCusip();
		List<Integer> tranType=TransTypes.getTranTypes(ii.getIssueType());
		List<Tran> transList = tranDao.loadTransByTanum(tanum);
		for (Tran tran : transList) {
			//处理买   作为对方   投资组合要用portfolio2
			if(tranType.get(0)==tran.getTrantype()){
				tranAdvice.setPortfolio(tranAdvice.getPortfolio2());
			}else{
				tranAdvice.setPortfolio(portfolio);
			}
			
			TranSum tsum=tranSumDao.loadTSumByLot(tranAdvice.getPortfolio(), tran.getCusip().getId(), tran.getLotnum());
			
			//调用该资产类别下的具体交易类型的类进行撤账
			SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tran.getTrantype());
			PostService postService = secBasic.createPostService();
			tsum=postService.unpostCaltsum(tran, tsum);
			if(tsum.getLstTrannum()==0){
				tranSumDao.deleteEntity(tsum);
				tranDao.deleteEntity(tran);
			}else{
				tranSumDao.updateEntity(tsum);
				tranDao.deleteEntity(tran);
			}
		}
		return null;
	}
	
	@Override
	public TranSum unpostCaltsum(Tran tran, TranSum tSum) {
		return null;
	}


	@Override
	public TranSum CaltSum(Tran tran, TranSum tSum) {
		return null;
	}
	
	@Override
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container) {
		// TODO Auto-generated method stub
		
	}
}
