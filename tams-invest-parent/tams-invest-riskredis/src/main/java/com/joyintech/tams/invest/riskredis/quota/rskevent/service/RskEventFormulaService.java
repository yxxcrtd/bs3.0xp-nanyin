/*
 * 文件名：RskEventFormulaService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.riskredis.quota.rskevent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.riskredis.quota.rskevent.dto.RskEventFormulaDto;

/**
 * 风控事项关联参数配置-公式配置-Service
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月10日
 * @see RskEventFormulaService
 * @since 1.0
 */
@Service
public class RskEventFormulaService extends BaseService<RskEventFormulaDto> {

    /**
     * 查询出需要的指标参数
     * 
     * @return 需要的指标参数列表
     */
    public List<SelectOptionDto> getParamList() {
        return restTemplate.getForList(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskEvent/selectParamList",
            SelectOptionDto.class);
    }
}
