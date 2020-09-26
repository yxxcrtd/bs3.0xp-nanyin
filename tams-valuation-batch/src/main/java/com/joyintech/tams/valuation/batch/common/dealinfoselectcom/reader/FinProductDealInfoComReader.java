/*
 * 文件名：FinProductDealInfoComReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.dealinfoselectcom.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.service.FinProductDealInfoComReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 拆借交易账务明细批处理读入
 *
 * @author wudebao
 * @version 1.0 2018年4月25日
 * @see FinProductDealInfoComReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FinProductDealInfoComReader extends BaseReader<FinProductDealInfoResult> {
    
    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 存款类资产理财账读入Service
     */
    @Autowired
    private FinProductDealInfoComReaderService finProductDealInfoComRederService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<FinProductDealInfoResult> createList() {

        // 变量定义
        List<FinProductDealInfoResult> listFinProductDealInfoResult = new ArrayList<FinProductDealInfoResult>();

        Map<String, Object> serviceParamMap = new HashMap<String, Object>();
        serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
        serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
        serviceParamMap.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }
        // 获取reader返回值
        listFinProductDealInfoResult = finProductDealInfoComRederService.execute(serviceParamMap);
//        
//        logger.error("【调查】交易Reader：[name = " + this.getBatchTask().getBeanName() + "| id = "
//            + this.getBatchTask().getTaskId() + "| pri = " + this.getBatchTask().getTaskPri()
//            + "| count = " + listFinProductDealInfoResult.size() + "]");
//        
        // 返回处理结果
        return listFinProductDealInfoResult;

    }

}
