package com.joyintech.sso.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther zhangnian
 * @date 2021/5/11
 */
@ApiModel(value="应用平台信息",description="应用平台信息")
public class Platform {

//    private static final long serialVersionUID = 6356714477516861236L;

    @ApiModelProperty(required=false,name="id",value="平台id",example="")
    private String id;

    @ApiModelProperty(required=true,name="platformName",value="平台名称",example="")
    private String platformName;

    @ApiModelProperty(required=true,name="platformUrl",value="平台地址",example="")
    private String platformUrl;

    @ApiModelProperty(required=true,name="weight",value="权重",example="")
    private String weight;

    @ApiModelProperty(required=true,name="icon",value="图标",example="")
    private String icon;

    @ApiModelProperty(required=true,name="cookieKey",value="cookieKey",example="")
    private String cookieKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(String platformUrl) {
        this.platformUrl = platformUrl;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCookieKey() {
        return cookieKey;
    }

    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }


}

