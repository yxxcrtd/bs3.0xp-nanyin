/*
 * 文件名：BatchDispatchWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.batch.batchdispatch.dto.BatchDispatchProcessorDto;
import com.joyintech.tams.common.base.dao.BokBatchTaskTodoMapper;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchWriterStep3
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BatchDispatchWriterStep3 extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    SysBatchgrpExecInfoMapper sysBatchgrpExecInfoMapper;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    @Override
    public void write(List<? extends NeedToWriteEntity> list)
        throws Exception {
        if (list == null || list.isEmpty()) {
            return;
        }
        List<BatchDispatchProcessorDto> dtoList = (List<BatchDispatchProcessorDto>)list;
        for (BatchDispatchProcessorDto dto : dtoList) {
            SysBatchgrpExecInfo info = dto.getSysBatchgrpExecInfo();
            sysBatchgrpExecInfoMapper.insert(info);
        }
    }

}
