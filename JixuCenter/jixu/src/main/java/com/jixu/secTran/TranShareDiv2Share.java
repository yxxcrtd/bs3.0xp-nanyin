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
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service
public class TranShareDiv2Share extends SecurityBasicService{
	@Resource(name="assetCapital")
	private SecurityAssetService assetService;
	
	@Resource(name = "tranShareDiv2ShareMsg")
	private MsgService msgService;
	
	@Resource(name="postShareBankZGDiv2Share")
	private PostService subredPost;
	
	@Resource(name="tranJobShareSub")
	private TranJobService jobService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Share_Div2Share;
	}
	
	@Override
	public String tranDesc() {
		return "红利再投";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.ta;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/share_div2share_form";
	}
	
	@Override
	public PostService createPostService() {
		return subredPost;
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
		return false;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
