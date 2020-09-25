package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
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
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssetJobCashMove
 * @author  		Pan Wei 
 * @Date 			2017年1月22日 下午11:55:10 	
 * @Description		[]  
 */

@Service("tranJobCashMove")
public class TranJobCashMove extends TranJobBasic{
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException {
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		Voucher voucher=new Voucher();
		CashCooa cashCooa = new CashCooa();
		for(CashFlowObj cashobj:cashflowObjList){
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			String bankdesc=bkcashacct.getBankdesc();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			double gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			
			String cashglcode=bkcashacct.getCashglcode();
			String cashunrealfxgnls=bkcashacct.getCashunrealfxgnls();
			String cashrealfxgnls=bkcashacct.getCashrealfxgnls();
			
			checkCashAcctValiable(cashglcode, bankdesc, BankAccountType.Cash_Account);
			checkCashAcctValiable(cashunrealfxgnls, bankdesc, BankAccountType.Cash_UnRealizedFxGainLoss);
			checkCashAcctValiable(cashrealfxgnls, bankdesc, BankAccountType.Cash_RealizedFxGainLoss);
			
			if (cashobj.getDbcr().equals(DBCR.DEBIT)) {
				jobCom.saveGL(cashCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()),
						0,
						cashunrealfxgnls);
				
				jobCom.saveGL(cashCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(func_cash), 
						RoundUtil.roundMoney2Dec(cashobj.getCash()),
						cashglcode);
				
					
				jobCom.saveGL(cashCooa,
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(gainloss),
					RoundUtil.roundMoney2Dec(0), 
					cashrealfxgnls);
					
			} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) {
				jobCom.saveGL(cashCooa,
						DBCR.CREDIT,								
						RoundUtil.roundMoney2Dec(func_cash), 
						RoundUtil.roundMoney2Dec(cashobj.getCash()),
						cashglcode);
				
				jobCom.saveGL(cashCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
						0,
						cashunrealfxgnls);
				
				jobCom.saveGL(cashCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(gainloss),
						RoundUtil.roundMoney2Dec(0), 
						cashrealfxgnls);
			}
			
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
		}
		
		voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(),"结转"+desc);
		cashCooa.setVoucher(voucher);
		cooaList.add(cashCooa);
		
		return cooaList;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
