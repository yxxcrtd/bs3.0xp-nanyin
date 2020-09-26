/*
 * 文件名：CleanOrderRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CleanOrderCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.cleanorder.CleanOrderTemplateRecSerive;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.cleanorder.dto.CleanOrderProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 结算指令接口 processor 的 service
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2018年8月17日
 * @see CleanOrderRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CleanOrderRecService {
    
    /**
     * 结算指令接口 模板处理Service
     */
    @Autowired
    CleanOrderTemplateRecSerive cleanOrderTemplateRecSerive;
    @SuppressWarnings({"unchecked"})
    public CleanOrderProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //初始化 模板解析对象
        CleanOrderCommonResult result = new CleanOrderCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        if(TempConstant.结算指令.equals(acctEntity.getTmpEName())){
        	result = cleanOrderTemplateRecSerive.execute(templateSourceFlgDTO, acctEntity);
        	paramMap.put("busiType",DictCodeList.业务类型_指令交易.getValue());
			if(result.getParamMap()!=null){
        		paramMap.put("booksetIds", result.getParamMap().get("booksetIds"));
        	}
        }else{
        	result.setDataNum(0);
        }
        result.setParamMap(paramMap);
        return new CleanOrderProcessorResult(result);
    }

}
