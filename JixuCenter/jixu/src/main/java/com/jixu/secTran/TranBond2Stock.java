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
 * @Description		[股票交易]  
 */

@Service("TranConvertibleBond2Stock")
public class TranBond2Stock extends SecurityBasicService{

	@Resource(name="AssetBond")
	private SecurityAssetService assetService;
	
	@Autowired
	private PostService postBond2Stock;
	
	@Resource(name="RCPYBond2StockService")
	private PostToRCPYService rcpyB2s;
	
	@Resource(name="TranJobBond2Stock")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranBond2Stock")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Bond2Stock;
	}
	
	@Override
	public String tranDesc() {
		return "债转股";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/bond_to_stock_form";
	}
	
	@Override
	public PostService createPostService() {
		return postBond2Stock;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		return rcpyB2s;
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
		return true;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
