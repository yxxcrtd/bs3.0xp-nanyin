/*
 * 文件名：FundDealInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.lend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.LendDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.lend.LendDealRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.lend.LendRateDealService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.lend.LendRecTradeService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.lend.dto.LendDealRecProcessorResult;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see LendDealRecComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendDealRecComForInterfService {

    /**
     * 拆借交易
     */
    @Autowired
    private LendDealRecService lendDealRecService;
    
    /**
     * 拆借收息
     */
    @Autowired
    private LendRecTradeService lendRecTradeService;
    
    /**
     * 拆借应收款项转让交易
     */
    @Autowired
    private LendRateDealService lendRateDealService;
    
    @Value("${interf.properties.fourLevelSubjectKg}")
    private String fourLevelSubjectKg;

    @SuppressWarnings({"unchecked"})
    public LendDealRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        LendDealCommonResult result = new LendDealCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> ps = new ArrayList<String>();
        acctEntity.setFourLevelSubjectKg(fourLevelSubjectKg);
        
        if (TempConstant.拆借交易.equals(acctEntity.getTmpEName())) {
            result = lendDealRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_拆借交易.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            ps.add(DictCodeList.交易方向_到期.getValue());
            paramMap.put("pslist", ps);
            
            List<String> fpsList = new ArrayList<String>();
            fpsList.add(DictCodeList.交易方向_收息到账.getValue());
            paramMap.put("fpsList", fpsList);
            
        }else if(TempConstant.拆借收息.equals(acctEntity.getTmpEName())){
            result = lendRateDealService.execute(templateSourceFlgDTO, acctEntity); 
            paramMap.put("busiType", DictCodeList.业务类型_拆借交易.getValue());
            ps.add(DictCodeList.交易方向_收息到账.getValue());
            paramMap.put("pslist", ps);
            List<String> fpsList = new ArrayList<String>();
            fpsList.add(DictCodeList.交易方向_还本到账.getValue());
            paramMap.put("fpsList", fpsList);
        }else if(TempConstant.拆借应收款项转让交易.equals(acctEntity.getTmpEName())){
            result = lendRecTradeService.execute(templateSourceFlgDTO, acctEntity); 
            paramMap.put("busiType", DictCodeList.业务类型_拆借交易.getValue());
            ps.add(DictCodeList.交易方向_应收款项转入.getValue());
            ps.add(DictCodeList.交易方向_应收款项转出.getValue());
            paramMap.put("pslist", ps);
        }
        else{
        	result.setDataNum(0);
        }
        result.setParamMap(paramMap);
        return new LendDealRecProcessorResult(result);
    }
}
