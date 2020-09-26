package com.joyintech.tams.valuation.batch.batchunistart.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.batchunistart.entity.BatchUnistartDto;
import com.joyintech.tams.common.base.dao.SysBatchUnistartDetailMapper;
import com.joyintech.tams.common.base.dao.SysBatchUnistartMapper;
import com.joyintech.tams.common.base.entity.SysBatchUnistartDetail;

@Service
@Scope("prototype")
public class BatchUnistartWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private SysBatchUnistartDetailMapper sysBatchUnistartDetailMapper;

    @Autowired
    private SysBatchUnistartMapper sysBatchUnistartMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> list) throws Exception {
        List<BatchUnistartDto> items = (List<BatchUnistartDto>)list;
        for (BatchUnistartDto dto : items) {
            sysBatchUnistartMapper.insert(dto.getSysBatchUnistart());
            List<SysBatchUnistartDetail> sysBatchUnistartDetailList = dto.getSysBatchUnistartDetailList();
            sysBatchUnistartDetailList.forEach(x -> sysBatchUnistartDetailMapper.insert(x));
        }
    }

}
