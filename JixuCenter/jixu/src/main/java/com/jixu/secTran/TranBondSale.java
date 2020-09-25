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
 * @ClassName 		TranBondSale
 * @author  		Pan Wei 
 * @Date 			2017年1月10日 下午8:21:17 	
 * @Description		[债券-还本付息]  
 */

@Service("TranBondSale")
public class TranBondSale extends SecurityBasicService{

	@Resource(name="AssetBond")
	private SecurityAssetService assetService;
	
	@Resource(name="slStockSalePostImp")
	private PostService salePost;
	
	@Resource(name="rcpySaleService")
	private PostToRCPYService postToRcpy;
	
	@Resource(name="TranJobBondSale")
	private TranJobService jobService;
	
	@Resource(name = "AssembleTranBond")
	private MsgService msgService;
	
	@Override
	public SecurityAssetService assetService() {
		return assetService;
	}
	
	@Override
	public int tranType() {
		return TransTypes.Bond_Sale;
	}
	
	@Override
	public String tranDesc() {
		return "卖出债券";
	}
	
	@Override
	public int workFlowSteps() {
		return WorkFlowSteps.tran;
	}
	
	@Override
	public String tradeFormURL() {
		return "/forms/trade/tradeForms/bond_sale_form";
	}
	
	@Override
	public PostService createPostService() {
		return salePost;
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
