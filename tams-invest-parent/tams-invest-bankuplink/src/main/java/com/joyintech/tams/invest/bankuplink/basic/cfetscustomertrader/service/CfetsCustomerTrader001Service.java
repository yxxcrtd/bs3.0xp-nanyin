/*
 * 文件名：CfetsCustomerTrader001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.invest.bankuplink.basic.cfetscustomertrader.dto.CfetsCustomerTrader001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 银行间交易员信息 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsCustomerTrader001Service
 * @since 1.0
 */
@Service
public class CfetsCustomerTrader001Service extends CommonExportService<CfetsCustomerTrader001Dto> {

    /**
     * 检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<CfetsCustomerTrader001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsCustomerTrader001/selectForPage",
            pageParam, CfetsCustomerTrader001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/bankuplink/basic/cfetscustomertrader/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CfetsCustomerTrader001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
}
