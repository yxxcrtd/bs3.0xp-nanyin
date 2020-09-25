package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.StockTranDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [股票交易]
 */

@Service("SecTranStockPurchase")
public class TranStockPurchase extends SecurityBasicService {

	@Resource(name = "slStockPurPostImp")
	private PostService purchasePost;

	@Resource(name = "rcpyPurchaseService")
	private PostToRCPYService postToRcpy;

	@Resource(name = "tranJobStockPurchase")
	private TranJobService jobService;

	@Resource(name = "assetCommonStock")
	private SecurityAssetService assetService;
	
	@Resource(name = "jixuStockTranDtoMsg")
	private MsgService<StockTranDto> msgService;

	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}

	@Override
	public int tranType() {
		return TransTypes.PURCHASE;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_all_form";
	}

	@Override
	public String tranDesc() {
		return "股票买入";
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
	public MsgService<StockTranDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_buy_edit";
	}
}
