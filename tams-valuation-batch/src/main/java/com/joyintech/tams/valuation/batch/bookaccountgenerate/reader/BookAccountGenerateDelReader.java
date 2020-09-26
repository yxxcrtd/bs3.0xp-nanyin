/*
 * 文件名：BookAccountGenerateDelReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.bookaccountgenerate.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateDelEntity;
import com.joyintech.tams.valuation.common.constants.BookConstant;

@Service
@Scope("prototype")
public class BookAccountGenerateDelReader extends BaseReader<BookAccountGenerateDelEntity> {

    @Override
    public List<BookAccountGenerateDelEntity> createList() {
        Date bookDate = (Date) this.getParams().get(BookConstant.BOOK_DATE);
        String taskId = this.getBatchExecuteInfo().getTaskId();
        ArrayList<BookAccountGenerateDelEntity> list = new ArrayList<BookAccountGenerateDelEntity>();
        list.add(new BookAccountGenerateDelEntity(bookDate, taskId));
        return list;
    }

}
