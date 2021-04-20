/*
 * 文件名：IbuInrfErrMsgSearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

/**
 * 银行间上行错误码查询条件DTO 〈功能详细描述〉
 * 
 * @author zhengjunping
 * @version 1.0 2020年5月18日
 * @see IbuInrfErrMsgSearchDto
 * @since 1.0
 */
public class IbuInrfErrMsgSearchDto {

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 排序方式
     */
    private String orderDirection;

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

    /**
     * 获取 orderField
     * 
     * @return orderField.
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * 设置 orderField
     * 
     * @param orderField orderField
     */
    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    /**
     * 获取 orderDirection
     * 
     * @return orderDirection.
     */
    public String getOrderDirection() {
        return orderDirection;
    }

    /**
     * 设置 orderDirection
     * 
     * @param orderDirection orderDirection
     */
    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

}
