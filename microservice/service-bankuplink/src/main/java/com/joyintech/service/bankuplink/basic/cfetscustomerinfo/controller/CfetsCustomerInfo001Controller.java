/*
 * 文件名：CfetsCustomerInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomerinfo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dto.CfetsCustomerInfo001Dto;
import com.joyintech.service.bankuplink.basic.cfetscustomerinfo.service.CfetsCustomerInfo001Service;

/**
 * 银行间机构信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerInfo001Controller
 * @since 1.0
 */
@RestController("backCfetsCustomerInfo001Controller")
@RequestMapping("/CfetsCustomerInfo001")
public class CfetsCustomerInfo001Controller {

    /**
     * {@Autowired}
     */
    @Autowired
    private CfetsCustomerInfo001Service cfetsCustomerInfo001Service;

    /**
     * 银行间机构信息分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @param page 页数
     * @param length 条数
     * @return Page类
     * @throws Exception 异常信息
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<CfetsCustomerInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,
                                                         @PathVariable("page") int page,
                                                         @PathVariable("length") int length)
        throws Exception {
        return cfetsCustomerInfo001Service.selectForPage(page, length, pageParam);
    }
}
