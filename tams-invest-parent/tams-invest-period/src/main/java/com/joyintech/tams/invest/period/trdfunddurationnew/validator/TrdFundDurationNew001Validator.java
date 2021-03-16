/*
 * 文件名：TrdDepoDuration001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dingzhipeng
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.period.trdfunddurationnew.validator;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.trdfunddurationnew.dto.TrdFundDurationNew001Dto;
import com.joyintech.tams.invest.period.trdfunddurationnew.service.TrdFundDurationNew001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基金Validator
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年10月11日
 * @see TrdFundDurationNew001Validator
 * @since 1.0
 */
@Component
public class TrdFundDurationNew001Validator implements BeanValidator{
    /**
     * service
     */
    @Autowired
    private PeriodCommonService periodCommonService;
    /**
     * service
     */
    @Autowired
    private TrdFundDurationNew001Service trdFundDurationNew001Service;
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
        TrdFundDurationNew001Dto dto = (TrdFundDurationNew001Dto)value;
        List<ValidMessage> messageList = new ArrayList<>();
        if(StringUtil.equals("revoke",dto.getMode())){
            Map<String,Object> map = new HashMap<>();
            map.put("tradeId",dto.getTotleTradeId());
            map.put("ps", DictCodeList.交易方向_红利再投.getValue());
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdFundDurationNew001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
//            check流程中（防止重复撤销）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdFundDuration002");
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
            String ps = StringUtil.equals("03",dto.getType())?DictCodeList.交易方向_现金分红.getValue():DictCodeList.交易方向_红利再投.getValue();
            map.put("ps",ps);
            map.put("yes_no", DictCodeList.是否_否.getValue());
            map.put("trade_status", DictCodeListExt1.交易状态_初始.getValue());
            if(trdFundDurationNew001Service.countFollowDeal(map)>0){
                messageList.add(new ValidMessage(getMessage("FIN_ASSET_E_0031")));
            }
            //check流程中（防止重复保存）
            WflValidInputParameter wflValidInputParameter = new WflValidInputParameter();
            wflValidInputParameter.setPageId("TrdFundDuration002New");
//            设置前置交易编号
            wflValidInputParameter.setBusinessDataPk3(dto.getTotleTradeId());
            if(StringUtil.isNotEmpty(mstWorkflowCommonServie)&&mstWorkflowCommonServie.isInWflProcess(wflValidInputParameter)){
                messageList.add(new ValidMessage(getMessage("BUSS_COM_E_0023")));
            }
        }
        else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
            if(StringUtil.equals(dto.getAssetType(),DictCodeList.基金类资产类型_非货币基金.getValue())){
                if(!StringUtil.isNotEmpty(dto.getRegistDate())){
                    messageList.add(new ValidMessage("registDate",getMessage("FW_E_0006",getMessage("TrdFundDurationNew001.registDate"))));
                }
                if(!StringUtil.isNotEmpty(dto.getExRightsDate())){
                    messageList.add(new ValidMessage("exRightsDate",getMessage("FW_E_0006",getMessage("TrdFundDurationNew001.exRightsDate"))));
                }
//            判断除权日是否为空
                if(StringUtil.isNotEmpty(dto.getExRightsDate())){
//                分红到账日check
                    if(StringUtil.isNotEmpty(dto.getArriveDate())&& DateUtil.compareDate(dto.getExRightsDate(),dto.getArriveDate())>0){
                        messageList.add(new ValidMessage("arriveDate",getMessage("FIN_PROD_E_0017",getMessage("TrdFundDurationNew001.exRightsDate"),getMessage("TrdFundDurationNew001.arriveDate"))));
                    }
//                红利再投日check
                    if(StringUtil.isNotEmpty(dto.getReInvesDate())&&DateUtil.compareDate(dto.getExRightsDate(),dto.getReInvesDate())>0){
                        messageList.add(new ValidMessage("reInvesDate",getMessage("FIN_PROD_E_0017",getMessage("TrdFundDurationNew001.exRightsDate"),getMessage("TrdFundDurationNew001.reInvesDate"))));
                    }
                }
            }
            //if (StringUtil.equals(dto.getType(),"03") || StringUtil.equals(dto.getType(),"06")) {
            if(StringUtil.equals(dto.getPstype(),"01")){
                if (dto.getBussTrdTransferOrder() == null ||  dto.getBussTrdTransferOrder().isEmpty()) {
                    messageList.add(new ValidMessage("bussTrdTransferOrder","请生成划款指令"));
                }
                else if (StringUtil.isEmpty(dto.getBussTrdTransferOrder().get(0).getRecAcctNo())) {
                    messageList.add(new ValidMessage("bussTrdTransferOrder","收款账户不能为空"));
                }
            }
            if(!StringUtil.equals(dto.getMode(),"04")){
                if(!StringUtil.isNotEmpty(dto.getTradeAmt())){
                    messageList.add(new ValidMessage("tradeAmt",getMessage("FW_E_0006",getMessage("TrdFundDurationNew001.tradeAmt"))));
                }
            }

            Map<String,FinAssetIntInfo> checkListMap=new HashMap<String,FinAssetIntInfo>();
            for(FinAssetIntInfo finAssetIntInfo : dto.getFinAssetIntInfoList()){
                if(StringUtil.isEmpty(finAssetIntInfo.getBonusMode())){
                    messageList.add(new ValidMessage("分红类型不能为空!"));
                    break;
                }
                String keytemp = finAssetIntInfo.getPortfolioId()+finAssetIntInfo.getSecAcctId()+finAssetIntInfo.getChIld()+finAssetIntInfo.getBonusMode();
                if(checkListMap.containsKey(keytemp)){
                    messageList.add(new ValidMessage("同一个组合、同一个证券账户/通道不能存在相同分红类型!"));
                    break;
                }else{
                    checkListMap.put(keytemp, finAssetIntInfo);
                }
            }
        }
        return messageList;
    }
}
