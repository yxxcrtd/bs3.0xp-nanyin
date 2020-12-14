/*
 * 文件名：ProductReportProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年3月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.productreport.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.psbc.productreport.dto.ProductReportDto;
import com.joyintech.tams.batch.psbc.productreport.dto.ProductReportResult;
import com.joyintech.tams.batch.psbc.productreport.service.ProductReportProcessorService;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 理财报表生成批处理 Processor
 *
 * @author jiangpeng
 * @version 1.0 2020/12/04
 * @see ProductReportProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProductReportProcessor extends BaseProcessor<NeedOperateEntity, ProductReportResult> {

    @Autowired
    private ProductReportProcessorService productReportProcessorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductReportResult process(NeedOperateEntity items) {
        // 获取reader方法的返回值
        ProductReportDto acctEntity = (ProductReportDto) items;
        if (!StringUtil.isNotEmpty(acctEntity)) {
            return null;
        }

        // 调用service方法进行逻辑逻辑
        ProductReportResult result = productReportProcessorService.execute(acctEntity);

        //当为null时候，给予初始化
        if (result == null) {
            result = new ProductReportResult();
        }
        return result;
    }
}

