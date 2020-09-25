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
 * @ClassName 		TranSecReturn
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:30:52 	
 * @Description		[]  
 */
@Service("TranSecReturn")
public class TranSecReturn extends SecurityBasicService{
	
	@Resource(name="assetSecLend")
	private SecurityAssetService assetService;
	
	@Resource(name="TranJobSecLendReturn")
	private TranJobService jobService;
	
	@Resource(name="RCPYSecLendReturn")
	private PostToRCPYService postToRcpy;
	
	@Autowired
	private PostService postSecLendReturn;
	
	@Resource(name = "AssembleTranSecLendReturn")
	private MsgService msgService;
	
	@Override
	public int tranType() {
		return TransTypes.Sec_Return;
	}
	
	@Override
	public String tranDesc() {
		return "融券偿还";
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
		return "/forms/trade/tradeForms/sec_return_form";
	}
	
	@Override
	public PostService createPostService() {
		return postSecLendReturn;
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
