package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.mvc.control.service.TradeService;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobCommonStock
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description []
 */

@Service("AssetJobTimeDeposit")
public class AssetJobTimeDeposit extends AssetJobBasic {

	@Resource(name = "assetJobTermRepo")
	private AssetJobService jobservice;

	@Resource(name = "msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio,
			STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException {
		return jobservice.exeGeneralJob(portfolio, stcostWrap, ledgerDate);
	}

	// @Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData)
			throws JNLException {
		return jobservice.exeIncomeJob(sourceData);
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		clo.setgMkValue(clo.getAmount());
		return setCloInterest(portfolio, sec, clo, lastClo, periodTrans, settleTranList);
	}
	
	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		// 查询前一天的clo表，有哪些需要结转的
		ResultContainer resultcontainer=new ResultContainer();
				
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		Date tradeDate=ledgerDate;
		List<TranClo> cloList=tranCloDao.loadTranCloWithAssetType(portnum, portfolio.getLastGLClose(), AssetTypes.TimeDeposit);

		for (TranClo clo : cloList) {
			String cusip=clo.getCusip();
			IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
			CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
				
			if(ledgerDate.before(clo.getAltDate())){
				if(!cfc.isAutoInterestFlag())
					continue;
				//1. 判断是否结息
				boolean setFlag=incomeCal.checkGenSetInterestVoucher(cfc, portfolio.getLastGLClose(), ledgerDate);
				
				if(setFlag){
					//获取那天现金结转
					Date cashSetDate=incomeCal.getCpnSetDate(cfc, ledgerDate);
					//post的时候根据现金账户区分的lot
					TranBasic tranbasic=tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
					double fxRate=commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), sec.getIncomeCur(), ledgerDate);
						
					String desc=ReturnResult.getDicHM().get(ReturnResult.incomeSet_chinese) + "-" + AssetTypes.getPortAsset(false).get(sec.getIssueType()) + "-"+sec.getSecurityid();
					String dbcr=String.valueOf(IncomeExpenseType.income);
					double funccash=RoundUtil.roundMoney2Dec(clo.getAccrued()*fxRate);
					String glacct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), sec.getCoaaGroup(), GLACCT.ACCRUED_INC);
					HashMap<String, Object> msgHM=msgPackage.cashOff("ADD","",String.valueOf(portnum), ledgerDate, 
							cashSetDate, String.valueOf(tranbasic.getBkCashacctNo().getId()), String.valueOf(funccash), 
							String.valueOf(clo.getAccrued()), dbcr, glacct, desc, sec.getSecurityid()); 
				
					MsgContainer msgContainer=new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
					ResultContainer container=tradeService.operateTran(msgContainer);
					commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
					
					if(!container.isCorrectFlag()){
						return container;
					}
				}
			}else{
				if(!cfc.isAutoSetFlag() && !cfc.isAutoInterestFlag())
					continue;
				
				double amount=0;
				if(cfc.isAutoSetFlag())
					amount=clo.getAmount();
				
				Date setDate=incomeCal.getCpnMaturitySetDate(cfc, tradeDate);
				double accrued=0;
				if(cfc.isAutoInterestFlag()){
					accrued=clo.getAccrued();
					if(setDate.after(ledgerDate) && cfc.getEndNDaysBase()==TradeSettleLedger.SettleDate){
						TranClo endClo=new TranClo();
						BeanUtils.copyProperties(clo, endClo);
						//最后一天计算利息是结算日的前一个自然日
						endClo.setCloseDate(DateUtil.getLastDay(setDate));
						//把结算日前一天的clo传入计算当天应记利息。因为投资组合的关帐日在上一天，所以会计算出今天到结算日之间总的利息
						double todayInterest=incomeCal.getInterestForAll(endClo);
						accrued=RoundUtil.roundMoney2Dec(accrued+todayInterest);
					}
				}
						
				if(amount==0 && accrued==0)
					continue;
				// 查找相同cusip,lot的交易 trannum=1的那笔记录
				TranBasic tb=tranBasicDao.loadTranBasicLot1(portnum, clo.getCusip(), clo.getLotnum());
				String secID = sec.getSecurityid();
				String secacct = clo.getSecacct();
				int bankAcct = tb.getBkCashacctNo().getId();
				int lotnum=clo.getLotnum();
				double fxRate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tb.getBkCashacctNo().getFxcurrency(), tradeDate);
				double cash = RoundUtil.roundMoney2Dec(amount+ accrued);
				String setCurCode = tb.getSettleCurrency();
				double setfxRate = commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tb.getSettleCurrency(),tradeDate);
				double setCash = RoundUtil.roundMoney2Dec(cash * setfxRate);
				String tradeCur=tb.getFxcurrency();
				
				HashMap<String, Object> msgHM=msgPackage.timeDepositMaturity("TranTimDepositeSale","ADD","",secID, portnum, tb.getManager(), 
						tradeDate,setDate,secacct, bankAcct,amount,accrued, tradeCur,fxRate,cash,setCurCode,setfxRate,setCash, lotnum, null);
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setUserID(userID);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				ResultContainer container=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
				
				if(!container.isCorrectFlag()){
					return container;
				}
			}
		}
			
		return resultcontainer;
	}
}
