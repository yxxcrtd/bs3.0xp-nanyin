package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 自动分发请求参数DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see FieldEtrDispatcherAutoRequest
 * @since 1.0
 */
@ApiModel(value="自动分发请求参数",description="自动分发请求参数")
public class FieldEtrDispatcherAutoRequest {

    @ApiModelProperty(name="distributeRule",value="分发规则",example="字符串1")
    private String distributeRule;

    @ApiModelProperty(name="distributeParameter",value="分发参数",example="字符串1")
    private String distributeParameter;

    @ApiModelProperty(name="vdate",value="自动分发日期开始日",example="字符串1")
    private String vdate;

    @ApiModelProperty(name="mdate",value="自动分发日期结束日",example="字符串1")
    private String mdate;

    public String getDistributeRule() {
        return distributeRule;
    }

    public void setDistributeRule(String distributeRule) {
        this.distributeRule = distributeRule;
    }

    public String getDistributeParameter() {
        return distributeParameter;
    }

    public void setDistributeParameter(String distributeParameter) {
        this.distributeParameter = distributeParameter;
    }

    public String getVdate() {
        return vdate;
    }

    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }
}

