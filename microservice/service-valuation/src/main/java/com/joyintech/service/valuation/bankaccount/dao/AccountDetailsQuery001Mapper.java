/*
 * 文件名：AccountDetailsQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.bankaccount.dto.AccountDetailsQuery001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月1日
 * @see AccountDetailsQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountDetailsQuery001Mapper")
public interface AccountDetailsQuery001Mapper {

    /**
     * 账务明细查询
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<AccountDetailsQuery001Dto> findAccountDetailsList(Map<String, Object> params);

    /**
     * 获取余额方向和科目余额
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    AccountDetailsQuery001Dto getSubjectAmt(Map<String, Object> params);
}
