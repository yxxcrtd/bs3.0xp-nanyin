/*
 * 文件名：BondAutoDurDealGenerateDelReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateReaderResult;

/**
 * 删除处理Reader
 *
 * @author etliu
 * @version 1.0 2018年12月13日
 * @see BondAutoDurDealGenerateDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BondAutoDurDealGenerateDelReader extends BaseReader<BondAutoDurDealGenerateReaderResult> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BondAutoDurDealGenerateReaderResult> createList() {
        BondAutoDurDealGenerateReaderResult readerResult = new BondAutoDurDealGenerateReaderResult();
        List<BondAutoDurDealGenerateReaderResult> resultList = new ArrayList<BondAutoDurDealGenerateReaderResult>();
        resultList.add(readerResult);

        return resultList;
    }

}
