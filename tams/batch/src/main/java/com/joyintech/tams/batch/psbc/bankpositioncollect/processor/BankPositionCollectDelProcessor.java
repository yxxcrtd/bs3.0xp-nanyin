/*
 * 文件名：SubjectBalanceCollectProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.batch.psbc.bankpositioncollect.processor;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dto.BankPositionCollectDelProcessorDto;
import com.joyintech.tams.batch.psbc.bankpositioncollect.dto.BankPositionCollectDelReaderDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankPositionCollectDelProcessor extends BaseProcessor<NeedOperateEntity, BankPositionCollectDelProcessorDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BankPositionCollectDelProcessorDto process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        BankPositionCollectDelProcessorDto result = ((BankPositionCollectDelReaderDto)items).toResult();
        result.setProdId(DateUtil.format((Date) this.getParams().get(BookConstant.BOOK_DATE)));
        return result;
    }

}
