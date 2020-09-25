package com.jixu.secTran;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		AssetBankZiGuan
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:20:27 	
 * @Description		[]  
 */

@Service
public class AssetBankZiGuan extends AssetSecurity{
	
	@Autowired
	private AssetJobService assetJobShareBankZG;
	
	@Override
	public String assetType() {
		return AssetTypes.BankZiGuan;
	}
	
	@Override
	public String assetDesc() {
		return "银行资管";
	}
	
	@Override
	public AssetJobService createJobService() {
		return assetJobShareBankZG;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/fund_product";
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		return null;
	}

	@Override
	public String newAssetFormURL(String operation) {
		// TODO Auto-generated method stub
		return null;
	}
}
