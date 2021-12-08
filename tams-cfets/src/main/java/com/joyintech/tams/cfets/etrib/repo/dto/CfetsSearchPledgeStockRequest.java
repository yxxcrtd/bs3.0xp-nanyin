package com.joyintech.tams.cfets.etrib.repo.dto;

import com.joyintech.tams.business.common.dto.PageMode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * 指令下拉框模糊查询
 */
@ApiModel(value="下拉框请求参数",description="下拉框请求参数")
public class CfetsSearchPledgeStockRequest {

    @ApiModelProperty(name="search",value="模糊查询输入值",example="1")
    private String search;
    @ApiModelProperty(name="pageMode",value="分页对象",example="{pageSize: 10,pageNo: 1}")
    private PageMode pageMode;

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


}
