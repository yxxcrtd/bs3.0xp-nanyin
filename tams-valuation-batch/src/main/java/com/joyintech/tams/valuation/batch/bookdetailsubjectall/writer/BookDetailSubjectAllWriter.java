/*
 * 文件名：BookDetailSubjectAllWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.writer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.service.BookDetailSubjectAllWriterService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.batch.common.bookgenerateresult.BookGenerateResultForCurrentCommonWriter;

/**
 * 更新账务明细（历史表）科目余额批处理 数据库写入
 * 
 * @author huangjian
 * @version 1.0 2018年6月1日
 * @see BookGenerateResultForCurrentCommonWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectAllWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookDetailSubjectAllWriterService bookDetailSubjectAllWriterService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 更新账务明细 历史数据 {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回组合的list值 - 记账凭证list
        List<BookGenerateResult> resultList = (List<BookGenerateResult>)items;

//        Object t = this.getParams() == null ? null: this.getParams().get("BATCHTASK");
//        if (t != null) {
//            SysBatchTask task = (SysBatchTask)this.getParams().get("BATCHTASK");
//            logger.info(
//                "【调查】历史表余额更新Writer:[name = " + task.getBeanName() + "| id = " + task.getTaskId()
//                        + "| pri = " + task.getTaskPri() + "| count = " + resultList.size() + "]");
//        }
//        
        // 循环记账凭证list，对每个记账凭证进行操作
//        for (BookGenerateResult bookGenerateResult : resultList) {
//            // 更新账务明细 历史数据
//            bookDetailSubjectAllWriterService.updateValBokBookDetailAll(bookGenerateResult);
//        }
        List<ValBokBookdetailBatch> valBokBookdetailBatchList = new ArrayList<>();
        for (BookGenerateResult bookGenerateResult : resultList) {
            if (bookGenerateResult.getValBokBookdetailBatch() != null) {
                valBokBookdetailBatchList.addAll(bookGenerateResult.getValBokBookdetailBatch());
            }
        }
        // 更新账务明细 历史数据
        bookDetailSubjectAllWriterService.updateValBokBookDetailAllBatch(
            valBokBookdetailBatchList);
    }

}
