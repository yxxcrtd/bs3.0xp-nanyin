package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[银行资管支付相关费用]  
 */

@Service
public class TranShareBankZGPayFee extends SecurityBasicService{
	
	@Autowired
	private PostService postShareBankZGPayFee;
	
	@Resource(name="rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobShareBankZGPayFee")
	private TranJobService jobService;
	
	@Resource(name="assetBankZiGuan")
	private SecurityAssetService assetService;
	
	@Autowired
	private MsgService tranBankZiGuanPayFeeMsg;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.ShareBankZGPayFee;
	}
	
	@Override
	public String tranDesc() {
		return "批次费用支付";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fundProduct_payment_form";
	}

	@Override
	public PostService createPostService() {
		return postShareBankZGPayFee;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		return postToRcpy;
	}
	
	@Override
	public TranJobService createJobService() {
		return jobService;
	}
	
	@Override
	public MsgService msgService() {
		return tranBankZiGuanPayFeeMsg;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
