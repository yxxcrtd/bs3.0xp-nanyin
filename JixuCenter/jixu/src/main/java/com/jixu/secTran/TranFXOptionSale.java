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
 * @ClassName 		TramOptionBuy
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:45:36 	
 * @Description		[]  
 */


@Service("TranFXOptionSale")
public class TranFXOptionSale extends SecurityBasicService{
	
	@Resource(name="AssetFXOption")
	private SecurityAssetService assetFXOption;
	
	@Resource(name="postShortSale")
	private PostService salePost;
	
	@Resource(name="rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobShortSale")
	private TranJobService jobService;
	
	@Resource(name = "assembleTranFXOption")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.FXOption_Sale;
	}
	
	@Override
	public String tranDesc() {
		return "卖出期权";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public SecurityAssetService assetService() {
		return assetFXOption;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fxOption_sale_form";
	}
	
	@Override
	public TranJobService createJobService() {
		return jobService;
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
	public MsgService msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
