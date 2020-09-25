package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobFxOpen")
public class TranJobFxOpen extends TranJobBasic{
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		String funCurrency=commfuncService.loadFxCurrencyCode(portfolio.getCurID());
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		
		double gainloss=0;
		int glgroup=tranbasic.getGlGroup();
		String chartnum=portfolio.getCoaahdr();
		CashCooa cashCooa = new CashCooa(); 
		Voucher voucher=new Voucher();
		voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
		cashCooa.setVoucher(voucher);
		
		for(CashFlowObj cashobj:cashflowObjList){
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			
			if(glgroup==0 && cashobj.getCusip() != null){
				glgroup=cashobj.getCusip().getCoaaGroup();
			}
				
				
			int inNum=GLACCT.RECEIV_SECUR_SOLD;
			int outNum=GLACCT.PAY_SECUR_PURCH;
			
			String cashglcode=bkcashacct.getCashglcode();
			checkCashAcctValiable(cashglcode, bkcashacct.getBankdesc(), BankAccountType.Cash_Account);
			
			if (cashobj.getDbcr().equals(DBCR.DEBIT)) { //收入
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate()), 
						cashobj.getCash(), 
						cashglcode,
						cashobj.getBkCashAcct().getFxcurrency());
				
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.CREDIT, 
						cashobj.getOrigcash(), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
						cashobj.getBkCashAcct().getFxcurrency());
						//银行存款已实现汇兑
				
				if (gainloss > 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL),
							funCurrency);
				} else if(gainloss<0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL),
							funCurrency);
				}
				
			} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,outNum),
						commfuncService.loadFxCurrencyCode(cashobj.getBkCashAcct().getFxcurrency()));
				
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
						cashobj.getCash(), cashglcode,
						commfuncService.loadFxCurrencyCode(cashobj.getBkCashAcct().getFxcurrency()));
				
				//清算款已实现汇兑
				if (gainloss < 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL),
							funCurrency);
					
				} else if(gainloss>0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL),
							funCurrency);
				}
			}
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
		}
		cooaList.add(cashCooa);
		
		return cooaList;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException {
		String dbcr=seccashList.get(0).getDbcr();
		int glmap=0;
		if(dbcr.equals(DBCR.DEBIT)){
			glmap=GLACCT.PAY_SECUR_PURCH;
		}else{
			glmap=GLACCT.FX_RESERVE_CAP;
		}
		
		if (gainAmount > 0) {
			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,					
					RoundUtil.roundMoney2Dec(gainAmount),
					0.0,			
					jobCom.loadGLAcctnum(chartnum, groupnum, glmap));
			
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(gainAmount),
					0.0,				
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_GAIN));

		} else {
			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,					
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_LOSS));

			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,
					jobCom.loadGLAcctnum(chartnum, groupnum, glmap));
		}
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=tranBasic.getGlGroup();
		if(groupnum==0)
			groupnum=params.getGroupnum();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());
		String localCur=commfuncService.loadFxCurrencyCode(portfolio.getCurID());
		IssueInfo issueInfo =tranBasic.getCusip();
		
		jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INVEST_REC_BANK),
				tranBasic.getFxcurrency());
		
		jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				tran.getgCostFunc(),
				0, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN),
				localCur);

		jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				tran.getgCostFunc2(),
				tran.getgCost2(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INVEST_PAY_BANK),
				tranBasic.getFxcurrency2());
		
		jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				tran.getgCostFunc2(),
				0, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS),
				localCur);
		
		//------------------------写voucherdetails
//		String desc=SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()) + ": " + 
//				commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency())+"/"+commfuncService.loadFxCurrencyCode(tranBasic.getFxcurrency2());
//		pursaleCooa.getVoucher().setDescription(desc);
//		jobCom.genVoucherDetailsFX(pursaleCooa, tran, "", chartnum);
	}
	
	private void saveGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
