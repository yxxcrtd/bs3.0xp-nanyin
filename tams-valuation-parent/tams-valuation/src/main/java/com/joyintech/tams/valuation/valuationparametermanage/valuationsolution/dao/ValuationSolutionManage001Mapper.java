/*
 * 文件名：ValuationSolutionManage001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：????
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dto.ValuationSolutionManage001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dto.ValuationSolutionManage002Dto;

@Repository
public interface ValuationSolutionManage001Mapper {

    List<SelectOptionDto> selectSolutionList();

    List<ValuationSolutionManage002Dto> selectSolutionDetail(Map<String, Object> parameters);

    ValuationSolutionManage001Dto selectScheme(String schemeId);
}
