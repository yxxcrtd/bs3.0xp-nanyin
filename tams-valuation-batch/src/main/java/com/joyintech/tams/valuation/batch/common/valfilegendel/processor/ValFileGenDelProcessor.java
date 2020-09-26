/*
 * 文件名：ValBokBalanceProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.valfilegendel.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.valfilegendel.dto.ValFileGenDelProcessorDto;
import com.joyintech.tams.valuation.batch.common.valfilegendel.dto.ValFileGenDelReaderDto;

/**
 * 估值表生成
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月31日
 * @see ValFileGenDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValFileGenDelProcessor extends BaseProcessor<NeedOperateEntity, ValFileGenDelProcessorDto> {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ValFileGenDelProcessorDto process(NeedOperateEntity item)
        throws Exception {
        ValFileGenDelProcessorDto result = ((ValFileGenDelReaderDto) item).toResult();
        result.setProdId(result.getGenDate());
        return result;
    }

}
