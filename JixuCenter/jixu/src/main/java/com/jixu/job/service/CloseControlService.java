package com.jixu.job.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.TranBasic;

/**
 * @author "taotaizhu"
 *
 */

public interface CloseControlService {
	/**
	 * 交易关帐,Portfolio_Hdr的lastTranClose=closeDate,nextTranClose=closeDate+1
	 * @param userID	用户ID
	 * @param portnum	投资组合编号
	 * @param closeDate	关帐日起
	 * @return	关帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer closeTran(int userID, String portnum, Date closeDate);
	
	/**
	 * 交易开账，遍历投资组合，调用reopenTran(int userID, String portnum, Date closeDate)
	 * @param userID	用户ID
	 * @param portnums	投资组合编号数组
	 * @param closeDate	关帐日期
	 * @return	关帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenTran(int userID, String[] portnums, Date closeDate);
	
	/**
	 * 交易开账，系统会找最近一个关帐日，遍历投资组合，调用reopenTran(int userID, String portnum, Date closeDate)
	 * @param userID	用户ID
	 * @param portnums	投资组合数组
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenTran(int userID, String[] portnums);
	
	/**
	 * 交易开账,Portfolio_Hdr的nextTranClose=lastTranClose，lastTranClose=lastTranClose-1,
	 * @param userID	用户ID
	 * @param portnum	投资组合编号
	 * @param closeDate	关帐日
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenTran(int userID, String portnum, Date closeDate);
	
	/**
	 * 交易开账，系统会找最近一个关帐日，调用reopenTran(int userID, String portnum, Date closeDate)	
	 * @param userID	用户ID
	 * @param portnum	投资组合编号
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenTran(int userID, String portnum);
	
	/**
	 * 总账关帐,遍历投资组合，调用closeGL(int userID, String portnum, Date closeDate)
	 * @param userID	用户ID
	 * @param portnums	投资组合编号数组
	 * @param closeDate	关帐日期
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer closeGL(int userID, String[] portnums, Date closeDate);
	
	/**
	 * 总账关帐,Portfolio_Hdr的lastGLClose=closeDate,nextGLClose=closeDate+1
	 * @param userID	用户ID
	 * @param portnum	投资组合编号
	 * @param closeDate	关帐日期
	 * @return 关帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer closeGL(int userID, String portnum, Date closeDate);
	
	/**
	 * 总账开账,Portfolio_Hdr的nextGLClose=lastGLClose，lastGLClose=lastGLClose-1,
	 * @param userID	用户ID
	 * @param portnum	投资组合编号
	 * @param closeDate	关帐日期
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenGL(int userID, String portnum, Date closeDate);
	
	/**
	 * 总账开账，遍历投资组合，调用reopenGL(int userID, String portnum, Date closeDate)
	 * @param userID	用户ID
	 * @param portnums	投资组合编号数组
	 * @param closeDate	关帐日期
	 * @return 开帐成功与否，如果有错误，则带回错误信息
	 */
	public ResultContainer reopenGL(int userID, String[] portnums, Date closeDate);

	public ResultContainer revokeDealAccount(List<TranBasic> tranbasicList);
}
