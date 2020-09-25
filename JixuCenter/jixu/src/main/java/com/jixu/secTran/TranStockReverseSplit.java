package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.StockResverseSplitDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		TranStockReverseSplit
 * @author  		Pan Wei 
 * @Date 			 	
 * @Description		[股票合并]  
 */

@Service("jixuTranStockReverseSplit")
public class TranStockReverseSplit extends SecurityBasicService{
	
	@Resource(name="jixuPostStockReverseSplit")
	private PostService postService;

	@Resource(name="tranJobStockReverseSplit")
	private TranJobService jobService;
	
	@Resource(name = "AssembleStockDividend")
	private MsgService<StockResverseSplitDto> msgService;
	
	@Resource(name="assetCommonStock")
	private SecurityAssetService assetService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.REVSTOCKSPLIT;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_stockdiv_form";
	}
	
	@Override
	public String tranDesc() {
		return "股票合并";
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
	public MsgService<StockResverseSplitDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_resversesplits_edit";
	}
}
