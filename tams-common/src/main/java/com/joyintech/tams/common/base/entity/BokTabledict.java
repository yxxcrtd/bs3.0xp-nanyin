package com.joyintech.tams.common.base.entity;

public class BokTabledict extends BokTabledictKey {
    /**
     * 会计元素编码
     */
    private String dictCode;

    /**
     * 会计场景代码，关联会计场景表
     */
    private String tableId;

    /**
     * 会计元素名称
     */
    private String dictName;

    /**
     * 元素数据类型: P 下拉框 N 数值型
     */
    private String dictType;

    /**
     * 元素数据来源
     */
    private String dictSource;

    /**
     * 取得 会计元素编码
     * @return 会计元素编码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 会计元素编码
     * @param dictCode 会计元素编码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * 取得 会计场景代码，关联会计场景表
     * @return 会计场景代码，关联会计场景表
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 会计场景代码，关联会计场景表
     * @param tableId 会计场景代码，关联会计场景表
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    /**
     * 取得 会计元素名称
     * @return 会计元素名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置 会计元素名称
     * @param dictName 会计元素名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * 取得 元素数据类型: P 下拉框 N 数值型
     * @return 元素数据类型: P 下拉框 N 数值型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 设置 元素数据类型: P 下拉框 N 数值型
     * @param dictType 元素数据类型: P 下拉框 N 数值型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    /**
     * 取得 元素数据来源
     * @return 元素数据来源
     */
    public String getDictSource() {
        return dictSource;
    }

    /**
     * 设置 元素数据来源
     * @param dictSource 元素数据来源
     */
    public void setDictSource(String dictSource) {
        this.dictSource = dictSource == null ? null : dictSource.trim();
    }
}