/*
 * 文件名：HandlePaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto;

/**
 * 估值表生成 - 手动生成 传递参数实体类
 * @author yangjian
 * @version 1.0 2018年9月4日
 * @see HandlePaDto
 * @since 1.0
 */
public class HandlePaDto {
    
    private String prodId;
    
    private String valDate;

    
    public String getProdId() {
        return prodId;
    }

    
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    public String getValDate() {
        return valDate;
    }

    
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }
    
    

}
