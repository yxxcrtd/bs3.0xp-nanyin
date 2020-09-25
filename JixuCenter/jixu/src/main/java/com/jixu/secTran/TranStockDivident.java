package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.StockDividendDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("TranStockDivident")
public class TranStockDivident extends SecurityBasicService{
	
	@Resource(name="jixuPostStockDividend")
	private PostService postService;

	@Resource(name="tranJobStockDividend")
	private TranJobService jobService;
	
	@Resource(name = "AssembleStockDividend")
	private MsgService<StockDividendDto> msgService;
	
	@Resource(name = "assetCommonStock")
	private SecurityAssetService assetService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.StockDividend;
	}
	
	@Override
	public String tradeFormURL() {
		return "";
	}
	
	@Override
	public String tranDesc() {
		return "股票股利";
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
	public MsgService<StockDividendDto> msgService() {
		return msgService;
	}
	
	@Override
	public boolean buySaleFlag() {
		return true;
	}

	@Override
	public String newTradeFormURL() {
		return "stock_dividends_edit";
	}
}
