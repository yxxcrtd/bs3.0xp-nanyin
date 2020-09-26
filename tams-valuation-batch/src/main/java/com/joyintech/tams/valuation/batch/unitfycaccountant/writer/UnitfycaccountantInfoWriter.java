package com.joyintech.tams.valuation.batch.unitfycaccountant.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.unitfycaccountant.entity.UnitfycaccountantInfoDto;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;

@Service
public class UnitfycaccountantInfoWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private SysUnaccountantInfoDetailMapper sysUcaccountantInfoDetailMapper;

    @Autowired
    private SysUnaccountantInfoMapper sysUcaccountantInfoMapper;

    @Override
    public void write(List<? extends NeedToWriteEntity> list) throws Exception {
        List<UnitfycaccountantInfoDto> items =  (List<UnitfycaccountantInfoDto>)list;
        for (UnitfycaccountantInfoDto dto : items) {
            List<SysUnaccountantInfoDetail> sysUnitfycaccountantInfos = dto.getList();
            sysUcaccountantInfoMapper.insert(dto.getSysUcaccountantInfo());
            sysUnitfycaccountantInfos.forEach(x->sysUcaccountantInfoDetailMapper.insert(x));
            if (dto.getProdId() == null) {
                dto.setProdId("UnitfycaccountantInfo");
            }
        }
    }
}
