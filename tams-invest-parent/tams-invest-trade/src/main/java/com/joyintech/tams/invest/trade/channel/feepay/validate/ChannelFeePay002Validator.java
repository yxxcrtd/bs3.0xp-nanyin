/*
 * 文件名：FinDisclosureModuleValidator.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：qiangchengxi
 * 修改时间：2018年8月24日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.validate;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.validator.CommonValidator;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeeInfoDto;
import com.joyintech.tams.invest.trade.channel.feepay.dto.ChannelFeePay002Dto;
import com.joyintech.tams.invest.trade.channel.feepay.service.ChannelFeePay002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiangchengxi
 * @version 1.0 2018年8月24日
 * @see ChannelFeePay002Validator
 * @since 1.0
 */
@Component
public class ChannelFeePay002Validator implements CommonValidator {

    @Autowired
    private ChannelFeePay002Service channelFeePay002Service;
    /**
     * 校验
     * 
     * @param value 页面数据
     * @return 校验结果
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        ChannelFeePay002Dto dto = (ChannelFeePay002Dto)value;
        List<ValidMessage> resultList = new ArrayList<>();
        // 该通道在这个计提结束日的费用是否已支付
        //判断时增加组合条件
        for(ChannelFeeInfoDto ChannelFeeInfo:dto.getFeeInfoList()){
            String tradeId = channelFeePay002Service.findChannelFeePaied(dto.getFinprodId(), dto.getMdate(), ChannelFeeInfo.getPortfolioId());
            if (StringUtil.isNotEmpty(tradeId)) {
                resultList.add(new ValidMessage("该通道在这个计提结束日的费用已支付，交易编号：" + tradeId));
            }
        }
//        String tradeId = channelFeePay002Service.findChannelFeePaied(dto.getFinprodId(), dto.getMdate(), dto.getPortfolioId());
//        if (StringUtil.isNotEmpty(tradeId)) {
//            resultList.add(new ValidMessage("该通道在这个计提结束日的费用已支付，交易编号：" + tradeId));
//        }
        if (dto.getBussTrdTransferOrder() == null || dto.getBussTrdTransferOrder().isEmpty()) {
            resultList.add(new ValidMessage("请生成划款指令。"));
        }
        //若所选合作资产的费用设置为 与回款无关，计提结束日必填
        String collectType = channelFeePay002Service.getCollectType(dto.getFinprodMarketId());
        if(StringUtil.equals(collectType, "02")) {
        	 if (!StringUtil.isNotEmpty(dto.getMdate())) {
        		 resultList.add(new ValidMessage("请输入计提结束日。"));
        	 }
        }
        if(StringUtil.isEmpty(dto.getIsClean())){
            resultList.add(new ValidMessage("请输入是否结清。"));
        }
        return resultList;
    }
}
