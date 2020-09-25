package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		TranJobForwardCurrencyOpen
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service
public class TranJobForwardCurrencyClose extends TranJobBasic{
	
	@Resource(name = "tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		
		double gainloss=0;
		int glgroup=0;
		String chartnum=portfolio.getCoaahdr();
		
		for(CashFlowObj cashobj:cashflowObjList){
			CashCooa cashCooa = new CashCooa(); 
			Voucher voucher=new Voucher();
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			
			if(cashobj.getCusip() != null){
				glgroup=cashobj.getCusip().getCoaaGroup();
			}else{
				//换汇fx_open用glgroup
				glgroup=tranbasic.getGlGroup();
				if(glgroup==0){
					glgroup=portfolio.getCoaaGroup().getGroupNum();
				}
			}
				
				
			int inNum=0;
			int outNum=0;
			if(cashobj.getPostType()==PostType.Fees){
				//产生费用的现金结转凭证
				TranBasicFee fee=cashobj.getBkCashRcpy().getFeeID();
				int feecol=fee.getFeeTypeID().getRcpyGlCol();
				outNum=feecol;
				inNum=feecol;
			}else{
				if(SecurityCoreFactory.getAssetTypeHM().get(AssetTypes.CAPITAL).containsKey(cashobj.getTranType())){
					outNum=GLACCT.PAY_FUND_SHARES_REDEEM;
					inNum=GLACCT.RECEIV_FUND_SHARES_SOLD;
				}else{
					outNum=GLACCT.PAY_SECUR_PURCH;
					inNum=GLACCT.RECEIV_SECUR_SOLD;
				}
			}
			
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
						tranbasic.getFxcurrency());
				
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.CREDIT, 
						cashobj.getOrigcash(), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
						tranbasic.getFxcurrency());
						//银行存款已实现汇兑
				
				if (gainloss > 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.CAPITAL_GAIN));
				} else if(gainloss<0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.CAPITAL_LOSS));
				}
				
			} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,outNum),
						tranbasic.getFxcurrency());
				
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
						cashobj.getCash(), cashglcode,tranbasic.getFxcurrency());
				
				//清算款已实现汇兑
				if (gainloss < 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.CAPITAL_GAIN));
					
				} else if(gainloss>0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.CAPITAL_LOSS));
				}
			}
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			cashCooa.setVoucher(voucher);
			
			cooaList.add(cashCooa);
		}
		
		return cooaList;
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
		
		//反冲开仓交易
		Tran t = tranDao.loadPreMaturityDepositTran(tran.getPortfolio(), tran.getCusip().getId(),tran.getLotnum());
		TranBasic tb=t.getTanum();
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranBasic.getPortfolio());
		IssueInfo issueInfo =tranBasic.getCusip();
		//表外账开仓交易当天多空方即期汇率
		double priFxRate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur(), t.getTradeDate());
		double priFxRate2 = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), issueInfo.getIncomeCur2(), t.getTradeDate());
		
		double diff=RoundUtil.roundMoney2Dec(tran.getAmount2()*tran.getIncFxrate2())-RoundUtil.roundMoney2Dec(tran.getAmount2()*t.getIncFxrate());
		double curFxrate= commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tranBasic.getFxcurrency(), tran.getTradeDate());
		
		//反冲表外账
		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT,
				Math.abs(RoundUtil.roundMoney2Dec(t.getAmount2() * priFxRate2)),
				Math.abs(t.getAmount2()), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),
				tb.getFxcurrency2());	
		
		jobCom.saveOffGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT,
				Math.abs(RoundUtil.roundMoney2Dec(t.getAmount() * priFxRate)), 
				Math.abs(t.getAmount()),
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
				tb.getFxcurrency());

		
		if(diff>0){
			//借
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				RoundUtil.roundMoney2Dec(diff*curFxrate),
				diff, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD),
				tranBasic.getFxcurrency());
			//贷
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				RoundUtil.roundMoney2Dec(diff*curFxrate),
				diff, 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN),
				tranBasic.getFxcurrency());
		}else{
			//借
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.DEBIT, 
				Math.abs(RoundUtil.roundMoney2Dec(diff*curFxrate)),
				Math.abs(diff), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS),
				tranBasic.getFxcurrency());
			//贷
			jobCom.saveSecGL(pursaleCooa,
				issueInfo.getId(),
				DBCR.CREDIT, 
				Math.abs(RoundUtil.roundMoney2Dec(diff*curFxrate)),
				Math.abs(diff), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),
				tranBasic.getFxcurrency());
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		return null;
	}
}
