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
 * @ClassName 		RepoLendTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[正回购]  
 */

@Service("TranRepoBorrow")
public class TranRepoBorrow extends SecurityBasicService{
	@Resource(name="assetTermRepo")
	private SecurityAssetService assetService;
	
	@Resource(name = "rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name = "repoLendPost")
	private PostService postService;
	
	@Resource(name = "TranJobRepoBorrow")
	private TranJobService jobService;
	
	@Autowired
	private MsgService assembleTARepoBorrow;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.REPO_BORROW;
	}
	
	@Override
	public String tranDesc() {
		return "正回购";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/repo_borrow_form";
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
		return assembleTARepoBorrow;
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
