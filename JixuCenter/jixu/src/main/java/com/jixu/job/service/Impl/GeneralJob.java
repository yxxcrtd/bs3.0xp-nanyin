package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.AssetJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.service.PurSaleService;
import com.jixu.secTran.SecurityAssetService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("generalJnljob")
public class GeneralJob extends GeneralBaseJob {
	
	@Resource(name="purSaleService")
	private PurSaleService purSaleService;
	
	//private Logger logger = Logger.getLogger(GeneralJob.class);
	
	public GeneralJob() {
		jobType=JobsType.GENERAL;
		journalType=JournalTypes.GENERAL;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		logger.info(jobParam.getPortfolio() + ": 估增日记账开始...");
		List<GLCOOABase> genJnlList = new ArrayList<GLCOOABase>();
			
		//不需要用前一个净值日，直接用前一个总账关账日代替
		//Date lastNavDate=getLastNavDate(jobParam.getPortfolio(), jobParam.getDate());
		PortfolioHdr portfolio=getPortfolio(jobParam.getPortfolio());
			
		/****001股票估值********/
		secVJNL(jobParam, genJnlList,portfolio);
			
		/****002 银行存款估值 **************/
		//super.bankBalanceVJNL(jobParam, genJnlList, portfolio, lastNavDate);
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			super.bkBalanceVJNL(jobParam, genJnlList, portfolio, portfolio.getLastGLClose());
			/****003 证券清算款估值 **************/
			super.secCashVJNL(jobParam, genJnlList,portfolio);
		}
		logger.info(jobParam.getPortfolio() + ": 估增日记账结束...");
		return genJnlList;
	}
	
	/********股票估值**********/
	private void secVJNL(JobParam jobParam, List<GLCOOABase> genJnlList, PortfolioHdr portfolio) throws JNLException {
		
		List<STCostWrapObj> stcostWrapList = generalservice.loadSecGenerelJNLRecord(portfolio, jobParam.getDate(), "0");
		if (stcostWrapList != null) {
			//chartnum=portfolio.getCoaahdr().getId();
			Voucher voucher=null;
			
			//重新该方法,把计算成本的逻辑移到这里
			for (STCostWrapObj costWrap : stcostWrapList) {
				GLCOOABase generalCooa = null;
				IssueInfo sec=costWrap.getSec();
				SecurityAssetService assetService=secCoreFactory.createSecAssetTypeBean(sec.getIssueType());
				AssetJobService jobService=assetService.createJobService();
				if(jobService!=null){
					generalCooa=jobService.exeGeneralJob(portfolio, costWrap, jobParam.getDate());
				}
				
				if(generalCooa!=null){
					String desc="公允价值变动 " + sec.getSecurityid();
					voucher=jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.GENERAL, String.valueOf(costWrap.getTranClo().getId()), desc);
					generalCooa.setVoucher(voucher);
					
					if(JixuUserUtil.isJoyinTablesFlag()){
						String tableID="";
						if(generalCooa.getOffVouch()!=null && !generalCooa.getVoucher().equals("")) {
							tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.GENERAL+","+sec.getIssueType()+","+generalCooa.getOffVouch());
						}else
							tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.GENERAL+","+sec.getIssueType());
						generalCooa.setTableID(tableID);
						List<VoucherDetails> voucherDetailsList=generalCooa.getVoucherDetails();
						for (VoucherDetails voucherDetails : voucherDetailsList) {
							//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
							if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
								continue;
							}else{
								voucherDetails.setBusiID(sec.getSecurityid());
								voucherDetails.setBusiDetailType(sec.getIssueType());
							}
						}
//						generalCooa.setBusiDetailType(sec.getIssueType());
//						generalCooa.setBusiId(sec.getSecurityid());
					}
					genJnlList.add(generalCooa);
				}
			}
		}
	}
}
