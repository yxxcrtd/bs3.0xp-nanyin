package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostType;
import com.jixu.common.types.SwapSetPrincipalType;
import com.jixu.common.types.TranCloType;
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
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.Voucher;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service
public class TranJobSwapInterestMaturity extends TranJobBasic {

	@Resource(name = "tranJobStockPurchase")
	private TranJobService tranJob;

	@Resource(name = "tranJobCashMove")
	private TranJobService cashMoveJob;
	
	@Resource(name="secPositionCenter")
	private SecPositionCenter position;

	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio,
			String desc, Date ledgerDate) throws JNLException {
		IssueInfo sec = tranbasic.getCusip();
		List<CashCooa> cooaList=new ArrayList<CashCooa>();
		//结转利息凭证
		double gainloss=0;
		int glgroup=0;
		String chartnum=portfolio.getCoaahdr();
		
		TranCloType clotype=new TranCloType(tranbasic.getPortfolio(), sec.getId(), tranbasic.getDivBasicDate());
		//获取基准日的头寸
		List<TranClo> cloList=position.getSecClo(clotype, false);
		
		for(CashFlowObj cashobj:cashflowObjList){
			CashCooa cashCooa = new CashCooa(); 
			Voucher voucher=new Voucher();
			
			BkCashAcct bkcashacct=cashobj.getBkCashAcct();
			
			double func_cash=RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate());
			gainloss=RoundUtil.roundMoney2Dec(func_cash-cashobj.getOrigcash());
			//总账组
			glgroup=cashobj.getCusip().getCoaaGroup();
				
			TranClo clo=null;
			for(TranClo tmpclo:cloList) {
				if(cashobj.getDbcr().equals(DBCR.DEBIT) && tmpclo.getLsindicator()==LongShortType.longTerm) {
					clo=tmpclo;
					break;
				}else if(cashobj.getDbcr().equals(DBCR.CREDIT) && tmpclo.getLsindicator()==LongShortType.ShortTerm) {
					clo=tmpclo;
					break;
				}
			}
				
			int inNum=0;
			int ingcNum=0;	//轧差科目总账栏
			int outNum=0;
			int outgcNum=0;	//支轧差总账栏
			double ysyflx=clo.getAccrued();	//获取基准头寸日常计提的利息
			double ysyfunit=clo.getAmount();	//获取基准头寸的数量
			double cjje=cashobj.getCash();
			if(tranbasic.isSetAllIntatmatFlag() && cashobj.getDbcr().equals(DBCR.DEBIT)
				|| tranbasic.isSetAllIntatmatFlag2() && cashobj.getDbcr().equals(DBCR.CREDIT)){
				//判断多空双方分别是否需要结清利息
				cjje=ysyflx*tranbasic.getAmount()/ysyfunit;	//冲减金额
			}
			double gcje=RoundUtil.roundMoney2Dec(cashobj.getCash()-cjje);
			double gcjeFunc=RoundUtil.roundMoney2Dec(gcje*cashobj.getAsofdayRate());
			
			if(cashobj.getPostType()==PostType.Income){
				outNum=GLACCT.ACCRUED_INT_EXP_PAY_SHORT;
				outgcNum=GLACCT.ACCRUED_INT_EXP_SHORT;
				inNum=GLACCT.ACCRUED_INC;
				ingcNum=GLACCT.INC_ACCRUAL;
				
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
							sec.getId(),
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(cjje*cashobj.getAsofdayRate()), 
							cjje, 
							jobCom.loadGLAcctnum(chartnum, glgroup,inNum));
							//银行存款已实现汇兑
					
					//轧差金额
					jobCom.saveSecGL(cashCooa,
							sec.getId(),
							DBCR.CREDIT, 
							gcjeFunc, 
							gcje, 
							jobCom.loadGLAcctnum(chartnum, glgroup,ingcNum));
					
					if (gainloss > 0) {
						jobCom.saveSecGL(cashCooa,
								sec.getId(),
								DBCR.CREDIT, 
								RoundUtil.roundMoney2Dec(gainloss), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
					} else if(gainloss<0){
						jobCom.saveSecGL(cashCooa,
								sec.getId(),
								DBCR.DEBIT, 
								RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
					}
					cashCooa.setTableID("1811");
					
				} else if (cashobj.getDbcr().equals(DBCR.CREDIT)) { //支出	
					jobCom.saveSecGL(cashCooa,
							sec.getId(),
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(cjje*cashobj.getAsofdayRate()), 
							cjje, 
							jobCom.loadGLAcctnum(chartnum, glgroup,outNum));
					
					jobCom.saveZJZHGL(cashCooa,
							portfolio,
							bkcashacct,
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
							cashobj.getCash(), cashglcode,bkcashacct.getFxcurrency());
					//轧差金额
					jobCom.saveSecGL(cashCooa,
							sec.getId(),
							DBCR.DEBIT, 
							gcjeFunc, 
							gcje, 
							jobCom.loadGLAcctnum(chartnum, glgroup,outgcNum));
					
					//清算款已实现汇兑
					if (gainloss < 0) {
						jobCom.saveSecGL(cashCooa,
								sec.getId(),
								DBCR.CREDIT, 
								RoundUtil.roundMoney2Dec(Math.abs(gainloss)), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_LOSS_CAPITAL_UNREAL));
					} else if(gainloss>0){
						jobCom.saveSecGL(cashCooa,
								sec.getId(),
								DBCR.DEBIT, 
								RoundUtil.roundMoney2Dec(gainloss), 
								0, 
								jobCom.loadGLAcctnum(chartnum, glgroup,GLACCT.FX_GAIN_CAPITAL_UNREAL));
					}
					cashCooa.setTableID("1812");
				}
				jobCom.genVoucherDetails(cashCooa, portfolio, cashobj);
				voucher=jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.CASH, tranbasic.getId(), desc);
				cashCooa.setVoucher(voucher);
				cooaList.add(cashCooa);
			}
		}
			
		List<CashCooa> cooaList2=new ArrayList<CashCooa>();	
		List<CashFlowObj> principalCashList=new ArrayList<CashFlowObj>();
		//排除利息结息现金流
		for(CashFlowObj cashobj:cashflowObjList) {
			if(cashobj.getPostType()!=PostType.Income) {
				principalCashList.add(cashobj);
			}
		}
		
		// 如果是本金交换 会产生一笔现金划拨
		if (sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap || sec.getSwapExchangeInd()==SwapSetPrincipalType.BackSwap)
			cooaList2=cashMoveJob.exeCashJob(principalCashList, tranbasic, portfolio, desc, ledgerDate);
		
		cooaList.addAll(cooaList2);
		return cooaList;
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum,
			int groupnum, String tanum) throws JNLException {
		tranJob.exeGeneralJob(generalCooa, seccashList, gainAmount, chartnum, groupnum, tanum);
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		IssueInfo sec = tranBasic.getCusip();

		if (sec.getSwapExchangeInd() == SwapSetPrincipalType.BackSwap
				|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
			if (tran.getLsindicator() == LongShortType.longTerm) {
				// 收入端
				// 获取总账组信息
				// Set BOOK_VALUE 成本
				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(), DBCR.CREDIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),tranBasic.getFxcurrency());

				// Set PAY_SECUR_PURCH 证券清算款(买)
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.DEBIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),tranBasic.getFxcurrency());
			} else {
				// 支出端
				// Set BOOK_VALUE 成本
				jobCom.saveSecGL(pursaleCooa,tran.getCusip().getId(), DBCR.DEBIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),tranBasic.getFxcurrency());

				// Set PAY_SECUR_PURCH 证券清算款(买)
				jobCom.saveQSKGL(pursaleCooa,tranBasic, DBCR.CREDIT, tran.getgCostFunc(), tran.getgCost(),
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.RECEIV_SECUR_SOLD),tranBasic.getFxcurrency());
			}
		}else {
			//表外账
			if (tran.getLsindicator() == LongShortType.longTerm) {
				jobCom.saveOffGL(pursaleCooa,
						sec.getId(),
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(tran.getAmount()*tran.getPrinFxrate()), 
						tran.getAmount(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),
						tranBasic.getFxcurrency());
			}else {
				jobCom.saveOffGL(pursaleCooa,
						sec.getId(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(tran.getAmount()*tran.getPrinFxrate()), 
						tran.getAmount(), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT),
						tranBasic.getFxcurrency2());
			}
		}

		// 交易接手过来的利息
