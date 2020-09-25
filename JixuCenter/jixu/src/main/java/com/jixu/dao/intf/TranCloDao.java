package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.data.pojo.SecPosition;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.job.obj.IntDoubleObj;
import com.orm.obj.TranClo;

/**
 * @ClassName 		TranCloDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranCloDao extends CommFuncDAO{

	public void deleteTranClose(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 	删除某个证券当日的头寸
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param closeDate
	 */
	public void deleteTranClose(String portnum, String cusip, int lotnum, Date closeDate);
	
	/**
	 * 获取当天tranclo的记录数量
	 * @param portnum	投资组合
	 * @param closeDate	关帐日期
	 * @return	记录数量
	 */
	public int getTranCloseTotalRecords(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 返回需要计算的利息基数,如果该利息已经结转,则返回0
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param closeDate
	 * @return
	 */
	public List<TranClo> getLastTranCloAccrued(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 判断该利息是否已被结转
	 * @param clo
	 * @return
	 */
	public boolean checkAccrued(@Param("clo")TranClo clo);
	
	/**
	 * 
	 * @param portnum	投资组合
	 * @param closeDate	关帐日
	 * @param assetType	证券类型
	 * @return
	 */
	public List<TranClo> loadTranCloWithAssetType(@Param("portnum")String portnum, @Param("closeDate") Date closeDate, @Param("assetType") String assetType);
	
	//查询需要结转的债券
	public List<TranClo> loadBondTranClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate, @Param("assetType") String assetType);
	
	/**
	 * 获取某天证券的头寸数量,不分lot
	 * @param portnum
	 * @param asOfDate
	 * @return
	 */
	public List<IntDoubleObj> getPositionAmountNoLot(@Param("portnum")String portnum, @Param("asOfDate") Date asOfDate);
	

	/**
	 * 获取起初对应的clo记录
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public TranClo loadTCloByLot(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 获取上一个记账日该证券的头寸
	 * @param portnu
	 * @param closeDate
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public TranClo getLastTranClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 获取当天所有的tranclo记录
	 * @param portfolio
	 * @param closeDate
	 * @return
	 */
	public List<TranClo> loadTranCloList(@Param("portfolio")String portfolio,@Param("portList")List<String> portList, @Param("closeDate") Date closeDate, @Param("classFetchFlag") boolean classFetchFlag);
	
	/**
	 * 获取某种证券类型的头寸
	 * @param portfolio
	 * @param closeDate
	 * @param assetType
	 * @return
	 */
	public List<TranClo> loadTranCloListByAssetType(@Param("portfolio")String portfolio, @Param("closeDate") Date closeDate, @Param("assetType") String assetType);
	
	/**
	 * @param portnum
	 * @param closeDate
	 * @param assetType
	 * @return	获取某种证券类型的头寸，允许应收利息为0
	 */
	public List<TranClo> loadTranCloWithAssetTypeNoAccrued(String portnum, Date closeDate, String assetType);
	
	/**
	 * 获取该日期之前的最近的clo记录，分lotnum
	 * @param portnum
	 * @param closeDate
	 * @param cusip
	 * @return
	 */
	public List<TranClo> loadTranCloListBeforeCloseDateWithClassFlag(@Param("portnum")String portnum,@Param("portList")List<String> portList, @Param("closeDate") Date closeDate, @Param("cusip") String cusip, @Param("classFetchFlag") boolean classFetchFlag);
	
	public List<TranClo> loadTranCloListBeforeCloseDate(@Param("portList")List<String> portList, @Param("closeDate") Date closeDate, @Param("cusip") String cusip);
	
	public TranClo loadTranClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate, @Param("cusip") String cusip, @Param("lotnum") int lotnum);

	/**
	 * 获取证券当天的头寸
	 * @param portnum 投资组合编号
	 * @param cusip		证券系统编号
	 * @param closeDate	关帐日期
	 * @return	证券当天的头寸
	 */
	public List<TranClo> loadTranCloListWithCusip(@Param("portnum")String portnum,@Param("portList")List<String> portList, @Param("cusip") String cusip, @Param("closeDate") Date closeDate, @Param("classFetchFlag") boolean classFetchFlag);
	
	public List<SecPosition> genResults(@Param("portList")List<String> portList,@Param("closeDate")Date closeDate);
	
	public List<TranClo> loadTranClose(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 删除出账日之后的所有头寸数据(包括当天)
	 * @param portnum
	 */
	public void deleteTranCloAfterLedgerDate(String portnum, Date ledgerDate);
}
