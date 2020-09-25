/*
 * 文件名：MarketRiskSceneManage001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.valid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneFactorTypeDto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.service.MarketRiskSceneManage002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;


/**
 * 市场性情景管理情景新增、编辑验证类
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月3日
 * @see MarketRiskSceneManage002Validator
 * @since 1.0
 */
@Transactional
@Component
public class MarketRiskSceneManage002Validator implements BeanValidator {

    /**
     * 情景新建002service注入
     */
    @Autowired
    private MarketRiskSceneManage002Service marketRiskSceneManage002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MarketRiskSceneManage002Dto dto = (MarketRiskSceneManage002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        
//        if(WorkFlowUtil.isNotFromToDoList() || DictCodeListRsk.是否_是.getValue().equals(dto.getNewType())){
            //新建模式或编辑模式
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                //情景名称唯一性校验
                if(StringUtil.isEmpty(dto.getSceneName())){
                    errorList.add(new ValidMessage("sceneName","情景名称不能为空！"));
                }else{
                    int count = marketRiskSceneManage002Service.queryScentNameCount(dto.getSceneName(),dto.getSceneId());
                    if(count != 0){
                        errorList.add(new ValidMessage("sceneName","情景名称不能重复！"));
                    }
                }
                
                //风险因子校验
                boolean selected = false;
                if(dto.getFtrTypeList()==null || dto.getFtrTypeList().size() > 0){
                	for(MarketRiskSceneFactorTypeDto item:dto.getFtrTypeList()){
                		selected = selected | item.isFtrChecked();
                    }
                }
                if(!selected){
                	errorList.add(new ValidMessage("","请至少选择一类风险因子！"));
                }
                
                //时间区间校验
                Date today = new Date();
                try {
                	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                    today = format.parse(format.format(today));
				} catch (Exception e) {
					e.printStackTrace();
				}
                if(dto.getStartDate() == null || dto.getEndDate() == null){
                    errorList.add(new ValidMessage("","请选择日期区间！"));
                }else if(dto.getStartDate().after(dto.getEndDate())){
                    errorList.add(new ValidMessage("","开始日期必须小于结束日期区间！"));
                }else if(dto.getEndDate().after(today) || dto.getEndDate().getTime() == today.getTime()){
                    errorList.add(new ValidMessage("","日期不能大于今天！"));
                }
            }
//        }
        return errorList;
    }


}
