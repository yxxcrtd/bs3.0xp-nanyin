package com.joyintech.sso.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/5/20
 */
@ApiModel(value="授权",description="授权")
public class GrantAuthorizationDto {
    @ApiModelProperty(required = true, name="userid",value="用户代码",example="")
    private String userid;

    @ApiModelProperty(required = true, name="platformid",value="平台代码",example="")
    private String platformid;

    @ApiModelProperty(required = true, name="userids",value="用户代码集合",example="")
    private List<String> userids;

    @ApiModelProperty(required = true, name="platformids",value="平台代码集合",example="")
    private List<String> platformids;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<String> getPlatformids() {
        return platformids;
    }

    public void setPlatformids(List<String> platformids) {
        this.platformids = platformids;
    }

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid;
    }

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }
}

