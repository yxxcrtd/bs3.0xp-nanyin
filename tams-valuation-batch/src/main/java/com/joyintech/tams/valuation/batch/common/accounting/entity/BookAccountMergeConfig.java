/*
 * 文件名：BookAccountMergeConfig.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.accounting.entity;


public class BookAccountMergeConfig {
    
    /**
     * 交易凭证是否合并
     */
    private String isCombTraVouc;
    /**
     * 补账凭证是否合并
     */
    private String isCombSupVouc;
    /**
     * 补账分录是否合并
     */
    private String isCombSupRec;
    
    public String getIsCombTraVouc() {
        return isCombTraVouc;
    }
    
    public void setIsCombTraVouc(String isCombTraVouc) {
        this.isCombTraVouc = isCombTraVouc;
    }
    
    public String getIsCombSupVouc() {
        return isCombSupVouc;
    }
    
    public void setIsCombSupVouc(String isCombSupVouc) {
        this.isCombSupVouc = isCombSupVouc;
    }
    
    public String getIsCombSupRec() {
        return isCombSupRec;
    }
    
    public void setIsCombSupRec(String isCombSupRec) {
        this.isCombSupRec = isCombSupRec;
    }

}
