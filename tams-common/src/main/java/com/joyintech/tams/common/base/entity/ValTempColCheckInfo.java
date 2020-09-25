package com.joyintech.tams.common.base.entity;

public class ValTempColCheckInfo extends ValTempColCheckInfoKey {
    /**
     * null
     */
    private String tmpCName;

    /**
     * null
     */
    private String colCName;

    /**
     * null
     */
    private String colType;

    /**
     * null
     */
    private String isNull;

    /**
     * null
     */
    private Long maxLength;

    /**
     * null
     */
    private String isExist;

    /**
     * 数据字典
     */
    private String dictCode;

    /**
     * 取得 null
     * @return null
     */
    public String getTmpCName() {
        return tmpCName;
    }

    /**
     * 设置 null
     * @param tmpCName null
     */
    public void setTmpCName(String tmpCName) {
        this.tmpCName = tmpCName == null ? null : tmpCName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getColCName() {
        return colCName;
    }

    /**
     * 设置 null
     * @param colCName null
     */
    public void setColCName(String colCName) {
        this.colCName = colCName == null ? null : colCName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getColType() {
        return colType;
    }

    /**
     * 设置 null
     * @param colType null
     */
    public void setColType(String colType) {
        this.colType = colType == null ? null : colType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsNull() {
        return isNull;
    }

    /**
     * 设置 null
     * @param isNull null
     */
    public void setIsNull(String isNull) {
        this.isNull = isNull == null ? null : isNull.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getMaxLength() {
        return maxLength;
    }

    /**
     * 设置 null
     * @param maxLength null
     */
    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsExist() {
        return isExist;
    }

    /**
     * 设置 null
     * @param isExist null
     */
    public void setIsExist(String isExist) {
        this.isExist = isExist == null ? null : isExist.trim();
    }

    /**
     * 取得 数据字典
     * @return 数据字典
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 数据字典
     * @param dictCode 数据字典
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }
}