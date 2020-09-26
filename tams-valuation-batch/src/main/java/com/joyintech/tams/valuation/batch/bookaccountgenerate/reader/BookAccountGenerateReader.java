/*
 * 文件名：BookAccountGenerateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.reader;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dao.BookAccountGenerateMapper;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountDetail;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateReadResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;

@Service
@Scope("prototype")
public class BookAccountGenerateReader extends BaseReader<BookAccountGenerateReadResult> {

    @Autowired
    private BookAccountGenerateMapper bookAccountGenerateMapper;

    @Override
    public List<BookAccountGenerateReadResult> createList() {
        return bookAccountGenerateMapper.selectData(this.getParams()).stream().collect(
            Collectors.groupingBy(BookAccountDetail::getBooksetId)).entrySet().stream().map(
                item -> {
                    BookAccountGenerateReadResult result = new BookAccountGenerateReadResult();
                    result.setBooksetId(item.getKey());
                    result.setBookDate((Date) this.getParams().get(BookConstant.BOOK_DATE));
                    result.setBookAccountDetailList(item.getValue());
                    return result;
                }).collect(Collectors.toList());
    }

}
