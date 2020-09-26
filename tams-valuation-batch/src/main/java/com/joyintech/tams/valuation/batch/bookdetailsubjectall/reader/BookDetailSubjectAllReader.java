/*
 * 文件名：BookDetailSubjectAllReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectall.reader;


import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.dto.BookDetailSubjectAllReadResult;
import com.joyintech.tams.valuation.batch.bookdetailsubjectall.service.BookDetailSubjectAllReaderService;

/**
 * 更新账务明细（历史表）科目余额批处理 - 读入
 * 
 * @author HuangjJian
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectAllReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectAllReader extends BaseReader<BookDetailSubjectAllReadResult> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private BookDetailSubjectAllReaderService bookDetailSubjectAllReaderService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<BookDetailSubjectAllReadResult> createList() {

        // 获取reader返回值
        if (ValDictCodeList.是否启用倒起息_启用.getValue().equals(CacheUtil.getSysParam(BookConstant.是否启用倒起息))) {
            List<BookDetailSubjectAllReadResult> resultList = bookDetailSubjectAllReaderService.execute(this.getParams());

//		logger.error("【调查】当前表余额更新Reader：[name = " + this.getBatchTask().getBeanName() + "| id = "
//            + this.getBatchTask().getTaskId() + "| pri = " + this.getBatchTask().getTaskPri()
//            + "| count = " + resultList.size() + "]");
            //
            return resultList;
        }
        return new ArrayList<BookDetailSubjectAllReadResult>();
	}
}
