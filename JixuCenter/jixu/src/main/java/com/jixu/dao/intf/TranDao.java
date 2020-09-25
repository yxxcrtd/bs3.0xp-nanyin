package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Tran;

/**
 * @ClassName 		TranDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranDao extends CommFuncDAO{
	/**
	 * 获取当天做账的交易
	 * @param portnum
	 * @param ledgerDate
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public List<Tran> loadTrans(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	public List<Tran> loadTransToday(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("ledgerDate")Date ledgerDate);
	
	/**
	 * 获取当日卖出的证券的数据集
	 * @param portnum
	 * @param asOfDate
	 * @return	cusip,units
	 */
	public List<Tran> getSaleUnits(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	
	/**
	 * 获取当日卖出的非投资组合内部交易的数据集
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<Tran> getSaleUnitsUnInternalTran(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	
	public List<Tran> loadIncomeUpdateTrans(@Param("portnum")String portnum, @Param("tradeDate") Date tradeDate);
	
	public List<Tran> loadPurSaleTrans(@Param("tradedate")Date tradedate, @Param("portfolio") String portfolio);
	
	public List<Tran> loadPurSaleTransWithCusip(@Param("tradedate")Date tradedate, @Param("portfolio") String portfolio, @Param("cusip") String cusip);
	
	public List<Tran> loadTranListByTranType(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("tradeDate") Date tradeDate, @Param("tradeType") int tradeType);
	
	/**
	 * 获取交易
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param tranNum
	 * @return
	 */
	public Tran loadTran(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("tranNum") int tranNum);
	
	/**
	 * 根据交易编号找到交易记录
	 * @param tanum	交易编号
	 * @return	交易原始数据记录
	 */
	public Tran loadTranByTanum(@Param("tanum")String tanum);
	
	/**
	 * 查看该交易日之后,该股是否有交易
	 * @param portnum
	 * @param cusip
	 * @param tradeDate
	 * @return
	 */
	public int getTranNumByCusipDate(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("tradeDate") Date tradeDate,@Param("tanum")String tanum);
	
	public List<Tran> loadtranList(@Param("portList")List<String> portList, @Param("secid") String secid);
	
	/**
	 * 获取某股票一段期间内的交易
	 * @param portfolio	投资组合
	 * @param cusip	证券系统ID
	 * @param startDate	开始日期 >=
	 * @param endDate	结束日期	<=
	 * @return	期间内的交易
	 */
	public List<Tran> loadCusipTrans(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	public List<Tran> loadPositionTransBySetDate(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("endDate") Date endDate);
	
	/**
	 * 根据交易编号获取所有的相关交易系统，换汇，可转债等交易，一条tran_basic记录会产生多条tran记录
	 * @param tanum
	 * @return 某个交易编号的tran相关实例数据集
	 */
	public List<Tran> loadTransByTanum(@Param("tanum")String tanum);
	
	/**
	 * 获取投资组合下，某个证券lot下，第一笔建仓交易
	 * @param portnum	投资组合编号
	 * @param cusip		证券系统ID
	 * @param lotnum	lotnum
	 * @return	tran实例
	 */
	public Tran loadPreMaturityDepositTran(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	

	/**
	 * 查找一个区间时间内的交易
	 * @param portnum	投资组合
	 * @param startDate	开始日期
	 * @param endDate	结束如期
	 * @return	某个区间内的交易集
	 */
	public List<Tran> loadTransInPeriod(@Param("portnum")String portnum,@Param("portList")List<String> portList, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("classFetchFlag") boolean classFetchFlag); 
	
	/**
	 * 获取上一个关帐日到当天这个区间内的某个证券类型的交易
	 * @param portnum	投资组合编号
	 * @param startDate	开始日期
	 * @param endDate	截至日期
	 * @param assetType	证券类型
	 * @return	交易数据集
	 */
	public List<Tran> loadTransInPeriodWithAssetType(@Param("portnum")String portnum, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("assetType") String assetType); 
	
	/**
	 * 获取头寸的第一笔交易
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public Tran loadTranLot1(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 获取期间结算日在期间内的交易
	 * @param portfolio
	 * @param cusip
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Tran> loadCusipTransSettle(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	/**
	 * 获取期间结算日在期间内的交易
	 * @param portnum
	 * @param stateDate
	 * @param endDate
	 * @param classFetchFlag
	 * @return
	 */
	public List<Tran> loadTransInPeriodSettle(@Param("portnum")String portnum, List<String> portnums, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("classFetchFlag") boolean classFetchFlag);

	/**
	 * 获取期间结算日在期间内的交易
	 * @param portnum
	 * @param stateDate
	 * @param endDate
	 * @param assetType
	 * @return
	 */
	public List<Tran> loadTransInPeriodSettleWithAssetType(@Param("portnum")String portnum, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("assetType") String assetType);
	
	public void deleteTranAfterLedgerDate(String portnum, Date ledgerDate);
	
	/**
	 * 获取当天之前的所有交易，用以再次计算头寸
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<Tran> loadReopenRecalPostionTrans(String portnum, Date ledgerDate);
}
