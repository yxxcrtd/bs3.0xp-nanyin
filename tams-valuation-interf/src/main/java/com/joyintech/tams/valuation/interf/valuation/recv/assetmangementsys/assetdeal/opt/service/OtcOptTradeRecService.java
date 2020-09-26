/*
 * 文件名：OtcOptTradeRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：tanxuan
 * 修改时间：2020年01月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.opt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValOtcOptTradeCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.opt.ValOtcOptTradeTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.opt.dto.OtcOptTradeProcessorResult;

/**
 * 批处理启动文件接收批处理 对场外期权交易信息文件处理，供给Writer使用
 * 
 * @author tanxuan
 * @version 1.0 2020年01月10日
 * @see OtcOptTradeRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OtcOptTradeRecService {

    /**
     * 债券买卖交易 模板处理Service
     */
    @Autowired
    private ValOtcOptTradeTemplateRecService valOtcOptTradeTemplateRecService;
    
    /**
     * 场外期权交易信息文件处理
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public OtcOptTradeProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {

        // 定义返回变量
    	ValOtcOptTradeCommonResult rtnResult = new ValOtcOptTradeCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        // 场外期权交易
        if (TempConstant.场外期权交易.equals(acctEntity.getTmpEName())) {
            rtnResult = valOtcOptTradeTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            paramMap.put("busiType", DictCodeListExt3.业务类型_场外期权.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeListExt3.交易方向_提前行权.getValue());
            ps.add(DictCodeListExt3.交易方向_到期行权.getValue());
            
            paramMap.put("pslist", ps);
        }else{
        	rtnResult.setDataNum(0);
        }
        rtnResult.setParamMap(paramMap);
        OtcOptTradeProcessorResult result = new OtcOptTradeProcessorResult(rtnResult);
        return result;
    }

}
