package com.joyintech.tams.cfets.etrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 场外指令分发
 * 交易员列表查询
 * @author
 * @version 1.0 2021年10月29日
 * @see CfetsEtrDispatcherTraderListRequest
 * @since 1.0
 */
@ApiModel(value="交易员查询展示列表",description="交易员查询展示列表")
public class CfetsEtrDispatcherTraderListRequest {

    @ApiModelProperty(name="userName",value="用户名",example="字符串")
    private String userName;

    @ApiModelProperty(name="realName",value="真实姓名",example="字符串")
    private String realName;

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
}

