/*
 * 文件名：ValBondMarketInfo001Service.java 
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dao.ComBondMarketInfo001Mapper;

/**
 * 每日债券行情
 * @author Administrator
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Service
 * @since 1.0
 */
@Service(value="serviceComBondMarketInfo001Service")
public class ComBondMarketInfo001Service extends CommonExportService<ComBondMarketInfo001Dto> {
    /**
     * 每日债券行情 mapper
     */
    @Autowired
    private ComBondMarketInfo001Mapper comBondMarketInfo001Mapper;
    
    /**
     * 
     *每日债券行情查询
     * @param page
     * @param length
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Page<ComBondMarketInfo001Dto> queryPageList(int page, int length, Map<String, Object> params) throws Exception{
        return super.queryForPage(page,length,()-> comBondMarketInfo001Mapper.queryPageList(params));
//        return valBondMarketInfo001Mapper.queryPageList(dto);
        
    }
    
    
    public List<ComBondMarketInfo001Dto> queryAllList(Map<String, Object> params) throws Exception{
        
        return comBondMarketInfo001Mapper.queryPageList(params);
    }

}
