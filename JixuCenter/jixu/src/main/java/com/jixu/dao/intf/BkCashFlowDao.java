package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.type.CashPositionModel;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkCashFlow;

/**
 * @ClassName 		BkCashFlowDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkCashFlowDao extends CommFuncDAO{
	
	public List<BkCashFlow> loadCashFlowList(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate);
	
	public List<BkCashFlow> loadCashFlowRecords(@Param("date")Date date, @Param("portfolio") String portfolio);

	/**加载现金流动信息**/
	public List<BkCashFlow> loadBankCashFlow(@Param("cashacctNO")int cashacctNO, @Param("portnum") String portnum, @Param("date") Date date);
	
	public List<BkCashFlow> loadCashFlowByCashAcct(@Param("date")Date date, @Param("bkcashAcctNo") int bkcashAcctNo, @Param("portnum") String portnum);
	
	public Pages<BkCashFlow> getCashFlow(@Param("pages")Pages<BkCashFlow> pages, @Param("portNum") String portNum, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public Page<BkCashFlow> getPageHelperCashFlow(@Param("page")Page<BkCashFlow> page, @Param("portNum") String portNum, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public List<BkCashFlow> loadCashFlowListByCashAcct(@Param("portnum")String portnum, @Param("cashacctID") int cashacctID);
	
	/**
	 * 获取现金头寸
	 * @param portNum	投资组合编号
	 * @param endDate	截至日期
	 * @return	现金头寸
	 */
	public List<CashPositionModel> getCashPosition(@Param("portNum")String portNum, @Param("endDate") Date endDate);

	/**
	 * 删除ledgerDate之后的现金流,用在撤账
	 * @param portnum
	 * @param ledgerDate
	 */
	public void deleteCashFlowAfterLedgerDate(String portnum, Date ledgerDate);
}
