/*
 * 文件名：SubjectBalanceCollectWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalancecollect.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dao.SubjectBalanceCollectMapper;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.SubjectBalanceCollectDelProcessorDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see SubjectBalanceCollectDelWriter
 * @since 1.0
 */
@Service
public class SubjectBalanceCollectDelWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 科目余额汇总mapper
     */
    @Autowired
    private SubjectBalanceCollectMapper subjectBalanceCollectMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 删除同一任务Id对应的之前的记录
        subjectBalanceCollectMapper.deleteBalanceByKey(this.getParams());
    }

}
