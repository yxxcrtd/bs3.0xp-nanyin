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
 * @ClassName 		StockTran
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[银行资管支付相关费用]  
 */

@Service
public class TranShareBankZGDiv2Share extends SecurityBasicService{
	
	@Resource(name="assetBankZiGuan")
	private SecurityAssetService assetService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.ShareBankZGDiv2Share;
	}
	
	@Override
	public String tranDesc() {
		return "批次红利转份额";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return null;
	}

	@Override
	public PostService createPostService() {
		return null;
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
		return null;
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
