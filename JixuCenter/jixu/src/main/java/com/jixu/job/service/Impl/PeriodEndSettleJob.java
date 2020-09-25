package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.ReturnResult;
import com.jixu.job.obj.JobParam;
import com.jixu.job.obj.PeriodSetDownType;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;

/** 
 * @ClassName 		PeriodEndSettle
 * @author  		Pan Wei 
 * @Date 			2016年11月14日 下午12:25:39 	
 * @Description		[]  
 */

@Service("periodEndSetJob")
public class PeriodEndSettleJob extends JNLJob {
	
	//private Logger logger = Logger.getLogger(PeriodEndSettleJob.class);
	
	public PeriodEndSettleJob() {
		jobType=JobsType.PeriodSet;
		journalType=JournalTypes.SETDOWN;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		List<GLCOOABase> expenseJnlList = new ArrayList<GLCOOABase>();
		
		processSetDown(jobParam, expenseJnlList, getPortfolio(jobParam.getPortfolio()));

		return expenseJnlList;
	}
	
	private void processSetDown(JobParam jobParam, List<GLCOOABase> setDownJnlList, PortfolioHdr portfolio) throws JNLException{
		Date ledgerDate=jobParam.getDate();
		
		List<PeriodSetDownType> setList=voucherDetailsDao.loadPreSetDownList(jobParam.getPortfolio(), ledgerDate);
		
		GLCOOABase setdownCooa=new GLCOOABase();
		
		for(PeriodSetDownType setdown:setList){
			if(setdown.getDr_func()>0){
				jobCom.saveGL(setdownCooa, 
				DBCR.DEBIT, 
				setdown.getDr_func(),
				setdown.getDr_local(), 
				setdown.getCloseToacctnum());
				
				jobCom.saveGL(setdownCooa, 
				DBCR.CREDIT, 
				setdown.getDr_func(),
				setdown.getDr_local(), 
				setdown.getAcctnum());
			}else if(setdown.getDr_func()<0){
				jobCom.saveGL(setdownCooa, 
				DBCR.CREDIT, 
				Math.abs(setdown.getDr_func()),
				Math.abs(setdown.getDr_local()), 
				setdown.getCloseToacctnum());
					
				jobCom.saveGL(setdownCooa, 
				DBCR.DEBIT, 
				Math.abs(setdown.getDr_func()),
				Math.abs(setdown.getDr_local()), 
				setdown.getAcctnum());
			}
		}
			
		Voucher voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.SETDOWN, "", "期末结转");
		setdownCooa.setVoucher(voucher);
			
		jobCom.genVoucherDetails(setdownCooa, portfolio, 0, ReturnResult.getDicHM().get(ReturnResult.closeto_journal_chinese));
		setDownJnlList.add(setdownCooa);
	}
}
