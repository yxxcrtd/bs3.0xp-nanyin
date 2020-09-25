package com.jixu.secTran;

import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		SecurityBasicService
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午7:08:38 	
 * @Description		[建立一个证券类型后,需要完成的所有方法]  
 */

public interface SecurityBasic {
	
	/**
	 * 交易类型
	 * 一旦产生交易,将不允许修改此值
	 * @return
	 */
	public int tranType();
	
	public int workFlowSteps();
	
	public String tranDesc();
	
	/**
	 * 返回属于哪种证券类型
	 * @return
	 */
	public SecurityAssetService assetService();
	
	/**
	 * 返回交易消息
	 * @return
	 */
	//public MsgService createTranMsgAssemble();
	
	/**
	 * 返回Post类
	 * @return
	 */
	public PostService createPostService();

	/**
	 * 返回Post交易后,生成应收应付
	 * @return
	 */
	public PostToRCPYService createPostToRCPYService();
	
	/**
	 * 返回Job的实现类
	 * @return
	 */
	public TranJobService createJobService();
	
	public MsgService msgService();
	
	public String tradeFormURL();
	
	public String newTradeFormURL();

	/**
	 * buy是true
	 * sale是false
	 * @return
	 */
	public boolean buySaleFlag();
}
