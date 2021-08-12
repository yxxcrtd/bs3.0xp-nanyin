package com.joyintech.sso.config.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="平台管理信息",description="平台管理信息")
public class SsoPlatformDto {
    @ApiModelProperty(required = false, name="platformname",value="平台名称",example="")
    private String platformname;

    @ApiModelProperty(required = false, name="status",value="可用状态(0:不可用,1:可用)",example="0")
    private Integer status;

    @ApiModelProperty(required = false, name="mduser",value="维护人员",example="")
    private String mduser;

    @ApiModelProperty(required = true, name="platformcode",value="平台编码",example="")
    private String platformcode;

    public String getPlatformname() {
        return platformname;
    }

    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMduser() {
        return mduser;
    }

    public void setMduser(String mduser) {
        this.mduser = mduser;
    }

    public String getPlatformcode() {
        return platformcode;
    }

    public void setPlatformcode(String platformcode) {
        this.platformcode = platformcode;
    }
}
