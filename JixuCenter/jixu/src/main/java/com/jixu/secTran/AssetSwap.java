package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetBond
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:26:57 	
 * @Description		[]  
 */
@Service
public class AssetSwap extends AssetSecurity{

	@Resource(name="assetJobSwap")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.SWAP_INTEREST;
	}
	
	@Override
	public String assetDesc() {
		return "利率互换";
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/swap";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		return null;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		if(operation.equalsIgnoreCase(MsgFields.update))
			return "smf_swap_edit";
		else
			return "smf_swap_new";
	}
}
