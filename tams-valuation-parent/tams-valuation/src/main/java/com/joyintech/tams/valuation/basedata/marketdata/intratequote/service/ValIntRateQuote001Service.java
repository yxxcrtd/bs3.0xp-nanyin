/*
 * 文件名：ValIntRateQuote001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.intratequote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.basedata.marketdata.intratequote.dto.ResultDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.mxgraph.util.svg.ParseException;

/**
 * 利率行情
 * 
 * @author HuangJian
 * @version 1.0 2018年7月18日
 * @see ValIntRateQuote001Service
 * @since 1.0
 */
@Service
public class ValIntRateQuote001Service extends CommonExportService<ResultDto> {

    /**
     * 表格加载
     * 
     * @param map 参数
     * @return List<ResultDto>
     * @see
     * @since 1.0
     */
    public List<ResultDto> tableLoad(Map<String, Object> map) {
//        return valIntRateQuote001Mapper.tableLoad(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValIntRateQuote001/tableLoad",
                map, ResultDto.class);
    }

    
    public Page<ResultDto> findPage(Map<String, Object> map) {

    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValIntRateQuote001/findPage",
                map, ResultDto.class);
  }
    
    /**
     * 设置参数
     * 
     * @param intRateId 利率代码
     * @param relaeseStartDate 发布日-开始
     * @param relaeseEndDate 发布日-结束
     * @param effStartDate 生效日-开始
     * @param effEndDate 生效日-结束
     * @return Map<String, Object>
     * @throws ParseException ParseException
     * @see
     * @since 1.0
     */
    public Map<String, Object> getParams(String rateName,String rateType,String intRateId, String relaeseStartDate,
                                         String relaeseEndDate, String effStartDate,
                                         String effEndDate)
        throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("intRateId", intRateId);
        map.put("rateName", rateName);
        if (relaeseStartDate != null && !"".equals(relaeseStartDate)) {
            map.put("relaeseStartDate", DateUtil.parse(relaeseStartDate));
            map.put("relaeseEndDate", DateUtil.parse(relaeseEndDate));
        }
        else {
            map.put("relaeseStartDate", null);
            map.put("relaeseEndDate", null);
        }
        if (effStartDate != null && !"".equals(effStartDate)) {
            map.put("effStartDate", DateUtil.parse(effStartDate));
            map.put("effEndDate", DateUtil.parse(effEndDate));
        }
        else {
            map.put("effStartDate", null);
            map.put("effEndDate", null);
        }

        return map;
    }

}
