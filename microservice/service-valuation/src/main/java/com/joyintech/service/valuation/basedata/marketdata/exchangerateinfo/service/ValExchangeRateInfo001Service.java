/*
 * 文件名：ValExchangeRateInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.exchangerateinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.exchangerateinfo.dao.ValExchangeRateInfo001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.exchangerateinfo.dto.ValExchangeRateInfo001Dto;

/**
 * 汇率信息查询逻辑处理层
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年8月7日
 * @see ValExchangeRateInfo001Service
 * @since 1.0
 */
@Service(value="serviceValExchangeRateInfo001Service")
public class ValExchangeRateInfo001Service extends CommonExportService<ValExchangeRateInfo001Dto>{

    /**
     * valExchangeRateInfo001Mapper
     */
    @Autowired
    private ValExchangeRateInfo001Mapper valExchangeRateInfo001Mapper;
    /**
     * 
     * 加载表格
     * 
     * @param valExchangeRateInfo001Dto  ValExchangeRateInfo001Dto
     * @return List<ValExchangeRateInfo001Dto>
     */
    public List<ValExchangeRateInfo001Dto> tableLoad(Map<String, Object> map) {
        return valExchangeRateInfo001Mapper.tableLoad(map);
    }
    
    
    public Page<ValExchangeRateInfo001Dto> findPage(int page,int length,Map<String, Object> map) {
        return super.queryForPage(page,length,()->valExchangeRateInfo001Mapper.tableLoad(map));
    }
   
}
