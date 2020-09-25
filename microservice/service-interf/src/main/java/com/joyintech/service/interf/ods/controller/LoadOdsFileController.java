/*
 * 文件名：LoadOdsFileController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ods.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.interf.cache.service.SysParamInfService;
import com.joyintech.service.interf.core.constant.SqlldrColsEnum;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.service.interf.ods.service.OdsFileReadService;

/**
 * ODS文件解析入库Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月1日
 * @see LoadOdsFileController
 * @since 1.0
 */
@RestController
public class LoadOdsFileController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 系统参数Service
     */
    @Autowired
    private SysParamInfService paraService;

    /**
     * 处理入口
     * 
     * @param dto 请求参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/batchConsumer/ods")
    public BatchResultDto ods(@RequestBody BatchResultDto dto) {
        logger.info("ODS 文件执行开始");

        String sysDate = paraService.getSysDate();
        OdsFileReadService service = ServiceMapperUtil.getApplicationContext().getBean(
            OdsFileReadService.class);
        service.init(IDGenerator.generate(), dto.getBatchId(), null);
        service.setSysDate(sysDate);
        service.loadFileIntoTable(SqlldrColsEnum.每日托管流水.getTable(), dto.getOdsFile());
        dto.setResult(service.isHandleOK());
        dto.setErrMsg(service.getErrMsg());

        logger.info("ODS 文件执行结束");
        return dto;
    }
}
