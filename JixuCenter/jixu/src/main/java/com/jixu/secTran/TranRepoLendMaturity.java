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
 * @ClassName 		RepoLendTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("SecTranRepoLendMaturity")
public class TranRepoLendMaturity extends SecurityBasicService{
	
	@Resource(name="repoLendMaturityPost")
	private PostService postService;
	
	@Resource(name="tranJobRepoLendMaturity")
	private TranJobService jobService;
	
	@Resource(name="assetTermRepo")
	private SecurityAssetService assetService;
	
	@Resource(name = "rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name = "taRepoLendMaturity")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.REPO_LEND_MATURITY;
	}
	
	@Override
	public String tranDesc() {
		return "逆回购到期";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return null;
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
		return false;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
