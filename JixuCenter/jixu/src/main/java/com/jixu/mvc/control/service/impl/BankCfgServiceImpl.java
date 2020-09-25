package com.jixu.mvc.control.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.BankAccountType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.dao.intf.BkInteRatioDao;
import com.jixu.dao.intf.BokSubjectDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.mvc.control.model.BankInterestModel;
import com.jixu.mvc.control.service.BankCfgService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.BkInteAcct;
import com.orm.obj.BkInteRatio;
import com.orm.obj.BokSubject;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.Coaa;
import com.orm.obj.PortfolioHdr;

@Service("bankCfgService")
public class BankCfgServiceImpl implements BankCfgService {
	
	@Autowired
	private BkInfoDao bkInfoDao;
	
	@Autowired
	private BkInteRatioDao bkInteRatioDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
	@Resource(name="commfuncService")
	private CommFuncService comFuncService;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private BokSubjectDao bokSubjectDao;
	
	public HashMap<String, String> loadBankList() {
		HashMap<String, String> bankHM=new HashMap<String, String>();
		List<BkInfo> bkList=bkInfoDao.loadBankList();
		for(BkInfo bk:bkList){
			bankHM.put(bk.getBankID(), bk.getBankDesc());
		}
		return bankHM;
	}
	
	public BkInfo loadBkInfoByID(String bankID) {
		return bkInfoDao.loadEntity(BkInfo.class, bankID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateBank(BkInfo bank) {
		bkInfoDao.updateEntity(bank);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createBank(BkInfo bank) {
		bkInfoDao.saveEntity(bank);
	}
	
	public HashMap<Integer, String> loadBankAcct(String bankID) {
		HashMap<Integer, String>  acctHM=new HashMap<Integer, String>();
		List<BkCashAcct> acctList=bkCashAcctDao.loadBankAcctList(bankID);
		for(BkCashAcct acct:acctList){
			acctHM.put(acct.getId(), acct.getExternalacctnumber());
		}
		return acctHM;
	}
	
	@Override
	public HashMap<Integer, String> unMapBankAccountList(String bankID){
		HashMap<Integer, String>  acctHM=new HashMap<Integer, String>();
		List<BkCashAcct> acctList=bkCashAcctDao.unMapBankAccountList(bankID);
		for(BkCashAcct acct:acctList){
			acctHM.put(acct.getId(), acct.getExternalacctnumber());
		}
		return acctHM;
	}
	
	public BkCashAcct loadAcctDetail(int acctID) {
		return bkCashAcctDao.loadBankAcct(acctID);
	}
	
	@Override
	public HashMap<Integer, String> loadBankInteAcct(String bankID) {
		HashMap<Integer, String> inteAcctHM=new HashMap<Integer, String>();
		
		if(!JixuUserUtil.isJoyinTablesFlag()) {
			List<BkInteAcct> acctList=bkInteAcctDao.loadBankInteAcctByBank(bankID);
			for(BkInteAcct acct:acctList){
				inteAcctHM.put(acct.getId(), acct.getMasterrecname());
			}
		}
		return inteAcctHM;
	}
	
	public BankInterestModel loadBankInteAcctDetail(int acctID) {
		BkInteAcct acct=bkInteAcctDao.loadEntity(BkInteAcct.class, acctID);
		CashFlowCfg cashFlowCfg=acct.getCashFlowCfg();
		BankInterestModel model=new BankInterestModel();
		model.setId(acct.getId());
		model.setCode(acct.getMasterrecname());
		model.setName(acct.getMasterrecname());
		model.setBankid(acct.getBkinfo().getBankID());
		model.setPmtDelay(cashFlowCfg.getCpnDelayDateNum());
		model.setCpnDateConv(cashFlowCfg.getCpnDelayDateType());
		model.setIntCode(cashFlowCfg.getIntCode());
		model.setIntperiodconv(acct.getIntperiodconv());
		model.setCpnFreq(cashFlowCfg.getCpnFreq());
		model.setCpnMo(cashFlowCfg.getCpnMo());
		model.setCpnDate(cashFlowCfg.getCpnDate());
		
		return model;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createBankAcct(BkCashAcct bkCashAcct) {
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=comFuncService.loadPortfolio(bkCashAcct.getPortfolio().getId());
		bkCashAcct.setLastclose(portfolio.getLastTranClose());
		bkCashAcct.setLastBalDate(portfolio.getLastTranClose());
		
		boolean checkExist=bkCashAcctDao.checkBkCashAcctNameExist(bkCashAcct.getPortfolio().getId(), bkCashAcct.getBankdesc());
		if(checkExist){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.bkCashAcct_name_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.bkCashAcct_name_exist));
		}else{
			bkCashAcctDao.saveEntity(bkCashAcct);
			container.setEntityObj(bkCashAcct);
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createBankInteAcct(List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		
		CashFlowCfg cashFlowCfg=new CashFlowCfg();
		cashFlowCfg.setCpnDelayDateNum(Integer.valueOf(param_list.get(3).toString()));
		cashFlowCfg.setCpnDelayDateType(Integer.valueOf(param_list.get(4).toString()));
		cashFlowCfg.setIntCode(Integer.valueOf(param_list.get(5).toString()));
		cashFlowCfg.setCpnFreq(Integer.valueOf(param_list.get(7).toString()));
		if(param_list.get(8)==null){
			cashFlowCfg.setCpnMo(0);
		}else{
			cashFlowCfg.setCpnMo(Integer.valueOf(param_list.get(8).toString()));
		}
		cashFlowCfg.setCpnDate(Integer.valueOf(param_list.get(9).toString()));
		cashFlowCfgDao.saveEntity(cashFlowCfg);
		
		BkInteAcct acct=new BkInteAcct();
		acct.setCashflowtype("I");
		acct.setIntperiodconv(Integer.valueOf(param_list.get(6).toString()));
		acct.setMasterrecname(param_list.get(1).toString());
		acct.setBkinfo(new BkInfo(param_list.get(2).toString()));
		acct.setCashFlowCfg(cashFlowCfg);
		
		boolean checkNameExist=bkInteAcctDao.checkInteAcctNameExist(acct.getBkinfo().getBankID(), acct.getMasterrecname());
		if(checkNameExist){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.bkInteAcct_name_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.bkInteAcct_name_exist) + ": " + acct.getMasterrecname());
		}else{
			bkInteAcctDao.saveEntity(acct);
			container.setEntityObj(acct);
		}
		
		return container;
	}
	
	
	public List<BkCashAcct> getBankCashAcctByPortNum(String portnum) {
		return bkCashAcctDao.getBankCashAcctByPortNum(portnum);
	}
	
	public BkInteAcct loadBankInteAcctByID(int acctID){
		return bkInteAcctDao.loadEntity(BkInteAcct.class, acctID);
	}
	
	public HashMap<Integer, String> loadBankAcctCOA(int accid) {
		BkCashAcct acct=bkCashAcctDao.loadBankAcct(accid);
		HashMap<String,String> coaaIDNameHM=new HashMap<String, String>();
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<Coaa> coaaList=coaaDao.loadCoaaDetailsByChartNum(acct.getPortfolio().getCoaahdr());
			for(Coaa coa:coaaList){
				coaaIDNameHM.put(coa.getAccntnum(),coa.getAccntname());
			}
		}else{
			//兆尹的科目表，账套信息
			List<BokSubject> bokList=bokSubjectDao.loadBokSubjectBySetID(acct.getPortfolio().getCoaahdr());
			for(BokSubject bok:bokList){
				coaaIDNameHM.put(bok.getBokSubjectPK().getSubject_no(),bok.getSubject_name());
			}
		}
		
		HashMap<Integer,String> bankAcctHM=new HashMap<Integer, String>();
		
		//现金
		if(acct.getCashglcode()!=null){
			bankAcctHM.put(BankAccountType.Cash_Account, acct.getCashglcode()+"|"+coaaIDNameHM.get(acct.getCashglcode()));
		}
		if(acct.getDefaultoffset()!=null){
			bankAcctHM.put(BankAccountType.Cash_DefaultOffet, acct.getDefaultoffset()+"|"+coaaIDNameHM.get(acct.getDefaultoffset()));
		}
		if(acct.getCashrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.Cash_RealizedFxGainLoss, acct.getCashrealfxgnls()+"|"+coaaIDNameHM.get(acct.getCashrealfxgnls()));
		}
		/*if(acct.getCashrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.Cash_RealizedFxGainLoss, acct.getCashrealfxgnls()+"|"+coaaIDNameHM.get(acct.getCashrealfxgnls()));
		}*/
		if(acct.getCashunrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.Cash_UnRealizedFxGainLoss, acct.getCashunrealfxgnls()+"|"+coaaIDNameHM.get(acct.getCashunrealfxgnls()));
		}
		if(acct.getCashunrealfxoffset()!=null){
			bankAcctHM.put(BankAccountType.Cash_UnRealizedFxGainLossOffset, acct.getCashunrealfxoffset()+"|"+coaaIDNameHM.get(acct.getCashunrealfxoffset()));
		}
		if(acct.getCashunrealfxoffset()!=null){
			bankAcctHM.put(BankAccountType.Cash_UnRealizedFxGainLossOffset, acct.getCashunrealfxoffset()+"|"+coaaIDNameHM.get(acct.getCashunrealfxoffset()));
		}
		if(acct.getCashtruncationgain()!=null){
			bankAcctHM.put(BankAccountType.Cash_TruncationGain, acct.getCashtruncationgain()+"|"+coaaIDNameHM.get(acct.getCashtruncationgain()));
		}
		if(acct.getCashtruncationloss()!=null){
			bankAcctHM.put(BankAccountType.Cash_TruncationLoss, acct.getCashtruncationloss()+"|"+coaaIDNameHM.get(acct.getCashtruncationloss()));
		}
		
