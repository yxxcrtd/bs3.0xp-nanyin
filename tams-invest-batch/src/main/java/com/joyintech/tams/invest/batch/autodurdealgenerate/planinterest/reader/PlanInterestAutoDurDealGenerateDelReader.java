/*
 * 文件名：PlanInterestAutoDurDealGenerateDelReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.dto.PlanInterestAutoDurDealGenerateReaderResult;

/**
 * 删除处理Reader
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see PlanInterestAutoDurDealGenerateDelReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlanInterestAutoDurDealGenerateDelReader extends BaseReader<PlanInterestAutoDurDealGenerateReaderResult> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlanInterestAutoDurDealGenerateReaderResult> createList() {
        PlanInterestAutoDurDealGenerateReaderResult readerResult = new PlanInterestAutoDurDealGenerateReaderResult();
        List<PlanInterestAutoDurDealGenerateReaderResult> resultList = new ArrayList<PlanInterestAutoDurDealGenerateReaderResult>();
        resultList.add(readerResult);

        return resultList;
    }
}
