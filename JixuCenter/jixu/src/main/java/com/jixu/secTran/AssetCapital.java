package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetCapital
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:20:27 	
 * @Description		[]  
 */


@Service("assetCapital")
public class AssetCapital extends AssetSecurity{
	
	@Resource(name="assetJobCapital")
	private AssetJobService jobService;
	
	
	@Override
	public String assetType() {
		return AssetTypes.CAPITAL;
	}
	
	@Override
	public String assetDesc() {
		return "申赎";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/capital";
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
