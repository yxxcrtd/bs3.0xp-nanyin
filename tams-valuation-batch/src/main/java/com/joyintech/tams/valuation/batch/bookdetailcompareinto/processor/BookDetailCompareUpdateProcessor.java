/*
 * 文件名：BookDetailCompareIntoProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailcompareinto.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;

/**
 * 账务明细比较合并批处理 - processor
 * 
 * @author wangwei1
 * @version 1.0 2018年6月7日
 * @see BookDetailCompareUpdateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailCompareUpdateProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
//        BokBokBookdetailCurrent acctEntity = (BokBokBookdetailCurrent)items;

//        Map<String, Object> thisParams = new HashMap<>();
//        thisParams.putAll(this.getParams());
//        // 将返回值塞入参数中
//        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);


        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = new BookGenerateResult();

        result.setProdId("账务明细比较批处理余额清空");
        return result;
    }
}
