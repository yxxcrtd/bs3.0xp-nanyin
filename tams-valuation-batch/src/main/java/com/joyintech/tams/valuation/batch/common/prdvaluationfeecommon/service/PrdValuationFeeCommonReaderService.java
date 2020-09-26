/*
 * 文件名：PrdValuationFeeBeforeReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchTask;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dao.PrdValuationFeeCommonMapper;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto.BokstatbokpositionReaderDto;

/**
 * 产品估值计算 - reader业务处理
 * 
 * @author yangjian
 * @version 1.0 2018年8月13日
 * @see PrdValuationFeeCommonReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdValuationFeeCommonReaderService {

    @Autowired
    private PrdValuationFeeCommonMapper prdValuationFeeCommonMapper;

    /**
     * 业务逻辑处理
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<BokstatbokpositionReaderDto> execute(Map<String, Object> param) {

        // 1、处理变量设置
//        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");
        // SysBatchgrpExecInfo batchExecuteDetail = (SysBatchgrpExecInfo)param.get("BATCHDEL");
        SysBatchTask batchTask = (SysBatchTask)param.get("BATCHTASK");

        // 定义查询参数
        Map<String, Object> sqlMap = new HashMap<String, Object>();
//        sqlMap.put("happenDate", (Date) param.get(BookConstant.)); // 发生日
//        sqlMap.put("taskId", batchExecuteInfo.getTaskId()); // 任务代码
        sqlMap.putAll(param);
        // 查询指定帐套的核算主体和TA产品
        List<BokstatbokpositionReaderDto> BeforeReaderResultDtoList = prdValuationFeeCommonMapper.getAcctAndTaInfo(sqlMap);
        // 设置任务优先级
        for (BokstatbokpositionReaderDto dto : BeforeReaderResultDtoList) {
            dto.setTaskPri(batchTask.getTaskPri()); // 设置任务优先级
        }

        return BeforeReaderResultDtoList;
    }

}
