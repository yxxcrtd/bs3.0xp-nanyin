package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.WorkFlowFactory;

/**
 * @ClassName 		WorkFlowFactoryDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface WorkFlowFactoryDao extends CommFuncDAO{
	
	public List<WorkFlowFactory> loadWorkFlow(@Param("portnum")String portnum, @Param("date") Date date);
	
	public List<WorkFlowFactory> loadWorkFlowWithStep(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate, @Param("step") int step);
	
	public List<Integer> loadWorkFlowJobStatus(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate, @Param("status")int status);

	/**
	 * 获取已经某天之后已经完成的job
	 * @param portfolios
	 * @param ledgerDate
	 * @return
	 */
	public List<WorkFlowFactory> loadCompleteWorkFlowAfterDay(String[] portfolios, Date ledgerDate);
	
	/**
	 * 撤账后统一更新workflow状态
	 * @param portnum
	 * @param ledgerDate
	 */
	public void updateWorkFlowStatusToOpen(String portnum, Date ledgerDate);
}
