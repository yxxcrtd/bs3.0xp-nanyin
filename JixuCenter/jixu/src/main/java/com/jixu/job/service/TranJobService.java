package com.jixu.job.service;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.job.obj.TranJobParams;
import com.jixu.journal.obj.CashCooa;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.GLCOOABase;
import com.jixu.journal.obj.SecCashObj;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		JobService
 * @author  		Pan Wei 
 * @Date 			2017年1月18日 下午3:17:22 	
 * @Description		[]  
 */

@Service("tranJobService")
public interface TranJobService {
	
	/**
	 * 	执行交易日记账
	 * @param pursaleCooa	交易凭证容器
	 * @param tranBasic		交易原始记录
	 * @param tran			交易记录
	 * @param totalFeeFunc	总费用
	 * @param chartnum		会计科目表
	 * @param groupnum		总账组
	 * @throws JNLException
	 */
	public void exeTranJob(TranJobParams params) throws JNLException;
	
	/**
	 * 	收入方面的交易日记帐,通常为自动job运行出来。比如股票股利
	 * @param portfolio	投资组合
	 * @param tranBasic	原始交易记录
	 * @return			交易日记帐
	 * @throws JNLException
	 */
	public GLCOOABase exeTranJob(PortfolioHdr portfolio, TranBasic tranBasic) throws JNLException;
	
	/**
	 * 	生成应收应付的估值增值凭证(在途现金由于汇率引起的估值增值)
	 * @param generalCooa	估增凭证容器
	 * @param seccashList	现金头寸
	 * @param gainAmount	数量
	 * @param chartnum		会计科目表
	 * @param groupnum		总账组
	 * @param tanum			交易编号
	 * @throws JNLException
	 */
	public void exeGeneralJob(GLCOOABase generalCooa, List<SecCashObj> seccashList,double gainAmount, String chartnum, int groupnum, String tanum) throws JNLException;
	
	/**
	 * 	生成现金凭证
	 * @param cashflowObjList	现金流
	 * @param tranbasic			原始交易记录
	 * @param portfolio			投资组合
	 * @param desc				交易描述
	 * @param ledgerDate		做账日 
	 * @return					现金凭证
	 * @throws JNLException
	 */
	public List<CashCooa> exeCashJob(List<CashFlowObj> cashflowObjList, TranBasic tranbasic, PortfolioHdr portfolio, String desc, Date ledgerDate) throws JNLException;
}
