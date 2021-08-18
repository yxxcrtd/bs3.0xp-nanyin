package com.joyintech.sso.config.pojo;

import com.joyintech.sso.constant.StringConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="平台管理信息",description="平台管理信息")
public class SsoPlatform {
    @ApiModelProperty(required = false, name="id",value="平台代码",example="")
    private String id;

    @ApiModelProperty(required = true, name="platformname",value="平台名称",example="")
    private String platformname;

    @ApiModelProperty(required = true, name="platformurl",value="平台地址",example="")
    private String platformurl;

    @ApiModelProperty(required = true, name="weight",value="权重",example="1")
    private Integer weight;

    @ApiModelProperty(required = true, name="status",value="可用状态(0:不可用,1:可用)",example="0")
    private Integer status;

    @ApiModelProperty(required = false, name="mdtime",value="创建时间(yyyy-MM-dd HH:mm:ss)",example="")
    private String mdtime;

    @ApiModelProperty(required = false, name="mduser",value="更新人",example="")
    private String mduser;

    @ApiModelProperty(required = false, name="icon",value="图标名",example="")
    private String icon;

    @ApiModelProperty(required = true, name="cookiekey",value="身份标识",example="")
    private String cookiekey;

    @ApiModelProperty(required = false, name="remark1",value="备注1",example="")
    private String remark1;

    @ApiModelProperty(required = false, name="remark2",value="备注2",example="")
    private String remark2;

    @ApiModelProperty(required = true, name="platformcode",value="平台编码",example="")
    private String platformcode;

    @ApiModelProperty(required = false, name="describe",value="描述",example="")
    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }

    public String getPlatformurl() {
        return platformurl;
    }

    public void setPlatformurl(String platformurl) {
        this.platformurl = platformurl;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public String getIcon() {
        return icon != null ? icon.trim() : StringConstant.empty;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCookiekey() {
        return cookiekey != null ? cookiekey.trim() : "username";
    }

    public void setCookiekey(String cookiekey) {
        this.cookiekey = cookiekey;
    }

    public String getPlatformcode() {
        return platformcode;
    }

    public void setPlatformcode(String platformcode) {
        this.platformcode = platformcode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
