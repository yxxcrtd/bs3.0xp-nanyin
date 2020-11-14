/*
 * 文件名：TrdPlanCurrDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人："??????é1?"
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdplancurrduration.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfundduration.dto.TrdFundDuration001Dto;
import com.joyintech.tams.invest.period.trdfundduration.service.TrdFundDuration001Service;
import com.joyintech.tams.invest.period.trdplancurrduration.dto.TrdPlanCurrDuration001Dto;
import com.joyintech.tams.invest.period.trdplancurrduration.service.TrdPlanCurrDuration001Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author "丁志鹏"
 * @version 1.0 2019年1月30日
 * @see TrdPlanCurrDuration001Validator
 * @since 1.0
 */
@Component
public class TrdPlanCurrDuration001Validator implements BeanValidator{
	/**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * service
     */
    @Autowired
    private TrdPlanCurrDuration001Service trdPlanCurrDuration001Service;
    /**
     * service
     */
    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonServie;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdPlanCurrDuration001Dto dto = (TrdPlanCurrDuration001Dto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals("revoke",dto.getMode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tradeId",dto.getTotleTradeId());
            map.put("ps", DictCodeList.交易方向_红利再投.getValue());
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdPlanCurrDuration001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
//            check流程中（防止重复撤销）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdPlanCurrDuration002");
//            设置原交易编号
            wflValidInputParameter.setBusinessDataPk4(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
//                存在流程中数据
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(),dto.getTotleTradeId(),messageList);
        }
        else if(StringUtil.equals("edit",dto.getMode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tradeId",dto.getTotleTradeId());
            String ps = DictCodeList.交易方向_现金分红.getValue();
            map.put("ps",ps);
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdPlanCurrDuration001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
            //check流程中（防止重复保存）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdPlanCurrDuration002");
//            设置前置交易编号
            wflValidInputParameter.setBusinessDataPk3(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
        }
        else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
            if (CollectionUtils.isEmpty(dto.getFinAssetIntInfoList())) {
                messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("PERIOD_E_0005")));
            }
            if(StringUtil.equals(dto.getBonusMode(),DictCodeList.分红方式_现金分红.getValue())){
                if(!StringUtil.isNotEmpty(dto.getRegistDate())){
                    messageList.add(new ValidMessage("registDate",getMessage("FW_E_0006",getMessage("TrdFundDuration001.registDate"))));
                }
                if(!StringUtil.isNotEmpty(dto.getExRightsDate())){
                    messageList.add(new ValidMessage("exRightsDate",getMessage("FW_E_0006",getMessage("TrdFundDuration001.exRightsDate"))));
                }
                //add by gaopeng 20191213 start 分红金额check
                if(!StringUtil.isNotEmpty(dto.getTradeAmt())) {
                	messageList.add(new ValidMessage("tradeAmt",getMessage("FW_E_0006",getMessage("TrdFundDuration001.tradeAmt"))));
                }
                //add by gaopeng 20191213 end 分红金额check
//            判断除权日是否为空
                if(StringUtil.isNotEmpty(dto.getExRightsDate())){
//                分红到账日check
                    if(StringUtil.isNotEmpty(dto.getArriveDate())&& DateUtil.compareDate(dto.getExRightsDate(),dto.getArriveDate())>0){
                        messageList.add(new ValidMessage("arriveDate",getMessage("FIN_PROD_E_0017",getMessage("TrdFundDuration001.exRightsDate"),getMessage("TrdFundDuration001.arriveDate"))));
                    }
//                红利再投日check
                    if(StringUtil.isNotEmpty(dto.getReInvesDate())&&DateUtil.compareDate(dto.getExRightsDate(),dto.getReInvesDate())>0){
                        messageList.add(new ValidMessage("reInvesDate",getMessage("FIN_PROD_E_0017",getMessage("TrdFundDuration001.exRightsDate"),getMessage("TrdFundDuration001.reInvesDate"))));
                    }
                }
            }else if(StringUtil.equals(dto.getBonusMode(),DictCodeList.分红方式_红利再投.getValue())){
                if(!StringUtil.isNotEmpty(dto.getRegistDate())){
                    messageList.add(new ValidMessage("registDate",getMessage("FW_E_0006",getMessage("TrdFundDuration001.registDate"))));
                }
                if(!StringUtil.isNotEmpty(dto.getTradeAmt())) {
                    messageList.add(new ValidMessage("tradeAmt",getMessage("FW_E_0006",getMessage("TrdFundDuration001.tradeAmt"))));
                }

            }
        }
        return messageList;
    }
}
