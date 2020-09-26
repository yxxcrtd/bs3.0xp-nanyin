
package com.joyintech.tams.valuation.system.excelimphandle.service;

import java.util.Map;

/**
 * excel导入基础service
 * @author weipeng
 * @version 1.0 2018年5月9日
 * @see ValExcelImpBaseService
 * @since 1.0
 */
public interface ValExcelImpBaseService {

    /**
     * 格式化行数据（特殊处理）
     *
     * @param rowDataMap 行数据（key为表字段的物理名）
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    void formatRowData(Map<String, Object> rowDataMap) throws Exception;

    /**
     * 主表数据录入前的特殊处理逻辑
     *
     * @param insertFlg 主表数据是insert还是update
     * @param revokeFlag 是否撤销处理
     * @param dataObj 数据实体，可以强转为单表的entity
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    void beforeHandleRowDataToDb(boolean insertFlg, boolean revokeFlag, Object dataObj) throws Exception;

    /**
     * 主表数据录入后的特殊处理逻辑
     *
     * @param insertFlg 主表数据是insert还是update
     * @param revokeFlag 是否先删后插入
     * @param dataObj 数据实体，可以强转为单表的entity
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    void afterHandleRowDataToDb(boolean insertFlg, boolean revokeFlag, Object dataObj) throws Exception;
}
