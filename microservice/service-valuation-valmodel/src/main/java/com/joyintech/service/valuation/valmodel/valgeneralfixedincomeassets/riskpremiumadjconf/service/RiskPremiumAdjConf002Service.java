/*
 * 文件名：RiskPremiumAdjConf002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-07-01
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dao.RiskPremiumAdjConf001Mapper;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf001Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-07-01
 * @see RiskPremiumAdjConf002Service
 * @since 1.0
 */
@Service(value="serviceRiskPremiumAdjConf002Service")
@Transactional(rollbackFor = Exception.class)
public class RiskPremiumAdjConf002Service extends BaseService<RiskPremiumAdjConf002Dto> {

    @Autowired
    private RiskPremiumAdjConf001Mapper riskPremiumAdjConf001Mapper;


    public void doComplete(RiskPremiumAdjConf002Dto dto, OperationType operationType) {
        if(OperationType.MODIFY.equals(operationType)){
            RiskPremiumAdjConf002Dto riskPremiumAdjConf002Dto = new RiskPremiumAdjConf002Dto();
            BeanUtils.copyProperties(dto, riskPremiumAdjConf002Dto);
            riskPremiumAdjConf002Dto.setSeqno(dto.getSeqno());
            riskPremiumAdjConf001Mapper.updateByPrimaryKeySelective(riskPremiumAdjConf002Dto);
        }
    }
}
