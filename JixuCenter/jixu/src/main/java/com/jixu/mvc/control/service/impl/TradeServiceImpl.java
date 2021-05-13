package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.func.SystemSelect;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.PortTranMapType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.types.InterestCycleDate;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.OptionType;
import com.jixu.common.types.PerformanceFeeType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.TradeSettleLedger;
import com.jixu.common.types.TranCloType;
import com.jixu.common.types.TranFeeTypes;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.ManagersDao;
import com.jixu.dao.intf.NavBankZiGuanDao;
import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.dao.intf.TranCollateralDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.job.service.Impl.PurSaleJob;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.jixu.mvc.control.model.SecTradeDetailModel;
import com.jixu.mvc.control.model.SecTradeSummaryModel;
import com.jixu.mvc.control.model.VoucherDetailsView;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.mvc.control.service.TradeService;
import com.jixu.mvc.control.service.VoucherService;
import com.jixu.post.service.PostIssueService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.Coaa;
import com.orm.obj.IssueInfo;
import com.orm.obj.NavBankZiGuan;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecContract;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranClo;
import com.orm.obj.TranCollateral;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeFollowClo;
import com.orm.obj.TranSum;
import com.orm.obj.VoucherDetails;

@Service("tradeService")
public class TradeServiceImpl implements TradeService{
	
	@Autowired
	private PerformanceFeeCfgDao performanceFeeCfgDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Resource(name="postIssues")
	private PostIssueService postIssue;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Resource(name="pursaleJnljob")
	private PurSaleJob purSaleJob;
	
	@Autowired
	private SecContractDao secContractDao;
	
	@Autowired
	private VoucherService voucherService;

	@Resource
	private SecPositionCenter secPositionCenter;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao;
	
	@Autowired
	private TranBasicFeeDao tranBasicFeeDao;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private TranCollateralDao tranCollateralDao;
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private ManagersDao managersDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private NavBankZiGuanDao navBankZiGuanDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private PortfolioCfgService portCfgService;
	
	@Resource(name="secPositionCenter")
	private SecPositionCenter secPostCenter;
	
