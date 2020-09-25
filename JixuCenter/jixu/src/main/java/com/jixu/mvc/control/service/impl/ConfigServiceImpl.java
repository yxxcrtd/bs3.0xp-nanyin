package com.jixu.mvc.control.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.func.OutSystemInterface;
import com.jixu.common.func.SystemSelect;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.tool.QuartzManager;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.MsgPackage;
import com.jixu.common.util.Pages;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkCashAcctDao;
import com.jixu.dao.intf.BokBookSetDao;
import com.jixu.dao.intf.BokSubjectDao;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.dao.intf.CashFlowCfgDao;
import com.jixu.dao.intf.CashFlowFloatRateDao;
import com.jixu.dao.intf.CashFlowParDao;
import com.jixu.dao.intf.CashFlowPaymentDao;
import com.jixu.dao.intf.CashFlowRateDao;
import com.jixu.dao.intf.CoaaDao;
import com.jixu.dao.intf.CoaaGroupDao;
import com.jixu.dao.intf.CoaahdrDao;
import com.jixu.dao.intf.CountryDao;
import com.jixu.dao.intf.ExchangeCfgDao;
import com.jixu.dao.intf.FxCurrencyDao;
import com.jixu.dao.intf.GlCoaaMapDao;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.IssuerDao;
import com.jixu.dao.intf.ManagerGroupDao;
import com.jixu.dao.intf.ManagersDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.ProvinceDao;
import com.jixu.dao.intf.RPSecTypeDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.dao.intf.TranFeeDefDao;
import com.jixu.dao.intf.TranFeeTypeMapDao;
import com.jixu.data.service.impl.OutDataCenter;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.framework.msg.MsgService;
import com.jixu.msg.dto.CurrencyDto;
import com.jixu.mvc.control.model.GlCoaaMapModel;
import com.jixu.mvc.control.model.IssuerModel;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.jixu.mvc.control.model.SecAccountModel;
import com.jixu.mvc.control.model.VouchersModel;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BokBookSet;
import com.orm.obj.BokSubject;
import com.orm.obj.Broker;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowFloatRate;
import com.orm.obj.CashFlowPar;
import com.orm.obj.CashFlowPayment;
import com.orm.obj.CashFlowRate;
import com.orm.obj.Coaa;
import com.orm.obj.CoaaGroup;
import com.orm.obj.Coaahdr;
import com.orm.obj.Country;
import com.orm.obj.ExchangeCfg;
import com.orm.obj.FxCurrency;
import com.orm.obj.GlCoaaMap;
import com.orm.obj.IssueInfo;
import com.orm.obj.Issuer;
import com.orm.obj.ManagerGroup;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Province;
import com.orm.obj.RPSecType;
import com.orm.obj.SecAcct;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeTypeMap;

@Service("configService")
public class ConfigServiceImpl implements ConfigService{
	
