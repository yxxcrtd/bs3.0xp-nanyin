package com.joyintech.tams.invest.common.transmatch.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

public class ExchangeTransMatchCommonParamDto extends BaseEntity {

    /**
     * 待处理数据列表
     */
    private List<ExchangeTransMatchCommonDto> paramDataList;

    public List<ExchangeTransMatchCommonDto> getParamDataList() {
        return paramDataList;
    }

    public void setParamDataList(List<ExchangeTransMatchCommonDto> paramDataList) {
        this.paramDataList = paramDataList;
    }
}
