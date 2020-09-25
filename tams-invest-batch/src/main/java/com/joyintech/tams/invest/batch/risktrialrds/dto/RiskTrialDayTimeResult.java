/*
 * 文件名：RiskTrialDayTimeResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.risktrialrds.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDetailDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;

/**
 * 日间日终生成NeedToWriteEntity
 *
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskTrialDayTimeResult
 * @since 1.0
 */
public class RiskTrialDayTimeResult extends NeedToWriteEntity {

    /**
     * 指标监控值
     */
    private RiskTrialRetDto riskTrialRetDto;

    /**
     * 指标监控明细值
     */
    private RiskTrialRetDetailDto riskTrialRetDetailDto;

    public RiskTrialRetDto getRiskTrialRetDto() {
        return riskTrialRetDto;
    }

    public void setRiskTrialRetDto(RiskTrialRetDto riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    public RiskTrialRetDetailDto getRiskTrialRetDetailDto() {
        return riskTrialRetDetailDto;
    }

    public void setRiskTrialRetDetailDto(RiskTrialRetDetailDto riskTrialRetDetailDto) {
        this.riskTrialRetDetailDto = riskTrialRetDetailDto;
    }
}
