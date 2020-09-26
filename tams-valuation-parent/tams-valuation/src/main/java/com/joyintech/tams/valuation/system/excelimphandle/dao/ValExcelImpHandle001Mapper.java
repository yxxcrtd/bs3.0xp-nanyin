/*
 * 文件名：ValExcelImpHandler001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.system.excelimphandle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleCustomSqlDto;
import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDetailDto;
import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDictcodeDto;
import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleMasterDataDto;
import com.joyintech.tams.valuation.system.excelimphandle.dto.ValExcelRuleDto;
import com.joyintech.tams.valuation.system.excelimphandle.dto.ValTableColumnDefineInfoDto;

/**
 * 导入管理mapper
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelImpHandle001Mapper
 * @since 1.0
 */
public interface ValExcelImpHandle001Mapper {
    
    /**
     * 查询Excel导入规则
     *
     * @param impFileName 导入文件名
     * @param impFileSheetName 导入文件sheet名
     * @return Excel导入规则
     * @see
     * @since 1.0
     */
    List<ValExcelRuleDto> selectValExcelRule(@Param("impFileName") String impFileName,
                                             @Param("impFileSheetName") String impFileSheetName);

    /**
     * 查询Excel导入规则明细
     *
     * @param ruleId 规则代码
     * @return Excel导入规则明细
     * @see
     * @since 1.0
     */
    List<ValExcelRuleDetailDto> selectExcelRuleDetail(@Param("ruleId") String ruleId);

    /**
     * 查询表的主键字段
     *
     * @param tableName 表名
     * @return 主键字段
     * @see
     * @since 1.0
     */
    List<String> selectTableKey(@Param("tableName") String tableName);

    /**
     * 查询表的字段定义信息
     *
     * @param tableName 表名
     * @return 表的字段定义信息
     * @see
     * @since 1.0
     */
    List<ValTableColumnDefineInfoDto> selectTableColumnDefineInfo(@Param("tableName") String tableName);

    /**
     * 获取Master值
     * 
     * @param masterDataTable 基础数据表名
     * @param masterDataIdColumn 基础数据表ID项目物理名
     * @param masterDataNameColumn 基础数据表NAME项目物理名
     * @param sourceValue 源值
     * @return 获取master值
     * @see
     * @since 1.0
     */
    String getMasterValue(@Param("masterDataTable") String masterDataTable,
                          @Param("masterDataIdColumn") String masterDataIdColumn,
                          @Param("masterDataNameColumn") String masterDataNameColumn,
                          @Param("sourceValue") String sourceValue);

    /**
     * Excel导入数据字典转化规则取得
     * 
     * @param ruleId 规则代码
     * @return 获取master值
     * @see
     * @since 1.0
     */
    List<ValExcelRuleDictcodeDto> selectRuleDictcode(@Param("ruleId") String ruleId);

    /**
     * 获取Excel导入基础数据规则
     * 
     * @param ruleId 规则代码
     * @return Excel导入基础数据规则
     * @see
     * @since 1.0
     */
    List<ValExcelRuleMasterDataDto> selectRuleMasterData(@Param("ruleId") String ruleId);

    /**
     * 获取Excel导入自定义SQL规则
     * 
     * @param ruleId 规则代码
     * @return Excel导入自定义SQL规则
     * @see
     * @since 1.0
     */
    List<ValExcelRuleCustomSqlDto> selectRuleCustomSql(@Param("ruleId") String ruleId);


}
