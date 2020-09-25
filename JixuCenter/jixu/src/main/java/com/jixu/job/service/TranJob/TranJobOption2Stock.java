package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.OptionType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobOption2Stock")
public class TranJobOption2Stock extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService cashjob;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return cashjob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}
	
	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException{
	}
	
	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		
		String tanum=tranBasic.getId();
		IssueInfo checkSec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());
		if(checkSec.getIssueType().equals(AssetTypes.COMMON_STOCK)){
			//会查出两条tran记录,分别为股票和债券交易。 交易凭证只出一张，所以放在债券交易的tran上处理。
			return;
		}
		
		List<Tran> transList=tranDao.loadTransByTanum(tanum);
		
		Tran tranStock=null;
		Tran tranBond=null;
		
		checkSec=issueInfoDao.loadIssueByCusip(transList.get(0).getCusip().getId());
		if(checkSec.getIssueType().equals(AssetTypes.COMMON_STOCK)){
			tranStock=transList.get(0);
			tranBond=transList.get(1);
		}else{
			tranStock=transList.get(1);
			tranBond=transList.get(0);
		}
		
		IssueInfo secStock=issueInfoDao.loadIssueByCusip(tranStock.getCusip().getId());
		IssueInfo secBond=issueInfoDao.loadIssueByCusip(tranBond.getCusip().getId());
		
		int groupnumStock=secStock.getCoaaGroup();
		int groupnumBond=secBond.getCoaaGroup();
		String priDay=DateUtil.getPriorDay(tran.getLedgerDate());
		TranClo oldclo=tranCloDao.loadTranClo(tranBond.getPortfolio(),DateUtil.convertStr2Date(priDay), tranBond.getCusip().getId(), tranBond.getLotnum());
		
		if(secBond.getOptionType()==OptionType.put){
			if(oldclo.getAmount()>0){
				//获取总账组信息
				// 证券清算款 （交易中的现金返还金额）
				double func_cash=RoundUtil.roundMoney2Dec(tranBasic.getCash()*tranStock.getPrinFxrate());
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						func_cash,
						tranBasic.getCash(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.PAY_SECUR_PURCH));
				
				//(强制调账到交易所费用)
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						tranBond.getgCostFunc(),
						tranBond.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.BOOK_VALUE));
				
				/*double stockCostFunc=RoundUtil.roundMoney2Dec(tranBond.getgCostFunc()+func_cash);
				double stockCost=RoundUtil.roundMoney2Dec(tranBond.getgCost()+tranBasic.getCash());
				// 股票投资－成本 （轧差所得）
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						stockCostFunc,
						stockCost, 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));*/
				//股票投资－成本 （按照转换股票当天的价格 ＊ 转换的份额）
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						tranStock.getgCostFunc(),
						tranStock.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));
				
				double diffFunc=RoundUtil.roundMoney2Dec(tranStock.getgCostFunc()-tranBond.getgCostFunc()-func_cash);
				double diff=RoundUtil.roundMoney2Dec(tranStock.getgCost()-tranBond.getgCost()-tranBasic.getCash());
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						diffFunc,
						diff, 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
			}else{
				//获取总账组信息
				// 股票投资－成本 （按照转换股票当天的价格 ＊ 转换的份额）
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						tranStock.getgCostFunc(),
						tranStock.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));
				
				// 证券清算款 （交易中的现金返还金额）
				double func_cash=RoundUtil.roundMoney2Dec(tranBasic.getCash()*tranStock.getPrinFxrate());
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						func_cash,
						tranBasic.getCash(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.PAY_SECUR_PURCH));
				
				//(强制调账到交易所费用)
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						tranBond.getgCostFunc(),
						tranBond.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.BOOK_VALUE));
		
				
				double gainFunc=RoundUtil.roundMoney2Dec(tranStock.getgCostFunc()-func_cash-tranBond.getgCostFunc());
				double gainlocal=RoundUtil.roundMoney2Dec(tranStock.getgCost()-tranBasic.getCash()-tranBond.getgCost());
				if(gainFunc>0){
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, 
							gainFunc,
							gainlocal, 
							jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
				}else{
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, 
							gainFunc,
							gainlocal, 
							jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
				}
			}
			
		}else{
			if(oldclo.getAmount()>0){
				//获取总账组信息
				// 证券清算款 （交易中的现金返还金额）
				double func_cash=RoundUtil.roundMoney2Dec(tranBasic.getCash()*tranStock.getPrinFxrate());
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						func_cash,
						tranBasic.getCash(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.PAY_SECUR_PURCH));
				
				//(强制调账到交易所费用)
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						tranBond.getgCostFunc(),
						tranBond.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.BOOK_VALUE));
				
				// 股票投资－成本 （按照转换股票当天的价格 ＊ 转换的份额）
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						tranStock.getgCostFunc(),
						tranStock.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));
				
				double gainFunc=RoundUtil.roundMoney2Dec(func_cash-tranStock.getgCostFunc()-tranBond.getgCostFunc());
				double gainlocal=RoundUtil.roundMoney2Dec(tranBasic.getCash()-tranStock.getgCost()-tranBond.getgCost());
				if(gainFunc>0){
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT, 
							gainFunc,
							gainlocal, 
							jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
				}else{
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT, 
							gainFunc,
							gainlocal, 
							jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
				}
			}else{
				// 证券清算款 （交易中的现金返还金额）
				double func_cash=RoundUtil.roundMoney2Dec(tranBasic.getCash()*tranStock.getPrinFxrate());
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT,
						func_cash,
						tranBasic.getCash(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.RECEIV_SECUR_SOLD));
				/*
				double stockCostFunc=RoundUtil.roundMoney2Dec(func_cash-tranBond.getgCostFunc());
				double stockCost=RoundUtil.roundMoney2Dec(tranBasic.getCash()-tranBond.getgCost());
				
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						stockCostFunc,
						stockCost, 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));*/
				
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						tranStock.getgCostFunc(),
						tranStock.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumStock, GLACCT.BOOK_VALUE));
				//(强制调账到交易所费用)
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						tranBond.getgCostFunc(),
						tranBond.getgCost(), 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.BOOK_VALUE));
				
				double diffFunc=RoundUtil.roundMoney2Dec(tranStock.getgCostFunc()+tranBond.getgCostFunc()-func_cash);
				double diff=RoundUtil.roundMoney2Dec(tranStock.getgCost()+tranBond.getgCost()-tranBasic.getCash());
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						diffFunc,
						diff, 
						jobCom.loadGLAcctnum(chartnum, groupnumBond, GLACCT.CAPITAL_GAIN));
			}
		}
		String desc=SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()) + ": " + tran.getCusip().getSecurityid();
		pursaleCooa.getVoucher().setDescription(desc);
		jobCom.genVoucherDetailsTranJob(pursaleCooa, tran, secBond.getSecurityid()+" - " + secStock.getSecurityid(), portfolio.getCurID(), tranBasic.getFxcurrency(), chartnum);
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		return null;
	}
}
