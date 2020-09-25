package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.InterestSwapPayInterestDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [利率互换付息]
 */

@Service
public class TranSwapPayInterest extends SecurityBasicService {

	@Resource(name = "tranJobSwapPayInterest")
	private TranJobService jobService;

	@Resource(name = "assetSwap")
	private SecurityAssetService assetService;
	
	@Resource(name = "jxInterestSwapMaturityDtoMsg")
	private MsgService<InterestSwapPayInterestDto> msgService;
	
	@Resource(name = "rcpySwapPayInterest")
	private PostToRCPYService postToRcpy;
	
	@Resource(name = "postSwapPayInterest")
	private PostService post;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.SWAP_Pay_Interest;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_all_form";
	}

	@Override
	public String tranDesc() {
		return "利率互换付息";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public PostService createPostService() {
		return post;
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
	public MsgService<InterestSwapPayInterestDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "interest_swap_payInt_edit";
	}
}
