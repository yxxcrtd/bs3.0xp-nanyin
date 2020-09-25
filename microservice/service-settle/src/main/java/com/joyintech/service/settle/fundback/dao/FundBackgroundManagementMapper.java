/*
 * 文件名：BussSettleOrderCommMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.fundback.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.settle.fundback.FundBackgroundManagementDto;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FundBackgroundManagementMapper {
    List<SelectOptionDto> findPortfolioId(Map<String, Object> param);

    List<SelectOptionDto>  findBizCode(Map<String, Object> params);
    List<SelectOptionDto>  findBizCodeA(Map<String, Object> params);
    List<SelectOptionDto>  findBizCodeB(Map<String, Object> params);
    List<SelectOptionDto>  findBizCodeC(Map<String, Object> params);
    List<SelectOptionDto>  findBizCodeD(Map<String, Object> params);

    List<FundBackgroundManagementDto> findPage(Map<String, Object> params);

    Map<String, Object> findWorkflowInfo(Map<String, Object> params);
}
