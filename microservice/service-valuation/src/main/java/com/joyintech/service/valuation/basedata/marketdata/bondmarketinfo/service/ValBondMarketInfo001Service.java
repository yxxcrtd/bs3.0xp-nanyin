/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dao.ValBondMarketInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dto.ValBondMarketInfo001SearchDto;
import com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.service.ValBondValuationInfo001Service;

/**
 * 每日债券行情
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Service
 * @since 1.0
 */
@Service(value="serviceValBondMarketInfo001Service")
public class ValBondMarketInfo001Service extends CommonExportService<ValBondMarketInfo001Dto> {
    /**
     * 每日债券行情 mapper
     */
    @Autowired
    private ValBondMarketInfo001Mapper valBondMarketInfo001Mapper;
    
    /**
     * 
     *每日债券行情查询
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Page<ValBondMarketInfo001Dto> queryPageList(int page,int length,Map<String, Object> params) throws Exception{
        return super.queryForPage(page,length,()->valBondMarketInfo001Mapper.queryPageList(params));
//        return valBondMarketInfo001Mapper.queryPageList(dto);
        
    }
    
    
    public List<ValBondMarketInfo001Dto> queryAllList(Map<String, Object> params) throws Exception{
        
        return valBondMarketInfo001Mapper.queryPageList(params);
    }

}
