package com.joyintech.tams.common.base.entity;

public class RepCheck extends RepCheckKey {
    /**
     * 查询范围
     */
    private String queryScope;

    /**
     * 查询类型
     */
    private String queryType;

    /**
     * 基础数据类型
     */
    private String baseDataType;

    /**
     * 查询内容描述
     */
    private String queryContenDescr;

    /**
     * 查询内容
     */
    private String queryConten;

    /**
     * 查询参数
     */
    private String queryParam;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数值1
     */
    private String paramValue1;

    /**
     * 参数值2
     */
    private String paramValue2;

    /**
     * 取得 查询范围
     * @return 查询范围
     */
    public String getQueryScope() {
        return queryScope;
    }

    /**
     * 设置 查询范围
     * @param queryScope 查询范围
     */
    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope == null ? null : queryScope.trim();
    }

    /**
     * 取得 查询类型
     * @return 查询类型
     */
    public String getQueryType() {
        return queryType;
    }

    /**
     * 设置 查询类型
     * @param queryType 查询类型
     */
    public void setQueryType(String queryType) {
        this.queryType = queryType == null ? null : queryType.trim();
    }

    /**
     * 取得 基础数据类型
     * @return 基础数据类型
     */
    public String getBaseDataType() {
        return baseDataType;
    }

    /**
     * 设置 基础数据类型
     * @param baseDataType 基础数据类型
     */
    public void setBaseDataType(String baseDataType) {
        this.baseDataType = baseDataType == null ? null : baseDataType.trim();
    }

    /**
     * 取得 查询内容描述
     * @return 查询内容描述
     */
    public String getQueryContenDescr() {
        return queryContenDescr;
    }

    /**
     * 设置 查询内容描述
     * @param queryContenDescr 查询内容描述
     */
    public void setQueryContenDescr(String queryContenDescr) {
        this.queryContenDescr = queryContenDescr == null ? null : queryContenDescr.trim();
    }

    /**
     * 取得 查询内容
     * @return 查询内容
     */
    public String getQueryConten() {
        return queryConten;
    }

    /**
     * 设置 查询内容
     * @param queryConten 查询内容
     */
    public void setQueryConten(String queryConten) {
        this.queryConten = queryConten == null ? null : queryConten.trim();
    }

    /**
     * 取得 查询参数
     * @return 查询参数
     */
    public String getQueryParam() {
        return queryParam;
    }

    /**
     * 设置 查询参数
     * @param queryParam 查询参数
     */
    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam == null ? null : queryParam.trim();
    }

    /**
     * 取得 数据类型
     * @return 数据类型
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 数据类型
     * @param dataType 数据类型
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 取得 参数类型
     * @return 参数类型
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 设置 参数类型
     * @param paramType 参数类型
     */
    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    /**
     * 取得 参数值1
     * @return 参数值1
     */
    public String getParamValue1() {
        return paramValue1;
    }

    /**
     * 设置 参数值1
     * @param paramValue1 参数值1
     */
    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1 == null ? null : paramValue1.trim();
    }

    /**
     * 取得 参数值2
     * @return 参数值2
     */
    public String getParamValue2() {
        return paramValue2;
    }

    /**
     * 设置 参数值2
     * @param paramValue2 参数值2
     */
    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2 == null ? null : paramValue2.trim();
    }
}