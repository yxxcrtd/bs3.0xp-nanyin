/*
 * 文件名：DataExtraction001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.system.dataextraction.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.system.dataextraction.dto.DataExtraction001Dto;

/**
 * 数据提取
 * @author wangqidong
 * @version 1.0 2019年2月18日
 * @see DataExtraction001Mapper
 * @since 1.0
 */
@Repository
public interface DataExtraction001Mapper {

    /**
     * 执行查询
     * @param replace
     * @return
     * @see
     * @since 1.0
     */
    List<LinkedHashMap<String, Object>> selectAllData(@Param("sqlText") String sqlText);
    
    
    List<T> findPage(@Param("sqlText") String sqlText);

}
