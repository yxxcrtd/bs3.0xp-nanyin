package com.joyintech.tams.settle.batch.dealrevoke.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何阳阳
 * @date 2020/8/3
 * @see InvestDealRevokeWriter
 * @since 1.0
 */
@Service
@Lazy
public class InvestDealRevokeWriter extends BaseWriter<NeedToWriteEntity> {
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        // 此步骤由交易汇总批处理执行
    }
}
