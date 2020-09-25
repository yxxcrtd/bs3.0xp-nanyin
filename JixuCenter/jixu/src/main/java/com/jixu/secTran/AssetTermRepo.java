package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetStock
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:20:27 	
 * @Description		[]  
 */

/**
 * 普通股
 * @author pan wei
 *
 */

@Service("assetTermRepo")
public class AssetTermRepo extends AssetSecurity{
	
	@Resource(name="assetJobTermRepo")
	private AssetJobService jobService;
	
	@Resource(name="assetMoneyMarket")
	private SecurityAssetService mmService;
	
	@Override
	public String assetType() {
		return AssetTypes.TERM_REPO;
	}
	
	@Override
	public String assetDesc() {
		return "定期回购";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/repo";
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
