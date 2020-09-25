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
 * @ClassName 		MMFundSubTran
 * @author  		XC 
 * @Date 			2017/4/30 	
 * @Description		[货币基金申购]  
 */

@Service("TranFundRed")
public class TranFundRed extends SecurityBasicService{

	@Resource(name="AssetCommonFund")
	private SecurityAssetService assetService;
	
	@Resource(name="slStockSalePostImp")
	private PostService salePost;
	
	@Resource(name="rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobStockSale")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranComFund")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.CommonFund_Red;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fund_all_form";
	}
	
	@Override
	public String tranDesc() {
		return "赎回|卖出";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public PostService createPostService() {
		return salePost;
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
		return "common_fund_sale_edit";
	}
}
