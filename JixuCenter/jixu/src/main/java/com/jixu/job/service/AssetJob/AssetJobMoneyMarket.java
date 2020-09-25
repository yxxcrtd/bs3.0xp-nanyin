package com.jixu.job.service.AssetJob;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.CarryOverType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.dao.intf.TranAccruedRecDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.post.service.PostIssueService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.Tran;
import com.orm.obj.TranAccruedRec;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobCommonStock
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:35:13 	
 * @Description		[]  
 */

@Service("assetJobMoneyMarket")
public class AssetJobMoneyMarket extends AssetJobBasic{
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="postIssues")
	private PostIssueService postIssue;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeCal;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Resource(name = "assetJobTermRepo")
	private AssetJobService jobservice;
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private SecPriceDao secPriceDao;
	
	@Autowired
	private TranAccruedRecDao tranAccruedRecDao;
	
	
	@Override
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException {
		GLCOOABase generalCooa=null;
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(stcostWrap.getCusip());
		int coagroup=sec.getCoaaGroup();
		
		//如果是多lot,分两张不同的凭证出。因为是两个证券账号买入
		TranClo priorlotCost = stcostWrap.getPriordayCost().get(0);
		TranClo asofdayCost = stcostWrap.getAsofdayCost().get(0);
		
		double lj_income_local_lastday=priorlotCost.getAccrued();
		//comDAO.getLastTranCloAccrued(portfolio.getId(), sec.getId(), tranclo.getLotnum(), ledgerDate);	//累计到上一个做账日的收益(原币)
		double lj_income_func_lastday=RoundUtil.roundMoney2Dec(priorlotCost.getAccrued()*priorlotCost.getPrinFxRate());
		//RoundUtil.roundMoney2Dec(lj_income_local_lastday*stcostWrap.getPriordayRate());	//累计到上一个做账日的收益(本币)
		double lj_income_local_today=lj_income_local_lastday;	//昨天的份额累计到今天的收益(原币)
		double lj_income_func_today=RoundUtil.roundMoney2Dec(lj_income_local_today*asofdayCost.getPrinFxRate()); //累计到今天的收益(本币)
		double gain_loss_local=lj_income_local_today-lj_income_local_lastday;
		double gain_loss_func=RoundUtil.roundMoney2Dec(lj_income_func_today-lj_income_func_lastday);		//每日估值万份收益金额
	
		if(gain_loss_local != 0 || gain_loss_func != 0){
			generalCooa=new GLCOOABase();
			if(gain_loss_func>0){
				jobCom.saveGL(generalCooa, DBCR.DEBIT,gain_loss_func,gain_loss_local, 
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(), coagroup, GLACCT.ACCRUED_INC));
				
				jobCom.saveGL(generalCooa, DBCR.CREDIT,gain_loss_func,gain_loss_local, 
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(), coagroup, GLACCT.FX_GAIN_INCOME_UNREAL));
			}else{
				jobCom.saveGL(generalCooa, DBCR.DEBIT,gain_loss_func,gain_loss_local, 
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(), coagroup, GLACCT.FX_LOSS_INCOME_UNREAL));
				
				jobCom.saveGL(generalCooa, DBCR.CREDIT,gain_loss_func,gain_loss_local, 
						jobCom.loadGLAcctnum(portfolio.getCoaahdr(), coagroup, GLACCT.ACCRUED_INC));
			}
			jobCom.genVoucherDetails(generalCooa, stcostWrap.getTranClo(), asofdayCost.getPrice(), asofdayCost.getPrinFxRate());
		}
		
		return generalCooa;
		
	}

	private double calIncomeLocal(String portnum, String cusip, double amount, Date lastCloseDate, Date closeDate){
		double yield=secPriceDao.loadCusipYield(portnum, cusip, closeDate);
		int days=DateUtil.getDiffDays(lastCloseDate, closeDate);
		
		double gain_local_today=days*RoundUtil.roundMoney2Dec(yield*amount/10000);
		return gain_local_today;
	}
	
	/*
	1)	当前做账日的累计万份收益估值总额=累计原币总额*当前做账日汇率
	2)	每日估值万份收益金额=当前做账日累计万份收益总额-上一做账日累计万份收益总额-当日计提万份收益金额
	3)	每日估值万份收益金额为正数时，凭证如下：
		DR：应收万份收益                     （总账栏编号33）
		CR：财务费用-汇兑损益            （总账栏编号116）
	4)	每日估值万份收益金额为负数时，凭证如下：
		DR：财务费用-汇兑损益            （总账栏编号117）
		CR：应收万份收益                       （总账栏编号33）
		*/
	@Override
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException {
		return jobservice.exeIncomeJob(sourceData);
	}
	
	@Override
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList) {
		double income_local=calIncomeLocal(portfolio.getId(), clo.getCusip(), clo.getAmount(), portfolio.getLastGLClose(), clo.getCloseDate());
		clo.setAccrued(income_local);
		for(Tran tran:periodTrans){
			if(tran.getTrantype()==TransTypes.IncomeUpdate){
				clo.setAccrued(clo.getAccrued()+tran.getIntps());//收益调整替换原先的accrued
				
			}
		}
		clo.setTodayInterest(clo.getAccrued());
		clo.setgMkValue(clo.getAmount());
		return clo;
	}
	
	@Override
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException {
		ResultContainer resultcontainer=new ResultContainer();
		//查询前一天的clo表，有哪些需要结转的
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		Date lastGlCloseDate=portfolio.getLastGLClose();
		//查询前一个关帐日的货币基金的总收入
		List<TranClo> cloList=tranCloDao.loadTranCloWithAssetType(portnum, lastGlCloseDate, AssetTypes.MONEY_MARKET_FUND);
		
		for(TranClo clo:cloList){
			if(tranCloDao.checkAccrued(clo)){
				//排除已经接转利息的记录
				continue;
			}
			
			IssueInfo cusip=issueInfoDao.loadIssueByCusip(clo.getCusip());
			CashFlowCfg cashcfg=cashFlowCfgDao.loadCashFlowCfg(cusip.getCashFlowCfgID());
			//如果当天是非工作日,不结转
			
			//1.通过货币基金配置判断是否结息
			boolean setFlag=incomeCal.checkGenSetInterestVoucher(cashcfg, lastGlCloseDate, ledgerDate);
			
			//2.如果当天全部卖空,提前结息
			if(!setFlag){
				//------------------------------------------------------
				//动态获取position clo配合今天的tran
				TranClo cloToday=secPositionCenter.getSecPosition(portnum, cusip.getId(), clo.getLotnum(), ledgerDate, false);
				double amountPosition=0;
				if(cloToday!=null){
					amountPosition=cloToday.getAmount();
				}
				if(amountPosition==0){
					setFlag=true;
				}
			}
			
			
			if(setFlag){
				//获取那天现金结转
				Date cashSetDate=incomeCal.getCpnSetDate(cashcfg, ledgerDate);
				//post的时候根据现金账户区分的lot
				String secacctnum=clo.getSecacct();
				BkCashAcct cashacct=bkCashAcctDao.loadCashAcctBySecAcct(secacctnum);
				if(cashacct==null){
					resultcontainer.setCorrectFlag(false);
					resultcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.default_cash_sec_notexist) + ": SecAcct: " + clo.getSecacct());
					return resultcontainer;
				}
				IssueInfo sec=issueInfoDao.loadIssueByCusip(clo.getCusip());
				double fxRate=commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), cusip.getIncomeCur(), ledgerDate);
				
				String desc=ReturnResult.getDicHM().get(ReturnResult.mmIncomeSet_chinese) + "-"+sec.getSecurityid();
				String dbcr=String.valueOf(IncomeExpenseType.income);
				double funccash=RoundUtil.roundMoney2Dec(clo.getAccrued()*fxRate);	//to-do pending 汇率取什么？
				String glacct=jobCom.loadGLAcctnum(portfolio.getCoaahdr(), cusip.getCoaaGroup(), GLACCT.ACCRUED_INC);
				HashMap<String, Object> cashOffMsg=msgPackage.cashOff("ADD","",String.valueOf(portnum), ledgerDate, cashSetDate, String.valueOf(cashacct.getId()), String.valueOf(funccash), String.valueOf(clo.getAccrued()), dbcr, glacct, desc, sec.getSecurityid()); 
			
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setUserID(userID);
				msgContainer.setFieldsHM(cashOffMsg);
				msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
				ResultContainer container=tradeService.operateTran(msgContainer);
				commfuncService.updateJobsHis(userID, portfolio, ledgerDate, JobsType.autoMaturity, resultcontainer);
				
				if(!container.isCorrectFlag()){
					return container;
				}
				
				String tanum=container.getEntityID();
				
				/**
				 * 用于撤帐的时候能记录哪个货币基金clo生成的交易
				 */
				TranAccruedRec accrec=new TranAccruedRec();
				accrec.setCusip(clo.getCusip());
				accrec.setLotnum(clo.getLotnum());
				accrec.setLastTranNum(clo.getLstTrannum());
				accrec.setAccruedDate(ledgerDate);
				accrec.setBasedDate(clo.getCloseDate());
				accrec.setTranTanum(tanum);
				accrec.setPortnum(clo.getPortfolio());
				tranAccruedRecDao.saveEntity(accrec);
				
				if(cashcfg.getPaymentKind()==CarryOverType.SHARES){
					SecAcct secacct=secAcctDao.loadSecAcct(secacctnum);
					//开始做货币基金的现金转份额
					String brokerID=secacct.getBrokerCode();
					int bankAcct=cashacct.getId();
					int managerID=clo.getManagerID();
					double units=clo.getAccrued();
					double principal=units;
					double cash=units;
					String setCurCode=cusip.getIncomeCur();
					
					HashMap<String, Object> fundTranMsg=msgPackage.fundTran("ADD","",TransTypes.MMFUND_CONVERT,cusip.getSecurityid(),ledgerDate,cashSetDate,
							0, brokerID, secacctnum, bankAcct, managerID, portnum,units,principal,cash,"",setCurCode, fxRate, setCurCode, 
							fxRate,cash);
					MsgContainer msgcontainer=new MsgContainer();
					msgcontainer.setUserID(userID);
					msgcontainer.setFieldsHM(fundTranMsg);
					msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
					ResultContainer fundcontainer=tradeService.operateTran(msgcontainer);
					
					resultcontainer.setObjID(fundcontainer.getObjID());
					tanum=fundcontainer.getEntityID();
					if(fundcontainer.isCorrectFlag()){
						//postIssue.postIssue(fundcontainer.getEntityID(), resultcontainer);
						
						if(fundcontainer.isCorrectFlag()){
							String trantanum=fundcontainer.getEntityID();
							TranAccruedRec accrec2=new TranAccruedRec();
							accrec2.setCusip(clo.getCusip());
							accrec2.setLotnum(clo.getLotnum());
							accrec2.setLastTranNum(clo.getLstTrannum());
							accrec2.setAccruedDate(ledgerDate);
							accrec2.setBasedDate(clo.getCloseDate());
							accrec2.setTranTanum(trantanum);
							accrec2.setPortnum(clo.getPortfolio());
							tranAccruedRecDao.saveEntity(accrec2);
						}
					}else{
						resultcontainer.setCorrectFlag(fundcontainer.isCorrectFlag());
						resultcontainer.setErrorMsg(fundcontainer.getErrorMsg());
					}
				}
				
				return container;
			}
		}
		return null;
	}
}
