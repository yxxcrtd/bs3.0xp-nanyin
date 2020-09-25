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
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranFeeDef;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service
public class TranJobShareBankZGPayFee extends TranJobBasic{
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		
		int glgroup=0;
		String chartnum=portfolio.getCoaahdr();
		int feetypecfg=Integer.valueOf(tranbasic.getContract());//目前只有在资管费用支付中，使用contract字段存放了费用的类型字段
		TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(feetypecfg);
		
		for(CashFlowObj cashobj:cashflowObjList){
			CashCooa cashCooa = new CashCooa(); 
			Voucher voucher=new Voucher();
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			glgroup=cashobj.getCusip().getCoaaGroup();
			
			String cashglcode=bkcashacct.getCashglcode();
			checkCashAcctValiable(cashglcode, bkcashacct.getBankdesc(), BankAccountType.Cash_Account);
			
			jobCom.saveGL(cashCooa,
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate()), 
					cashobj.getCash(), 
					cashglcode);
				
			jobCom.saveGL(cashCooa,
					DBCR.DEBIT, 
					cashobj.getOrigcash(), 
					cashobj.getCash(), 
					jobCom.loadGLAcctnum(chartnum, glgroup,feeDef.getSecPayGlCol()));
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			cashCooa.setVoucher(voucher);
			
			cooaList.add(cashCooa);
		}
		
		return cooaList;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa,
			List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		// TODO Auto-generated method stub
		//tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		int feetypecfg=Integer.valueOf(tranBasic.getContract());//目前只有在资管费用支付中，使用contract字段存放了费用的类型字段
		TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(feetypecfg);
		//获取总账组信息
		// Set BOOK_VALUE 成本
		jobCom.saveGL(pursaleCooa,
				DBCR.DEBIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getRcpyGlCol()));
		
		// Set PAY_SECUR_PURCH 证券清算款(买)
		jobCom.saveGL(pursaleCooa,
				DBCR.CREDIT,
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, feeDef.getSecPayGlCol()));
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
