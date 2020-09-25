package com.jixu.secTran;



import javax.annotation.Resource;

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

//@Service("TranFuturesLongContribution")
public class TranFuturesLongContribution extends SecurityBasicService{

	@Resource(name="jixuAssetFutures")
	private SecurityAssetService assetService;
	
	@Resource(name="PostFutureLongBuy")
	private PostService purchasePost;
	
	@Resource(name = "AssembleTranFutureBuy")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Futures_LongtBuy_Contri;
	}
	
	@Override
	public String tranDesc() {
		return "开仓买入期初";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/futures_buy_form";
	}
	
	@Override
	public PostService createPostService() {
		return purchasePost;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		return null;
	}
	
	@Override
	public TranJobService createJobService() {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}
