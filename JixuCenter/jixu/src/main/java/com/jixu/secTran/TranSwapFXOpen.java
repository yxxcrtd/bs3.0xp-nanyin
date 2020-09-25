package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.SwapFXOpenDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [利率互换开仓]
 */

@Service
public class TranSwapFXOpen extends SecurityBasicService {

	//直接调用利率互换的post
	@Resource(name = "postSwapFXOpen")
	private PostService post;

	@Resource(name = "tranJobSwapFXOpen")
	private TranJobService jobService;

	@Resource(name = "assetSwapFX")
	private SecurityAssetService assetService;
	
	@Resource(name = "jxSwapFXOpenDtoMsg")
	private MsgService<SwapFXOpenDto> msgService;
	
	@Resource(name = "rcpySwapFXOpen")
	private PostToRCPYService postToRcpy;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.SWAP_FX_OPEN;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/swap_open_form";
	}

	@Override
	public String tranDesc() {
		return "外汇掉期-开仓";
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
	public MsgService<SwapFXOpenDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "swap_fx_open_edit";
	}
}
