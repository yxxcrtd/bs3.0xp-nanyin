package com.joyintech.tams.cfets.etrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 场外指令分发
 * 分发权限配置交易员请求数据DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherTraderAuthListRequest
 * @since 1.0
 */
@ApiModel(value="分发权限配置交易员请求数据",description="分发权限配置交易员请求数据")
public class CfetsEtrDispatcherTraderAuthListRequest {

    @ApiModelProperty(name="dtoList",value="分发权限配置交易员请求数据",example="dtoList")
    private List<CfetsEtrDispatcherTraderAuthListResponse> dtoList;

    @ApiModelProperty(name="isConfirmFlag",value="标识",example="Y")
    private String isConfirmFlag;

    public List<CfetsEtrDispatcherTraderAuthListResponse> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CfetsEtrDispatcherTraderAuthListResponse> dtoList) {
        this.dtoList = dtoList;
    }

    public String getIsConfirmFlag() {
        return isConfirmFlag;
    }

    public void setIsConfirmFlag(String isConfirmFlag) {
        this.isConfirmFlag = isConfirmFlag;
    }
}

