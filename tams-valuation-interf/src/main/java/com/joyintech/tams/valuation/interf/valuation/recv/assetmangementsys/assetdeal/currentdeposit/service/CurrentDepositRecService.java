/*
 * 文件名：CurrentDepositRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chendijun
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.CurrentDepositCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.currentdeposit.CurrentDepositDealIntTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.currentdeposit.CurrentDepositDealTransTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.currentdeposit.dto.CurrentDepositProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 活期存款利息-交易数据接口 处理processor 的service,返回结果供writer使用
 * 
 * @author chendijun
 * @version 1.0 2018年8月21日
 * @see CurrentDepositRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CurrentDepositRecService { 
    
    @Autowired
    private CurrentDepositDealIntTemplateRecService currentDepositDealIntTemplateRecService;
    @Autowired
    private CurrentDepositDealTransTemplateRecService currentDepositDealTransTemplateRecService;
    @SuppressWarnings({"unchecked"})
    public CurrentDepositProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //初始化返回结果
        CurrentDepositCommonResult result = new CurrentDepositCommonResult();
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        
        List<String> ps = new ArrayList<String>();
        if(TempConstant.活期存款收息交易.equals(acctEntity.getTmpEName())) {
            result = currentDepositDealIntTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_活期存款交易.getValue());
            ps.add(DictCodeList.交易方向_收息.getValue());
            paramMap.put("pslist", ps);
        } else if (TempConstant.活期存款转仓交易.equals(acctEntity.getTmpEName())) {
            result = currentDepositDealTransTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_活期存款交易.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            paramMap.put("pslist", ps);
        } else {
        	result.setDataNum(0);
        }
        result.setParamMap(paramMap);
        return new CurrentDepositProcessorResult(result);
        
    }

}
