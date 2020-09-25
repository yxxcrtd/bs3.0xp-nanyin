package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.JNLJob;
import com.jixu.job.service.TranJobService;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.CashFlowWrapObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.service.CashJNLService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.TranBasic;
import com.orm.obj.VoucherDetails;

@Service("cashjnlJob")
public class CashJob extends JNLJob {

	//private static Logger logger = Logger.getLogger(CashJob.class);
	
	@Resource(name="cashjnlService")
	private CashJNLService cashjnlService;
	
	@Autowired(required=false)
	private InterfCommonService interfCommonService;
	
	public CashJob() {
		jobType=JobsType.CASH;
		journalType=JournalTypes.CASH;
	}
	
	@Override
	public List<GLCOOABase> run(JobParam jobParam, ResultContainer container) throws JNLException{
		logger.info(jobParam.getPortfolio() + ": 现金日记账开始...");
		List<GLCOOABase> cashJnlList = new ArrayList<GLCOOABase>();
		
		CashFlowWrapObj cashFlowWrap = cashjnlService.loadCashFlowWrap(getPortfolio(jobParam.getPortfolio()),jobParam.getDate() );
		/*** 股票现金凭证***/
		cashJNL(jobParam, cashFlowWrap, cashJnlList);
		/*** 股票现金凭证***/
		logger.info(jobParam.getPortfolio() + ": 现金日记账结束...");
		return cashJnlList;
	}
	
	private void cashJNL(JobParam jobParam, CashFlowWrapObj cashFlowWrap, List<GLCOOABase> cashJnlList) throws JNLException {
		HashMap<TranBasic,List<CashFlowObj>> cashflowHM = cashFlowWrap.getCashFlowHM();
		if (cashflowHM != null) {
			Iterator<Entry<TranBasic,List<CashFlowObj>>> cashFlowIter=cashflowHM.entrySet().iterator();
			while(cashFlowIter.hasNext()) {
				Entry<TranBasic,List<CashFlowObj>> entry=cashFlowIter.next();
				List<CashFlowObj> cashflowObjList=entry.getValue();
				TranBasic tranbasic=entry.getKey();
				String desc=tranbasic.getTranDesc();
				if(desc==null || desc.equals("")){
					desc=SecurityCoreFactory.getAllTranTypeHM().get(tranbasic.getTrantype());
				}
				
				SecurityBasic secBasic=secCoreFactory.createSecTranTypeBean(tranbasic.getTrantype());
				TranJobService jobservice=secBasic.createJobService();
				List<CashCooa> cooaList=new ArrayList<CashCooa>();
				if(jobservice!=null){
					cooaList=secBasic.createJobService().exeCashJob(cashflowObjList, tranbasic, getPortfolio(jobParam.getPortfolio()), desc, jobParam.getDate());
				}			
				
				//如果银行用平均汇率,计算出银行存款的汇兑损益
				/*//银行存款已实现汇兑
				gainAmount = RoundUtil.roundMoney2Dec(cashobj.getCash() * cashobj.getAsofdayRate()) 
						- RoundUtil.roundMoney2Dec(cashobj.getCash()*cashobj.getBkavgrate());
				if (gainAmount < 0) {
					jobCom.saveGL(cashCooa, new VoucherDetails(), 
							DBCR.CREDIT, 
							RoundUtil.roundMoney2Dec(Math.abs(gainAmount)), 
							0, 
							bankglAcct.getCashFXRealAcctNO());
					
				} else {
					jobCom.saveGL(cashCooa, new VoucherDetails(), 
							DBCR.DEBIT, 
							RoundUtil.roundMoney2Dec(gainAmount), 
							0, 
							bankglAcct.getCashFXRealAcctNO());
				}*/
				
				if(cooaList!=null){
					for(CashCooa cashCooa:cooaList){
						if(JixuUserUtil.isJoyinTablesFlag()){
							IssueInfo sec=tranbasic.getCusip();
							if(cashCooa.getTableID()==null || cashCooa.getTableID().equals("")) {
								String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.CASH+","+sec.getIssueType()+","+tranbasic.getTrantype());
								cashCooa.setTableID(tableID);
								cashCooa.setOfftableID(tableID);
							}
							
							//通过账套id 现金账户 查询兆尹账套清算账户表信息
							List<VoucherDetails> voucherDetailsList=cashCooa.getVoucherDetails();
							BkCashAcct bkCashAcct=null;
							BokBooksetCapacctInfo bbci=null;
							if(tranbasic.getBkCashacctNo()!=null){
								bkCashAcct=tranbasic.getBkCashacctNo();
							}else{
								bkCashAcct=tranbasic.getBkCashacctNo2();
							}
							if(bkCashAcct!=null) {
								bbci=interfCommonService.queryBokBooksetCapacctInfo(tranbasic.getPortfolio(), bkCashAcct.getExternalacctnumber(),bkCashAcct.getFxcurrency(),null);
							}else {
								logger.error("Error", "未取到银行现金账户，无法获得现金凭证的业务明细代码");
							}
							
							for (VoucherDetails voucherDetails : voucherDetailsList) {
								if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
									continue;
								}else{
									//业务明细类型
									voucherDetails.setBusiDetailType(bbci.getAcctType());
									//业务明细代码
									voucherDetails.setBusiID(bbci.getAcctNo());
								}
							}
							//表外账业务明细代码
							List<VoucherDetails> offVoucherDetailsList=cashCooa.getOffVoucherDetails();
							for (VoucherDetails offVoucherDetails : offVoucherDetailsList) {
								//判断表外账  如果凭证里面没有业务明细类型和业务明细代码 就给上
								if(offVoucherDetails.getBusiID()!=null && !offVoucherDetails.getBusiID().equals("")){
									continue;
								}else{
									offVoucherDetails.setBusiID(sec.getSecurityid());
									offVoucherDetails.setBusiDetailType(sec.getIssueType());
								}
							}
							
						}
						cashJnlList.add(cashCooa);
					}
				}
			}
		}
	}
}
