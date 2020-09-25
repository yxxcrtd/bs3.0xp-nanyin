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


@Service("TranOptionStockExercise")
public class TranOptionStockExercise extends SecurityBasicService{
	
	@Resource(name="AssetOption")
	private SecurityAssetService assetService;
	
	@Resource(name="PostOption2Stock")
	private PostService postService;
	
	@Resource(name="RCPYBond2StockService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="TranJobOption2Stock")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranOptionExercise")
	private MsgService msgService;

	@Override
	public int tranType() {
		return TransTypes.Option_StockExercise;
	}
	
	@Override
	public String tranDesc() {
		return "期权行权";
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
		return "/forms/trade/tradeForms/option_exercise";
	}
	
	@Override
	public TranJobService createJobService() {
		return jobService;
	}
	
	@Override
	public PostService createPostService() {
		return postService;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		// TODO Auto-generated method stub
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
		return "option_exercise_edit";
	}
}
