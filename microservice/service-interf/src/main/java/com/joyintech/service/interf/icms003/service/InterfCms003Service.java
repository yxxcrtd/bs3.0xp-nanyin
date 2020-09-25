/*
 * 文件名：InterfCms003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icms003.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.tams.common.base.dao.InterfCreditItemInfoMapper;
import com.joyintech.tams.common.base.entity.InterfCreditItemInfo;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.icms003.entity.InterfCms003InEntity;
import com.joyintech.service.interf.trade.dto.TradeClearDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * CMS出账信息撤销接口详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月15日
 * @see InterfCms003Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_CMS003)
public class InterfCms003Service extends SendEsbService<InterfCms003InEntity, InterfCms003InEntity> {

    /**
     * 信贷项目信息更新Mapper
     */
    @Autowired
    private InterfCreditItemInfoMapper interfMapper;

    /**
     * 接口输入
     */
    private InterfCms003InEntity inEntity;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfCms003InEntity preparedLocalData(Object param)
        throws Exception {
        TradeClearDto dto = (TradeClearDto)param;
        inEntity = new InterfCms003InEntity();
        // 出账编号
        inEntity.setOrderSeqNo(dto.getOrderOrTradeNo());
        // 撤销原因
        inEntity.setReason(dto.getRemark());
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus(CompositeData rspData, boolean sendOK, boolean receiveOK) {
        // 更新撤销状态
        InterfCreditItemInfo info = new InterfCreditItemInfo();
        info.setCashOutNo(inEntity.getOrderSeqNo());
        info.setReason(inEntity.getReason());
        info.setIsRevoke(
            receiveOK ? DictCodeList.是否_是.getValue() : DictCodeList.是否_否.getValue());
        interfMapper.updateByPrimaryKeySelective(info);
        super.updateStatus(rspData, sendOK, receiveOK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfCms003InEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfCms003InEntity entity)
        throws Exception {
        return 0;
    }
}
