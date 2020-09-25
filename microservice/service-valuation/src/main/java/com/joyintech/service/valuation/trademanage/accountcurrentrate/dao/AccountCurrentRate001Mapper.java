/*
 * 文件名：ProductTradeDealQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate001Dto;

/**
 * 账户活期利率查询 Mapper
 * 
 * @author huwei
 * @version 1.0 2018年10月11日
 * @see AccountCurrentRate001Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountCurrentRate001Mapper")
public interface AccountCurrentRate001Mapper {

    /**
     * 根据条件查询产品 ：账户活期利率查询
     * 
     * @param params Map
     * @return List<AccountCurrentRate001Dto>
     * @see
     * @since 1.0
     */
    List<AccountCurrentRate001Dto> queryAccountCurrentRateList(Map<String, Object> params);

    /**
     * 下拉框获取：账户
     * 
     * @param params Map
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAccount(Map<String, Object> params);
}
