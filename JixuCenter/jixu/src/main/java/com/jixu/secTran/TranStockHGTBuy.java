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

@Service("TranStockHGTBuy")
public class TranStockHGTBuy extends SecurityBasicService {

	@Resource(name = "slStockPurPostImp")
	private PostService purchasePost;

	@Resource(name = "RCPYHGTBuy")
	private PostToRCPYService postToRcpy;

	@Resource(name = "TranJobStockHGTBuy")
	private TranJobService jobService;

	@Resource(name = "assetCommonStock")
	private SecurityAssetService assetService;
	
	@Resource(name = "AssembleTranHGTStock")
	private MsgService msgService;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.HK_HGT_Buy;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/HK_connect_stock_all_form";
	}

	@Override
	public String tranDesc() {
		return "港股通买入";
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
		return "stock_HK_buy_edit";
	}
}
