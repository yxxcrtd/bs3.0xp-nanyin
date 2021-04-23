/*
 * 文件名：ValidMessageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.dto;

/**
 * 校验结果对象
 * @author Jiaoxujin
 * @version 1.0 2020年5月8日
 * @see ValidMessageDto
 * @since 1.0
 */
public class ValidMessageDto implements java.io.Serializable {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5323343707339452442L;

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 校验消息
     */
    private String message;

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessageWithId() {
        return "quoteId: " + this.quoteId + " " + this.message;
    }
}
