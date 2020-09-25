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
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [投资组合间交易]
 */

@Service("TranPortfolioInternal")
public class TranPortfolioInternal extends SecurityBasicService {

	@Resource(name = "PostPortfolioInternal")
	private PostService purchasePost;

	@Resource(name = "TranJobCostUpdate")
	private TranJobService jobService;
	
	@Resource(name = "assemblePortfolioInternalTran")
	private MsgService msgService;

	@Override
	public SecurityAssetService assetService() {
		return null;
	}

	@Override
	public int tranType() {
		return TransTypes.Portfolio_InternalTran;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/cross_port_form";
	}

	@Override
	public String tranDesc() {
		return "投资组合间交易";
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
