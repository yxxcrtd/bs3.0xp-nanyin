/*
 * 文件名：InterfCmsUploadController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icmsbfts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.service.interf.icmsbfts.service.InterfCmsBftsService;
import com.joyintech.service.interf.loadfile.service.UpLoadFileService;

/**
 * 每日上传CMS信贷资产余额信息
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月4日
 * @see InterfCmsUploadController
 * @since 1.0
 */
@RestController
public class InterfCmsUploadController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 每日上传CMS信贷资产余额信息Service
     */
    @Autowired
    private InterfCmsBftsService cmsFileCreateService;

    /**
     * 文件上传Service
     */
    @Autowired
    private UpLoadFileService upLoadSevice;

    /**
     * 文件上传
     * 
     * @param dto 批处理
     * @return 批处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/batchConsumer/odsUpload")
    public BatchResultDto odsUpload(@RequestBody BatchResultDto dto) {
        logger.info("ODS 文件上传开始");

        String file = cmsFileCreateService.execute();
        String uuid = IDGenerator.generate();
        upLoadSevice.upLoadFile(dto.getBatchId(), uuid, null, InterfConstant.INF_BFTS, file);
        dto.setResult(upLoadSevice.isHandleOK());
        dto.setErrMsg(upLoadSevice.getErrMsg());

        logger.info("ODS 文件上传结束");
        return dto;
    }
}
