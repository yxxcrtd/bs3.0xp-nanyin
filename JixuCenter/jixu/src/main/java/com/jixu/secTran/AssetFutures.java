package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetFutures
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:10:30 	
 * @Description		[]  
 */

@Service("jixuAssetFutures")
public class AssetFutures extends AssetSecurity{
	
	@Resource(name="AssetJobFuture")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.FUTURES;
	}
	
	@Override
	public String assetDesc() {
		return "期货";
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/futures";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		if(operation.equalsIgnoreCase(MsgFields.update))
			return "smf_futures_edit";
		else
			return "smf_futures_new";
	}
}
