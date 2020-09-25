package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.mvc.control.model.MarketFxRateModel;
import com.jixu.mvc.control.model.MarketPriceModel;
import com.jixu.mvc.control.model.MarketStockDetailModel;
import com.jixu.mvc.control.model.MarketStockModel;
import com.orm.obj.TranBasic;

public interface MarketDataService {
	public Pages<MarketPriceModel> loadPriceList(Pages<MarketPriceModel> pages, int[] sources, String secid, Date startDate, Date endDate);
	
	public Page<MarketPriceModel> loadPageHelperPriceList(Page<MarketPriceModel> page, int[] sources, String secid, Date startDate, Date endDate);
	
	public MarketPriceModel loadPriceDetail(int priceid);
	
	public HashMap<Integer, String> loadSourceList(String[] ports, int sourcetype);
	
	public Pages<MarketFxRateModel> loadFxRateList(Pages<MarketFxRateModel> pages, int source,String cur,String basceCur,Date startDate,Date endDate);
	
	public MarketFxRateModel loadFxRateDetail(int fxRateID);
	
	/**
	 * 获取股票类型
	 * @param sourceid
	 * @param sectype
	 * @return
	 */
	public List<String> loadSecIDNameList(int sourceid,String sectype);
	
	public Pages<MarketStockModel> loadstockList(int[] sourceids,String secType,String secID, Pages<MarketStockModel> pages);
	
	public MarketStockDetailModel loadStockDetail(String cusip);
	
	public double loadFxRate(String port,String localCurID, String funcCurID, Date date);
	
	public String loadSecCurrencyBySecCode(String secCode);
	
	public int getTranBasicPostNum(@Param("cusip")String cusip);
	
	public List<TranBasic> getForwardSmfTranBasic(String secid,int tranType);
	
	public TranBasic getForwardSmf(String secid,int tranType);
}
