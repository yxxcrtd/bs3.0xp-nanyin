package com.joyintech.tams.settle.batch.datagather.processor.repo;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;
import com.joyintech.tams.settle.batch.datagather.dto.TrdProductDealDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回购交易处理
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherRepo4Processor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataGatherRepo4Processor extends StlDataGatherRepo3Processor {
    @Override
    protected void setDealTotal(TotalTrdProductDealDto dealTotal, List<TrdProductDealDto> dealList) {
        super.setDealTotal(dealTotal, dealList);
        // 交易方向
        dealTotal.setPs(DictCodeList.交易方向_到期.getValue());
    }
}
