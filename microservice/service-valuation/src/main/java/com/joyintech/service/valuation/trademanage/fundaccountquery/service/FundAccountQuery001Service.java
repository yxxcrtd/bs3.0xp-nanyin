/*
 * 文件名：FundAccountQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.fundaccountquery.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.trademanage.fundaccountquery.dao.FundAccountQuery001Mapper;
import com.joyintech.service.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;

/**
 * 资金账户查询-service
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Service
 * @since 1.0
 */
@Service(value="serviceFundAccountQuery001Service")
public class FundAccountQuery001Service extends CommonExportService<FundAccountQuery001Dto> {

    @Autowired
    private FundAccountQuery001Mapper fundAccountQuery001Mapper;

    /**
     * 分页查询-资金账户查询
     * 
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FundAccountQuery001Dto> queryFundAccountList(int page, int length, Map<String, Object> params)
        throws Exception {

        // 获取账户类型
        String acctType = (String)params.get("acctType");
        params.put("acctTypeArr", this.String2Arr(acctType));
        Page<FundAccountQuery001Dto> pages = super.queryForPage(page, length,
            () -> fundAccountQuery001Mapper.queryFundAccountList(params));
        List<FundAccountQuery001Dto> list = pages.getRoot();
        for (FundAccountQuery001Dto dto : list) {
            String acctName = dto.getAcctName();
            acctName = acctName.split(",")[0];
            dto.setAcctName(acctName);
        }
        pages.setRoot(list);
        return pages;
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

        // 获取账户类型
        String acctType = (String)params.get("acctType");
        params.put("acctTypeArr", this.String2Arr(acctType));
        return fundAccountQuery001Mapper.queryFundAccountList(params);

    }

    // /**
    // * 检索账户列表
    // *
    // * @param param 过滤参数
    // * @return 账户列表
    // * @see
    // * @since 1.0
    // */
    // public List<SuggestDto> getAcct(Map<String, Object> params) {
    // return queryForSuggest(() -> {
    // return fundAccountQuery001Mapper.getAcct(params);
    // }, null, null);
    // }
    /**
     * 资金账户查询-双击查看
     * 
     * @param acctNo
     * @return
     * @see
     * @since 1.0
     */
    public FundAccountQuery001Dto queryFundAccountInfo(String acctNo) {

        return fundAccountQuery001Mapper.queryFundAccountInfo(acctNo);
    }

    /**
     * 字符串转数组
     * 
     * @param valueString
     * @return
     * @see
     * @since 1.0
     */
    private String[] String2Arr(String valueString) {
        // 定义返回数组
        String[] returnArr = null;

        // 字符串转数组
        if (valueString != null && valueString.trim().length() > 0) {
            returnArr = valueString.trim().split(",");
        }

        return returnArr;
    }

}
