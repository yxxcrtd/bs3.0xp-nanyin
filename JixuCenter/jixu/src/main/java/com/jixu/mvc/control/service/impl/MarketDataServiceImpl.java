package com.jixu.mvc.control.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.types.OptionType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.Pages;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.FxRateDao;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.IssuerDao;
import com.jixu.dao.intf.RPSecTypeDao;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.dao.intf.SecPriceDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.framework.msg.MsgAssemble;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.model.MarketFxRateModel;
import com.jixu.mvc.control.model.MarketPriceModel;
import com.jixu.mvc.control.model.MarketStockDetailModel;
import com.jixu.mvc.control.model.MarketStockModel;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.jixu.mvc.control.service.ConfigService;
import com.jixu.mvc.control.service.MarketDataService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.Issuer;
import com.orm.obj.SecContract;
import com.orm.obj.TranBasic;

@Service("marketDataService")
public class MarketDataServiceImpl implements MarketDataService{
	
	@Autowired
	private SecContractDao secContractDao;
	
	@Autowired
	private InterfaceSourceDao interfaceSourceDao;
	
	@Autowired
	private SecPriceDao secPriceDao;
	
	@Autowired
	private FxRateDao fxRateDao;
	
	@Resource(name="configService")
	private ConfigService configService;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private IssuerDao issuerDao;
	
	@Resource(name="msgAssemble")
	private MsgAssemble msgAssemble;
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Resource(name="secCoreFactory")
	private SecurityCoreFactory secCoreFactory;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@Autowired
	private RPSecTypeDao rpSecTypeDao;
	
	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Override
	public Pages<MarketPriceModel> loadPriceList(Pages<MarketPriceModel> pages, int[] sources, String secid, Date startDate, Date endDate) {
		if(secid.equals("") && startDate==null && endDate==null){
			Date maxDate=secPriceDao.getMaxPriceDate();
			if(maxDate!=null){
				startDate=maxDate;
				endDate=maxDate;
			}else{
				startDate=DateUtil.convert2YYYYMMDD(new Date());
				endDate=DateUtil.convert2YYYYMMDD(new Date());
			}
		}
		
		Pages<MarketPriceModel> beans=secPriceDao.loadMarketPriceModelList(pages, sources, secid, startDate, endDate);
		for(MarketPriceModel bean:beans.getResultList()) {
			bean.setCurency(commfuncService.loadFxCurrencyCode(bean.getCurency()));
		}
		return beans;
	}
	
	@Override
	public Page<MarketPriceModel> loadPageHelperPriceList(Page<MarketPriceModel> page, int[] sources, String secid, Date startDate, Date endDate){
		if(secid.equals("") && startDate==null && endDate==null){
			Date maxDate=secPriceDao.getMaxPriceDate();
			if(maxDate!=null){
				startDate=maxDate;
				endDate=maxDate;
			}else{
				startDate=DateUtil.convert2YYYYMMDD(new Date());
				endDate=DateUtil.convert2YYYYMMDD(new Date());
			}
		}
		return secPriceDao.loadPageHelperMarketPriceModelList(page, sources, secid, startDate, endDate);
	}
	
	@Override
	public HashMap<Integer, String> loadSourceList(String[] ports, int type) {
		HashMap<Integer, String> optoinHM=new HashMap<Integer, String>();
		List<InterfaceSource> sourceList=interfaceSourceDao.loadInterfaceSourceList(ports, type);
		for(InterfaceSource source:sourceList){
			optoinHM.put(source.getSourceID(), source.getSourceDesc());
		}
		return optoinHM;
	}
	
	@Override
	public MarketPriceModel loadPriceDetail(int priceid) {
		MarketPriceModel bean=secPriceDao.loadMarketPriceDetail(priceid);;
		bean.setCurency(commfuncService.loadFxCurrencyCode(bean.getCurency()));;
		return bean;
	}
	
	@Override
	public Pages<MarketFxRateModel> loadFxRateList(Pages<MarketFxRateModel> pages, int source, String cur,
			String baseCur, Date startDate, Date endDate) {
		Pages<MarketFxRateModel> beanList=fxRateDao.lostMarketFxRate(pages, source, cur, baseCur, startDate, endDate);
		//取出货币ID查询货币代码
		for(MarketFxRateModel bean:beanList.getResultList()) {
			bean.setBaseCurrency(commfuncService.loadFxCurrencyCode(bean.getBaseCurID()));
			bean.setCurrency(commfuncService.loadFxCurrencyCode(bean.getCurID()));
		}
		return beanList;
	}

	@Override
	public MarketFxRateModel loadFxRateDetail(int fxRateID) {
		MarketFxRateModel bean=fxRateDao.loadMarketFxRateDetail(fxRateID);
		bean.setBaseCurrency(commfuncService.loadFxCurrencyCode(bean.getBaseCurID()));
		bean.setCurrency(commfuncService.loadFxCurrencyCode(bean.getCurID()));
		return bean;
	}
	
	@Override
	public List<String> loadSecIDNameList(int sourceid, String sectype) {
		return issueInfoDao.loadSecIDNameList(sourceid, sectype);
	}
	
	@Override
	public Pages<MarketStockModel> loadstockList(int[] sourceids, String secType, String secID, Pages<MarketStockModel> pages) {
		Pages<MarketStockModel> beans=issueInfoDao.loadStockList(sourceids, secType, secID, pages);
		HashMap<String, String> countryMap=configService.loadCountry();
		for(MarketStockModel bean:beans.getResultList()) {
			bean.setCurName(commfuncService.loadFxCurrencyCode(bean.getCurName()));;
			bean.setCountry(countryMap.get(bean.getCountry()));
		}
		return beans;
		
		/*
		 * if(frameWorkFlag==1){ Page<MarketStockModel>
		 * page=issueInfoDao.loadPageHelperStockList(sourceids, secType,
		 * secID,PageHelper.startPage(pages.getCurrentPage(),pages.getPageMaxRecords()))
		 * ; pages.setResultList(page.getResult());
		 * pages.setTotalPages(page.getPages()); return pages; }else{
		 */
		//}
	}
	
