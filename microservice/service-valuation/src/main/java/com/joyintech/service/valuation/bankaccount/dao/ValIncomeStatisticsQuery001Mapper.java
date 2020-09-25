/*
 * 文件名：ValIncomeStatisticsQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.bankaccount.dto.ValIncomeStatisticsQuery001Dto;
import com.joyintech.tams.common.base.entity.BokBookset;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年11月1日
 * @see ValIncomeStatisticsQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValIncomeStatisticsQuery001Mapper")
public interface ValIncomeStatisticsQuery001Mapper {

    /**
     * 获取账套名称
     * 
     * @return List<BokBookset>
     * @see
     * @since 1.0
     */
    List<BokBookset> getBookSetName();

    /**
     * 查询
     * 
     * @param params Map<String, Object>
     * @return List<ValIncomeStatisticsQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValIncomeStatisticsQuery001Dto> findPage(Map<String, Object> params);

}
