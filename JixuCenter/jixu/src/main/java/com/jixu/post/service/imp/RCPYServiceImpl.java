package com.jixu.post.service.imp;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.FeeTypesAmount;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkCashRcpyDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.job.service.ContractualSetService;
import com.jixu.post.service.PostToRCPYService;
import com.orm.obj.BkCashFlow;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		RcpyServiceImpl
 * @author  		Pan Wei 
 * @Date 			2016年10月20日 下午4:53:08 	
 * @Description		[]  
 */

@Service("rcpyService")
public abstract class RCPYServiceImpl implements PostToRCPYService{
	
	@Autowired
	protected FxCurrencyDao fxCurrencyDao;
	
	@Autowired
	protected TranBasicDao tranBasicDao;
	
	@Autowired
	protected IssueInfoDao issueInfoDao;
	
	@Autowired
	protected BkCashAcctDao bkCashAcctDao;

	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setService;
	
	@Autowired
	protected BkCashRcpyDao bkCashRcpyDao;
	
	@Autowired
	private TranBasicFeeDao tranBasicFeeDao;
	
	@Autowired
	protected CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	protected SecPositionCenter secPositionCenter;
	
	public void deleteRcpy(TranBasic tranAdvice, ResultContainer container) {
		List<BkCashRcpy> rcpyList=bkCashRcpyDao.loadRCPYByTanum(tranAdvice.getId());
		for(BkCashRcpy rcpy:rcpyList){
			Iterator<BkCashFlow> cashIter=rcpy.getBkCashFlows().iterator();
			while(cashIter.hasNext()){
				BkCashFlow cash=cashIter.next();
				setService.deleteCashFlow(cash, rcpy);
			}
			bkCashRcpyDao.deleteEntity(rcpy);
		}
		
	}
	
	/**
	 * 获取需要单独结转的费用
	 * @param tran
	 * @return
	 */
	/*public double rcpyFee(TranBasic tran){
		List<TranBasicFee> tranfeeList=tranBasicFeeDao.loadTranBasicFeeByTanum(tran.getId());
		double rcpyFee=0;
		for(TranBasicFee tranfee:tranfeeList){
			FeeTypesAmount feeModel=commfuncService.genFeeTypesAmount(tranfee);
			rcpyFee=rcpyFee+feeModel.getRcpyExpensedFee()+feeModel.getRcpyCapitalFee();
		}
		return rcpyFee;
	}*/
	
	protected abstract void postToRcpy(TranBasic tranAdvice, ResultContainer container);
	
	@Override
	public void postRcpy(TranBasic tranAdvice, ResultContainer container) {
		postToRcpy(tranAdvice, container);
		if(container.isCorrectFlag()){
			postRcpyFee(tranAdvice, container);
		}
	}
	
	/**
	 * 交易提交到应收应付后,提交费用的应收应付
	 * @param tranAdvice
	 * @param container
	 */
	private void postRcpyFee(TranBasic tranAdvice, ResultContainer container) {
		//List<TranFee> tranfeeList=tranFeeDao.loadTranFeeList(tranAdvice.getId());
		List<TranBasicFee> tranfeeList=tranBasicFeeDao.loadTranBasicFee(tranAdvice.getId());
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranAdvice.getPortfolio());
		for(TranBasicFee basicfee:tranfeeList){
			FeeTypesAmount feeModel=commfuncService.genFeeTypesAmount(basicfee);
			if(feeModel.getRcpyCapitalFee()!=0){
				double fxorigbal = feeModel.getRcpyCapitalFee();
				double fxremainbal = fxorigbal;
				double fxrate = basicfee.getFxrate();
				double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
				double remainbal = origbal;
				
				String dbcr="";
				if(feeModel.getRecOrPay()==DBCR.pay){
					dbcr=DBCR.DEBIT;
				}else{
					dbcr=DBCR.CREDIT;
				}
				
				BkCashRcpy rcpy=new BkCashRcpy();
				rcpy.setPortfolio(portfolio);
				rcpy.setZero(0);
				rcpy.setDbcr(dbcr);
				rcpy.setPosttype(PostType.Fees);
				rcpy.setTanum(tranAdvice);
				rcpy.setFeeID(basicfee);
				rcpy.setCusip(tranAdvice.getCusip());
				rcpy.setBkCashAcct(basicfee.getBkCashAcct());
				rcpy.setLedger(tranAdvice.getLedgerdate());
				rcpy.setOrigbal(origbal);
				rcpy.setRemainbal(remainbal);
				rcpy.setFxcurrency(basicfee.getCurrID());
				rcpy.setFxorigbal(fxorigbal);
				rcpy.setFxremainbal(fxremainbal);
				rcpy.setUorigbal(0);
				rcpy.setUremainbal(0);
				rcpy.setFxrate(basicfee.getFxrate());
				rcpy.setTrantype(tranAdvice.getTrantype());
				rcpy.setPaydate(basicfee.getSettleDate());
				rcpy.setExpdcashdate(basicfee.getSettleDate());
				rcpy.setBlockid(0);
				rcpy.setMiscorigbal(0);
				rcpy.setMiscremainbal(0);
				rcpy.setRcpyDesc(tranAdvice.getTranDesc());
				
				bkCashRcpyDao.saveEntity(rcpy);
			}
			
			if(feeModel.getRcpyExpensedFee()!=0){
				double fxorigbal = feeModel.getRcpyExpensedFee();
				double fxremainbal = fxorigbal;
				double fxrate = basicfee.getFxrate();
				double origbal = RoundUtil.roundMoney2Dec(fxorigbal * fxrate);
				double remainbal = origbal;
				
				String dbcr="";
				if(feeModel.getRecOrPay()==DBCR.pay){
					dbcr=DBCR.DEBIT;
				}else{
					dbcr=DBCR.CREDIT;
				}
				
				BkCashRcpy rcpy=new BkCashRcpy();
				
				rcpy.setPortfolio(portfolio);
				rcpy.setZero(0);
				rcpy.setDbcr(dbcr);
				rcpy.setPosttype(PostType.Fees);
				rcpy.setFeeID(basicfee);
				rcpy.setTanum(tranAdvice);
				rcpy.setCusip(tranAdvice.getCusip());
				rcpy.setBkCashAcct(basicfee.getBkCashAcct());
				rcpy.setLedger(tranAdvice.getLedgerdate());
				rcpy.setOrigbal(origbal);
				rcpy.setRemainbal(remainbal);
				rcpy.setFxcurrency(basicfee.getCurrID());
				rcpy.setFxorigbal(fxorigbal);
				rcpy.setFxremainbal(fxremainbal);
				rcpy.setUorigbal(0);
				rcpy.setUremainbal(0);
				rcpy.setFxrate(basicfee.getFxrate());
				rcpy.setTrantype(tranAdvice.getTrantype());
				rcpy.setPaydate(basicfee.getSettleDate());
				rcpy.setExpdcashdate(basicfee.getSettleDate());
				rcpy.setBlockid(0);
				rcpy.setMiscorigbal(0);
				rcpy.setMiscremainbal(0);
				rcpy.setRcpyDesc(tranAdvice.getTranDesc());
				
				bkCashRcpyDao.saveEntity(rcpy);
			}
		}
	}
}
