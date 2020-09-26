/*
 * 文件名：ValFileUploadProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年12月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valfileupload.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.valfileupload.dto.ValFileUploadProcessorDto;
import com.joyintech.tams.valuation.batch.valfileupload.dto.ValFileUploadReaderDto;

/**
 * 批处理文件上传Processor
 * @author weipeng
 * @version 1.0 2018年12月19日
 * @see ValFileUploadProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValFileUploadProcessor extends BaseProcessor<NeedOperateEntity, ValFileUploadProcessorDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValFileUploadProcessorDto process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        ValFileUploadProcessorDto result = ((ValFileUploadReaderDto)item).toValFileUploadProcessorDto();
        result.setProdId(DateUtil.format(result.getGenDate(), "yyyyMMdd"));
        return result;
    }

}
