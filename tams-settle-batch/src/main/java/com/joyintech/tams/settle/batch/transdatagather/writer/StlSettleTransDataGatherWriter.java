package com.joyintech.tams.settle.batch.transdatagather.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meifaxin
 * @date 2021/10/29
 * @see StlSettleTransDataGatherWriter
 * @since 1.0
 */
@Service
@Lazy
public class StlSettleTransDataGatherWriter extends BaseWriter<NeedToWriteEntity> {
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        // 此步骤由交易汇总批处理执行
    }
}
