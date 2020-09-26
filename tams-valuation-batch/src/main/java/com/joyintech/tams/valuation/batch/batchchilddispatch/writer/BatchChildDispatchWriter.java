/*
 * 文件名：BatchChildDispatchWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.dao.BokBatchTaskTodoMapper;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;
import com.joyintech.tams.valuation.batch.batchchilddispatch.dto.BatchChildDispatchDtoW;


/**
 * 子调度批处理 - writer
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchChildDispatchWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    BokBatchTaskTodoMapper ValBatchTaskTodoMapper;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> arg0)
        throws Exception {
        if(arg0 == null || arg0.isEmpty()){
            return;
        }
        List<BatchChildDispatchDtoW> details = (List<BatchChildDispatchDtoW>)arg0;
        List<BokBatchTaskTodo> detail = new ArrayList<BokBatchTaskTodo>();
        for(BatchChildDispatchDtoW dto : details){
            detail.addAll(dto.getDetails());
        }
        for(BokBatchTaskTodo tmp : detail){
            //需要批量插入
            ValBatchTaskTodoMapper.insert(tmp);
        }        
    }
}
