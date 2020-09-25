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
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("TranStockCashDivident")
public class TranStockCashDivident extends SecurityBasicService{
	
	@Resource(name="tranJobStockCashDividend")
	private TranJobService jobService;
	
	@Resource(name="RCPYCashDividendPost")
	private PostToRCPYService postToRcpy;
	
	@Resource(name = "AssembleStockCashDiv")
	private MsgService msgService;
	
	@Resource(name = "assetCommonStock")
	private SecurityAssetService assetService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.CashDividend;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_cashdiv_form";
	}
	
	@Override
	public String tranDesc() {
		return "股票现金分红";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.corpAction;
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
		return "stock_cashDividends_edit";
	}
}
