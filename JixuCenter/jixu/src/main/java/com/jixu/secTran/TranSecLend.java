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
 * @ClassName TranTimeDepositBuy
 * @author Pan Wei
 * @Date 2017年5月31日 下午2:30:52
 * @Description [目前大多模仿repo,逆回购]
 */
@Service("TranSecLend")
public class TranSecLend extends SecurityBasicService {

	@Resource(name = "assetSecLend")
	private SecurityAssetService assetService;

	@Autowired
	private PostService postSecLend;
	
	@Resource(name = "rcpySaleService")
	private PostToRCPYService postToRcpy;

	@Resource(name = "TranJobSecLend")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranSecLend")
	private MsgService msgService;

	@Override
	public int tranType() {
		return TransTypes.Sec_Lend;
	}

	@Override
	public String tranDesc() {
		return "融券交易";
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
		return "/forms/trade/tradeForms/sec_lend_form";
	}

	@Override
	public PostService createPostService() {
		return postSecLend;
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
		return true;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
