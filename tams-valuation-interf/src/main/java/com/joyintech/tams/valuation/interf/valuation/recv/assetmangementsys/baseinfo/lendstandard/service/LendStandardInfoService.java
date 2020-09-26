/*
 * 文件名：FundBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.lendstandard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.LendStandardCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard.LendStandardPlanRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard.LendStandardRateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard.LendStandardRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.lendstandard.LendStandardRuleRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.lendstandard.dto.LendStandardRecResult;
/**
 * 批处理启动文件接收批处理 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see LendStandardInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendStandardInfoService {
 
 

    /**
     * 拆借标的
     */
    @Autowired
    private LendStandardRecService lendStandardRecService;
    
    /**
     * 拆借付息规则
     */
    @Autowired
    private LendStandardRuleRecService lendStandardRuleRecService;
    
    /**
     * 拆借付息计划
     */
    @Autowired
    private LendStandardPlanRecService lendStandardPlanRecService;
    
    /** add by dongjun on 20181127 start **/
    /**
     * 拆借利率调整
     */
    @Autowired
    private LendStandardRateRecService lendStandardRateRecService;
    /** add by dongjun on 20181127 start **/
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    public LendStandardRecResult execute(TemplateOrderInfoReaderResult acctEntity){
        // 定义返回变量
        LendStandardCommonResult resultCommon = new LendStandardCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        if (TempConstant.拆借交易标的信息.equals(acctEntity.getTmpEName())) {
            resultCommon = lendStandardRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }else if(TempConstant.拆借付息规则.equals(acctEntity.getTmpEName())){
        	resultCommon = lendStandardRuleRecService.execute(templateSourceFlgDTO,
                    acctEntity);        
        }else if(TempConstant.拆借付息计划.equals(acctEntity.getTmpEName())){
        	resultCommon = lendStandardPlanRecService.execute(templateSourceFlgDTO,
                    acctEntity);   
        }
        /**add by dongjun on 20181127 start**/
        else if(TempConstant.拆借利率调整.equals(acctEntity.getTmpEName())){
            resultCommon = lendStandardRateRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        /**add by dongjun on 20181127 start**/
        resultCommon.setParamMap(paramMap);
        return new LendStandardRecResult(resultCommon);
    }
}
