/*
 * 文件名：ProfitDistributionBookNetReader.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1
 * 修改人：WUJIE
 * 修改时间：2020年4月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1
 */
package com.joyintech.tams.valuation.batch.profitdistributionnet.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.profitdistributionnet.dto.ProfitDistributionNetReaderDto;
import com.joyintech.tams.valuation.batch.profitdistributionnet.service.ProfitDistributionBookNetReaderService;

/**
 * 净值型产品利润分配批处理
 * 
 * @author WUJIE
 * @version 1.0 2020年4月1日
 * @see ProfitDistributionBookNetReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookNetReader extends BaseReader<ProfitDistributionNetReaderDto> {

    
    /**
     * reader方法的 Service
     */
    @Autowired
    private ProfitDistributionBookNetReaderService readerService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<ProfitDistributionNetReaderDto> createList() {
        // 获取task传递参数
        Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return new ArrayList<>();
        }

        // 获取reader返回值
        List<ProfitDistributionNetReaderDto> resultList = readerService.execute(this.getParams());

        return resultList;
    }

}
