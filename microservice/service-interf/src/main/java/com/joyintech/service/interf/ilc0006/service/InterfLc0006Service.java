/*
 * 文件名：InterfLc0006Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0006.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfCreditItemInfoMapper;
import com.joyintech.tams.common.base.entity.InterfCreditItemInfo;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.ilc0006.entity.InterfLc0006InEntity;

/**
 * Lc0006信贷指令推送给理财系统接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0006Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0006)
public class InterfLc0006Service extends ReceivedEsbService<InterfLc0006InEntity, InterfLc0006InEntity> {

    /**
     * CMS_信贷项目信息mapper
     */
    @Autowired
    private InterfCreditItemInfoMapper interfCreditItemInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLc0006InEntity selectResponseData(InterfLc0006InEntity entity)
        throws Exception {
        // 无输出entity
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLc0006InEntity entity)
        throws Exception {
        InterfCreditItemInfo info = interfCreditItemInfoMapper.selectByPrimaryKey(
            entity.getCashOutNo());
        // 登录接口表
        // 已登录的数据不做处理
        return info == null ? interfCreditItemInfoMapper.insert(entity) : 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLc0006InEntity entity)
        throws Exception {
        // 无需登录业务表
        return 0;
    }

}
