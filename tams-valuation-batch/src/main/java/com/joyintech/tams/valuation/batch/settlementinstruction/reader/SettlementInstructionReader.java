/*
 * 文件名：SettlementInstructionReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.settlementinstruction.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.settlementinstruction.dto.SettlementInstructionResult;
import com.joyintech.tams.valuation.batch.settlementinstruction.service.SettlementInstructionService;

/**
 * 科目余额汇总批处理 reader
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see SettlementInstructionReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SettlementInstructionReader extends BaseReader<SettlementInstructionResult>{
    
    /**
     *  settlementInstructionService
     */
    @Autowired
    private SettlementInstructionService  settlementInstructionService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<SettlementInstructionResult> createList() {
        //变量定义        
        List<SettlementInstructionResult> listSettlementInstructionResult =  new ArrayList<SettlementInstructionResult>();
        
        //获取reader返回值
        listSettlementInstructionResult = settlementInstructionService.execute(this.getParams());
        
        return listSettlementInstructionResult;
       
    }

}
