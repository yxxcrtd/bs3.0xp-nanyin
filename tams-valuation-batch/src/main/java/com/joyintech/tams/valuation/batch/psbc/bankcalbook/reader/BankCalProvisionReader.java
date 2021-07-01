/*
 * 文件名：BankCalProvisionReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankcalbook.reader;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BankCalBookComResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.psbc.bankcalbook.service.BankCalProvisionReaderService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行账计提账务处理
 * 
 * @author zhangwei
 * @version 1.0 Apr 10, 2019
 * @see BankCalProvisionReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankCalProvisionReader extends BaseReader<BankCalBookComResult> {

    /**
     * 记账公共类
     */
    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    @Autowired
    private BankCalProvisionReaderService bankCalProvisionReaderService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<BankCalBookComResult> createList() {
       
        // 变量定义
        List<BankCalBookComResult> listResult = Lists.newArrayList();

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
        if (this.getParams() != null) {
            this.getParams().putAll(serviceParamMap);
        }
        // 获取reader返回值
        listResult = bankCalProvisionReaderService.execute(
            serviceParamMap);
        return listResult;
    }

}
