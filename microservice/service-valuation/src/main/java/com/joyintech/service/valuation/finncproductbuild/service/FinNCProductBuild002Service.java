/*
 * 文件名：FinNCProductBuild002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.finncproductbuild.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.finncproductbuild.dao.FinNCProductBuild002Mapper;
import com.joyintech.service.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;

/**
 * 
 * 新建、修改Service
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild002Service
 * @since 1.0
 */
@Service(value="serviceFinNCProductBuild002Service")
public class FinNCProductBuild002Service extends BaseService<FinNCProductBuild001Dto>{
	
    /**
     * FinNCProductBuild002Mapper
     */
    @Autowired
    private FinNCProductBuild002Mapper mapper;
	
    /**
     * 下拉框分页获取母产品
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getpFinprodId(int page,int length,Map<String,Object> map) {
        return super.queryForPageForSelectReturn(page,length,()->mapper.getpFinprodId(map));
    }
    
    /**
     * 获取机构信息
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCustomerId(int page,int length,Map<String,Object> map) {
        return super.queryForPageForSelectReturn(page,length,()->mapper.getCustomerId(map));
    }
    
    /**
     * 唯一性验证
     * <功能详细描述>
     * @param finprodId
     * @see
     * @since 1.0
     */
    public FinNCProductBuild001Dto findByFinprodId(String finprodId) {
        return mapper.findByFinprodId(finprodId);
    }
	
}
