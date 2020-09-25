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
 * @ClassName TranForwardCurrencyClose
 * @author Wang Zhongyuan
 * @Date 2018年8月15日 下午8:21:17
 * @Description [远期换汇]
 */

@Service
public class TranForwardCurrencyClose extends SecurityBasicService {

	@Resource(name = "postForwardCurrencyClose")
	private PostService purchasePost;

	@Resource(name = "tranJobForwardCurrencyClose")
	private TranJobService jobService;

	@Resource(name = "assetForwardCurrency")
	private SecurityAssetService assetService;
	
	@Resource(name = "jxForwardCurrencyCloseDtoMsg")
	private MsgService msgService;
	
	@Resource(name = "rcpyForwardClosePost")
	private PostToRCPYService postToRcpy;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.FORWORD_CURRENCY_CLOSE;
	}

	@Override
	public String tranDesc() {
		return "平仓交易";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.fxTrade;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fxForward_open_form";
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
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "forward_close_edit";
	}
}
