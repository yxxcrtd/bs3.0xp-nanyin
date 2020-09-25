package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SystemSelect;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.DBCR;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.Pages;
import com.jixu.dao.intf.StandardVoucherDetailsDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.mvc.control.model.VoucherDetailsView;
import com.jixu.mvc.control.model.VouchersModel;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.mvc.control.service.VoucherService;
import com.orm.obj.StandardVoucher;
import com.orm.obj.StandardVoucherDetails;
import com.orm.obj.Voucher;
import com.orm.obj.VoucherDetails;

@Service("voucherService")
public class VoucherServiceImpl implements VoucherService{
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Autowired
	private StandardVoucherDetailsDao standardVoucherDetailsDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	//private static Logger logger = Logger.getLogger(VoucherServiceImpl.class);
	@Override
	public Pages<VouchersModel> fetchVouchers(Pages<VouchersModel> pages, String portnum, Date startDate, Date endDate, int journalType, String startVoucherNum, String endVoucherNum, String secID, String curCode, String accountnum){
		
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		List<Voucher> vouchers=new ArrayList<Voucher>();
		endDate=DateUtil.getNextDay(endDate);
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<Voucher> page=voucherDao.searchPageHelperVouchers(PageHelper.startPage(pages.getCurrentPage(),pages.getPageMaxRecords()), portList, startDate, endDate, journalType, startVoucherNum, endVoucherNum, secID, curCode, accountnum);
			vouchers=page.getResult();
			pages.setTotalPages(page.getPages());
		}else{
			Pages<Voucher> pages1=voucherDao.searchVouchers(pages, portList, startDate, endDate, journalType, startVoucherNum, endVoucherNum, secID, curCode, accountnum);
			vouchers=pages1.getResultList();
			pages.setTotalPages(pages1.getTotalPages());
		}
		
		
		List<VouchersModel> voucherModelList=new ArrayList<VouchersModel>();
		
		accountnum=accountnum.trim();
		boolean addFlag=false;
		boolean accountnumFlag=false;
		
		
		for(Voucher voucher:vouchers){
			VouchersModel vm=new VouchersModel();
			List<VoucherDetails> vds=new ArrayList<VoucherDetails>();
			Set<VoucherDetails> vdsSet=voucher.getVoucherDetails();
			Iterator<VoucherDetails> vdsIt=vdsSet.iterator();
			addFlag=false;
			accountnumFlag=false; 
			while(vdsIt.hasNext()){
				VoucherDetails voucherDetails=vdsIt.next();
				if(curCode!=null && !curCode.equals("") && !curCode.equals("0")){
					if(voucherDetails.getLocalCurrency().equals(commfuncService.loadFxCurrencyCode(curCode))){
						addFlag=true;
					}else{
						addFlag=false;
					}
				}else{
					addFlag=true;
				}
		
				if(accountnum.equals(voucherDetails.getAccountNum())){
					accountnumFlag=true;
				}else if(accountnum.equals("")){
					accountnumFlag=true;
				}
				
				if(addFlag){
					vds.add(voucherDetails);
				}
			}
			if(vds.size()>0 && accountnumFlag){
				vm.setVoucher(voucher);
				vm.setVoucherDetailsList(vds);
				voucherModelList.add(vm);
			}
		}
		
