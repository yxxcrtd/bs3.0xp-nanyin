package com.jixu.job.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.GLACCT;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.types.AssetCooaMapType;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.GlCoaaMapDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.journal.obj.CashBalanceWrapObj;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BokBookSet;
import com.orm.obj.CoaaGlSecMap;
import com.orm.obj.CoaaGroup;
import com.orm.obj.Coaahdr;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranSubRed;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Component("jobCommonImpl")
public class JobCommonImpl {
	
	@Autowired
	private GlCoaaMapDao glCoaaMapDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Resource(name="commfuncService")
	private CommFuncService comFuncService;
	
	@Autowired(required=false)
	private InterfCommonService interfCommonService;

	//证券和总账栏的对应关系
	private HashMap<AssetCooaMapType, Integer> newCoaGlMap;
	
	private HashMap<AssetCooaMapType, Integer> getCoaGloupMap() {
		if(newCoaGlMap==null) {
			newCoaGlMap=new HashMap<AssetCooaMapType, Integer>();
			List<CoaaGlSecMap> secGLMapList=glCoaaMapDao.loadEntityAll(CoaaGlSecMap.class);
			for(CoaaGlSecMap map:secGLMapList) {
				AssetCooaMapType type=new AssetCooaMapType();
				type.setAssetType(map.getAssetType());
				type.setRpSecType(map.getRpSecType());
				if(map.getPurpose()!=null)
					type.setPurpose(map.getPurpose());
				if(map.getLongCur()!=null)
					type.setLongCur(map.getLongCur());
				if(map.getShortCur()!=null)
					type.setShortCur(map.getShortCur());
				newCoaGlMap.put(type, map.getGlgoup());
			}
		}
		return newCoaGlMap;
	}
	
	/**
	 * 根据证券的资产类型和 rpsectype
	 * @param sec	证券
	 * @return
	 */
	public int getGlGroup(IssueInfo sec) {
		int glgroup=0;
		if(sec!=null) {
			AssetCooaMapType type=new AssetCooaMapType();
			type.setAssetType(sec.getIssueType());
			type.setRpSecType(sec.getRpSecType().getId());
			glgroup=getGlGroup(type);
		}
		return glgroup;
	}
	
	private int getGlGroup(AssetCooaMapType secType) {
		HashMap<AssetCooaMapType, Integer> glmap=getCoaGloupMap();
		for (Entry<AssetCooaMapType, Integer> entry : glmap.entrySet()) {
			AssetCooaMapType type=entry.getKey();
			if(type.equals(secType)) {
				return entry.getValue();
			}
		}
		return 0;
	}
	
	public String loadGLAcctnumComm(String chartnum, IssueInfo sec, int glnum) throws JNLException{
		AssetCooaMapType type=new AssetCooaMapType();
		type.setAssetType(sec.getIssueType());
		type.setRpSecType(sec.getRpSecType().getId());
		return loadGLAcctnum(chartnum, type, glnum);
	}
	
	public String loadGLAcctnum(String chartnum, AssetCooaMapType secgltype, int glnum) throws JNLException{
		int groupnum=getGlGroup(secgltype);
		String acctnum = glCoaaMapDao.loadGLAcctnum(chartnum, groupnum, glnum);
		if(acctnum==null){
			String coaname="";
			if(JixuUserUtil.jixuUserSystemFlag()){
				Coaahdr coaahdr=issueInfoDao.loadEntity(Coaahdr.class, chartnum);
				coaname=coaahdr.getChartname();
			}else{
				BokBookSet bookset=issueInfoDao.loadEntity(BokBookSet.class, chartnum);
				coaname=bookset.getBookset_name();
			}
			CoaaGroup coaGroup=issueInfoDao.loadEntity(CoaaGroup.class, groupnum);
			
			StringBuilder errmsg=new StringBuilder();
			errmsg.append(ReturnResult.getResultHM().get(ReturnResult.journal_account_unmap));
			errmsg.append(": ");
			errmsg.append("	会计科目表:");
			errmsg.append(chartnum + "|"+coaname);
			errmsg.append("	总账组:");
			errmsg.append(groupnum+"|"+coaGroup.getGroupName());
			errmsg.append("	总账栏:");
			errmsg.append(GLACCT.optionHM().get(glnum));
			throw new JNLException(errmsg.toString());
		}
		return acctnum;
	}
	
