/*
 * 文件名：ValSubjectBalanceQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook001Service;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.dto.ValSubjectBalanceQuery001Dto;
import com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.service.ValSubjectBalanceQuery001Service;
import com.joyintech.tams.valuation.common.service.BaseCommonService;
/**
 * 
 * 科目余额查询
 * @author wanggengyun
 * @version 1.0 2018年5月16日
 * @see ValSubjectBalanceQuery001Contoller
 * @since 1.0
 */
@RestController(value="serviceValSubjectBalanceQuery001Contoller")
@RequestMapping("/ValSubjectBalanceQuery001")
public class ValSubjectBalanceQuery001Contoller extends BaseController {
    
    /**
     * ValSubjectBalanceQuery001Service
     */
    @Autowired
    private ValSubjectBalanceQuery001Service valSubjectBalanceQuery001Service;
    
    @Autowired
    private ValAssetBook001Service valAssetBook001Service;
    
    @Autowired
    private BaseCommonService baseCommonService;
    

    
    /**
     * 
     * 分页数据查询
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValSubjectBalanceQuery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valSubjectBalanceQuery001Service.findPage(page, length, param);
    }
    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("getBooksetId")
    public List<SelectOptionDto> getBooksetId() {
        return valSubjectBalanceQuery001Service.getBooksetId();
    }
    
    
    /**
     * 所属科目下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @PostMapping("/getSubjectNo/{page}/{length}")
    public Page<SelectOptionDto> getSubjectNo(@RequestBody Map<String, Object> pageParam,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return valSubjectBalanceQuery001Service.queryValBokSubject(page,length,pageParam);
    }
    
    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
	@PostMapping("/getAllList")
    public List<ValSubjectBalanceQuery001Dto> getAllList(@RequestBody Map<String, Object> params) {
		return valSubjectBalanceQuery001Service.getAllList(params);
    }
}
