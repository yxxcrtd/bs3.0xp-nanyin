package com.jixu.post.service;

import com.jixu.common.type.ResultContainer;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranSum;

public interface PostService {

	public void post(TranBasic tranAdvice, ResultContainer container);

	public void reverse(TranBasic tranAdvice, ResultContainer container);

	public void delete(TranBasic tranAdvice, ResultContainer container);
	
	/**
	 * 计算tsum记录的核心逻辑，是整个系统的运算核心
	 * tran是原来的
	 * 
	 * @param tran
	 * @param tSum
	 * @return
	 */
	public TranSum CaltSum(Tran tran, TranSum tSum);
	
	/**
	 * 
	 * @param tran
	 * @param tSum
	 * @return
	 */
	public TranSum unpostCaltsum(Tran tran, TranSum tSum);
	
	/**
	 * 交易在输入之前的校验
	 * @param tran
	 * @param container
	 */
	public void preValidateTranBasic(TranBasic tranBasic, ResultContainer container);
}
