package com.jixu.job.service.TranJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("tranJobStockPurchase")
public class TranJobStockPurchase extends TranJobBasic{
	
	/**
	 * 	交易凭证
	 */
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		double totalFeeFunc=params.getTotalFeeFunc();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		//成本科目
		jobCom.saveSecGL(pursaleCooa,
				tran.getCusip().getId(),	//证券代码
				DBCR.DEBIT, 				//借贷
				tran.getgCostFunc(),		//本币
				tran.getgCost(), 			//原币
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE),	//科目号
				tranBasic.getFxcurrency());		//货币
		
		double funcCash=RoundUtil.roundMoney2Dec(tranBasic.getSettleCash()*tranBasic.getSettleFxRate());
		
		//证券清算款科目
		jobCom.saveSecGL(pursaleCooa,
				tran.getCusip().getId(),
				DBCR.CREDIT, 
				tran.getgCostFunc(),
				tran.getgCost(), 
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),
				tranBasic.getFxcurrency());
		
		/***本币借贷不平调整到费用(强制调账到交易所费用)**/
		double diff = RoundUtil.roundMoney2Dec(funcCash-totalFeeFunc-tran.getgCostFunc());
		
		//(强制调账到交易所费用)
		if (Math.abs(diff) > DBCR.ZERO) {
			jobCom.saveSecGL(pursaleCooa,
					tran.getCusip().getId(),
					DBCR.DEBIT, 
					RoundUtil.roundMoney2Dec(diff), 
					0, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FEE_EXPENSE),
					tranBasic.getFxcurrency());
			jobCom.saveSecGL(pursaleCooa,
					tran.getCusip().getId(),
					DBCR.CREDIT, 
					RoundUtil.roundMoney2Dec(diff), 
					0, 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH),
					tranBasic.getFxcurrency());
		}
	}
	
	/**
	 * 	现金交割凭证
	 * @param cashflowObjList 该交易产生的现金流
	 * @param tranbasic 交易原始数据
	 * @param portfolio 产品
	 * @param desc 交易描述
	 */
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
				//银行科目
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getAsofdayRate()), 
						cashobj.getCash(), 
						cashglcode,
						bkcashacct.getFxcurrency());
				
				//证券清算款科目
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.CREDIT, 
						cashobj.getOrigcash(), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,inNum),
						bkcashacct.getFxcurrency());
						//银行存款已实现汇兑
				
				//汇兑损益科目
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
				//证券清算款科目
				jobCom.saveSecGL(cashCooa,
						tranbasic.getCusip().getId(),
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(cashobj.getOrigcash()), 
						cashobj.getCash(), 
						jobCom.loadGLAcctnum(chartnum, glgroup,outNum),
						bkcashacct.getFxcurrency());
				
				//成本科目
				jobCom.saveZJZHGL(cashCooa,
						portfolio,
						bkcashacct,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getBkavgrate()), 
						cashobj.getCash(), cashglcode,bkcashacct.getFxcurrency());
				
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
			
			cooaList.add(cashCooa);
		}
		
		return cooaList;
	}
	
	/**
	 * 	证券清算款/应收应付由于汇率产生的估增
	 * 	@param seccashList 现金流
	 * 	@param gainAmount 由于汇率产生的估增
	 */
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
		/**计算估值**/
		if (gainAmount < 0) {
			
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),	//本币估增
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));
			
			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_GAIN));

		} else {
			jobCom.saveGL(generalCooa,
					DBCR.DEBIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_VALUATION_LOSS));

			jobCom.saveGL(generalCooa,
					DBCR.CREDIT,
					RoundUtil.roundMoney2Dec(Math.abs(gainAmount)),
					0.0,// 证券清算款估值没有原币估增。
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.FX_RESERVE_CAP));

		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic)
			throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
