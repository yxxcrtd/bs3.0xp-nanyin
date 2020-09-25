/*
 * 文件名：PositionTemplate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.dao;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 头寸模板导入
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月9日
 * @see PositionTemplate001Mapper
 * @since 1.0
 */
@Repository(value="servicePositionTemplate001Mapper")
public interface PositionTemplate001Mapper {

    /**
     * 获取核算主体代码
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    String getAcctSubjectId(Map<String, Object> params);

    /**
     * 判断数据是否重复插入
     * 
     * @param booksetId
     * @param happenDate
     * @param layeringId
     * @return
     * @see
     * @since 1.0
     */
    boolean checkInsertRepeat(@Param("booksetId") String booksetId,
                         @Param("happenDate") Date happenDate,
                         @Param("layeringId") String layeringId);

}
