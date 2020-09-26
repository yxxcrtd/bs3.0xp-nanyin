/*
 * 文件名：ValNetConfirmDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valscheme.processor;

import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeDelResult;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeTodoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author weipeng
 * @version 1.0 2018年10月18日
 * @see ValSchemeDelProcessor
 * @since 1.0
 */
@Service
public class ValSchemeDelProcessor extends BaseProcessor<NeedOperateEntity, ValSchemeDelResult>{

    /**
     * {@inheritDoc}
     */
    @Override
    public ValSchemeDelResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        ValSchemeTodoDto acctEntity = (ValSchemeTodoDto)items;
        ValSchemeDelResult schemeDelResult = new ValSchemeDelResult();
        schemeDelResult.setProdId(acctEntity.getBooksetId());
        schemeDelResult.setFileType(DictCodeList.文件类型_估值表.getValue());
        schemeDelResult.setBookDate(acctEntity.getBookDate());
        return schemeDelResult;
    }

}