	//private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Resource(name="msgPackage")
	private MsgPackage msgPackage;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="quartzManager")
	private QuartzManager jobManager;
	
	@Resource(name="commfuncService")
	private CommFuncService comFuncService;
	
	@Autowired
	private OutDataCenter outDataCenter;
	
	@Resource(name="outSystemInterface")
	private OutSystemInterface outSystem;
	
	@Autowired
	private CoaaDao coaaDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private CoaaGroupDao coaaGroupDao;
	
	@Autowired
	private CoaahdrDao coaahdrDao;
	
	@Autowired
	private GlCoaaMapDao glCoaaMapDao;
	
	@Autowired
	private BrokerDao brokerDao;
	
	@Autowired
	private RPSecTypeDao rpSecTypeDao;
	
	@Autowired
	private IssuerDao issuerDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private ExchangeCfgDao exchangeCfgDao;
	
	@Autowired
	private TranFeeDefDao tranFeeDefDao;
	
	@Autowired
	private FxCurrencyDao fxCurrencyDao;
	
	@Autowired
	private TranFeeTypeMapDao tranFeeTypeMapDao;
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private BkCashAcctDao bkCashAcctDao;
	
	@Autowired
	private ManagerGroupDao managerGroupDao;
	
	@Autowired
	private ManagersDao managersDao;
	
	@Autowired
	private InterfaceSourceDao interfaceSourceDao;
	
	@Autowired
	private CashFlowCfgDao cashFlowCfgDao;
	
	@Autowired
	private CashFlowPaymentDao cashFlowPaymentDao;
	
	@Autowired
	private CashFlowRateDao cashFlowRateDao;
	
	@Autowired
	private CashFlowFloatRateDao cashFlowFloatRateDao;
	
	@Autowired
	private CashFlowParDao cashFlowParDao;
	
	@Autowired
	private BokBookSetDao bokBookSetDao;
	
	@Autowired
	private BokSubjectDao bokSubjectDao;
	
	public List<List<Object>> loadCoaaDetailsAllByChartNum(String chartnum) {
		List<List<Object>> result=new ArrayList<List<Object>>();
		
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<Coaa> coaaList=coaaDao.loadCoaaDetailsAllByChartNum(chartnum);
			for(Coaa coa:coaaList){
	            List<Object> item = new ArrayList<Object>();
	            item.add(coa.getId());
	            item.add(coa.getAccntnum());
	            item.add(coa.getAccntname());
	            item.add(coa.getAccntcategory());
	            if(coa.getClosetoaccntnum()==null){
	                item.add("");
	            }else{
	                item.add(coa.getClosetoaccntnum());
	            }
	            result.add(item);
	        }
		}else{
			//兆尹的科目表，账套信息
			List<BokSubject> bokList=bokSubjectDao.loadBokSubjectBySetID(chartnum);
			for(BokSubject bok:bokList){
	            List<Object> item = new ArrayList<Object>();
	            item.add(bok.getBokSubjectPK().getSubject_no());
	            item.add(bok.getBokSubjectPK().getSubject_no());
	            item.add(bok.getSubject_name());
	            item.add(0);
	            item.add("");
	            result.add(item);
	        }
		}
		return result;
	}
	
	public List<String> loadCoaaDetailsByChartNum(String chartnum) {
		List<String> result = new ArrayList<String>();
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<Coaa> coaaList=coaaDao.loadCoaaDetailsByChartNum(chartnum);
	        for(Coaa coa:coaaList){
		        result.add(coa.getAccntnum()+"|" + coa.getAccntname());
	        }
		}else{
			List<BokSubject> bokSubjectList=bokSubjectDao.loadBokSubjectLevel3BySetID(chartnum);
			for(BokSubject bok:bokSubjectList){
				result.add(bok.getBokSubjectPK().getSubject_no()+"|" + bok.getSubject_name());
			}
		}
		return result;
	}
	
	@Override
	public HashMap<String, String> getCoaaDetailsMapByCharNum(String chartnum) {
		HashMap<String, String> result=new HashMap<String, String>();
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<Coaa> coaaList=coaaDao.loadCoaaDetailsByChartNum(chartnum);
			for(Coaa coa:coaaList){
		        result.put(coa.getAccntnum()+"|" + coa.getAccntname(), coa.getAccntnum()+"|" + coa.getAccntname());
			}
		}else{
			List<BokSubject> bokSubjectList=bokSubjectDao.loadBokSubjectLevel3BySetID(chartnum);
			for(BokSubject bok:bokSubjectList){
				result.put(bok.getBokSubjectPK().getSubject_no()+"|" + bok.getSubject_name(), bok.getBokSubjectPK().getSubject_no()+"|" + bok.getSubject_name());
			}
		}
		return result;
	}
	
	public List<Coaa> loadCoaaDetailsByPortNum(String portnum) {
		return coaaDao.loadCoaaDetailsByPortnum(portnum);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCOADetail(int codid, String acctnum, String actname, int acttype, String actclo) {
		Coaa coaa=(Coaa)coaaDao.loadEntity(Coaa.class, codid);
		coaa.setAccntnum(acctnum);
		coaa.setAccntname(actname);
		coaa.setAccntcategory(acttype);
		coaa.setClosetoaccntnum(actclo);
		coaaDao.updateEntity(coaa);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public HashMap<String, String> getCurrencyList() {
		List<FxCurrency> curList=null;
		if(JixuUserUtil.isJoyinTablesFlag()) 
			curList=outSystem.loadFxCurrencyList();
		else
			curList=fxCurrencyDao.getCurrencyList();
		
		HashMap<String, String> result=new HashMap<String, String>();
		for(FxCurrency cur:curList){
			result.put(cur.getId(), cur.getCurrCode());
		}
		return result;
	}
	
	@Override
	public HashMap<String, String> loadCountry() {
		List<Country> countryList=countryDao.getCountryist();
		HashMap<String, String> CountryHM=new HashMap<String, String>();
		for(Country country:countryList){
			CountryHM.put(country.getCountryID(), country.getCountryName());
		}
		return CountryHM;
	}
	
	@Override
	public List<Province> getProvinceist() {
		return provinceDao.getProvinceist();
	}
	
	@Override
	public HashMap<Integer, String> loadCoaaGroup() {
		List<CoaaGroup> coaaGroupList=coaaGroupDao.loadAllCoaaGroup();
		HashMap<Integer, String> coaaGroupHM=new HashMap<Integer, String>();
		for(CoaaGroup coaaGroup:coaaGroupList){
			coaaGroupHM.put(coaaGroup.getGroupNum(), coaaGroup.getGroupName());
		}
		return coaaGroupHM;
	}
	
	@Override
	public HashMap<String, String> loadCoaahdr() {
		HashMap<String, String> coaahdrHM=new HashMap<String, String>();
		
		if(JixuUserUtil.jixuUserSystemFlag()){
			List<Coaahdr> coaahdrList=coaahdrDao.loadCoaahdr(); 
			for(Coaahdr coaahdr:coaahdrList){
				coaahdrHM.put(coaahdr.getId(), coaahdr.getChartname());
			}
		}else{
			List<BokBookSet> bokBookSetList=bokBookSetDao.loadBokBookSetTemplateList();
			for(BokBookSet book:bokBookSetList){
				coaahdrHM.put(book.getBookset_id(), book.getBookset_name());
			}
		}
		return coaahdrHM;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCoaaHdr(String chartname) {
		Coaahdr coaahdr=new Coaahdr();
		coaahdr.setChartname(chartname);
		coaahdrDao.saveEntity(coaahdr);
	}
	
	public List<GlCoaaMapModel> loadGlCoaaMap(String chartnum, int groupnum) {
		List<GlCoaaMapModel> gcmmList=null;
		if(JixuUserUtil.jixuUserSystemFlag()){
			gcmmList=glCoaaMapDao.loadGlCoaaMap(chartnum, groupnum);
		}else{
			gcmmList=glCoaaMapDao.loadGlBookSubjectMap(chartnum, groupnum);
		}
		return gcmmList;
	}
	
	public HashMap<String,String> loadBrokerListByCountry(String countryID) {
		HashMap<String,String> brokerHM=new HashMap<String, String>();
		
		List<Broker> brokerList=brokerDao.loadBrokerList(countryID);
		for(Broker broker:brokerList){
			brokerHM.put(broker.getBrokerID(), broker.getBrokerName());
		}
		return brokerHM;
	}
	
	@Override
	public HashMap<String, String> loadAllBrokerList() {
		HashMap<String,String> brokerHM=new HashMap<String, String>();
		List<Broker> brokerList=brokerDao.loadAllBrokerList();
		for(Broker broker:brokerList){
			brokerHM.put(broker.getBrokerID(), broker.getBrokerName());
		}
		
		return brokerHM;
	}
	
	@Override
	public HashMap<String, String> loadBrokerListByPortNums(List<String> ports) {
		if(ports.size()==0){
			return loadAllBrokerList();
		}else{
			HashMap<String,String> brokerHM=new HashMap<String, String>();
			for(String portnum:ports){
				List<Broker> brokerList=brokerDao.loadBrokerListByPortnum(portnum);
				for(Broker broker:brokerList){
					brokerHM.put(broker.getBrokerID(), broker.getBrokerName());
				}
			}
			return brokerHM;
		}
	}
	
	public HashMap<Integer, String> loadAssetType() {
		HashMap<Integer, String> assetType=new HashMap<Integer, String>();
		List<RPSecType> typeList=rpSecTypeDao.loadRPSecTypeS(1,3);
		for(RPSecType rtype:typeList){
			assetType.put(rtype.getId(), rtype.getTypename());
		}
		return assetType;
	}
	
	public HashMap<Integer, String> loadAssetTypeByAssetID(String assetID) {
		HashMap<Integer, String> assetType=new HashMap<Integer, String>();
		HashMap<Integer, String> joyinAssetType=new HashMap<Integer, String>();
		List<RPSecType> typeList=rpSecTypeDao.loadRPSecTypeS(1,3);
		for(RPSecType rtype:typeList){
			assetType.put(rtype.getId(), rtype.getTypename());
		}
		if(!JixuUserUtil.isJoyinTablesFlag()){
			return assetType;
		}else{
			switch (assetID) {
			case AssetTypes.COMMON_STOCK:
				joyinAssetType.put(101,assetType.get(101));
				joyinAssetType.put(102,assetType.get(102));
				joyinAssetType.put(103,assetType.get(103));
				joyinAssetType.put(104,assetType.get(104));
				break;
			case AssetTypes.OPTION:
				joyinAssetType.put(105,assetType.get(105));
				joyinAssetType.put(106,assetType.get(106));
				break;
			case AssetTypes.FUTURES:
				joyinAssetType.put(109,assetType.get(109));
				joyinAssetType.put(110,assetType.get(110));
				joyinAssetType.put(111,assetType.get(111));
				joyinAssetType.put(112,assetType.get(112));
				joyinAssetType.put(113,assetType.get(113));
				break;
			}
		}
		return joyinAssetType;
	}

	
	public HashMap<Integer, String> loadIndustryType() {
		HashMap<Integer, String> industryType=new HashMap<Integer, String>();
		List<RPSecType> typeList=rpSecTypeDao.loadRPSecTypeS(2,3);
		for(RPSecType rtype:typeList){
			industryType.put(rtype.getId(), rtype.getTypename());
		}
		return industryType;
	}
	
	public RPSecTypeLevelModel loadAssetType(int level3id) {
		RPSecTypeLevelModel rpModel=null;
		List<RPSecTypeLevelModel> typeList=rpSecTypeDao.loadRPSecTypeLevelModel(level3id,1);
		if(typeList.size()>0){
			rpModel=typeList.get(0);
		}
		return rpModel;
	}
	
	public RPSecTypeLevelModel loadIndustryType(int level3id) {
		RPSecTypeLevelModel rpModel=null;
		List<RPSecTypeLevelModel> typeList=rpSecTypeDao.loadRPSecTypeLevelModel(level3id,2);
		if(typeList.size()>0){
			rpModel=typeList.get(0);
		}
		return rpModel;
	}
	
	public HashMap<String, String> loadIssuer() {
		List<Issuer> issuerList=issuerDao.loadAllIssuer();
		HashMap<String, String> issuerHM=new HashMap<String, String>();
		for(Issuer issuer:issuerList){
			issuerHM.put(issuer.getIssuerID(),issuer.getIssuerID()+"|"+issuer.getIssuerName());
		}
		return issuerHM;
	}
	
	@Override
	public HashMap<String, String> loadExchange() {
		HashMap<String, String> exchangeHM=new HashMap<String, String>();
		List<ExchangeCfg> exchangeList=null;
		if(JixuUserUtil.isJoyinTablesFlag()) {
			exchangeList=outSystem.loadExchangeList();
		}else {
			exchangeList=exchangeCfgDao.loadExchangeList();
		}
		for(ExchangeCfg exchange:exchangeList){
			exchangeHM.put(exchange.getCode(), exchange.getName());
		}
		return exchangeHM;
	}
	
	public List<TranFeeDef> loadTranFeeDefList() {
		return tranFeeDefDao.loadTranFeeTypeList();
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secFeeCache", key="'secFeeFollow'")
	public int createTranFeeDef(List<Object> param_list) {
		TranFeeDef feeDef=new TranFeeDef();
	    feeDef.setFeename(param_list.get(0).toString());
	    feeDef.setUnCapitalizedPercent(Double.valueOf(param_list.get(1).toString()));
	    feeDef.setCashPercent(Double.valueOf(param_list.get(2).toString()));
	    feeDef.setCostGlCol(Integer.valueOf(param_list.get(3).toString()));
	    feeDef.setFeeGlCol(Integer.valueOf(param_list.get(4).toString()));
	    feeDef.setSecPayGlCol(Integer.valueOf(param_list.get(5).toString()));
	    feeDef.setRcpyGlCol(Integer.valueOf(param_list.get(6).toString()));
	    feeDef.setStatus(Integer.valueOf(param_list.get(7).toString()));
	    tranFeeDefDao.saveEntity(feeDef);
	   
	    List<Object> tranTypesList=(List)param_list.get(8);
	    for(Object obj:tranTypesList){
	    	String assettype=obj.toString();
	    	
	    	TranFeeTypeMap typeMap=new TranFeeTypeMap();
	    	typeMap.setAssetType(assettype);
	    	typeMap.setTranFeeTypeCode(feeDef);
	    	tranFeeTypeMapDao.saveEntity(typeMap);
	    }
		
		return feeDef.getId();
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@Caching(evict={@CacheEvict(value="secFeeCache", key="'TranComFee_'+#feeDefID"),@CacheEvict(value="secFeeCache", key="'secFeeFollow'")})
	public void updateTranFeeDef(int feeDefID, List<Object> param_list) {
		TranFeeDef feeDef=tranFeeDefDao.loadEntity(TranFeeDef.class, feeDefID);
		
		feeDef.setFeename(param_list.get(0).toString());
	    feeDef.setUnCapitalizedPercent(Double.valueOf(param_list.get(1).toString()));
	    feeDef.setCashPercent(Double.valueOf(param_list.get(2).toString()));
	    feeDef.setCostGlCol(Integer.valueOf(param_list.get(3).toString()));
	    feeDef.setFeeGlCol(Integer.valueOf(param_list.get(4).toString()));
	    feeDef.setSecPayGlCol(Integer.valueOf(param_list.get(5).toString()));
	    feeDef.setRcpyGlCol(Integer.valueOf(param_list.get(6).toString()));
	    feeDef.setStatus(Integer.valueOf(param_list.get(7).toString()));
	    tranFeeDefDao.updateEntity(feeDef);
	    
	    tranFeeTypeMapDao.deleteTranFeeDefMapByFeeID(feeDefID);
	    
	    List<Object> tranTypesList=(List)param_list.get(8);
	    for(Object obj:tranTypesList){
	    	String assettype=obj.toString();
	    	
	    	TranFeeTypeMap typeMap=new TranFeeTypeMap();
	    	typeMap.setAssetType(assettype);
	    	typeMap.setTranFeeTypeCode(feeDef);
	    	tranFeeTypeMapDao.saveEntity(typeMap);
	    }
		
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secFeeCache", key="'TranComFee_'+#defID")
	public boolean removeTranFeeDef(int defID) {
		TranFeeDef feeDef=tranFeeDefDao.loadEntity(TranFeeDef.class, defID);
		if(feeDef.getTranBasicFeeSet().size()==0){
			tranFeeDefDao.deleteEntity(feeDef);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<Country> loadCountryList() {
		return countryDao.getCountryist();
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCountry(Country country) {
		countryDao.updateCountry(country);
		outDataCenter.exePublicDataSender(country);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCountry(Country country) {
		countryDao.saveCountry(country);
		outDataCenter.exePublicDataSender(country);
	}
	
	@Override
	public List<CurrencyDto> loadCurrencyList() {
		List<CurrencyDto> dtoList=new ArrayList<CurrencyDto>();
		List<FxCurrency> curList=null;
		if(JixuUserUtil.isJoyinTablesFlag()) 
			curList=outSystem.loadFxCurrencyList();
		else
			curList=fxCurrencyDao.getCurrencyList();
		for(FxCurrency cur:curList) {
			CurrencyDto dto=new CurrencyDto();
			dto.setCurID(cur.getId());
			dto.setCurCode(cur.getCurrCode());
			dto.setCurName(cur.getCurrName());
			dto.setActive(cur.getActive());
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCurrency(FxCurrency currency) {
		fxCurrencyDao.saveCurrency(currency);
		outDataCenter.exePublicDataSender(currency);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCurrency(FxCurrency currency) {
		fxCurrencyDao.updateCurrency(currency);
		outDataCenter.exePublicDataSender(currency);
	}
	
	public HashMap<Integer, String> loadAssetTypeFather() {
		List<RPSecType> rpsecTypeList=rpSecTypeDao.loadAssetFatherType();
		HashMap<Integer,String> assetTypeHM=new HashMap<Integer, String>();
		for(RPSecType assetType:rpsecTypeList){
			assetTypeHM.put(assetType.getId(), assetType.getTypename());
		}
		return assetTypeHM;
	}
	
	public HashMap<Integer, String> loadIndustryTypeFather() {
		List<RPSecType> rpsecTypeList=rpSecTypeDao.loadIndustryFatherType();
		HashMap<Integer,String> industryHM=new HashMap<Integer, String>();
		for(RPSecType assetType:rpsecTypeList){
			industryHM.put(assetType.getId(), assetType.getTypename());
		}
		return industryHM;
	}
	
	public List<RPSecType> loadAssetTypeList(int typeID) {
		return rpSecTypeDao.loadRPSecTypeList(1,typeID);
	}
	
	public List<RPSecType> loadIndustryTypeList(int typeID) {
		return rpSecTypeDao.loadRPSecTypeList(2,typeID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateAssetType(List<Object> param_list) {
		updateIndustryAssetType(param_list, 1);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateIndustry(List<Object> param_list) {
		updateIndustryAssetType(param_list, 2);
	}
	
	public TranFeeDef loadTranFeeDef(int feeDefID) {
		return tranFeeDefDao.loadTranFeeDef(feeDefID);
	}
	
	
	public void updateIndustryAssetType(List<Object> param_list, int type) {
		List<RPSecType> rpList=rpSecTypeDao.loadIndustryAssetTypeList(type);
		HashMap<Integer, RPSecType> rpHM=new HashMap<Integer, RPSecType>();
		HashMap<Integer, Integer> rpHMLel=new HashMap<Integer, Integer>();
		HashMap<String, Integer> newIDMAP=new HashMap<String, Integer>();
		
		for(RPSecType rpType:rpList){
			rpHM.put(rpType.getId(), rpType);
			rpHMLel.put(rpType.getId(), rpType.getGrouplevel());
		}
		
		for(Object obj:param_list){
			if(!(obj instanceof HashMap)){
				continue;
			}
			HashMap<String,Object> indHM=(HashMap<String,Object>)obj;
        	String idStr=indHM.get("id").toString();
        	String typename=indHM.get("name").toString();
        	String parentCode=indHM.get("parent").toString();
        	
        	if(!idStr.toUpperCase().startsWith("NEW")){
        		int prID=Integer.valueOf(idStr);
        		
        		RPSecType rpType=rpHM.get(prID);
        		if(!rpType.getTypename().equals(typename)){
	        		rpType.setTypename(typename);
	        		rpSecTypeDao.updateEntity(rpType);
        		}
        		
        		newIDMAP.put(idStr, prID);
        	}else{
        		
        		RPSecType rpType=new RPSecType();
        		
        		if(parentCode.equals("0")){
        			rpType.setFathernode(0);
        			rpType.setGrouplevel(1);
        			rpType.setStatus(1);
        			rpType.setTablecode(type);
        			rpType.setTypename(typename);
        			
        		}else{
        			int newParentCode=newIDMAP.get(parentCode);
        			int newLevel=rpHMLel.get(newParentCode)+1;
        			rpType.setFathernode(newParentCode);
        			rpType.setGrouplevel(newLevel);
        			rpType.setStatus(1);
        			rpType.setTablecode(type);
        			rpType.setTypename(typename);
        		}
        		
        		rpSecTypeDao.saveEntity(rpType);
    			newIDMAP.put(idStr, rpType.getId());
    			rpHMLel.put(rpType.getId(), rpType.getGrouplevel());
        	}
        }
	}
	
	
	public Broker loadBrokerByID(String brokerID) {
		return brokerDao.loadEntity(Broker.class, brokerID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateBroker(Broker broker) {
		brokerDao.updateEntity(broker);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createBroker(Broker broker) {
		brokerDao.saveEntity(broker);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createSecAcct(PortfolioHdr portfolio, SecAcct secacct){
		ResultContainer container=new ResultContainer();
		boolean checkExist=secAcctDao.checkSecAcctNameExist(secacct.getAcctCode(), secacct.getPortfolio());
		if(checkExist){
			container.setCorrectFlag(false);
		}else{
			secAcctDao.saveEntity(secacct);
			container.setEntityObj(secacct);
			
			//如果投资组合没有指定过默认证券账户,这里自动绑定
			if(portfolio.getDefaultSecAcct().equals("0")){
				portfolio.setDefaultSecAcct(secacct.getAcctID());
				portfolioHdrDao.updatePortfolio(portfolio);
			}
		}
		
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createSecAcct(List<Object> param_list, List<Object> cash_list) {
		ResultContainer container=new ResultContainer();
		
		SecAcct secacct=new SecAcct();
		secacct.setAcctCode(param_list.get(0).toString());
		secacct.setBrokerCode(param_list.get(1).toString());
		//新建席位号代码
		secacct.setSeatNumber(param_list.get(2).toString());
		secacct.setAcctDesc(param_list.get(3).toString());
		secacct.setPortfolio(param_list.get(4).toString());
		secacct.setGlGrop(new CoaaGroup(Integer.valueOf(param_list.get(5).toString())));
		//判断是否为主账户
		PortfolioHdr portfolio=comFuncService.loadPortfolio(secacct.getPortfolio());
		container=createSecAcct(portfolio, secacct);

		if(container.isCorrectFlag()){
			if(param_list.get(6).toString().equals("true")||param_list.get(6).toString().equals("1")){
				portfolio.setDefaultSecAcct(secacct.getAcctID());
				portfolioHdrDao.updatePortfolio(portfolio);
			}
			
			for(Object objs:cash_list){
				List<String> cashacctList=(List<String>)objs;
				int cashAcctCode=Integer.valueOf(cashacctList.get(1).toString());
				BkCashAcct cashacct=bkCashAcctDao.loadCashAcctWithNoSecAcct(cashAcctCode);
				if(cashacct!=null){
					cashacct.setSecAcctCode(secacct.getAcctCode());
					bkCashAcctDao.updateEntity(cashacct);
				}else{
					container.setCorrectFlag(false);
				}
			}
			container.setEntityObj(secacct);
			container.setEntityID(secacct.getAcctID());
		}
		
		return container;
	}
	
	public List<SecAccountModel> loadSecAcctByBrokerID(String brokerID) {
		List<SecAccountModel> models=secAcctDao.loadSecAcctList(brokerID);
		for(SecAccountModel model:models) {
			int count=bkCashAcctDao.countCashAcctsBySecAcct(model.getAcctID());
			model.setCashAcctNum(count);
		}
		return models;
	}
	
	
	
	public SecAcct loadSecAcct(String secAcctid) {
		return secAcctDao.loadSecAcct(secAcctid);
	}
	
	public List<BkCashAcct> loadBkCashAcctBySecAcct(String secAcctID) {
		return bkCashAcctDao.loadBkCashAcctBySecAcct(secAcctID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer unMapCashSecAcct(int cashAcct) {
		ResultContainer container=new ResultContainer();
		BkCashAcct cashacct=bkCashAcctDao.loadBankCash(cashAcct);
		SecAcct secacct=secAcctDao.loadSecAcct(cashacct.getSecAcctCode());
		if(secacct.getDefaultCashAcct()!=cashAcct){
			//bkCashAcctDao.unMapCashSecAcct(cashAcct);
			//删除证券账户下的关联银行实际是更改银行的默认证券账户代码    更新语句报错  暂改为更新实体
			cashacct.setSecAcctCode(null);
			bkCashAcctDao.updateEntity(cashacct);
		}else{
			container.setCorrectFlag(false);
			container.setObjID(ReturnResult.del_seccash_default);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.del_seccash_default));
		}
		
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateSecAcct(SecAcct secacct) {
		secAcctDao.updateSecacct(secacct);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createSecCashAcctMap(String secacctID, int cashacctID, boolean defaultCashAcct, ResultContainer container) {
		BkCashAcct cashacct=bkCashAcctDao.loadCashAcctWithNoSecAcct(cashacctID);
		if(cashacct!=null){
			SecAcct secacct=secAcctDao.loadEntity(SecAcct.class, secacctID);
			if(secacct.getDefaultCashAcct()==0){
				//如果目前没有默认现金账户,把目前的账户设置为主账户
				defaultCashAcct=true;
			}
			cashacct.setSecAcctCode(secacct.getAcctCode());
			bkCashAcctDao.updateEntity(cashacct);
			
			int defaultacctid=secacct.getDefaultCashAcct();
			if(defaultacctid==0 || defaultCashAcct){
				secacct.setDefaultCashAcct(cashacctID);
				secAcctDao.updateSecacct(secacct);
			}
			
			container.setObjID(cashacct.getId());
		}else{
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.acct_notexist_matched);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.acct_notexist_matched));
		}
	}
	
	@Override
	public List<SecAccountModel> loadSecAcctByPortnum(String portNum) {
		List<SecAccountModel> models=secAcctDao.loadSecAcctByPortnum(portNum);
		for(SecAccountModel model:models) {
			int count=bkCashAcctDao.countCashAcctsBySecAcct(model.getAcctID());
			model.setCashAcctNum(count);
		}
		return models;
	}
	
	@Override
	public HashMap<String, String> loadSecAccstByPortnum(String portNum) {
		HashMap<String, String> result=new HashMap<String, String>();
		if(JixuUserUtil.isJoyinTablesFlag() && !comFuncService.isJoyinSingleValSystem()) {
			//从joyin表中获取证券账户
			result=secAcctDao.loadSecAcctByPortnumFromJoyin(portNum);
		}
		
		if(!JixuUserUtil.isJoyinTablesFlag() || result.size()==0) {
			//如果joyin没有取到证券账户，暂时去jixu的证券账户表里再去一次。单估值没有证券账户表前，临时处理方案
			List<SecAccountModel> acctList = loadSecAcctByPortnum(portNum);
			for (SecAccountModel acct : acctList) {
				result.put(acct.getAcctID(), acct.getAcctCode());
			}
		}
		return result;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createManagerGroup(ManagerGroup managerGroup) {
		managerGroupDao.saveManagerGroup(managerGroup);
	}
	
	public HashMap<Integer, String> loadManagerGroupList() {
		List<ManagerGroup> managerGrouplist=managerGroupDao.loadManagerGroupList();
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		for(ManagerGroup group:managerGrouplist){
			optionHM.put(group.getId(), group.getGroupname());
		}
		
		return optionHM;
	}
	
	public List<Managers> loadManagersListByGroupID(int managerGroupID) {
		return managersDao.loadManagersByGroupID(managerGroupID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createManager(Managers manager){
		managersDao.saveManagers(manager);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateManager(Managers manager) {
		managersDao.updateManagers(manager);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void removeManager(int managerID) {
		Managers manager=managersDao.loadManagerByID(managerID);
		manager.setActive(0);
		managersDao.updateManagers(manager);
	}
	
	public List<IssuerModel> loadIssuerModelList(Pages<VouchersModel> pages, String issuername) {
		
		List<IssuerModel> issuerList=new ArrayList<IssuerModel>();
		if(SystemSelect.getSystemDBFrameWork()==1){
			Page<IssuerModel> page=issuerDao.loadPageHelperIssuerModel(PageHelper.startPage(pages.getCurrentPage(),pages.getPageMaxRecords()),issuername);
			issuerList=page.getResult();
			pages.setTotalPages(page.getPages());;
		}else{
			Pages<IssuerModel> issuerPages=issuerDao.loadIssuerModel(pages, issuername);
			issuerList=issuerPages.getResultList();
		}
		
		List<RPSecTypeLevelModel> levelList=rpSecTypeDao.loadRPSecTypeLevelModel(0,2);
		
		HashMap<Integer, RPSecTypeLevelModel> levelHM=new HashMap<Integer, RPSecTypeLevelModel>();
		for(RPSecTypeLevelModel level:levelList){
			levelHM.put(level.getLevel3id(), level);
		}
		
		for(IssuerModel issuer:issuerList){
			int level3Code=issuer.getIndustryType3();
			RPSecTypeLevelModel l123=levelHM.get(level3Code);
			if(l123!=null){
				issuer.setIndustrype1(l123.getLevel1name());
				issuer.setIndustryType2(l123.getLevel2name());
			}else{
				issuer.setIndustrype1("没有数据");
				issuer.setIndustryType2("没有数据");
			}
		}
		
		return issuerList;
	}
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer removeIssuer(String issuerCode){
		ResultContainer container=new ResultContainer();
		Issuer issuer=issuerDao.loadIssuerByID(issuerCode);
		if(issuer.getCusip().size()>0){
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.issuer_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.issuer_exist));
		}else{
			issuerDao.deleteEntity(issuer);
			container.setCorrectFlag(true);
			
			issuer.setIssuerID("-"+issuer.getIssuerID());
			outDataCenter.exePublicDataSender(issuer);
		}
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createIssuer(Issuer issuer) {
		issuerDao.saveEntity(issuer);
		outDataCenter.exePublicDataSender(issuer);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateIssuer(Issuer issuer) {
		issuerDao.updateEntity(issuer);
		outDataCenter.exePublicDataSender(issuer);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="coaDataCache", key="'GlColMap_'+#glcoaamap.getChartNum()+'_'+#glcoaamap.getGroupNum()+'_'+#glcoaamap.getColumnNum()")
	public void createGLMap(GlCoaaMap glcoaamap) {
		glCoaaMapDao.saveEntity(glcoaamap);
	}
	
	//增加复制总账组功能   一次性提交可减少复制时间
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void copyGLMap(List<GlCoaaMap> list){
		for (GlCoaaMap glmap : list) {
			glCoaaMapDao.saveEntity(glmap);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteGlCoaMap(int glMapID) {
		GlCoaaMap coamap=(GlCoaaMap)glCoaaMapDao.loadEntity(GlCoaaMap.class, glMapID);
		glCoaaMapDao.deleteGlColMap(coamap);
	}
	
	/* (non-Javadoc)
	 * @see com.jixu.mvc.control.service.ConfigService#createInterfaceSource(com.orm.obj.InterfaceSource)
	 */

//	@Override
//	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
//	public ResultContainer createInterfaceSource(List<Object> paramList) {
//		ResultContainer container=new ResultContainer();
//		
//		try{
//			InterfaceSource is=new InterfaceSource();
//			is.setSourceName(paramList.get(0).toString());
//			is.setSourceDesc(paramList.get(1).toString());
//			is.setSourceType(Integer.valueOf(paramList.get(2).toString()));
//			is.setSourceSecType(Integer.valueOf(paramList.get(3).toString()));
//			is.setTranType(Integer.valueOf(paramList.get(4).toString()));
//			is.setChannelType(Integer.valueOf(paramList.get(5).toString()));
//			is.setChannelAccount(paramList.get(6).toString());
//			is.setChannelPWD(paramList.get(7).toString());
//			is.setFileType(Integer.valueOf(paramList.get(8).toString()));
//			is.setEnCodeType(Integer.valueOf(paramList.get(9).toString()));
//			is.setFilepath(paramList.get(10).toString());
//			is.setFileName(paramList.get(11).toString());
//			is.setFileMultiFlag(Boolean.valueOf(paramList.get(12).toString()));
//			is.setTablename(paramList.get(13).toString());
//			String startLineStr=paramList.get(14).toString();
//			if(!startLineStr.equals("")){
//				is.setTableStartLine(Integer.valueOf(startLineStr));
//			}
//			is.setTableSpilitChar(paramList.get(15).toString());
//			is.setTableEndChar(paramList.get(16).toString());
//			is.setHoliday(paramList.get(17).toString());
//			is.setAutoFlag(Boolean.valueOf(paramList.get(18).toString()));
//			is.setAutoType(Integer.valueOf(paramList.get(19).toString()));
//			String ndays=paramList.get(20).toString();
//			if(!ndays.equals("")){
//				is.setAutoNDays(Integer.valueOf(ndays));
//			}
//			is.setAutoTime(paramList.get(21).toString());
//			is.setBackupPath(paramList.get(22).toString());
//			
//			interfaceSourceDao.saveEntityBean(is);
//			container.setObjID(is.getSourceID());
//			
//			addAutoJob(is);
//		}catch(NumberFormatException ne){
//			container.setErrorMsg(ne.getMessage());
//			container.setCorrectFlag(false);
//		}
//		return container;
//	}
	
//	private JobModel<AutoJobAction> setJobModel(InterfaceSource source){
//		JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
//		job.setJobID(source.getSourceID());
//		job.setJobName(source.getSourceName());
//		job.setJobGroup("Interface-Auto");
//		job.setCronExp(InterfaceAutoType.genAutoJobTime(source.getAutoType(), source.getAutoTime()));
//		job.setJobClass(AutoJobAction.class);
//		
//		return job;
//	}
	
//	private JobModel<AutoJobAction> setRemoveJobModel(InterfaceSource source){
//		JobModel<AutoJobAction> job = new JobModel<AutoJobAction>();
//		job.setJobID(source.getSourceID());
//		job.setJobName(source.getSourceName());
//		job.setJobGroup("Interface-Auto");
//		job.setJobClass(AutoJobAction.class);
//		
//		return job;
//	}
	
//	private void addAutoJob(InterfaceSource source){
//		if(source.isAutoFlag()){
//			JobModel<AutoJobAction> job=setJobModel(source);
//			try{
//				jobManager.addJob(job);
//			}catch(SchedulerException se){
//				logger.error("Error", se);
//			}
//		}
//	}
	
//	private void modifyAutoJob(InterfaceSource source){
//		if(source.isAutoFlag()){
//			JobModel<AutoJobAction> job=setJobModel(source);
//			try{
//				jobManager.modifyJobTime(job);
//			}catch(SchedulerException se){
//				logger.error("Error", se);
//			}
//		}
//	}
	
//	private void removeAutoJob(InterfaceSource source){
//		JobModel<AutoJobAction> job=setRemoveJobModel(source);
//		try{
//			jobManager.removeJob(job);
//		}catch(SchedulerException se){
//			logger.error("Error", se);
//		}
//	}
	
	public List<InterfaceSource> loadInterfaceSourceList(int sourceType) {
		return interfaceSourceDao.loadInterfaceByType(sourceType);
	}
	
	public InterfaceSource loadInterfaceSource(int sourceid) {
		return (InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceid);
	}
	
//	@Override
//	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
//	public ResultContainer updateInterfaceSource(int sourceID, List<Object> paramList) {
//		InterfaceSource is=loadInterfaceSource(sourceID);
//		
//		ResultContainer container=new ResultContainer();
//		
//		try{
//			is.setSourceName(paramList.get(0).toString());
//			is.setSourceDesc(paramList.get(1).toString());
//			is.setSourceType(Integer.valueOf(paramList.get(2).toString()));
//			is.setSourceSecType(Integer.valueOf(paramList.get(3).toString()));
//			is.setChannelType(Integer.valueOf(paramList.get(4).toString()));
//			is.setChannelAccount(paramList.get(5).toString());
//			is.setChannelPWD(paramList.get(6).toString());
//			is.setFileType(Integer.valueOf(paramList.get(7).toString()));
//			is.setEnCodeType(Integer.valueOf(paramList.get(8).toString()));
//			is.setFilepath(paramList.get(9).toString());
//			is.setFileName(paramList.get(10).toString());
//			is.setFileMultiFlag(Boolean.valueOf(paramList.get(11).toString()));
//			is.setTablename(paramList.get(12).toString());
//			
//			String startLineStr=paramList.get(13).toString();
//			if(!startLineStr.equals("")){
//				is.setTableStartLine(Integer.valueOf(startLineStr));
//			}
//			is.setTableSpilitChar(paramList.get(14).toString());
//			is.setTableEndChar(paramList.get(15).toString());
//			is.setHoliday(paramList.get(16).toString());
//			is.setAutoFlag(Boolean.valueOf(paramList.get(17).toString()));
//			is.setAutoType(Integer.valueOf(paramList.get(18).toString()));
//			is.setAutoTime(paramList.get(20).toString());
//			is.setBackupPath(paramList.get(21).toString());
//			
//			interfaceSourceDao.updateEntityBean(is);
//			container.setObjID(is.getSourceID());
//			
//			if(is.isAutoFlag()){
//				modifyAutoJob(is);
//			}else{
//				removeAutoJob(is);
//			}
//		}catch(NumberFormatException ne){
//			container.setErrorMsg(ne.getMessage());
//			container.setCorrectFlag(false);
//		}
//		return container;
//	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateInterfaceSource(int sourceID, String msg) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		source.setMsg(msg);
		interfaceSourceDao.updateEntityBean(source);
	}
	
	public String getConfigMsg(int sourceID) {
		//TODO 以后全部改成hashmap传字段
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String msg=source.getMsg();
		StringBuilder strbuilder=new StringBuilder();
		if(msg==null || msg.equals("")){
			int tranType=source.getTranType();
			
			String msgHeader=null;
			switch(source.getSourceType()){
			case InterfaceSourceType.tran:
			case InterfaceSourceType.ta:
			case InterfaceSourceType.cash:
			case InterfaceSourceType.fxTrade:
				msgHeader=SecurityCoreFactory.getMsgService().get(tranType).msgHeader();
				if(msgHeader!=null){
					strbuilder=genMsgTemplate(strbuilder, msgHeader);
					msg=strbuilder.toString();
				}
				break;
			case InterfaceSourceType.price:
				msgHeader=MsgTemplate.PRICINGDATA_Header;
				if(msgHeader!=null){
					strbuilder=genMsgTemplateOld(strbuilder, msgHeader);
					msg=strbuilder.toString();
				}
				break;
			case InterfaceSourceType.fxRate:
				msgHeader=MsgTemplate.FXRATE_Header;
				if(msgHeader!=null){
					strbuilder=genMsgTemplateOld(strbuilder, msgHeader);
					msg=strbuilder.toString();
				}
				break;
			case InterfaceSourceType.smf:
				msgHeader=MsgTemplate.SMFStock_Header;
				if(msgHeader!=null){
					strbuilder=genMsgTemplate(strbuilder, msgHeader);
					msg=strbuilder.toString();
				}
				break;
			default:
				break;
			}
			
			
		}
		if(msg==null){
			msg="";
		}
		return msg;
	}
	
	private StringBuilder genMsgTemplateOld(StringBuilder msgBuilder, String msgHeader){
		String fieldMsg=MsgFactory.getMsgFieldsTemplateHM().get(msgHeader.toUpperCase());
		String[] fieldsArr=fieldMsg.split(",");
		msgBuilder.append(msgHeader);
		msgBuilder.append("(");
		for(int i=0;i<fieldsArr.length;i++){
			String field=fieldsArr[i];
			if(field.contains("(") && field.contains(")")){
				String subMsgHeader=field.substring(0, field.indexOf("("));
				msgBuilder=genMsgTemplateOld(msgBuilder, subMsgHeader);
			}else{
				field=field.replaceAll("\\(", "");
				field=field.replaceAll("\\)", "");
				msgBuilder.append(InterfaceConfig.fieldStart);
				msgBuilder.append(field);
				msgBuilder.append(InterfaceConfig.fieldEnd);
			}
			if(i<fieldsArr.length-1){
				msgBuilder.append(",");	
			}
		}
		msgBuilder.append(")");
		
		return msgBuilder;
	}
	
	private StringBuilder genMsgTemplate(StringBuilder msgBuilder, String msgHeader){
		MsgService msgService=MsgFactory.getMsgMap().get(msgHeader);
		String fieldMsg=msgService.msgFields();
		String[] fieldsArr=fieldMsg.split(",");
		msgBuilder.append("{");
		msgBuilder.append("\"header\":");
		msgBuilder.append("\"");
		msgBuilder.append(InterfaceConfig.fieldStart);
		msgBuilder.append(msgHeader);
		msgBuilder.append(InterfaceConfig.fieldEnd);
		msgBuilder.append("\"");
		msgBuilder.append(",");
		for(int i=0;i<fieldsArr.length;i++){
			String field=fieldsArr[i];
		
			field=field.replaceAll("\\(", "");
			field=field.replaceAll("\\)", "");
			msgBuilder.append("\"");
			msgBuilder.append(field);
			msgBuilder.append("\"");
			msgBuilder.append(":");
			msgBuilder.append("\"");
			msgBuilder.append(InterfaceConfig.fieldStart);
			msgBuilder.append(field);
			msgBuilder.append(InterfaceConfig.fieldEnd);
			msgBuilder.append("\"");
			if(i<fieldsArr.length-1){
				msgBuilder.append(",");	
			}
		}
		msgBuilder.append("}");
		
		return msgBuilder;
	}
	
	public List<String> getMsgParams(int sourceID) {
		InterfaceSource source=(InterfaceSource)interfaceSourceDao.loadEntity(InterfaceSource.class, sourceID);
		String msg=source.getMsg();
		List<String> paramsList=new ArrayList<String>();
		if(msg!=null){
			String[] msgarr=msg.split(InterfaceConfig.fieldStart);
			if(msgarr.length>1){
				for(int i=1;i<msgarr.length;i++){
					int endpoint=msgarr[i].indexOf(InterfaceConfig.fieldEnd);
					if(endpoint>0){
						paramsList.add(msgarr[i].substring(0, endpoint));
					}
				}
			}
		}
		return paramsList;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCoaahdr(Coaahdr coaahdr){
		coaahdrDao.saveEntity(coaahdr);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCoaaGroup(CoaaGroup coaaGroup) {
		coaaGroupDao.saveEntity(coaaGroup);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer createCoa(String chartNum, List<Object> param_list) {
		MsgContainer container=new MsgContainer();
		String msg=msgPackage.coaMsg("ADD", param_list.get(0).toString(), param_list.get(1).toString(), 
				String.valueOf(chartNum), param_list.get(2).toString(), param_list.get(3).toString().split("\\|")[0]);
		msgAssemble.genMsgContainer(msg, container);
		return container;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateGlCoaMap(int glMapID, String acctnum) {
		GlCoaaMap glcoaMap=(GlCoaaMap)glCoaaMapDao.loadEntity(GlCoaaMap.class, glMapID);
		glcoaMap.setAcctNum(acctnum);
		glCoaaMapDao.updateGlColMap(glcoaMap);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfcfg.getCashFlowID()")
	public void createCashFlowCfg(CashFlowCfg cfcfg) {
		cashFlowCfgDao.saveEntity(cfcfg);
	}
	
	@Override
	public List<CashFlowCfg> loadCashFlowCfgList(String name) {
		if(name.trim().equals("")){
			return cashFlowCfgDao.loadEntityAll(CashFlowCfg.class);
		}else{
			return cashFlowCfgDao.loadCashFlowCfgList(name);
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfcfg.getCashFlowID()")
	public void updateCashFlowCfg(CashFlowCfg cfcfg) {
		cashFlowCfgDao.updateEntity(cfcfg);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfcfg.getCashFlowID()")
	public void deleteCashFlowCfg(CashFlowCfg cfcfg) {
		cashFlowCfgDao.deleteEntity(cfcfg);
	}
	
	@Override
	public CashFlowCfg loadCashFlowCfg(String id) {
		return cashFlowCfgDao.loadCashFlowCfg(id);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCashFlowRate(CashFlowRate cfr) {
		cashFlowRateDao.saveEntity(cfr);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCashFlowRate(CashFlowRate cfr) {
		cashFlowRateDao.updateEntity(cfr);
	}

	@Override
	public CashFlowRate loadCashFlowRate(int id) {
		return cashFlowRateDao.loadEntity(CashFlowRate.class, id);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteCashFlowRate(CashFlowRate cfr) {
		cashFlowRateDao.deleteEntity(cfr);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteCashFlowFloatRate(CashFlowFloatRate cffr) {
		cashFlowFloatRateDao.deleteEntity(cffr);
	}


	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCashFlowPayment(CashFlowPayment cfp) {
		cashFlowPaymentDao.saveEntity(cfp);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfp.getCfid().getCashFlowID()")
	public void createCashFlowPar(CashFlowPar cfp){
		cashFlowParDao.saveEntity(cfp);
	}
	
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createCashFlowFloatRate(CashFlowFloatRate cffr){
		cashFlowFloatRateDao.saveEntity(cffr);
	}
	
	@Override
	public CashFlowPayment loadCashFlowPayment(int id) {
		return cashFlowPaymentDao.loadEntity(CashFlowPayment.class, id);
	}
	
	@Override
	public CashFlowPar loadCashFlowPar(int id) {
		return cashFlowParDao.loadEntity(CashFlowPar.class, id);
	}
	
	@Override
	public CashFlowFloatRate loadCashFlowFloatRate(int id){
		return cashFlowRateDao.loadEntity(CashFlowFloatRate.class, id);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCashFlowPayment(CashFlowPayment cfp){
		cashFlowPaymentDao.updateEntity(cfp);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfp.getCfid().getCashFlowID()")
	public void updateCashFlowPar(CashFlowPar cfp){
		cashFlowParDao.updateEntity(cfp);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void updateCashFlowFloatRate(CashFlowFloatRate cffr){
		cashFlowFloatRateDao.updateEntity(cffr);
	}

	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void deleteCashFlowPayment(CashFlowPayment cfp){
		cashFlowPaymentDao.deleteEntity(cfp);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	//@CacheEvict(value="secDataCache", key="'CashFlowCfgID'+#cfp.getCfid().getCashFlowID()")
	public void deleteCashFlowPar(CashFlowPar cfp){
		cashFlowParDao.deleteEntity(cfp);
	}

	@Override
	public List<CashFlowPayment> loadCashFlowPaymentByCfID(String cfID){
		return cashFlowPaymentDao.loadCashFlowPaymentByCfID(cfID);
	}

	@Override
	public List<CashFlowRate> loadCashFlowRateByCfID(String cfID){
		return cashFlowRateDao.loadCashFlowRateByCfID(cfID);
	}
	
	@Override
	public List<CashFlowFloatRate> loadCashFlowFloatRateByCfID(String cfID){
		return cashFlowFloatRateDao.loadCashFlowFloatRateByCfID(cfID);
	}
	
	@Override
	public int checkFeeFixed(int feeID) {
		TranFeeDef fee=tranFeeDefDao.loadTranFeeDef(feeID);
		return fee.getOneTimeFeeFlag();
	}
	
	public List<TranFeeDef> subExpenseTypes(String portID,String secid, int lotnum){
		return tranFeeDefDao.subExpenseTypes(portID, secid, lotnum);
	}
	
	@Override
	public HashMap<String, Object> getCashFlowFloatDetail(String cashFlowID) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		CashFlowCfg cfg=cashFlowCfgDao.loadCashFlowCfg(cashFlowID);
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cfg.getVrBase());
		data.put("id", cashFlowID);
		if(sec!=null){
			data.put("secid", sec.getId());
		}else{
			data.put("secid", 0);
		}
		data.put("vrMethod", cfg.getVrMethod());
		data.put("vrBasisPtAdjustment", cfg.getVrBasisPtAdjustment());
		data.put("max", cfg.getVrMax());
		data.put("min", cfg.getVrMin());
		data.put("frequency", cfg.getVrResetFreq());
		data.put("month", cfg.getVrResetMo());
		data.put("day",cfg.getVrResetDate());
		data.put("vrResetNDays", cfg.getVrResetNDays());
		data.put("vrDeterAdjMo", cfg.getVrDeterAdjMo());
		data.put("holiday", cfg.getResetHolidaySched());
		return data;
	}
	
	@Override
	public HashMap<String, String> getBasePointList() {
		List<IssueInfo> secList=issueInfoDao.loadBasePotinSec();
		HashMap<String, String> hm=new HashMap<String, String>();
		for(IssueInfo sec:secList){
			hm.put(sec.getId(), sec.getSecurityid());
		}
		return hm;
	}
	
	@Override
	public List<BokSubject> loadBokSubjectsByPortNum(String portnum) {
		return bokSubjectDao.loadBokSubjectsByPortnum(portnum);
	}
	
	@Override
	public int autoMapGlGroupForJoyin(String assetType, String curr, String curr2) {
		int groupnum=0;
		if(assetType!=null) {
			CoaaGroup group=glCoaaMapDao.getMapedGlGroup(assetType, curr, curr2);;
			if(group!=null)
				groupnum=group.getGroupNum();
		}
		return groupnum;
	}
	
	@Override
	 public IssueInfo loadIssueInfoBuyCur(String assetType, String longCur, String shortCur) {
	  return issueInfoDao.loadIssueInfoBuyCur(assetType, longCur, shortCur);
	 }
}


