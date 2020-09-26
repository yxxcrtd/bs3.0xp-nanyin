/*
 * 文件名：ValBooksetDateUpdateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbooksetdateupdate.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.valbooksetdateupdate.dto.ValBooksetDateUpdateProcessorDto;
import com.joyintech.tams.valuation.batch.valbooksetdateupdate.dto.ValBooksetDateUpdateReaderDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年7月11日
 * @see ValBooksetDateUpdateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBooksetDateUpdateProcessor extends BaseProcessor<NeedOperateEntity, ValBooksetDateUpdateProcessorDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValBooksetDateUpdateProcessorDto process(NeedOperateEntity item)
        throws Exception {
        ValBooksetDateUpdateProcessorDto entity = new ValBooksetDateUpdateProcessorDto();
        entity.setBooksetIds(((ValBooksetDateUpdateReaderDto) item).getBooksetIds());
        entity.setProdId("ALL");
        return entity;
    }


}
