/*
 * 文件名：bondDebitCreditCalBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.seclenddrawbookdetail.processor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResultWithBokSummary;
import com.joyintech.tams.valuation.batch.common.drawbaseinfoselectcom.dto.DrawBaseInfoSelectComResult;
import com.joyintech.tams.valuation.batch.seclenddrawbookdetail.service.SecLendDrawBookDetailService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 债券借贷计提账务处理 processor
 * 
 * @author zhangwei
 * @version 1.0 2018年10月20日
 * @see bondDebitCreditCalBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecLendDrawBookDetailProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private SecLendDrawBookDetailService secLendDrawBookDetailService;

    @Override
    public BookGenerateResultWithBokSummary process(NeedOperateEntity item)
        throws Exception {

        // 获取reader方法的返回值
        DrawBaseInfoSelectComResult acctEntity = (DrawBaseInfoSelectComResult)item;

        Map<String, Object> thisParams = Maps.newHashMap();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        BookGenerateResultWithBokSummary result = secLendDrawBookDetailService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new BookGenerateResultWithBokSummary();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }

}
