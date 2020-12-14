/*
 * 文件名：ProductReportWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月9日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.productreport.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.psbc.productreport.dao.ProductReportProcessorMapper;
import com.joyintech.tams.batch.psbc.productreport.dto.ProductReportResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 理财报表生成批处理 Writer
 *
 * @author yangjian
 * @version 1.0 2018年5月9日
 * @see ProductReportWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProductReportWriter extends BaseWriter<NeedToWriteEntity> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductReportProcessorMapper productReportProcessorMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends NeedToWriteEntity> items) {
        List<ProductReportResult> productReportResultList = (List<ProductReportResult>) items;
        if (!productReportResultList.isEmpty()) {
            for (ProductReportResult productReportResult : productReportResultList) {
                // 理财产品状态表
                if (StringUtil.isNotEmpty(productReportResult.getRepProdStatusDto())) {
                    productReportProcessorMapper.deleteRepProdStatus(productReportResult.getBookSetId(), productReportResult.getBookDate());
                    productReportProcessorMapper.insetRepProdStatus(productReportResult.getRepProdStatusDto());
                }
                // 理财产品信息表
                if (StringUtil.isNotEmpty(productReportResult.getRepProdMessageDto())) {
                    productReportProcessorMapper.deleteRepProdMessage(productReportResult.getBookSetId(), productReportResult.getBookDate());
                    productReportProcessorMapper.insetRepProdMessage(productReportResult.getRepProdMessageDto());
                }
            }
        }
    }

}
