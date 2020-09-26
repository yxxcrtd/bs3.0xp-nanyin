/*
 * 文件名：ValBooksetDateUpdateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbooksetdateupdate.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.valbooksetdateupdate.dto.ValBooksetDateUpdateReaderDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年7月11日
 * @see ValBooksetDateUpdateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBooksetDateUpdateReader extends BaseReader<ValBooksetDateUpdateReaderDto> {

    /**
     * 记账引擎
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValBooksetDateUpdateReaderDto> createList() {
        List<ValBooksetDateUpdateReaderDto> list= new ArrayList<>();
        // 根据任务代码，获取跑批范围信息list（批处理待处理任务表）
        List<BokBatchTaskTodo> listBatchTaskTodo = bookCommonLogic.getTaskTodoScopeInfo(this.getParams());
        Set<String> booksetIds = listBatchTaskTodo.stream().collect(
            Collectors.groupingBy(x -> x.getBooksetId())).keySet();
        this.getParams().put("booksetIds", booksetIds);
        list.add(new ValBooksetDateUpdateReaderDto(new ArrayList<>(booksetIds)));
        return list;
    }

}
