/*
 * 文件名：DataFileUpload001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.fileupload.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.dao.BokFileInfoMapper;
import com.joyintech.tams.common.base.dao.BokValbalTableFileMapper;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.fileupload.dao.DataFileUpload001Mapper;
import com.joyintech.tams.valuation.fileupload.dto.DataFileUpload001Dto;

/**
 * 文件上传
 * @author zhagnwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload001Service
 * @since 1.0
 */
@Service
public class DataFileUpload001Service extends BaseService<DataFileUpload001Dto>{

    
    
    @Autowired
    DataFileUpload001Mapper dataFileUpload001Mapper;
    
    /**
     * 文件信息表mapper
     */
    @Autowired
    BokFileInfoMapper bokFileInfoMapper;
    
    /**
     * 估值表余额表上传文件信息mapper
     */
    @Autowired
    BokValbalTableFileMapper bokValbalTableFileMapper;
    
    /**
     * 根据条件分页查询
     * @param pageParam
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<DataFileUpload001Dto> findPage(Map<String, Object> pageParam) throws Exception {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DataFileUpload001/findPage",
                pageParam, DataFileUpload001Dto.class);
    }


    /**
     * 批量 删除文件 和 表记录
     * @param fileId
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String fileId) {

        for (String item : fileId.split(",")) {
            bokFileInfoMapper.deleteByPrimaryKey(item); // 删除 文件信息表
            
            dataFileUpload001Mapper.deleteByFileId(item); // 删除 估值表余额表上传文件信息
        }
        
    }

    
    
}
