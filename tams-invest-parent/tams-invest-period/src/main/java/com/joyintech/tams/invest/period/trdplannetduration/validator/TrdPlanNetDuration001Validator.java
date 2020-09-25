/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdplannetduration.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdplannetduration.dto.TrdPlanNetDuration001Dto;
import com.joyintech.tams.invest.period.trdplannetduration.service.TrdPlanNetDuration001Service;

/**
 一句话功能简述〉
 * 〈功能详细描述〉* 〈
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdPlanNetDuration001Validator
 * @since 1.0
 */
@Component
public class TrdPlanNetDuration001Validator implements BeanValidator{
    /**
     * service
     */
    @Autowired
    private TrdPlanNetDuration001Service trdPlanNetDuration001Service;

    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
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
        TrdPlanNetDuration001Dto dto = (TrdPlanNetDuration001Dto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals("revoke",dto.getMode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tradeId",dto.getTotleTradeId());
            map.put("ps", DictCodeList.交易方向_红利再投.getValue());
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdPlanNetDuration001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
//            check流程中（防止重复撤销）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdPlanNetDuration002");
//            设置原交易编号
            wflValidInputParameter.setBusinessDataPk4(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
            periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(),dto.getTotleTradeId(),messageList);
        }
//        确认操作：从001的操作按钮发起进入
        else if(StringUtil.equals("edit",dto.getMode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tradeId",dto.getTotleTradeId());
            String ps = StringUtil.equals("03",dto.getType())?DictCodeList.交易方向_现金分红.getValue():DictCodeList.交易方向_红利再投.getValue();
            map.put("ps",ps);
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdPlanNetDuration001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
            //            check流程中（防止重复保存）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdPlanNetDuration002");
//            设置前置交易编号
            wflValidInputParameter.setBusinessDataPk3(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
        }
        else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
            if (CollectionUtils.isEmpty(dto.getFinAssetIntInfoList())) {
                messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("PERIOD_E_0005")));
            }
            if(!StringUtil.isNotEmpty(dto.getRegistDate())){
                messageList.add(new ValidMessage("registDate",getMessage("FW_E_0006",getMessage("TrdPlanNetDuration001.registDate"))));
            }
            if(!StringUtil.isNotEmpty(dto.getExRightsDate())){
                messageList.add(new ValidMessage("exRightsDate",getMessage("FW_E_0006",getMessage("TrdPlanNetDuration001.exRightsDate"))));
            }
//            判断除权日是否为空
            if(StringUtil.isNotEmpty(dto.getExRightsDate())){
//                分红到账日check
                if(StringUtil.isNotEmpty(dto.getArriveDate())&&DateUtil.compareDate(dto.getExRightsDate(),dto.getArriveDate())>0){
                    messageList.add(new ValidMessage("arriveDate",getMessage("FIN_PROD_E_0017",getMessage("TrdPlanNetDuration001.exRightsDate"),getMessage("TrdPlanNetDuration001.arriveDate"))));
                }
//                红利再投日check
                if(StringUtil.isNotEmpty(dto.getReInvesDate())&&DateUtil.compareDate(dto.getExRightsDate(),dto.getReInvesDate())>0){
                    messageList.add(new ValidMessage("reInvesDate",getMessage("FIN_PROD_E_0017",getMessage("TrdPlanNetDuration001.exRightsDate"),getMessage("TrdPlanNetDuration001.reInvesDate"))));
                }
            }
        }
        return messageList;
    }
}
