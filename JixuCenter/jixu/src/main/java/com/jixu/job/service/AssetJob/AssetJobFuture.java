package com.jixu.job.service.AssetJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.GeneralCooa;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.journal.service.GeneralJNLService;
import com.jixu.mvc.control.service.TradeService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/**
 * @ClassName AssetJobFuture
 * @author Pan Wei
 * @Date 2017年5月16日 上午9:35:13
 * @Description [期货估增，无负债结转等功能]
 */

@Service("AssetJobFuture")
public class AssetJobFuture extends AssetJobBasic {

	@Resource(name = "genjnlService")
	private GeneralJNLService generalservice;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="tradeService")
	private TradeService tradeService;

	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio,
			STCostWrapObj costWrap, Date ledgerDate) throws JNLException {
		String chartnum = portfolio.getCoaahdr();
		GeneralCooa generalCooa = new GeneralCooa();
		TranClo priorlotCost = costWrap.getPriordayCost().get(0);
		TranClo asofdayCost = costWrap.getAsofdayCost().get(0);
		double[] gainloss=genGainLoss(priorlotCost, asofdayCost);
		double l_diff=gainloss[0];
		double f_diff=gainloss[1];
		
		/*double[] closeSalegainloss=genCloseSaleGainLoss(costWrap);
		l_diff=l_diff-closeSalegainloss[0];
		f_diff=f_diff-closeSalegainloss[1];*/
		
		IssueInfo sec = issueInfoDao.loadIssueByCusip(costWrap.getCusip());
		int groupnum=sec.getCoaaGroup();
		
		int longshort=costWrap.getTranClo().getLsindicator();
		int gain=GLACCT.VALUATION_GAIN;			//估增盈利
		int loss=GLACCT.VALUATION_LOSS;			//估增亏损
		int offset=GLACCT.VARIATION_MARGIN_RP;	//追加保证金
		
		if(longshort==LongShortType.ShortTerm){
			gain=GLACCT.VALUATION_GAIN_Short;	//估增盈利(卖空)
			loss=GLACCT.VALUATION_LOSS_Short;	//估增亏损(卖空)
			offset=GLACCT.VARIATION_MARGIN_RP_Short;	//追加保证金(卖空)
		}
		
		if (f_diff > 0) { // 赚了。
			jobCom.saveGL(generalCooa, DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(f_diff), RoundUtil
							.roundMoney2Dec(Math.abs(l_diff)), jobCom
							.loadGLAcctnum(chartnum, groupnum, offset));

			jobCom.saveGL(generalCooa, DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(f_diff), RoundUtil
							.roundMoney2Dec(Math.abs(l_diff)), jobCom
							.loadGLAcctnum(chartnum, groupnum, gain));

		} else { // 亏了或平了。
			jobCom.saveGL(generalCooa, DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
							.roundMoney2Dec(Math.abs(l_diff)), jobCom
							.loadGLAcctnum(chartnum, groupnum, loss));

			jobCom.saveGL(generalCooa, DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(f_diff)), RoundUtil
							.roundMoney2Dec(Math.abs(l_diff)), jobCom
							.loadGLAcctnum(chartnum, groupnum, offset));
		}
		jobCom.genVoucherDetails(generalCooa, costWrap.getTranClo(),
				asofdayCost.getPrice(), asofdayCost.getPrinFxRate());
		return generalCooa;
	}

	// @Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData)
			throws JNLException {
		return null;
	}

	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec,
			TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		clo.setgMkValue(RoundUtil.roundMoney2Dec(clo.getgMkValue()*sec.getContractSize()));
		return clo;
	}
	
	private double[] genCloseSaleGainLoss(Date todayDate, STCostWrapObj costWrap){
		//获取当天的卖出交易
		TranClo tsum=costWrap.getTranClo();
		List<Tran> tranList=tranDao.loadTransToday(tsum.getPortfolio(), tsum.getCusip(), tsum.getLotnum(), todayDate);
		double l_salegain=0;
		double f_salegain=0;
		for(Tran tran:tranList){
			if(tran.getProceeds()>0){
				l_salegain=l_salegain+(tran.getProceeds()-tran.getgCost());
				f_salegain=f_salegain+(RoundUtil.roundMoney2Dec(tran.getProceeds()*tran.getPrinFxrate())-tran.getgCostFunc());
			}
		}
		
		if(costWrap.getTranClo().getLsindicator()==LongShortType.ShortTerm){
			l_salegain=l_salegain*-1;
			f_salegain=f_salegain*-1;
		}
		
		double[] gainloss={l_salegain,f_salegain};
		return gainloss;
	}

	private double[] genGainLoss(TranClo priorlotCost, TranClo asofdayCost) {
		// A1*P2*R2 - A1*P1*R1 (总的损益)= (A1*P2*R2 - A1*P1*R2) (价格引起)+ (A1*P1*R2 -
		// A1*P1*R1) (汇率变化引起)
		// 公式：估值=今天的市值-昨天的市值
		// (估值=价格损益+汇率损益)
		double l_diff = 0.0;
		double f_diff = 0.0; // 估值变量。l_diff为local。f_diff为functional.
		double l_priordayValuation = 0.0; // 前一天原币估增
		double l_asofdayValuation = 0.0; // 当天原币估增
		double f_priordayValuation = 0.0; // 前一天本币估增
		double f_asofdayValuation = 0.0; // 当天本币估增
		// double l_p_valuation = 0.0; // 因价格引起的原币(估值=价格损益+汇率损益)
		// double f_p_valuation = 0.0; // 因价格引起的本币(估值=价格损益+汇率损益)
		// VALUATION_LOSS, CARRY_VAL_ADJ, VALUATION_GAIN,
		// 外汇引起的(FX_VALUATION_GAIN,FX_VALUATION_LOSS),FX_RESERVE_CAP

		l_priordayValuation = RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue() - priorlotCost.getgCost());
		f_priordayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(priorlotCost.getgMkValue() * priorlotCost.getPrinFxRate())
				- priorlotCost.getgCostFunc());

		l_asofdayValuation = RoundUtil.roundMoney2Dec(asofdayCost.getgMkValue() - asofdayCost.getgCost());
		f_asofdayValuation = RoundUtil.roundMoney2Dec(
				RoundUtil.roundMoney2Dec(asofdayCost.getgMkValue() * asofdayCost.getPrinFxRate()))
				- asofdayCost.getgCostFunc();

		l_diff = l_asofdayValuation - l_priordayValuation;
		f_diff = f_asofdayValuation - f_priordayValuation;
		
		if(asofdayCost.getLsindicator()==LongShortType.ShortTerm){
			l_diff=l_diff*-1;
			f_diff=f_diff*-1;
		}
		double[] gainloss={l_diff, f_diff};
		return gainloss;
	}

	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		PortfolioHdr portfolio = commfuncService.loadPortfolio(portnum);
		String chartnum=portfolio.getCoaahdr();
		Date endDate = ledgerDate;
		
		// 和估增日记账相同的调用取数逻辑
		// TODO pending 最后卖出的估增反冲,会不会存在问题？
		List<STCostWrapObj> costWrapList = generalservice.loadSecGenerelJNLRecord(portfolio, endDate, AssetTypes.FUTURES);
		
		//同一证券存入到一个list中   目的是为了接下来将同一证券的当日无负债结算合并  平仓损益合并
		HashMap<String, List<STCostWrapObj>> costWrapMap = new HashMap<String, List<STCostWrapObj>>();
		String cusip = "";
		for (STCostWrapObj stCostWrapObj : costWrapList) {
			cusip = stCostWrapObj.getTranClo().getCusip();
			if (costWrapMap.containsKey(cusip)) {
				costWrapMap.get(cusip).add(stCostWrapObj);
			} else {
				List<STCostWrapObj> tmpList = new ArrayList<STCostWrapObj>();
				tmpList.add(stCostWrapObj);
				costWrapMap.put(cusip, tmpList);
			}
		}

		for (Map.Entry<String, List<STCostWrapObj>> entry : costWrapMap.entrySet()) {
			List<STCostWrapObj> costList = entry.getValue();
			double l_diff = 0.0;
			double f_diff = 0.0;
			double l_salediff = 0.0;
			double f_salediff = 0.0;
			for (STCostWrapObj stCostWrapObj : costList) {
				// 当日无负债结算
				TranClo priorlotCost = stCostWrapObj.getPriordayCost().get(0);
				TranClo asofdayCost = stCostWrapObj.getAsofdayCost().get(0);
				double[] gainloss = genGainLoss(priorlotCost, asofdayCost);
				l_diff = l_diff + gainloss[0];
				f_diff = f_diff + gainloss[1];

				// 确认平仓损益(当日卖出盈亏的保证金结转)
				double[] closesalegainloss = genCloseSaleGainLoss(ledgerDate, stCostWrapObj);
				l_salediff = l_salediff + closesalegainloss[0];
				f_salediff = f_salediff + closesalegainloss[1];
			}
			
			if(costList.size()>0){
				STCostWrapObj costWrap=costList.get(0);
				TranClo clo=costWrap.getTranClo();
				//反查交易  获取交易是的冲销现金账户
				TranBasic tb=tranBasicDao.getMapedContractTranBasic(portnum, clo.getCusip(), clo.getLotnum());
				BkCashAcct cashacct=null;
				if(tb!=null&&tb.getBkCashacctNo()!=null){
					cashacct=bkCashAcctDao.loadBankCash(tb.getBkCashacctNo().getId());
				}
				if(cashacct==null){
					ResultContainer resultcontainer=new ResultContainer();
					resultcontainer.setCorrectFlag(false);
					resultcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_cash_sec_notexist));
					return resultcontainer;
				}
				IssueInfo sec=issueInfoDao.loadIssueByCusip(costWrap.getCusip());
				int groupnum=sec.getCoaaGroup();
				String desc="期货-当日无负债结算-"+sec.getSecurityid()+"-"+LongShortType.getOptions().get(costWrap.getTranClo().getLsindicator());
				
				String dbcr=DBCR.CREDIT;
				String glacct="";
				if(l_diff>0){
					dbcr=String.valueOf(IncomeExpenseType.income);
					//应收清算款
					glacct=jobCom.loadGLAcctnum(chartnum, groupnum,GLACCT.RECEIV_SECUR_SOLD);
				}else if(l_diff<0){
					dbcr=String.valueOf(IncomeExpenseType.expense);
					//应付清算款
					glacct=jobCom.loadGLAcctnum(chartnum, groupnum,GLACCT.PAY_SECUR_PURCH);
				}else{
					continue;
				}
				
				l_diff=Math.abs(l_diff);
				f_diff=Math.abs(f_diff);
				HashMap<String, Object> msgHM=msgPackage.cashOff("ADD","",String.valueOf(portnum), endDate, endDate, String.valueOf(cashacct.getId()), String.valueOf(f_diff), String.valueOf(l_diff), dbcr, glacct, desc, sec.getSecurityid()); 
				
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setUserID(userID);
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				ResultContainer container=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
				
				if(!container.isCorrectFlag()){
					return container;
				}
				
				
				
				//确认平仓损益(当日卖出盈亏的保证金结转)
				if(l_salediff>0){
					dbcr=String.valueOf(IncomeExpenseType.income);
					glacct=jobCom.loadGLAcctnum(chartnum, groupnum,GLACCT.CAPITAL_GAIN);
				}else if(l_salediff<0){
					dbcr=String.valueOf(IncomeExpenseType.expense);
					glacct=jobCom.loadGLAcctnum(chartnum, groupnum,GLACCT.PAY_SECUR_PURCH);
				}else{
					continue;
				}
				
				l_salediff=Math.abs(l_salediff);
				f_salediff=Math.abs(f_salediff);
				msgHM=msgPackage.cashOff("ADD","",String.valueOf(portnum), endDate, endDate, String.valueOf(cashacct.getId()), String.valueOf(f_salediff), String.valueOf(l_salediff), dbcr, glacct, desc, sec.getSecurityid()); 
				
				msgContainer.setUserID(userID);
				msgContainer.setFieldsHM(msgHM);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				container=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, container);
			}
				
		}
		return null;
	}
}
