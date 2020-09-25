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
 * @ClassName 		TranBondReverse
 * @author  		XC 
 * @Date 			2018年4月8日 下午8:21:17 	
 * @Description		[资产减值回转]  
 */

@Service("TranBondReverse")
public class TranBondReverse extends SecurityBasicService{

	@Resource(name="AssetBond")
	private SecurityAssetService assetService;
	
	@Autowired
	private PostService postBondReverse;
	
	@Resource(name="rcpyPurchaseService")
	private PostToRCPYService postToRcpy;
	
	@Autowired
	private TranJobService tranJobBondReverse;
	
	@Resource(name = "AssembleTranWriteOff")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Bond_Reverse;
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tranDesc() {
		return "资产减值回转";
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/bond_reverse_form";
	}
	
	@Override
	public PostService createPostService() {
		return postBondReverse;
	}
	
	@Override
	public PostToRCPYService createPostToRCPYService() {
		return postToRcpy;
	}
	
	@Override
	public TranJobService createJobService() {
		return tranJobBondReverse;
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
