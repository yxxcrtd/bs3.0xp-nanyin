/*
 * 文件名：PtlDvpFundAllocationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年3月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptldvpfundallocation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptldvpfundallocation.dto.PtlDvpFundAllocationDto;
import com.joyintech.service.portfolio.ptldvpfundallocation.service.PtlDvpFundAllocationService;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.invest.common.CommonResult;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * DVP资金调拨微服务
 *
 * @author etliu
 * @version 1.0 2019年3月29日
 * @see PtlDvpFundAllocationController
 * @since 1.0
 */
@RestController("ptlDvpFundAllocationCtl_dtx")
@RequestMapping("/PtlDvpFundAllocation")
public class PtlDvpFundAllocationController {

    /**
     * PtlDvpFundAllocationService
     */
    @Autowired
    private PtlDvpFundAllocationService ptlDvpFundAllocationService;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlDvpFundAllocationDto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return ptlDvpFundAllocationService.findPage(page, length, param);
    }

    /**
     * 查询收款账号
     * 
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getCounterAcctNo")
    public List<SelectOptionDto> getCounterAcctNo(@RequestBody Map<String, Object> map) {
        return ptlDvpFundAllocationService.getCounterAcctNo(map);
    }

    /**
     * 查询付款账号
     * 
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    @PostMapping("/getMyAcctNo")
    public List<SelectOptionDto> getMyAcctNo(@RequestBody Map<String, Object> map) {
        return ptlDvpFundAllocationService.getMyAcctNo(map);
    }
    
    /**
     * 查询交易编号(根据指令编号)
     * 
     * @param map 请求参数
     * @return 交易编号
     * @see
     * @since 1.0
     */
    @PostMapping("/getTradeId")
    public String getTradeId(@RequestBody Map<String, Object> map) {
        return ptlDvpFundAllocationService.getTradeId(map);
    }
    
    /**
     * 查询dvp指令信息
     *
     * @param map 参数（交易编号）
     * @return dvp指令信息
     * @see
     * @since 1.0
     */
    @PostMapping("/selectOrdersByBizCode")
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(@RequestBody Map<String, Object> map) {
        String bizCode = (String) map.get("bizCode");
        return ptlDvpFundAllocationService.selectOrdersByBizCode(bizCode);
    }

    /**
     * 代办处理
     * 
     * @param entity 资金划拨实体类
     * @return 处理结果
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DTXTransaction
    @RequestMapping("/doComplete")
    public int doComplete(@RequestBody PtlDvpFundAllocationDto entity) {
    	String operationType = entity.getOperateType();
    	int result = -1;
    	if (StringUtil.equals(operationType, OperationType.NEW.name())) {
    		result = ptlDvpFundAllocationService.insertData(entity);
		} else {
			result = ptlDvpFundAllocationService.revokeData(entity);
		}
    	ptlDvpFundAllocationService.doDelete(entity.getWorkflowId());
    	return result;
    }

    /**
     * 更新
     * 
     * @param entity 资金划拨实体类
     * @return 处理结果
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DTXTransaction
    @RequestMapping("/doUpdate")
    public int doUpdate(@RequestBody PtlDvpFundAllocationDto entity) {
    	return ptlDvpFundAllocationService.doUpdate(entity);
    }
    
    /**
     * 插入
     * 
     * @param entity 资金划拨实体类
     * @return 处理结果
     * @see
     * @since 1.0
     */
//    @Transactional
//    @DTXTransaction
    @RequestMapping("/doInsert")
    public int doInsert(@RequestBody PtlDvpFundAllocationDto entity) {
    	return ptlDvpFundAllocationService.doInsert(entity);
    }
    
    /**
     * 删除
     * 
     * @param workflowId 工作流ID
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/doDelete")
    public CommonResult doDelete(@RequestBody String workflowId) {
    	return ptlDvpFundAllocationService.doDelete(workflowId);
    }

	/**
	 * 从3025接口表查询余额
	 * 
	 * @param paramMap 参数map
	 * @return 余额
	 */
    @PostMapping("/getDvpAmount")
	public String getDvpAmount(@RequestBody Map<String, Object> paramMap){
		return ptlDvpFundAllocationService.getAmount(paramMap);
	}
}
