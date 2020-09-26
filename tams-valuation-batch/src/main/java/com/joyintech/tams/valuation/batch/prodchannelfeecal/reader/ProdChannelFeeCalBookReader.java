/*
 * 文件名：ProfitDistributionBookReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prodchannelfeecal.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.dto.ProdChannelFeeCalBookReaderDto;
import com.joyintech.tams.valuation.batch.prodchannelfeecal.service.ProdChannelFeeCalBookReaderService;

/**
 * 理财产品通道费补提处理批处理- 读入
 * 
 * @author chengzhong
 * @version 1.0 2018年5月7日
 * @see ProdChannelFeeCalBookReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProdChannelFeeCalBookReader extends BaseReader<ProdChannelFeeCalBookReaderDto> {

    
    /**
     * reader方法的 Service
     */
    @Autowired
    private ProdChannelFeeCalBookReaderService prodChannelFeeCalBookReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<ProdChannelFeeCalBookReaderDto> createList() {
        // 获取task传递参数
        Map<String, Object> params = this.getParams();
        if (MapUtils.isEmpty(params)) {
            return new ArrayList<>();
        }

        // 获取reader返回值
        List<ProdChannelFeeCalBookReaderDto> resultList = prodChannelFeeCalBookReaderService.execute(this.getParams());

        return resultList;
    }

}
