package com.jixu.job.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.LANGUAGE;
import com.jixu.common.util.ReturnResult;
import com.jixu.job.service.ImportManualService;
import com.jixu.job.service.PrintJNLFactory;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("jxImportManual")
public class ManualVoucher implements ImportManualService{

	@Resource(name="commfuncService")
	private CommFuncService comFuncService;

	@Resource(name = "printJNLFactory")
	private PrintJNLFactory printJNLFactory;
	
	@Resource(name="jobCommonImpl")
	private JobCommonImpl jobCom;

	private static final Logger logger = LoggerFactory.getLogger(ManualVoucher.class);

	public int importManualVoucher(String portnum, Date voucherDate, List<VoucherDetails> vdList) {
		try {
			Voucher voucher = new Voucher();
			PortfolioHdr portfolio = comFuncService.loadPortfolio(portnum);
			voucher.setAffirmDate(voucherDate);
			voucher.setAffirmUser("AffirmUser");
			voucher.setAuditDate(null);
			voucher.setAuditMsg(null);
			voucher.setAuditUser(null);
			voucher.setImportedDate(voucherDate);
			voucher.setImportedUser("PW");
			voucher.setIsAudit(0);
			voucher.setIsAuditMsgValid(0); 
			voucher.setJournalSourceCode(JournalTypes.MANNUAL);
			voucher.setLedgerDate(voucherDate);
			voucher.setManagerGroupCode(0);
			voucher.setPortfolio(portfolio.getId());
			voucher.setSettlementDate(voucherDate);
			voucher.setStampDate(voucherDate);
			voucher.setStampUser("");
			voucher.setTradeDate(voucherDate);

			GLCOOABase glcoa = new GLCOOABase();
			glcoa.setVoucher(voucher);
			
			for(VoucherDetails vd:vdList){
				vd.setChartNum(portfolio.getCoaahdr());
				vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));
				vd.setFxRate(1);
			}
			glcoa.setVoucherDetails(vdList);

			List<GLCOOABase> glList = new ArrayList<GLCOOABase>();
			glList.add(glcoa);

			printJNLFactory.getPringInstance(LANGUAGE.LANG).printJNL(glList);
			
			return ReturnResult.import_manual_voucher_success;
		} catch (Exception e) {
			logger.error("Error", e);
		}

		return ReturnResult.import_manual_voucher_failed;
	}
	
	@Override
	public ResultContainer importManualVoucher(String portnum, Date ledgerDate, IssueInfo sec, int tranType, 
			GLCOOABase cooa) {
		ResultContainer container=new ResultContainer();
		try {
			PortfolioHdr portfolio=comFuncService.loadPortfolio(portnum);
			Voucher voucher = jobCom.genVoucher(ledgerDate, portfolio, JournalTypes.MANNUAL, "", "");
			cooa.setVoucher(voucher);
			
			if(!JixuUserUtil.jixuUserSystemFlag() && JixuUserUtil.isJoyinTablesFlag()){
				String tableID=SecurityCoreFactory.getTableIDProperties().getProperty(JournalTypes.MANNUAL+","+sec.getIssueType()+","+tranType);
				cooa.setTableID(tableID);	//业务场景
				List<VoucherDetails> voucherDetailsList=cooa.getVoucherDetails();
				for (VoucherDetails voucherDetails : voucherDetailsList) {
					//判断  如果凭证里面没有业务明细类型和业务明细代码 就给上
					if(voucherDetails.getBusiID()!=null && !voucherDetails.getBusiID().equals("")){
						continue;
					}else{
						voucherDetails.setBusiID(sec.getSecurityid());  //业务明细代码  资产id
						voucherDetails.setBusiDetailType(sec.getIssueType()); //明细业务类型，资产类型
					}
				}
//				cooa.setBusiDetailType(sec.getIssueType());	//明细业务类型，资产类型
//				cooa.setBusiId(sec.getSecurityid()); //业务明细代码  资产id
			}

			List<GLCOOABase> glList = new ArrayList<GLCOOABase>();
			glList.add(cooa);
			
			printJNLFactory.getPringInstance(LANGUAGE.LANG).printJNL(glList);
		} catch (Exception e) {
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage());
			logger.error("Error", e);
		}
		
		return container;
	}
}