	public String loadGLAcctnum(String chartnum, int groupnum, int glnum) throws JNLException{
		String acctnum = glCoaaMapDao.loadGLAcctnum(chartnum, groupnum, glnum);
		if(acctnum==null){
			String coaname="";
			if(JixuUserUtil.jixuUserSystemFlag()){
				Coaahdr coaahdr=issueInfoDao.loadEntity(Coaahdr.class, chartnum);
				coaname=coaahdr.getChartname();
			}else{
				BokBookSet bookset=issueInfoDao.loadEntity(BokBookSet.class, chartnum);
				coaname=bookset.getBookset_name();
			}
			CoaaGroup coaGroup=issueInfoDao.loadEntity(CoaaGroup.class, groupnum);
			
			StringBuilder errmsg=new StringBuilder();
			errmsg.append(ReturnResult.getResultHM().get(ReturnResult.journal_account_unmap));
			errmsg.append(": ");
			errmsg.append("	会计科目表:");
			errmsg.append(chartnum + "|"+coaname);
			errmsg.append("	总账组:");
			errmsg.append(groupnum+"|"+coaGroup.getGroupName());
			errmsg.append("	总账栏:");
			errmsg.append(GLACCT.optionHM().get(glnum));
			throw new JNLException(errmsg.toString());
		}
		return acctnum;
	}
	
