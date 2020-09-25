package com.jixu.secTran;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description		[证券头寸期初]  
 */

@Service
public class TranSecContribution extends SecurityBasicService{

	@Autowired
	private PostService postSecContribution;
	
	@Autowired
	private MsgService secContribuationMsg;
	
	@Override
	public SecurityAssetService assetService() {
		return null;
	}
	
	@Override
	public int tranType() {
		return TransTypes.SecContribution;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/sec_contribution_form";
	}
	
	@Override
	public String tranDesc() {
		return "头寸初始化";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public PostService createPostService() {
		return postSecContribution;
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
		return secContribuationMsg;
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
