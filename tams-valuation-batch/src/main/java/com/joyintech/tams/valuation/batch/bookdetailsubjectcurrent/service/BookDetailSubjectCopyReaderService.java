/*
 * 文件名：BookDetailSubjectCopyReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dao.BookDetailSubjectCurrentMapper;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyReadResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;

/**
 * 更新账务明细（当前表表）科目余额批处理 - reader相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月28日
 * @see BookDetailSubjectCopyReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCopyReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectCurrentMapper bookDetailSubjectCurrentMapper;

    /**
     * 获取数据库数据
     * 
     * @return List<BookDetailSubjectCurrentReadResult>
     * @see
     * @since 1.0
     */

    public List<BookDetailSubjectCopyReadResult> execute(Map<String, Object> param) {

        List<BookDetailSubjectCopyReadResult> list = new ArrayList<BookDetailSubjectCopyReadResult>();

        BookDetailSubjectCopyReadResult bookDetailSubjectCopyReadResult = new BookDetailSubjectCopyReadResult();
        // 查询条件
//        List<ValBokBookdetailBatch> bookList = bookDetailSubjectCurrentMapper.searchTodayTrade(param);
        List<ValBokBookdetailBatch> bookList = new ArrayList<>();
        if (bookList != null && !bookList.isEmpty()) {
            List<ValBokBookdetailBatch> cruList = bookDetailSubjectCurrentMapper.selectValBokBookdetailCurrent(
                bookList);
            bookDetailSubjectCopyReadResult.setListValBokBookdetailBatch(cruList);
            list.add(bookDetailSubjectCopyReadResult);
        }

        // 定义reader返回list
        return list;
    }

}
