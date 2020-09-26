/*
 * 文件名：BookDetailSubjectCopyReader.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyReadResult;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.service.BookDetailSubjectCopyReaderService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;

/**
 * 更新账务明细（当前表）科目余额批处理 - 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年6月28日
 * @see BookDetailSubjectCopyReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCopyReader extends BaseReader<BookDetailSubjectCopyReadResult> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectCopyReaderService bookDetailSubjectCopyReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<BookDetailSubjectCopyReadResult> createList() {

        // 获取reader返回值
    
        List<BookDetailSubjectCopyReadResult> resultList = bookDetailSubjectCopyReaderService.execute(this.getParams());
        
        return resultList;
    }
}
