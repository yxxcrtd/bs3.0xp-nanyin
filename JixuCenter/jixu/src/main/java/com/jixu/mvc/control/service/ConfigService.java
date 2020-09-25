package com.jixu.mvc.control.service;

import java.util.HashMap;
import java.util.List;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.Pages;
import com.jixu.msg.dto.CurrencyDto;
import com.jixu.mvc.control.model.GlCoaaMapModel;
import com.jixu.mvc.control.model.IssuerModel;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.jixu.mvc.control.model.SecAccountModel;
import com.jixu.mvc.control.model.VouchersModel;
import com.orm.obj.BkCashAcct;
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

public interface ConfigService {
	/**
	 * 获取会计科目表明细
	 * @param chartnum
	 * @return
	 */
	public List<String> loadCoaaDetailsByChartNum(String chartnum);
	
	public HashMap<String, String> getCoaaDetailsMapByCharNum(String chartnum);
	
	/**
	 * 获取会计科目表明细
	 * @param portnum
	 * @return
	 */
	public List<Coaa> loadCoaaDetailsByPortNum(String portnum);
	
	/**
	 * 获取总账科目表
	 * @param portnum
	 * @return
	 */
	public List<BokSubject> loadBokSubjectsByPortNum(String portnum);
	
	/**
	 * 更新科目
	 * @param acctid	科目ID
	 * @param acctnum	科目号
	 * @param actname	科目名称
	 * @param acttype	科目类型
	 * @param actclo	关帐科目
	 */
	public void updateCOADetail(int acctid, String acctnum, String actname, int acttype, String actclo);
	
	/**
	 * 获取货币列表
	 * @return
	 */
	public HashMap<String, String> getCurrencyList();
	
	/**
	 * 获取国家列表
	 * @return
	 */
	public HashMap<String, String> loadCountry();
	
	/**
	 * 获取省列表
	 * @return
	 */
	public List<Province> getProvinceist();
	
	/**
	 * 获取coaagroup列表
	 * @return
	 */
	public HashMap<Integer, String> loadCoaaGroup();
	
	
	/**
	 * 获取所有会计科目表
	 * @return
	 */
	public HashMap<String, String> loadCoaahdr();
	
	/**
	 * 创建会计科目表
	 * @param chartnum
	 */
	public void createCoaaHdr(String chartnum);
	
	public List<GlCoaaMapModel> loadGlCoaaMap(String chartnum, int groupnum);
	
	public void deleteGlCoaMap(int glMapID);
	
	public void updateGlCoaMap(int glMapID, String acctnum);
	
	/**
	 * 获取一个国家的券商
	 * @param countryID
	 * @return
	 */
	public HashMap<String,String> loadBrokerListByCountry(String countryID);
	
	public HashMap<String,String> loadAllBrokerList();
	
	public HashMap<String,String> loadBrokerListByPortNums(List<String> ports);
	
	public HashMap<Integer,String> loadAssetType();
	
	public HashMap<Integer,String> loadAssetTypeByAssetID(String assetID);
	
	public RPSecTypeLevelModel loadAssetType(int level3ID);
	
	public HashMap<String,String> loadIssuer();
	
	public HashMap<String,String> loadExchange();
	
	public List<TranFeeDef> loadTranFeeDefList();
	
	public int createTranFeeDef(List<Object> param_list);
	
	public void updateTranFeeDef(int feeDefID, List<Object> param_list);
	
	public boolean removeTranFeeDef(int defID);
	
	public List<Country> loadCountryList();
	
	public void updateCountry(Country country);
	
	public void createCountry(Country country);
	
	/**
	 * 获取货币的所有信息
	 * @return
	 */
	public List<CurrencyDto> loadCurrencyList();
	
	public void updateCurrency(FxCurrency currency);
	
	public void createCurrency(FxCurrency currency);
	
	public HashMap<Integer, String> loadAssetTypeFather();
	
	public HashMap<Integer, String> loadIndustryTypeFather();
	
	public List<RPSecType> loadAssetTypeList(int typeID);
	
	public List<RPSecType> loadIndustryTypeList(int typeID);
	
	public void updateAssetType(List<Object> param_list);
	
	public void updateIndustry(List<Object> param_list);
	
	public TranFeeDef loadTranFeeDef(int feeDefID);
	
	public Broker loadBrokerByID(String brokerID);
	
	public void updateBroker(Broker broker);
	
	public void createBroker(Broker broker);
	
	public ResultContainer createSecAcct(PortfolioHdr portfolio, SecAcct secacct);
	
	public ResultContainer createSecAcct(List<Object> param_list, List<Object> cash_list);
	
	public List<SecAccountModel> loadSecAcctByBrokerID(String brokerID);
	
	public SecAcct loadSecAcct(String secAcctid);
	
	public List<BkCashAcct> loadBkCashAcctBySecAcct(String secAcctID);
	
	public ResultContainer unMapCashSecAcct(int cashAcct);
	
	public void updateSecAcct(SecAcct secacct);
	
