/*
 * 文件名：FxDealInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2019年12月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.overdue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FxDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.overdue.FxDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.overdue.dto.FxDealInfoProcessorResult;

/**
 * 批处理启动文件接收批处理 对逾期交易信息文件系列机型处理，供给Writer使用
 * 
 * @author tanxuan
 * @version 1.0 2019年12月27日
 * @see FxDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FxDealInfoRecService {

    /**
     * 逾期交易 模板处理Service
     */
    @Autowired
    private FxDealTemplateRecService fxDealTemplateRecService;
    
    /**
     * 逾期交易信息文件处理
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public FxDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {

        // 定义返回变量
    	FxDealInfoValCommonResult rtnResult = new FxDealInfoValCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        // 01-逾期交易
        if (TempConstant.逾期交易.equals(acctEntity.getTmpEName())) {
            rtnResult = fxDealTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_逾期交易.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            ps.add(DictCodeList.交易方向_收息到账.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }else{
        	rtnResult.setDataNum(0);
        }
        rtnResult.setParamMap(paramMap);
        FxDealInfoProcessorResult result = new FxDealInfoProcessorResult(rtnResult);
        return result;
    }

}
