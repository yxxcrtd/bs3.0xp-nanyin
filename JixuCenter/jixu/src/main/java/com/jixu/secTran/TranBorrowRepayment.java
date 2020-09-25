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
 * @ClassName 		TranBorrowRepayment
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:30:52 	
 * @Description		[]  
 */
@Service("TranBorrowRepayment")
public class TranBorrowRepayment extends SecurityBasicService{
	
	@Resource(name="assetBorrow")
	private SecurityAssetService assetService;
	
	@Resource(name="TranJobBorrowReturn")
	private TranJobService jobService;
	
	@Resource(name = "rcpySaleService")
	private PostToRCPYService postToRcpy;

	@Resource(name="repoLendMaturityPost")
	private PostService postService;
	
	@Resource(name = "AssembleTranBorrowRepayment")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.Borrow_Repayment;
	}
	
	@Override
	public String tranDesc() {
		return "融资偿还";
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
		return "/forms/trade/tradeForms/borrow_repayment_form";
	}
	
	@Override
	public PostService createPostService() {
		return postService;
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
