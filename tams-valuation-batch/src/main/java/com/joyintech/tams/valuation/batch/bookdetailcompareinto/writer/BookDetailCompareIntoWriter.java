/*
 * 文件名：BookDetailCompareIntoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriter;

/**
 * 明细记账比较批处理 数据库写入
 * 
 * @author wangwei1
 * @version 1.0 2018年6月8日
 * @see BookGenerateResultForCurrentCommonWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailCompareIntoWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * reader方法的 Service
     */
//    @Autowired
//    private BookDetailCompareIntoMapper bookDetailCompareIntoMapper;

    @Autowired 
    private BookAccountGenLogic bookAccountGenLogic;
    /**
     * 明细记账比较批处理
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<BookGenerateResult> resultList = (List<BookGenerateResult>)items;

        List<ValBokBookdetailBatch> valBokBookdetailBatch = new ArrayList<ValBokBookdetailBatch>();
        for (BookGenerateResult bookGenerateResult : resultList) {
            valBokBookdetailBatch.addAll(bookGenerateResult.getValBokBookdetailBatch());
        }
        bookAccountGenLogic.insertValBokBookdetailAll(valBokBookdetailBatch);
//        for (ValBokBookdetailBatch model : valBokBookdetailBatch) {
//            bookDetailCompareIntoMapper.insertComparebookDetail(model);
//            
//        }

    }

}
