package com.joyintech.tams.common.base.entity;

public class RskIsupIndicatorParaDtl extends RskIsupIndicatorParaDtlKey {
    /**
     * 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     */
    private String paraDisplayType;

    /**
     * 参数内容
     */
    private String content;

    /**
     * Redis存储识别域（识别风险事件）
     */
    private String redisField;

    /**
     * 刷新时是否能做交易
     */
    private String isFreshCanTrd;

    /**
     * 取得 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     * @return 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     */
    public String getParaDisplayType() {
        return paraDisplayType;
    }

    /**
     * 设置 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     * @param paraDisplayType 参数实现类型(SQL:SQL,PLUGIN:类名,FIX:固定值)
     */
    public void setParaDisplayType(String paraDisplayType) {
        this.paraDisplayType = paraDisplayType == null ? null : paraDisplayType.trim();
    }

    /**
     * 取得 参数内容
     * @return 参数内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 参数内容
     * @param content 参数内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 取得 Redis存储识别域（识别风险事件）
     * @return Redis存储识别域（识别风险事件）
     */
    public String getRedisField() {
        return redisField;
    }

    /**
     * 设置 Redis存储识别域（识别风险事件）
     * @param redisField Redis存储识别域（识别风险事件）
     */
    public void setRedisField(String redisField) {
        this.redisField = redisField == null ? null : redisField.trim();
    }

    /**
     * 取得 刷新时是否能做交易
     * @return 刷新时是否能做交易
     */
    public String getIsFreshCanTrd() {
        return isFreshCanTrd;
    }

    /**
     * 设置 刷新时是否能做交易
     * @param isFreshCanTrd 刷新时是否能做交易
     */
    public void setIsFreshCanTrd(String isFreshCanTrd) {
        this.isFreshCanTrd = isFreshCanTrd == null ? null : isFreshCanTrd.trim();
    }
}