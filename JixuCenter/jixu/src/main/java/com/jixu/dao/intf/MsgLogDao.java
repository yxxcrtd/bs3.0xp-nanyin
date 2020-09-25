package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.pojo.MsgLog;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.WfFailedData;
import com.jixu.mvc.control.model.WfStatus;

/**
 * @ClassName 		MsgLogDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface MsgLogDao extends CommFuncDAO{
	
	/**
	 * 获取操作日志
	 * @param portfolios
	 * @param ledgerDate
	 * @param operationType
	 * @return
	 */
	public Pages<MsgLog> loadMsgLog(@Param("pages")Pages<MsgLog> pages, @Param("portfolios") String[] portfolios, @Param("ledgerDate") Date ledgerDate, @Param("operationType") int operationType);
	
	public Page<MsgLog> loadPageHelperMsgLog(@Param("pages")Page<MsgLog> page, @Param("portfolios") String[] portfolios, @Param("ledgerDate") Date ledgerDate, @Param("operationType") int operationType);
	
	public List<WfFailedData> loadMsgLogMarketList(@Param("portfolios")String[] portfolios, @Param("operationStep") int operationStep, @Param("ledgerDate") Date ledgerDate);
	
	public List<WfStatus> loadMsgLogMarketTotalNum(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate);
	
	public List<WfStatus> loadMsgLoTradeTotalNum(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate);
	
	public String loadMsg(@Param("logid")long logid);

}
