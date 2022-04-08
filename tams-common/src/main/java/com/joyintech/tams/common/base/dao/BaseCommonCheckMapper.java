package com.joyintech.tams.common.base.dao;


import com.joyintech.tams.common.base.entity.BaseCommonCheckTableMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 基础共通mapper
 *
 * @author Administrator
 * @version 2016年10月22日
 * @see BaseCommonCheckMapper
 * @since
 */
@Repository
public interface BaseCommonCheckMapper {
    /**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<BaseCommonCheckTableMessage> getTableColumnList(@Param("tableName") String tableName);
}