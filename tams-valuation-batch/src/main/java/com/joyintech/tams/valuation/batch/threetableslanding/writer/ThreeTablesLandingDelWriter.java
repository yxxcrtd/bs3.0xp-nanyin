package com.joyintech.tams.valuation.batch.threetableslanding.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.valuation.batch.threetableslanding.dao.ThreeTableLandingMapper;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingDelResult;

@Service
public class ThreeTablesLandingDelWriter extends BaseWriter<NeedToWriteEntity> {
    
    @Autowired
    private ThreeTableLandingMapper threeTableLandingMapper;
    
    @Override
    public void write(List<? extends NeedToWriteEntity> list) throws Exception {
        @SuppressWarnings("unchecked")
        List<ThreeTableLandingDelResult> items =  (List<ThreeTableLandingDelResult>)list;
        // 循环返回结果
        for (ThreeTableLandingDelResult item : items) {
            threeTableLandingMapper.deleteTdyValTableBas(item);
            threeTableLandingMapper.deleteTdyValTableIns(item);
            threeTableLandingMapper.deleteTdyValTableScoe(item);
//            threeTableLandingMapper.deleteRemindMessage(item);
        }
    }
}
