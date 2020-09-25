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


@Service("TranFXOptionAbandonExercise")
public class TranFXOptionAbandonExercise extends SecurityBasicService{
	
	@Resource(name="AssetFXOption")
	private SecurityAssetService assetFXOption;
	
	@Resource(name = "postFXOAbandonExercise")
	private PostService purchasePost;

	@Resource(name = "tranJobFXOAbandonExercise")
	private TranJobService jobService;
	
	@Resource(name = "assembleTranFXOAbandonExercise")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.FXOption_AbandonExercise;
	}
	
	@Override
	public String tranDesc() {
		return "放弃行权";
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
		return "/forms/trade/tradeForms/fxOption_abondonExcercise_form";
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
		return null;
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
