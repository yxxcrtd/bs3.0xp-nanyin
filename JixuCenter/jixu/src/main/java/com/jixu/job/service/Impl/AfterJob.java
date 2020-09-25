package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.RoundUtil;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.VoucherDetails;

/** 
 * @ClassName 		AfterJob
 * @author  		Pan Wei 
 * @Date 			2018年4月24日 上午10:03:57 	
 * @Description		[]  
 */

@Service
public class AfterJob extends JNLJob{
	
	public AfterJob() {
		jobType=JobsType.AfterJob;
		journalType=JournalTypes.EXPEND;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException {
		List<GLCOOABase> resultList=new ArrayList<GLCOOABase>();
		String portnum=jobParam.getPortfolio();
		Date asOfDate=jobParam.getDate();
		List<PortExpenseCfg> cfgList=portExpenseCfgDao.loadTaxFeeCfgNewNetAssetByPortNum(portnum);
		
		//如果有配置计算费前净值的
		if(cfgList.size()>0){
			List<AcctDCBalance> balList=voucherDetailsDao.loadAcctDCBalance(portnum, asOfDate);
			for(PortExpenseCfg cfg:cfgList){
				//通过配置选择了哪个费用实现类,来判断调用哪个job
				GLCOOABase result=TaxFeeJobFactory.createInstance(cfg.getClassService()).executeJob(jobParam.getUserID(), balList, cfg, asOfDate);
				
				if(result==null)
					continue;
				//把凭证累加到原有的科目余额中,在所有的afterjob执行完成后，才会把凭证添加到voucher和voucherdetails表中
				List<VoucherDetails> detailsList=result.getVoucherDetails();
				for(VoucherDetails detail:detailsList){
					double drFunc=detail.getDr_func();
					double crFunc=detail.getCr_func();
					double drLocal=detail.getDr_local();
					double crLocal=detail.getCr_local();
					String acct=detail.getAccountNum();
					String secid=detail.getSecurityID();
					boolean matchFlag=false;
					for(AcctDCBalance bal:balList){
						if(acct.equals(bal.getAcctnum())){
							if((secid==null && bal.getSecid()==null) || (secid!=null && secid.equals(bal.getSecid()))){
								bal.setCrFuncAmt(RoundUtil.roundMoney2Dec(bal.getCrFuncAmt()+crFunc));
								bal.setDrFuncAmt(RoundUtil.roundMoney2Dec(bal.getDrFuncAmt()+drFunc));
								bal.setCrFuncAmtToday(RoundUtil.roundMoney2Dec(bal.getCrFuncAmtToday()+crFunc));
								bal.setDrFuncAmtToday(RoundUtil.roundMoney2Dec(bal.getDrFuncAmtToday()+drFunc));
								bal.setCrFuncAmtUnSet(RoundUtil.roundMoney2Dec(bal.getCrFuncAmtUnSet()+crFunc));
								bal.setDrFuncAmtUnSet(RoundUtil.roundMoney2Dec(bal.getDrFuncAmtUnSet()+drFunc));
								bal.setCrFuncAmtUnSetToday(RoundUtil.roundMoney2Dec(bal.getCrFuncAmtUnSetToday()+crFunc));
								bal.setDrFuncAmtUnSetToday(RoundUtil.roundMoney2Dec(bal.getDrFuncAmtUnSetToday()+drFunc));
								
								bal.setCrLocalAmt(RoundUtil.roundMoney2Dec(bal.getCrLocalAmt()+crLocal));
								bal.setDrLocalAmt(RoundUtil.roundMoney2Dec(bal.getDrLocalAmt()+drLocal));
								bal.setCrLocalAmtToday(RoundUtil.roundMoney2Dec(bal.getCrLocalAmtToday()+crLocal));
								bal.setDrLocalAmtToday(RoundUtil.roundMoney2Dec(bal.getDrLocalAmtToday()+drLocal));
								bal.setCrLocalAmtUnSet(RoundUtil.roundMoney2Dec(bal.getCrLocalAmtUnSet()+crLocal));
								bal.setDrLocalAmtUnSet(RoundUtil.roundMoney2Dec(bal.getDrLocalAmtUnSet()+drLocal));
								bal.setCrLocalAmtUnSetToday(RoundUtil.roundMoney2Dec(bal.getCrLocalAmtUnSetToday()+crLocal));
								bal.setDrLocalAmtUnSetToday(RoundUtil.roundMoney2Dec(bal.getDrLocalAmtUnSetToday()+drLocal));
								matchFlag=true;
								break;
							}
						}
					}
					if(!matchFlag){
						AcctDCBalance bal=new AcctDCBalance();
						bal.setCrFuncAmt(crFunc);
						bal.setDrFuncAmt(drFunc);
						bal.setCrFuncAmtToday(crFunc);
						bal.setDrFuncAmtToday(drFunc);
						bal.setCrFuncAmtUnSet(crFunc);
						bal.setDrFuncAmtUnSet(drFunc);
						bal.setCrFuncAmtUnSetToday(crFunc);
						bal.setDrFuncAmtUnSetToday(drFunc);
						
						bal.setCrLocalAmt(crLocal);
						bal.setDrLocalAmt(drLocal);
						bal.setCrLocalAmtToday(crLocal);
						bal.setDrLocalAmtToday(drLocal);
						bal.setCrLocalAmtUnSet(crLocal);
						bal.setDrLocalAmtUnSet(drLocal);
						bal.setCrLocalAmtUnSetToday(crLocal);
						bal.setDrLocalAmtUnSetToday(drLocal);
					}
				}
				if(result!=null){
					resultList.add(result);
				}
			}
		}
		return resultList;
	}
}
