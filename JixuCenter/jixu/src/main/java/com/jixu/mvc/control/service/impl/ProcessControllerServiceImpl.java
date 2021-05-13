package com.jixu.mvc.control.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jixu.common.exception.NewFundException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.NetCategory;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.NewFundType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.NewFundContentDao;
import com.jixu.dao.intf.NewFundDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.dao.intf.VoucherDetailsDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgError;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.job.obj.NavAssetModel;
import com.jixu.job.service.ContractualSetService;
import com.jixu.mvc.control.service.BankCfgService;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.MarketDataService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.mvc.control.service.ProcessControllerService;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.post.service.PostIssueService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.BkInteAcct;
import com.orm.obj.Broker;
import com.orm.obj.Coaa;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.NewFund;
import com.orm.obj.NewFundContent;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		ProcessControllerServiceImpl
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 上午11:26:53 	
 * @Description		[]  
 */

@Service("processControlerService")
public class ProcessControllerServiceImpl implements ProcessControllerService{
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private VoucherDetailsDao voucherDetailsDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Resource(name="portCfgService")
	private PortfolioCfgService portfolioService;
	
	@Resource(name="bankCfgService")
	private BankCfgService bankCfgService;
	
	@Resource(name="configService")
	private ConfigService configService;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;

	@Resource(name="postIssues")
	private PostIssueService postIssue;
	
	@Resource(name="contractualSetService")
	private ContractualSetService setService;;
	
	@Resource(name="marketDataService")
	private MarketDataService marketDataService;
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private NewFundDao newFundDao;
	