		pages.setResultList(voucherModelList);
		return pages;
	}
	
	public List<VoucherDetailsView> fetchVoucherDetails(String voucherNum){
		if(JixuUserUtil.jixuUserSystemFlag()){
			return voucherDetailsDao.searchVoucherDetails(voucherNum);
		}else{
			return voucherDetailsDao.searchVoucherDetailsBokSubject(voucherNum);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createManualVoucher(int userID,String portnum, String ledgerDate, List<Object> fields) {
		
		String ledgerMsg=MsgTemplate.LedgerEntry_Header+"(";
		String subMsgTmp="";
		String desc="";
		for(Object obj:fields){
			List<String> feeArrList=(List<String>)obj;
			int drflg=Integer.valueOf(feeArrList.get(0));
			
			String acctnum=feeArrList.get(1).split("\\|")[0];
			//String funcCode=feeArrList.get(2);
			String drfunc="";
			String crfunc="";
			String drlocal="";
			String crlocal="";
			
			if(drflg==DBCR.receive){
				drlocal=feeArrList.get(5);
				drfunc=feeArrList.get(3);
			}else{
				crlocal=feeArrList.get(5);
				crfunc=feeArrList.get(3);
			}
			
			String localCode=feeArrList.get(4);
			desc=feeArrList.get(6);
			
			//String price=feeArrList.get(7);
			String fxrate=feeArrList.get(8);
			
			
			String submsg=msgPackage.ledgerSubManual(acctnum, drfunc, crfunc, localCode, drlocal, crlocal, fxrate);
			subMsgTmp=subMsgTmp+submsg+",";
		}
		if(subMsgTmp.length()>1){
			subMsgTmp=subMsgTmp.substring(0,subMsgTmp.length()-1);
		}
		ledgerMsg=ledgerMsg+subMsgTmp+")";
		
		String msg=msgPackage.manualVoucher("ADD", "", portnum, ledgerDate, ledgerMsg, desc);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setUserID(userID);
		msgAssemble.genMsgContainer(msg,msgcontainer);
		
		ResultContainer resultcontainer=new ResultContainer();
		resultcontainer.setObjID(msgcontainer.getObjID());
		resultcontainer.setCorrectFlag(msgcontainer.isCorrectFlag());
		resultcontainer.setErrorMsg(msgcontainer.getErrorMsg());
		
		return resultcontainer;
	}

	//根据投资组合和记账日期 查询voucher表中的当天凭证    支持多投资组合
	@Override
	public ResultContainer testCaseDailyVoucher(String[] portnum, Date ledgerDate) {
		//查询当天的凭证
		List<VouchersModel> list=voucherDao.testCaseDailyVoucher(portnum, ledgerDate);
		List<VoucherDetails> vdlist=new ArrayList<VoucherDetails>();
		//查询标准表的标准凭证
		HashMap<String, StandardVoucherDetails> map=new HashMap<String, StandardVoucherDetails>();
		List<StandardVoucherDetails> arrayList=standardVoucherDetailsDao.testCaseDailyVoucherDetails(portnum,ledgerDate);
		for (StandardVoucherDetails svd : arrayList) {
			StandardVoucher svr=svd.getVoucher();
			if(svd.getSecurityID()==null){
				map.put(svr.getPortfolio()+"_"+svd.getAccountNum()+"_"+svr.getJournalSourceCode()+"_"+svd.getCr_func()+"_"+svd.getDr_func(), svd);
			}else{
				map.put(svr.getPortfolio()+"_"+svd.getAccountNum()+"_"+svr.getJournalSourceCode()+"_"+svd.getCr_func()+"_"+svd.getDr_func()+"_"+svd.getSecurityID(), svd);
			}
		}
		StandardVoucherDetails svd=null;
		ResultContainer resultcontainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		for (VouchersModel vm : list) {
			Voucher v=vm.getVoucher();
			vdlist=vm.getVoucherDetailsList();
			for (VoucherDetails vd : vdlist) {
				if(vd.getSecurityID()==null){  
					svd=map.get(v.getPortfolio()+"_"+vd.getAccountNum()+"_"+v.getJournalSourceCode()+"_"+vd.getCr_func()+"_"+vd.getDr_func());
				}else{
					svd=map.get(v.getPortfolio()+"_"+vd.getAccountNum()+"_"+v.getJournalSourceCode()+"_"+vd.getCr_func()+"_"+vd.getDr_func()+"_"+vd.getSecurityID());
				}
				if(svd==null){  //返回错误的凭证信息
					ResultContainer container=new ResultContainer();
					container.setCorrectFlag(false);
					container.setErrorMsg("做账日期:"+DateUtil.convertD2YYYY_MM_DD(ledgerDate)+"  投资组合:"+v.getPortfolio()+"  科目号:"+vd.getAccountNum()
							+"  凭证类型:"+JournalTypes.getJournalTypeHM().get(v.getJournalSourceCode())
							+"  贷原币:"+vd.getCr_func()+"  证券代码:"+vd.getSecurityID());
					containerList.add(container);
				}
			}
		}
		resultcontainer.setEntityObj(containerList);
		return resultcontainer;
	}
}
