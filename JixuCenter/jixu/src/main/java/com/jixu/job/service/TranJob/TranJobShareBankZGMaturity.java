package com.jixu.job.service.TranJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.GLACCT;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranFeeDef;

/**
 * @ClassName AssetJobBasic
 * @author Pan Wei
 * @Date 2017年1月18日 下午3:43:21
 * @Description []
 */

@Service
public class TranJobShareBankZGMaturity extends TranJobBasic {

	@Resource(name = "tranJobStockPurchase")
	private TranJobService tranJob;
	
	@Autowired
	private PerformanceFeeCfgDao performanceFeeCfgDao;

	@Override
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException {
		return tranJob.exeCashJob(cashflowObjList, tranbasic, portfolio, desc, ledgerDate);
	}

	@Override
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList, double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException {
	}

	@Override
	public void exeTranJob(TranJobParams params) throws JNLException{
		GLCOOABase pursaleCooa=params.getPursaleCooa();
		TranBasic tranBasic=params.getTranBasic();
		Tran tran=params.getTran();
		String chartnum=params.getChartnum();
		int groupnum=params.getGroupnum();
		
		// 获取总账组信息
		double income = tranBasic.getIntatmat();
		jobCom.saveGL(pursaleCooa, DBCR.CREDIT,
				RoundUtil.roundMoney2Dec(income * tranBasic.getFxrate()),
				income,
				jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.ACCRUED_INC));

		jobCom.saveGL(pursaleCooa, DBCR.DEBIT, RoundUtil.roundMoney2Dec(income
				* tranBasic.getFxrate()), income, jobCom.loadGLAcctnum(
				chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
		
		
		if(tran.getProceeds2()!=0){
			PerformanceFeeCfg pfc=performanceFeeCfgDao.loadActivePerformanceFeeCfg(tran.getPortfolio(), tran.getCusip().getId(), tran.getLotnum());
			
			if(pfc!=null){
				double proceedsFunc=RoundUtil.roundMoney2Dec(tran.getProceeds2()*tran.getPrinFxrate());
				if(tran.getProceeds2()>0){
					//绩效费
					jobCom.saveGL(pursaleCooa, 
							DBCR.CREDIT,
							proceedsFunc,
							tran.getProceeds2(),
							pfc.getCrAcct());
					
					jobCom.saveGL(pursaleCooa, 
							DBCR.DEBIT, 
							proceedsFunc,
							tran.getProceeds2(),
							jobCom.loadGLAcctnum(chartnum, groupnum, GLACCT.PAY_SECUR_PURCH));
				}else if(tran.getProceeds2()<0){
					//回拨费用
					int feeDefID=pfc.getClawBackExpenseType();
					TranFeeDef feedef=tranFeeDefDao.loadTranFeeDef(feeDefID);
					
					jobCom.saveGL(pursaleCooa, 
							DBCR.DEBIT,
							proceedsFunc,
							tran.getProceeds2(),
							jobCom.loadGLAcctnum(chartnum, groupnum, feedef.getFeeGlCol()));
					
					jobCom.saveGL(pursaleCooa,
							DBCR.CREDIT,
							proceedsFunc,
							tran.getProceeds2(),
							jobCom.loadGLAcctnum(chartnum, groupnum, feedef.getRcpyGlCol()));
				}
			}
		}
	}

	@Override
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException {
		// TODO Auto-generated method stub
		return null;
	}
}
