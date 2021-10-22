package com.joyintech.tams.cfets.etrib.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 指令下拉框模糊查询
 */
@ApiModel(value="下拉框请求参数",description="下拉框请求参数")
public class CfetsSearchInfoDto{

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
