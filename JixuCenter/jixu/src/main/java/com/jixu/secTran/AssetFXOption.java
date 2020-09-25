package com.jixu.secTran;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service("AssetFXOption")
public class AssetFXOption extends AssetSecurity{

	@Autowired
	private AssetJobService assetJobFXOption;
	
	@Override
	public String assetType() {
		return AssetTypes.FXOption;
	}
	
	@Override
	public String assetDesc() {
		return "货币期权";
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/fxOption";
	}
	
	@Override
	public AssetJobService createJobService() {
		return assetJobFXOption;
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		if(operation.equalsIgnoreCase(MsgFields.update))
			return "smf_FXoption_edit";
		else
			return "smf_FXoption_new";
	}
}
