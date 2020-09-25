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
 * @Description		[货币基金申购]  
 */

@Service("TranFundDivident")
public class TranFundCashDiv2Share extends SecurityBasicService{

	@Resource(name="AssetCommonFund")
	private SecurityAssetService assetService;
	
	@Resource(name="PostComFundDiv")
	private PostService postService;
	
	@Resource(name = "TranJobComFundCash2Share")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranComFundCashDiv")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.CommonFund_Divident2Share;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fund_cashdiv_form";
	}
	
	@Override
	public String tranDesc() {
		return "红利再投";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public PostService createPostService() {
		return postService;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}
