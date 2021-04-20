/*
 * 文件名：CfetsCustomerTrader001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.basic.cfetscustomertrader.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.bankuplink.basic.cfetscustomertrader.dao.CfetsCustomerTrader001Mapper;
import com.joyintech.service.bankuplink.basic.cfetscustomertrader.dto.CfetsCustomerTrader001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Service
 * @since 1.0
 */
@Service("backCfetsCustomerTrader001Service")
public class CfetsCustomerTrader001Service extends BaseService<CfetsCustomerTrader001Dto> {

    /**
     * 银行间交易员信息mapper
     */
    @Autowired
    private CfetsCustomerTrader001Mapper cfetsCustomerTrader001Mapper;

    /**
     * 银行间交易员信息分页数据。返回类型是Service的泛型。
     * 
     * @param length 条数
     * @param page 页数
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<CfetsCustomerTrader001Dto> selectForPage(int page, int length,
                                                         Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> cfetsCustomerTrader001Mapper.select(pageParam));
    }
}
