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
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobShareSub")
public class TranJobShareSub extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		int glgroup=portfolio.getCoaaGroup().getGroupNum();
		String chartnum=portfolio.getCoaahdr();
		
		for(CashFlowObj cashobj:cashflowObjList){
			CashCooa cashCooa = new CashCooa(); 
			Voucher voucher=new Voucher();
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
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
						bkcashacct.getFxcurrency());
				
				jobCom.savePortGL(cashCooa,
						portfolio.getId(),
						DBCR.CREDIT, 
						cashobj.getOrigcash(), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
						bkcashacct.getFxcurrency());
			} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
				jobCom.savePortGL(cashCooa,
						portfolio.getId(),
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
						cashobj.getCash(), cashglcode,bkcashacct.getFxcurrency());
			}
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			cashCooa.setVoucher(voucher);
			cooaList.add(cashCooa);
		}
		
		return cooaList;
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