//		double intpsFunc = RoundUtil.roundMoney2Dec(tran.getIntps() * tranBasic.getFxrate());
//
//		if (tran.getIntps() != 0) {
//			if (tran.getLsindicator() == LongShortType.longTerm) {
//				// 收入端
//				jobCom.saveGL(pursaleCooa, DBCR.DEBIT, intpsFunc, tran.getIntps(),
//						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
//
//				jobCom.saveGL(pursaleCooa, DBCR.CREDIT, intpsFunc, tran.getIntps(),
//						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
//			} else { // 支出端
//				jobCom.saveGL(pursaleCooa, DBCR.CREDIT, intpsFunc, tran.getIntps(),
//						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
//
//				jobCom.saveGL(pursaleCooa, DBCR.DEBIT, intpsFunc, tran.getIntps(),
//						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));
//			}
//		}

		/*** 本币借贷不平调整到费用(强制调账到交易所费用) **/
//		double diff = 0;
//		double funcCash = RoundUtil.roundMoney2Dec(tranBasic.getCash() * tranBasic.getFxrate());
//
//		if (tran.getLsindicator() == LongShortType.ShortTerm) {
//			if (sec.getSwapExchangeInd() == SwapSetPrincipalType.BackSwap
//					|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
//				diff = RoundUtil.roundMoney2Dec(funcCash - totalFeeFunc - tran.getgCostFunc() - intpsFunc);
//			} else {
//				diff = RoundUtil.roundMoney2Dec(funcCash - totalFeeFunc - intpsFunc);
//			}
//		} else {
//			if (sec.getSwapExchangeInd() == SwapSetPrincipalType.BackSwap
//					|| sec.getSwapExchangeInd() == SwapSetPrincipalType.BothSwap) {
//				diff = RoundUtil.roundMoney2Dec(funcCash + totalFeeFunc - tran.getgCostFunc() - intpsFunc);
//			} else {
//				diff = RoundUtil.roundMoney2Dec(funcCash + totalFeeFunc - intpsFunc);
//			}
//		}
//
//		// (强制调账到交易所费用)
//		if (Math.abs(diff) > DBCR.ZERO) {
//			jobCom.saveGL(pursaleCooa, DBCR.DEBIT, RoundUtil.roundMoney2Dec(diff), 0,
//					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE));
//			jobCom.saveGL(pursaleCooa, DBCR.CREDIT, RoundUtil.roundMoney2Dec(diff), 0,
//					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
//		}

	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
