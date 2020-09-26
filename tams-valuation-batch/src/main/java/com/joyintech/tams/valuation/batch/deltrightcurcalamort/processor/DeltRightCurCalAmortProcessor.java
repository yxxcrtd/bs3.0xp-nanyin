/*
 * 文件名：PlanCurrencyProAccountingProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.deltrightcurcalamort.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.deltrightcurcalamort.service.DeltRightCurCalAmortProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 计划类（货币型）计提账务处理-processor
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月24日
 * @see DeltRightCurCalAmortProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DeltRightCurCalAmortProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>{

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
    
    @Autowired
    private DeltRightCurCalAmortProcessorService deltRightCurCalAmortProcessorService;
    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items) throws Exception {
        //获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        //调用service方法进行逻辑逻辑
        BookGenerateResultWithBokSummary result = deltRightCurCalAmortProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokSummary();
        }
        
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }
}
