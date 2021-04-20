/*
 * 文件名：CfetsCustomerInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomerinfo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dao.CfetsCustomerInfo001Mapper;
import com.joyintech.service.bankuplink.basic.cfetscustomerinfo.dto.CfetsCustomerInfo001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerInfo001Service
 * @since 1.0
 */
@Service("backCfetsCustomerInfo001Service")
public class CfetsCustomerInfo001Service extends BaseService<CfetsCustomerInfo001Dto> {

    /**
     * 银行间机构信息mapper
     */
    @Autowired
    private CfetsCustomerInfo001Mapper cfetsCustomerInfo001Mapper;

    /**
     * 银行间机构信息分页数据。返回类型是Service的泛型。
     * 
     * @param length 条数
     * @param page 页数
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<CfetsCustomerInfo001Dto> selectForPage(int page, int length,
                                                         Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> cfetsCustomerInfo001Mapper.select(pageParam));
    }
}
