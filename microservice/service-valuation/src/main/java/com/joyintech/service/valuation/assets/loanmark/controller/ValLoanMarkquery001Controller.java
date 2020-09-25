/*
 * 文件名：ValVoucherQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.assets.loanmark.controller;

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
import com.joyintech.service.valuation.assets.loanmark.dto.ValLoanMarkquery001Dto;
import com.joyintech.service.valuation.assets.loanmark.service.ValLoanMarkquery001Service;

/**
 * 拆借标的查询
 * 
 * @author huwei
 * @version 2.0 2018年10月19日
 * @see ValLoanMarkquery001Controller
 * @since 2.0
 */
@RestController(value="serviceValLoanMarkquery001Controller")
@RequestMapping("/ValLoanMarkquery001")
public class ValLoanMarkquery001Controller extends BaseController {

    /**
     * ValLoanMarkquery001Service
     */
    @Autowired
    private ValLoanMarkquery001Service valLoanMarkquery001Service;

 

    /**
     * 债券类资产数据取得
     *
     * @return Page<ValLoanMarkquery001Dto>
     * @throws Exception 异常
     * @see
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ValLoanMarkquery001Dto> findPage(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valLoanMarkquery001Service.findPage(page, length, param);
    }

    @PostMapping("/findAll")
    public List<ValLoanMarkquery001Dto> findAll(@RequestBody Map<String, Object> param){
    	 return valLoanMarkquery001Service.findAll(param);
    }

}
