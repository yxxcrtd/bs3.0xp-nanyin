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
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobStockCashDividend")
public class TranJobStockCashDividend extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
		/**计算估值**/
		if (gainAmount < 0) {
			
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDEND_REC));
			
			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_GAIN_INCOME_UNREAL));

		} else {
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_LOSS_INCOME_UNREAL));

			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDEND_REC));

		}
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		GLCOOABase pursaleCooa=null;
		String chartnum=portfolio.getCoaahdr();
		IssueInfo sec=issueInfoDao.loadIssueByCusip(tranBasic.getCusip().getId());
		int groupnum=sec.getCoaaGroup();
		
		List<BkCashRcpy> rcpyList=bkCashRcpyDao.loadRcpyList(tranBasic.getId());
		pursaleCooa=new GLCOOABase();
		for(BkCashRcpy rcpy:rcpyList){
			if(rcpy.getDbcr().equals(DBCR.CREDIT)){
				jobCom.saveSecGL(pursaleCooa,
						sec.getId(),
						DBCR.DEBIT, 
						rcpy.getOrigbal(), 
						rcpy.getFxorigbal(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDEND_REC));
				jobCom.saveSecGL(pursaleCooa,
						sec.getId(),
						DBCR.CREDIT, 
						rcpy.getOrigbal(), 
						rcpy.getFxorigbal(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INC_ACCRUAL));
			}else{
				pursaleCooa=new GLCOOABase();
				jobCom.saveSecGL(pursaleCooa,
						sec.getId(),
						DBCR.DEBIT, 
						rcpy.getOrigbal(), 
						rcpy.getFxorigbal(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.DIVIDENDS_PAYABLE));
				jobCom.saveSecGL(pursaleCooa,
						sec.getId(),
						DBCR.CREDIT, 
						rcpy.getOrigbal(), 
						rcpy.getFxorigbal(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.INC_ACCRUAL));
			}
		}
		
		return pursaleCooa;
	}
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc,
			Date ledgerDate) throws JNLException {
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
			outNum=GLACCT.DIVIDENDS_PAYABLE;
			inNum=GLACCT.DIVIDEND_REC;
			
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
						tranbasic.getCusip().getId(),
						DBCR.CREDIT, 
						cashobj.getOrigcash(), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,inNum));
						//银行存款已实现汇兑
				
				if (gainloss > 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
				} else if(gainloss<0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
				}
				
			} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.DEBIT,
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,outNum));
				
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.CREDIT,
						RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
						cashobj.getCash(), cashglcode,
						bkcashacct.getFxcurrency());
				
				//清算款已实现汇兑
				if (gainloss < 0) {
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
					
				} else if(gainloss>0){
					jobCom.saveSecGL(cashCooa,
							tranbasic.getCusip().getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(gainloss), 
							0, 
							jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
				}
			}
			jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
			voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
			cashCooa.setVoucher(voucher);
			
//			if(JixuUserUtil.isJoyinTablesFlag()){
//				//临时解决
//				BkCashAcct cashacct=bkCashAcctDao.loadBankAcct(cashobj.getBkCashAcct().getId());
//				BokBooksetCapacctInfo bbci=interfCommonService.queryBokBooksetCapacctInfo(tranbasic.getPortfolio(), cashacct.getExternalacctnumber(),cashacct.getFxcurrency(),null);
//				//业务明细类型
//				cashCooa.setBusiDetailType(bbci.getAcctType());
//				//业务明细代码
//				cashCooa.setBusiId(bbci.getAcctNo());
//			}
			
			cooaList.add(cashCooa);
		}
		
		return cooaList;
	}
}