	public void createSecCashAcctMap(String secacctID, int cashacctID, boolean isDefault, ResultContainer container);
	
	public List<SecAccountModel> loadSecAcctByPortnum(String portNum);
	
	/**
	 * 根据投资组合获取匹配的证券账户
	 * @param portNum
	 * @return
	 */
	public HashMap<String, String> loadSecAccstByPortnum(String portNum);
	
	public void createManagerGroup(ManagerGroup managerGroup);
	
	public HashMap<Integer,String> loadManagerGroupList();
	
	public List<Managers> loadManagersListByGroupID(int managerGroupID);
	
	public void createManager(Managers manager);
	
	public void updateManager(Managers manager);
	
	public void removeManager(int managerID);
	
	public RPSecTypeLevelModel loadIndustryType(int level3id);
	
	public HashMap<Integer, String> loadIndustryType();
	
	public List<IssuerModel> loadIssuerModelList(Pages<VouchersModel> pages, String issuername);
	
	public ResultContainer removeIssuer(String issuerCode);
	
	public void createIssuer(Issuer issuer);
	
	public void updateIssuer(Issuer issuer);
	
	public void createGLMap(GlCoaaMap glcoaamap);
	
	//增加复制总账组功能
	public void copyGLMap(List<GlCoaaMap> list);
	
	//public ResultContainer createInterfaceSource(List<Object> paramList);
	
	public List<InterfaceSource> loadInterfaceSourceList(int sourceType);
	
	public InterfaceSource loadInterfaceSource(int sourceid);
	
	//public ResultContainer updateInterfaceSource(int sourceID, List<Object> paramList);
	
	public void updateInterfaceSource(int sourceID, String msg);
	
	public String getConfigMsg(int sourceID);
	
	public List<String> getMsgParams(int sourceID);
	
	public void createCoaahdr(Coaahdr coaahdr);
	
	public void createCoaaGroup(CoaaGroup coaaGroup);
	
	public ResultContainer createCoa(String chartNum, List<Object> param_list);
	
	public void createCashFlowCfg(CashFlowCfg cfcfg);
	
	public void createCashFlowRate(CashFlowRate cfr);
	
	public void createCashFlowPayment(CashFlowPayment cfp);
	
	//现金流还本计划
	public void createCashFlowPar(CashFlowPar cfp);
	
	public void createCashFlowFloatRate(CashFlowFloatRate cffr);
	
	public CashFlowCfg loadCashFlowCfg(String id);
	
	public CashFlowRate loadCashFlowRate(int id);
	
	public CashFlowPayment loadCashFlowPayment(int id);
	
	//现金流还本计划
	public CashFlowPar loadCashFlowPar(int id);
	
	public CashFlowFloatRate loadCashFlowFloatRate(int id);
	
	public List<CashFlowCfg> loadCashFlowCfgList(String name);
	
	public void updateCashFlowRate(CashFlowRate cfr);
	
	public void updateCashFlowCfg(CashFlowCfg cfcfg);
	
	public void updateCashFlowPayment(CashFlowPayment cfp);
	
	public void updateCashFlowPar(CashFlowPar cfp);
	
	public void updateCashFlowFloatRate(CashFlowFloatRate cffr);
	
	public void deleteCashFlowCfg(CashFlowCfg cfcfg);
	
	public void deleteCashFlowPayment(CashFlowPayment cfp);
	
	public void deleteCashFlowPar(CashFlowPar cfp);

	public void deleteCashFlowRate(CashFlowRate cfr);
	
	public void deleteCashFlowFloatRate(CashFlowFloatRate cffr);
	
	public List<CashFlowPayment> loadCashFlowPaymentByCfID(String cfID);
	
	public List<CashFlowRate> loadCashFlowRateByCfID(String cfID);
	
	public List<CashFlowFloatRate> loadCashFlowFloatRateByCfID(String cfID);
	
	public List<List<Object>> loadCoaaDetailsAllByChartNum(String chartnum);
	
	/**
	 * 检查费用是一次性计提还是每天计提
	 * @param feeID
	 * @return
	 */
	public int checkFeeFixed(int feeID);
	
	public List<TranFeeDef> subExpenseTypes(String portID,String secid, int lotnum);
	
	/**
	 * 获取浮动利率的详细配置
	 * @param cashFlowID
	 * @return
	 */
	public HashMap<String, Object> getCashFlowFloatDetail(String cashFlowID);
	
	/**
	 * 获取基础指数证券
	 * @return
	 */
	public HashMap<String, String> getBasePointList();
	
	/**
	 * 自动匹配总账栏
	 * @param assetType	资产类型
	 * @param currency1	即期换汇(空方货币), 外汇掉期(远端收入货币)
	 * @param currency2 即期换汇(多方货币), 外汇掉期(远端支出货币)
	 * @return
	 */
	public int autoMapGlGroupForJoyin(String assetType, String curr, String curr2);
	
	//根据多空方货币查询即期smf
	public IssueInfo loadIssueInfoBuyCur(String assetType,String longCur,String shortCur);
}
