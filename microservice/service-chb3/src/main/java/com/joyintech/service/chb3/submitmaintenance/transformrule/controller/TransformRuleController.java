/**  
 * @Project     service-chb3  
 * @File        TransformRuleController.java  
 * @Package     com.joyintech.service.chb3.submitmaintenance.transformrule.controller  
 * @Version     V1.0  
 * @Date        2019年12月31日 上午9:27:29  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.service.chb3.submitmaintenance.transformrule.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.queryanalysis.prosharecheck.dto.ProShareCheck001Dto;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRule001Service;
import com.joyintech.tams.report.submitmaintenance.transformrule.service.TransformRule002Service;

/**  
 * @Description 变形转换规则微服务控制层  
 * @ClassName   TransformRuleController  
 * @Date        2019年12月31日 上午9:27:29  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@RestController
@RequestMapping("/TransformRule")
public class TransformRuleController {

	@Autowired
	private TransformRule001Service transformRule001Service;

	@Autowired
	private TransformRule002Service transformRule002Service;

	/**
	 * 
	 * @Description 页面查询  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:59:14  
	 * @param @param param
	 * @param @return 参数  
	 * @return Page<TransformRule001Dto> 返回类型   
	 * @throws
	 */
	@PostMapping("searchPage/{page}/{length}")
	public Page<TransformRule001Dto> searchPage(@RequestBody Map<String, Object> param) {
		Page<TransformRule001Dto> page = transformRule001Service.searchPage(param);
		return page;
	}

	/**
	 * 
	 * @Description 删除  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:59:25  
	 * @param @param list 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	@PostMapping("doDelete")
	public void doDelete(@RequestBody List<TransformRule001Dto> list) {
		transformRule001Service.doDelete(list);
	}

	/**
	 * 
	 * @Description 导出查询  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:59:34  
	 * @param @param param
	 * @param @return 参数  
	 * @return List<TransformRule001Dto> 返回类型   
	 * @throws
	 */
	@PostMapping("setDataList")
	public List<TransformRule001Dto> setDataList(@RequestBody Map<String, Object> param) {
		return transformRule001Service.setDataList(param);
	}
	
	@PostMapping("getEntity")
	public TransformRule001Dto getEntity(@RequestBody Map<String, Object> map) {
		return transformRule002Service.getEntity(map);
	}
	
	/**
	 * 
	 * @Description 保存事件  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:59:49  
	 * @param @param dto 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	@PostMapping("doSave")
	public void doSave(@RequestBody TransformRule001Dto dto) {
		transformRule002Service.doSave(dto);
	}
	
	/**
	 * 
	 * @Description 修改保存  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午9:59:57  
	 * @param @param dto 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	@PostMapping("doEditSave")
	public void doEditSave(@RequestBody TransformRule001Dto dto) {
		transformRule002Service.doEditSave(dto);
	}
	
	/**
	 * 
	 * @Description 刷新待处理数据  
	 * @Author      高健  
	 * @Date        2019年12月31日 上午10:00:05  
	 * @param @param map 参数  
	 * @return void 返回类型   
	 * @throws
	 */
	@PostMapping("excuteRefresh")
	public void excuteRefresh(@RequestBody Map<String, Object> map) {
		transformRule002Service.excuteRefresh();
	}
}
