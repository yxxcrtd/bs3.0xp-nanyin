package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkCashRcpy;

/**
 * @ClassName 		BkCashRcpyDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkCashRcpyDao extends CommFuncDAO{
	public List<BkCashRcpy> loadRCPYByEndDate(@Param("portfolio")String portfolio, @Param("endDate") Date endDate);
	
	public int loadRcpyNumByTanum(@Param("tanum")String tanum);
	
	public List<BkCashRcpy> loadRcpyList(@Param("tanum")String tanum);
	
	/*加载应收应付款*/
	public List<BkCashRcpy> loadSecCashList(@Param("portfolio")String portfolio, @Param("date") Date date);
	
	/*加载冲销应收应付款*/
	public List<BkCashRcpy> loadSecCashListCX(@Param("portfolio")String portfolio, @Param("date") Date date);
	
	public List<BkCashRcpy> loadRCPYByTanum(@Param("tanum")String tanum);
	
	public Pages<BkCashRcpy> getCashRcpy(@Param("pages")Pages<BkCashRcpy> pages, @Param("portNum")String portNum, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public Page<BkCashRcpy> getPageHelperCashRcpy(@Param("pages")Page<BkCashRcpy> page, @Param("portNum")String portNum, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * 删除出账日之后的所有应收应付(包括当天)
	 * @param portnum
	 */
	public void deleteRcpyAfterLedgerDate(String portnum, Date ledgerDate);
}
