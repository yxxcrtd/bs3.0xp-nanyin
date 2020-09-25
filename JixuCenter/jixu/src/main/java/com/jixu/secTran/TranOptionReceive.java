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
 * @ClassName 		TramOptionBuy
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:45:36 	
 * @Description		[]  
 */


@Service("TranOptionReceive")
public class TranOptionReceive extends SecurityBasicService{
	
	@Resource(name="AssetOption")
	private SecurityAssetService assetService;
	
	@Resource(name="PostOptionReceive")
	private PostService postService;
	
	@Resource(name="tranJobStockDividend")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranOption")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.Option_Receive;
	}
	
	@Override
	public String tranDesc() {
		return "期权获赠";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/option_receive_form";
	}
	
	@Override
	public TranJobService createJobService() {
		return jobService;
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
	public MsgService msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "option_awarded_edit";
	}
}
