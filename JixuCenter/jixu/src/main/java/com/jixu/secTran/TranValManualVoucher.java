package com.jixu.secTran;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.TransTypes;
import com.jixu.common.type.WorkFlowSteps;
import com.jixu.framework.msg.MsgService;
import com.jixu.job.service.TranJobService;
import com.jixu.msg.dto.ValManualVoucherDto;
import com.jixu.post.service.PostService;
import com.jixu.post.service.PostToRCPYService;

/**
 * @ClassName TranSecPositionUpdateDtoMsg
 * @author Pan Wei
 * @Date 2019年12月27日 下午11:10:02
 * @Description [证券头寸调整]
 */

@Service
public class TranValManualVoucher extends SecurityBasicService {

	@Autowired
	private PostService postValManualVoucher;

	@Resource(name = "TranJobCostUpdate")
	private TranJobService jobService;
	
	@Resource(name = "jxTranValManualVoucherDtoMsg")
	private MsgService<ValManualVoucherDto> msgService;

	@Override
	public SecurityAssetService assetService() {
		return null;
	}

	@Override
	public int tranType() {
		return TransTypes.ValMannulPositionUpdate;
	}

	@Override
	public String tradeFormURL() {
		return "";
	}

	@Override
	public String tranDesc() {
		return "手工凭证调整头寸";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}

	@Override
	public PostService createPostService() {
		return postValManualVoucher;
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
	public MsgService<ValManualVoucherDto> msgService() {
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
