/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenjiaxing
 * 修改时间：2021年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrexchangetrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade003Dto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 场内交易指令管理导出
 * 
 * @author chenjiaxing
 * @version 1.0 2021年8月30日
 * @see EtrExchangeTrade003Service
 * @since 1.0
 */
@Service
public class EtrExchangeTrade003Service extends CommonExportService<EtrExchangeTrade003Dto>{
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("vdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format(new Date((long)value)));
        }
        if(("mdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format(new Date((long)value)));
        }
    }
}
