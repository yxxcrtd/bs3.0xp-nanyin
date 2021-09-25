package com.joyintech.tams.invest.batch.valbatchimport.dto;

import java.util.Date;

public class RskOutsValTableImportHisDto{
    /**
     * 映射状态
     */
    private String mapperType;

    /**
     * 接入状态
     */
    private String importType;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 估值日期
     */
    private Date valdate;

    /**
     * 取得 文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 资产名称
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValdate() {
        return valdate;
    }

    /**
     * 设置 估值日期
     * @param valdate 估值日期
     */
    public void setValdate(Date valdate) {
        this.valdate = valdate;
    }

    /**
     * 取得 映射状态
     * @return 映射状态
     */
    public String getMapperType() {
        return mapperType;
    }

    /**
     * 设置 映射状态
     * @param mapperType 映射状态
     */
    public void setMapperType(String mapperType) {
        this.mapperType = mapperType == null ? null : mapperType.trim();
    }

    /**
     * 取得 接入状态
     * @return 接入状态
     */
    public String getImportType() {
        return importType;
    }

    /**
     * 设置 接入状态
     * @param importType 接入状态
     */
    public void setImportType(String importType) {
        this.importType = importType == null ? null : importType.trim();
    }
}