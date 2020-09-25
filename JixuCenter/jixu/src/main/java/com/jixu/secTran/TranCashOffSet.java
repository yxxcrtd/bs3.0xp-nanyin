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
 * @Description [股票交易]
 */

@Service("SecTranCashOff")
public class TranCashOffSet extends SecurityBasicService {

	@Resource(name = "rcpyCashOff")
	private PostToRCPYService postToRcpy;

	@Resource(name = "tranJobCashOff")
	private TranJobService jobService;

	@Resource(name = "assetCash")
	private SecurityAssetService assetService;
	
	@Resource(name="cashOffMsg")
	private MsgService msgService;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.CASHOFF;
	}

	@Override
	public String tranDesc() {
		return "现金冲销";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.cash;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/cashOffset_form";
	}

	@Override
	public PostService createPostService() {
		return null;
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
		return "cashOffset_edit";
	}
}
