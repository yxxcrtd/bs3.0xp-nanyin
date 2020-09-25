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
 * @ClassName 		TranOptionOpenSale
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:45:36 	
 * @Description		[]  
 */


@Service
public class TranOptionOpenSale extends SecurityBasicService{
	
	@Resource(name="AssetOption")
	private SecurityAssetService assetService;
	
	@Resource(name="slStockPurPostImp")
	private PostService purchasePost;
	
	@Resource(name="rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobStockPurchase")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranOption")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.Option_OpenSale;
	}
	
	@Override
	public String tranDesc() {
		return "开仓卖出";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/option_buysell_form";
	}
	
	@Override
	public TranJobService createJobService() {
		return jobService;
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
	public MsgService msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "option_open_sale_edit";
	}
}
