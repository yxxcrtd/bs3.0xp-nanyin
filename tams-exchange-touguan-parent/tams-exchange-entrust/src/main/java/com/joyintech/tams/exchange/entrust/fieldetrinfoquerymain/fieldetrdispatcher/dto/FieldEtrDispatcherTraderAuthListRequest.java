package com.joyintech.tams.exchange.entrust.fieldetrinfoquerymain.fieldetrdispatcher.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 场外指令分发
 * 分发权限配置交易员请求数据DTO
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see FieldEtrDispatcherTraderAuthListRequest
 * @since 1.0
 */
@ApiModel(value="分发权限配置交易员请求数据",description="分发权限配置交易员请求数据")
public class FieldEtrDispatcherTraderAuthListRequest {

    @ApiModelProperty(name="dtoList",value="分发权限配置交易员请求数据",example="dtoList")
    private List<FieldEtrDispatcherTraderAuthListResponse> dtoList;

    @ApiModelProperty(name="isConfirmFlag",value="标识",example="Y")
    private String isConfirmFlag;

    public List<FieldEtrDispatcherTraderAuthListResponse> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<FieldEtrDispatcherTraderAuthListResponse> dtoList) {
        this.dtoList = dtoList;
    }

    public String getIsConfirmFlag() {
        return isConfirmFlag;
    }

    public void setIsConfirmFlag(String isConfirmFlag) {
        this.isConfirmFlag = isConfirmFlag;
    }
}

