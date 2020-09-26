/*
 * 文件名：MoneyTaAutoCalReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.MoneyTaAutoCalResult;
import com.joyintech.tams.valuation.batch.moneytaautocal.service.MoneyTaAutoCalReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalReader extends BaseReader<MoneyTaAutoCalResult> {

    @Autowired
    private BookCommonLogic bookCommonLogic;

    /**
     * 货币型产品自动结转处理Service
     */
    @Autowired
    private MoneyTaAutoCalReaderService moneyTaAutoCalReaderService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<MoneyTaAutoCalResult> createList() {
        // 变量定义
        List<MoneyTaAutoCalResult> listResult = new ArrayList<>();

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
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }

        // 获取reader返回值
        listResult = moneyTaAutoCalReaderService.execute(serviceParamMap);

        // 返回处理结果
        return listResult;
    }

}
