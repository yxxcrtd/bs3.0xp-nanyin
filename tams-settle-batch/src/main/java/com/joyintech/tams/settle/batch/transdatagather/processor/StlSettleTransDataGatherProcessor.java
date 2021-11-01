package com.joyintech.tams.settle.batch.transdatagather.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalAdd;
import com.joyintech.tams.settle.batch.transdatagather.dto.StlSettleTransDataGatherParamDto;
import com.joyintech.tams.settle.batch.transdatagather.service.StlSettleTransDataGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meifaxin
 * @date 2021/10/29
 * @see StlSettleTransDataGatherProcessor
 * @since 1.0
 */
@Service
@Lazy
public class StlSettleTransDataGatherProcessor extends BaseProcessor<NeedOperateEntity, NeedToWriteEntity> {
    @Autowired
    private StlSettleTransDataGatherService stlSettleTransDataGatherService;
    @Override
    public NeedToWriteEntity process(NeedOperateEntity entity) throws Exception {
        StlSettleTransDataGatherParamDto item = (StlSettleTransDataGatherParamDto)entity;
        // 交易编号
        List<SettleTransDataTotal> transDataTotalList = item.getSettleTransDataTotalList();
        // 汇总交易编号
        List<SettleTransDataTotalAdd> transDataTotalAddList = item.getSettleTransDataTotalAddList();
        //批量写入汇总成交数据表
        stlSettleTransDataGatherService.batchInsetSettleTransDataTotal(transDataTotalList);
        stlSettleTransDataGatherService.batchInsetSettleTransDataTotalAdd(transDataTotalAddList);
        return null;
    }
}
