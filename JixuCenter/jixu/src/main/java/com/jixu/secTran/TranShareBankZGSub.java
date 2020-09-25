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
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[银行资管行业，子产品申购汇总到一个总产品。子产品汇总钱到总产品，计算收益，并增加总产品的份额]  
 */

@Service
public class TranShareBankZGSub extends SecurityBasicService{
	
	@Autowired
	private PostService postShareBankZG;
	
	@Resource(name="rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="tranJobShareBankZG")
	private TranJobService jobService;
	
	@Resource(name="assetBankZiGuan")
	private SecurityAssetService assetService;
	
	@Autowired
	private MsgService tranBankZiGuanBorrowMsg;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.ShareBankZG;
	}
	
	@Override
	public String tranDesc() {
		return "批次申购";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/fundProduct_sub_form";
	}

	@Override
	public PostService createPostService() {
		return postShareBankZG;
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
		return tranBankZiGuanBorrowMsg;
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
