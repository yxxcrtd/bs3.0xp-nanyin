/*
 * 文件名：DataFileUpload001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.fileupload.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.fileupload.dto.DataFileUpload001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload001Mapper
 * @since 1.0
 */
@Repository(value="serviceDataFileUpload001Mapper")
public interface DataFileUpload001Mapper {

    /**
     * 查询列表
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<DataFileUpload001Dto> selectList(Map<String, Object> pageParam);

    
    /**
     * 通过文件名删除
     * @param item
     * @see
     * @since 1.0
     */
    int deleteByFileId(String item);
}
