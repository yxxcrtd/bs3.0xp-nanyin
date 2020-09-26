/*
 * 文件名：HandleReturnDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto;

/**
 *  估值表生成 - 手动生成 执行结果返回实体类
 * @author yangjian
 * @version 1.0 2018年9月4日
 * @see HandleReturnDto
 * @since 1.0
 */
public class HandleReturnDto {
    
    /**
     * 返回结果：0：失败，1：成功
     */
    private int returnNum;
    
    /**
     * 返回结果信息
     */
    private String returnMessage;

    
    public int getReturnNum() {
        return returnNum;
    }

    
    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    
    public String getReturnMessage() {
        return returnMessage;
    }

    
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
    
}
