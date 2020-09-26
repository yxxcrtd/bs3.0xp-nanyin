/*
 * 文件名：BookDetailSubjectCurrentReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.reader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCurrentReadResult;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.service.BookDetailSubjectCurrentReaderService;

/**
 * 更新账务明细（当前表）科目余额批处理 - 读入
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectCurrentReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCurrentReader extends BaseReader<BookDetailSubjectCurrentReadResult> {
    
    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectCurrentReaderService bookDetailSubjectCurrentReaderService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<BookDetailSubjectCurrentReadResult> createList() {

        // 获取reader返回值
    
        List<BookDetailSubjectCurrentReadResult> resultList = bookDetailSubjectCurrentReaderService.execute(this.getParams());
        
//        logger.error("【调查】当前表余额更新Reader：[name = " + this.getBatchTask().getBeanName() + "| id = "
//            + this.getBatchTask().getTaskId() + "| pri = " + this.getBatchTask().getTaskPri()
//            + "| count = " + resultList.size() + "]");
//        
        return resultList;
    }
}
