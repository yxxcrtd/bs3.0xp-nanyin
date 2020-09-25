package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.CashFlowFloatRate;
import com.orm.obj.CashFlowRate;

/**
 * @ClassName 		CashFlowFloatRateDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CashFlowFloatRateDao extends CommFuncDAO{
	
	public List<CashFlowFloatRate> loadCashFlowFloatRateByCfID(@Param("cfID")String cfID);

	/**
	 * 根据现金流ID和做账日，获取目前使用的利率重置配置
	 * @param cashFlowID	现金流ID
	 * @param ledgerDate 做账日
	 * @return
	 */
	public CashFlowRate getCashFlowRate(@Param("cashFlowID")String cashFlowID, @Param("ledgerDate") Date ledgerDate);
}
