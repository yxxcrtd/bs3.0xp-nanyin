/*
 * 文件名：BookAccountGenerateDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateDelEntity;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateDelResult;

@Service
@Scope("prototype")
public class BookAccountGenerateDelProcessor extends BaseProcessor<NeedOperateEntity, BookAccountGenerateDelResult> {

    @Override
    public BookAccountGenerateDelResult process(NeedOperateEntity item)
        throws Exception {
        BookAccountGenerateDelResult result = ((BookAccountGenerateDelEntity)item).toResult();
        result.setProdId(DateUtil.format(result.getBookDate()));
        return result;
    }

}
