package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.PortExpenseCfg;

/**
 * @ClassName 		PortExpenseCfgDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PortExpenseCfgDao extends CommFuncDAO{

	public List<PortExpenseCfg> loadTaxFeeCfgOldNetAssetByPortNum(@Param("portnum")String portnum);
	
	/**
	 * 按照优先级ordernum来读取税费配置模块
	 * @param portnum
	 * @return
	 */
	public List<PortExpenseCfg> loadTaxFeeCfgNewNetAssetByPortNum(@Param("portnum")String portnum);
	
	/**
	 * 删除该条费用
	 * @param expID
	 */
	public void delExpense(@Param("expID")int expID);
	
	public boolean checkSamePortExpenseExise(@Param("portnum")String portnum, @Param("expenseName") String expenseName);
	
	/**
	 * 根据投资组合获取相应的费用配置
	 * @param portnum
	 * @return
	 */
	public List<PortExpenseCfg> loadExpenseListByPortnum(@Param("portnum")String portnum);
	
}
