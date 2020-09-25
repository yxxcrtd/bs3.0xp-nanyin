package com.joyintech.service.valuation.component.microservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.component.microservice.dao.ValFinFundTargetMapper;
import com.joyintech.service.valuation.component.microservice.dto.ValBussFinIntPayDto;
import com.joyintech.service.valuation.component.microservice.dto.ValFinFundTargetDto;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service
public class ValFinFundTargetService extends BaseService<ValFinFundTargetDto>{
	
	@Autowired
    private ValFinFundTargetMapper finFundTargetMapper;
	
	/**
     * <一句话功能简述>
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public ValBussFinIntPayDto getIntPayByFinprodId(String finprodId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("finprodId", finprodId);
        param.put("FIN_CASH_BUSINESS_TYPE_01", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        param.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        return finFundTargetMapper.getIntPayByFinprodId(param);
    }
}
