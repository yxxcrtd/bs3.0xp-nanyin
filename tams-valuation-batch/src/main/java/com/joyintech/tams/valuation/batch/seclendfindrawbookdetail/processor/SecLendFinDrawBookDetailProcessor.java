/*
 * 文件名：SecLendFinDrawBookDetailProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.seclendfindrawbookdetail.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.seclendfindrawbookdetail.service.SecLendFinDrawBookDetailService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 债券借贷标的债券计提处理 processor
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年10月25日
 * @see SecLendFinDrawBookDetailProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecLendFinDrawBookDetailProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private SecLendFinDrawBookDetailService secLendFinDrawBookDetailService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult) items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResult serviceDealResult = secLendFinDrawBookDetailService.execute(thisParams);

        // 当为null时候，给予初始化
        if (serviceDealResult == null) {
            serviceDealResult = new BookGenerateResult();
        }

        serviceDealResult.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return serviceDealResult;
    }

}
