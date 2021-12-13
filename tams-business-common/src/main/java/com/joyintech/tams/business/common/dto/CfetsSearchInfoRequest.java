package com.joyintech.tams.business.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * 指令下拉框模糊查询
 */
@ApiModel(value="下拉框请求参数",description="下拉框请求参数")
public class CfetsSearchInfoRequest {

    @ApiModelProperty(name="search",value="模糊查询输入值",example="1")
    private String search;
    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1}")
    private PageMode pageMode;
    @ApiModelProperty(name="ps",value="交易方向",example="T03")
    private String ps;
    @ApiModelProperty(name="partyId",value="本方/对手方的21位码",example="111")
    private String partyId;
    @ApiModelProperty(name="dealType",value="交易类型",example="04")
    private String dealType;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public PageMode getPageMode() {
        return pageMode;
    }

    public void setPageMode(PageMode pageMode) {
        this.pageMode = pageMode;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
}
