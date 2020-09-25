/*
 * 文件名：RskPortfolioIndicator002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.service.FlowRiskStressTest001Service;

/**
 * 组合关联指标验证
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月13日
 * @see FlowRiskStressTest001Validator
 * @since 1.0
 */
@Transactional
@Component
public class FlowRiskStressTest001Validator implements BeanValidator{

	@Autowired(required = false)
	private FlowRiskStressTest001Service flowRiskStressTest001Service;
	  
	/**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
	
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        FlowRiskStressTest001Dto dto = (FlowRiskStressTest001Dto)value;
        if(StringUtil.isNotEmpty(dto.getTestName())){
            //压测名称不能重读
            int count = flowRiskStressTest001Service.stressTestNameCount(dto.getTestName());
            if(count > 0){
                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0009",getMessage("FlowRiskStressTest001.testName"))));
            }
        }else{
        	//压力测试名称不能为空
            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0008",getMessage("FlowRiskStressTest001.testName"))));
        }
        //压测基准日期不能为空
        if(!StringUtil.isNotEmpty(dto.getBaseDate())) {
        	errorList.add(new ValidMessage("", getMessage("RSK_W_F_0008","分析基准日")));
        }
        //组合产品名称不为空
        if(StringUtil.isEmpty(dto.getPortfolioSingleName())){
            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0008",getMessage("FlowRiskStressTest001.portfolioName"))));
        }
        //情景不为空
        if(StringUtil.isEmpty(dto.getSceneId())){
            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0008",getMessage("FlowRiskStressTest001.sceneName"))));
        }
        //压力测试结束时间不能为空
        if(!StringUtil.isNotEmpty(dto.getAnalysisMdate())){
            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0008",getMessage("FlowRiskStressTest001.zbEndDate"))));
        }else{
        	//压力测试结束时间不能小于系统日期
            if(DateUtil.compareDate(DateUtil.parse(dto.getAnalysisMdate()),iPlatformDate.getSysDate()) < 0){
                errorList.add(new ValidMessage("",getMessage("RSK_W_F_0010",
                    getMessage("FlowRiskStressTest001.zbEndDate"))));
            }
            //压力测试结束时间不能为法定节假日
            String isHoliday = flowRiskStressTest001Service.judgeYesOrNoHoliday(dto.getAnalysisMdate());
            //是否节假日-0：工作日，1:节假日
            if(StringUtil.equals(isHoliday, "1")){
                errorList.add(new ValidMessage("",getMessage("RSK_W_F_0013",
                    getMessage("FlowRiskStressTest001.zbEndDate"))));
            }
        }
        
        return errorList;
    }

}
