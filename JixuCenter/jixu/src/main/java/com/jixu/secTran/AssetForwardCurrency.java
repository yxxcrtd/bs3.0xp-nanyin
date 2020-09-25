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
 * 远期换汇
 * @author Wang Zhongyuan
 *
 */

@Service("assetForwardCurrency")
public class AssetForwardCurrency extends AssetSecurity{
	
	@Resource(name="assetJobForwardCurrency")
	private AssetJobService jobService;
	
	
	@Override
	public String assetType() {
		return AssetTypes.FORWARD_CURRENCY;
	}
	
	@Override
	public String assetDesc() {
		return "远期换汇";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/fx_spot";
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
