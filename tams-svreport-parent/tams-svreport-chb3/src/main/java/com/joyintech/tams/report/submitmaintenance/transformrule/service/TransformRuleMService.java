/**  
 * @Project     tams-svreport-chb3  
 * @File        TransformRuleMService.java  
 * @Package     com.joyintech.tams.report.submitmaintenance.transformrule.service  
 * @Version     V1.0  
 * @Date        2019年12月31日 上午9:18:57  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.report.submitmaintenance.transformrule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;

/**  
 * @Description 变形转换规则  
 * @ClassName   TransformRuleMService  
 * @Date        2019年12月31日 上午9:18:57  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Service
public class TransformRuleMService extends CommonExportService<TransformRule001Dto>{
	
	/**
	 * 
	 * @Description 业务画面查询事件
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:55:17  
	 * @param @param param
	 * @param @return 参数  
	 * @return Page<TransformRule001Dto> 返回类型   
	 * @throws
	 */
	public Page<TransformRule001Dto> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
	       		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/searchPage",param,
	       		TransformRule001Dto.class);
	}

	/**
	 * 
	 * @Description 删除  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:56:07  
	 * @param @param deleteList 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	public void doDelete(List<TransformRule001Dto> deleteList){
		restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/doDelete", deleteList,
        		Integer.class);
	}
	
	/**
	 * 
	 * @Description 业务画面导出查询事件  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:56:51  
	 * @param @param map
	 * @param @return 参数  
	 * @return List<TransformRule001Dto> 返回类型   
	 * @throws
	 */
	public List<TransformRule001Dto> setDataList(Map<String, Object> map) {
		return restTemplate.postForList(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/setDataList",map,
        		TransformRule001Dto.class);
	}
	
	public TransformRule001Dto getEntity(Map<String, Object> map) {
		return restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/getEntity",map,
        		TransformRule001Dto.class);
	}
	
	/**
	 * 
	 * @Description 保存  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:57:28  
	 * @param @param dto 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	public void doSave(TransformRule001Dto dto) {
		restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/doSave", dto,
        		Integer.class);
	}
	
	/**
	 * 
	 * @Description 修改保存  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:58:10  
	 * @param @param dto 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	public void doEditSave(TransformRule001Dto dto) {
		restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/doEditSave", dto,
        		Integer.class);
	}
	
	/**
	 * 
	 * @Description 刷新待处理数据  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:58:27  
	 * @param  参数  
	 * @return void 返回类型   
	 * @throws
	 */
	public void excuteRefresh(){
		Map<String,Object> param = new HashMap<String,Object>();
		restTemplate.postForObject(
        		SvrServiceApplication.CHB3_SERVICE_NAME + "/TransformRule/excuteRefresh",param,
        		Integer.class);
	}
}
