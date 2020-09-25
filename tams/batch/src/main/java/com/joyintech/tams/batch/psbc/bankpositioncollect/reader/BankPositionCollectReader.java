/*
 * 文件名：SubjectBalanceCollectReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.batch.psbc.bankpositioncollect.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dao.BankPositionCollectMapper;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.BooksetTodoDto;

/**
 * 银行账科目余额汇总批处理 reader
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankPositionCollectReader extends BaseReader<BooksetTodoDto>{
    
    /**
     *  科目余额汇总mapper
     */
    @Autowired
    private BankPositionCollectMapper mapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<BooksetTodoDto> createList() {
        // 获取科目余额汇总的任务代码
        // 根据参数查询对应待处理的账套
        List<BooksetTodoDto> listDto = mapper.selectBooksetTodo(this.getParams());
        return listDto;
    }

}
