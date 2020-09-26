/*
 * 文件名：ValVoucherQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto;

import java.util.Date;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * ValVoucherQuery001页面筛选条件dto 用于页面查询条件校验
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月8日
 * @see ValTableGenerate001SearchDto
 * @since 1.0
 */
public class ValTableGenerate001SearchDto {

    /**
     * 产品
     */
    private String[] prodIds;
    
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 生成状态
     */
    private String[] buildStatuses;

    /**
     * 产品类型
     */
    private String[] prodTypes;

    /**
     * 估值开始日
     */
    private Date valDateBegin;
   
    /**
     * 估值结束日
     */
    private Date valDateEnd;
    
    /**
     * 产品代码
     */
    private Date[] valDate;
    
    
    /**
     * 金融产品类型
     */
    private String finprodType =  DictCodeListExt1.金融产品类型_理财产品.getValue();
    
    /**
     * 文件类型
     */
    private String fileType =DictCodeList.文件类型_估值表.getValue();
    
    /**
     * 文件类型
     */
    private String checkStatus ;

    
    public String[] getProdIds() {
        return prodIds;
    }

    public void setProdIds(String[] prodIds) {
        this.prodIds = prodIds;
    }
    
    public String[] getProdTypes() {
        return prodTypes;
    }
    
    public void setProdTypes(String[] prodTypes) {
        this.prodTypes = prodTypes;
    }
    
    public void setBuildStatuses(String[] buildStatuses) {
        this.buildStatuses = buildStatuses;
    }

    public Date getValDateBegin() {
        return valDateBegin;
    }

    public void setValDateBegin(Date valDateBegin) {
        this.valDateBegin = valDateBegin;
    }

    public Date getValDateEnd() {
        return valDateEnd;
    }

    public void setValDateEnd(Date valDateEnd) {
        this.valDateEnd = valDateEnd;
    }
    
    public String[] getBuildStatuses() {
        return buildStatuses;
    }

    
    public String getFinprodType() {
        return finprodType;
    }

    
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    public String getFileType() {
        return fileType;
    }

    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
    public Date[] getValDate() {
        return valDate;
    }

    
    public void setValDate(Date[] valDate) {
        this.valDate = valDate;
    }

    
    public String getProdId() {
        return prodId;
    }

    
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    public String getCheckStatus() {
        return checkStatus;
    }

    
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    
}
