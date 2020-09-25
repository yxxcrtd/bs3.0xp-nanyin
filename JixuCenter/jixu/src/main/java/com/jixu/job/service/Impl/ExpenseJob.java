package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;

@Service("expenseJNLJob")
public class ExpenseJob extends JNLJob {
	
	@Autowired
	private GenFeeFollowFee genFeeFollowFee;
	
	public ExpenseJob() {
		jobType=JobsType.EXPEND;
		journalType=JournalTypes.EXPEND;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		List<GLCOOABase> resultList=new ArrayList<GLCOOABase>();
		String portnum=jobParam.getPortfolio();
		Date asOfDate=jobParam.getDate();
		List<PortExpenseCfg> cfgList=portExpenseCfgDao.loadTaxFeeCfgOldNetAssetByPortNum(portnum);
		List<AcctDCBalance> balList=voucherDetailsDao.loadAcctDCBalance(portnum, asOfDate);
		
		//如果有配置计算费前净值的
		if(cfgList.size()>0){
			for(PortExpenseCfg cfg:cfgList){
				//通过配置选择了哪个费用实现类,来判断调用哪个job
				GLCOOABase result=TaxFeeJobFactory.createInstance(cfg.getClassService()).executeJob(jobParam.getUserID(), balList, cfg, asOfDate);
				if(result!=null)
					resultList.add(result);
			}
		}
		
		//浮动费用凭证
		List<GLCOOABase> feeVoucherList=genFeeFollowFee.genTrannFeeVoucherDetails(portnum, asOfDate);
		for(GLCOOABase voucher:feeVoucherList){
			resultList.add(voucher);
		}
		return resultList;
	}
}
