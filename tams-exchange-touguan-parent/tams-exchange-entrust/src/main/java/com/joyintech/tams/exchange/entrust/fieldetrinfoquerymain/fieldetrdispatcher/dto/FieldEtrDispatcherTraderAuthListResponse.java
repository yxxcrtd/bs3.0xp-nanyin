package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 自动分发请求参数DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see FieldEtrDispatcherTraderAuthListResponse
 * @since 1.0
 */
@ApiModel(value="分发权限配置交易员返回数据",description="分发权限配置交易员返回数据")
public class FieldEtrDispatcherTraderAuthListResponse {

    @ApiModelProperty(name="userName",value="用户名",example="字符串1")
    private String userName;

    @ApiModelProperty(name="realName",value="真实姓名",example="字符串1")
    private String realName;

    @ApiModelProperty(name="finprodType2",value="投资品",example="F01")
    private String finprodType2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }
}

