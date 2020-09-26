/*
 * 文件名：SubjectBalanceCollectReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.subjectbalancecollect.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dao.SubjectBalanceCollectMapper;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.SubjectBalanceCollectDelReaderDto;

/**
 * 科目余额汇总批处理 reader
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see SubjectBalanceCollectDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SubjectBalanceCollectDelReader extends BaseReader<SubjectBalanceCollectDelReaderDto> {

    /**
     * 科目余额汇总mapper
     */
    @Autowired
    private SubjectBalanceCollectMapper subjectBalanceCollectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubjectBalanceCollectDelReaderDto> createList() {
        // 获取科目余额汇总的任务代码
        String taskId = this.getBatchExecuteInfo().getTaskId();
        // 根据参数查询对应待处理的账套
        List<SubjectBalanceCollectDelReaderDto> listDto = new ArrayList<>();
        listDto.add(new SubjectBalanceCollectDelReaderDto(taskId));
        return listDto;
    }

}
