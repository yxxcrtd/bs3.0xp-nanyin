package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.SwapSetPrincipalType;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.Voucher;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service
public class TranJobSwapFXMaturity extends TranJobBasic {

	@Resource(name = "tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio,
			String desc, Date ledgerDate) throws JNLException {
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
	
		double gainloss=0;
		IssueInfo sec=commfuncService.loadSecByCusip(tranbasic.getCusip().getId());
		int glgroup=sec.getCoaaGroup();
		String chartnum=portfolio.getCoaahdr();
		String cusip=tranbasic.getCusip().getId();
		String localCur=portfolio.getCurID();
		
		if(sec.getSwapExchangeInd()==SwapSetPrincipalType.BothSwap) {
			CashCooa cashCooa = new CashCooa();
			Voucher voucher=new Voucher();
			for(CashFlowObj cashobj:cashflowObjList){
				BkCashAcct bkcashacct=cashobj.getBkCashAcct();
				
				double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
				gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
				
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
							bkcashacct.getFxcurrency());
					
					jobCom.saveSecGL(cashCooa,
							cusip,
							DBCR.CREDIT, 
							cashobj.getOrigcash(), 
							cashobj.getCash(), 
							jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
							bkcashacct.getFxcurrency());
							//银行存款已实现汇兑
					
					if (gainloss > 0) {
						jobCom.saveSecGL(cashCooa,cusip,
								DBCR.CREDIT, 
								RoundUtil.roundMoney2Dec(gainloss), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL),
								localCur);
					} else if(gainloss<0){
						jobCom.saveSecGL(cashCooa,cusip,
								DBCR.DEBIT, 
								RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL),
								localCur);
					}
					
				} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
					jobCom.saveSecGL(cashCooa,
							cusip,
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
							cashobj.getCash(), 
							jobCom.loadGLAcctnum(chartnum, glgroup,outNum),
							bkcashacct.getFxcurrency());
					
					jobCom.saveZJZHGL(cashCooa,
							portfolio,
							bkcashacct,
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
							cashobj.getCash(), cashglcode,
							bkcashacct.getFxcurrency());
					
					//清算款已实现汇兑
					if (gainloss < 0) {
						jobCom.saveSecGL(cashCooa,cusip,
								DBCR.CREDIT, 
								RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL),
								localCur);
						
					} else if(gainloss>0){
						jobCom.saveSecGL(cashCooa,cusip,
								DBCR.DEBIT, 
								RoundUtil.roundMoney2Dec(gainloss), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL),
								localCur);
					}
				}
				jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
				voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			}
			cashCooa.setVoucher(voucher);
			cooaList.add(cashCooa);
		}else {
			CashCooa cashCooa = new CashCooa();
			Voucher voucher=new Voucher();
			
			CashCooa outcashCooa = new CashCooa();
			Voucher outvoucher=new Voucher();	//表外账
			for(CashFlowObj cashobj:cashflowObjList){
				BkCashAcct bkcashacct=cashobj.getBkCashAcct();
				
				double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
				
				int outNum=GLACCT.OUT_PAY_SECUR_PURCH;
				int inNum=GLACCT.OUT_RECEIV_SECUR_SOLD;
				
				String cashglcode=bkcashacct.getCashglcode();
				checkCashAcctValiable(cashglcode, bkcashacct.getBankdesc(), BankAccountType.Cash_Account);
				
				if (cashobj.getDbcr().equals(DBCR.DEBIT)) { //收入
					jobCom.saveZJZHGL(cashCooa,
							portfolio,
							bkcashacct,
							DBCR.DEBIT, 
							func_cash, 
							cashobj.getCash(), 
							cashglcode,
							bkcashacct.getFxcurrency());
					
					//表外账
					jobCom.saveOffGL(outcashCooa,
							cusip,
							DBCR.CREDIT, 
							cashobj.getOrigcash(), 
							cashobj.getCash(), 
							jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
							bkcashacct.getFxcurrency());
							//银行存款已实现汇兑
					
					jobCom.saveSecGL(cashCooa,cusip,
								DBCR.CREDIT, 
								func_cash, 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL),
								localCur);
					
					
				} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
					jobCom.saveOffGL(outcashCooa,
							cusip,
							DBCR.DEBIT, 
							cashobj.getOrigcash(), 
							cashobj.getCash(), 
							jobCom.loadGLAcctnum(chartnum, glgroup,outNum),
							bkcashacct.getFxcurrency());
					
					jobCom.saveZJZHGL(cashCooa,
							portfolio,
							bkcashacct,
							DBCR.CREDIT, 
							func_cash, 
							cashobj.getCash(), cashglcode,
							bkcashacct.getFxcurrency());
		
					jobCom.saveSecGL(cashCooa,cusip,
							DBCR.DEBIT, 
							func_cash, 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL),
							localCur);
				}
				jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
				jobCom.genVoucherDetailsOFF(outcashCooa, portfolio, cashobj);
			}
			
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			outvoucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			
			cashCooa.setVoucher(voucher);
			outcashCooa.setVoucher(outvoucher);
			cooaList.add(cashCooa);
			cooaList.add(outcashCooa);
		}
		
		return cooaList;
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
