/*
 * 文件名：BookDetailCompareIntoReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto.BokBokBookdetailCurrent;
import com.joyintech.tams.valuation.batch.bookdetailcompareinto.service.BookDetailCompareIntoReaderService;

/**
 * 账务明细比较合并批处理 - 读入
 * 
 * @author yangjian
 * @version 1.0 2018年5月7日
 * @see BookDetailCompareIntoReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailCompareIntoReader extends BaseReader<BokBokBookdetailCurrent> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailCompareIntoReaderService bookDetailCompareIntoReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<BokBokBookdetailCurrent> createList() {

        // 获取reader返回值
        List<BokBokBookdetailCurrent> resultList = bookDetailCompareIntoReaderService.execute(this.getParams());

        return resultList;
    }

}
