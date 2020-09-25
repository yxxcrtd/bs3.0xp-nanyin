package com.jixu.dao.intf;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.PerformanceFeeCfg;

/**
 * @ClassName 		PerformanceFeeCfgDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PerformanceFeeCfgDao extends CommFuncDAO{

	/**
	 * 获取绩效费配置
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public PerformanceFeeCfg loadPerformanceFeeCfg(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	@CacheEvict(value="secFeeCache", key="'PerformanceFeeActive_'+#pfc.getPortnum()+#pfc.getCusip()+#pfc.getLotnum()")
	public void updatePerformanceFee(@Param("pfc")PerformanceFeeCfg pfc);
	
	/**
	 * 获取绩效费配置
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	@Cacheable(value="secFeeCache", key="'PerformanceFeeActive_'+#portnum+#cusip+#lotnum")
	public PerformanceFeeCfg loadActivePerformanceFeeCfg(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
}
