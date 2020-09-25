package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.StockSplitDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("jixuTranStockSplit")
public class TranStockSplit extends SecurityBasicService{
	
	@Resource(name="jixuPostStockSplit")
	private PostService postService;

	@Resource(name="tranJobStockSplit")
	private TranJobService jobService;
	
	@Resource(name = "AssembleStockDividend")
	private MsgService<StockSplitDto> msgService;
	
	@Resource(name="assetCommonStock")
	private SecurityAssetService assetService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.STOCKSPLIT;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_stockdiv_form";
	}
	
	@Override
	public String tranDesc() {
		return "股票分割";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.corpAction;
	}

	@Override
	public PostService createPostService() {
		return postService;
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
	public MsgService<StockSplitDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_splits_edit";
	}
}
