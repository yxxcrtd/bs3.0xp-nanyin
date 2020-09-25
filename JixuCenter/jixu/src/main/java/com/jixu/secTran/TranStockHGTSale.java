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

@Service("TranStockHGTSale")
public class TranStockHGTSale extends SecurityBasicService {

	@Resource(name = "slStockSalePostImp")
	private PostService purchasePost;

	@Resource(name = "RCPYHGTSale")
	private PostToRCPYService postToRcpy;

	@Resource(name = "TranJobStockHGTSale")
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
		return TransTypes.HK_HGT_Sale;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/HK_connect_stock_all_form";
	}

	@Override
	public String tranDesc() {
		return "港股通卖出";
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
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_HK_sale_edit";
	}
}
