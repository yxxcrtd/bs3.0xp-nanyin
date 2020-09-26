/*
 * 文件名：BookGenerateResultCommonWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.bookgenerateresult;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookAccountGenLogic;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 记账凭证返回实体类 - 公用方法写入 (针对账务明细历史表) 包括 插入【记账凭证主表信息】 和 【插入记账凭证明细表信息(历史表)】
 * 
 * @author yangjian
 * @version 1.0 2018年5月9日
 * @see BookGenerateResultForCurrentCommonWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookGenerateResultForAllCommonWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * 记账凭证生成类
     */
    @Autowired
    private BookAccountGenLogic bookAccountGenLogic;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 记账凭证数据写入数据库(针对账务明细历史表) {@inheritDoc}
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
//            logger.error(
//                "【调查】历史表Writer:[name = " + task.getBeanName() + "| id = " + task.getTaskId()
//                        + "| pri = " + task.getTaskPri() + "| count = " + resultList.size() + "]");
//        }
//       
//        
        // 循环记账凭证list，对每个记账凭证进行操作
        for (BookGenerateResult bookGenerateResult : resultList) {
            // 插入记账凭证主表信息 和 插入记账凭证明细表信息
            bookAccountGenLogic.insertKeepAccountForAll(bookGenerateResult);
        }

    }

}
