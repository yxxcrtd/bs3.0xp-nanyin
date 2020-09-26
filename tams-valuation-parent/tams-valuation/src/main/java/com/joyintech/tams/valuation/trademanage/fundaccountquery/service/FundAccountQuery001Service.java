/*
 * 文件名：FundAccountQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.fundaccountquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;

/**
 * 资金账户查询-service
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Service
 * @since 1.0
 */
@Service
public class FundAccountQuery001Service extends CommonExportService<FundAccountQuery001Dto> {

    /**
     * 分页查询-资金账户查询
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FundAccountQuery001Dto> queryFundAccountList(Map<String, Object> params)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/FundAccountQuery001/findPage",
            params, FundAccountQuery001Dto.class);
    }

    /**
     * 根据条件查询所有
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public List<FundAccountQuery001Dto> findAll(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/FundAccountQuery001/findAll",
            params, FundAccountQuery001Dto.class);

    }

    /**
     * 资金账户查询-双击查看
     * 
     * @param acctNo
     * @return
     * @see
     * @since 1.0
     */
    public FundAccountQuery001Dto queryFundAccountInfo(String acctNo) {
        return restTemplate.getForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/FundAccountQuery002/queryFundAccountInfo?acctNo=" + acctNo,
            FundAccountQuery001Dto.class);
    }

}
