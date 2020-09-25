package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.InterestSwapOpenDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [利率互换开仓]
 */

@Service
public class TranSwapInterestOpen extends SecurityBasicService {

	@Resource(name = "postSwapInterestOpen")
	private PostService post;

	@Resource(name = "tranJobSwapInterestOpen")
	private TranJobService jobService;

	@Resource(name = "assetSwap")
	private SecurityAssetService assetService;
	
	@Resource(name = "jxInterestSwapOpenDtoMsg")
	private MsgService<InterestSwapOpenDto> msgService;
	
	@Resource(name = "rcpySwapOpen")
	private PostToRCPYService postToRcpy;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.SWAP_OPEN_Interest;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/swap_open_form";
	}

	@Override
	public String tranDesc() {
		return "利率互换-开仓";
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
	public MsgService<InterestSwapOpenDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "interest_swap_open_edit";
	}
}