	@Autowired
	private NewFundContentDao newFundContentDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	private String excelFormatError="文件格式错误,必须为excel格式的文件";
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessControllerServiceImpl.class);
	
	@Override
	public NewFund loadNewFund(int configID) {
		NewFund newfund=newFundDao.loadEntity(NewFund.class, configID);
		return newfund;
	}
	
	@Override
	public List<NewFund> loadNewFundList(int clientID, int newFundType) {
		return newFundDao.loadNewFundList(clientID, newFundType);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createNewFund(NewFund newfund) {
		newFundDao.saveEntity(newfund);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void saveNewFundConfig(int newFundID, String newFundContent) {
		NewFundContent newfundContent=newFundContentDao.loadNewFundContent(newFundID);
		if(newfundContent==null){
			newfundContent=new NewFundContent();
			newfundContent.setNewFundID(newFundID);
			newfundContent.setNewFundContent(newFundContent);
			newFundContentDao.saveEntity(newfundContent);
		}else{
			newfundContent.setNewFundContent(newFundContent);
			newFundContentDao.updateEntity(newfundContent);
		}
	}
	
	@Override
	public NewFundContent loadNewFundContent(int newFundID) {
		return newFundContentDao.loadNewFundContent(newFundID);
	}
	
	private boolean checkNewFundExecuteAble(String newfund){
		if(newfund==null){
			return false;
		}
		HashMap<String, Object> result = (HashMap<String, Object>) JsonUtil.stringToMap(newfund);
		HashMap<String, Object> porthm=(HashMap<String, Object>)result.get("port");
		if(porthm.size()==0){
			logger.debug(porthm.toString());
			return false;
		}
		HashMap<String, Object> banksHM=(HashMap<String, Object>)result.get("bankAccts");
		if(banksHM.size()==0){
			logger.debug(banksHM.toString());
			return false;
		}
		HashMap<String, Object> secAcctHM=(HashMap<String, Object>)result.get("secAccts");
		if(secAcctHM.size()==0){
			logger.debug(secAcctHM.toString());
			return false;
		}
		return true;
	}
	
	private boolean checkOldFundExecuteAble(String newfund){
		if(newfund==null){
			return false;
		}
		HashMap<String, Object> result = (HashMap<String, Object>) JsonUtil.stringToMap(newfund);
		HashMap<String, Object> tbhm=(HashMap<String, Object>)result.get("tb");
		if(tbhm.size()==0){
			logger.debug(tbhm.toString());
			return false;
		}else{
			List<Object> tbList=(List<Object>)tbhm.get("info");
			if(tbList.size()==0){
				logger.debug(tbhm.toString());
				return false;
			}
		}
		HashMap<String, Object> secPosHM=(HashMap<String, Object>)result.get("secPos");
		if(secPosHM.size()==0){
			logger.debug(secPosHM.toString());
			return false;
		}else{
			List<Object> secPosList=(List<Object>)secPosHM.get("info");
			if(secPosList.size()==0){
				logger.debug(tbhm.toString());
				return false;
			}
		}
		return true;
	}
	
	
	
	
	/**
	 * @param exeDate	执行日期
	 * @param initShares	初始化份额
	 * @param initType		执行类型	0. 新基金上线	1.老基金迁移
	 * @param newFundContent
	 * @return
	 */
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public ResultContainer executeNewFund(Date exeDate, String newFundContent) throws NewFundException{
		ResultContainer container=new ResultContainer();
		
		HashMap<String, Object> newObjectHM=new HashMap<String, Object>();
		HashMap<String, Object> result = (HashMap<String, Object>) JsonUtil.stringToMap(newFundContent);
		
		if(!checkNewFundExecuteAble(newFundContent)){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.newFund_filedNotComplete);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.newFund_filedNotComplete));
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		
		HashMap<String, Object> porthm=(HashMap<String, Object>)result.get("port");
		List<Object> portlist=(List<Object>)porthm.get("data");
		container=portfolioService.createPortfolio(portlist);
		if(!container.isCorrectFlag()){
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		
		PortfolioHdr portfolio=(PortfolioHdr)container.getEntityObj();
		
		
		//解析利率
		HashMap<String, Object> interests=(HashMap<String, Object>)result.get("interests");
		if(interests.size()>0){
			Iterator<Entry<String, Object>> intIter=interests.entrySet().iterator();
			while(intIter.hasNext()){
				Entry<String, Object> InteEntry=intIter.next();
				HashMap<String, Object> innerInteHM=(HashMap<String, Object>)InteEntry.getValue();
				//获取新建利率账号:
				List<Object> newInteAcctList=(List<Object>)innerInteHM.get("info");
				String tmpID=newInteAcctList.get(0).toString();
				newInteAcctList.remove(0);
				newInteAcctList.add(0, newInteAcctList.get(1));
				container =bankCfgService.createBankInteAcct(newInteAcctList);
				if(!container.isCorrectFlag()){
					throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
				}
				
				BkInteAcct inteacct=(BkInteAcct)container.getEntityObj();
				newObjectHM.put(tmpID, inteacct);
				//创建利率
				List<Object> newInteRatioList=(List<Object>)innerInteHM.get("rates");
				if(newInteRatioList!=null){
					for(Object inteRatio:newInteRatioList){
						List<Object> newInteRatio=(List<Object>)inteRatio;
						bankCfgService.createIntereRatio(inteacct.getId(), newInteRatio);
					}
				}
			}
		}
		
		//解析银行账户
		HashMap<String, Object> banksHM=(HashMap<String, Object>)result.get("bankAccts");
		List<Integer> defaultCashAcctList=new ArrayList<Integer>();
		if(banksHM.size()>0){
			Iterator<Entry<String, Object>> banksIter=banksHM.entrySet().iterator();
			int num=0;
			while(banksIter.hasNext()){
				Entry<String, Object> basnkEntry=banksIter.next();
				HashMap<String, Object> innerBankHM=(HashMap<String, Object>)basnkEntry.getValue();
				//获取银行账户信息
				List<Object> newBkAcctList=(List<Object>)innerBankHM.get("info");
				
				BkCashAcct acct=new BkCashAcct();
				acct.setExternalacctnumber(newBkAcctList.get(1).toString());
				acct.setBankdesc(newBkAcctList.get(3).toString());
				acct.setBkinfo(new BkInfo(newBkAcctList.get(2).toString()));
				acct.setSttlmntcurrdefault("");	//to-do-pending
				acct.setFxcurrency(newBkAcctList.get(4).toString());
				acct.setStatus(1);
				acct.setSttlmntcurrdefault(newBkAcctList.get(5).toString());
				acct.setPortfolio(portfolio);
				if(newBkAcctList.get(6).equals("0")){
					acct.setIntCshflwMasterRec(0);
				}else{
					acct.setIntCshflwMasterRec(((BkInteAcct)newObjectHM.get(newBkAcctList.get(6))).getId());
				}
				acct.setLastclose(exeDate);
				acct.setLastBalDate(exeDate);
				
				if(acct.getIntCshflwMasterRec()!=0 && acct.getBkinfo().getBankID()!=((BkInteAcct)newObjectHM.get(newBkAcctList.get(6))).getBkinfo().getBankID()){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.bkAcct_InteAcct_BkNotMatch);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.bkAcct_InteAcct_BkNotMatch));
					throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
					
				}
				
				container=bankCfgService.createBankAcct(acct);
				if(!container.isCorrectFlag()){
					throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
				}
				
				newObjectHM.put(newBkAcctList.get(0).toString(), acct);
				
				List<Object> newBkAcctCoaList=(List<Object>)innerBankHM.get("mapping");
				if(newBkAcctCoaList!=null){
					for(Object bkcoaObj:newBkAcctCoaList){
						List<String> bkcoaList=(List<String>)bkcoaObj;
						int glid=Integer.valueOf(bkcoaList.get(1));
						String glacct=bkcoaList.get(2).split("\\|")[0];
						bankCfgService.updateBankacctGL(acct.getId(), glid, glacct);
					}
				}
				
				//设置默认银行账户
				if(newBkAcctList.size()==8){
					int defaultflag=(Integer)newBkAcctList.get(7);
					if(defaultflag==1){
						defaultCashAcctList.add(acct.getId());
					}
				}else if(num==0){
					defaultCashAcctList.add(acct.getId());
				}
				
				num++;
			}
		}
		
		//证券账户
		HashMap<String, Object> secAcctHM=(HashMap<String, Object>)result.get("secAccts");
		
		//默认证券账户
		String defaultSecAcct="0";
		
		if(secAcctHM.size()>0){
			Iterator<Entry<String, Object>> secAcctIter=secAcctHM.entrySet().iterator();
			int num=0;
			while(secAcctIter.hasNext()){
				Entry<String, Object> secAcctEntry=secAcctIter.next();
				HashMap<String, Object> innersecAcctHM=(HashMap<String, Object>)secAcctEntry.getValue();
				//获取证券账户信息
				List<Object> newSecAcctList=(List<Object>)innersecAcctHM.get("info");
				
				SecAcct secacct=new SecAcct();
				secacct.setAcctCode(newSecAcctList.get(1).toString());	//to-do pending
				secacct.setBrokerCode(newSecAcctList.get(2).toString());
				secacct.setAcctDesc(newSecAcctList.get(3).toString());
				secacct.setPortfolio(portfolio.getId());
				secacct.setGlGrop(portfolio.getCoaaGroup());
				
				container=configService.createSecAcct(portfolio, secacct);
				if(!container.isCorrectFlag()){
					throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
				}
				
				//把生成的银行账户实例放入哈希表
				newObjectHM.put(newSecAcctList.get(0).toString(), secacct);
				
				List<String> newSecCashLinkList=(List<String>)newSecAcctList.get(4);
				if(newSecCashLinkList.size()>0){
					for(String bankacct:newSecCashLinkList){
						BkCashAcct cashacct=(BkCashAcct)newObjectHM.get(bankacct);
						cashacct.setSecAcctCode(secacct.getAcctCode());
						bkCashAcctDao.updateEntity(cashacct);
						
						//设置默认现金账户
						for(int cashacctid:defaultCashAcctList){
							if(cashacctid==cashacct.getId()){
								secacct.setDefaultCashAcct(cashacctid);
								secAcctDao.updateEntity(secacct);
								break;
							}
						}
					}
				}
				
				if(newSecAcctList.size()==6){
					if((Integer)newSecAcctList.get(5)==1){
						defaultSecAcct=secacct.getAcctID();
					}
				}else if(num==0){
					defaultSecAcct=secacct.getAcctID();
				}
				num++;
			}
		}
		portfolio.setDefaultSecAcct(defaultSecAcct);
		portfolioHdrDao.updateEntity(portfolio);
		
		//费用配置
		HashMap<String, Object> expenseHM=(HashMap<String, Object>)result.get("expenses");
		if(expenseHM.size()>0){
			Iterator<Entry<String, Object>> expensetIter=expenseHM.entrySet().iterator();
			while(expensetIter.hasNext()){
				Entry<String, Object> expenseEntry=expensetIter.next();
				HashMap<String, Object> innersecAcctHM=(HashMap<String, Object>)expenseEntry.getValue();
				
				//获取费用信息
				List<Object> newExpenseList=(List<Object>)innersecAcctHM.get("info");
				newExpenseList.remove(0);
				container=portfolioService.savePortExpense(portfolio.getId(), newExpenseList);
				if(!container.isCorrectFlag()){
					throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
				}
			}
		}
		
		//接口配置
		HashMap<String, Object> interfaceHM=(HashMap<String, Object>)result.get("interfaces");
		if(interfaceHM.size()>0){
			Iterator<Entry<String, Object>> interfaceIter=interfaceHM.entrySet().iterator();
			while(interfaceIter.hasNext()){
				Entry<String, Object> interfaceEntry=interfaceIter.next();
				HashMap<String, Object> innerInterfaceHM=(HashMap<String, Object>)interfaceEntry.getValue();
				
				//获取接口映射配置信息
				List<Object> newInterfaceMapList=(List<Object>)innerInterfaceHM.get("info");
				newInterfaceMapList.remove(0);
				portfolioService.createPortInterfaceMap(portfolio.getId(), newInterfaceMapList);
			}
		}
		
		
		return container;
	}
	
	/**
	 * 执行老基金期初
	 * @param exeDate	执行日期
	 * @param initShares	初始份额
	 * @param newFundContent
	 * @param portnum
	 * @param userID
	 * @return
	 * @throws NewFundException
	 */
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public ResultContainer executeOldFundQiChu(Date exeDate, double initShares, String newFundContent, String portnum, int userID) throws NewFundException{
		ResultContainer container=new ResultContainer();
		
		HashMap<String, Object> result = (HashMap<String, Object>) JsonUtil.stringToMap(newFundContent);
		
		if(!checkOldFundExecuteAble(newFundContent)){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.oldFund_filedNotComplete);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.oldFund_filedNotComplete));
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		
		
		PortfolioHdr portfolio=commService.loadPortfolio(portnum);
		Date initDate=portfolio.getNextTranClose();
		if(exeDate.before(initDate)){
			throw new NewFundException(ReturnResult.oldfund_exeDateBeforeInitDate, ReturnResult.getResultHM().get(ReturnResult.oldfund_exeDateBeforeInitDate));
		}
		
		int countnum=voucherDao.getVoucherCountByPortnum(portfolio.getId());
		if(countnum>0){
			throw new NewFundException(ReturnResult.oldfund_voucher_exist, ReturnResult.getResultHM().get(ReturnResult.oldfund_voucher_exist));
		}
		
		countnum=tranBasicDao.getTranBasicCountByPortnum(portfolio.getId());
		if(countnum>0){
			throw new NewFundException(ReturnResult.oldfund_tran_exist, ReturnResult.getResultHM().get(ReturnResult.oldfund_tran_exist));
		}
		
		//创建科目余额
		HashMap<String, Object> tbHM=(HashMap<String, Object>)result.get("tb");
		container.setUserID(userID);
		HashMap<String, Double[]> coaBalanceHM=createCoaBalance(container, portfolio, exeDate, tbHM);
		
		if(container.isCorrectFlag()){
			//创建银行现金
			createCashPosition(container, portfolio, exeDate, coaBalanceHM);
			
			if(container.isCorrectFlag()){
				HashMap<String, Object> secPositionHM=(HashMap<String, Object>)result.get("secPos");
				container.setUserID(userID);
				container=createSecPosition(container, portfolio, exeDate, secPositionHM,initShares);
					
				//创建应收应付
				if(container.isCorrectFlag()){
					HashMap<String, Object> rcpyHM=(HashMap<String, Object>)result.get("rcpy");
					container.setUserID(userID);
					createRcpy(container, portfolio, exeDate, rcpyHM);
				}
			}
		}
		
		if(!container.isCorrectFlag()){
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		
		return container;
	}
	
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public ResultContainer executeNewFundProcess(int userID, int newFundID, Date exeDate,double initShares) throws NewFundException{
		NewFund baseNewFund=newFundDao.loadEntity(NewFund.class, newFundID);
		NewFundContent newfund=loadNewFundContent(newFundID);
		ResultContainer container=new ResultContainer();
		
		try{
			
			if(newfund.getNewFundContent()==null){
				throw new NewFundException(ReturnResult.newFund_filedNotComplete, ReturnResult.getResultHM().get(ReturnResult.newFund_filedNotComplete));
			}
			
			if(baseNewFund.getNewFundType()==NewFundType.newFund.getIndex()){
				//新基金上线
				container=executeNewFund(exeDate, newfund.getNewFundContent());
			}else{
				//老基金期初
				container=executeOldFundQiChu(exeDate, initShares, newfund.getNewFundContent(), baseNewFund.getPortnum(), userID);
			}
			
			if(!container.isCorrectFlag()){
				throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
			}
			
			
			baseNewFund.setExecuteFlag(true);
			newFundDao.updateEntity(baseNewFund);
		}catch(NewFundException nfe){
			logger.error("Error", nfe);
			throw nfe;
		}
		
		return container;
	}
	
	/**
	 * 创建科目余额表
	 * @param container
	 * @param portnum
	 * @param initDate
	 * @param tbHM
	 * @throws NewFundException
	 */
	private HashMap<String, Double[]> createCoaBalance(ResultContainer container, PortfolioHdr portfolio, Date initDate, HashMap<String, Object> tbHM) throws NewFundException{
		List<Object> coaArrList=(List<Object>)tbHM.get("info");
		
		String ledgerMsg=MsgTemplate.LedgerEntry_Header+"(";
		String subMsgTmp="";
		String desc=ReturnResult.getDicHM().get(ReturnResult.newFundPeriodStart);
		HashMap<String, Double[]> coaBalanceHM=new HashMap<String, Double[]>();
				
		for(Object coaObj:coaArrList){
			String drfunc="";
			String crfunc="";
			String drlocal="";
			String crlocal="";
			List<String> coaList=(List<String>)coaObj;
			double local=Double.valueOf(coaList.get(3).replaceAll(",",""));
			double func=Double.valueOf(coaList.get(4).replaceAll(",",""));
			String acctnum=coaList.get(5).split("\\|")[0];
			
			if(func>=0){
				drfunc=String.valueOf(func);
				drlocal=String.valueOf(local);
			}else{
				crfunc=String.valueOf(func*-1);
				crlocal=String.valueOf(local*-1);
			}
			
			String fxrate="1";
			String localCode="1";//todo-pending
			
			//先保持原币为0,采用客户数据测试，没有很好的维护原币，所以导入后原币不平
			//todo-pending
			String submsg=msgPackage.ledgerSubManual(acctnum, drfunc, crfunc, localCode, "0", "0", fxrate);
			subMsgTmp=subMsgTmp+submsg+",";
			Double[] doubleReturn=new Double[2];
			doubleReturn[0]=local;
			doubleReturn[1]=func;
			coaBalanceHM.put(acctnum, doubleReturn);
		}
				
		if(subMsgTmp.length()>1){
			subMsgTmp=subMsgTmp.substring(0,subMsgTmp.length()-1);
		}
		ledgerMsg=ledgerMsg+subMsgTmp+")";
		
		String msg=msgPackage.manualVoucher("ADD", "", String.valueOf(portfolio.getId()), DateUtil.convertD2YYYYMMDD(initDate), ledgerMsg, desc);
		MsgContainer msgcontainer=new MsgContainer();
		msgcontainer.setUserID(container.getUserID());
		msgAssemble.genMsgContainer(msg,msgcontainer);
		container=msgcontainer;
		if(!container.isCorrectFlag()){
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		
		return coaBalanceHM;
	}
	
	private void createCashPosition(ResultContainer container, PortfolioHdr portfolio, Date initDate, HashMap<String, Double[]> coaBalanceHM) throws NewFundException{
		String portnum=portfolio.getId();
		List<BkCashAcct> bkcashacctList=bkCashAcctDao.loadBkCashAcctByPortnum(portnum);
		String tradeDate=DateUtil.convertD2YYYYMMDD(initDate);
		String settleDate=tradeDate;
		
		
		double localbal=0;
		double funcbal=0;
		int dbcr;
		boolean cashFlag=false;
		for(BkCashAcct cashacct:bkcashacctList){
			if(coaBalanceHM.containsKey(cashacct.getCashglcode())){
				localbal=coaBalanceHM.get(cashacct.getCashglcode())[0];
				funcbal=coaBalanceHM.get(cashacct.getCashglcode())[1];
				if(localbal != 0){
					if(localbal>0){
						dbcr=DBCR.receive;
					}else{
						dbcr=DBCR.pay;
					}
					localbal=Math.abs(localbal);
					funcbal=Math.abs(funcbal);
					
					String msg=msgPackage.cashContrib("ADD", "", String.valueOf(portnum), tradeDate, settleDate, String.valueOf(cashacct.getId()), String.valueOf(localbal), String.valueOf(funcbal), String.valueOf(dbcr));
					MsgContainer msgcontainer=new MsgContainer();
					msgAssemble.genMsgContainer(msg,msgcontainer);
					container=msgcontainer;
					if(!msgcontainer.isCorrectFlag()){
//						TranBasic ta=(TranBasic)msgcontainer.getEntityObj();
//						postIssue.postIssue(ta.getId(), container);
//						cashFlag=true;
//					}else{
						throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
					}
				}
			}
		}
		if(!cashFlag){
			container.setCorrectFlag(false);
			container.setErrorMsg("银行账户没有现金导入");
		}
		//setService.settleToCash(portnum, settleDate);
	}
	
	/**
	 * 创建证券头寸
	 * @param secPositionHM
	 */
	private ResultContainer createSecPosition(ResultContainer container, PortfolioHdr portfolio, Date initDate, HashMap<String, Object> secPositionHM,double initShares) throws NewFundException{
		int userID=container.getUserID();
		
		List<Object> secPosArrList=(List<Object>)secPositionHM.get("info");
		HashMap<String, Object> newSMFHM=(HashMap<String, Object>)secPositionHM.get("newSecs");
		container=createSMF(container, newSMFHM);

		if(!container.isCorrectFlag()){
			return container;
		}
		
		String portnum=portfolio.getId();
		for(Object secPosObj:secPosArrList){
			List<String> secPosList=(List<String>)secPosObj;
			Date expireDate=DateUtil.convertStr2Date2(secPosList.get(3));
			double amount=Double.valueOf(secPosList.get(4));
			double cashlocal=Double.valueOf(secPosList.get(6));
			double accruedlocal=Double.valueOf(secPosList.get(7));
			//double cashfunc=Double.valueOf(secPosList.get(8));
			//String accruedfunc=secPosList.get(9);
			double alRate=Double.valueOf(secPosList.get(10));
			int valueMathod=Integer.valueOf(secPosList.get(11));
			String secid=secPosList.get(12).split("\\|")[0];
			String secacctID=secPosList.get(13);
			
			
			IssueInfo sec=issueInfoDao.loadSecurity(secid);
			if(sec!=null){
				int managerGroupID=portfolio.getManagerGroup();
				List<Managers> managersList=configService.loadManagersListByGroupID(managerGroupID);
				int managerID=0;
				if(managersList.size()>0){
					managerID=managersList.get(0).getId();
				}else{
					throw new NewFundException(ReturnResult.manager_notexist, ReturnResult.getResultHM().get(ReturnResult.manager_notexist));
				}
				
				SecAcct secacct=secAcctDao.loadEntity(SecAcct.class, Integer.valueOf(secacctID));
				String brokerID=secacct.getBrokerCode();
				List<BkCashAcct> bkacctList=configService.loadBkCashAcctBySecAcct(secacct.getAcctID());
				int bkcashID=0;
				for(BkCashAcct acct:bkacctList){
					if(acct.getFxcurrency().equals(sec.getPriCur())){
						bkcashID=acct.getId();
						break;
					}
				}
				
				if(bkcashID==0){
					container.setCorrectFlag(false);
					container.setErrorCode(ReturnResult.oldfund_sec_bkacct_notmatch);
					container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.oldfund_sec_bkacct_notmatch)+": "+sec.getSecurityid());
					return container;
				}
				
				//估值方法，默认1，公允价值法
				if(valueMathod!=2){
					//如果值不是2,则是公允价值发
					valueMathod=ValuationType.FV;
				}
				
				//银行账户,券商等都采用默认第一个查询出来的数据
				HashMap<String, Object> msgHM=msgPackage.secContribution("ADD", "", initDate, portnum, secid, TradeMarket.level2, brokerID, secacct.getAcctID(), 
						bkcashID, managerID, amount, cashlocal, accruedlocal, alRate, expireDate, valueMathod);

				
				if(msgHM!=null){
					MsgContainer msgContainer=new MsgContainer();
					msgContainer.setFieldsHM(msgHM);
					msgContainer.setUserID(userID);
					container=tradeService.operateTran(msgContainer);
					if(!container.isCorrectFlag()){
						throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
					}
				}
			}else{
				throw new NewFundException(ReturnResult.newfund_sec_notexist, 
						ReturnResult.getResultHM().get(ReturnResult.newfund_sec_notexist)+": " + secid);
			}
		}
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portnum);
		List<NavAssetModel> netAssetList=voucherDetailsDao.getNetAsset(portList, initDate);
		ConcurrentHashMap<Integer, Double> hashMap=new ConcurrentHashMap<Integer, Double>();
		for (NavAssetModel navAssetModel : netAssetList) {
			hashMap.put(navAssetModel.getCategoryType(), navAssetModel.getCategoryAmount());
		}
		double assets=hashMap.get(NetCategory.Assets)==null?0:hashMap.get(NetCategory.Assets);//资产
		double liabilitis=hashMap.get(NetCategory.Liabilitis)==null?0:hashMap.get(NetCategory.Liabilitis);//负债
		double principal=RoundUtil.roundMoney2Dec(assets-(-1*liabilitis));//申购金额=资产-负债	
		double amount=initShares;
		String secacctID=portfolio.getDefaultSecAcct();
		SecAcct acct=configService.loadSecAcct(secacctID);
		int bkcashID=acct.getDefaultCashAcct();
		if(bkcashID==0){
			container.setCorrectFlag(false);
			container.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, "Cash Acct: " + secacctID));
			return container;
		}
		double price=RoundUtil.roundMoney2Dec(principal/amount);
		String tradeDate=DateUtil.convertD2YYYYMMDD(initDate);
		HashMap<String, Object> msgHM=msgPackage.shareTran("add", String.valueOf(TransTypes.Share_init), String.valueOf(portnum), tradeDate, tradeDate,String.valueOf(secacctID),
				String.valueOf(bkcashID),String.valueOf(1), String.valueOf(price), String.valueOf(amount), String.valueOf(principal), "", 
				String.valueOf(1), String.valueOf(1),String.valueOf(principal),String.valueOf(1),null, null);
		MsgContainer msgContainer=new MsgContainer();
		msgContainer.setFieldsHM(msgHM);
		msgContainer.setUserID(userID);
		container=tradeService.operateTran(msgContainer);
		if(!container.isCorrectFlag()){
			throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
		}
		return container;
	}
	
	private ResultContainer createSMF(ResultContainer container, HashMap<String, Object> smfHM){
		int userID=container.getUserID();
		if(smfHM.size()>0){
			Iterator<Entry<String, Object>> smfIter=smfHM.entrySet().iterator();
			while(smfIter.hasNext()){
				HashMap<String, Object> fieldsMH=(HashMap<String, Object>)smfIter.next().getValue();
				fieldsMH.remove("id");
				fieldsMH.put(MsgFields.header, "smfOperation");
				fieldsMH.put(MsgFields.operation, "add");
				MsgContainer msgContainer=new MsgContainer();
				msgContainer.setUserID(userID);
				msgContainer.setFieldsHM(fieldsMH);
				container=msgFactory.executeMsg(msgContainer);
			}
		}
		return container;
	}
	
	/**
	 * 老基金,创建应收应付
	 * @param container
	 * @param portfolio
	 * @param initDate
	 * @param rcpyHM
	 * @throws NewFundException
	 */
	private void createRcpy(ResultContainer container, PortfolioHdr portfolio, Date initDate, HashMap<String, Object> rcpyHM) throws NewFundException{
		int userID=container.getUserID();
		List<Object> rcpyArrList=(List<Object>)rcpyHM.get("info");
		String portnum=portfolio.getId();
		Date tradeDate=initDate;
		for(Object rcpyObj:rcpyArrList){
			List<String> rcpyList=(List<String>)rcpyObj;
			String settleDateStr=rcpyList.get(1).replaceAll("-", "");
			Date settleDate=DateUtil.convertStr2Date(settleDateStr);
			String cashacct=rcpyList.get(8);
			String funccash=rcpyList.get(6);
			String localcash=rcpyList.get(5);
			String dbcr=rcpyList.get(2);
			String glacct=rcpyList.get(7).split("\\|")[0];
			String desc=rcpyList.get(3);
			
			HashMap<String, Object> msgHM=msgPackage.cashOff("ADD", "", String.valueOf(portnum), tradeDate, settleDate, cashacct, funccash, localcash, dbcr, glacct, desc, null);
			
			MsgContainer msgContainer=new MsgContainer();
			msgContainer.setFieldsHM(msgHM);
			msgContainer.setUserID(userID);
			container=tradeService.operateTran(msgContainer);
			
			if(!container.isCorrectFlag()){
				throw new NewFundException(container.getErrorCode(), container.getErrorMsg());
			}
		}
	}
		
	
	private double getComletePercent(NewFundContent newfund){
		if(newfund==null){
			return 0;
		}
		
		NewFund fund=loadNewFund(newfund.getNewFundID());
		
		HashMap<String, Object> result = (HashMap<String, Object>) JsonUtil.stringToMap(newfund.getNewFundContent());
		int completenum=0;
		double pecent=0;
		
		if(fund.getNewFundType()==NewFundType.newFund.getIndex()){
			HashMap<String, Object> porthm=(HashMap<String, Object>)result.get("port");
			if(porthm.size()>0){
				completenum++;
			}
			HashMap<String, Object> banksHM=(HashMap<String, Object>)result.get("bankAccts");
			if(banksHM.size()>0){
				completenum++;
			}
			HashMap<String, Object> secAcctHM=(HashMap<String, Object>)result.get("secAccts");
			if(secAcctHM.size()>0){
				completenum++;
			}
			HashMap<String, Object> expenseHM=(HashMap<String, Object>)result.get("expenses");
			if(expenseHM.size()>0){
				completenum++;
			}
			HashMap<String, Object> interfaceHM=(HashMap<String, Object>)result.get("interfaces");
			if(interfaceHM.size()>0){
				completenum++;
			}
			
			pecent=RoundUtil.roundMoney2Dec(100*(double)completenum/5);
		}else if(fund.getNewFundType()==NewFundType.copyOldFund.getIndex()){
			HashMap<String, Object> tbhm=(HashMap<String, Object>)result.get("tb");
			if(tbhm.size()>0){
				List<Object> tbList=(List<Object>)tbhm.get("info");
				if(tbList.size()>0){
					completenum++;
				}
			}
			HashMap<String, Object> secAcctHM=(HashMap<String, Object>)result.get("secPos");
			if(secAcctHM.size()>0){
				if(tbhm.size()>0){
					List<Object> secPostList=(List<Object>)secAcctHM.get("info");
					if(secPostList.size()>0){
						completenum++;
					}
				}
			}
			HashMap<String, Object> expenseHM=(HashMap<String, Object>)result.get("rcpy");
			if(expenseHM.size()>0){
				List<Object> expenseList=(List<Object>)expenseHM.get("info");
				if(expenseList.size()>0){
					completenum++;
				}
			}
			pecent=RoundUtil.roundMoney2Dec(100*(double)completenum/3);
		}
		return Math.abs(pecent);
	}
	
	@Override
	public double getComletePercent(int newFundID) {
		NewFundContent newfund=loadNewFundContent(newFundID);
		return getComletePercent(newfund);
	}
	
	@Override
	public List<List<String>> loadCustomCoa(String portnum, int newFundID, List<MultipartFile> mpfList, ResultContainer container) {
		
		List<List<String>> fieldsContainer=new ArrayList<List<String>>();
		PortfolioHdr portfolio=commService.loadPortfolio(portnum);
		
		List<Coaa> coaaList=coaaDao.loadCoaaDetailsByPortnum(portfolio.getId());
		HashMap<String, String> acctHM=new HashMap<String, String>();
		HashMap<String, String> acctNameHM=new HashMap<String, String>();
		for(Coaa coa:coaaList){
			acctHM.put(coa.getAccntnum(), coa.getAccntname());
			acctNameHM.put(coa.getAccntname(), coa.getAccntnum());
		}
		
		try{
			for(MultipartFile mpf:mpfList){
				Workbook workbook=null;
				try{
					if(mpf.getOriginalFilename().endsWith(".xls")){
						workbook=new HSSFWorkbook(mpf.getInputStream());
					}else if(mpf.getOriginalFilename().endsWith(".xlsx")){
						workbook=new XSSFWorkbook(mpf.getInputStream());
					}else{
						container.setCorrectFlag(false);
						container.setErrorMsg(excelFormatError);
						return null;
					}
					Sheet sheet = workbook.getSheetAt(0);
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						Row row=sheet.getRow(i);

						List<String> fieldsList = new ArrayList<String>();

						String acctnum=getXLSCellValue(row.getCell(0));
						if(acctnum==null || acctnum.equals("")){
							continue;
						}

						String acctname=getXLSCellValue(row.getCell(1));
						double localBal=Double.valueOf(getXLSCellValue(row.getCell(2)));
						double funcBal=Double.valueOf(getXLSCellValue(row.getCell(3)));
						String comments="";

						fieldsList.add("coa"+i);
						fieldsList.add(acctnum);
						fieldsList.add(acctname);
						fieldsList.add(DataConvert.convertCash2US(localBal));
						fieldsList.add(DataConvert.convertCash2US(funcBal));

						if(acctHM.containsKey(acctnum)){
							fieldsList.add(acctnum+"|"+acctHM.get(acctnum));
							if(!acctHM.get(acctnum).equals(acctname)){
								comments=ReturnResult.getResultHM().get(ReturnResult.newfund_coaname_notmatch);
							}
						}else if(acctNameHM.containsKey(acctname)){
							fieldsList.add(acctNameHM.get(acctname)+"|"+acctname);
							if(!acctNameHM.get(acctname).equals(acctnum)){
								comments=ReturnResult.getResultHM().get(ReturnResult.newfund_coanum_notmatch);
							}
						}else{
							fieldsList.add("");
							comments=ReturnResult.getResultHM().get(ReturnResult.coa_notexist);
						}
						fieldsList.add(comments);

						fieldsContainer.add(fieldsList);
					}
				}finally {
					if(workbook != null){
						workbook.close();
					}
				}
			}
		}catch(Exception e){
			container.setCorrectFlag(false);
			container.setErrorMsg("loadCustomCoa异常");//e.toString());
			logger.error("Error"+": PortNum: " + portnum, e);
			return null;
		}
		return fieldsContainer;
	}
	
	@Override
	public List<List<String>> loadCustomRcpy(String portnum, int newFundID,
			List<MultipartFile> mpfList, ResultContainer container) {
		List<List<String>> fieldsContainer=new ArrayList<List<String>>();
		
		try{
			for(MultipartFile mpf:mpfList){
				Workbook workbook = null;
				try {
					if (mpf.getOriginalFilename().endsWith(".xls")) {
						workbook = new HSSFWorkbook(mpf.getInputStream());
					} else if (mpf.getOriginalFilename().endsWith(".xlsx")) {
						workbook = new XSSFWorkbook(mpf.getInputStream());
					} else {
						container.setCorrectFlag(false);
						container.setErrorMsg(excelFormatError);
						return null;
					}
					Sheet sheet = workbook.getSheetAt(0);
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						Row row = sheet.getRow(i);
						List<String> fieldsList = new ArrayList<String>();
						String preDate = getXLSCellValue(row.getCell(0));
						if (preDate == null || preDate.equals("")) {
							continue;
						}
						Date setDate = DateUtil.convertStr2Date(preDate);
						String desc = getXLSCellValue(row.getCell(1));
						String cur = getXLSCellValue(row.getCell(2));
						double localAmount = Double.valueOf(getXLSCellValue(row.getCell(3)));
						double funcAmount = Double.valueOf(getXLSCellValue(row.getCell(4)));
						int rcpytype = Integer.valueOf(getXLSCellValue(row.getCell(5)));

						List<BkCashAcct> cashacctList = bkCashAcctDao.loadCashAcctByPortnumCur(portnum, cur);
						int cashacctnum = 0;
						if (cashacctList.size() > 0) {
							cashacctnum = cashacctList.get(0).getId();
						}
						fieldsList.add(String.valueOf(i));
						fieldsList.add(DateUtil.convertD2YYYY_MM_DD(setDate));
						fieldsList.add(String.valueOf(rcpytype));
						fieldsList.add(desc);
						fieldsList.add(cur);
						fieldsList.add(String.valueOf(localAmount));
						fieldsList.add(String.valueOf(funcAmount));
						fieldsList.add("");
						fieldsList.add(String.valueOf(cashacctnum));
						fieldsContainer.add(fieldsList);
					}
				} finally {
					if(workbook != null){
						workbook.close();
					}
				}
			}
		}catch(Exception e){
			container.setCorrectFlag(false);
			container.setErrorMsg(null);//e.getMessage());
			logger.error("Error", e);
			return null;
		}
		return fieldsContainer;
	}
	
	@Override
	public List<List<String>> loadCustomSecPosition(String portnum, int newFundID,
			List<MultipartFile> mpfList, ResultContainer container) {
		List<List<String>> fieldsContainer=new ArrayList<List<String>>();
		
		DecimalFormat format = new DecimalFormat();
		format.applyPattern("#.##");
		
		try{
			for(MultipartFile mpf:mpfList){
				Workbook workbook=null;
				try{
					if(mpf.getOriginalFilename().endsWith(".xls")){
						workbook=new HSSFWorkbook(mpf.getInputStream());
					}else if(mpf.getOriginalFilename().endsWith(".xlsx")){
						workbook=new XSSFWorkbook(mpf.getInputStream());
					}else{
						container.setCorrectFlag(false);
						container.setErrorMsg(excelFormatError);
						return null;
					}
					Sheet sheet = workbook.getSheetAt(0);
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						Row row=sheet.getRow(i);
						List<String> fieldsList = new ArrayList<String>();
						String secid=getXLSCellValue(row.getCell(0));
						if(secid==null || secid.equals("")){
							continue;
						}

						String secname=getXLSCellValue(row.getCell(1));
						String expDateStr=getXLSCellValue(row.getCell(2));
						if(expDateStr.equals("")){
							expDateStr="20000101";
						}
						Date exeDate=DateUtil.convertStr2Date(expDateStr);
						double amount=Double.valueOf(getXLSCellValue(row.getCell(3)));
						String currency=getXLSCellValue(row.getCell(4));
						double costlocal=Double.valueOf(getXLSCellValue(row.getCell(5)));
						double costfunc=Double.valueOf(getXLSCellValue(row.getCell(6)));
						double intlocal=Double.valueOf(getXLSCellValue(row.getCell(7)));
						double intfunc=Double.valueOf(getXLSCellValue(row.getCell(8)));
						String secacctCode=getXLSCellValue(row.getCell(9));
						double alrate=Double.valueOf(getXLSCellValue(row.getCell(10)));
						SecAcct secacct=secAcctDao.loadSecAcctByAcctCode(secacctCode.trim());
						int valMethod=Integer.valueOf(getXLSCellValue(row.getCell(11)));
						String secacctno="0";
						if(secacct!=null){
							secacctno=secacct.getAcctID();
						}

						fieldsList.add("position"+i);
						fieldsList.add(secid);
						fieldsList.add(secname);
						fieldsList.add(DateUtil.convertD2YYYY_MM_DD(exeDate));
						fieldsList.add(format.format(amount));
						fieldsList.add(currency);
						fieldsList.add(format.format(costlocal));
						fieldsList.add(format.format(costfunc));
						fieldsList.add(format.format(intlocal));
						fieldsList.add(format.format(intfunc));
						fieldsList.add(format.format(alrate));
						fieldsList.add(format.format(valMethod));

						IssueInfo sec=issueInfoDao.loadSecurity(secid);

						String comments="";
						if(sec==null){
							fieldsList.add("");
							comments=ReturnResult.getResultHM().get(ReturnResult.newfund_sec_notexist);
						}else{
							if(sec.getSecurityname()==null || sec.getSecurityname().equals("")){
								fieldsList.add(sec.getSecurityid());
							}else{
								fieldsList.add(sec.getSecurityid()+"|"+sec.getSecurityname());
							}

							if(!sec.getSecurityname().equals(secname)){
								comments=ReturnResult.getResultHM().get(ReturnResult.newfund_secname_notmatch);
							}
						}

						fieldsList.add(String.valueOf(secacctno));
						fieldsList.add(comments);

						fieldsContainer.add(fieldsList);
					}
				}finally {
					if(workbook != null){
						workbook.close();
					}
				}
			}
		}catch(Exception e){
			container.setCorrectFlag(false);
			container.setErrorMsg("loadCustomSecPosition异常");//e.getMessage());
			logger.error("Error", e);
			return null;
		}
		return fieldsContainer;
	}
	
	public String getXLSCellValue(Cell cell) {
		String result = new String();
		if(cell==null){
			return "";
		}
		
        switch (cell.getCellTypeEnum()) {
        case NUMERIC:
            if (cell.getCellStyle().getDataFormat() == 176) {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                double value = cell.getNumericCellValue();
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                result = sdf.format(date);
            } else {
                double value = cell.getNumericCellValue();
                CellStyle style = cell.getCellStyle();
                DecimalFormat format = new DecimalFormat();
                String temp = style.getDataFormatString();
                // 单元格设置成常规
                if (temp.equals("General")) {
                    format.applyPattern("#.##");
                }
                result = format.format(value);
            }
            break;
        case STRING:// String类型
            result = cell.getStringCellValue();
            break;
        case BOOLEAN:
        	result = String.valueOf(cell.getBooleanCellValue());
    		break;
    	case BLANK:
    		result = "";
    		break;
        default:
            result = "";
            break;
        }
        return result.trim();
    }
}
