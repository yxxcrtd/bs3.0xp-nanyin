package com.joyintech.tams.common.base.entity;

public class RskRulePoolConfig extends RskRulePoolConfigKey {
    /**
     * 分类维度值,多个用逗号分隔
     */
    private String classifyDimensionVal;

    /**
     * null
     */
    private String linkSign;

    /**
     * null
     */
    private Long paramNum;

    /**
     * 取得 分类维度值,多个用逗号分隔
     * @return 分类维度值,多个用逗号分隔
     */
    public String getClassifyDimensionVal() {
        return classifyDimensionVal;
    }

    /**
     * 设置 分类维度值,多个用逗号分隔
     * @param classifyDimensionVal 分类维度值,多个用逗号分隔
     */
    public void setClassifyDimensionVal(String classifyDimensionVal) {
        this.classifyDimensionVal = classifyDimensionVal == null ? null : classifyDimensionVal.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getLinkSign() {
        return linkSign;
    }

    /**
     * 设置 null
     * @param linkSign null
     */
    public void setLinkSign(String linkSign) {
        this.linkSign = linkSign == null ? null : linkSign.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getParamNum() {
        return paramNum;
    }

    /**
     * 设置 null
     * @param paramNum null
     */
    public void setParamNum(Long paramNum) {
        this.paramNum = paramNum;
    }
}