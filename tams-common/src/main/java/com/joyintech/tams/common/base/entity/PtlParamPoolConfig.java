package com.joyintech.tams.common.base.entity;

public class PtlParamPoolConfig extends PtlParamPoolConfigKey {
    /**
     * 连接符
     */
    private String linkSign;

    /**
     * 分类维度值
     */
    private String classifyDimensionVal;

    /**
     * 参数序号
     */
    private Long paramNum;

    /**
     * 取得 连接符
     * @return 连接符
     */
    public String getLinkSign() {
        return linkSign;
    }

    /**
     * 设置 连接符
     * @param linkSign 连接符
     */
    public void setLinkSign(String linkSign) {
        this.linkSign = linkSign == null ? null : linkSign.trim();
    }

    /**
     * 取得 分类维度值
     * @return 分类维度值
     */
    public String getClassifyDimensionVal() {
        return classifyDimensionVal;
    }

    /**
     * 设置 分类维度值
     * @param classifyDimensionVal 分类维度值
     */
    public void setClassifyDimensionVal(String classifyDimensionVal) {
        this.classifyDimensionVal = classifyDimensionVal == null ? null : classifyDimensionVal.trim();
    }

    /**
     * 取得 参数序号
     * @return 参数序号
     */
    public Long getParamNum() {
        return paramNum;
    }

    /**
     * 设置 参数序号
     * @param paramNum 参数序号
     */
    public void setParamNum(Long paramNum) {
        this.paramNum = paramNum;
    }
}