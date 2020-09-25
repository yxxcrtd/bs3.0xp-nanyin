/*
 * 文件名：BondAutoDurDealGenerateReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.service.BondAutoDurDealGenerateReaderService;

/**
 * 读入Reader
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BondAutoDurDealGenerateReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BondAutoDurDealGenerateReader extends BaseReader<BondAutoDurDealGenerateReaderResult> {

    /**
     * BondAutoDurDealGenerateReaderService
     */
    @Autowired
    private BondAutoDurDealGenerateReaderService readerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BondAutoDurDealGenerateReaderResult> createList() {
        return readerService.execute(this.getParams());
    }

}
