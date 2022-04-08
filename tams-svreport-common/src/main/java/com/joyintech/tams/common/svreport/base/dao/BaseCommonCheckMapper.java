package com.joyintech.tams.common.svreport.base.dao;


import com.joyintech.tams.common.base.entity.ColumnDto;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodataWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 基础共通mapper
 *
 * @author
 * @version
 * @see BaseCommonCheckMapper
 * @since
 */
@Repository
public interface BaseCommonCheckMapper {


    int insertIntoErro(ChbInterfErrodataWithBLOBs record);


    /**
     * MySQL查询表结构
     * @param tableName
     * @return
     */
    @Select("select column_name,ordinal_position,is_nullable,data_type,character_maximum_length,column_type,column_comment,numeric_precision,numeric_scale,privileges from information_schema.columns where table_schema = 'gd_test' and table_name = #{tableName}")
    List<ColumnDto> selectBySql(@Param("tableName") String tableName);
}