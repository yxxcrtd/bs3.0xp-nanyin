/*
 * 文件名：ValBokBalanceReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbokbalance.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.valbokbalance.dao.ValBoKBanlanceMapper;
import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBatchTaskTodoDto;

/**
 * 科目余额汇总批处理 reader
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValBokBalanceReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBokBalanceReader extends BaseReader<ValBatchTaskTodoDto>{
    
    /**
     *  余额表生成处理mapper
     */
    @Autowired
    private ValBoKBanlanceMapper mapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValBatchTaskTodoDto> createList() {
        //根据参数查询对应待处理的账套
        List<ValBatchTaskTodoDto> listDto = mapper.selectValBatchTaskTodo(getParams());
        return listDto;
    }

}
