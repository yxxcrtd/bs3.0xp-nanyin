package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetCommonFund
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:24:03 	
 * @Description		[]  
 */


@Service("AssetCommonFund")
public class AssetCommonFund extends AssetSecurity{
	
	@Resource(name="AssetJobCommonFund")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.COMMON_FUND;
	}
	
	@Override
	public String assetDesc() {
		return "普通基金";
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/common_fund";
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
			return "smf_common_fund_edit";
		else
			return "smf_common_fund_new";
	}
}
