/*
 * 文件名：ProBenefitPrdValuationFeeBeforeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.dao.ProBenefitPrdValuationFeeBeforeMapper;
import com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.dto.PrdValuationFeeBeforeReaderDto;

/**
 * 预期收益型产品估值计算 - reader业务处理
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月28日
 * @see ProBenefitPrdValuationFeeBeforeReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProBenefitPrdValuationFeeBeforeReaderService {

    @Autowired
    private ProBenefitPrdValuationFeeBeforeMapper proBenefitPrdValuationFeeBeforeMapper;

    public List<PrdValuationFeeBeforeReaderDto> execute(Map<String, Object> param) {
        // 处理变量设置
        SysBatchTask batchTask = (SysBatchTask)param.get("BATCHTASK");
        // 定义查询参数
        Map<String, Object> sqlMap = new HashMap<String, Object>();
        sqlMap.putAll(param);
        // 查询指定帐套的核算主体和TA产品
        List<PrdValuationFeeBeforeReaderDto> acctSubjectAndTaInfo = proBenefitPrdValuationFeeBeforeMapper.getAcctSubjectAndTaInfo(
            sqlMap);
        // 设置任务优先级
        for (PrdValuationFeeBeforeReaderDto dto : acctSubjectAndTaInfo) {
            dto.setTaskPri(batchTask.getTaskPri());
        }

        return acctSubjectAndTaInfo;
    }

}
