package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.SecTradeSummaryModel;
import com.orm.obj.TranBasic;

/**
 * @ClassName 		TranBasicDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranBasicDao extends CommFuncDAO{
	public List<TranBasic> loadTranBasicList(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	public List<TranBasic> loadTranBasicByCusip(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("ledgerDate") Date ledgerDate);
	
	public List<TranBasic> loadTranBasic(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate);
	
	public int getTranTotalNum(@Param("cusip")String cusip);
	
	public TranBasic getMapedContractTranBasic(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	public int getTranBasicCountByPortnum(@Param("portnum")String portnum);
	
	public Pages<TranBasic> getTranBasic(@Param("pages")Pages<SecTradeSummaryModel> pages, @Param("secType")String secType, @Param("secID") String secID, @Param("portNum") List<String> portList, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("displayDelete")Boolean displayDelete);
	
	public Page<TranBasic> getPageHelperTranBasic(Page<TranBasic> page,@Param("secType")String secType, @Param("secID") String secID, @Param("portList") List<String> portList, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("displayDelete")Boolean displayDelete);
	
	public TranBasic loadTranBasicByID(@Param("tanum")String tanum);
	
	public  Pages<TranBasic> getTranBasicByTranType(@Param("portList")List<String> portList, @Param("tranTypes") int[] tranTypes, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("pages")Pages<SecTradeSummaryModel> pages, @Param("displayDelete")Boolean displayDelete);
	
	public  Page<TranBasic> getPageHelperTranBasicByTranType(@Param("portList")List<String> portList, @Param("tranTypes") int[] tranTypes, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("page")Page<TranBasic> page, @Param("displayDelete")Boolean displayDelete);

	/**
	 * 获取头寸的第一笔交易的原始数据
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public TranBasic loadTranBasicLot1(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);

	public TranBasic getFCOpenTranBasic(String portnum, String secid,int tranType);
	
	public List<TranBasic> getForwardSmfTranBasic(String secid,int tranType);
	
	public TranBasic getForwardSmf(String secid, int tranType);
	
	public List<TranBasic> loadTranBasicList(String assetType, Date tradeDate);

	/**
	 * 获取做账日自动执行的交易
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<String> loadAutoTranBasicList(String portnum, Date ledgerDate);
	
	public List<TranBasic> loadTranBasicByContract(String portnum,String cusip,String contract);
	
	public List<TranBasic> loadTranBasicCusipPost(String portnum,String cusip);
	
	/**
	 * 外汇掉期直接可以根据证券id获取第一笔交易
	 * @param portnum
	 * @param cusip
	 * @return
	 */
	public TranBasic getSwapFxTranBasic(String portnum, String cusip);
	
	/**
	 * 更新基础交易表的交易状态
	 * @param portnum
	 * @param ledgerDate
	 */
	public void updateTranBasicPostStatusLedgerDate(String portnum, Date ledgerDate);
	
	/**
	 * 获取当天出账但是没有post的交易
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<TranBasic> loadUnpostedTranBasic(String portnum, Date ledgerDate);
	
	
	/**
	 * 获取外汇远期当天手动做的自动到期但是没有post的交易
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<TranBasic> loadForwrdUnpostedTranBasic(String portnum, Date ledgerDate,String assetType);
	
	/**
	 * 用于删除joyin表里的记录
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<TranBasic> loadTranBasicAfterLedgerDate(String portnum, Date ledgerDate);

	/**
	 * 把包括今天之后的自动生成的交易标记为删除
	 * @param portnum
	 * @param ledgerDate
	 */
	public void deleteAutoTranBasic(String portnum, Date ledgerDate);
	
	/**
	 * 外汇远期开仓交易删除校验
	 */
	public TranBasic loadForwardListByTranType(String portfolio, String cusip, int tradeType);
	
	/**
	 * 获取当天某个组合某个券某个交易类型所有交易
	 * @param portnum
	 * @param ledgerDate
	 * @param tranType
	 * @return
	 */
	public List<TranBasic> loadUndeletedTranBasicByCusip(String portnum,String cusip, Date ledgerDate,int tranType);
	
	/**
	 * 查询某组合下 某账户的交易信息
	 * @param portnum
	 * @param acctNo
	 * @return
	 */
	public List<TranBasic> loadTranBasicByBankAcct(String portnum,int acctNo);
	
	/**
	 * 	获取joyin录入的手工凭证
	 * @param portnum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<TranBasic> loadManualValTran(String portnum, Date startDate, Date endDate);
	
}
