package com.joyintech.sso.config.pojo;

import com.joyintech.sso.constant.StringConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户授权平台信息", description = "用户授权平台信息")
public class SsoUserPlatForm {
    @ApiModelProperty(required = true, name = "userid", value = "用户代码", example = "")
    private String userid;

    @ApiModelProperty(required = true, name = "platformid", value = "平台代码", example = "")
    private String platformid;

    @ApiModelProperty(required = false, name = "mdtime", value = "创建时间(yyyy-MM-dd HH:mm:ss)", example = "")
    private String mdtime;

    @ApiModelProperty(required = false, name = "mduser", value = "更新人", example = "")
    private String mduser;

    @ApiModelProperty(required = false, name = "remark1", value = "备注1", example = "")
    private String remark1;

    @ApiModelProperty(required = false, name = "remark2", value = "备注2", example = "")
    private String remark2;

    @ApiModelProperty(required = true, name = "platformname", value = "平台名称", example = "")
    private String platformname;

    @ApiModelProperty(required = true, name = "platformurl", value = "平台地址", example = "")
    private String platformurl;

    @ApiModelProperty(required = false, name = "platformcode", value = "系统编码", example = "")
    private String platformcode;

    @ApiModelProperty(hidden = true, name = "platformHost", value = "平台主机", example = "")
    private String platformHost;

    @ApiModelProperty(hidden = true, name = "platformIndex", value = "平台主页地址", example = "")
    private String platformIndex;

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

    public String getMdtime() {
        return mdtime != null ? mdtime.trim() : StringConstant.empty;
    }

    public void setMdtime(String mdtime) {
        this.mdtime = mdtime;
    }

    public String getMduser() {
        return mduser != null ? mduser.trim() : StringConstant.empty;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser;
    }

    public String getRemark1() {
        return remark1 != null ? remark1.trim() : StringConstant.empty;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2 != null ? remark2.trim() : StringConstant.empty;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }

    public String getPlatformurl() {
        return platformHost + platformIndex;
    }

    public void setPlatformurl(String platformurl) {
        this.platformurl = platformurl;
    }

    public String getPlatformcode() {
        return platformcode;
    }

    public void setPlatformcode(String platformcode) {
        this.platformcode = platformcode;
    }

    public String getPlatformHost() {
        return platformHost;
    }

    public void setPlatformHost(String platformHost) {
        this.platformHost = platformHost;
    }

    public String getPlatformIndex() {
        return platformIndex;
    }

    public void setPlatformIndex(String platformIndex) {
        this.platformIndex = platformIndex;
    }
}
