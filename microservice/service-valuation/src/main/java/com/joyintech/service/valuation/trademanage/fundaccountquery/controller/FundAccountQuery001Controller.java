/*
 * 文件名：FundAccountQuery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.fundaccountquery.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.service.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;
import com.joyintech.service.valuation.trademanage.fundaccountquery.service.FundAccountQuery001Service;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;

/**
 * 资金账户查询-Controller
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Controller
 * @since 1.0
 */
@RestController(value="serviceFundAccountQuery001Controller")
@RequestMapping("/FundAccountQuery001")
public class FundAccountQuery001Controller extends BaseController {

    @Autowired
    private FundAccountQuery001Service fundAccountQuery001Service;

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<FundAccountQuery001Dto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                 @PathVariable("length") int length)
        throws Exception {
        return fundAccountQuery001Service.queryFundAccountList(page, length, param);
    }

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findAll")
    public List<FundAccountQuery001Dto> findAll(@RequestBody Map<String, Object> param)
        throws Exception {
        return fundAccountQuery001Service.findAll(param);
    }

}
