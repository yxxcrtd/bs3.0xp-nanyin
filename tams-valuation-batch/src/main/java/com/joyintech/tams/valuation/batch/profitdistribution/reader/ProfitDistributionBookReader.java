/*
 * 文件名：ProfitDistributionBookReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;
import com.joyintech.tams.valuation.batch.profitdistribution.service.ProfitDistributionBookReaderService;

/**
 * 利润分配批处理- 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see ProfitDistributionBookReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookReader extends BaseReader<ProfitDistributionReaderDto> {

    
    /**
     * reader方法的 Service
     */
    @Autowired
    private ProfitDistributionBookReaderService profitDistributionBookReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<ProfitDistributionReaderDto> createList() {
        // 获取task传递参数
        Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return new ArrayList<>();
        }

        // 获取reader返回值
        List<ProfitDistributionReaderDto> resultList = profitDistributionBookReaderService.execute(this.getParams());

        return resultList;
    }

}
