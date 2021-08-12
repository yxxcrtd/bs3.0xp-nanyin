package com.joyintech.sso.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户授权平台信息",description="用户授权平台信息")
public class SsoUserPlatFormDto {
    @ApiModelProperty(required = true, name="userid",value="用户代码",example="")
    private String userid;

    @ApiModelProperty(required = true, name="platformid",value="平台代码",example="")
    private String platformid;

    @ApiModelProperty(required = false, name="platformcode",value="系统编码",example="")
    private String platformcode;

    @ApiModelProperty(required = false, name="platformname",value="系统名称",example="")
    private String platformname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPlatformid() {
        return platformid;
    }

    public void setPlatformid(String platformid) {
        this.platformid = platformid;
    }

    public String getPlatformcode() {
        return platformcode;
    }

    public void setPlatformcode(String platformcode) {
        this.platformcode = platformcode;
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }
}