	public void saveGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//清算款业务明细代码
	public void saveQSKGL(GLCOOABase glcooa,TranBasic trandata,String dbcr, double funcCash, double localCash,String acctnum,String localCur) {
		PortfolioHdr portfolio=comFuncService.loadPortfolio(trandata.getPortfolio());
		//清算款账户业务明细代码
		BokBooksetCapacctInfo bbci=interfCommonService.queryBokBooksetCapacctInfo(portfolio.getId(), null,localCur,"A03");
		
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		if(bbci!=null){
			//业务明细类型
			jnlobj.setBusiDetailType(bbci.getAcctType());
			//业务明细代码
			jnlobj.setBusiID(bbci.getAcctNo());
		}
		jnlobj.setLocalCurrency(localCur);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//资金账户业务明细代码
	public void saveZJZHGL(GLCOOABase glcooa,PortfolioHdr portfolio,BkCashAcct bkcashacct,String dbcr, double funcCash, double localCash,String acctnum,String localCur) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		
		if(JixuUserUtil.isJoyinTablesFlag()) {
			//银行账户信息业务明细代码
			BokBooksetCapacctInfo bbci=interfCommonService.queryBokBooksetCapacctInfo(portfolio.getId(), bkcashacct.getExternalacctnumber(),bkcashacct.getFxcurrency(),null);
			if(bbci!=null){
				//业务明细类型
				jnlobj.setBusiDetailType(bbci.getAcctType());
				//业务明细代码
				jnlobj.setBusiID(bbci.getAcctNo());
			}
		}
		
		jnlobj.setLocalCurrency(localCur);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//标的信息业务明细代码
	public void saveSecGL(GLCOOABase glcooa,String cusip,String dbcr, double funcCash, double localCash,String acctnum,String localCur) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		//原币币种
		jnlobj.setLocalCurrency(localCur);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//标的信息业务明细代码(不填原币)
	public void saveSecGL(GLCOOABase glcooa,String cusip,String dbcr, double funcCash, double localCash,String acctnum) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//股票标的信息业务明细代码
	public void saveStockSecGL(GLCOOABase glcooa,String cusip, String dbcr, double funcCash, double localCash, String acctnum, double amount) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		jnlobj.setAmount(amount);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//表外账  标的信息明细代码
	public void saveOffGL(GLCOOABase glcooa,String cusip, String dbcr, double funcCash, double localCash, String acctnum,String localCur) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		jnlobj.setLocalCurrency(localCur);
		glcooa.getOffVoucherDetails().add(jnlobj);
	}
	//掉期  标的信息明细代码
	public void saveSwapGL(GLCOOABase glcooa,String cusip, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	//掉期  表外账标的信息明细代码
	public void saveSwapGLOFF(GLCOOABase glcooa,String cusip, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		//标的信息
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		//业务明细类型
		jnlobj.setBusiDetailType(sec.getIssueType());
		//业务明细代码
		jnlobj.setBusiID(sec.getSecurityid());
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getOffVoucherDetails().add(jnlobj);
	}
	
	//产品代码业务明细代码
	public void savePortGL(GLCOOABase glcooa,String portID, String dbcr, double funcCash, double localCash, String acctnum, String localCur) {
		PortfolioHdr portfolio=comFuncService.loadPortfolio(portID);
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		//业务明细类型
		jnlobj.setBusiDetailType("F16");
		//业务明细代码
		jnlobj.setBusiID(portfolio.getId());
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	
	public void saveGLOFF(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		glcooa.getOffVoucherDetails().add(jnlobj);
	}
	
	public void saveGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	public void saveGLOFF(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, String localCur, String funcCur, double fxrate) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		jnlobj.setFunctionCurrency(funcCur);
		jnlobj.setFxRate(fxrate);
		glcooa.getOffVoucherDetails().add(jnlobj);
	}
	
	public void saveLocalCurGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, String localCur) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setLocalCurrency(localCur);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	public void saveGL(GLCOOABase glcooa, String dbcr, double funcCash, double localCash, String acctnum, double amount) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);
		
		if (dbcr.equals(DBCR.CREDIT)) {
			jnlobj.setCr_func(funcCash);
			jnlobj.setCr_local(localCash);
		} else {
			jnlobj.setDr_func(funcCash);
			jnlobj.setDr_local(localCash);
		}
		jnlobj.setAmount(amount);
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	public void saveGL(GLCOOABase glcooa, double dr_funcCash, double dr_localCash, double cr_funcCash, double cr_localCash, String acctnum) {
		VoucherDetails jnlobj=new VoucherDetails();
		jnlobj.setAccountNum(acctnum);

		jnlobj.setCr_func(cr_funcCash);
		jnlobj.setCr_local(cr_localCash);
		jnlobj.setDr_func(dr_funcCash);
		jnlobj.setDr_local(dr_localCash);
		
		glcooa.getVoucherDetails().add(jnlobj);
	}
	
	
	public Voucher genVoucher(Date ledgerDate, PortfolioHdr portfolio, int journalTypeCode, String taNum, String desc){
		Voucher voucher=new Voucher();
		
		voucher.setPortfolio(portfolio.getId());
		voucher.setTradeDate(ledgerDate);
		voucher.setSettlementDate(ledgerDate);
		voucher.setLedgerDate(ledgerDate);
		voucher.setManagerGroupCode(portfolio.getManagerGroup());
		voucher.setJournalSourceCode(journalTypeCode);
		voucher.setStampUser("StampUser"); //to-do-pending
		voucher.setStampDate(null);//to-do-pending
		voucher.setAffirmUser("AffirmUser");//to-do-pending
		voucher.setAffirmDate(null); //to-do-pending
		voucher.setAuditUser("AuditUser");//to-do-pending
		voucher.setAuditDate(null);//to-do-pending
		voucher.setImportedUser("ImportedUser");//to-do-pending
		voucher.setImportedDate(DateUtil.convert2YYYYMMDD(new Date()));
		voucher.setIsAudit(0);
		voucher.setIsAuditMsgValid(0);
		voucher.setAuditMsg(null);
		voucher.setTaNum(taNum);
		voucher.setDescription(desc);
		return voucher;
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, TranBasic trandata, String secID, String chartnum){
		PortfolioHdr portfolio=comFuncService.loadPortfolio(trandata.getPortfolio());
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(chartnum); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(trandata.getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));	//to-do-pending
			vd.setFxRate(trandata.getFxrate());
			vd.setSecurityID(secID);
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(trandata.getPrice());
			vd.setAmount(trandata.getAmount());
			vd.setCurFxRate(trandata.getFxrate()); 
		}
	}
	
	public void genVoucherDetailsTranJob(GLCOOABase pursaleCooa, Tran trandata, String secID, String funcCurrID, String localCurrID, String chartnum){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			if(vd.getItemNum()!=null && !vd.getItemNum().equals("")){
				//如果在子交易实现类里面已经实现了凭证的细节，这里则不再二次生成
				continue;
			}
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(chartnum); 
			vd.setIsAdjusted(0);
			if(vd.getLocalCurrency()==null){
				vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(localCurrID));
			}
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(funcCurrID));
			vd.setFxRate(trandata.getTanum().getFxrate());
			vd.setSecurityID(secID);
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(trandata.getTanum().getPrice());
			vd.setAmount(trandata.getAmount());
			vd.setCurFxRate(trandata.getTanum().getFxrate()); 
		}
	}
	
	public void genVoucherDetailsTranByAmount(GLCOOABase pursaleCooa, Tran trandata, String secID, String funcCurrID, String localCurrID, String chartnum){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			if(vd.getItemNum()!=null && !vd.getItemNum().equals("")){
				//如果在子交易实现类里面已经实现了凭证的细节，这里则不再二次生成
				continue;
			}
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(chartnum); 
			vd.setIsAdjusted(0);
			if(vd.getLocalCurrency()==null){
				vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(localCurrID));
			}
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(funcCurrID));
			vd.setFxRate(trandata.getTanum().getFxrate());
			vd.setSecurityID(secID);
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(trandata.getTanum().getPrice());
			vd.setCurFxRate(trandata.getTanum().getFxrate()); 
		}
	}
	
	public void genOffVoucherDetailsTranJob(GLCOOABase pursaleCooa,String secID, String funcCurrID, String localCurrID){
		for(int i=0;i<pursaleCooa.getOffVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getOffVoucherDetails().get(i);
			if(vd.getItemNum()!=null && !vd.getItemNum().equals("")){
				continue;
			}
			vd.setItemNum(String.valueOf(i+1));
			if(vd.getLocalCurrency()==null){
				vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(localCurrID));
			}
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(funcCurrID));
			vd.setSecurityID(secID);
		}
	}
	
	public void genVoucherDetailsFX(GLCOOABase pursaleCooa, Tran trandata, String secID, String chartnum){
		
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(chartnum); 
			vd.setIsAdjusted(0);
			vd.setSecurityID(secID);
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(trandata.getTanum().getPrice());
			vd.setAmount(trandata.getAmount());
			vd.setCurFxRate(trandata.getTanum().getFxrate()); 
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, TranClo tranclo, double tprice, double fxrate){
		IssueInfo cusip=issueInfoDao.loadIssueByCusip(tranclo.getCusip());
		PortfolioHdr portfolio=comFuncService.loadPortfolio(tranclo.getPortfolio());
		
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portfolio.getCoaahdr()); //to-do-pending
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(cusip.getPriCur()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));	//to-do-pending
			vd.setFxRate(fxrate);
			vd.setSecurityID(cusip.getSecurityid());
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(tprice);
			vd.setAmount(tranclo.getAmount());
			vd.setCurFxRate(fxrate); 	
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, SecCashWrapObj secashwrapObj, double fxrate){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(secashwrapObj.getCurrShortCode());
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	//to-do-pending
			vd.setFxRate(fxrate);
			vd.setSecurityID(secashwrapObj.getSecid());
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(0);
			vd.setCurFxRate(fxrate); 	
		}
	}
		
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, CashFlowObj cashFlow){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			if(vd.getSecurityID()==null) {
				if(cashFlow.getCusip()==null){
					vd.setSecurityID(null);
				}else{
					vd.setSecurityID(cashFlow.getCusip().getSecurityid());
				}
			}
			if(vd.getAmount()==0)
				vd.setAmount(cashFlow.getAmount());
			
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			if(vd.getLocalCurrency()==null){
				vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(cashFlow.getBkCashRcpy().getFxcurrency()));
			}
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	//to-do-pending
			vd.setFxRate(cashFlow.getAsofdayRate());
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(0);
			vd.setCurFxRate(cashFlow.getAsofdayRate()); 	
		}
	}
	
	public void genVoucherDetailsOFF(GLCOOABase pursaleCooa, PortfolioHdr portHdr, CashFlowObj cashFlow){
		for(int i=0;i<pursaleCooa.getOffVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getOffVoucherDetails().get(i);
			if(vd.getLocalCurrency()!=null&&vd.getFunctionCurrency()!=null)
				continue;
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(cashFlow.getBkCashRcpy().getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	//to-do-pending
			vd.setFxRate(cashFlow.getAsofdayRate());
			if(cashFlow.getCusip()==null){
				vd.setSecurityID(null);
			}else{
				vd.setSecurityID(cashFlow.getCusip().getSecurityid());
			}
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(cashFlow.getAmount());
			vd.setCurFxRate(cashFlow.getAsofdayRate()); 	
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, String secid){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setSecurityID(secid);
			vd.setSecurityTypeCode("10007"); //to-do-pending
			vd.setPrice(0);
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, CashBalanceWrapObj cashBalancewrap, double fxrate){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(cashBalancewrap.getBkCashAcct().getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	
			vd.setFxRate(fxrate);
			vd.setSecurityID(null);
			vd.setSecurityTypeCode("0"); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(cashBalancewrap.getBkCashAcct().getLastBalance());
			vd.setCurFxRate(fxrate); 	
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, BkCashAcct bkCashAcct, double fxrate){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(bkCashAcct.getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	
			vd.setFxRate(fxrate);
			vd.setSecurityID(null);
			vd.setSecurityTypeCode("0"); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(bkCashAcct.getLastBalance());
			vd.setCurFxRate(fxrate); 	
		}
	}
	
	public void genVoucherDetails(GLCOOABase incomeCooa, PortfolioHdr portHdr, BkCashAcct bkcashacct, double totalBalance, double fxrate){
		for(int i=0;i<incomeCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=incomeCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(bkcashacct.getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	
			vd.setFxRate(fxrate);
			vd.setSecurityID(null);
			vd.setSecurityTypeCode("0"); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(totalBalance);
			vd.setCurFxRate(fxrate); 	
		}
	}
	
	public void genSubRedVoucherDetails(GLCOOABase pursaleCooa, TranSubRed trandata){
		PortfolioHdr portfolio=trandata.getPortfolio();
		TranBasic tb=trandata.getTanum();
		
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portfolio.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(tb.getFxcurrency()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));
			vd.setFxRate(trandata.getFxrate());
			vd.setSecurityID("-");
			vd.setSecurityTypeCode("-");
			vd.setPrice(0);
			vd.setAmount(trandata.getAmount());
			vd.setCurFxRate(trandata.getFxrate()); 
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, double amount){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	//to-do-pending
			vd.setFxRate(1);
			vd.setSecurityID(null);
			vd.setSecurityTypeCode(null); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(amount);
			vd.setCurFxRate(1); 
		}
	}
	
	public void genVoucherDetails(GLCOOABase pursaleCooa, PortfolioHdr portHdr, double units, String secid){
		for(int i=0;i<pursaleCooa.getVoucherDetails().size();i++){
			VoucherDetails vd=pursaleCooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portHdr.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portHdr.getCurID()));	//to-do-pending
			vd.setFxRate(1);
			vd.setSecurityID(secid);
			vd.setSecurityTypeCode(null); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(units);
			vd.setCurFxRate(1); 
		}
	}
	
	public void genVoucherDetails(GLCOOABase cooa, PortfolioHdr portfolio, SecIncomeWrapObj secIncomeObj, double fxRate){
		for(int i=0;i<cooa.getVoucherDetails().size();i++){
			VoucherDetails vd=cooa.getVoucherDetails().get(i);
			vd.setItemNum(String.valueOf(i+1));
			vd.setChartNum(portfolio.getCoaahdr()); 
			vd.setIsAdjusted(0);
			vd.setLocalCurrency(comFuncService.loadFxCurrencyCode(secIncomeObj.getSecurity().getPriCur()));
			vd.setFunctionCurrency(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));	//to-do-pending
			vd.setFxRate(fxRate);
			vd.setSecurityID(secIncomeObj.getSecurity().getSecurityid());
			vd.setSecurityTypeCode(SecurityCoreFactory.getAllAssetTypeHM().get(secIncomeObj.getSecurity().getIssueType())); //to-do-pending
			vd.setPrice(0);
			vd.setAmount(secIncomeObj.getClo().getAmount());
			vd.setCurFxRate(fxRate); 
		}
	}
}
