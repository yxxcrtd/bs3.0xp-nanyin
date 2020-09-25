/*
 * 文件名：ValOptInfo001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valoptinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valoptinfo.dto.ValOptInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.valoptinfo.service.ValOptInfo001Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 场外期权标的
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOptInfo001Contoller
 * @since 1.0
 */
@RestController(value="serviceValOptInfo001Contoller")
@RequestMapping("/ValOptInfo001")
public class ValOptInfo001Contoller extends BaseController {
    
    /**
     * ValOptInfo001Service
     */
    @Autowired
    private ValOptInfo001Service valOptInfo001Service;
    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValOptInfo001Dto> findPage(@RequestBody Map<String, Object> params,
    		@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valOptInfo001Service.findPage(page,length,params);
    }
    
    /**
     * 
     * 数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findByTargetId")
    public List<ValOptInfo001Dto> findByTargetId(@RequestBody Map<String, Object> params) throws Exception{
        return valOptInfo001Service.findByTargetId(params);
    }
    
    /**
     * 交易编号联想框
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/findFinprodId")
    public List<SuggestDto> findFinprodId(@RequestBody Map<String,Object> map) {
        return valOptInfo001Service.findFinprodId(map);
    }
    
	/**
	 * 新建
	 * 
	 * @param dto
	 * @throws Exception
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("insert")
	public void insert(@RequestBody ValOptInfo001Dto dto) throws Exception {
		valOptInfo001Service.doComplete(dto, OperationType.NEW);
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("update")
	public void update(@RequestBody ValOptInfo001Dto dto) throws Exception {
		valOptInfo001Service.doComplete(dto, OperationType.MODIFY);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("delete")
	public void delete(@RequestBody ValOptInfo001Dto dto) throws Exception {
		valOptInfo001Service.doComplete(dto, OperationType.DELETE);
	}
    
}
