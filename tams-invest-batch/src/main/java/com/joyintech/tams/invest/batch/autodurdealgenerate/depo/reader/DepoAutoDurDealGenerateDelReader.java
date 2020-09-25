/*
 * 文件名：DepoAutoDurDealGenerateDelReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.depo.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateReaderResult;

/**
 * 删除处理Reader
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see DepoAutoDurDealGenerateDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepoAutoDurDealGenerateDelReader extends BaseReader<DepoAutoDurDealGenerateReaderResult> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepoAutoDurDealGenerateReaderResult> createList() {
        DepoAutoDurDealGenerateReaderResult readerResult = new DepoAutoDurDealGenerateReaderResult();
        List<DepoAutoDurDealGenerateReaderResult> resultList = new ArrayList<DepoAutoDurDealGenerateReaderResult>();
        resultList.add(readerResult);

        return resultList;
    }

}
