package com.jixu.dao.intf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.job.obj.FollowFeeBean;
import com.orm.obj.TranFeeFollowClo;

/**
 * @ClassName 		TranFeeFollowCloDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranFeeFollowCloDao extends CommFuncDAO{

	public void deleteTranFeeFollowClo(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	public List<TranFeeFollowClo> loadTranFeeFollowClo(@Param("portID")String portID, @Param("closeDate") Date closeDate);
	
	/**
	 * 获取最新的费用头寸信息
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param feeType
	 * @param asOfDate
	 * @return
	 */
	public TranFeeFollowClo getTranFeeFollowClo(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum, @Param("feeType") int feeType, @Param("asOfDate") Date asOfDate);
	
	/**
	 * 获取浮动费用需要计费的头寸
	 * @param portnum
	 * @param closeDate
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public List<FollowFeeBean> getFollowFeeListWithCusip(@Param("portnum")String portnum, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
	
	/**
	 * 获取某一天所有的浮动费用关帐记录
	 * @param portnum
	 * @param closeDate
	 * @return
	 */
	public List<TranFeeFollowClo> getFollowFeeList(@Param("portnum")String portnum, @Param("closeDate") Date closeDate);
	
	/**
	 * 获取浮动费用的记录
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public List<TranFeeFollowClo> getTranFeeFollowClos(@Param("portnum")String portnum, @Param("closeDate") Date closeDate, @Param("cusip") String cusip, @Param("lotnum") int lotnum);
}
