/*
 * 文件名：ValVoucherQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto;

import java.util.Date;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 记账凭证查询dto
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ValTableGenerate001Dto
 * @since 2.0
 */
public class ValTableGenerate001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 估值日期
     */
    private String valDate;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 产品简称
     */
    private String finprodName;

    /**
     * 产品类型
     */
    private String finprodType;

    /**
     * 生成状态
     */
    private String buildStatus;

    /**
     * 错误类型
     */
    private String errorType;

    /**
     * 错误明细
     */
    private String errorInfo;

    
    public String getValDate() {
        if(StringUtil.isNotEmpty(valDate)){
            return valDate.substring(0,10);
        }else {
            return valDate;
        }
    }

    
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    
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

    
    public String getFinprodType() {
        return finprodType;
    }

    
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    public String getBuildStatus() {
        return buildStatus;
    }

    
    public void setBuildStatus(String buildStatus) {
        this.buildStatus = buildStatus;
    }

    
    public String getErrorType() {
        return errorType;
    }

    
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    
    public String getErrorInfo() {
        return errorInfo;
    }

    
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

}
