package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.WfStatus;
import com.orm.obj.JobsHis;

/**
 * @ClassName 		JobsHisDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface JobsHisDao extends CommFuncDAO{
	
	public List<JobsHis> loadErrorJobsHis(@Param("ports")String[] ports, @Param("ledgrDate") Date ledgrDate, @Param("jobsList") List<Integer> jobsList);
	
	public List<JobsHis> loadJobsHis(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate);
	
	public List<WfStatus> loadJobsTotalNum(@Param("portfolios")String[] portfolios, @Param("ledgerDate") Date ledgerDate);

	/**
	 * 删除某个job类型当天的jobhis表数据，包括执行结果，错误信息等
	 * @param portnum	投资组合编号
	 * @param ledgerDate	日期
	 * @param jobID	某个job类型，交易关帐，交易日期帐等
	 */
	public void deleteJobsHist(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate, @Param("jobID") int jobID);
	
	/**
	 * 获取某个job类型当天的jobhis表数据，包括执行结果，错误信息等
	 * @param portnum	投资组合编号
	 * @param ledgerDate	日期
	 * @param jobID	某个job类型，交易关帐，交易日期帐等
	 * @return
	 */
	public JobsHis loadJobsHist(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate, @Param("jobID") int jobID);
	
	public void deleteJobsHists(@Param("portnums")String[] portnums, @Param("ledgerDate")Date ledgerDate, @Param("jobIDs")int[] jobIDs);
}
