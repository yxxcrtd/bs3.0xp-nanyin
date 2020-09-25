/*
 * 文件名：FlowRiskFlowAssets001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowriskflowassets.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.dto.FlowRiskFlowAssetsDto;
import com.joyintech.service.risk.stresstestmanage.flowriskflowassets.service.FlowRiskFlowAssetsService;


/**
 * 流动性风险-流动性资产配置
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFlowAssetsController
 * @since 1.0
 */
@RestController("FlowRiskFlowAssets001Ctl_dtx")
@RequestMapping("/FlowRiskFlowAssets001")
public class FlowRiskFlowAssetsController {

	/**
	 * flowRiskFlowAssetsService
	 */
	@Autowired
	private FlowRiskFlowAssetsService flowRiskFlowAssetsService;
	
	/**
	 * 更新
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param dto
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
    @DataSource(DataSourceType.STRESSTEST)
	@PostMapping("update")
    public void update(@RequestBody FlowRiskFlowAssetsDto dto) throws Exception {
        flowRiskFlowAssetsService.update(dto);
    }
	/**
	 * 根据参数代码和参数值拼接sql
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getFloAssTypDetByCodVal")
    public List<FlowRiskFlowAssetsDto> getFloAssTypDetByCodVal(@RequestBody Map<String, Object> map) {
        return flowRiskFlowAssetsService.getFloAssTypDetByCodVal(map);
    }
    
	/**
	 * 得到一级流动性资产
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	 @GetMapping("getFloAssOne")
	 public List<FlowRiskFlowAssetsDto> getFloAssOne() {
        return flowRiskFlowAssetsService.getFloAssOne();
	 }
	
	/**
	 * 得到二级流动性资产
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	 @GetMapping("getFloAssTwo")
	 public List<FlowRiskFlowAssetsDto> getFloAssTwo() {
	     return flowRiskFlowAssetsService.getFloAssTwo();
	 }
	 
	 /**
	  * 获取资产二级和三级分类的树数据
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @return
	  * @see
	  * @since 1.0
	  */
	 @GetMapping("getFloAssTreeData")
     public List<FlowRiskFlowAssetsDto> getFloAssTreeData() {
         return flowRiskFlowAssetsService.getFloAssTreeData();
     }
	 
	 /**
	  * 初始化页面的备注信息
	  * <一句话功能简述>
	  * <功能详细描述>
	  * @return
	  * @see
	  * @since 1.0
	  */
	 @GetMapping("selectFlowAssOneMes")
	 public FlowRiskFlowAssetsDto selectFlowAssOneMes() {
	     return flowRiskFlowAssetsService.selectFlowAssOneMes();
	 }
	 
}
