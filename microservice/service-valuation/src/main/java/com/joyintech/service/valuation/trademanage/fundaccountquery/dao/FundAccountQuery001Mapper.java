/*
 * 文件名：FundAccountQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.fundaccountquery.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.trademanage.fundaccountquery.dto.FundAccountQuery001Dto;

/**
 * 资金账户查询-Mapper
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月11日
 * @see FundAccountQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceFundAccountQuery001Mapper")
public interface FundAccountQuery001Mapper {

    /**
     * 分页查询-资金账户查询
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<FundAccountQuery001Dto> queryFundAccountList(Map<String, Object> map);

    // /**
    // * 检索账号和账户名
    // *
    // * @return 账户列表
    // * @see
    // * @since 1.0
    // */
    // List<SuggestDto> getAcct(Map<String, Object> params);
    /**
     * 资金账户查询-双击查看
     * 
     * @param acctNo
     * @return
     * @see
     * @since 1.0
     */
    FundAccountQuery001Dto queryFundAccountInfo(@Param("acctNo") String acctNo);
}
