package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobStockSale")
public class TranJobStockSale extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException {
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//获取总账组信息
		// Set RECEIV_SECUR_SOLD
		double secash=RoundUtil.roundMoney2Dec(tran.getProceeds()+tran.getIntps());
		jobCom.saveSecGL(pursaleCooa,
				tran.getCusip().getId(),
				DBCR.DEBIT, 
				RoundUtil.roundMoney2Dec(secash*tranBasic.getFxrate()), 
				secash, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD),
				tranBasic.getFxcurrency());
		
		// Set BOOK_VALUE 成本
		jobCom.saveSecGL(pursaleCooa,
				tran.getCusip().getId(),
				DBCR.CREDIT, 
				tran.getgCostFunc(), 
				tran.getgCost(),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
				tranBasic.getFxcurrency());
		
		double gainORloss = RoundUtil.roundMoney2Dec(tran.getProceeds()*tranBasic.getFxrate())-tran.getgCostFunc();

		if (gainORloss > 0) { // 赚了
			jobCom.saveSecGL(pursaleCooa,
					tran.getCusip().getId(),
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(gainORloss),
					RoundUtil.roundMoney2Dec(tran.getProceeds()-tran.getgCost()), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN),
					tranBasic.getFxcurrency());
		} else { // 亏了
			jobCom.saveSecGL(pursaleCooa,
					tran.getCusip().getId(),
					DBCR.DEBIT, 
					RoundUtil.roundMoney2Dec(Math.abs(gainORloss)),
					RoundUtil.roundMoney2Dec(Math.abs(tran.getProceeds()-tran.getgCost())), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS),
					tranBasic.getFxcurrency());
		}
		
		if(tran.getIntps()!=0){
			jobCom.saveSecGL(pursaleCooa,
					tran.getCusip().getId(),
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(tran.getIntps()*tran.getIncFxrate()), 
					tran.getIntps(),
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC),
					tranBasic.getFxcurrency());
		}
		
		if(tran.getCusip()!=null) {
			IssueInfo sec=commfuncService.loadSecByCusip(tran.getCusip().getId());
			if(sec.getIssueType().equals(AssetTypes.COMMON_STOCK)) {
				//估值反冲，卖出放到这里
				PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());

				TranClo oldClo=tranCloDao.getLastTranClo(portfolio.getId(), tran.getLedgerDate(), tran.getCusip().getId(), tran.getLotnum());
				if(oldClo!=null) {
					double lastCloseAmount=oldClo.getAmount();
					double last_l_v_gainloss=RoundUtil.roundMoney2Dec(oldClo.getgMkValue()-oldClo.getgCost());
					double last_f_v_gainloss=RoundUtil.roundMoney2Dec(RoundUtil.roundMoney2Dec(oldClo.getgMkValue()*oldClo.getPrinFxRate())-oldClo.getgCostFunc());
					List<Tran> tranList=params.getTranList();
					
					for(Tran todaytran:tranList) {
						if(todaytran.getTrantype()==TransTypes.SALE || todaytran.getTrantype()==TransTypes.TENDER_OFFER) {
							//卖出带出的估增=卖出数量/当前总数*前一天的估增
							double l_diff=RoundUtil.roundMoney2Dec(last_l_v_gainloss*todaytran.getAmount()/lastCloseAmount);
							double f_diff=RoundUtil.roundMoney2Dec(last_f_v_gainloss*todaytran.getAmount()/lastCloseAmount);
							//获得剩余估增
							last_l_v_gainloss=RoundUtil.roundMoney2Dec(last_l_v_gainloss-l_diff);
							last_f_v_gainloss=RoundUtil.roundMoney2Dec(last_f_v_gainloss-f_diff);
							
							if(todaytran.getTrannum()==tran.getTrannum()) {
								if (f_diff < 0) { // 赚了。
									jobCom.saveGL(pursaleCooa, 
											DBCR.DEBIT,
											Math.abs(f_diff), 
											Math.abs(l_diff), 
											jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
						
									jobCom.saveGL(pursaleCooa, 
											DBCR.CREDIT,
											Math.abs(f_diff), 
											Math.abs(l_diff), 
											jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_GAIN));
						
								} else if(f_diff>0){ // 亏了或平了。
									jobCom.saveGL(pursaleCooa,
											DBCR.DEBIT,
											f_diff,
											l_diff, 
											jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.VALUATION_LOSS));
						
									jobCom.saveGL(pursaleCooa, 
											DBCR.CREDIT,
											Math.abs(f_diff), 
											Math.abs(l_diff), 
											jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CARRY_VAL_ADJ));
								}
								break;
							}
						}
						
						SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(todaytran.getTrantype());
						boolean buysalefalg=secBasic.buySaleFlag();
						//获得剩余数量
						if(buysalefalg) {
							lastCloseAmount=lastCloseAmount+todaytran.getAmount();
						}else {
							lastCloseAmount=lastCloseAmount-todaytran.getAmount();
						}
					}
				}
			}
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