	@Override
	public MarketStockDetailModel loadStockDetail(String cusip) {
		IssueInfo sec=issueInfoDao.loadIssueByCusip(cusip);
		MarketStockDetailModel stockDetail=null;
		
		Issuer issuer=issuerDao.loadIssuer(sec.getIssuer());
		stockDetail=new MarketStockDetailModel();
		stockDetail.setCusip(cusip);
		stockDetail.setSecID(sec.getSecurityid());
		stockDetail.setSecName(sec.getSecurityname());
		stockDetail.setSecType(sec.getIssueType());
		if(!sec.getIssueType().equals(AssetTypes.SWAP_INTEREST)&&!sec.getIssueType().equals(AssetTypes.FXOption)){
			stockDetail.setIssuerCode(issuer.getIssuerID()+"|"+issuer.getIssuerCode());
			stockDetail.setCountryID(sec.getCountry());
		}
		SecContract sc=secContractDao.loadSecContract(cusip);
		if(sec.getIssueType().equals(AssetTypes.FXOption)){
			stockDetail.setLongCurID(sc.getLongCurID());
			stockDetail.setLongCash(sc.getLongCash());
			stockDetail.setShortCurID(sc.getShortCurID());
			stockDetail.setShortCash(sc.getShortCash());
			if(sec.getOptionType()==OptionType.put){
				stockDetail.setExecuteFX(RoundUtil.roundMoney2Dec(sc.getLongCash()/sc.getShortCash()));
			}else{
				stockDetail.setExecuteFX(RoundUtil.roundMoney2Dec(sc.getShortCash()/sc.getLongCash()));
			}
		}
		//期货smf显示期货类型  合约标的  保证金
		if(sec.getIssueType().equals(AssetTypes.FUTURES)){
			stockDetail.setMarginRate(sc.getMarginRate());
			stockDetail.setFuturesTarget(sc.getFuturesTarget());
			stockDetail.setFuturesType(sc.getFuturesType());
		}
		stockDetail.setPrinCurID(sec.getPriCur());
		stockDetail.setIncomeCurID(sec.getIncomeCur());
		stockDetail.setPrinCurID2(sec.getPriCur2());
		stockDetail.setIncomeCurID2(sec.getIncomeCur2());
		stockDetail.setCoaNum(sec.getCoaaGroup());
		stockDetail.setAssetType3(sec.getRpSecType().getId());
		stockDetail.setSourceID(sec.getSourceID().getSourceID());
		stockDetail.setContractSize(sec.getContractSize());
		stockDetail.setExpireDate(sec.getExpireDate());
		stockDetail.setCashFlowCfgID(sec.getCashFlowCfgID());
		stockDetail.setCashFlowCfgID2(sec.getCashFlowCfgID2());
		stockDetail.setFixIncomeType(sec.getFixIncomeType());
		stockDetail.setStartPrice(sec.getStartPrice());
		stockDetail.setEndPrice(sec.getEndPrice());
		//证券主文件资产类型
		RPSecTypeLevelModel rpModel=null;
		List<RPSecTypeLevelModel> typeList=rpSecTypeDao.loadRPSecTypeLevelModel(stockDetail.getAssetType3(),1);
		if(typeList.size()>0){
			rpModel=typeList.get(0);
		}
		
		if(sec.getExchangeCode()!=null)
			stockDetail.setExchangeID(sec.getExchangeCode());
//		stockDetail.setAssetType1(rpModel.getLevel1name());
//		stockDetail.setAssetType2(rpModel.getLevel2name());
		stockDetail.setAssetType1(String.valueOf(rpModel.getLevel1id()));
		stockDetail.setAssetType2(String.valueOf(rpModel.getLevel2id()));
		stockDetail.setOptionType(sec.getOptionType());
		
		if(sec.getUnderCusip()!=null && !sec.getUnderCusip().equals("") && !sec.getUnderCusip().equals("0")){
			IssueInfo underCusip=issueInfoDao.loadIssueByCusip(sec.getUnderCusip());
			stockDetail.setUnderSecName(0+"|"+underCusip.getSecurityid()+"|"+underCusip.getSecurityname());
		}
		stockDetail.setActiveDate(DateUtil.convertD2YYYY_MM_DD(sec.getActiveDate()));
		
		return stockDetail;
	}
	
	@Override
	public double loadFxRate(String port,String localCurID, String funcCurID, Date date) {
		return commfuncService.loadFxrate(port,funcCurID, localCurID, date);
	}
	
	@Override
	public String loadSecCurrencyBySecCode(String secCode) {
		IssueInfo sec=issueInfoDao.loadSecurity(secCode);
		if(sec!=null){
			return sec.getPriCur();
		}else{
			return "";
		}
	}

	@Override
	public int getTranBasicPostNum(String cusip) {
		return tranBasicDao.getTranTotalNum(cusip);
	}

	@Override
	public List<TranBasic> getForwardSmfTranBasic(String secid, int tranType) {
		return tranBasicDao.getForwardSmfTranBasic(secid, tranType);
	}
	
	@Override
	public TranBasic getForwardSmf(String secid, int tranType) {
		return tranBasicDao.getForwardSmf(secid, tranType);
	}
}
