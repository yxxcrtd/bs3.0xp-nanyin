/*
 * 文件名：FundBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.fund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FundBaseInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund.FundBaseInfoMoneyFundRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund.FundBaseInfoNFundRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund.FundPayPlanMoneyFundRecService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.fund.FundPayRuleMoneyFundRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.fund.dto.FundBaseInfoForInterfRecProcessorResult;

/**
 * 批处理启动文件接收批处理 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年6月26日
 * @see FundBaseInfoComForInterfService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FundBaseInfoComForInterfService {
 
    /**
     * 货币基金基础信息
     */
    @Autowired
    private FundBaseInfoMoneyFundRecService fundBaseInfoMoneyFundRecService;

    /**
     * 净值型基金基础信息
     */
    @Autowired
    private FundBaseInfoNFundRecService fundBaseInfoNFundRecService;

    /**
     * 货币基金付息计划
     */
    @Autowired
    private FundPayPlanMoneyFundRecService fundPayPlanMoneyFundRecService;

    /**
     * 货币基金付息规则
     */
    @Autowired
    private FundPayRuleMoneyFundRecService fundPayRuleMoneyFundRecService;
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    public FundBaseInfoForInterfRecProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
        // 定义返回变量
        FundBaseInfoForInterfRecCommonResult resultCommon = new FundBaseInfoForInterfRecCommonResult();

        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        if (TempConstant.基金类资产定义货币基金基础信息.equals(acctEntity.getTmpEName())) {
            resultCommon = fundBaseInfoMoneyFundRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.基金类资产定义货币基金付息规则.equals(acctEntity.getTmpEName())) {
            resultCommon = fundPayRuleMoneyFundRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.基金类资产定义货币基金付息计划.equals(acctEntity.getTmpEName())) {
            resultCommon = fundPayPlanMoneyFundRecService.execute(templateSourceFlgDTO,
                acctEntity);
        }
        else if (TempConstant.基金类资产定义净值型基金基础信息.equals(acctEntity.getTmpEName())) {
            resultCommon = fundBaseInfoNFundRecService.execute(templateSourceFlgDTO, acctEntity);
        }
        return new FundBaseInfoForInterfRecProcessorResult(resultCommon);
    }
}
