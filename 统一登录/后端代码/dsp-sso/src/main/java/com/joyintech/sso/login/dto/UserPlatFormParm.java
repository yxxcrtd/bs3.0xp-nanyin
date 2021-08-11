package com.joyintech.sso.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@ApiModel(value="重定向参数信息",description="重定向参数信息")
public class UserPlatFormParm {

    @ApiModelProperty(required=true,name="id",value="平台id",example="")
    private String id;

    @ApiModelProperty(required=true,name="username",value="用户名",example="")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

