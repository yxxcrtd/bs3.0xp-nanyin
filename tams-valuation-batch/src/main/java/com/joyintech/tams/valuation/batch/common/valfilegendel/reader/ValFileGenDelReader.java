/*
 * 文件名：ValBokBalanceReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.valfilegendel.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.common.valfilegendel.dto.ValFileGenDelReaderDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.constant.DictCode;

/**
 * 估值表生成 reader
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValFileGenDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValFileGenDelReader extends BaseReader<ValFileGenDelReaderDto> {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValFileGenDelReaderDto> createList() {
        List<ValFileGenDelReaderDto> listDto = new ArrayList<ValFileGenDelReaderDto>();
        String fileType = (String)this.getParams().get(DictCode.文件类型.getValue());
        String taskId = this.getBatchExecuteInfo().getTaskId();
        String genDate = DateUtil.format((Date) this.getParams().get(BookConstant.BOOK_DATE));
        listDto.add(new ValFileGenDelReaderDto(taskId, genDate, fileType));
        return listDto;
    }

}
