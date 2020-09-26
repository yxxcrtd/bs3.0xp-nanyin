/*
 * 文件名：VatDealInfoRecServices.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.services;

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
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.vatax.AddValueTaxDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.vatax.dto.VatDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对增值税交易信息文件系列进行处理，供给Writer使用
 * 
 * @author wangqidong
 * @version 1.0 2018年8月16日
 * @see VatDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class VatDealInfoRecService {

    /**
     * 增值税交易 模板处理Service
     */
    @Autowired
    private AddValueTaxDealTemplateRecService addValueTaxDealTemplateRecService;

    /**
     * 
     * 增值税交易信息文件处理
     * @param acctEntity  acctEntity
     * @return TemplateOrderInfoReaderResult
     */
    @SuppressWarnings({"unchecked"})
    public VatDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        
        // 定义返回变量
        AssetDealInfoValCommonResult rtnResult = new AssetDealInfoValCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        //去重数据
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        
        //增值税交易
        if(TempConstant.增值税交易.equals(acctEntity.getTmpEName())){
            rtnResult = addValueTaxDealTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            
            paramMap.put("busiType", DictCodeList.业务类型_增值税交易.getValue());
            ps.add(DictCodeList.交易方向_税费缴税.getValue());
            ps.add(DictCodeList.交易方向_税费退税.getValue());
            paramMap.put("pslist", ps);//删除除这些交易方向的数据
        }
        else{
        	rtnResult.setDataNum(0);
        }
        
        rtnResult.setParamMap(paramMap);
        VatDealInfoProcessorResult result = new VatDealInfoProcessorResult(rtnResult);
        return result;
        
    }

}




