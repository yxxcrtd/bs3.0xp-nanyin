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
 * @ClassName 		TranBondWriteOffPrepare
 * @author  		XC 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[股票交易]  
 */

@Service("TranBondWriteOffPrepare")
public class TranBondWriteOffPrepare extends SecurityBasicService{

	@Resource(name="AssetBond")
	private SecurityAssetService assetService;
	
	@Resource(name="PostBondWriteOffPrepare")
	private PostService purchasePost;
	
	@Resource(name="TranJobBondWriteOffPrepare")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranWriteOff")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Bond_WriteOff_Prepare;
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tranDesc() {
		return "预提减值准备";
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/bond_writeOff_form";
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
