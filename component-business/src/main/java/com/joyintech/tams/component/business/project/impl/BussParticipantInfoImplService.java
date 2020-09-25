/*
 * 文件名：BussParticipantInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.project.BussParticipantInfoService;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 参与方信息 
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussParticipantInfoImplService
 * @since 1.0
 */
@Service
public class BussParticipantInfoImplService extends BaseService<BussParticipantInfoDto> implements BussParticipantInfoService{


    /**
     * {@inheritDoc}
     */
    @Override
    public BussParticipantInfoDto queryAllinfoByfinprodId(Map<String,Object> paramMap) {
        return restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussParticipantInfo/queryAllinfoByfinprodId", paramMap, BussParticipantInfoDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussParticipantInfoDto> queryBussParticipantInfo(Map<String, Object> paramMap) {
        return restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussParticipantInfo/queryBussParticipantInfo",
            paramMap,BussParticipantInfoDto.class);
    }
    
    /**
     * 汇总通道费
     */
    @Override
    public BigDecimal sumChannelFee(List<BussParticipantInfoDto> list){
        BigDecimal channelFee = BigDecimal.ZERO;
        for(BussParticipantInfoDto dto:list){
            channelFee = ArithmeticUtil.doAdd(12,null,channelFee,dto.getClientYield(),dto.getManagerYield(),dto.getOtherYield());
        }
        return channelFee;
    }
    /**
     * 通过通道代码获取托管账号下拉框值
     * {@inheritDoc}
     */
    public  List<SelectOptionDto> selectAcctNoByCounterId(Map<String,Object> paramMap){
        return restTemplate.postForList(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussParticipantInfo/selectAcctNoByCounterId",paramMap,
            SelectOptionDto.class); 
    }
}
