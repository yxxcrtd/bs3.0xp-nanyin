/*
 * 文件名：BookAccountGenerateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.writer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dao.BookAccountGenerateMapper;
import com.joyintech.tams.valuation.batch.bookaccountgenerate.dto.BookAccountGenerateResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.common.base.dao.BokVouchDetailMapper;
import com.joyintech.tams.common.base.dao.BokVouchMainMapper;
import com.joyintech.tams.common.base.dao.BokVouchRelMapper;

@Service
@Scope("prototype")
public class BookAccountGenerateWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private BookAccountGenerateMapper bookAccountGenerateMapper;

    @Autowired
    private BokVouchMainMapper valVouchMainMapper;

    @Autowired
    private BokVouchDetailMapper valVouchDetailMapper;

    @Autowired
    private BokVouchRelMapper valVouchRelMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        Date bookDate = (Date)this.getParams().get(BookConstant.BOOK_DATE);
        String bookDateStr = DateUtil.format(bookDate);
        String yearStr = DateUtil.getYearStr(bookDateStr);
        String monthStr = String.valueOf(DateUtil.getMonth(bookDateStr));
      //20181123  记账凭证修改   leipeng  start
        //        if (items.stream().map(item -> (BookAccountGenerateResult)item).filter(
//            item -> item.isUpdateHistory()).count() > 0) {
//            bookAccountGenerateMapper.updateHistory(yearStr, monthStr);
//        }
      //20181123  记账凭证修改   leipeng  end
        for (NeedToWriteEntity needToWriteEntity : items) {
            BookAccountGenerateResult result = (BookAccountGenerateResult)needToWriteEntity;
            result.getValVouchMain().forEach(item -> {
                valVouchMainMapper.insert(item);
            });
            result.getValVouchDetail().forEach(item -> {
                valVouchDetailMapper.insert(item);
            });
//            result.getValVouchRel().forEach(item -> {
//                valVouchRelMapper.insert(item);
//            });
        }
    }

}
