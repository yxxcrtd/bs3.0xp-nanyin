/*
 * 文件名：OpicsController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.opics.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.service.interf.opics.service.OpicsService;

/**
 * OPICS-数据接入Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月21日
 * @see OpicsController
 * @since 1.0
 */
@RestController
public class OpicsController {

    /**
     * 处理入口
     * 
     * @param dto 请求参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/batchConsumer/opicsBatch")
    public BatchResultDto windBatch(@RequestBody BatchResultDto dto) {
        OpicsService service = getService(dto.getBatchName());
        if (service == null) {
            dto.setResult(false);
            dto.setErrMsg(dto.getBatchName() + "对应的Service不存在");
            return dto;
        }
        String uuid = IDGenerator.generate();
        return service.executeService(dto, uuid);
    }

    /**
     * 取得 接口处理Service
     * 
     * @param key batch名
     * @return 接口处理Service
     * @see
     * @since 1.0
     */
    public OpicsService getService(String key) {
        return ServiceMapperUtil.getOpicsService(key);
    }
}
