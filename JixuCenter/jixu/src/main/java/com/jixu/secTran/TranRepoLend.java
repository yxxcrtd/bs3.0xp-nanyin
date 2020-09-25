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
 * @ClassName RepoLendTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [股票交易]
 */

@Service("SecTranRepoLend")
public class TranRepoLend extends SecurityBasicService {
	@Resource(name = "repoLendPost")
	private PostService postService;

	@Resource(name = "rcpyPurchaseService")
	private PostToRCPYService postToRcpy;

	@Resource(name = "tranJobRepoLend")
	private TranJobService jobService;

	@Resource(name = "assetTermRepo")
	private SecurityAssetService assetService;
	
	@Resource(name = "taRepoLend")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		// TODO Auto-generated method stub
		return assetService;
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public int tranType() {
		return TransTypes.REPO_LEND;
	}

	@Override
	public String tranDesc() {
		return "逆回购";
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/repo_lend_form";
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
		// TODO Auto-generated method stub
		return null;
	}
}
