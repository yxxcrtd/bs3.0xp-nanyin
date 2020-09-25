package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/** 
 * @ClassName 		TranTimeDepositBuy
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:30:52 	
 * @Description		[]  
 */
@Service
public class TranTimeDepositSale extends SecurityBasicService{
	
	@Resource(name="AssetTimeDeposit")
	private SecurityAssetService assetService;
	
	@Resource(name="TranJobTimeDepositSale")
	private TranJobService jobService;
	
	@Resource(name = "rcpySaleService")
	private PostToRCPYService postToRcpy;

	@Autowired
	private PostService postTimeDepositMaturity;
	
	@Resource(name = "AssembleTranTimeDepositSale")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.TimeDep_sale;
	}
	
	@Override
	public String tranDesc() {
		return "存款卖出";
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
		return "/forms/trade/tradeForms/deposit_withdraw_form";
	}
	
	@Override
	public PostService createPostService() {
		return postTimeDepositMaturity;
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
		// TODO Auto-generated method stub
		return null;
	}
}
