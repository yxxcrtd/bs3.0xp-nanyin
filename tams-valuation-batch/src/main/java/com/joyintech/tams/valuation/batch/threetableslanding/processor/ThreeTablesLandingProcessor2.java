package com.joyintech.tams.valuation.batch.threetableslanding.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingResultDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;
import com.joyintech.tams.valuation.batch.threetableslanding.service.ThreeTablesLandingProcessor2Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

@Service
@Scope("prototype")
public class ThreeTablesLandingProcessor2 extends BaseProcessor<NeedOperateEntity, ThreeTableLandingResultDto> {
    
    @Autowired
    private ThreeTablesLandingProcessor2Service threeTablesLandingProcessor2Service;

    @Override
    public ThreeTableLandingResultDto process(NeedOperateEntity item)
            throws Exception {
        // 获取reader方法的返回值
        ValTaskTodoDto acctEntity = (ValTaskTodoDto)item;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        ThreeTableLandingResultDto result = null;
        result = threeTablesLandingProcessor2Service.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new ThreeTableLandingResultDto();
        }

        result.setProdId(acctEntity.getBooksetId());

        // 取得科目余额
        return result;
    }
}
