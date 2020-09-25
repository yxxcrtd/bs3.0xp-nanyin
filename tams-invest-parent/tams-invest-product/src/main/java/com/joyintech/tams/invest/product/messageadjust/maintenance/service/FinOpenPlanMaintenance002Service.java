package com.joyintech.tams.invest.product.messageadjust.maintenance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenance002Dto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.OpenPlanParamDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * @author zhangliang
 * @version 1.0 2018年11月28日
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinOpenPlanMaintenance002Service extends BaseService<FinOpenPlanMaintenance002Dto> implements BaseProcessService<FinOpenPlanMaintenance002Dto> {

	public void doComplete(FinOpenPlanMaintenance002Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinOpenPlanMaintenance/doUpdate",
				entity, Integer.class);
	}
	
    public OpenPlanParamDto selectOpenPlanParamDto(String finprodId) {
    	
    	Map<String,Object> paraMap = new HashMap<String,Object>();
    	paraMap.put("finprodId", finprodId);
    	
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
            + "/FinOpenPlanMaintenance/selectOpenPlanParamDto",paraMap,OpenPlanParamDto.class);
        
    }
    
    /**
     * 产品代码联想下拉框
     */
    public List<SelectOptionDto> productCodeList(Map<String,Object> param) {
        //数据字典.金融产品类型.理财产品, 数据字典.金融产品类型.理财产品分层
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("operationType", DictCodeList.开放类型_开放式.getValue());
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
                + "/FinOpenPlanMaintenance/productCodeList",param,SelectOptionDto.class);      
    }
}
