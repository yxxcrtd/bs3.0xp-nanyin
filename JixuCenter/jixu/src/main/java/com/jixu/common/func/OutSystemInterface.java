package com.jixu.common.func;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.type.AssetTypes;
import com.jixu.dao.intf.InterfaceSourceDao;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.SecPriceDao;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstValFx;
import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.CurrencyMarketDto;
import com.joyintech.tams.valuation.common.batch.finproduct.service.FinProductCommonBatchService;
import com.joyintech.tams.valuation.common.jixu.finproduct.service.JxFinProductService;
import com.orm.obj.ExchangeCfg;
import com.orm.obj.FxCurrency;

/**
 *  
 * 用于外部系统接入后，对于外部输入的获取
 * @author 桃汰猪
 *
 */

@Component
public class OutSystemInterface {
	
	@Autowired
	private SecPriceDao secPriceDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;
	
	@Autowired
	private InterfaceSourceDao interfaceSourceDao;
	
	@Autowired(required=false)
	private FinProductCommonBatchService finProductCommonBatchService;
	
	@Autowired(required=false)
	private JxFinProductService jxFinProductService;
	
	/**
	 * 获取最新价格
	 * @param portnum
	 * @param cusip
	 * @param priceDate
	 * @return
	 */
	public double loadCusipPrice(String portnum, String securityid, String assetType, String exchangeCode, Date priceDate) {
		double price=0;
		if(assetType.equals(AssetTypes.SWAP_INTEREST)) {
			MstValIrs pricemodel=finProductCommonBatchService.getInterestRateSwap(priceDate, portnum, securityid);
			if(pricemodel!=null) {
				price=pricemodel.getValuation().doubleValue();
			}
		}else if(assetType.equals(AssetTypes.FORWARD_CURRENCY) || assetType.equals(AssetTypes.SWAP_FX)){
			MstValFx pricemodel=finProductCommonBatchService.getForeignExcMarket(priceDate, portnum, securityid);
			if(pricemodel!=null) {
				price=pricemodel.getValuation().doubleValue();
			}
		}else {
			//调用兆尹根据金融产品代码查询金融产品信息方法
			FinProduct fpd=jxFinProductService.queryFinProduct(securityid);
			if(fpd!=null){
				securityid=fpd.getFinprodMarketId();
			}
			Map<String, Object> pricesMap=finProductCommonBatchService.getStockMarket(priceDate, portnum, securityid, exchangeCode, null);
			if(pricesMap!=null){
				price=Double.parseDouble(pricesMap.get("valuation").toString());
			}
		}
		return price;
	}
	
	/**
	 * 获取价格。根据投资组合配置的数据接口，获取包括当天之前最新的一条汇率记录,如果获取不到汇率，则为1。例如：投资组合配置的汇率数据源是央行汇率，
	 * 人民币兑美元的汇率，2018年1月1日，为6.6
	 * @param portnum
	 * @param funcCurCode
	 * @param localCurCdoe
	 * @param date
	 * @return
	 */
	public double loadFxrate(String portnum, String funcCurCode, String localCurCdoe, Date date) {
		if(funcCurCode.equals(localCurCdoe)){
			return 1;
		}else{
			double fxrate=0;
			List<CurrencyMarketDto> dtoList=finProductCommonBatchService.getCurrencyMarket(date, portnum, funcCurCode, localCurCdoe);
			if(dtoList==null || dtoList.size()==0){
				fxrate=1;
			}else {
				CurrencyMarketDto dto=dtoList.get(0);
				fxrate=dto.getClosePrice().doubleValue();
			}
			return fxrate;
		}
	}
	
	/**
	 * 获取joyin的货币配置
	 * @param curCode
	 * @return
	 */
	public FxCurrency loadFxCurrency(String curCode) {
		FxCurrency cur=new FxCurrency();
		DataDict dataDict = CacheUtil.getDictCode("CCY"); 
		List<? extends DataDictItem> itemList = dataDict.getItems(); 
		for (DataDictItem dictItem : itemList) { 
			if(dictItem.getItemCode().equals(curCode)) {
				//jixu有个主键id, joyin的id=code
				cur.setId(curCode); 
				cur.setCurrCode(curCode);
				cur.setCurrName(dictItem.getItemValue());
				cur.setActive(0);
				break;
			}
		}
		return cur;
	}
	
	/**
	 * 获取joyin的所有的货币配置
	 * @param curCode
	 * @return
	 */
	public List<FxCurrency> loadFxCurrencyList() {
		List<FxCurrency> curList=new ArrayList<FxCurrency>();
		DataDict dataDict = CacheUtil.getDictCode("CCY"); 
		List<? extends DataDictItem> itemList = dataDict.getItems(); 
		//jixu有个主键id, joyin的id=code
		for (DataDictItem dictItem : itemList) { 
			FxCurrency cur=new FxCurrency();
			cur.setId(dictItem.getItemCode()); 
			cur.setCurrCode(dictItem.getItemCode());
			cur.setCurrName(dictItem.getItemValue());
			cur.setActive(0);
			curList.add(cur);
		}
		return curList;
	}
	
	/**
	 * 获取交易所
	 * @return
	 */
	public List<ExchangeCfg> loadExchangeList(){
		List<ExchangeCfg> exgList=new ArrayList<ExchangeCfg>();
		DataDict dataDict = CacheUtil.getDictCode("TRADE_MARKET"); 
		List<? extends DataDictItem> itemList = dataDict.getItems(); 
		//jixu有个主键id, joyin的id=code
		for (DataDictItem dictItem : itemList) { 
			ExchangeCfg exg=new ExchangeCfg();
			exg.setCode(dictItem.getItemCode());
			exg.setName(dictItem.getItemValue());
			exgList.add(exg);
		}
		return exgList;
	}
	
	/**
	 * 获取交易所
	 * @param curCode
	 * @return
	 */
	public ExchangeCfg loadExchangeCfg(String exgCode) {
		ExchangeCfg exg=new ExchangeCfg();
		DataDict dataDict = CacheUtil.getDictCode("TRADE_MARKET"); 
		List<? extends DataDictItem> itemList = dataDict.getItems(); 
		for (DataDictItem dictItem : itemList) { 
			if(dictItem.getItemCode().equals(exgCode)) {
				//jixu有个主键id, joyin的id=code
				exg.setCode(dictItem.getItemCode());
				exg.setName(dictItem.getItemValue());
				break;
			}
		}
		return exg;
	}
	
	
	//写凭证入当前表
}
