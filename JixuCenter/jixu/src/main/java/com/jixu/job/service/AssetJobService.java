package com.jixu.job.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.ResultContainer;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.STCostWrapObj;
import com.jixu.journal.obj.SecIncomeWrapObj;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		JobService
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:17:22 	
 * @Description		[]  
 */

@Service("tranJobService")
public interface AssetJobService {
	
	/**
	 * 	头寸由于价格和汇率产生的估增日记账
	 * @return
	 */
	public GLCOOABase exeGeneralJob(PortfolioHdr portfolio, STCostWrapObj stcostWrap, Date ledgerDate) throws JNLException;
	
	/**
	 * 	收入凭证, 债券利息、股票股利
	 * @return
	 */
	public GLCOOABase exeIncomeJob(SecIncomeWrapObj sourceData) throws JNLException;
	
	/**
	 * 	目前是计算每天tranclo表中, accrued的累计值
	 * @param sec
	 * @param clo			
	 * @param lastClo		上一个关帐日
	 * @param periodTrans	这段日期内的交易
	 * @return
	 */
	public TranClo closeTsum2TcloJob(PortfolioHdr portfolio, IssueInfo sec, TranClo clo, TranClo lastClo, List<Tran> periodTrans, List<Tran> settleTranList);
	
	/**
	 * 	生成自动到期的交易
	 * @param userID
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 * @throws JNLException
	 */
	public ResultContainer autoJob(int userID, String portnum, Date ledgerDate) throws JNLException;
}
