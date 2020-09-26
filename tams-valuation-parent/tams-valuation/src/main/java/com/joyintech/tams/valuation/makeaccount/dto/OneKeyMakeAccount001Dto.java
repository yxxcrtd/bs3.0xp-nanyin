/*
 * 文件名：OneKeyMakeAccount001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.valuation.makeaccount.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年3月5日
 * @see OneKeyMakeAccount001Dto
 * @since 1.0
 */
public class OneKeyMakeAccount001Dto {
    
    private String seqNo;
    
    
    public String getSeqNo() {
        return seqNo;
    }

    
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    
    public String getStartTime() {
        return startTime;
    }

    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    
    public String getEndTime() {
        return endTime;
    }

    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getModelCode() {
        return modelCode;
    }

    
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    
    public String getBusiDate() {
        return busiDate;
    }

    
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate;
    }

    
    public String getPageDate() {
        return pageDate;
    }

    
    public void setPageDate(String pageDate) {
        this.pageDate = pageDate;
    }

    private String startTime;
    
    private String endTime;
    
    private String status;
    
    private String modelCode;
    
    
    private String busiDate;
    
    private String pageDate;
    
    private String runTime;
    
    
    private String lastTime;

    

    
    
    public String getLastTime() {
        return lastTime;
    }


    
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }


    public String getRunTime() {
        return runTime;
    }


    
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    
    
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal useingTime;


    
    public BigDecimal getUseingTime() {
        return useingTime;
    }


    
    public void setUseingTime(BigDecimal useingTime) {
        this.useingTime = useingTime;
    }
    
    private String finprodId;
    
    private String finprodName;


    
    public String getFinprodId() {
        return finprodId;
    }


    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    public String getFinprodName() {
        return finprodName;
    }


    
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
    private String interType;


    
    public String getInterType() {
        return interType;
    }


    
    public void setInterType(String interType) {
        this.interType = interType;
    }
    
    
    

}
