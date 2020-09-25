/*
 * 文件名：InterfLC0008Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0008.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.ilc0007.dao.InterfLc0007Mapper;
import com.joyintech.service.interf.ilc0007.entity.InterfLc0007OutEntity;
import com.joyintech.service.interf.ilc0008.dao.InterfLC0008Mapper;
import com.joyintech.service.interf.ilc0008.entity.InterfLc0008InEntity;

/**
 * 信贷卖出余额展期推送接口Lc0008处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月13日
 * @see InterfLC0008Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0008)
public class InterfLC0008Service extends ReceivedEsbService<InterfLc0007OutEntity, InterfLc0008InEntity> {

    /**
     * 信贷卖出余额展期推送接口Lc0008mapper
     */
    @Autowired
    private InterfLC0008Mapper interfLc0008Mapper;

    /**
     * 实时查询余额更新接口Lc0007mapper
     */
    @Autowired
    private InterfLc0007Mapper interfLc0007Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLc0007OutEntity selectResponseData(InterfLc0008InEntity entity)
        throws Exception {
        // 异常检测
        InterfLc0007OutEntity outEntity = checkExce(entity);
        // 设置接口输出entity
        entity.setCdate(getSysDate());
        outEntity.setDuebillbalance(interfLc0007Mapper.selectDuebillBalanceAmount(entity));
        outEntity.setSellbalance("0");
        return outEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLc0008InEntity entity)
        throws Exception {
        // 异常检测
        checkExce(entity);
        // 接口表更新
        String expirydate = entity.getExpirydate();
        // 判断是否展期更新
        if (StringUtil.isNotEmpty(expirydate)) {
            interfLc0008Mapper.updateData(entity);
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLc0008InEntity entity)
        throws Exception {
        // 不登录业务表
        return 0;
    }

    /**
     * 放款指令编号，业务品种编号，借据号异常检测
     * 
     * @param entity 信贷卖出余额展期推送LC0008输入entity
     * @return 信贷卖出余额展期推送LC0008输出entity
     * @throws InterfException 接口异常
     * @see
     * @since 1.0
     */
    private InterfLc0007OutEntity checkExce(InterfLc0008InEntity entity)
        throws InterfException {
        // 判断 放款指令编号 与 业务品种编号 是否正确
        if (interfLc0007Mapper.selectCmsInfoCnt(entity) < 1) {
            throw new InterfException("放款指令编号（" + entity.getPutoutorderno() + "）或业务品种编号（"
                                      + entity.getBussvarietyno() + "）错误");
        }
        // 判断 放款指令编号 与 借据号 是否正确
        InterfLc0007OutEntity outEntity = interfLc0007Mapper.selectDuebillAmt(entity);
        if (outEntity == null) {
            throw new InterfException("放款指令编号（" + entity.getPutoutorderno() + "）或借据号流水号（"
                                      + entity.getDuebillseqno() + "）错误");
        }
        return outEntity;
    }
}
