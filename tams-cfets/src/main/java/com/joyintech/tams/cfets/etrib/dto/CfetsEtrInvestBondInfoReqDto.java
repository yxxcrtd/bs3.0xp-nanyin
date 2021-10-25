/*
 * 文件名：EtrInvestBondInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.cfets.etrib.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 债券投资委托dto
 * 指令修改时，前端传数据到后台
 * @author xiexinting
 * @version 1.0 2018年9月11日
 * @see CfetsEtrInvestBondInfoReqDto
 * @since 1.0
 */
@ApiModel(value="指令操作请求参数",description="指令操作请求参数")
public class CfetsEtrInvestBondInfoReqDto {

    @ApiModelProperty(name="selectedItem",value="选中的数据",example="01")
    private transient List<CfetsEtrInvestBondInfoReqDto> selectedItem;

    @ApiModelProperty(name="entrustId",value="委托代码",example="01")
    private String entrustId;

 /*   @ApiModelProperty(name="closeWay",value="委托关闭方式",example="01")
    private String closeWay;*/

    @ApiModelProperty(name="entrustStatus",value="委托状态",example="01")
    private String entrustStatus;

    public List<CfetsEtrInvestBondInfoReqDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<CfetsEtrInvestBondInfoReqDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

   /* public String getCloseWay() {
        return closeWay;
    }

    public void setCloseWay(String closeWay) {
        this.closeWay = closeWay;
    }*/

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }
}
