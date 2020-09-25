package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.CashFlowCfg;

/**
 * @ClassName 		CashFlowCfgDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CashFlowCfgDao extends CommFuncDAO{
	
	public CashFlowCfg loadCashFlowCfgBySecid(@Param("secid")String secid);
	
	public List<CashFlowCfg> loadCashFlowCfgList(@Param("name")String name);
	
	public CashFlowCfg loadCashFlowCfgByName(@Param("name")String name);

	/**
	 * 获取现金流配置
	 * @param cfgID
	 * @return
	 */
	@Cacheable(value="secDataCache", key="'CashFlowCfgID'+#cfgID")
	public CashFlowCfg loadCashFlowCfg(@Param("cfgID")String cfgID);
}
