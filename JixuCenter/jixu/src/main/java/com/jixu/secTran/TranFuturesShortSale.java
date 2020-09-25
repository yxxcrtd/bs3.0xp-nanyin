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

@Service("TranFuturesShortSale")
public class TranFuturesShortSale extends SecurityBasicService{

	@Resource(name="jixuAssetFutures")
	private SecurityAssetService assetService;
	
	@Resource(name="PostFutureShortSale")
	private PostService postservice;
	
	@Resource(name="TranJobFutureShortSale")
	private TranJobService jobService;
	
	@Resource(name = "jixuFutureTranCloseDtoMsg")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Futures_ShortSale;
	}
	
	@Override
	public String tranDesc() {
		return "平仓买入";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/futures_sale_form";
	}
	
	@Override
	public PostService createPostService() {
		return postservice;
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
	public MsgService msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "close_buy_edit";
	}
}
