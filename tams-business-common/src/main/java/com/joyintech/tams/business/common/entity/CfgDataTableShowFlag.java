package com.joyintech.tams.business.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "数据表格设置标识")
public class CfgDataTableShowFlag {

    @ApiModelProperty(name = "pageCode", value = "页面编码")
    private String pageCode;

    @ApiModelProperty(name = "dtCode", value = "数据表格编号")
    private String dtCode;

    @ApiModelProperty(name = "colCode", value = "列的字段名")
    private String colCode;

    @ApiModelProperty(name = "effeRange", value = "有效范围(0:全局,1:个人)")
    private Long effeRange;

    @ApiModelProperty(name = "rangeId", value = "范围ID(见有效范围字段)")
    private String rangeId;

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

    public String getColCode() {
        return colCode;
    }

    public void setColCode(String colCode) {
        this.colCode = colCode;
    }

    public Long getEffeRange() {
        return effeRange;
    }

    public void setEffeRange(Long effeRange) {
        this.effeRange = effeRange;
    }

    public String getRangeId() {
        return rangeId;
    }

    public void setRangeId(String rangeId) {
        this.rangeId = rangeId;
    }
}