	//投资组合交易查询分页
	public Pages<SecTradeSummaryModel> loadTranBasicList(Pages<SecTradeSummaryModel> pages,String secType, String secID, String portNum, Date startDate, Date endDate,Boolean displayDelete) {
		//分页
		//查询该投资组合下 所有的子投资组合
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portNum);
		List<TranBasic> tranBasicList=new ArrayList<TranBasic>();
		//pageHelper分页
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<TranBasic> page=tranBasicDao.getPageHelperTranBasic(PageHelper.startPage(pages.getCurrentPage(),pages.getPageMaxRecords()),secType, secID, portList, startDate, endDate,displayDelete);
			tranBasicList=page.getResult();
			pages.setTotalPages(page.getPages());
		}else{
			Pages<TranBasic> pages1=tranBasicDao.getTranBasic(pages,secType, secID, portList, startDate, endDate,displayDelete);
			tranBasicList=pages1.getResultList();
			pages.setTotalPages(pages1.getTotalPages());
		}
		List<SecTradeSummaryModel> sumaryList=new ArrayList<SecTradeSummaryModel>();
		
		for(TranBasic tran:tranBasicList){
			SecTradeSummaryModel secSummary=new SecTradeSummaryModel();
			
			Iterator<TranBasicFee> fees=tran.getTranBasicFees().iterator();
			double totalfee=getTotalFeeAmount(fees);
			
			secSummary.setTanum(tran.getId());
			if(tran.getTrantype()==TransTypes.SWAP_FX_MATURITY)
				continue;
			secSummary.setTrantypeCode(tran.getTrantype());
			secSummary.setTrantype(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
			if(tran.getPostStatus().equals(PostStatusType.post)){
				secSummary.setPostType("已过账");
			}else if(tran.getPostStatus().equals(PostStatusType.unpost)){
				secSummary.setPostType("未过账");
			}else if(tran.getPostStatus().equals(PostStatusType.delete)){
				secSummary.setPostType("已删除");
			}
			secSummary.setSecurityName(tran.getCusip().getSecurityname());
			secSummary.setTradeDate(DateUtil.convertD2YYYYMMDD(tran.getTradedate()));
			secSummary.setSetDate(DateUtil.convertD2YYYYMMDD(tran.getSettledate()));
			secSummary.setSecID(tran.getCusip().getSecurityid());
			secSummary.setAmount(tran.getAmount());
			secSummary.setCost(tran.getPrincipal());
			secSummary.setInterest(RoundUtil.roundMoney2Dec(tran.getIntatmat()));
			secSummary.setCurrency(commfuncService.loadFxCurrencyCode(tran.getCusip().getPriCur()));
			secSummary.setRate(tran.getFxrate());
			
			secSummary.setAmount2(tran.getAmount2());
			secSummary.setCost2(tran.getPrincipal2());
			
			secSummary.setFee(RoundUtil.roundMoney2Dec(totalfee));
			//从缓存中取投资组合
			PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());
			secSummary.setPortname(portfolio.getShortName());
			sumaryList.add(secSummary);
		}
		pages.setResultList(sumaryList);
		return pages;
	}
	
	public SecTradeDetailModel loadTranBasicByID(String tanum) {
		TranBasic tran=tranBasicDao.loadTranBasicByID(tanum);
		SecTradeDetailModel detail=new SecTradeDetailModel();
		detail.setTanum(tran.getId());
		detail.setPortnum(tran.getPortfolio());
		detail.setPortnum2(tran.getPortfolio2());
		IssueInfo cusip=tran.getCusip();
		if(cusip != null){
			detail.setSecID(cusip.getId()+"|"+cusip.getSecurityid()+"|"+cusip.getSecurityname());
			detail.setSecType(cusip.getIssueType());
			detail.setSwapExchangeInd(cusip.getSwapExchangeInd());
		}else{
			detail.setSecType(SecurityCoreFactory.getTranTypeAssetTypeMap().get(tran.getTrantype()));
		}
		detail.setTradeDate(DateUtil.convertD2YYYY_MM_DD(tran.getTradedate()));
		detail.setSetDate(DateUtil.convertD2YYYY_MM_DD(tran.getSettledate()));
		
		Date settleDate2=tran.getSettledate2();
		if(settleDate2!=null){
			detail.setSettleDate2(DateUtil.convertD2YYYY_MM_DD(settleDate2));
		}
		
		detail.setTranType(tran.getTrantype());
		if(tran.getBroker() != null){
			detail.setBrokerCode(tran.getBroker());
		}
		BkCashAcct acct=tran.getBkCashacctNo();
		if(acct!=null){
			detail.setCashacct(acct.getId());
		}
			
		BkCashAcct acct2=tran.getBkCashacctNo2();
		if(acct2!=null){
			detail.setCashacct2(acct2.getId());
		}
		
		detail.setUnits(tran.getAmount());
		detail.setPrice(tran.getPrice());
		detail.setFeeConfigNum(1);		//to-do-pending
		detail.setPrincipal(tran.getPrincipal());
		String setCurrency=tran.getSettleCurrency();
		detail.setSetCurID(setCurrency);
		detail.setSetCash(tran.getSettleCash());
		detail.setSetFxRate(tran.getSettleFxRate());
		detail.setSetcurCode2(tran.getSettleCurrency2());
		detail.setSetCash2(tran.getSettleCash2());
		detail.setSetFxRate2(tran.getSettleFxRate2());
		detail.setFxrate(tran.getFxrate());
		detail.setFxrate2(tran.getFxrate2());
		if(tran.getSecacct()!=null){
			detail.setSecacct(tran.getSecacct());
		}
		if(tran.getSecacct2()!=null){
			detail.setSecacct2(tran.getSecacct2());
		}
		detail.setCash2(tran.getCash2());
		
		int manager=tran.getManager();
		if(manager!=0){
			detail.setManager(manager);
		}
		
		PortfolioHdr portfolio=commfuncService.loadPortfolio(tran.getPortfolio());
		detail.setLedgerCurrency(portfolio.getCurID());
		detail.setCash(tran.getCash());
		if(tran.getAltDate()!=null){
			detail.setAlDate(DateUtil.convertD2YYYY_MM_DD(tran.getAltDate()));
		}
		detail.setAlRate(tran.getAltRate());
		detail.setAmount(tran.getAmount());
		detail.setAmount2(tran.getAmount2());
		detail.setIntatmat(tran.getIntatmat());
		detail.setIntatmat2(tran.getIntatmat2());
		detail.setIntent(tran.getValuationType());
		
		String fxcurrency=tran.getFxcurrency();
		if(fxcurrency!=null){
			detail.setFxcurrency(fxcurrency);
			detail.setFxCurCode(commfuncService.loadFxCurrencyCode(fxcurrency));
		}
		String fxcurrency2=tran.getFxcurrency2();
		if(fxcurrency2!=null){
			detail.setFxcurrency2(fxcurrency2);
			detail.setFxCurCode2(commfuncService.loadFxCurrencyCode(fxcurrency2));
		}
		
		Iterator<TranBasicFee> fees=tran.getTranBasicFees().iterator();
		List<Double> feeList=getTradeDetailTotalFeeAmount(fees);
		
		detail.setFeeTotal(feeList.get(0));
		detail.setFeeTotal2(feeList.get(1));
		
		if(tran.getCash()>=0){
			detail.setDbcr(DBCR.receive);
		}else{
			detail.setDbcr(DBCR.pay);
		}
		detail.setGlacct(tran.getGlacct());
		detail.setDesc(tran.getTranDesc());
		detail.setContract(tran.getContract());
		detail.setGlcol(tran.getGlGroup());
		detail.setDivBaseDate(tran.getDivBasicDate());
		detail.setTradeMarket(tran.getTradeMarket());
		detail.setPrice2(tran.getPrice2());
		detail.setPrincipal2(tran.getPrincipal2());
		detail.setHiloCode(tran.getHiloCode());
		detail.setSpecificLotID(tran.getSpecificLotID());
		detail.setSetIntatmat(tran.isSetAllIntatmatFlag());
		detail.setSetIntatmat2(tran.isSetAllIntatmatFlag2());
		
		IssueInfo cusip2=tran.getCusip2();
		if(cusip2 != null){
			detail.setSecID2(cusip2.getId()+"|"+cusip2.getSecurityid()+"|"+cusip2.getSecurityname());
		}
		
		return detail;
	}
	
	public Pages<SecTradeSummaryModel> loadSubRedTranList(String portnum, int tranType, Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete) {
			int[] typeArr=null;
			if(tranType==0){
				typeArr=new int[2];
				typeArr[0]=TransTypes.SUB;
				typeArr[1]=TransTypes.RED;
			}else{
				typeArr=new int[1];
				typeArr[0]=tranType;
			}
			return getTranBasicByTranType(portnum, typeArr, startDate,endDate,pages,displayDelete);
	}
	
	public Pages<SecTradeSummaryModel> loadFXTranTranList(String portnum, String assetType, Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete) {
		int[] typeArr=null;
		if(assetType.equals(AssetTypes.SPOT_CURRENCY)){
			typeArr=new int[3];
			typeArr[0]=TransTypes.FX_CLOSING;
			typeArr[1]=TransTypes.FX_DELIVERY;
			typeArr[2]=TransTypes.FX_OPENING;
		}else if(assetType.equals(AssetTypes.FORWARD_CURRENCY)){
			typeArr=new int[5];
			typeArr[0]=TransTypes.FORWORD_CURRENCY;
			typeArr[1]=TransTypes.FORWORD_CURRENCY_OPEN;
			typeArr[2]=TransTypes.FORWORD_CURRENCY_CLOSE;
			typeArr[3]=TransTypes.FORWORD_CURRENCY_Cutting;
			typeArr[4]=TransTypes.FORWORD_CURRENCY_Maturity;
		}else if(assetType.equals(AssetTypes.FXOption)){
			typeArr=new int[5];
			typeArr[0]=TransTypes.FXOption_Buy;
			typeArr[1]=TransTypes.FXOption_Sale;
			typeArr[2]=TransTypes.FXOption_FullExercise;
			typeArr[3]=TransTypes.FXOption_NetExercise;
			typeArr[4]=TransTypes.FXOption_AbandonExercise;
		}
		return getTranBasicByTranType(portnum, typeArr, startDate,endDate,pages,displayDelete);
	}
	
	public Pages<SecTradeSummaryModel> loadCashOffList(String portnum, Date startDate, Date endDate, int tranType,Pages<SecTradeSummaryModel> pages,Boolean displayDelete) {
		
		int[] typeArr=new int[1];
		typeArr[0]=tranType;
		
		return getTranBasicByTranType(portnum, typeArr, startDate,endDate,pages,displayDelete);
	}
	
	public Pages<SecTradeSummaryModel> loadCashTranList(String portnum, Date startDate, Date endDate, Pages<SecTradeSummaryModel> pages,Boolean displayDelete) {
		
		int[] typeArr=new int[1];
		typeArr[0]=TransTypes.CASHMOVE;
		
		return getTranBasicByTranType(portnum, typeArr, startDate,endDate,pages,displayDelete);
	}
	
	/*public List<SecTradeSummaryModel> loadCashOffList(String portNum, int[] tranTypes, String startDate, String endDate) {
		int[] typeArr=new int[1];
		typeArr[0]=TransTypes.CASHOFF;
		List<TranBasic> offsetList=tradeDAO.getTranBasicByTranType(portNum, tranTypes, startDate, endDate);
		List<SecTradeSummaryModel> sumaryList=new ArrayList<SecTradeSummaryModel>();
		
		for(TranBasic off:offsetList){
			SecTradeSummaryModel secSummary=new SecTradeSummaryModel();
			secSummary.setTanum(off.getId());
			secSummary.setTrantype(SecurityCoreFactory.getAllTranTypeHM().get(off.getTrantype()));
			secSummary.setTradeDate(off.getTradedate());
			secSummary.setSetDate(off.getSettledate());
			if(off.getAmount()>0){
				secSummary.setAmount(off.getAmount());
				secSummary.setBankAcct(off.getBkCashacctNo().getBankdesc());
				secSummary.setCurrency(commfuncService.loadFxCurrencyCode(off.getBkCashacctNo().getFxcurrency().getId()));
				secSummary.setRate(off.getFxrate());
				secSummary.setDbcr("收入");
			}else{
				secSummary.setAmount(off.getAmount2());
				secSummary.setBankAcct(off.getBkCashacctNo2().getBankdesc());
				secSummary.setCurrency(commfuncService.loadFxCurrencyCode(off.getBkCashacctNo2().getFxcurrency().getId()));
				secSummary.setRate(off.getFxrate2());
				secSummary.setDbcr("支出");
			}
			secSummary.setGlAccount(off.getGlacct());
			
			sumaryList.add(secSummary);
		}
		
		return sumaryList;
	} */
	
	/**
	 * 计算净费用
	 * @param fees
	 * @return
	 */
	private double getTotalFeeAmount(Iterator<TranBasicFee> fees){
		double totalfee=0;
		while(fees.hasNext()){
			TranBasicFee fee=fees.next();
			if(fee.getRecOrPay()==DBCR.pay){
				totalfee=totalfee+fee.getTotalFeeamt();
			}else{
				totalfee=totalfee-fee.getTotalFeeamt();;
			}
		}
		return RoundUtil.roundMoney2Dec(totalfee);
	}
	
	private List<Double> getTradeDetailTotalFeeAmount(Iterator<TranBasicFee> fees){
		List<Double> totalfeeList= new ArrayList<Double>();
		double totalfeeTrade=0;
		double totalfeeNoTrade=0;
		while(fees.hasNext()){
			TranBasicFee fee=fees.next();
			TranFeeDef feeDef=tranFeeDefDao.loadTranFeeDef(fee.getFeeTypeID().getId());
			if(feeDef.getOneTimeFeeFlag()==ExpenseBaseType.onetimeFee){
				if(fee.getRecOrPay()==DBCR.pay){
					totalfeeTrade=totalfeeTrade+(fee.getTotalFeeamt()-fee.getAmtexclfromcash());
					totalfeeNoTrade=totalfeeNoTrade+fee.getAmtexclfromcash();
				}else{
					totalfeeTrade=totalfeeTrade-(fee.getTotalFeeamt()-fee.getAmtexclfromcash());
					totalfeeNoTrade=totalfeeNoTrade-fee.getAmtexclfromcash();
				}
			}
		}
		totalfeeList.add(RoundUtil.roundMoney2Dec(totalfeeTrade));
		totalfeeList.add(RoundUtil.roundMoney2Dec(totalfeeNoTrade));
		return totalfeeList;
	}
	

	
	private  Pages<SecTradeSummaryModel> getTranBasicByTranType(String portNum, int[] tranTypes, Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete){
		List<String> portList=portCfgService.loadPortfolioClassIDRecursion(portNum);
		List<TranBasic> subredList=new ArrayList<TranBasic>();
		int totalPages=0;
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<TranBasic> page=tranBasicDao.getPageHelperTranBasicByTranType(portList, tranTypes, startDate, endDate,PageHelper.startPage(pages.getCurrentPage(),pages.getPageMaxRecords()),displayDelete);
			totalPages=page.getPages();
			subredList=page.getResult();
		}else{
			Pages<TranBasic> tranBasicPages=tranBasicDao.getTranBasicByTranType(portList, tranTypes, startDate, endDate,pages,displayDelete);
			totalPages=tranBasicPages.getTotalPages();
			subredList=tranBasicPages.getResultList();
		}
		List<SecTradeSummaryModel> sumaryList=new ArrayList<SecTradeSummaryModel>();
		for(TranBasic tran:subredList){
			SecTradeSummaryModel secSummary=new SecTradeSummaryModel();
			
			Iterator<TranBasicFee> fees=tran.getTranBasicFees().iterator();
			double totalFeeAmount=getTotalFeeAmount(fees);
			
			secSummary.setTanum(tran.getId());
			secSummary.setTrantypeCode(tran.getTrantype());
			secSummary.setTrantype(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
			if(tran.getPostStatus().equals(PostStatusType.post)){
				secSummary.setPostType("已过账");
			}else if(tran.getPostStatus().equals(PostStatusType.unpost)){
				secSummary.setPostType("未过账");
			}else if(tran.getPostStatus().equals(PostStatusType.delete)){
				secSummary.setPostType("已删除");
			}
			
			secSummary.setTradeDate(DateUtil.convertD2YYYYMMDD(tran.getTradedate()));
			secSummary.setSetDate(DateUtil.convertD2YYYYMMDD(tran.getSettledate()));
			
			if(tran.getAmount()!=0){
				secSummary.setAmount(tran.getAmount());
				secSummary.setCost(tran.getPrincipal());
				secSummary.setCurrency(commfuncService.loadFxCurrencyCode(tran.getFxcurrency()));
				if(tran.getBkCashacctNo()!=null){
					secSummary.setBankAcct(tran.getBkCashacctNo().getBankdesc());
				}
				secSummary.setRate(tran.getFxrate());
			}
			
			if(tran.getAmount2()!=0){
				secSummary.setAmount2(tran.getAmount2());
				secSummary.setCost2(tran.getPrincipal2());
				secSummary.setCurrency2(commfuncService.loadFxCurrencyCode(tran.getFxcurrency2()));
				if(tran.getBkCashacctNo2()!=null){
					secSummary.setBankAcct2(tran.getBkCashacctNo2().getBankdesc());
				}
				secSummary.setRate2(tran.getFxrate2());
			}
			
			/*if(tran.getAmount()==0 && tran.getAmount2()==0){
				continue;
			}*/
			if(tran.getTrantype()==TransTypes.FXOption_Buy||tran.getTrantype()==TransTypes.FXOption_Sale||tran.getTrantype()==TransTypes.FXOption_FullExercise
					||tran.getTrantype()==TransTypes.FXOption_AbandonExercise||tran.getTrantype()==TransTypes.FXOption_NetExercise){
				SecContract sc=secContractDao.loadSecContract(tran.getCusip().getId());
				secSummary.setAmount(sc.getLongCash());
				secSummary.setCurrency(commfuncService.loadFxCurrencyCode(sc.getLongCurID()));
				secSummary.setAmount2(sc.getShortCash());
				secSummary.setCurrency2(commfuncService.loadFxCurrencyCode(sc.getShortCurID()));
			}
			
			secSummary.setFee(RoundUtil.roundMoney2Dec(totalFeeAmount));
			secSummary.setGlAccount(tran.getGlacct());
			if(tran.getAmount()>0){
				secSummary.setDbcr(DBCR.optionsHM.get(DBCR.receive));
			}else{
				secSummary.setDbcr(DBCR.optionsHM.get(DBCR.pay));
			}
			secSummary.setTranDesc(tran.getTranDesc());
			secSummary.setContract(tran.getContract());
			//根据投资组合id  从缓存中查询对应的Portfolio
			secSummary.setPortname(commfuncService.loadPortfolio(tran.getPortfolio()).getShortName());
			
			sumaryList.add(secSummary);
		}
		pages.setResultList(sumaryList);
		pages.setTotalPages(totalPages);
		return pages;
	}
	
	public HashMap<Integer, String> loadTranFeeDefByTranTypeID(String assettype) {
		List<TranFeeDef> feeDefList=tranFeeDefDao.loadTranFeeDefListByTranType(assettype);
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		
		for(TranFeeDef feedef:feeDefList){
			optionHM.put(feedef.getId(), feedef.getFeename());
		}
		
		return optionHM;
	}
	
	public List<TranBasicFee> loadTranBasicFeeList(String tanum) {
		return tranBasicFeeDao.loadTranBasicFee(tanum);
	}
	
	public double genRepoMaturity(String secid, double rate, double baseamount, Date tradeDate, Date setDate, Date matDate) {
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfgBySecid(secid);
		if(cfc!=null){
			Date startDate=tradeDate;
			if(cfc.getStartNDaysBase()==TradeSettleLedger.SettleDate)
				startDate=setDate;
				
			//结转现金那天是不计息的，没有钱计算利息
			Date endDate=DateUtil.getLastDay(matDate);
			
			List<Date> holidayList=holidayLSDao.loadHolidayList(cfc.getHolidaySD());
			double accrued=incomeExpenseCal.getRepoMaturity(cfc, startDate, endDate, baseamount, rate, holidayList);
			return accrued;
		}else{
			return 0;
		}
	}
	
	
	public List<TranCollateral> loadTranCollateralList(String tanum) {
		return tranCollateralDao.loadTranCollateralList(tanum);
	}
	
	public double calTotalFeeAmount(List<Object> exp_list,String port){
		double totalFee = 0;
		for(Object exp:exp_list){
			List<Object> expl = (List<Object>)exp;
			int feeid=Integer.valueOf(expl.get(0).toString());
			TranFeeDef feedef=tranFeeDefDao.loadTranFeeDef(feeid);
			if(feedef.getOneTimeFeeFlag()==ExpenseBaseType.followFee)
				continue;
			
			int rbcr=Integer.valueOf(expl.get(1).toString());
			//double fxRate=Double.valueOf(expl.get(5).toString());
			double unitTotalFee=Double.valueOf(expl.get(6).toString());
			//double expensedFee= Double.valueOf(expl.get(7).toString());
			double rcpyExpenseFee = Double.valueOf(expl.get(8).toString());
			double cashFee=RoundUtil.roundMoney2Dec(unitTotalFee-rcpyExpenseFee);
			if(rbcr==DBCR.receive){
				cashFee=-1*cashFee;
			}
			String tradeCur=expl.get(3).toString();
			String feeCur=expl.get(4).toString();
			if(cashFee!=0 && tradeCur!=feeCur && expl.size()==10){
				String feeDateStr=expl.get(9).toString().replaceAll("-", "");
				Date feeDate=DateUtil.convertStr2Date(feeDateStr);
				double fxrate=commfuncService.loadFxrate(port,tradeCur, feeCur, feeDate);
				if(fxrate==0){
					double fxrate1=commfuncService.loadFxrate(port,feeCur, tradeCur, feeDate);
					if(fxrate1!=0){
						fxrate=1/fxrate1;
					}
				}
				if(fxrate==0){
					//如果实在计算不出汇率，默认为1
					fxrate=1;
				}
				cashFee=RoundUtil.roundMoney2Dec(cashFee*fxrate);	
			}
			totalFee=RoundUtil.roundMoney2Dec(totalFee+cashFee);
		}
		return totalFee;
	}
	
	
	@Override
	public double newCalTotalFeeAmount(List<HashMap> mapList, String port) {
		double totalFee = 0;
		for (HashMap map : mapList) {
			int feeid = Integer.valueOf(map.get("expense").toString());
			TranFeeDef feedef = tranFeeDefDao.loadTranFeeDef(feeid);
			if (feedef.getOneTimeFeeFlag() == ExpenseBaseType.followFee)
				continue;
			int rbcr=DBCR.pay;
			if(map.containsKey("rpType")) 
				rbcr = Integer.valueOf(map.get("rpType").toString());

			double unitTotalFee = Double.valueOf(map.get("totalAmount").toString());
			// double expensedFee= Double.valueOf(expl.get(7).toString());
			int paywithTrade=TranFeeTypes.PAY_WITH_TRADE;
			double rcpyExpenseFee = 0;
			if(map.containsKey("cashAmount"))
				paywithTrade=Integer.valueOf(map.get("cashAmount").toString());
			if(paywithTrade==TranFeeTypes.PAY_WITH_TRADE) {
				//随交易支付
				rcpyExpenseFee=0;
			}else {
				//不随交易支付
				rcpyExpenseFee=unitTotalFee;
			}
			
			double cashFee = RoundUtil.roundMoney2Dec(unitTotalFee - rcpyExpenseFee);
			if (rbcr == DBCR.receive) {
				cashFee = -1 * cashFee;
			}
			String tradeCur = map.get("feeTradeCurrency").toString();
			String feeCur =map.get("tableFeeCurrency").toString();
			if (cashFee != 0 && tradeCur != feeCur) {
				Date feeDate = DateUtil.convertStr2Date("29991231");
				double fxrate = commfuncService.loadFxrate(port, tradeCur, feeCur, feeDate);
				if (fxrate == 0) {
					double fxrate1 = commfuncService.loadFxrate(port, feeCur, tradeCur, feeDate);
					if (fxrate1 != 0) {
						fxrate = 1 / fxrate1;
					}
				}
				if (fxrate == 0) {
					// 如果实在计算不出汇率，默认为1
					fxrate = 1;
				}
				cashFee = RoundUtil.roundMoney2Dec(cashFee * fxrate);
			}
			totalFee = RoundUtil.roundMoney2Dec(totalFee + cashFee);
		}
		return totalFee;
	}
	
	@Override
	public double newCalTotalFeeAmount2(List<HashMap> mapList, String port) {
		double totalAllFee=0;
		double totalFee = 0;
		for (HashMap map : mapList) {
			int feeid = Integer.valueOf(map.get("expense").toString());
			TranFeeDef feedef = tranFeeDefDao.loadTranFeeDef(feeid);
			if (feedef.getOneTimeFeeFlag() == ExpenseBaseType.followFee)
				continue;
			int rbcr=DBCR.pay;
			if(map.containsKey("rpType")) 
				rbcr = Integer.valueOf(map.get("rpType").toString());

			double unitTotalFee = Double.valueOf(map.get("totalAmount").toString());
			// double expensedFee= Double.valueOf(expl.get(7).toString());
			int paywithTrade=TranFeeTypes.PAY_WITH_TRADE;
			double rcpyExpenseFee = 0;
			if(map.containsKey("cashAmount"))
				paywithTrade=Integer.valueOf(map.get("cashAmount").toString());
			if(paywithTrade==TranFeeTypes.PAY_WITH_TRADE) {
				//随交易支付
				rcpyExpenseFee=0;
			}else {
				//不随交易支付
				rcpyExpenseFee=unitTotalFee;
			}
			
			double cashFee = RoundUtil.roundMoney2Dec(unitTotalFee - rcpyExpenseFee);
			if (rbcr == DBCR.receive) {
				cashFee = -1 * cashFee;
			}
			String tradeCur = map.get("feeTradeCurrency").toString();
			String feeCur =map.get("tableFeeCurrency").toString();
			
			Date feeDate = DateUtil.convertStr2Date("29991231");
			double fxrate = commfuncService.loadFxrate(port, tradeCur, feeCur, feeDate);
			if (fxrate == 0) {
				double fxrate1 = commfuncService.loadFxrate(port, feeCur, tradeCur, feeDate);
				if (fxrate1 != 0) {
					fxrate = 1 / fxrate1;
				}
			}
			if (fxrate == 0) {
				// 如果实在计算不出汇率，默认为1
				fxrate = 1;
			}
			
			if (cashFee != 0 && tradeCur != feeCur) {
				cashFee = RoundUtil.roundMoney2Dec(cashFee * fxrate);
			}
			totalFee = RoundUtil.roundMoney2Dec(totalFee + cashFee);
			totalAllFee=RoundUtil.roundMoney2Dec(totalAllFee+RoundUtil.roundMoney2Dec(unitTotalFee * fxrate));
		}
		return RoundUtil.roundMoney2Dec(totalAllFee-totalFee);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer deleteTran(String tradeID, boolean wflFlag){
		MsgContainer container=new MsgContainer(); 
		HashMap<String, Object> fieldsHM=new HashMap<String, Object>();
		String header="jixuStockTranDtoMsg";
		fieldsHM.put(MsgFields.operation, "DELETE");
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.tanum, tradeID);
		
		TranBasic tran=tranBasicDao.loadTranBasicByID(tradeID);
		if(tran.getCusip()!=null) {
			IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
			fieldsHM.put(MsgFields.secid, sec.getSecurityid());
		}else {
			fieldsHM.put(MsgFields.secid, "");
		}
		
		String json=JSONObject.toJSONString(fieldsHM);
		container.setFieldsHM(fieldsHM);
		container.setUserID(JixuUserUtil.getUserID());
		container.setJsonMsg(json);
		if(wflFlag)
			container.setNewProcessFlag(true);

		container=msgFactory.executeMsg(container);
		if(!container.isCorrectFlag()){
			container.setCorrectFlag(container.isCorrectFlag());
			container.setErrorMsg(container.getErrorMsg());
		}
		return container;
	}
	
	/**
	 * 执行交易消息
	 */
	public MsgContainer operateTran(MsgContainer container) {
		container=MsgFactory.executeMsg(container);
		return container;
	}
	
	public MsgContainer operateTranAuto(TranBaseMsgDto dto) {
		String jsonmsg=JSONObject.toJSONString(dto);
		HashMap<String, Object> msgHM=JSONObject.parseObject(jsonmsg, HashMap.class);
		MsgContainer msgContainer=new MsgContainer();
		msgContainer.setUserID(JixuUserUtil.getUserID());
		msgContainer.setFieldsHM(msgHM);
		msgContainer.setTradeImportType(JixuDictCode.IMP_TRADE_TYPE_AUTO);
		return operateTran(msgContainer);
	}
	
	/**
	 * 执行交易消息
	 * 经过工作流
	 */
	public MsgContainer operateTranWF(MsgContainer container) {
		container.setNewProcessFlag(true);
		container=msgFactory.executeMsg(container);
		return container;
	}
	
	/**
	 * 返回 证券账户|LotNum|数量
	 * @param portnum
	 * @param secid
	 * @return
	 */
	@Override
	public List<String> loadLotUnits(String portnum, String secid) {
		List<TranSum> tsumList=tranSumDao.loadTranSum(portnum, secid);
		List<String> lotList=new ArrayList<String>();
		for(TranSum tsum:tsumList){
			Tran tran = tranDao.loadPreMaturityDepositTran(portnum, tsum.getCusip(), tsum.getLotnum());
			TranBasic tranbasic = tranBasicDao.loadEntity(TranBasic.class, tran.getTanum().getId());
			lotList.add(secid+"|"+tranbasic.getContract()+"|"+tsum.getAmount()+"|"+tsum.getLotnum());
		}
		return lotList;
	}
	
	@Override
	public List<String> loadLotUnitsPosition(String portnum, String secid) {
		List<TranSum> tsumList=tranSumDao.loadTranSum(portnum, secid);
		List<String> lotList=new ArrayList<String>();
		for(TranSum tsum:tsumList){
			Tran tran = tranDao.loadPreMaturityDepositTran(portnum, tsum.getCusip(), tsum.getLotnum());
			TranBasic tranbasic = tranBasicDao.loadEntity(TranBasic.class, tran.getTanum().getId());
			String contract=" ";
			if(tranbasic.getContract()!=null){
				contract=tranbasic.getContract();
			}
			lotList.add(secid+"|"+DataConvert.convertCashNoCalFormat(tsum.getAmount())+"|"+tsum.getLotnum()+"|"+contract);
		}
		return lotList;
	}
	
	
	@Override
	public List<String> loadFutureLotSelect(String portnum, String secid,String secacct,int longShort) {
		List<String> lotList=new ArrayList<String>();
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		TranSum tsum = tranSumDao.loadTSum(portnum, sec.getId(), secacct,longShort);
		if(tsum!=null){
			Tran tran = tranDao.loadPreMaturityDepositTran(portnum, tsum.getCusip(), tsum.getLotnum());
			TranBasic tranbasic = tranBasicDao.loadEntity(TranBasic.class, tran.getTanum().getId());
			String contract=" ";
			if(tranbasic.getContract()!=null){
				contract=tranbasic.getContract();
			}
			lotList.add(secid+"|"+DataConvert.convertCashNoCalFormat(tsum.getAmount())+"|"+tsum.getLotnum()+"|"+contract);
		}
		return lotList;
	}
	
	@Override
	public List<PortTranMapType> getPortTranMapTypeList(String portnum) {
		int manager=managersDao.getDefaultManagerID(portnum);
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		List<PortTranMapType> ptmList=secAcctDao.getPortTranMapTypeList(portnum);
		for(PortTranMapType ptm:ptmList){
			ptm.setManagerID(manager);
			ptm.setPortCurID(portfolio.getCurID());
		}
		
		return ptmList;
	}
	
	@Override
	public double calAmortRate(String portnum, String smf, Date tradeDate, Date settleDate, double amount, double pincipal) {
		List<Date> holidayList=commfuncService.getHolidays(portnum);
		IssueInfo sec=issueInfoDao.loadSecurity(smf);
		CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
		return incomeExpenseCal.calAmortRateByTrade(cfc, tradeDate, settleDate, sec.getExpireDate(), pincipal, 
				sec.getContractSize()*amount, holidayList);
	}
	
	@Override
	public ResultContainer getTradeVouhcerDetailsViewList(String tradeNum) {
		ResultContainer container=new ResultContainer();
		List<VoucherDetailsView> resultList=null;
		TranBasic tran=tranBasicDao.loadTranBasicByID(tradeNum);
		if(tran.getPostStatus().equals(PostStatusType.post)){
			String vouchernum=voucherDao.getVouchernumByTradeNum(tradeNum);
			if(!vouchernum.equals("")){
				resultList=voucherService.fetchVoucherDetails(vouchernum);
				container.setEntityObj(resultList);
			}else{
				try{
					GLCOOABase voucherObj=purSaleJob.genTranVoucher(tradeNum);
					List<VoucherDetails> details=voucherObj.getVoucherDetails();
					List<VoucherDetailsView> vdvList=new ArrayList<VoucherDetailsView>();
					for(VoucherDetails vd:details){
						VoucherDetailsView vdv=new VoucherDetailsView();
						BeanUtils.copyProperties(vd, vdv, "id");
						Coaa coa=coaaDao.loadCoa(vd.getChartNum(), vd.getAccountNum());
						vdv.setAcctname(coa.getAccntname());
						
						vdvList.add(vdv);
					}
					container.setEntityObj(vdvList);
				}catch(JNLException e){
					container.setCorrectFlag(false);
					container.setErrorMsg("交易提交失败");//e.getMessage());
				}
			}
		}else{
			container.setCorrectFlag(false);
			container.setErrorMsg("交易尚未提交");
		}
		return container;
	}
	
	/**
	 * 计算绩效费
	 * @param portnum	投资组合代码
	 * @param cusip		证券代码
	 * @param lotnum	批次
	 * @param peCfg		绩效费配置
	 * @param shares	赎回份额
	 * @return
	 */
	public double[] calPerformanceFee(String portnum, String cusip, int lotnum, double shares){
		double performanceFee=0;
		double clawBackFee=0;
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		PerformanceFeeCfg pfc=performanceFeeCfgDao.loadActivePerformanceFeeCfg(portnum, cusip, lotnum);
		
		if(pfc==null){
			return new double[]{0,0};
		}
		
		//获取上一个关账日的净值
		NavBankZiGuan nbzg=navBankZiGuanDao.getNavBankZiGuan(portnum, cusip, lotnum, portfolio.getLastGLClose());
		double maxl=pfc.getMaxLevel()/100;
		double minl=pfc.getMinLevel()/100;
		if(pfc!=null && nbzg!=null){
			double expenseBasis=0;
			TranSum tsum=tranSumDao.loadTSumByCusipLot(portnum, cusip, lotnum);
			
			if(pfc.getExpenseBasisType()==PerformanceFeeType.expenseBasis1){
				//原始金额
				expenseBasis=tsum.getgCost();
			}else if(pfc.getExpenseBasisType()==PerformanceFeeType.expenseBasis2){
				expenseBasis=0;
			}
			expenseBasis=Math.abs(expenseBasis);
			
			double overPerformReturn=RoundUtil.roundMoney(((nbzg.getNavIncrease()-pfc.getBenchMark()))/100,4);	
			if(overPerformReturn<minl){
				clawBackFee=RoundUtil.roundMoney2Dec((minl-overPerformReturn)*expenseBasis);
			}else{
				double inPercentageFee=0;
				double outPercentageFee=0;
				if(overPerformReturn>maxl){
					inPercentageFee=RoundUtil.roundMoney2Dec((maxl-minl)*expenseBasis*pfc.getInPercentage()/100);
					outPercentageFee = RoundUtil.roundMoney2Dec((overPerformReturn - maxl) * expenseBasis * pfc.getOutPercentage()/100); 
				}else{
					inPercentageFee=RoundUtil.roundMoney2Dec((overPerformReturn-minl)*expenseBasis*pfc.getInPercentage()/100);
				}
				performanceFee=inPercentageFee+outPercentageFee;
			}
			
			double redRate=shares/tsum.getAmount2();
			performanceFee=RoundUtil.roundMoney2Dec(performanceFee*redRate);
			clawBackFee=RoundUtil.roundMoney2Dec(clawBackFee*redRate);
		}
		double[] dols={performanceFee, clawBackFee};
		return dols;
	}
	
	@Override
	public double getSalePortIncomes(String portnum, String cusip, int lotnum, double shares) {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		TranClo clo=secPositionCenter.getSecPosition(portnum, cusip, lotnum, portfolio.getLastTranClose(), false);
		NavBankZiGuan navZG=navBankZiGuanDao.getNavBankZiGuan(portnum, cusip, lotnum, portfolio.getLastGLClose());
		if(navZG!=null){
			double externalShares=navZG.getSharesEnternal();
			return RoundUtil.roundMoney2Dec(clo.getAccrued()*shares/externalShares)*-1;
		}else{
			return 0;
		}
	}
	
	@Override
	public double getIncomeSalePayable(String portnum, String cusip, int lotnum, double shares) {
		double income=0;
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		TranClo clo=secPositionCenter.getSecPosition(portnum, cusip, lotnum, portfolio.getLastTranClose(), false);
		if(clo!=null){
			income=Math.abs(RoundUtil.roundMoney2Dec(clo.getAccrued()*shares/clo.getAmount()));
		}
		return income;
	}
	
	@Override
	public double getPortBatchNAV(String portnum, String cusip, int lotnum) {
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		if(sec.getIssueType().equals(AssetTypes.BankZiGuan) && sec.getOptionType()==OptionType.zgBank){
			return 1;
		}else{
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			NavBankZiGuan navZG=navBankZiGuanDao.getNavBankZiGuan(portnum, cusip, lotnum, portfolio.getLastGLClose());
			if(navZG!=null){
				return RoundUtil.round(navZG.getNetAsset()/navZG.getSharesEnternal(),portfolio.getNavPrecision());
			}else{
				return 0;
			}
		}
	}
	
	@Override
	public double getPortExpensePayable(String portnum, String cusip, int lotnum, int feeType) {
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		TranFeeFollowClo feeclo=tranFeeFollowCloDao.getTranFeeFollowClo(portnum, cusip, lotnum, feeType, portfolio.getLastTranClose());
		return feeclo.getFeeamount();
	}
	
	@Override
	public TranBasic getFCOpenTranBasic(String portnum, String secid,int tranType){
		return tranBasicDao.getFCOpenTranBasic(portnum, secid,tranType);
	}
	
	@Override
	public double getPositionUnits(String portnum, String cusip, Date closeDate) {
		TranCloType tranCloType=new TranCloType(portnum, cusip, closeDate);
		List<TranClo> cloList=secPostCenter.getSecClo(tranCloType, false);
		double units=0;
		for(TranClo clo:cloList) {
			units=units+clo.getAmount();
		}
		return RoundUtil.roundMoney2Dec(units);
	}
	
	@Override
	public Date returnLastDivBasicDate(String secid, Date ledgerDate, int tranType) {
		//根据现金流配置，获取上一个周期的结息付款日
		IssueInfo sec=issueInfoDao.loadSecurity(secid);
		if(sec==null) {
			return new Date();
		}else if(tranType==TransTypes.SWAP_Maturity_Interest) {
			return DateUtil.getLastDay(ledgerDate);
		}else {
			CashFlowCfg cfg=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
			InterestCycleDate cycDate=incomeExpenseCal.getPeriodCycleDate(ledgerDate, cfg, cfg.getCpnType());
			Date lastEndDate=DateUtil.getLastDay(cycDate.getLastInteEndDate());
			if(ledgerDate.compareTo(cycDate.getEndIInteDate())==0 || lastEndDate==null) {
				//目前的计息规则是按照银行来计算的。银行20号是最后一个计息日，而债券20号是第一个计息日。详细请看InterestCycleDate类的注释
				lastEndDate=DateUtil.getLastDay(ledgerDate);
			}
			if(tranType==TransTypes.SWAP_Pay_Interest && cycDate.getLastInteEndDate().compareTo(cfg.getFirstIntDate())==0){
				//利率互换付息  如果如果返回日期是现金流的起息日   应该取付息交易日期的上一天
				lastEndDate=DateUtil.getLastDay(ledgerDate);
			}
			return lastEndDate;
		}
	}
	
	@Override
	public double genUnCapitalizedPercent(int feeDefID, double totalFee) {
		TranFeeDef feeDef=tranFeeDefDao.loadEntity(TranFeeDef.class, feeDefID);
		return RoundUtil.roundMoney2Dec(totalFee*feeDef.getUnCapitalizedPercent()/100);
	}
	
	@Override
	public int isPaywithTrade(int feeDefID) {
		TranFeeDef feeDef=tranFeeDefDao.loadEntity(TranFeeDef.class, feeDefID);
		if(feeDef.getCashPercent()==100)
			return TranFeeTypes.PAY_WITHOUT_TRADE;
		else 
			return TranFeeTypes.PAY_WITH_TRADE;
	}
}
