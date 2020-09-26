/*
 * 文件名：prdValuationFeeBeforeReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.dto.BokstatbokpositionReaderDto;
import com.joyintech.tams.valuation.batch.common.prdvaluationfeecommon.service.PrdValuationFeeCommonReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 产品估值计算公用读取 - 针对(产品估值计算-费前)和(产品估值计算-费后)两个批
 * 
 * @author yangjian
 * @version 1.0 2018年8月13日
 * @see PrdValuationFeeCommonReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdValuationFeeCommonReader extends BaseReader<BokstatbokpositionReaderDto> {

    @Autowired
    private PrdValuationFeeCommonReaderService prdValuationFeeCommonReaderService;

    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    @Override
    public List<BokstatbokpositionReaderDto> createList() {
        // 变量定义
        List<BokstatbokpositionReaderDto> BeforeReaderResultDtoList = new ArrayList<BokstatbokpositionReaderDto>();

        
        Map<String, Object> serviceParamMap = new HashMap<String, Object>();
        serviceParamMap.put("BATCHINFO", this.getBatchExecuteInfo());
        serviceParamMap.put("BATCHDEL", this.getBatchExecuteDetail());
        serviceParamMap.put("BATCHTASK", this.getBatchTask());
        Map<String, String> dimValueMap = bookCommonLogic.getDimensionValue(this.getBatchExecuteInfo());
        serviceParamMap.put(BookConstant.BOOKSET_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOKSET_DATE)));
        serviceParamMap.put(BookConstant.BOOK_DATE, DateUtil.parse(dimValueMap.get(BookConstant.BOOK_DATE)));
        serviceParamMap.put(BookConstant.HAPPEN_DATE, DateUtil.parse(dimValueMap.get(BookConstant.HAPPEN_DATE)));
        serviceParamMap.put("seqNo", this.getBatchExecuteDetail().getSeqNo());
        serviceParamMap.put("taskId", this.getBatchExecuteInfo().getTaskId());
        serviceParamMap.put(BookConstant.IS_SUB_BOOKSET, dimValueMap.get(BookConstant.IS_SUB_BOOKSET));
        this.getParams().putAll(serviceParamMap);
        // 获取reader返回值
        BeforeReaderResultDtoList = prdValuationFeeCommonReaderService.execute(serviceParamMap);

        // 返回处理结果
        return BeforeReaderResultDtoList;
    }

}
