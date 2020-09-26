/*
 * 文件名：ChannelBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年3月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.channel.ChannelBaseInfoService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.channel.ChannelFeeBaseInfoService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.channel.ChannelRateInfoService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.channel.dto.ChannelBaseInfoProcessorResult;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2019年3月12日
 * @see ChannelBaseInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelBaseInfoRecService {
    /**
     * 通道基础信息
     */
    @Autowired
    private ChannelBaseInfoService channelBaseInfoService;
    /**
     * 通道费用基础信息
     */
    @Autowired
    private ChannelFeeBaseInfoService channelFeeBaseInfoService;
    /**
     * 通道利率信息
     */
    @Autowired
    private ChannelRateInfoService channelRateInfoService;

    
    public ChannelBaseInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
        ChannelBaseInfoCommonResult rtnResult = new ChannelBaseInfoCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
       
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getDtoName());
        // 01-通道基础信息
        if(TempConstant.通道基础信息.equals(acctEntity.getTmpEName())){            
            rtnResult = channelBaseInfoService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 02-通道费用基础信息
        else if (TempConstant.通道费用基础信息.equals(acctEntity.getTmpEName())) {
            rtnResult = channelFeeBaseInfoService.execute(templateSourceFlgDTO,acctEntity);
        }
        // 03-通道利率信息
        else if (TempConstant.通道利率信息.equals(acctEntity.getTmpEName())) {
            rtnResult = channelRateInfoService.execute(templateSourceFlgDTO,acctEntity);
        }
        rtnResult.setParamMap(paramMap);
        
        return new ChannelBaseInfoProcessorResult(rtnResult);
        
    }
    
    
}
