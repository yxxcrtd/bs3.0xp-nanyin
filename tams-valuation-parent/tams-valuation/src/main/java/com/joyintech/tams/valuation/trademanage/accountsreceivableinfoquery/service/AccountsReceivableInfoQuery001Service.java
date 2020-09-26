/*
 * 文件名：AccountsReceivableInfoQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.accountsreceivableinfoquery.dto.AccountsReceivableInfoQuery001Dto;

/**
 * 应收款项信息查询
 * 
 * @author zhangzhao
 * @version 1.0 2018年10月9日
 * @see AccountsReceivableInfoQuery001Service
 * @since 1.0
 */
@Service
public class AccountsReceivableInfoQuery001Service extends CommonExportService<AccountsReceivableInfoQuery001Dto> {

    /**
     * 标的资产下拉框数据选择
     * 
     * @return pageParam
     * @exception Exception
     */
    public List<SelectOptionDto> getBdFinprodId(Map<String, Object> pageParam) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/AccountsReceivableInfoQuery001/getBdFinprodId", pageParam,
            SelectOptionDto.class);
    }

    /**
     * 根据条件分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public Page<AccountsReceivableInfoQuery001Dto> findByPage(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/AccountsReceivableInfoQuery001/findPage", params,
            AccountsReceivableInfoQuery001Dto.class);
    }

    /**
     * 导出功能 - 不分页查询
     * 
     * @param params
     * @return
     * @throws Exception
     */
    public List<AccountsReceivableInfoQuery001Dto> findPageout(Map<String, Object> params) {
        return restTemplate.postForList(
            ServiceApplication.VALUATION_SERVICE_NAME + "/AccountsReceivableInfoQuery001/findPageout", params,
            AccountsReceivableInfoQuery001Dto.class);
    }

}
