package com.joyintech.tams.business.common.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 数据表格设置参数
 */
public class SetDataTabelDto {

    @ApiModelProperty(name = "srcCode", value = "原字段code")
    private String srcCode;

    @ApiModelProperty(name = "targetCode", value = "目标字段code")
    private String targetCode;

    @ApiModelProperty(name = "pageCode", value = "页面编码")
    private String pageCode;

    @ApiModelProperty(name = "dtCode", value = "数据表格编号")
    private String dtCode;

    @ApiModelProperty(name = "flag", value = "方向1上，2下")
    private Integer flag;

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public String getDtCode() {
        return dtCode;
    }

    public void setDtCode(String dtCode) {
        this.dtCode = dtCode;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
