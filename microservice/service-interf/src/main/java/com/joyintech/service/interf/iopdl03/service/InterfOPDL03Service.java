/*
 * 文件名：InterfOPDL03Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iopdl03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.tams.common.base.dao.InterfSendOpicsMapper;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.iopdl03.entity.InterfOPDL03InEntity;
import com.joyintech.service.interf.iopdl03.entity.InterfOPDL03OutEntity;
import com.joyintech.service.interf.trade.dto.TradeClearDto;

/**
 * 投管系统向OPICS系统发送拆借交易信息处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月19日
 * @see InterfOPDL03Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_OPDL03)
public class InterfOPDL03Service extends SendEsbService<InterfOPDL03InEntity, InterfOPDL03OutEntity> {

    /**
     * 系统向OPICS系统发送拆借交易信息查询mapper
     */
    @Autowired
    private InterfSendOpicsMapper sendOpicsMapper;

    /**
     * 主键
     */
    private String key;

    /**
     * 发送次数
     */
    private int sendNum;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfOPDL03InEntity preparedLocalData(Object param)
        throws Exception {
        TradeClearDto dto = (TradeClearDto)param;
        key = dto.getOrderOrTradeNo();
        InterfSendOpics sendOpicsInfo = sendOpicsMapper.selectByPrimaryKey(key);
        sendNum = sendOpicsInfo == null ? 0 : sendOpicsInfo.getSendnum();
        return sendOpicsInfo == null ? null : new InterfOPDL03InEntity(sendOpicsInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(CompositeData rspData, boolean sendOK, boolean receiveOK) {
        // 更新发送结果
        InterfSendOpics sendOpicsInfo = new InterfSendOpics();
        sendOpicsInfo.setUuid(key);
        sendOpicsInfo.setSendStatus(
            sendOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        sendOpicsInfo.setOrderRecStatus(
            receiveOK ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        sendOpicsInfo.setSendnum(sendNum + 1);
        sendOpicsMapper.updateByPrimaryKeySelective(sendOpicsInfo);
        super.updateStatus(rspData, sendOK, receiveOK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfOPDL03OutEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfOPDL03OutEntity entity)
        throws Exception {
        return 0;
    }

}
