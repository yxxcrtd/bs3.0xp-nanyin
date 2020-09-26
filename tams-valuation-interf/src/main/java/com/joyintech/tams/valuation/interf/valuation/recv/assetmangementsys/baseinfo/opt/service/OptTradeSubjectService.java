/*
 * 文件名：OptTradeSubjectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：TanXuan
 * 修改时间：2020年01月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.opt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.OptTradeSubjectRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.opt.OptTradeSubjectRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.opt.dto.OptTradeSubjectProcessorResult;

/**
 * 期权交易标的信息
 * 
 * @author TanXuan
 * @version 1.0 2020年01月08日
 * @see OptTradeSubjectService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OptTradeSubjectService {
    /**
     * 期权交易标的信息
     */
    @Autowired
    private OptTradeSubjectRecService optTradeSubjectRecService;
    
    public OptTradeSubjectProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
    	OptTradeSubjectRecProcessorRecCommonResult resultCommon = new OptTradeSubjectRecProcessorRecCommonResult();
        
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        if (TempConstant.场外期权标的信息.equals(acctEntity.getTmpEName())) {
            resultCommon = optTradeSubjectRecService.execute(templateSourceFlgDTO,
                    acctEntity);
        }
        return new OptTradeSubjectProcessorResult(resultCommon);
    }
}
