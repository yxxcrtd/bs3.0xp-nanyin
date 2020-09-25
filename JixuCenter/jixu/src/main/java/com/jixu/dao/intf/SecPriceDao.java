package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.MarketPriceModel;
import com.orm.obj.SecPrice;

/**
 * @ClassName 		SecPriceDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface SecPriceDao extends CommFuncDAO{
	/**
	 * 获取当天有价格的证券
	 * @param cusipList
	 * @param asOfDate
	 * @return
	 */
	public List<String> getCusipByPriceDate(@Param("cusipList")List<String> cusipList, @Param("asOfDate") Date asOfDate);
	
	/**
	 * 校验价格是否已经存在
	 * @param secprice
	 * @return
	 */
	public SecPrice loadSecPriceByParams(@Param("secprice")SecPrice secprice);
	
	public Pages<MarketPriceModel> loadMarketPriceModelList(@Param("pages")Pages<MarketPriceModel> pages, @Param("sources") int[] sources, @Param("secid") String secid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public Page<MarketPriceModel> loadPageHelperMarketPriceModelList(@Param("page")Page<MarketPriceModel> page, @Param("sources") int[] sources, @Param("secid") String secid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public MarketPriceModel loadMarketPriceDetail(@Param("priceid")int priceid);
	
	public Date getMaxPriceDate();
	
	public SecPrice loadSecPrice(@Param("securityID")String securityID, @Param("ledgerDate")Date ledgerDate);

	/**
	 * 获取当天的股票价格,如果当天没有股票价格,则取之前最近一天的
	 * @param cusip	证券系统ID
	 * @param priceDate	价格日期
	 * @param sourceID	数据源接口系统ID
	 * @return	估值价格
	 */
	@Cacheable(value="priceCache", key="#cusip+'_'+#priceDate.getTime()+'_'+#sourceID")
	public double loadCusipPrice(@Param("cusip")String cusip, @Param("priceDate") Date priceDate, @Param("sourceID") int sourceID);
	
	/**
	 * 获取价格表的yield字段，作为计息的利率
	 * @param portnum
	 * @param cusip
	 * @param priceDate
	 * @return
	 */
	public double loadCusipYield(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("priceDate") Date priceDate);
}
