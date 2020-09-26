/*
 * 文件名：DrawBaseInfoSelectComReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.batch.batchdispatch.dao.BatchDispatchMapper;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDimensionConfig;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchTaskInfo;

/**
 * 计提批处理基础信息读入service
 *
 * @author wudebao
 * @version 1.0 2018年5月25日
 * @see BatchDispathReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispathReaderService {

    /**
     * batchDispatchMapper
     */
    @Autowired
    private BatchDispatchMapper batchDispatchMapper;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<BatchTaskInfo> selectBatchTaskInfo(List<String> batchGroupSeqNos){
        // 查询批处理任务
        List<BatchTaskInfo> batchTaskInfoList = batchDispatchMapper.selectBatchJob(
            batchGroupSeqNos);
        return batchTaskInfoList;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<BatchDimensionConfig> selectDimConfig(){
        List<BatchDimensionConfig> dimConfigList = batchDispatchMapper.selectBatchDimensionConfig();
        return dimConfigList;
    }
}
