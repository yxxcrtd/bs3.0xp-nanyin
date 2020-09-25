/*
 * 文件名：OpicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.opics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * OPICS-数据接入Service共同类
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月21日
 * @see OpicsService
 * @since 1.0
 */
public abstract class OpicsService extends InterfBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据接入方法
     * 
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public abstract void saveData()
        throws Exception;

    /**
     * 执行数据接入
     * 
     * @param dto batch参数
     * @param uuid 处理识别码
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public BatchResultDto executeService(BatchResultDto dto, String uuid) {
        setUuid(uuid);
        try {
            saveData();
        }
        catch (Exception e) {
            logger.error("数据接入异常：{}", e.getMessage());
            doException(e, "数据接入异常");
        }
        addInterfLog(dto.getBatchId(), dto.getBatchId(), DictCodeList.是否_否.getValue(),
            StringUtil.EMPTY, StringUtil.EMPTY);
        dto.setResult(isHandleOK());
        dto.setErrMsg(getErrMsg());
        return dto;
    }
}
