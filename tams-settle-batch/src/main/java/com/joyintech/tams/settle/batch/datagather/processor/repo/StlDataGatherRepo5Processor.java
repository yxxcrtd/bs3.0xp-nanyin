package com.joyintech.tams.settle.batch.datagather.processor.repo;

import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 债券质押式竞价回购（到期）
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherRepo5Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo5Processor extends StlDataGatherRepo3Processor {

    @Override
    protected List<TotalTrdProductDealDto> generateDealTotal(List<TrdProductDealDto> dealList) {
        List<TotalTrdProductDealDto> totalTrdProductDealDtoList = new ArrayList<>();
        // 按金融产品代码分组
        Map<String, List<TrdProductDealDto>> dealMapList = dealList.stream().collect(JoyinCollectors.groupingBy(TrdProductDealDto::getFinprodId));
        dealMapList.values().forEach(dealDtoList -> {
            totalTrdProductDealDtoList.addAll(super.generateDealTotal(dealList));
        });
        return totalTrdProductDealDtoList;
    }

    @Override
    protected List<TrdProductDealDto> generateProductDeal(List<SettleTransDataDto> settleTransDataDtos) {
        List<TrdProductDealDto> trdProductDealDtos = super.generateProductDeal(settleTransDataDtos);
        trdProductDealDtos.forEach(dealDto -> {
            // 投管交易编号
            dealDto.setFinprodId(settleTransDataDtos.get(0).getTradeIdTg());
            dealDto.setEntrustId(null);
        });
        return trdProductDealDtos;
    }
}
