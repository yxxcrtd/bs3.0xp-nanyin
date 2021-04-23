package com.joyintech.tams.valuation.batch.threetableslanding.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingDelResult;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;

@Service
@Scope("prototype")
public class ThreeTablesLandingDelProcessor extends BaseProcessor<NeedOperateEntity, ThreeTableLandingDelResult> {
    
    @Override
    public ThreeTableLandingDelResult process(NeedOperateEntity item)
            throws Exception {
        // 获取reader方法的返回值
        ValTaskTodoDto acctEntity = (ValTaskTodoDto)item;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        ThreeTableLandingDelResult result = new ThreeTableLandingDelResult();
        result.setProdId(acctEntity.getBooksetId());
        result.setBooksetId(acctEntity.getBooksetId());
        result.setHappenDate(acctEntity.getHappenDate());
//        result.setMessageType(DictCodeList.消息类型_资负不平提醒.getValue());
        return result;
    }
}
