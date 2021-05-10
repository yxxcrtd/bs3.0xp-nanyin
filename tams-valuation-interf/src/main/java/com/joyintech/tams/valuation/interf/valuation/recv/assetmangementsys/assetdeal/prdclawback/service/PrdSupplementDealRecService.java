/*
 * 文件名：PrdSupplementDealRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.PrdSupplementDealRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.prdclawback.PrdSupplementDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.prdclawback.dto.PrdSupplementDealRecResult;


/**
 * 产品回补交易Service
 * 
 * @author dongjun
 * @version 1.0 2019年2月21日
 * @see PrdSupplementDealRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PrdSupplementDealRecService {
    
    @Autowired
    private PrdSupplementDealTemplateRecService prdSupplementDealTemplateRecService;
    @SuppressWarnings({"unchecked"})
    public PrdSupplementDealRecResult execute(TemplateOrderInfoReaderResult acctEntity) {
        
        //初始化 模板解析对象
        PrdSupplementDealRecCommonResult result = new PrdSupplementDealRecCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> ps = new ArrayList<String>();
        
        if(TempConstant.产品回补交易.equals(acctEntity.getTmpEName())) {
            result = prdSupplementDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType",DictCodeList.业务类型_产品回补交易.getValue());
            ps.add(DictCodeList.交易方向_回补.getValue());    
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        } 
        else {
            result.setDataNum(0);
        }
        result.setParamMap(paramMap);
        return new PrdSupplementDealRecResult(result);
    }


}
