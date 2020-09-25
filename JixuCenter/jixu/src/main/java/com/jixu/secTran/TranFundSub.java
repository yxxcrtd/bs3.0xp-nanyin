package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		MMFundSubTran
 * @author  		XC 
 * @Date 			2017/4/30 	
 * @Description		[普通基金申购]  
 */

@Service("TranFundSub")
public class TranFundSub extends SecurityBasicService{

	@Resource(name="AssetCommonFund")
	private SecurityAssetService assetService;
	
	@Resource(name="rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="slStockPurPostImp")
	private PostService purchasePost;
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranComFund")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.CommonFund_Sub;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fund_all_form";
	}
	
	@Override
	public String tranDesc() {
		return "申购|买入";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public PostService createPostService() {
		return purchasePost;
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
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "common_fund_buy_edit";
	}
}
