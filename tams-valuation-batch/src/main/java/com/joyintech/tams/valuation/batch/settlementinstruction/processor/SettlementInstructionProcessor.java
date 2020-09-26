/*
 * 文件名：SettlementInstructionProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.settlementinstruction.processor;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.settlementinstruction.dto.SettlementInstructionResult;
import com.joyintech.tams.valuation.batch.settlementinstruction.service.SettlementInstructionService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2018年5月31日
 * @see SettlementInstructionProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SettlementInstructionProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {
	 /**
     * settlementInstructionService
     */
    @Autowired
	private SettlementInstructionService settlementInstructionService;
    /**
     * {@inheritDoc}
     */
    
    @Override
    public BookGenerateResult process(NeedOperateEntity items) throws Exception {
        
        //接受Reader返回的数据
        SettlementInstructionResult acctEntity = (SettlementInstructionResult)items;
        
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        
        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = settlementInstructionService.executeProcessor(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new BookGenerateResult();
        }
        
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }

}
