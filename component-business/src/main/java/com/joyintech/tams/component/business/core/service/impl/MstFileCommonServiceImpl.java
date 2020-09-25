/*
 * 文件名：MstFileCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.tams.component.business.core.service.MstFileCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;

/**
 * 附件信息共通
 * 
 * @author dingzhipeng
 * @version 1.0 2018年8月27日
 * @see MstFileCommonServiceImpl
 * @since 1.0
 */
@Component("mstFileCommonService")
public class MstFileCommonServiceImpl implements MstFileCommonService {

    @Autowired
    private JoyinRestTemplate restTemplate;

    /**
     * 上传附件
     * 
     * @param mstFileInputParameter 函数输入参数
     * @return 文件列表
     */
    @Override
    public MstFileOutputParameter uploadFile(MstFileInputParameter mstFileInputParameter) {
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/insertFileUploadInfo",
            mstFileInputParameter, MstFileOutputParameter.class);
    }

    /**
     * 获取附件(业务编码)
     * 
     * @param bussinessId 业务编号
     * @return 文件列表
     */
    @Override
    public MstFileOutputParameter getFileInfoByBusiId(String bussinessId) {
        return restTemplate.getForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/getUploadInfo/{bussinessId}",
            MstFileOutputParameter.class, bussinessId);
    }

    /**
     * 获取附件(通过文件名)
     * @param fileName 文件名
     * @return 文件信息
     */
    @Override
    public MstFileOutputParameter getFileInfoByFileName(String fileName) {
        Map<String,Object>map =new HashMap<>();
        map.put("fileName", fileName);
        return restTemplate.postForObject(
            ServiceApplication.COMMON_SERVICE_NAME + "/core/getFileInfo",
            map,
            MstFileOutputParameter.class);
    }
}
