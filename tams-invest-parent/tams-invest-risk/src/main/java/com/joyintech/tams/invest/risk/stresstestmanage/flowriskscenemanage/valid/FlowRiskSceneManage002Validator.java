/*
 * 文件名：FlowRiskSceneManage002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManageListDto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service.FlowRiskSceneManage002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 情景新建、编辑验证类
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月29日
 * @see FlowRiskSceneManage002Validator
 * @since 1.0
 */
@Transactional
@Component
public class FlowRiskSceneManage002Validator implements BeanValidator {
    
    /**
     * 情景新建002service注入
     */
    @Autowired    
    private FlowRiskSceneManage002Service flowRiskSceneManage002Service;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FlowRiskSceneManage002Dto dto = (FlowRiskSceneManage002Dto)value;
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        
//        if (WorkFlowUtil.isNotFromToDoList()) {
            //新建模式
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {

                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                    WflValidInputParameter param = new WflValidInputParameter();
                    param.setBusinessDataPk1(dto.getSceneId());
                    param.setPageId("FlowRiskSceneManage002");
                    if (null != mstWorkflowCommonService &&
                            mstWorkflowCommonService.isInWflProcess(param)) {
                        errorList.add(new ValidMessage(this.getMessage("COMMON_E_0012", dto.getSceneName())));
                    }
                }

                int flag = 0;
                if(StringUtil.isNotEmpty(dto.getFtrList1()) && dto.getFtrList1().size() != 0){
                    FlowRiskSceneManageListDto ftr1 = dto.getFtrList1().get(0);
                    if(StringUtil.isEmpty(ftr1.getFtrId())){
                        errorList.add(new ValidMessage("", getMessage("RSK_W_0017",getMessage("FlowRiskSceneManage002.ftrId"))));
                    }else{//因子下拉框不为空
                        if(StringUtil.equals(ftr1.getFtrId(), DictCodeListRsk.流动性风险因子_申赎净流出增加比例.getValue())){
                            //申赎净流出增加比例校验
                            if(StringUtil.isNotEmpty(ftr1.getLightValue()) && ftr1.getLightValue().intValue()<0 ){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0004",getMessage("FlowRiskSceneManage002.lightValue"))));
                            }
                            if(StringUtil.isNotEmpty(ftr1.getMediumValue()) && ftr1.getMediumValue().intValue()<0 ){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0004",getMessage("FlowRiskSceneManage002.mediumValue"))));
                            }
                            if(StringUtil.isNotEmpty(ftr1.getHeavyValue()) && ftr1.getHeavyValue().intValue()<0 ){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0004",getMessage("FlowRiskSceneManage002.heavyValue"))));
                            }
                        }else{//产品赎回比例校验
                            if(StringUtil.isNotEmpty(ftr1.getLightValue()) && (ftr1.getLightValue().intValue()<0 || ftr1.getLightValue().intValue()>100)){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0005",getMessage("FlowRiskSceneManage002.lightValue"))));
                            }
                            if(StringUtil.isNotEmpty(ftr1.getMediumValue()) && (ftr1.getMediumValue().intValue()<0 || ftr1.getMediumValue().intValue()>100)){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0005",getMessage("FlowRiskSceneManage002.mediumValue"))));
                            }
                            if(StringUtil.isNotEmpty(ftr1.getHeavyValue()) && (ftr1.getHeavyValue().intValue()<0 || ftr1.getHeavyValue().intValue()>100)){
                                errorList.add(new ValidMessage("", getMessage("RSK_W_F_0005",getMessage("FlowRiskSceneManage002.heavyValue"))));
                            }
                        }
                        if(!StringUtil.isNotEmpty(ftr1.getLightValue()) && !StringUtil.isNotEmpty(ftr1.getMediumValue()) && !StringUtil.isNotEmpty(ftr1.getHeavyValue())){
                            flag++;
                        }
                    }
                }
                if(StringUtil.isNotEmpty(dto.getFtrList2()) && dto.getFtrList2().size() != 0){
                	FlowRiskSceneManageListDto ftr2 = dto.getFtrList2().get(0);
                    if(StringUtil.isEmpty(ftr2.getFtrId())){
                        errorList.add(new ValidMessage("", getMessage("RSK_W_0017",getMessage("FlowRiskSceneManage002.ftrId"))));
                    }else{//二级流动性资产变化率校验
                        if(!StringUtil.isNotEmpty(ftr2.getLightValue()) && !StringUtil.isNotEmpty(ftr2.getMediumValue()) && !StringUtil.isNotEmpty(ftr2.getHeavyValue())){
                            flag++;
                        }
                        if(StringUtil.isNotEmpty(ftr2.getLightValue()) && (ftr2.getLightValue().intValue()<0 || ftr2.getLightValue().intValue()>100)){
                            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0006",getMessage("FlowRiskSceneManage002.lightValue"))));
                        }
                        if(StringUtil.isNotEmpty(ftr2.getMediumValue()) && (ftr2.getMediumValue().intValue()<0 || ftr2.getMediumValue().intValue()>100)){
                            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0006",getMessage("FlowRiskSceneManage002.mediumValue"))));
                        }
                        if(StringUtil.isNotEmpty(ftr2.getHeavyValue()) && (ftr2.getHeavyValue().intValue()<0 || ftr2.getHeavyValue().intValue()>100)){
                            errorList.add(new ValidMessage("", getMessage("RSK_W_F_0006",getMessage("FlowRiskSceneManage002.heavyValue"))));
                        }
                    }
                }
                if(flag == 2){
                    errorList.add(new ValidMessage("",getMessage("RSK_W_F_0007")));
                }
                //情景名称唯一性校验
                if(StringUtil.isEmpty(dto.getSceneName())){
                    errorList.add(new ValidMessage("",getMessage("RSK_W_F_0008",getMessage("FlowRiskSceneManage002.sceneName"))));
                }
                //情景二级类型不为空
                if(StringUtil.isEmpty(dto.getSceneType2())){
                    //errorList.add(new ValidMessage("",getMessage("RSK_W_F_0008",getMessage("FlowRiskSceneManage002.riskType2"))));
                }
                //适用产品不为空
                if(StringUtil.isEmpty(dto.getOpenmode())){
                    //errorList.add(new ValidMessage("",getMessage("RSK_W_F_0008",getMessage("FlowRiskSceneManage002.applyFin"))));
                }
                if(StringUtil.isNotEmpty(dto.getSceneName()) && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                    int count = flowRiskSceneManage002Service.queryScentNameCount(dto.getSceneName());
                    if(count != 0){
                        errorList.add(new ValidMessage("",getMessage("RSK_W_F_0009",getMessage("FlowRiskSceneManage002.sceneName"))));
                    }
                }
                if(StringUtil.isNotEmpty(dto.getSceneName()) && StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                	if(!StringUtil.equals(dto.getSceneName(), dto.getOldSceneName())){
                		int count = flowRiskSceneManage002Service.queryScentNameCount(dto.getSceneName());
                        if(count != 0){
                            errorList.add(new ValidMessage("",getMessage("RSK_W_F_0009",getMessage("FlowRiskSceneManage002.sceneName"))));
                        }
                	}
                }
                //编辑模式
            }else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                
            }
//        }

        return errorList;
    }

}
