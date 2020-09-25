package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 基础利率
 * @author pan wei
 *
 */

@Service
public class AssetBasePoint extends AssetSecurity{
	@Resource(name="assetMoneyMarket")
	private SecurityAssetService mmService;
	
	@Autowired
	private AssetJobService assetJobBsePoint;
	
	@Override
	public String assetType() {
		return AssetTypes.BasePoint;
	}
	
	@Override
	public String assetDesc() {
		return "基准指数";
	}
	
	@Override
	public AssetJobService createJobService() {
		return assetJobBsePoint;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/basePoint_fund";
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		return mmService.assetCreateSMFMsg(action, smfFields);
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		// TODO Auto-generated method stub
		return null;
	}
}
