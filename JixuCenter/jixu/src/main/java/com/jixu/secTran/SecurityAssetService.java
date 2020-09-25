package com.jixu.secTran;

import com.jixu.job.service.AssetJobService;

/** 
 * @ClassName 		SecurityAssetService
 * @author  		Pan Wei 
 * @Date 			2017年5月15日 下午11:20:29 	
 * @Description		[]  
 */

public interface SecurityAssetService {
	/**
	 * 证券类型
	 * @return
	 */
	public String assetType();
	
	/**
	 * 该证券类型的描述
	 * @return
	 */
	public String assetDesc();
	
	/**
	 * 该证券类型的日记账
	 * @return
	 */
	public AssetJobService createJobService();
	
	public String assetFormURL();
	
	public String newAssetFormURL(String operation); 
	
	/**
	 * 创建
	 * @return
	 */
	public String assetCreateSMFMsg(String action, String[] smfFields);
}
