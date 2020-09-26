/*
 * 文件名：BookDetailSubjectCurrentProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.processor;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyReadResult;
import com.joyintech.tams.valuation.batch.bookdetailsubjectcurrent.dto.BookDetailSubjectCopyResult;
import com.joyintech.tams.valuation.batch.common.base.entity.extcommon.ValBokBookdetailBatch;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 更新账务明细（当前表）科目余额批处理- processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月1日
 * @see BookDetailSubjectCopyProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BookDetailSubjectCopyProcessor extends BaseProcessor<NeedOperateEntity, BookDetailSubjectCopyResult> {

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;
    
    @Override
    public BookDetailSubjectCopyResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        BookDetailSubjectCopyReadResult acctEntity = (BookDetailSubjectCopyReadResult)items;
        
        // 调用service方法进行逻辑逻辑
        BookDetailSubjectCopyResult result = new BookDetailSubjectCopyResult();;
        for (ValBokBookdetailBatch vo : acctEntity.getListValBokBookdetailBatch()) {
            // 流水号
            vo.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
            // 处理序列
            vo.setBookOrder(new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
            // 创建时间 当前时间
            vo.setCreateTime(new Date());
            // 更新时间 当前时间
            vo.setUpdateTime(new Date());
            // 入账日
            vo.setBookDate((Date)this.getParams().get(BookConstant.BOOK_DATE));
            vo.setBooksetDate((Date)this.getParams().get(BookConstant.BOOKSET_DATE));
        }
        result.setListValBokBookdetailBatch(acctEntity.getListValBokBookdetailBatch());
        result.setProdId(" ");

        return result;
    }
}
