package com.jixu.job.service.TaxFeeJob;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.NavDao;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.service.TaxFeeJobService;
import com.jixu.job.service.Impl.JobCommonImpl;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		NetAssetExpenseJob
 * @author  		Pan Wei 
 * @Date 			2018年5月10日 上午11:04:54 	
 * @Description		[]  
 */

@Service
public class NetAssetExpenseJob implements TaxFeeJobService{
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;
	
	@Autowired
	private NavDao navDao;
	
	@Override
	public String jobName() {
		return "资产净值费用";
	}
	
	/**
	 * 根据前一天的净资产为基准，计算当天的管理费，托管费等费用
	 */
	@Override
	public GLCOOABase executeJob(int userID, List<AcctDCBalance> balList, PortExpenseCfg cfg, Date asOfDate) throws JNLException {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(cfg.getPortfolio().getId());
		NavAssetObj navAssetObj=navDao.getLatestNAV(portfolio.getId(), asOfDate);
		double rate=cfg.getExpenseAmount();
		double expense=0;
		Date lastNavDate=navAssetObj.getNavDate();
		Date startDate=null;
		if(lastNavDate!=null){
			startDate=DateUtil.getNextDay(lastNavDate);
		}else{
			startDate=portfolio.getLastTranClose();
		}
		
		if(cfg.getFixFeeFlag()==ExpenseBaseType.FixExpense){
			expense=incomeExpenseCal.incomeFixedCal(startDate, asOfDate, rate);
		}else if(cfg.getFixFeeFlag()==ExpenseBaseType.FloatExpense){
			expense=incomeExpenseCal.incomeCal(cfg.getIntCode(), startDate, asOfDate, rate, navAssetObj.getNetAsset());
		}
		
		if(expense!=0){
			GLCOOABase expenseCooa=new GLCOOABase();
			jobCom.saveGL(expenseCooa, 
					DBCR.DEBIT, 
					expense,
					expense, 
					cfg.getDrAcct());
			
			jobCom.saveGL(expenseCooa, 
					DBCR.CREDIT, 
					expense,
					expense, 
					cfg.getCrAcct());
			/*if(cfg.isExpenseFlag()){
				jobCom.saveGL(expenseCooa, 
						DBCR.DEBIT, 
						expense,
						expense, 
						cfg.getDrAcct());
				
				jobCom.saveGL(expenseCooa, 
						DBCR.CREDIT, 
						expense,
						expense, 
						cfg.getCrAcct());
			}else{
				jobCom.saveGL(expenseCooa, 
						DBCR.CREDIT, 
						expense,
						expense, 
						cfg.getDrAcct());
				
				jobCom.saveGL(expenseCooa, 
						DBCR.DEBIT, 
						expense,
						expense, 
						cfg.getCrAcct());
			}*/
			
			Voucher voucher=jobCom.genVoucher(asOfDate, portfolio, JournalTypes.EXPEND, "", jobName());
			expenseCooa.setVoucher(voucher);
			
			jobCom.genVoucherDetails(expenseCooa, portfolio, navAssetObj.getNetAsset(), cfg.getPortFeeName());
			return expenseCooa;
		}else{
			return null;
		}
	}
	
	@Override
	public String jobDesc() {
		String desc="当选择浮动:费用金额=最新的净资产位基准*税费利率/选择的总天数;当选择固定:费用金额=填入的费用金额";
		return desc;
	}
}
