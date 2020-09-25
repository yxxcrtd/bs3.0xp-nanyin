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

@Service("TranFuturesLongSale")
public class TranFuturesLongSale extends SecurityBasicService{

	@Resource(name="jixuAssetFutures")
	private SecurityAssetService assetService;
	
	@Resource(name="PostFutureLongSale")
	private PostService postservice;
	
	@Resource(name="TranJobFutureLongSale")
	private TranJobService jobService;
	
	@Resource(name = "jixuFutureTranCloseDtoMsg")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Futures_LongSale;
	}
	
	@Override
	public String tranDesc() {
		return "平仓卖出";
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
		return "close_sale_edit";
	}
}
