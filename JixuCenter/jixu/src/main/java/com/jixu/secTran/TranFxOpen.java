package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.FXOpenDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [即期换汇开仓交易]
 */

@Service("SecTranFXOpen")
public class TranFxOpen extends SecurityBasicService {

	@Resource(name = "fxTranPost")
	private PostService purchasePost;

	@Resource(name = "rcpyFXPost")
	private PostToRCPYService postToRcpy;

	@Resource(name = "tranJobFxOpen")
	private TranJobService jobService;

	@Resource(name = "assetSpotCurrency")
	private SecurityAssetService assetService;
	
	@Resource(name = "jxSwapFXOpenDtoMsg")
	private MsgService<FXOpenDto> msgService;

	@Override
	public SecurityAssetService assetService() {
		// TODO Auto-generated method stub
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.FX_OPENING;
	}

	@Override
	public String tranDesc() {
		return "即期换汇";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.fxTrade;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fxSpot_open_form";
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
	public MsgService<FXOpenDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "spot_edit";
	}
}
