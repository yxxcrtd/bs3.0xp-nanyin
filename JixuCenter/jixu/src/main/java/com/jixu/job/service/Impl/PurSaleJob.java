package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.job.obj.JobParam;
import com.jixu.job.obj.TranJobParams;
import com.jixu.job.service.JNLJob;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.TransactionWrapObj;
import com.jixu.journal.service.PurSaleService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("pursaleJnljob")
public class PurSaleJob extends JNLJob {

	@Resource(name="purSaleService")
	private PurSaleService purSaleService;
	
	@Resource(name="genTranFee")
	private GenTranFee genTranFee;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	
	public PurSaleJob() {
		jobType=JobsType.PURSALEJNL;
		journalType=JournalTypes.PURSALEJNL;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		logger.info(jobParam.getPortfolio() + ": 交易日记账开始...");
		List<TransactionWrapObj> transWrapList = purSaleService.loadTransData(jobParam.getDate(), getPortfolio(jobParam.getPortfolio()), jobParam.getCusip());
		//股票买卖
		List<GLCOOABase> pursaleJnlList=new ArrayList<GLCOOABase>();
		processPurSaleSec(pursaleJnlList, jobParam, transWrapList);
		
		//证券收入的交易日记帐,比如现金分红
		processIncomeSec(pursaleJnlList, jobParam);
		logger.info(jobParam.getPortfolio() + ": 交易日记账结束...");
		return pursaleJnlList;
	}
	
