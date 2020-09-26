/*
 * 文件名：BatchDispatchReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;
import com.joyintech.tams.valuation.batch.batchdispatch.dao.BatchDispatchMapper;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDimensionConfig;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchReaderDto;
import com.joyintech.tams.valuation.batch.batchdispatch.service.BatchDispathReaderService;

/**
 * 整体调度批处理 - reader
 * 
 * @author qiangchengxi
 * @version 1.0 2018年4月27日
 * @see BatchDispatchReaderStep2
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchReaderStep2 extends BaseReader<BatchDispatchReaderDto> {

    /**
     * batchDispatchMapper
     */
    @Autowired
    private BatchDispatchMapper batchDispatchMapper;

    @Autowired
    private BatchDispathReaderService batchDispathReaderService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BatchDispatchReaderDto> createList() {
        String detailSeqno = (String) this.getParams().get("detailSeqNo");
        List<BokBatchTaskTodo> todoList = batchDispatchMapper.selectBatchTaskTodo(detailSeqno);
        List<BatchDispatchReaderDto> result = new ArrayList<>();
        BatchDispatchReaderDto dto = new BatchDispatchReaderDto();
        dto.setBokBatchTaskTodoList(todoList);
        List<BatchDimensionConfig> dimConfigList = batchDispathReaderService.selectDimConfig();
        this.getParams().put(BatchDimensionConfig.class.getName(), dimConfigList);
        dto.setDetailSeqNo(detailSeqno);
        result.add(dto);
        return result;
    }
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @DataSource(DataSourceType.OTHER)
//    private void selectDimConfig(){
//        List<BatchDimensionConfig> dimConfigList = batchDispatchMapper.selectBatchDimensionConfig();
//        this.getParams().put(BatchDimensionConfig.class.getName(), dimConfigList);
//    }
}
