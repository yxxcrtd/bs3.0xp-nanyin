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
 * @ClassName StockTran
 * @author Pan Wei
 * @Date 2017年1月10日 下午8:21:17
 * @Description [成本调整]
 */

@Service
public class TranCostUpdate extends SecurityBasicService {

	@Autowired
	private PostService postCostUpdate;

	@Resource(name = "TranJobCostUpdate")
	private TranJobService jobService;
	
	@Resource(name = "costUpdateTranMsg")
	private MsgService msgService;

	@Override
	public SecurityAssetService assetService() {
		return null;
	}

	@Override
	public int tranType() {
		return TransTypes.CostUpdate;
	}

	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/all_costUpdate_form";
	}

	@Override
	public String tranDesc() {
		return "成本调整";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public PostService createPostService() {
		return postCostUpdate;
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
		return true;
	}

	@Override
	public String newTradeFormURL() {
		// TODO Auto-generated method stub
		return null;
	}
}
