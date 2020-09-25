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
public class AssetSwapFX extends AssetSecurity{

	@Resource(name="assetJobSwapFX")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.SWAP_FX;
	}
	
	@Override
	public String assetDesc() {
		return "外汇掉期";
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
