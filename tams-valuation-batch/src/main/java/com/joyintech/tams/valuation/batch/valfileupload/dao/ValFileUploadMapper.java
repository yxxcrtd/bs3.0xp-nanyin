/*
 * 文件名：ValBoKBanlanceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valfileupload.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.batch.valfileupload.dto.ValFileUploadReaderDto;

/**
 * 批处理文件上传Mapper
 * @author weipeng
 * @version 1.0 2018年12月20日
 * @see ValFileUploadMapper
 * @since 1.0
 */
@Repository
public interface ValFileUploadMapper {
    
    /**
     * 查询所有待上传的文件
     * @param params 
     *          taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
//    List<ValFileUploadReaderDto> selectBokFileGenTodo(Map<String, Object> params);
    
}
