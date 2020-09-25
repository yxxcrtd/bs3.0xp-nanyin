package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetBond
 * @author  		Pan Wei 
 * @Date 			2017年5月31日 下午2:26:57 	
 * @Description		[]  
 */
@Service("AssetBond")
public class AssetBond extends AssetSecurity{

	@Resource(name="AssetJobBond")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.Bond;
	}
	
	@Override
	public String assetDesc() {
		return "固定收益";
	}
	
	@Override
	public String assetFormURL() {
		// TODO Auto-generated method stub
		return "/forms/trade/assetForms/bond";
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
		// TODO Auto-generated method stub
		return null;
	}
}
