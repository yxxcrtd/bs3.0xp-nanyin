/*
 * 文件名：ValuationSolutionManage001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：????
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationscheme.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationscheme.dto.ValuationScheme001Dto;

@Repository
public interface ValuationScheme001Mapper {

    List<SelectOptionDto> selectSolutionList();


    ValuationScheme001Dto selectScheme(String schemeId);
}
