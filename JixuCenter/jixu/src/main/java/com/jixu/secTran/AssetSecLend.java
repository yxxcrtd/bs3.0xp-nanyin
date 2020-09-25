package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetBorrow
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:20:27 	
 * @Description		[]  
 */

/**
 * 融券协议
 * @author pan wei
 *
 */

@Service("assetSecLend")
public class AssetSecLend extends AssetSecurity{
	
	@Resource(name="AssetJobSecLend")
	private AssetJobService jobService;
	
	@Resource(name="assetMoneyMarket")
	private SecurityAssetService mmService;
	
	@Override
	public String assetType() {
		return AssetTypes.SEC_LENDING;
	}
	
	@Override
	public String assetDesc() {
		return "融券协议";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/sec_lend";
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		return mmService.assetCreateSMFMsg(action, smfFields);
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		// TODO Auto-generated method stub
		return null;
	}
}
