/*
 * 文件名：RevDealBookReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.revdealbook.dto.RevDealBookReaderDto;
import com.joyintech.tams.valuation.batch.revdealbook.service.RevDealBookReaderService;

/**
 * 撤销交易批处理 - 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see RevDealBookReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RevDealBookReader extends BaseReader<RevDealBookReaderDto> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private RevDealBookReaderService revDealBookReaderReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<RevDealBookReaderDto> createList() {

        // 获取reader返回值
        List<RevDealBookReaderDto> resultList = revDealBookReaderReaderService.execute(this.getParams());

        return resultList;
    }

}
