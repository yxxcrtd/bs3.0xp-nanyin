/*
 * 文件名：ValFinProductValuation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.dao.ValFinProductValuation001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.dto.ResultDto;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年7月20日
 * @see ValFinProductValuation001Service
 * @since 1.0
 */
@Service(value="serviceValFinProductValuation001Service")
public class ValFinProductValuation001Service extends CommonExportService<ResultDto> {

    /**
     * ValFinProductValuation001Mapper
     */
    @Autowired
    private ValFinProductValuation001Mapper valFinProductValuation001Mapper;
    
    
    /**
     * 表格加载
     * 
     * @param params map
     * @return List<ResultDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ResultDto> tableLoadPage(int page,int length,Map<String, Object> params) throws Exception {
        return super.queryForPage(page,length,()->valFinProductValuation001Mapper.tableLoad(params));
    }

    /**
     * 表格加载
     * 
     * @param params map
     * @return List<ResultDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<ResultDto> tableLoad(Map<String, Object> params) throws Exception {
        return valFinProductValuation001Mapper.tableLoad(params);
    }

    /**
     * 参数封装
     * 
     * @param finprodId 产品代码
     * @param fundtype 基金类型
      * @param fundAbbr 基金简称
     * @param valuationStartDate 估值开始日
     * @param valuationEndDate 估值结束日
     * @return Map<String, Object>
     * @see
     * @since 1.0
     */
    public Map<String, Object> getParams(String finprodId, String fundtype, String fundAbbr,
                                         String valuationStartDate, String valuationEndDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("fundtype", fundtype);
        map.put("fundAbbr",fundAbbr);
        map.put("finprodType", DictCodeListExt1.金融产品类型_基金.getValue());
        if (valuationStartDate != null && !"".equals(valuationStartDate)) {
            map.put("valuationStartDate", DateUtil.parse(valuationStartDate));
            map.put("valuationEndDate", DateUtil.parse(valuationEndDate));
        }
        else {
            map.put("valuationStartDate", null);
            map.put("valuationEndDate", null);
        }

        return map;
    }
}
