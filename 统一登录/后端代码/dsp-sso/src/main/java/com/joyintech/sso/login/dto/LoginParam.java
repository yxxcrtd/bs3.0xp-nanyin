package com.joyintech.sso.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@ApiModel(value="登录信息",description="登录信息")
public class LoginParam {

    @ApiModelProperty(required=true,name="username",value="用户名",example="")
    private String username;

    @ApiModelProperty(required=true,name="password",value="密码",example="")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

