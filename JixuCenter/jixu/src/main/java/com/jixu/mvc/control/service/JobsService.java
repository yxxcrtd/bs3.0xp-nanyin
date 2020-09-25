package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.type.ResultContainer;

public interface JobsService {
	
	/**
	 * 运行当天所有的任务，
	 *  1. 自动结转
	 *  2. 关账
	 *  3. 跑批
	 *  4. 计算净值
	 *  5. 关账
	 * @param ports
	 * @param ledgerDate
	 * @return
	 */
	public ResultContainer executeJobs(List<String> ports, Date ledgerDate);
	
	/**
	 * 撤回所有的jobs
	 * @param ports
	 * @param ledgerDate
	 * @return
	 */
	public ResultContainer redoAllJobs(List<String> ports, Date ledgerDate);
	
	/**
	 * 
	 * @param ports	投资组合列表
	 * @param ledgerDate	做账日期
	 * @param jobsSteps		需要跑账的步骤, 自动化, 关交易, 日记账,计算净值,关总账。按照顺序传入，如果顺序错误，会返回错误结果
	 * @param dailyJobIDs	如果前面配了日记账，这里需要传入跑哪些日记账
	 * @param redoFlag		如果已经有了凭证，是否需要撤账
	 * @return
	 */
	public ResultContainer executeAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps, int[] dailyJobIDs, boolean redoFlag);

	/**
	 * 撤销指定的jobs
	 * @param ports
	 * @param ledgerDate
	 * @param jobsSteps	需要撤销的job,关总账,计算净值,日记账,关交易,自动化 。按照顺序传入，如果顺序错误，会返回错误结果
	 * @return
	 */
	public ResultContainer redoAppointedJobs(List<String> ports, Date ledgerDate, int[] jobsSteps);
	
	
	/**
	 * 执行指定job
	 * @param portnum
	 * @param ledgerDate
	 * @param jobID
	 * @return
	 */
	public ResultContainer executeJobByJobID(String portnum, Date ledgerDate, int jobID);
}
