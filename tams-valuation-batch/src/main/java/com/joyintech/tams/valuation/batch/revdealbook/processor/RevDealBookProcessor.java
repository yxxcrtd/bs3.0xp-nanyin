/*
 * 文件名：BookDetailCompareIntoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.revdealbook.dto.RevDealBookReaderDto;
import com.joyintech.tams.valuation.batch.revdealbook.service.RevDealBookProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 账务明细比较合并批处理 - processor
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see RevDealBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RevDealBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private RevDealBookProcessorService revDealBookProcessorService;

    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        RevDealBookReaderDto acctEntity = (RevDealBookReaderDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);


        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = revDealBookProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResult();
        }
        result.setProdId("撤销交易处理");
        return result;
    }
}
