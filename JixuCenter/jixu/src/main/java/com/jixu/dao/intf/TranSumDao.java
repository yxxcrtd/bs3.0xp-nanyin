package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.util.Pages;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.SecPositionModel;
import com.orm.obj.TranSum;
import com.orm.obj.TranSumRecord;

/**
 * @ClassName 		TranSumDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranSumDao extends CommFuncDAO{
	
	/*加载头寸(>0)记录*/
	public List<TranSum> loadSecPosition(@Param("portfolio")String portfolio);
	
	/*加载头寸(>0)记录*/
	public List<TranSum> loadSecPositionWithAssetType(@Param("portfolio")String portfolio, @Param("assetType") String assetType);
	
	/**
	 * 如果有买多卖空,分为两个头寸
	 * @param portfolio
	 * @param cusip
	 * @param secacct
	 * @param longshort
	 * @return
	 */
	public TranSum loadTSum(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("secacct") String secacct, @Param("longshort") int longshort);
	
	/**
	 * 获取所有的头寸
	 * @param portfolio
	 * @param cusip
	 * @param secacct
	 * @param longshort
	 * @return
	 */
	public List<TranSum> loadTSums(@Param("portfolio")String portfolio, @Param("cusip") String cusip, @Param("secacct") String secacct, @Param("longshort") int longshort);
	
	public List<TranSum> loadTranSumByPortCusipForSale(@Param("portfolio")String portfolio, @Param("cusip") String cusip, int longshort);
	
	public TranSum loadTranSumByPortCusip(@Param("portfolio")String portfolio, @Param("cusip") String cusip, int longshort);
	
	public int findMaxLotNumByCusip(@Param("portfolio")String portfolio, @Param("cusip") String cusip);
	
	public int findMaxLotNumByFxTranFlag(@Param("portnum")String portnum, @Param("fxTranFlag") int fxTranFlag);
	
	public TranSum loadFxTranTSum(@Param("portnum")String portnum, @Param("amount") double amount, @Param("amount2") double amount2, @Param("lsttradeDate") Date lsttradeDate, @Param("lstSettledate") Date lstSettledate);
	
	public double getTotalTsumAmountByCusip(@Param("portfolio")String portfolio, @Param("cusip") String cusip);
	
	public TranSum getMapedRepoTranSum(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 根据lot获取该tsum记录
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public TranSum loadTSumByLot(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 获取某个lot的最新的证券头寸
	 * @param portnum	投资组合编号
	 * @param cusip		证券系统ID
	 * @param lotnum	lotnum
	 * @return	证券某个lot的最新证券头寸
	 */
	public TranSum loadTSumByCusipLot(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	//获取远期换汇头寸
	public TranSum loadTranSumByCusip(@Param("portnum")String portnum, @Param("cusip") String cusip);
	
	/**
	 * 获取该股票的所有tsum信息
	 * @param portnum	投资组合编号
	 * @param cusip		证券系统ID
	 * @return	投资组合下某个证券的所有头寸
	 */
	public List<TranSum> loadTSumByCusip(@Param("portnum")String portnum, @Param("cusip") String cusip);

	public List<TranSum> loadTranSum(@Param("portnum")String portnum, @Param("secid") String secid);
	
	/**
	 * 获取投资组合投资过的所有证券系统内部ID
	 * @param pages		获取第几页的
	 * @param portnum	投资组合编号
	 * @return 投资组合投资过的所有证券系统内部ID
	 */
	public Pages<String> loadTranedSecCusip(@Param("pages")Pages<SecPositionModel> pages, @Param("portnum")String portnum);
	
	public Page<String> loadPageHelperTranedSecCusip(@Param("page")Page<SecPositionModel> page,@Param("portnum")String portnum);

	/**
	 * 清空tsum表某个投资组合所有的数据
	 * @param portnum
	 */
	public void deleteAllTSumDataByPortnum(String portnum);
	
	/**
	 * 获取transumrecord表的数据，用以还原transum表
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<TranSumRecord> loadTranSumRecordByPortDate(String portnum, Date ledgerDate);
	
	/**
	 * 获取还有头寸的tsum
	 * @param portnum
	 * @return
	 */
	public List<TranSum> loadOpenedTranSum(String portnum);
	
	/**
	 * 根据投资组合和日期删除镜像记录
	 * @param portnum
	 * @param ledgerDate
	 */
	public void deleteTranSumRecord(String portnum, Date ledgerDate);
}
