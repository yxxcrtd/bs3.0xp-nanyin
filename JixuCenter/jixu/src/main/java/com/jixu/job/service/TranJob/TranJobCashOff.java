package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranBasicDao;
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

@Service("tranJobCashOff")
public class TranJobCashOff extends TranJobBasic{
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException {
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		for(CashFlowObj cashobj:cashflowObjList){
			Voucher voucher=new Voucher();
			CashCooa cashCooa = new CashCooa();
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			double gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			
			String offsetacct=tranbasic.getGlacct();
			
			String cashglcode=bkcashacct.getCashglcode();
			String cashrealfxgnls=bkcashacct.getCashrealfxgnls();
			
			checkCashAcctValiable(cashglcode, bkcashacct.getBankdesc(), BankAccountType.Cash_Account);
			checkCashAcctValiable(cashrealfxgnls, bkcashacct.getBankdesc(), BankAccountType.Cash_RealizedFxGainLoss);
			
			if(offsetacct==null || offsetacct.equals("")){
				StringBuilder errmsg=new StringBuilder();
				errmsg.append("Cash Off");
				errmsg.append(": ");
				errmsg.append("Offset Account");
				errmsg.append(": ");
				errmsg.append(offsetacct);
				throw new JNLException(errmsg.toString());
			}
				
			if (cashobj.getDbcr().equals(DBCR.DEBIT)) {
				jobCom.saveGL(cashCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()),
						RoundUtil.roundMoney2Dec(cashobj.getCash()),
						offsetacct);
				
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
						RoundUtil.roundMoney2Dec(cashobj.getCash()),
						offsetacct);
				
				jobCom.saveGL(cashCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(gainloss),
						RoundUtil.roundMoney2Dec(0), 
						cashrealfxgnls);
			}
			
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), "结转"+desc);
			cashCooa.setVoucher(voucher);
			cooaList.add(cashCooa);
		}
		
		return cooaList;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		TranBasic tran=tranBasicDao.loadEntity(TranBasic.class, tanum);
		int cashacctid=0;
		if(tran.getAmount()>0){
			cashacctid=tran.getBkCashacctNo().getId();
		}else{
			cashacctid=tran.getBkCashacctNo2().getId();
		}
		BkCashAcct cashacct=bkCashAcctDao.loadBankCash(cashacctid);
		String dracct=tran.getGlacct();
		String cracct=cashacct.getCashrealfxgnls();
		
		
		if (gainAmount < 0) {
			
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					dracct);
			
			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					cracct);

		} else {
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					cracct);

			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					dracct);

		}
		
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
