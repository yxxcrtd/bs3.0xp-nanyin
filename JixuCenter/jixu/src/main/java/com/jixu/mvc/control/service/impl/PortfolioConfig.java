package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.SecPositionCenter;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.ExpenseBaseType;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.LotType;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.types.PortfolioTypes;
import com.jixu.common.types.TranCloType;
import com.jixu.common.util.ConverIDNum;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.Pages;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.ManagersDao;
import com.jixu.dao.intf.NavBankZiGuanDao;
import com.jixu.dao.intf.NavDao;
import com.jixu.dao.intf.PerformanceFeeCfgDao;
import com.jixu.dao.intf.PortExpenseCfgDao;
import com.jixu.dao.intf.PortfolioClassIDMapDao;
import com.jixu.dao.intf.PortfolioClientDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.PortfolioInterfaceMapDao;
import com.jixu.dao.intf.ProvinceDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.TranFeeFollowCloDao;
import com.jixu.mvc.control.model.PortfolioSummary;
import com.jixu.mvc.control.model.SecPositionModel;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.PortfolioCfgService;
import com.jixu.mvc.control.service.SecCashService;
import com.orm.obj.CoaaGroup;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.NavBankZiGuan;
import com.orm.obj.PerformanceFeeCfg;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioClassIDMap;
import com.orm.obj.PortfolioClient;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.PortfolioInterfaceMap;
import com.orm.obj.Province;
import com.orm.obj.TranBasic;
import com.orm.obj.TranClo;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeFollowClo;

@Service("portCfgService")
public class PortfolioConfig implements PortfolioCfgService{
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private PortExpenseCfgDao portExpenseCfgDao;
	
	@Autowired
	private NavBankZiGuanDao navBankZiGuanDao;
	
	@Autowired
	private PortfolioInterfaceMapDao portfolioInterfaceMapDao;
	
	@Autowired
	private PortfolioClientDao portfolioClientDao;
	
	@Autowired
	private PerformanceFeeCfgDao performanceFeeCfgDao;
	
	@Autowired
	private TranFeeFollowCloDao tranFeeFollowCloDao;
	
	@Autowired
	private ManagersDao managersDao;
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private PortfolioClassIDMapDao portfolioClassIDMapDao;
	
	@Autowired
	private InterfaceSourceDao interfaceSourceDao;
	
	@Autowired
	private NavDao navDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Resource(name="SecCashService")
	private SecCashService secCashService;
	
	@Resource(name="commfuncService")
	private CommFuncService comFuncService;
	
	@Resource(name = "configService")
	private ConfigService configService;
	
	@Autowired
	protected SecPositionCenter secPositionCenter;
	