		//利息
		if(acct.getIntrecvgl()!=null){
			bankAcctHM.put(BankAccountType.Int_InterestReceived, acct.getIntrecvgl()+"|"+coaaIDNameHM.get(acct.getIntrecvgl()));
		}
		if(acct.getIntrcvpaygl()!=null){
			bankAcctHM.put(BankAccountType.Int_Rcpy, acct.getIntrcvpaygl()+"|"+coaaIDNameHM.get(acct.getIntrcvpaygl()));
		}
		if(acct.getIntrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.Int_RealizedFxGainLoss, acct.getIntrealfxgnls()+"|"+coaaIDNameHM.get(acct.getIntrealfxgnls()));
		}
		if(acct.getInttolerancegl()!=null){
			bankAcctHM.put(BankAccountType.Int_Tolerance, acct.getInttolerancegl()+"|"+coaaIDNameHM.get(acct.getInttolerancegl()));
		}
		if(acct.getIntunrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.Int_UnRealizedFxGainLoss, acct.getIntunrealfxgnls()+"|"+coaaIDNameHM.get(acct.getIntunrealfxgnls()));
		}
		if(acct.getIntunrealfxoffset()!=null){
			bankAcctHM.put(BankAccountType.Int_UnRealizedFxGainLossOffset, acct.getIntunrealfxoffset()+"|"+coaaIDNameHM.get(acct.getIntunrealfxoffset()));
		}
		if(acct.getInterestaccrued()!=null){
			bankAcctHM.put(BankAccountType.Int_InterestAccrued, acct.getInterestaccrued()+"|"+coaaIDNameHM.get(acct.getInterestaccrued()));
		}
		if(acct.getInterestaccoffset()!=null){
			bankAcctHM.put(BankAccountType.Int_InterestAccruedOffset, acct.getInterestaccoffset()+"|"+coaaIDNameHM.get(acct.getInterestaccoffset()));
		}
		
		//
		if(acct.getOdpaidgl()!=null){
			bankAcctHM.put(BankAccountType.OD_OverDraftPaid, acct.getOdpaidgl()+"|"+coaaIDNameHM.get(acct.getOdpaidgl()));
		}
		if(acct.getOdrcvpaygl()!=null){
			bankAcctHM.put(BankAccountType.OD_Rcpy, acct.getOdrcvpaygl()+"|"+coaaIDNameHM.get(acct.getOdrcvpaygl()));
		}
		if(acct.getOdrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.OD_Rcpy, acct.getOdrealfxgnls()+"|"+coaaIDNameHM.get(acct.getOdrealfxgnls()));
		}
		if(acct.getOdtolerancegl()!=null){
			bankAcctHM.put(BankAccountType.OD_Tolerance, acct.getOdtolerancegl()+"|"+coaaIDNameHM.get(acct.getOdtolerancegl()));
		}
		if(acct.getOdunrealfxgnls()!=null){
			bankAcctHM.put(BankAccountType.OD_UnRealizedFxGainLoss, acct.getOdunrealfxgnls()+"|"+coaaIDNameHM.get(acct.getOdunrealfxgnls()));
		}
		if(acct.getOdunrealfxoffset()!=null){
			bankAcctHM.put(BankAccountType.OD_UnRealizedFxGainLossOffset, acct.getOdunrealfxoffset()+"|"+coaaIDNameHM.get(acct.getOdunrealfxoffset()));
		}
		if(acct.getOdaccrued()!=null){
			bankAcctHM.put(BankAccountType.OD_InterestAccrued, acct.getOdaccrued()+"|"+coaaIDNameHM.get(acct.getOdaccrued()));
		}
		if(acct.getOdaccruedoffset()!=null){
			bankAcctHM.put(BankAccountType.OD_InterestAccruedOffset, acct.getOdaccruedoffset()+"|"+coaaIDNameHM.get(acct.getOdaccruedoffset()));
		}
		
		return bankAcctHM;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateBankacctGL(int acctid, int glid, String coaAccount) {
		BkCashAcct cashacct=bkCashAcctDao.loadBankAcct(acctid);
		
		switch(glid){
		case BankAccountType.Cash_Account:
			cashacct.setCashglcode(coaAccount);
			break;
		case BankAccountType.Cash_DefaultOffet:
			cashacct.setDefaultoffset(coaAccount);
			break;
		case BankAccountType.Cash_RealizedFxGainLoss:
			cashacct.setCashrealfxgnls(coaAccount);
			break;
		/*case BankAccountType.Cash_Tolerance:
			cashacct.setCashrealfxgnls(coaAccount);
			break;*/
		case BankAccountType.Cash_UnRealizedFxGainLoss:
			cashacct.setCashunrealfxgnls(coaAccount);
			break;
		case BankAccountType.Cash_UnRealizedFxGainLossOffset:
			cashacct.setCashunrealfxoffset(coaAccount);
			break;
		case BankAccountType.Cash_TruncationGain:
			cashacct.setCashtruncationgain(coaAccount);
			break;
		case BankAccountType.Cash_TruncationLoss:
			cashacct.setCashtruncationloss(coaAccount);
			break;
		case BankAccountType.Int_InterestReceived:
			cashacct.setIntrecvgl(coaAccount);
			break;
		case BankAccountType.Int_Rcpy:
			cashacct.setIntrcvpaygl(coaAccount);
			break;
		case BankAccountType.Int_RealizedFxGainLoss:
			cashacct.setIntrealfxgnls(coaAccount);
			break;
		case BankAccountType.Int_Tolerance:
			cashacct.setInttolerancegl(coaAccount);
			break;
		case BankAccountType.Int_UnRealizedFxGainLoss:
			cashacct.setIntunrealfxgnls(coaAccount);
			break;
		case BankAccountType.Int_UnRealizedFxGainLossOffset:
			cashacct.setIntunrealfxoffset(coaAccount);
			break;
		case BankAccountType.Int_InterestAccrued:
			cashacct.setInterestaccrued(coaAccount);
			break;
		case BankAccountType.Int_InterestAccruedOffset:
			cashacct.setInterestaccoffset(coaAccount);
			break;
		case BankAccountType.OD_OverDraftPaid:
			cashacct.setOdpaidgl(coaAccount);
			break;
		case BankAccountType.OD_Rcpy:
			cashacct.setOdrcvpaygl(coaAccount);
			break;
		case BankAccountType.OD_RealizedFxGainLoss:
			cashacct.setOdrealfxgnls(coaAccount);
			break;
		case BankAccountType.OD_Tolerance:
			cashacct.setOdtolerancegl(coaAccount);
			break;
		case BankAccountType.OD_UnRealizedFxGainLoss:
			cashacct.setOdunrealfxgnls(coaAccount);
			break;
		case BankAccountType.OD_UnRealizedFxGainLossOffset:
			cashacct.setOdunrealfxoffset(coaAccount);
			break;
		case BankAccountType.OD_InterestAccrued:
			cashacct.setOdaccrued(coaAccount);
			break;
		case BankAccountType.OD_InterestAccruedOffset:
			cashacct.setOdaccruedoffset(coaAccount);
			break;
		}
		
		bkCashAcctDao.updateEntity(cashacct);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void removeBankacctGL(int acctid, int glid) {
		BkCashAcct cashacct=bkCashAcctDao.loadBankAcct(acctid);
		String coaAccount=null;
		
		switch(glid){
		case BankAccountType.Cash_Account:
			cashacct.setCashglcode(coaAccount);
			break;
		case BankAccountType.Cash_DefaultOffet:
			cashacct.setDefaultoffset(coaAccount);
			break;
		case BankAccountType.Cash_RealizedFxGainLoss:
			cashacct.setCashrealfxgnls(coaAccount);
			break;
		/*case BankAccountType.Cash_Tolerance:
			cashacct.setCashrealfxgnls(coaAccount);
			break;*/
		case BankAccountType.Cash_UnRealizedFxGainLoss:
			cashacct.setCashunrealfxgnls(coaAccount);
			break;
		case BankAccountType.Cash_UnRealizedFxGainLossOffset:
			cashacct.setCashunrealfxoffset(coaAccount);
			break;
		case BankAccountType.Cash_TruncationGain:
			cashacct.setCashtruncationgain(coaAccount);
			break;
		case BankAccountType.Cash_TruncationLoss:
			cashacct.setCashtruncationloss(coaAccount);
			break;
		case BankAccountType.Int_InterestReceived:
			cashacct.setIntrecvgl(coaAccount);
			break;
		case BankAccountType.Int_Rcpy:
			cashacct.setIntrcvpaygl(coaAccount);
			break;
		case BankAccountType.Int_RealizedFxGainLoss:
			cashacct.setIntrealfxgnls(coaAccount);
			break;
		case BankAccountType.Int_Tolerance:
			cashacct.setInttolerancegl(coaAccount);
			break;
		case BankAccountType.Int_UnRealizedFxGainLoss:
			cashacct.setIntunrealfxgnls(coaAccount);
			break;
		case BankAccountType.Int_UnRealizedFxGainLossOffset:
			cashacct.setIntunrealfxoffset(coaAccount);
			break;
		case BankAccountType.Int_InterestAccrued:
			cashacct.setInterestaccrued(coaAccount);
			break;
		case BankAccountType.Int_InterestAccruedOffset:
			cashacct.setInterestaccoffset(coaAccount);
			break;
		case BankAccountType.OD_OverDraftPaid:
			cashacct.setOdpaidgl(coaAccount);
			break;
		case BankAccountType.OD_Rcpy:
			cashacct.setOdrcvpaygl(coaAccount);
			break;
		case BankAccountType.OD_RealizedFxGainLoss:
			cashacct.setOdrealfxgnls(coaAccount);
			break;
		case BankAccountType.OD_Tolerance:
			cashacct.setOdtolerancegl(coaAccount);
			break;
		case BankAccountType.OD_UnRealizedFxGainLoss:
			cashacct.setOdunrealfxgnls(coaAccount);
			break;
		case BankAccountType.OD_UnRealizedFxGainLossOffset:
			cashacct.setOdunrealfxoffset(coaAccount);
			break;
		case BankAccountType.OD_InterestAccrued:
			cashacct.setOdaccrued(coaAccount);
			break;
		case BankAccountType.OD_InterestAccruedOffset:
			cashacct.setOdaccruedoffset(coaAccount);
			break;
		}
		
		bkCashAcctDao.updateEntity(cashacct);
	}
	
	public String loadFxCurrencyByCashAcct(int cashacctID) {
		return bkCashAcctDao.loadCurIDByCashAcctID(cashacctID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public int createIntereRatio(int intAcctID, List<Object> intRatioList) {
		BkInteRatio ratio=new BkInteRatio();
		BkInteAcct inteacct=bkInteAcctDao.loadEntity(BkInteAcct.class, intAcctID);
		ratio.setBkInteAcct(inteacct);
		ratio.setStartdate(DateUtil.convertStr2Date2(intRatioList.get(0).toString()));
		ratio.setPrice(Double.valueOf(intRatioList.get(1).toString()));
		bkInteRatioDao.saveEntity(ratio);
		return ratio.getId();
	}
	
	@Override
	public List<BkInteRatio> loadRatioList(int intAcctID) {
		return bkInteRatioDao.loadBkInteRatio(intAcctID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteInteRatio(int ratioID) {
		bkInteRatioDao.deleteEntity(BkInteRatio.class, ratioID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public int updateIntereRatio(int rateID, List<Object> intRatioList){
		BkInteRatio ratio=bkInteRatioDao.loadEntity(BkInteRatio.class, rateID);
		ratio.setStartdate(DateUtil.convertStr2Date2(intRatioList.get(0).toString()));
		ratio.setPrice(Double.valueOf(intRatioList.get(1).toString()));
		bkInteRatioDao.updateEntity(ratio);
		return ratio.getId();
	}
}
