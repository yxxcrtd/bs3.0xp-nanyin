/*
 * 文件名：SubjectBalanceCollectProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.batch.psbc.bankpositioncollect.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dto.BankPositionGenerateResult;
import com.joyintech.tams.batch.psbc.bankpositioncollect.service.BankPositionCollectProcessorService;
import com.joyintech.tams.valuation.batch.subjectbalancecollect.dto.BooksetTodoDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankPositionCollectProcessor extends BaseProcessor<NeedOperateEntity, BankPositionGenerateResult> {
    
    /**
     * processor方法的 Service
     */
    @Autowired
    private BankPositionCollectProcessorService bankPositionCollectProcessorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BankPositionGenerateResult process(NeedOperateEntity items) throws Exception {
        // 获取reader方法的返回值
        BooksetTodoDto acctEntity = (BooksetTodoDto) items;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
        BankPositionGenerateResult result= bankPositionCollectProcessorService.execute(thisParams);
        //当为null时候，给予初始化
        if(result == null){
            result = new BankPositionGenerateResult();
        }
        return result;
    }

}
