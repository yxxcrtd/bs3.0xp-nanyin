package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.job.service.AssetJobService;

/**
 * @ClassName AssetStockOption
 * @author Pan Wei
 * @Date 2017年5月31日 下午2:07:26
 * @Description []
 */
@Service("AssetOption")
public class AssetOption extends AssetSecurity{

	@Resource(name="AssetJobOption")
	private AssetJobService jobService;
	
	@Resource(name="assetCommonStock")
	private SecurityAssetService mmService;
	@Override
	public String assetType() {
		return AssetTypes.OPTION;
	}

	@Override
	public String assetDesc() {
		return "期权";
	}

	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/option";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		return mmService.assetCreateSMFMsg(action, smfFields);
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		if(operation.equalsIgnoreCase(MsgFields.update))
			return "smf_option_edit";
		else
			return "smf_option_new";
	}
}
