/*
 * 文件名：DataFileUpload002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.fileupload.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload002Mapper
 * @since 1.0
 */
@Repository(value="serviceDataFileUpload002Mapper")
public interface DataFileUpload002Mapper {

    /**
     * 根据条件统计
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    int countByCondition(Map map);

    
}
