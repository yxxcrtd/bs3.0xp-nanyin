package com.jixu.job.service;

import java.util.Date;
import java.util.List;

import com.jixu.common.exception.JNLException;
import com.jixu.job.obj.AcctDCBalance;
import com.jixu.journal.obj.GLCOOABase;
import com.orm.obj.PortExpenseCfg;

/** 
 * @ClassName 		AfterJobService
 * @author  		Pan Wei 
 * @Date 			2018年4月30日 下午1:22:03 	
 * @Description		[]  
 */

public interface TaxFeeJobService {
	
	/**
	 * 计算税费并生成凭证
	 * @param cfg	税费配置模块
	 * @param asOfDate	日期
	 * @return	税费凭证
	 * @throws JNLException
	 */
	public GLCOOABase executeJob(int userID, List<AcctDCBalance> balList, PortExpenseCfg cfg, Date asOfDate) throws JNLException;
	
	/**
	 * 一个类代表了一个税费计算方法，这里返回的jobName将会直接出现在页面让用户配置是否采用该税费计算方式。然后基于PortExpenseCfg的配置计算凭证
	 * @return	税费计算方法的名称，例如：营改增-估值增值,营改增-差价收入,费前净值-管理费等
	 */
	public String jobName();
	
	public String jobDesc();
}
