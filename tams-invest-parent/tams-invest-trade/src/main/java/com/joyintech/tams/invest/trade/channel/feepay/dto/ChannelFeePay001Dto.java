/*
 * 文件名：ChannelAssetQueryDto.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：yangjm 修改时间：2019年3月13日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.channel.feepay.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeePayDto;

import java.util.Date;

/**
 * 通道资产查询 〈功能详细描述〉
 * 
 * @author yangjm
 * @version 1.0 2019年3月13日
 * @see ChannelFeePay001Dto
 * @since 1.0
 */
public class ChannelFeePay001Dto extends ChannelFeePayDto {
    
    
    public String getFeeNameString() {
        return SysDataUtil.getNameFromDataDict(DictCode.一次性费用类型, super.getFeeName());
    }

    public String getMdateString() {
        return DateUtil.format(super.getMdate());
    }

    public String getSettleDateString() {
        return DateUtil.format(super.getSettleDate());
    }
    public String getEffectStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态, super.getEffectStatus());
    }
}
