package com.joyintech.tams.cfets.trdconfirm.dto;

import com.joyintech.tams.business.common.dto.PageMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 银行间交易成交确认
 * 交易执行数据与Cstp下行数据匹配DTO
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmManualMatchInfoRequest
 * @since 1.0
 */
@ApiModel(value="成交确认手工匹配请求参数",description="成交确认手工匹配请求参数")
public class CfetsTrdConfirmManualMatchInfoRequest {

    @ApiModelProperty(name="CfetsTrdConfirmRegInfoListResponse",value="交易执行数据",example="字符串1")
    private List<CfetsTrdConfirmRegInfoListResponse> selectedCfetsTrdConfirmRegInfoList;

    @ApiModelProperty(name="cfetsTrdConfirmCstpInfoListResponse",value="成交回报数据",example="字符串1")
    private  List<CfetsTrdConfirmCstpInfoListResponse> selectedCfetsTrdConfirmCstpInfoList;


    public List<CfetsTrdConfirmRegInfoListResponse> getSelectedCfetsTrdConfirmRegInfoList() {
        return selectedCfetsTrdConfirmRegInfoList;
    }

    public void setSelectedCfetsTrdConfirmRegInfoList(List<CfetsTrdConfirmRegInfoListResponse> selectedCfetsTrdConfirmRegInfoList) {
        this.selectedCfetsTrdConfirmRegInfoList = selectedCfetsTrdConfirmRegInfoList;
    }

    public List<CfetsTrdConfirmCstpInfoListResponse> getSelectedCfetsTrdConfirmCstpInfoList() {
        return selectedCfetsTrdConfirmCstpInfoList;
    }

    public void setSelectedCfetsTrdConfirmCstpInfoList(List<CfetsTrdConfirmCstpInfoListResponse> selectedCfetsTrdConfirmCstpInfoList) {
        this.selectedCfetsTrdConfirmCstpInfoList = selectedCfetsTrdConfirmCstpInfoList;
    }
}

