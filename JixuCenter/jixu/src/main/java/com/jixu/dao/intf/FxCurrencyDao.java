package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.FxCurrency;

/**
 * @ClassName 		FxCurrencyDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface FxCurrencyDao extends CommFuncDAO{
	public List<FxCurrency> loadCurrency();
	
	/*** 加载某种货币信息 **/
	public FxCurrency loadFxCurrencyIDByCurCode(@Param("currcode")String currcode);
	
	/**
	 * 返回货币实例
	 * @param currencyCode	货币编号
	 * @return 货币实例
	 */
	@Cacheable(value="portDataCache", key="'FxCurCode'+#currencyCode")
	public FxCurrency loadFxCurrency(@Param("currencyCode")String currencyCode);

	public List<FxCurrency> loadCurrencyList();
	
	@CacheEvict(value="portDataCache", key="'FxCurCode'+#currency.getId()")
	public void updateCurrency(@Param("currency")FxCurrency currency);
	
	public void saveCurrency(@Param("currency")FxCurrency currency);
	
	public List<FxCurrency> getCurrencyList();
}
