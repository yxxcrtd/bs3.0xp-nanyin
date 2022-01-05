package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "分页请求信息", description = "分页请求信息")
public class PageMode {
    @ApiModelProperty(required = true, name = "pageNo", value = "当前页数，0 表示查出全部", example = "1")
    @NotNull(message = "当前页数不可为空")
    private int pageNo;

    @ApiModelProperty(required = true, name = "pageSize", value = "每页数量，0 表示查出全部", example = "20")
    @NotNull(message = "每页数量不可为空")
    private int pageSize;

    @ApiModelProperty(required = true, name = "pageNo", value = "当前页数，0 表示查出全部", example = "1")
    private int total;

    @ApiModelProperty(required = true, name = "pageNo", value = "当前页数，0 表示查出全部", example = "1")
    private String sort;

    @ApiModelProperty(required = true, name = "pageNo", value = "当前页数，0 表示查出全部", example = "1")
    private String direction;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
