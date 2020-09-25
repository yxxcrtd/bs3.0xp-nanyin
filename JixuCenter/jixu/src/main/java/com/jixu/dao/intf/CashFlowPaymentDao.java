package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.CashFlowPayment;

/**
 * @ClassName 		CashFlowPaymentDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CashFlowPaymentDao extends CommFuncDAO{
	
	public List<CashFlowPayment> loadCashFlowPaymentByCfID(@Param("cfID")String cfID);

	/**
	 * 获取现金流配置的付息计划
	 * @param cashflowID
	 * @return
	 */
	public List<Date> loadCashFlowSchedule(@Param("cashflowID")String cashflowID);
	
	/**
	 * 从joyin利率重置表中，通过现金流id和重置日获取基准利率日期
	 * @param cfgid
	 * @param resetDate
	 * @return
	 */
	public Date getCashFlowResetRateDate(String cfgid, Date resetDate);
	
	/**
	 * 获取浮动利率重置计划的日期
	 * @param cashflowID
	 * @return
	 */
	public List<Date> loadCashFlowRestSchedule(String cashflowID);
}
