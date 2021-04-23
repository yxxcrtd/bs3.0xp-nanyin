/*
 * 文件名：CfetsCustomerTrader001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomertrader.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.bankuplink.basic.cfetscustomertrader.dto.CfetsCustomerTrader001Dto;
import com.joyintech.service.bankuplink.basic.cfetscustomertrader.service.CfetsCustomerTrader001Service;

/**
 * 银行间交易员信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Controller
 * @since 1.0
 */
@RestController("backCfetsCustomerTrader001Controller")
@RequestMapping("/CfetsCustomerTrader001")
public class CfetsCustomerTrader001Controller {

    /**
     * {@Autowired}
     */
    @Autowired
    private CfetsCustomerTrader001Service cfetsCustomerTrader001Service;

    /**
     * 银行间交易员信息分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @param page 页数
     * @param length 条数
     * @return Page类
     * @throws Exception 异常信息
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CfetsCustomerTrader001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                         @PathVariable("page") int page,
                                                         @PathVariable("length") int length)
        throws Exception {
        return cfetsCustomerTrader001Service.selectForPage(page, length, pageParam);
    }
}
