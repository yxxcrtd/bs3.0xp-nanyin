/*
 * 文件名：ValuationSolutionManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dao.ValuationSolutionManage001Mapper;
import com.joyintech.tams.valuation.valuationparametermanage.valuationsolution.dto.ValuationSolutionManage001Dto;

@Service
public class ValuationSolutionManage001Service extends BaseService<ValuationSolutionManage001Dto> {

    @Autowired
    private ValuationSolutionManage001Mapper valuationSolutionManage001Mapper;
    public Page<ValuationSolutionManage001Dto> findPage(Map<String, Object> parameters) throws Exception {
        return super.queryForPage(ValuationSolutionManage001Mapper.class, "selectValScheme", parameters);
    }

    public List<SelectOptionDto> selectSolutionList() {
        return valuationSolutionManage001Mapper.selectSolutionList();
    }

}
