package com.jixu.dao.intf;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.MarketFxRateModel;
import com.orm.obj.FxRate;

/**
 * @ClassName 		FxRateDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface FxRateDao extends CommFuncDAO{

	@CacheEvict(value="marketFxRateCache", key="#fxrate.getBaseCurrID().getId()+'_'+#fxrate.getCurrID().getId()+'_'+#fxrate.getRateDate().getTime()+#fxrate.getSourceID().getSourceID()")
	public void saveFxRate(@Param("fxrate")FxRate fxrate);
	
	/**
	 * 校验汇率是否已经存在
	 * @param fxrate
	 * @return
	 */
	public FxRate loadFxRate(@Param("fxrate")FxRate fxrate);
	
	@CacheEvict(value="marketFxRateCache", key="#fxrate.getBaseCurrID().getId()+'_'+#fxrate.getCurrID().getId()+'_'+#fxrate.getRateDate().getTime()+#fxrate.getSourceID().getSourceID()")
	public void updateFxRate(@Param("fxrate")FxRate fxrate);
	
	public Pages<MarketFxRateModel> lostMarketFxRate(@Param("pages")Pages<MarketFxRateModel> pages, @Param("sourceid") int sourceid, @Param("cur") String cur, @Param("baseCur") String baseCur, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public MarketFxRateModel loadMarketFxRateDetail(@Param("rateid")int rateid);

	/**
	 * 获取某天币种汇率。是当天之前，包括当天，最近的一条汇率记录
	 * @param baseCurrency	基准货币，投资组合货币
	 * @param localCurrency	证券货币
	 * @param date	日期,yyyymmdd
	 * @param sourceID	接口数据源接口系统ID
	 * @return	估值汇率
	 */
	@Cacheable(value="marketFxRateCache", key="#baseCurrency+'_'+#localCurrency+'_'+#date.getTime()+#sourceID")
	public double loadFxrate(@Param("baseCurrency")String baseCurrency, @Param("localCurrency") String localCurrency, @Param("date") Date date, @Param("sourceID") int sourceID);
}