	public List<PortfolioSummary> getPortfolioSummary(int clientCode, int portType, String portName) {
		
		List<PortfolioSummary> summaryList=new ArrayList<PortfolioSummary>();
		List<PortfolioHdr> portfolios=portfolioHdrDao.getPortfolios(clientCode, portType, portName);
		HashMap<Integer, PortfolioClient> clients=getAllClients();
		for(PortfolioHdr portfolio:portfolios){
			PortfolioSummary summary=new PortfolioSummary();
			summary.setPortnum(portfolio.getId());
			summary.setPortCode(portfolio.getShortCode());
			summary.setPortName(portfolio.getPortName());
			summary.setPortShortName(portfolio.getShortName());
			summary.setPortType(PortfolioTypes.portfolioTypesHM.get(portfolio.getPortType()));
			summary.setClientName(clients.get(portfolio.getPortfolioClient()).getClientName());
			summary.setCurCode(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));
			summary.setPortInitDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getInceptionDate()));
			summary.setNextCloseDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getNextTranClose()));
			summary.setNextCloseYearDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getNextYealClose()));
			
			if(JixuUserUtil.isJoyinTablesFlag()) {
				summary.setNav(0);
				summary.setAccNav(0);
				summary.setNetAsset(0);
				summary.setShares(0);
			}else {
				NavAssetObj navObj=navDao.getLatestNAV(portfolio.getId(), new Date());
				summary.setNav(navObj.getNavPerShare());
				summary.setAccNav(navObj.getAccNavPerShare());
				summary.setNetAsset(navObj.getNetAsset());
				summary.setShares(navObj.getShares());
			}
			
			summaryList.add(summary);
		}
		return summaryList;
	}
	
	@Override
	public List<PortfolioSummary> getPortfolioSummary(int userid, int clientCode, int portType, String portName) {
		List<PortfolioSummary> summaryList=new ArrayList<PortfolioSummary>();
		List<PortfolioHdr> portfolios=portfolioHdrDao.getPortfoliosByUserID(userid, clientCode, portType, portName);
		HashMap<Integer, PortfolioClient> clients=getAllClients();
		for(PortfolioHdr portfolio:portfolios){
			PortfolioSummary summary=new PortfolioSummary();
			summary.setPortnum(portfolio.getId());
			summary.setPortCode(portfolio.getShortCode());
			summary.setPortName(portfolio.getPortName());
			summary.setPortShortName(portfolio.getShortName());
			summary.setPortType(PortfolioTypes.portfolioTypesHM.get(portfolio.getPortType()));
			summary.setClientName(clients.get(portfolio.getPortfolioClient()).getClientName());
			summary.setCurCode(comFuncService.loadFxCurrencyCode(portfolio.getCurID()));
			summary.setPortInitDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getInceptionDate()));
			summary.setNextCloseDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getNextTranClose()));
			summary.setNextCloseYearDate(DateUtil.convertD2YYYY_MM_DD(portfolio.getNextYealClose()));
			
			NavAssetObj navObj=navDao.getLatestNAV(portfolio.getId(), new Date());
			summary.setNav(navObj.getNavPerShare());
			summary.setAccNav(navObj.getAccNavPerShare());
			summary.setNetAsset(navObj.getNetAsset());
			summary.setShares(navObj.getShares());
			
			summaryList.add(summary);
		}
		return summaryList;
	}
	
	public List<PortExpenseCfg> loadExpenseListByPortnum(String portnum) {
		if(JixuUserUtil.isJoyinTablesFlag())
			return new ArrayList<PortExpenseCfg>();
		else
			return portExpenseCfgDao.loadExpenseListByPortnum(portnum);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createPortfolio(List<Object> param_list) {
		ResultContainer container=new ResultContainer();		
		PortfolioHdr portfolio=new PortfolioHdr();
		portfolio.setPortfolioClient(Integer.valueOf(param_list.get(0).toString()));
		portfolio.setShortName(param_list.get(1).toString());
		portfolio.setShortCode(param_list.get(2).toString());
		portfolio.setProvince(new Province(Integer.valueOf(param_list.get(4).toString())));
		portfolio.setCurID(param_list.get(5).toString());
		portfolio.setLotSelection(Integer.valueOf(param_list.get(6).toString()));
		portfolio.setHolidaySD(param_list.get(7).toString());
		
		String startDateStr=param_list.get(8).toString();
		if(startDateStr.equals(""))
			startDateStr="2000-01-01";
		portfolio.setInceptionDate(DateUtil.convertStr2Date2(startDateStr));
		portfolio.setPortType(Integer.valueOf(param_list.get(9).toString()));
		portfolio.setCoaahdr(param_list.get(10).toString());
		portfolio.setCoaaGroup(new CoaaGroup(Integer.valueOf(param_list.get(11).toString())));

		int navPrecision=ConverIDNum.converIDNumByString(param_list.get(12).toString());
		portfolio.setNavPrecision(navPrecision);
		portfolio.setBookNBDays(Integer.valueOf(param_list.get(13).toString()));
		portfolio.setWkndValuationClose(Integer.valueOf(param_list.get(14).toString()));
		portfolio.setTradeSettleLedger(Integer.valueOf(param_list.get(15).toString()));
		portfolio.setClassid(Integer.valueOf(param_list.get(16).toString()));
		portfolio.setManagerGroup(Integer.valueOf(param_list.get(17).toString()));
		if(param_list.size()==19){
			portfolio.setJzgyFlag(Boolean.valueOf(param_list.get(18).toString()));
			portfolio.setSyzfeFlag(Boolean.valueOf(param_list.get(19).toString()));
		}
		
		portfolio.setPortName(portfolio.getShortName());
		portfolio.setSinglelot(LotType.SingleLot);
		
		portfolio.setLastTranClose(portfolio.getInceptionDate());
		portfolio.setNextTranClose(portfolio.getInceptionDate());
		portfolio.setLastGLClose(portfolio.getInceptionDate());
		portfolio.setNextGLClose(portfolio.getInceptionDate());
		
		int year=DateUtil.getYear(portfolio.getInceptionDate());
		String endYearStr=year+"-12-31";
		portfolio.setNextYealClose(DateUtil.convertStr2Date2(endYearStr));
		portfolio.setLastActiveDate(new Date());
		
		boolean sameCodeExist=portfolioHdrDao.checkSamePortNameExist(portfolio.getShortCode());
		if(sameCodeExist){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.port_sameCode_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.port_sameCode_exist) + ": " + portfolio.getShortCode());
		}else{
			portfolioHdrDao.saveEntity(portfolio);
			container.setEntityObj(portfolio);
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updatePortfolio(PortfolioHdr portfolio) {
		portfolio.setPortName(portfolio.getShortName());
		portfolioHdrDao.updatePortflio(portfolio);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer savePortExpense(String portnum, List<Object> param_list) {
		ResultContainer container=new ResultContainer();
		PortExpenseCfg expense=new PortExpenseCfg();
		expense.setFixFeeFlag(Integer.valueOf(param_list.get(0).toString()));
		expense.setPortFeeName(param_list.get(1).toString());
		expense.setClassService(param_list.get(2).toString());
		expense.setBaseAcct(param_list.get(3).toString());
		expense.setDrAcct(param_list.get(4).toString().split("\\|")[0]);
		expense.setCrAcct(param_list.get(5).toString().split("\\|")[0]);
		if(!param_list.get(6).toString().equals("")){	
			expense.setExpenseAmount(Double.valueOf(param_list.get(6).toString()));;
		}
		if(!param_list.get(7).toString().trim().equals("")){
			expense.setLowestFee(Double.valueOf(param_list.get(7).toString()));
		}
		if(!param_list.get(8).toString().trim().equals("")){
			expense.setHighestFee(Double.valueOf(param_list.get(8).toString()));
		}
		expense.setIntCode(Integer.valueOf(param_list.get(9).toString()));
		expense.setCpnfReq(Integer.valueOf(param_list.get(10).toString()));
		expense.setCpnMo(Integer.valueOf(param_list.get(11).toString()));
		
		if(!param_list.get(12).toString().trim().equals("")){
			expense.setCpnDate(Integer.valueOf(param_list.get(12).toString()));
		}
		expense.setStartDate(DateUtil.convertStr2Date2(param_list.get(13).toString()));
		expense.setExpireDate(DateUtil.convertStr2Date2(param_list.get(14).toString()));
		
		if(param_list.get(15).toString().equals("true")){
			expense.setNewNavFeeFlag(ExpenseBaseType.NewAssetExpense);
		}else{
			expense.setNewNavFeeFlag(ExpenseBaseType.UnNewAssetExpense);
		}
		expense.setOrderNum(Integer.valueOf(param_list.get(16).toString()));
		
		PortfolioHdr portfolio=comFuncService.loadPortfolio(portnum);
		expense.setPortfolio(portfolio);
		
		boolean checkFlag=portExpenseCfgDao.checkSamePortExpenseExise(portnum, expense.getPortFeeName());
		if(checkFlag){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.portexpense_name_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.portexpense_name_exist) + ": " + expense.getPortFeeName());
		}else{
			portExpenseCfgDao.saveEntity(expense);
			container.setEntityObj(expense);
		}
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deletePortExpense(int expenseID) {
		portExpenseCfgDao.delExpense(expenseID);
	}
	
	public PortExpenseCfg loadPortExpenseCfgByID(int expenseID) {
		return portExpenseCfgDao.loadEntity(PortExpenseCfg.class, expenseID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updatePortExpense(String portID, int expID, List<Object> param_list) {
		PortExpenseCfg expense=(PortExpenseCfg)portExpenseCfgDao.loadEntity(PortExpenseCfg.class, expID);
		expense.setFixFeeFlag(Integer.valueOf(param_list.get(0).toString()));
		expense.setPortFeeName(param_list.get(1).toString());
		expense.setClassService(param_list.get(2).toString());
		expense.setBaseAcct(param_list.get(3).toString());
		expense.setDrAcct(param_list.get(4).toString().split("\\|")[0]);
		expense.setCrAcct(param_list.get(5).toString().split("\\|")[0]);
		if(!param_list.get(6).toString().equals("")){	
			expense.setExpenseAmount(Double.valueOf(param_list.get(6).toString()));;
		}
		if(!param_list.get(7).toString().trim().equals("")){
			expense.setLowestFee(Double.valueOf(param_list.get(7).toString()));
		}
		if(!param_list.get(8).toString().trim().equals("")){
			expense.setHighestFee(Double.valueOf(param_list.get(8).toString()));
		}
		expense.setIntCode(Integer.valueOf(param_list.get(9).toString()));
		expense.setCpnfReq(Integer.valueOf(param_list.get(10).toString()));
		expense.setCpnMo(Integer.valueOf(param_list.get(11).toString()));
		
		if(!param_list.get(12).toString().trim().equals("")){
			expense.setCpnDate(Integer.valueOf(param_list.get(12).toString()));
		}
		expense.setStartDate(DateUtil.convertStr2Date2(param_list.get(13).toString()));
		expense.setExpireDate(DateUtil.convertStr2Date2(param_list.get(14).toString()));
		
		if(param_list.get(15).toString().equals("true")){
			expense.setNewNavFeeFlag(ExpenseBaseType.NewAssetExpense);
		}else{
			expense.setNewNavFeeFlag(ExpenseBaseType.UnNewAssetExpense);
		}
		expense.setOrderNum(Integer.valueOf(param_list.get(16).toString()));
		expense.setPortfolio(new PortfolioHdr(portID));
		portExpenseCfgDao.updateEntity(expense);
	}
	
	@Override
	public List<PortfolioInterfaceMap> loadPortInterfaceMapImport(String portID) {
		PortfolioHdr portfolio=comFuncService.loadPortfolio(portID);
		if(portfolio != null){
			return portfolioInterfaceMapDao.loadPortInterfaceMapListByPortNumImport(portID);
		}else{
			return null;
		}
	}
	
	@Override
	public List<PortfolioInterfaceMap> loadPortInterfaceMapExport(String portID) {
		PortfolioHdr portfolio=comFuncService.loadPortfolio(portID);
		if(portfolio != null){
			return portfolioInterfaceMapDao.loadPortInterfaceMapListByPortNumExport(portID);
		}else{
			return null;
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deletePortfolioInterfaceMap(int id) {
		PortfolioInterfaceMap piMap=portfolioInterfaceMapDao.loadEntity(PortfolioInterfaceMap.class, id);
		int sourceType=piMap.getIneterfaceSource().getSourceType();
		portfolioInterfaceMapDao.deletePortInterfaceMap(piMap,sourceType);
	}
	
	@Override
	public HashMap<Integer, String> loadManagersByPortnum(String portnum) {
		List<Managers> managersList=managersDao.loadManagersByPortnum(portnum);
		HashMap<Integer,String> managerHM=new HashMap<Integer, String>();
		for(Managers manager:managersList){
			managerHM.put(manager.getId(), manager.getManagerName());
		}
		return managerHM;
	}
	
	@Override
	public List<String> loadPortfolioPosition(String portnum, String endDateStr) {
		Pages<SecPositionModel> pages=new Pages<SecPositionModel>();
		Date endDate=DateUtil.convertStr2Date(endDateStr.replaceAll("-", ""));
		pages=secCashService.getSecPositions(pages,portnum, endDate);
		List<SecPositionModel> positionList=pages.getResultList();
		List<String> secList=new ArrayList<String>();
		for(SecPositionModel position:positionList){
			if(position.getAmount()>0){
				secList.add(position.getSecID()+"|"+position.getSecDesc());
			}
		}
		return secList;
	}
	
	public List<PortfolioHdr> loadPortfolios(List<Object> portList) {
		String[] ports=new String[portList.size()];
		for(int i=0;i<portList.size();i++){
			ports[i]=portList.get(i).toString();
		}
		
		return portfolioHdrDao.loadPortfoliosByIds(ports);
	}
	
	public PortfolioHdr loadPortfolio(String portnum){
		return comFuncService.loadPortfolio(portnum);
	}
	
	@Override
	public String loadCountryIDByProvinceID(int provinceID){
		if(JixuUserUtil.isJoyinTablesFlag()) {
			return JixuDictCode.DEFAULT_COUNTRY_CODE;
		}else {
			Province province=(Province)provinceDao.loadEntity(Province.class, provinceID);
			return province.getCountry();
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public PortfolioInterfaceMap createPortInterfaceMap(String portnum, List<Object> paramsList) {
		int sourceType=Integer.valueOf(paramsList.get(0).toString());
		int rpsectype=0;
		if(!paramsList.get(1).toString().toLowerCase().equals("all")){
			rpsectype=Integer.valueOf(paramsList.get(1).toString());
		}
		int interfaceSourceID=Integer.valueOf(paramsList.get(2).toString());
		int autoType=Integer.valueOf(paramsList.get(3).toString());
		
		PortfolioInterfaceMap piMap=new PortfolioInterfaceMap();
		piMap.setPortfolio(new PortfolioHdr(portnum));
		piMap.setIneterfaceSource(new InterfaceSource(interfaceSourceID));
		piMap.setDefaultImportType(autoType);
		piMap.setRpSecType(rpsectype);

		portfolioInterfaceMapDao.createPortInterfaceMap(piMap, sourceType);
		return piMap;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public PortfolioInterfaceMap createPortInterfaceExpMap(String portnum, List<Object> paramsList) {
		int interfaceSourceID=Integer.valueOf(paramsList.get(1).toString());
		int opStep=Integer.valueOf(paramsList.get(2).toString());
		int importType=Integer.valueOf(paramsList.get(3).toString());
		
		PortfolioInterfaceMap piMap=new PortfolioInterfaceMap();
		piMap.setPortfolio(new PortfolioHdr(portnum));
		piMap.setIneterfaceSource(new InterfaceSource(interfaceSourceID));
		piMap.setOperationStep(opStep);
		piMap.setDefaultImportType(importType);

		portfolioInterfaceMapDao.saveEntity(piMap);
		
		return piMap;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updatePortInterfaceExpMap(int mapID, List<Object> paramsList) {
		PortfolioInterfaceMap pim=portfolioInterfaceMapDao.loadEntity(PortfolioInterfaceMap.class, mapID);
		int interfaceSourceID=Integer.valueOf(paramsList.get(1).toString());
		int opStep=Integer.valueOf(paramsList.get(2).toString());
		int importType=Integer.valueOf(paramsList.get(3).toString());
		
		pim.setIneterfaceSource(new InterfaceSource(interfaceSourceID));
		pim.setOperationStep(opStep);
		pim.setDefaultImportType(importType);

		portfolioInterfaceMapDao.updateEntity(pim);
	}
	
	@Override
	public HashMap<Integer, String> loadInterfaceSourceByPortnum(String portnum) {
		List<InterfaceSource> sourceList=interfaceSourceDao.loadInterfaceSourceByPortnum(portnum);;
		HashMap<Integer, String> sourceHM=new HashMap<Integer, String>();
		for(InterfaceSource source:sourceList){
			sourceHM.put(source.getSourceID(), source.getSourceName());
		}
		return sourceHM;
	}
	
	@Override
	public List<InterfaceSource> loadInterfaceSource(String[] ports, int sourceType) {
		return interfaceSourceDao.loadInterfaceSource(ports, sourceType);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	@Caching(evict={@CacheEvict(value="portDataCache", key="'PortInterfaceMap_'+#portnum+#sourceType+#rpSecType"),
			@CacheEvict(value="portDataCache", key="'ExportPortNum_'+#portnum")})
	public void updatePortInterfaceMap(int mapID, String portnum, int sourceType,
			int rpSecType, int sourceID, int importType) {
		PortfolioInterfaceMap pim=portfolioInterfaceMapDao.loadEntity(PortfolioInterfaceMap.class, mapID);
		pim.setRpSecType(rpSecType);
		pim.setIneterfaceSource(new InterfaceSource(sourceID));
		pim.setDefaultImportType(importType);
		
		portfolioInterfaceMapDao.updateEntity(pim);
	}


	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createPortfolioClassIDMap(String classID, List<String> portIDList) {
		portfolioClassIDMapDao.deletePortfolioClassIDMap(classID);
		for(String portID:portIDList){
			PortfolioClassIDMap map=new PortfolioClassIDMap();
			map.setClassID(classID);
			map.setPortID(portID);
			portfolioClassIDMapDao.saveEntity(map);
		}
	}

	@Override
	public List<PortfolioHdr> loadPortfolios(String portID) {
		return portfolioHdrDao.loadPortfolios(portID);
	}

	@Override
	public HashMap<Integer, String> loadTranFeeDef() {
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		List<TranFeeDef> feeDefList = configService.loadTranFeeDefList();
		for (TranFeeDef td : feeDefList) {
			map.put(td.getId(), td.getFeename());
		}
		return map;
	}
	
	@Override
	public HashMap<String, Object> getBankZiGuanInfo(String portnum, Date startDate, Date endDate) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = new ArrayList<>(Arrays.asList("编号","产品代码", "产品名称", "批次信息", "起息日期", "截止日期", "原始金额", "剩余金额", "客户收益"));
		List<List<Object>> data = new ArrayList<List<Object>>();
		HashMap<String, Double> gCostMap=new HashMap<String, Double>();
		List<String> objList=new ArrayList<String>(); //用来动态添加title的费用
		
		
		
		if(portnum!=null && !portnum.equals("")) {
			PortfolioHdr portfolio=comFuncService.loadPortfolio(portnum);
			if(endDate.after(portfolio.getLastGLClose())){
				endDate=portfolio.getLastGLClose();
			}
			
			List<TranClo> tlist=tranCloDao.loadTranCloListByAssetType(portnum, endDate, AssetTypes.BankZiGuan);	//获取资管的头寸
			HashMap<Integer, String> tdfMap=loadTranFeeDef();  //费用id和名称
			//查询TranClo对应的TranFee
			HashMap<String, List<TranFeeFollowClo>> tfcMap=new HashMap<String, List<TranFeeFollowClo>>();
			List<TranFeeFollowClo> list=tranFeeFollowCloDao.loadTranFeeFollowClo(portnum, endDate);
			String id="";
			for (TranFeeFollowClo tfc : list) {
				id=tfc.getTranClo().getId();
				if(tfcMap.containsKey(id))
					tfcMap.get(id).add(tfc);
				else{
					List<TranFeeFollowClo> tmpList=new ArrayList<TranFeeFollowClo>();
					tmpList.add(tfc);
					tfcMap.put(id, tmpList);
				}
			}
			
			for (TranClo clo : tlist) {
				gCostMap.put(clo.getCloseDate()+"_"+clo.getCusip()+"_"+clo.getLotnum(), clo.getgCost());
				List<TranFeeFollowClo> feeCloList=tfcMap.get(clo.getId());  //取出TranFee
				if(feeCloList!=null){
					for(TranFeeFollowClo feeClo:feeCloList){
						if(!objList.contains(tdfMap.get(feeClo.getTranFeeDefID()))){  //如果objList中没有这个费用名称  则添加进去 
							objList.add(tdfMap.get(feeClo.getTranFeeDefID()));
						}
					}
				}
			}
			titles.addAll(objList);  //动态添加title
			
			titles.add("净资产");
			titles.add("份额(内)");
			titles.add("单位净值(内)");
			titles.add("份额(外)");
			titles.add("单位净值(外)");
			
			result.put("titles", titles);
			
			List<NavBankZiGuan> navzgList=navBankZiGuanDao.loadNavBankZiGuan(portnum, endDate);
			
			for (TranClo tclo : tlist) {
				ArrayList<Object> item = new ArrayList<Object>();
				item.add(tclo.getId());
				IssueInfo ii=issueInfoDao.loadIssueByCusip(tclo.getCusip());  //从缓存中取出IssueInfo
				item.add(ii.getSecurityid());
				item.add(ii.getSecurityname());
				item.add(tclo.getLotnum());  //批次
				item.add(DateUtil.convertD2YYYY_MM_DD(tclo.getLsttradeDate()));  //起息日期
				item.add(DateUtil.convertD2YYYY_MM_DD(tclo.getAltDate()));     //截止日期
				if(gCostMap.get(tclo.getLsttradeDate()+"_"+tclo.getCusip()+"_"+tclo.getLotnum())==null)
					item.add(DataConvert.convertCash2US(tclo.getgCost()));
				else
					item.add(DataConvert.convertCash2US(gCostMap.get(tclo.getLsttradeDate()+"_"+tclo.getCusip()+"_"+tclo.getLotnum())));
				item.add(DataConvert.convertCash2US(tclo.getgCost()));
				item.add(DataConvert.convertCash2US(tclo.getAccrued())+"("+tclo.getAltRate()+"%)");
				List<TranFeeFollowClo> feeCloList=tfcMap.get(tclo.getId());  //遍历TranClo对应的TranFee
				for (int i = 0; i < objList.size(); i++) {    //根据objList.size()的个数确定列数
					if(feeCloList!=null){
						double feeAmout=0;
						double feeRate=0;
						for (TranFeeFollowClo feeClo : feeCloList) {   //当前列   判断费用集合里面是否含有当前列的值 ,如果不包含显示-
							if(tdfMap.get(feeClo.getTranFeeDefID()).equals(objList.get(i))){
								feeAmout=feeClo.getFeeamount();
								feeRate=feeClo.getFeeRate();
							}
						}
						if(feeAmout!=0)     
							item.add(DataConvert.convertCash2US(feeAmout)+" ("+feeRate+"%)");
						else
							item.add("-");
					}else{
						item.add("-");   //如果一个费用都没有  显示时都是-
					}
				}
				
				
				for(NavBankZiGuan zg:navzgList){
					if(zg.getCusip().equals(tclo.getCusip()) && zg.getLotnum()==tclo.getLotnum()){
						item.add(zg.getNetAsset());
						item.add(zg.getSharesInternal());
						if(zg.getSharesInternal()!=0){
							item.add(RoundUtil.round(zg.getNetAsset()/zg.getSharesInternal(),3));
							item.add(zg.getSharesEnternal());
							item.add(RoundUtil.round(zg.getNetAsset()/zg.getSharesEnternal(),3));
						}else{
							item.add(0);
							item.add(0);
							item.add(0);
						}
						break;
					}
				}
				data.add(item);
			}
		}
		
		result.put("data", data);
		return result;
	}
	
	@Override
	public PerformanceFeeCfg loadPerformanceFeeCfg(String portnum, String cusip, int lotnum) {
		return performanceFeeCfgDao.loadPerformanceFeeCfg(portnum, cusip, lotnum);
	}
	
	//递归查询投资组合
	@Override
	public List<String> loadPortfolioClassIDRecursion(String classid) {
		List<String> totalList=new ArrayList<String>();
		if(JixuUserUtil.isJoyinTablesFlag()) {
			totalList.add(classid);
		}else {
			//取到该投资组合下第一层的子组合(包括该投资组合)
			List<String> portfolioClassIDList=portfolioHdrDao.loadPortfolioClassIDMap(classid);
			if(portfolioClassIDList.size()>0){
				totalList.addAll(portfolioClassIDList);
				for (String portID : portfolioClassIDList) {
					//对该组合下的所有子组合进行递归查询
					if(!portID.equals(classid)){
						loadPortfolioCIDRecursion(portID,totalList);
					}
				}
			}
		}
		//返回该组合下所有子组合
		return totalList;
	}
	
	@Override
	public void loadPortfolioCIDRecursion(String classid,List<String> totalList){
		//查询子组合下的子投资组合
		List<String> list=portfolioHdrDao.loadPortfolioCIDRecursion(classid);
		if(list.size()>0){
			//将查询的子组合存入到totalList
			totalList.addAll(list);
			//继续递归向后查询子组合
			for (String portID : list) {
				if(!portID.equals(classid)){
					loadPortfolioCIDRecursion(portID,totalList);
				}
			}
		}
	}
	
	/**
	 * 获取所有的客户
	 * @return
	 */
	private HashMap<Integer, PortfolioClient> getAllClients(){
		List<PortfolioClient> clients=portfolioClientDao.fetchClients();
		HashMap<Integer, PortfolioClient> clientsMap=new HashMap<Integer, PortfolioClient>();
		clients.forEach(client->clientsMap.put(client.getClientid(), client));
		return clientsMap;
	}

	/**
	 * 获取该投资组合下所有股票现金分红的数量和金额
	 * @return
	 */
	@Override
	public Object[] getPortfolioStockCashDivident(TranBasic tranAdvice) {
		
		List<Double> cashlist=new ArrayList<Double>();
		Date tradeDate=tranAdvice.getTradedate();
		List<TranClo> cloList=new ArrayList<TranClo>();
		if(tradeDate.compareTo(tranAdvice.getDivBasicDate())==0){
			TranCloType tranCloType=new TranCloType(tranAdvice.getPortfolio(), tranAdvice.getCusip().getId(), tranAdvice.getDivBasicDate());
			cloList=secPositionCenter.getSecClo(tranCloType, false);
		}else{
			List<String> portList=loadPortfolioClassIDRecursion(tranAdvice.getPortfolio());
			cloList=tranCloDao.loadTranCloListWithCusip(tranAdvice.getPortfolio(),portList, tranAdvice.getCusip().getId(), tranAdvice.getDivBasicDate(), false);
		}
		
		//留接口,如果以后可以强制输入分红金额,则可以直接使用。目前是0，需要用比例计算
		double alamount=tranAdvice.getIntatmat();
		//获取总头寸
		double totalAmount=0;
		for(TranClo clo:cloList){
			totalAmount=totalAmount+clo.getAmount();
		}
		
		double alcash=0;
		double alcashTmpTotal=0;
		for(int i=0;i<cloList.size();i++){
			TranClo clo=cloList.get(i);
			if(alamount>0){
				//这里表示强制的输入了分红的金额
				if(i==cloList.size()-1){
					//暂且把利息计算的小数差额放到最后一个
					alcash=totalAmount-alcashTmpTotal;
				}else{
					alcash=RoundUtil.roundMoney2Dec(alamount*clo.getAmount()/totalAmount);
					alcashTmpTotal=alcashTmpTotal+alcash;
				}
			}else{
				alcash=RoundUtil.roundMoney2Dec(clo.getAmount()*tranAdvice.getAltRate());
			}
			cashlist.add(alcash);
		}
		return new Object[] { totalAmount, cashlist };
	}
}
