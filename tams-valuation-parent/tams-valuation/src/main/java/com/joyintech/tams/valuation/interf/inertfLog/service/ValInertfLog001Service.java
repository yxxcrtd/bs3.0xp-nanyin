/*
 * 文件名：ValInertfLog001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.inertfLog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.dto.Interflog;
import com.joyintech.tams.valuation.accounadjustmanage.voucherquery.dto.ValVoucherQuery001SearchDto;
import com.joyintech.tams.valuation.interf.inertfLog.dao.ValInertfLog001Mapper;
import com.joyintech.tams.valuation.interf.inertfLog.dto.ValInertfLog001Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月13日
 * @see ValInertfLog001Service
 * @since 1.0
 */
@Service
public class ValInertfLog001Service extends CommonExportService<ValInertfLog001Dto> {
    /**
     * ValInertfLog001Mapper
     */
    @Autowired
    private ValInertfLog001Mapper valInertfLog001Mapper;
//    /**
//     * 
//     * 加载表格
//     * <功能详细描述>
//     * @param fileType 文件类型
//     * @param fileName 文件名称
//     * @param exDate 日期 
//     * @param check 是否通过
//     * @return   List<ValInertfLog001Dto>
//     * @see
//     * @since 1.0
//     */
//    public Page<ValInertfLog001Dto> search(String tmpName,String fileName,Date startDate,Date endDate,String check) throws Exception{
//
//     	return super.queryForPage(()-> valInertfLog001Mapper.search(tmpName, fileName, startDate, endDate,check));
//    }
    
    /**
     * 
     * 加载表格
     * <功能详细描述>
     * @param fileType 文件类型
     * @param fileName 文件名称
     * @param exDate 日期 
     * @param check 是否通过
     * @return   List<ValInertfLog001Dto>
     * @see
     * @since 1.0
     */
    public Page<ValInertfLog001Dto> search(String tmpName,String fileName,Date startDate,Date endDate,String check) throws Exception{
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("tmpName", tmpName);
        parameters.put("fileName", fileName);
        parameters.put("endDate", DateUtil.format(endDate));
        parameters.put("startDate", DateUtil.format(startDate));
        parameters.put("check", check);
        
        return restTemplate.postForPage(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValInertfLog001/initTable",
            parameters, ValInertfLog001Dto.class);
    }
}
