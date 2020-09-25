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
 * @ClassName TranTimeDepositBuy
 * @author Pan Wei
 * @Date 2017年5月31日 下午2:30:52
 * @Description [目前大多模仿repo,逆回购]
 */
@Service("TranTimeDepositBuy")
public class TranTimeDepositBuy extends SecurityBasicService {

	@Resource(name = "AssetTimeDeposit")
	private SecurityAssetService assetService;

	@Resource(name = "repoLendPost")
	private PostService postService;

	@Resource(name = "rcpyPurchaseService")
	private PostToRCPYService postToRcpy;

	@Resource(name = "tranJobRepoLend")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranTimeDepositBuy")
	private MsgService msgService;

	@Override
	public int tranType() {
		return TransTypes.TimeDep_Buy;
	}

	@Override
	public String tranDesc() {
		return "存款买入";
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
		return "/forms/trade/tradeForms/deposit_buy_form";
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
