/*
 * 文件名：ProductReportReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年3月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.psbc.productreport.reader;

import com.google.common.collect.Lists;
import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.batch.psbc.productreport.dto.ProductReportDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 理财报表生成批处理 Reader
 *
 * @author jiangpeng
 * @version 1.0 2020/12/04
 * @see ProductReportReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProductReportReader extends BaseReader<ProductReportDto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductReportDto> createList() {
        Map<String, Object> params = getParams();
        //根据参数查询对应待处理的账套
        Date batchDate = (Date) params.get(BookConstant.BOOK_DATE);
        List<ProductReportDto> listDto = Lists.newArrayList();
//        batchDate = DateUtil.addDay(batchDate, -1);
        if (1 != DateUtil.getDayOfMonth(DateUtil.format(DateUtil.addDay(batchDate, 1)))) {
            logger.info("非当月1号，批处理退出。");
            return null;
        }
        for (String productId : (List<String>) params.get("productIdList")) {
            ProductReportDto dto = new ProductReportDto();
            dto.setBookDate(batchDate);
            if (!productId.contains("F16_")) {
                productId = "F16_" + productId;
            }
            dto.setBooksetId(productId);
            listDto.add(dto);
        }
        return listDto;
    }
}
