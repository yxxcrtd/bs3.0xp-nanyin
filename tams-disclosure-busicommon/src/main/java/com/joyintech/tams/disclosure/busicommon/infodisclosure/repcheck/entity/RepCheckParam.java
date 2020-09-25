/*
 * 文件名：RepCheckParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanghb
 * 修改时间：2019年3月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.busicommon.infodisclosure.repcheck.entity;

/**
 * 校验参数
 * 
 * @author wanghb
 * @version 1.0 2019年3月20日
 * @see RepCheckParam
 * @since 1.0
 */
public class RepCheckParam {

    private String prodId;

    private String dictCode;

    private String itemCode;

    private String paramValue1;

    private String paramValue2;

    /**
     * 获取 prodId
     * 
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     * 
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 dictCode
     * 
     * @return dictCode.
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 dictCode
     * 
     * @param dictCode dictCode
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取 itemCode
     * 
     * @return itemCode.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 itemCode
     * 
     * @param itemCode itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取 paramValue1
     * 
     * @return paramValue1.
     */
    public String getParamValue1() {
        return paramValue1;
    }

    /**
     * 设置 paramValue1
     * 
     * @param paramValue1 paramValue1
     */
    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1;
    }

    /**
     * 获取 paramValue2
     * 
     * @return paramValue2.
     */
    public String getParamValue2() {
        return paramValue2;
    }

    /**
     * 设置 paramValue2
     * 
     * @param paramValue2 paramValue2
     */
    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2;
    }

    public String replaceForString(String sql) {
        return sql.replaceAll("\\$\\{prodId\\}", "'" +prodId+ "'")
                  .replaceAll("\\$\\{dictCode\\}","'" + dictCode + "'")
                  .replaceAll("\\$\\{itemCode\\}", "'" +itemCode+ "'")
                  .replaceAll("\\$\\{paramValue1\\}","'" + paramValue1+ "'")
                  .replaceAll("\\$\\{paramValue2\\}","'" +paramValue2+ "'");
    }

}