	private void processIncomeSec(List<GLCOOABase> pursaleJnlList, JobParam jobParam) throws JNLException{
			
		//移到tranjob里去,查询tranbasic trantype=cashdivident
		String portnum=jobParam.getPortfolio();
		PortfolioHdr portfolio=getPortfolio(portnum);
		String chartnum=portfolio.getCoaahdr();
		Date ledgerDate=jobParam.getDate();
		String cusip=jobParam.getCusip();
		List<TranBasic> tranincList=purSaleService.loadTranIncDate(portnum, ledgerDate, cusip);
		for(TranBasic inc:tranincList){
			SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(inc.getTrantype());
			TranJobService jobService=secBasic.createJobService();
			if(jobService!=null){
				GLCOOABase pursaleCooa=jobService.exeTranJob(portfolio, inc);
				if(pursaleCooa!=null){
					IssueInfo sec=inc.getCusip();
					String secid="";
					String desc=SecurityCoreFactory.getAllTranTypeHM().get(inc.getTrantype());
					if(sec!=null){
						desc=desc + ": " + sec.getSecurityid();
						secid=sec.getSecurityid();
					}
					jobCom.genVoucherDetails(pursaleCooa, inc, secid, chartnum);
					Voucher voucher= jobCom.genVoucher(jobParam.getDate(), portfolio, JournalTypes.PURSALEJNL, inc.getId(), desc);
					pursaleCooa.setVoucher(voucher);
					
					if(!JixuUserUtil.jixuUserSystemFlag() && JixuUserUtil.isJoyinTablesFlag()){
						String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.PURSALEJNL+","+sec.getIssueType()+","+inc.getTrantype());
						pursaleCooa.setTableID(tableID);	//业务场景
						List<VoucherDetails> voucherDetailsList=pursaleCooa.getVoucherDetails();
						for (VoucherDetails voucherDetails : voucherDetailsList) {
							//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
							if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
								continue;
							}else{
								voucherDetails.setBusiID(sec.getSecurityid());
								voucherDetails.setBusiDetailType(sec.getIssueType());
							}
						}
						
						List<VoucherDetails> offVoucherDetailsList=pursaleCooa.getOffVoucherDetails();
						for (VoucherDetails offVoucherDetails : offVoucherDetailsList) {
							//判断表外账  如果凭证里面没有业务明细类型和业务明细代码 就给上
							if(offVoucherDetails.getBusiID()!=null && !offVoucherDetails.getBusiID().equals("")){
								continue;
							}else{
								offVoucherDetails.setBusiID(sec.getSecurityid());
								offVoucherDetails.setBusiDetailType(sec.getIssueType());
							}
						}
						
//						pursaleCooa.setBusiDetailType(sec.getIssueType());	//明细业务类型，资产类型
//						pursaleCooa.setBusiId(sec.getSecurityid()); //业务明细代码  资产id
					}
					
					pursaleJnlList.add(pursaleCooa);
				}
			}
		}
	}
	
	/*************** Start 股票**************************/
	private List<GLCOOABase> processPurSaleSec(List<GLCOOABase> pursaleJnlList, JobParam jobParam, List<TransactionWrapObj> transWrapList)  throws JNLException{
		//A1*P2*R2 - A1*P1*R1 (总的损益)= (A1*P2*R2 - A1*P1*R2) (价格引起)+ (A1*P1*R2 - A1*P1*R1) (汇率变化引起)
		TransactionWrapObj tranwrap = null;
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(jobParam.getPortfolio());
		
		Iterator<TransactionWrapObj> ittranwrap = transWrapList.iterator();
		
		while (ittranwrap.hasNext()) {
			tranwrap = ittranwrap.next();
			Tran tran = tranwrap.getTrandataList().get(0); // Single Lot只有一条记录
			TranBasic tranBasic=tranwrap.getTranBasic();
			//生成费用凭证
			List<TranBasicFee> tranFeeList=tranwrap.getTransFeeList();
			
			GLCOOABase pursaleCooa=genVoucher(portfolio, jobParam.getDate(), tranBasic, tran, tranFeeList, tranwrap.getTodayTranList());
			pursaleJnlList.add(pursaleCooa);
		}
		
		return pursaleJnlList;
	}
	
	private GLCOOABase genVoucher(PortfolioHdr portfolio, Date voucherDate, TranBasic tranbasic, Tran trandata, List<TranBasicFee> tranFeeList, List<Tran> todayTranList) throws JNLException{
		GLCOOABase pursaleCooa = new GLCOOABase();
		Voucher voucher = new Voucher();
		int groupnum=0;
		String chartnum=portfolio.getCoaahdr();
		
		String secid="";
		if(trandata.getCusip()!=null){
			IssueInfo cusip=issueInfoDao.loadIssueByCusip(trandata.getCusip().getId());
			if(cusip!=null){
				groupnum = cusip.getCoaaGroup();
				secid=cusip.getSecurityid();
			}
		}else{
			groupnum=tranbasic.getGlGroup();
			secid="-";
		}
		
		//生成voucher总凭证
		String desc=SecurityCoreFactory.getAllTranTypeHM().get(trandata.getTrantype()) + ": " + secid;
		voucher=jobCom.genVoucher(voucherDate, portfolio, JournalTypes.PURSALEJNL, tranbasic.getId(), desc);
		pursaleCooa.setVoucher(voucher);
		
		double totalFeeFunc=genTranFee.genTrannFeeVoucherDetails(pursaleCooa, tranFeeList, chartnum, groupnum);
		
		SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(trandata.getTrantype());
		
		//转到不同交易类型的class去分别处理
		TranJobService jobService=secBasic.createJobService();
		if(jobService!=null){
			TranJobParams params=new TranJobParams(pursaleCooa, tranbasic, trandata, todayTranList, totalFeeFunc, chartnum, groupnum);
			
			jobService.exeTranJob(params);
			if(pursaleCooa.getVoucherDetails()!=null && pursaleCooa.getVoucherDetails().size()>0){
				if(pursaleCooa.isRemoveZeroFlag())
					jobCom.genVoucherDetailsTranJob(pursaleCooa, trandata, secid, portfolio.getCurID(), tranbasic.getFxcurrency(), chartnum);
				else
					jobCom.genVoucherDetailsTranByAmount(pursaleCooa, trandata, secid, portfolio.getCurID(), tranbasic.getFxcurrency(), chartnum);
			}
			if(!JixuUserUtil.jixuUserSystemFlag() && JixuUserUtil.isJoyinTablesFlag()){
				IssueInfo sec=tranbasic.getCusip();
				String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.PURSALEJNL+","+sec.getIssueType()+","+tranbasic.getTrantype());
				//表外账会计场景
				String offtableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.OFFBALANCESHEET+","+sec.getIssueType()+","+tranbasic.getTrantype());
				if(tableID!=null){
					pursaleCooa.setTableID(tableID);
				}else{
					pursaleCooa.setTableID(offtableID);
				}
				if(offtableID!=null){
					pursaleCooa.setOfftableID(offtableID);
				}else{
					pursaleCooa.setOfftableID(tableID);
				}
				List<VoucherDetails> voucherDetailsList=pursaleCooa.getVoucherDetails();
				for (VoucherDetails voucherDetails : voucherDetailsList) {
					//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
					if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
						continue;
					}else{
						voucherDetails.setBusiID(sec.getSecurityid());
						voucherDetails.setBusiDetailType(sec.getIssueType());
					}
				}
				//pursaleCooa.setBusiDetailType(sec.getIssueType());
				//pursaleCooa.setBusiId(sec.getSecurityid()); //业务明细代码  资产id
				if(pursaleCooa.getOffVoucherDetails()!=null && pursaleCooa.getOffVoucherDetails().size()>0){
					jobCom.genOffVoucherDetailsTranJob(pursaleCooa, secid, portfolio.getCurID(), tranbasic.getFxcurrency());
				}
			}
		}
		return pursaleCooa;
	}
	
	/**
	 * 单条交易查询交易凭证，效率慢
	 * @param tanum
	 * @return
	 */
	public GLCOOABase genTranVoucher(String tanum) throws JNLException{
		TranBasic tranbasic=(TranBasic)tranBasicDao.loadEntity(TranBasic.class, tanum);
		Tran tran=tranbasic.getTran().iterator().next(); // Single Lot只有一条记录
		List<TranBasicFee> tranFeeList=new ArrayList<TranBasicFee>();
		Iterator<TranBasicFee> iterators=tranbasic.getTranBasicFees().iterator();
		while(iterators.hasNext()){
			tranFeeList.add(iterators.next());
		}
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tranbasic.getPortfolio());
		return genVoucher(portfolio, tranbasic.getLedgerdate(), tranbasic, tran, tranFeeList, new ArrayList<Tran>());
	}
}
