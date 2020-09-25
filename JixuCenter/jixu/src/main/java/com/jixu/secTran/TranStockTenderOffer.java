package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.StockTranDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		TranStockTenderOffer
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("jixuTranStockTenderOffer")
public class TranStockTenderOffer extends SecurityBasicService{

	@Resource(name="slStockSalePostImp")
	private PostService salePost;
	
	@Resource(name="rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobStockSale")
	private TranJobService jobService;
	
	@Resource(name="assetCommonStock")
	private SecurityAssetService assetService;
	
	@Resource(name = "jixuStockTranDtoMsg")
	private MsgService<StockTranDto> msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.TENDER_OFFER;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/stock_all_form";
	}
	
	@Override
	public String tranDesc() {
		return "要约收购";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	/*@Override
	public MsgService createTranMsgAssemble() {
		return msgAssemble;
	}*/
	
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
	public MsgService<StockTranDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return false;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_tender_offer_edit";
	}
}
