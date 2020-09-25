package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		AssetJobBasic
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:43:21 	
 * @Description		[]  
 */

@Service("TranJobSecLendReturn")
public class TranJobSecLendReturn extends TranJobBasic{
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException{
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
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
		int groupnum=params.getGroupnum();
		
		IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
		if(sec.getIssueType().equals(AssetTypes.COMMON_STOCK) && tran.getLsindicator()==LongShortType.longTerm){
			List<Tran> tranList=tranDao.loadTransByTanum(tranBasic.getId());
			IssueInfo sec_rq=null;
			
			Tran tran_long=tran;
			Tran tran_short=null;
			
			for(Tran tmpTran:tranList){
				String tmpCusip=tmpTran.getCusip().getId();
				IssueInfo secTmp=issueInfoDao.loadIssueByCusip(tmpCusip);
				if(tmpTran.getLsindicator()==LongShortType.ShortTerm){
					tran_short=tmpTran;
				}else if(secTmp.getIssueType().equals(AssetTypes.SEC_LENDING)){
					sec_rq=secTmp;
				}
			}
			
			if(tran_short==null || sec_rq==null){
				return;
			}
			
			//获取总账组信息
			// Set BOOK_VALUE 成本
			jobCom.saveGL(pursaleCooa,
					DBCR.DEBIT, 
					tran_short.getgCostFunc(),
					tran_short.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE_SHORT));
			
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tran_long.getgCostFunc(),
					tran_long.getgCost(), 
					jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.BOOK_VALUE));
			
			if(tranBasic.getIntatmat()!=0){
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(tranBasic.getIntatmat()*tranBasic.getFxrate()), 
						tranBasic.getIntatmat(),
						jobCom.loadGLAcctnum(chartnum, sec_rq.getCoaaGroup(), GLACCT.ACCRUED_INC));
			}
			
			jobCom.saveGL(pursaleCooa,
					DBCR.CREDIT, 
					tranBasic.getCash(), 
					tranBasic.getCash(), 
					jobCom.loadGLAcctnum(chartnum, sec_rq.getCoaaGroup(), GLACCT.RECEIV_SECUR_SOLD));
			
			double diff = RoundUtil.roundMoney2Dec(tran_long.getgCost()-tran_short.getgCost());
			double diff_func=RoundUtil.roundMoney2Dec(tran_long.getgCostFunc()-tran_short.getgCostFunc());
			
			if (diff_func < 0) { // 赚了
				jobCom.saveGL(pursaleCooa,
						DBCR.CREDIT, 
						RoundUtil.roundMoney2Dec(Math.abs(diff_func)),
						RoundUtil.roundMoney2Dec(Math.abs(diff)), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_GAIN));
			} else { // 亏了
				jobCom.saveGL(pursaleCooa,
						DBCR.DEBIT, 
						RoundUtil.roundMoney2Dec(diff_func),
						RoundUtil.roundMoney2Dec(diff), 
						jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.CAPITAL_LOSS));
			}
			
			//反冲已收利息
			TranSum tsum=tranSumDao.loadTSumByCusipLot(tran.getPortfolio(), tran.getCusip().getId(), tran.getLotnum());
			if(tsum.getAmount()==0){
				double accruedAmt=0;
				List<TranClo> cloList=tranCloDao.getLastTranCloAccrued(tran.getPortfolio(), tran.getCusip().getId(), tran.getLotnum(), tran.getLedgerDate());
				if(cloList.size()>0){
					TranClo clo=cloList.get(0);
					boolean checkAccrued=tranCloDao.checkAccrued(clo);
					if(!checkAccrued){
						accruedAmt=clo.getAccrued();
					}
				}
				
				double diffAcc=RoundUtil.roundMoney2Dec(accruedAmt-tran.getIntps());
				double diffAccFunc=RoundUtil.roundMoney2Dec(diffAcc*tran.getIncFxrate());
			
				if(diffAcc!=0 || diffAccFunc!=0){
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT,
							diffAcc, 
							diffAccFunc,
							jobCom.loadGLAcctnum(chartnum, sec.getCoaaGroup(), GLACCT.ACCRUED_INC));
					
					jobCom.saveGL(pursaleCooa,
							DBCR.DEBIT,
							diffAcc, 
							diffAccFunc,
							jobCom.loadGLAcctnum(chartnum, sec.getCoaaGroup(), GLACCT.CAPITAL_LOSS));
				}
			}
		}else{
			return;
		}
	}
	
	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		return null;
	}
}
