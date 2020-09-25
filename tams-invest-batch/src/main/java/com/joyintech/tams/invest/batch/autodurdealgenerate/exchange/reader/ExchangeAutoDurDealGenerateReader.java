/*
 * 文件名：ExchangeAutoDurDealGenerateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service.ExchangeAutoDurDealGenerateReaderService;

/**
 * 外汇远期掉期到期交易自动生成读入Reader
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExchangeAutoDurDealGenerateReader extends BaseReader<ExchangeAutoDurDealGenerateReaderResult> {

    /**
     * ExchangeAutoDurDealGenerateReaderService
     */
    @Autowired
    private ExchangeAutoDurDealGenerateReaderService readerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExchangeAutoDurDealGenerateReaderResult> createList() {
        return readerService.execute(this.getParams());
    }

}
