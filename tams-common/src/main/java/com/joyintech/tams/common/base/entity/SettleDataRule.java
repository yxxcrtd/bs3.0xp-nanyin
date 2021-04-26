package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleDataRule extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 清算数据表
     */
    private String tableName;

    /**
     * 数据源文件
     */
    private String dataFile;

    /**
     * 证券品种
     */
    private String finprodType;

    /**
     * 接口字段中文名
     */
    private String interfFieldCnname;

    /**
     * 表字段名
     */
    private String tableField;

    /**
     * 数据项CODE
     */
    private String fieldCode;

    /**
     * 数据项值
     */
    private String fieldValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 清算数据表
     * @return 清算数据表
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 清算数据表
     * @param tableName 清算数据表
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 取得 数据源文件
     * @return 数据源文件
     */
    public String getDataFile() {
        return dataFile;
    }

    /**
     * 设置 数据源文件
     * @param dataFile 数据源文件
     */
    public void setDataFile(String dataFile) {
        this.dataFile = dataFile == null ? null : dataFile.trim();
    }

    /**
     * 取得 证券品种
     * @return 证券品种
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 证券品种
     * @param finprodType 证券品种
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 接口字段中文名
     * @return 接口字段中文名
     */
    public String getInterfFieldCnname() {
        return interfFieldCnname;
    }

    /**
     * 设置 接口字段中文名
     * @param interfFieldCnname 接口字段中文名
     */
    public void setInterfFieldCnname(String interfFieldCnname) {
        this.interfFieldCnname = interfFieldCnname == null ? null : interfFieldCnname.trim();
    }

    /**
     * 取得 表字段名
     * @return 表字段名
     */
    public String getTableField() {
        return tableField;
    }

    /**
     * 设置 表字段名
     * @param tableField 表字段名
     */
    public void setTableField(String tableField) {
        this.tableField = tableField == null ? null : tableField.trim();
    }

    /**
     * 取得 数据项CODE
     * @return 数据项CODE
     */
    public String getFieldCode() {
        return fieldCode;
    }

    /**
     * 设置 数据项CODE
     * @param fieldCode 数据项CODE
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    /**
     * 取得 数据项值
     * @return 数据项值
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * 设置 数据项值
     * @param fieldValue 数据项值
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}