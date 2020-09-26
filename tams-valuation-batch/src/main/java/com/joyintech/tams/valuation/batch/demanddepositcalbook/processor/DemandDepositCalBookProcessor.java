/*
 * 文件名：DemandDepositCalBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.demanddepositcalbook.processor;

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
import com.joyintech.tams.valuation.batch.demanddepositcalbook.service.DemandDepositCalBookService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 活期存款计提 processor
 * 
 * @author chendijun
 * @version 1.0 2018年8月2日
 * @see DemandDepositCalBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DemandDepositCalBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult>{
    
    /**
     * 服务于processor的server
     */
    @Autowired
    private DemandDepositCalBookService demandDepositCalBookService;

    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity items)
        throws Exception {
        //获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) items;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        //调用service方法进行逻辑逻辑
        BookGenerateResultWithBokSummary result = demandDepositCalBookService.execute(thisParams);
        
      //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResultWithBokSummary();
        }
        
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());
        
        return result;
    }

}
