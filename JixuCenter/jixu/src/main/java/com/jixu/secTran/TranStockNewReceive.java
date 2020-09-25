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
 * @ClassName 		TranStockNewReceive
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[新股中签]  
 */

@Service("TranStockNewReceive")
public class TranStockNewReceive extends SecurityBasicService{

	@Resource(name="assetCommonStock")
	private SecurityAssetService assetService;
	
	@Resource(name="slStockPurPostImp")
	private PostService postService;
	
	@Resource(name = "jixuStockNewReceiveDtoMsg")
	private MsgService msgService;
	
	@Resource(name = "tranJobStockNewReceive")
	private TranJobService jobService;
	
	@Resource(name = "rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Override
	public SecurityAssetService assetService() {
		// TODO Auto-generated method stub
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.NewStock_Receive;
	}
	
	@Override
	public String tradeFormURL() {
		return "";
	}
	
	@Override
	public String tranDesc() {
		return "新股中签";
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
		return "stock_newReceive_edit";
	}
}
