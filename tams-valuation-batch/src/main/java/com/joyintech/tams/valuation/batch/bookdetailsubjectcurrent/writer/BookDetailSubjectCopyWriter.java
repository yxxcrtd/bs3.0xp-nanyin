/*
 * 文件名：BookDetailSubjectCurrentWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriter;

/**
 * 更新账务明细（当前表）科目余额批处理 数据库写入
 * 
 * @author chengzhong
 * @version 1.0 2018年6月1日
 * @see BookGenerateResultForCurrentCommonWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCopyWriter extends BaseWriter<NeedToWriteEntity> {
    
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;
    /**
     * 更新账务明细 历史数据 {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<BookDetailSubjectCopyResult> resultList = (List<BookDetailSubjectCopyResult>)items;

        List<ValBokBookdetailBatch> valBokBookdetailBatch = new ArrayList<>();
        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookDetailSubjectCopyResult bookGenerateResult : resultList) {
            valBokBookdetailBatch.addAll(bookGenerateResult.getListValBokBookdetailBatch());
            // if(bookGenerateResult.getListValBokBookdetailBatch().)
            // baseAccountWriterMapper.insertValBokBookdetailCurrent(bookGenerateResult.getListValBokBookdetailBatch());
        }
        // 更新账务明细 历史数据
        bookAccountGenLogic.insertValBokBookdetailCurrent(valBokBookdetailBatch);
    }

}
