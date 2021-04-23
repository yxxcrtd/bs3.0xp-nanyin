/*
 * 文件名：ReceiveBondDialogueQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.uplink.interbank.business.dto;


public class ReceiveBondDialogueQuoteDto extends BondDialogueQuoteDto   {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2255095189310448122L;
    
    /**
     * 处理状态 ，true正常，false异常
     */
    private boolean status = true;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    
    public boolean isStatus() {
        return status;
    }

    
    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public String getErrCode() {
        return errCode;
    }

    
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    
    public String getErrMsg() {
        return errMsg;
    }

    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    
    
}
