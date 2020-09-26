/*
 * 文件名：DemandDepositTransBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.demanddeposittransbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.dealinfoselectcom.dto.FinProductDealInfoResult;
import com.joyintech.tams.valuation.batch.demanddeposittransbook.service.DemandDepositTransBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 活期存款交易账务处理-processor
 * 
 * @author chendijun
 * @version 1.0 2018年8月2日
 * @see DemandDepositTransBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DemandDepositTransBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * demandDepositTransBookService
     * processor 的service
     */
    @Autowired
    private DemandDepositTransBookService demandDepositTransBookService;
    
    /**
     * 传入参数
     */
    private Map<String, Object> param = new HashMap<String, Object>();

    /**
     * 设置 param
     * 
     * @param param param
     */
    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    
    @Override
    public BookGenerateResult process(NeedOperateEntity item)
        throws Exception {
        
        //获取reader返回值
        FinProductDealInfoResult acctEntity = (FinProductDealInfoResult) item;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        
        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = demandDepositTransBookService.execute(thisParams);
        
        //为空初始化
        if(result == null) {
            result = new BookGenerateResult();
        }
        
        //
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());
        return result;
    }

}
