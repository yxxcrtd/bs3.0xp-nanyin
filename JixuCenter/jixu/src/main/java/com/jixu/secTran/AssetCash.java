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

@Service("assetCash")
public class AssetCash extends AssetSecurity{
	
	@Resource(name="assetJobCash")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.CASH;
	}
	
	@Override
	public String assetDesc() {
		return "现金";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/cash";
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		// TODO Auto-generated method stub
		return null;
	}
}
