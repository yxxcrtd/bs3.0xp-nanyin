/*
 * 文件名：DepoAutoDurDealGenerateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.depo.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.service.DepoAutoDurDealGenerateReaderService;

/**
 * 读入Reader
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see DepoAutoDurDealGenerateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepoAutoDurDealGenerateReader extends BaseReader<DepoAutoDurDealGenerateReaderResult> {

    /**
     * DepoAutoDurDealGenerateReaderService
     */
    @Autowired
    private DepoAutoDurDealGenerateReaderService readerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepoAutoDurDealGenerateReaderResult> createList() {
        return readerService.execute(this.getParams());
    }

}
