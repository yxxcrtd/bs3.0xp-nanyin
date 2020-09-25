/*
 * 文件名：InterfLC0003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0003.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfTcms001Mapper;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.ilc0003.entity.InterfLC0003InEntity;
import com.joyintech.service.interf.tcms001.dao.Tcms001Mapper;

/**
 * 产品新建信息接口导入（LC0003）详细处理
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月16日
 * @see InterfLC0003Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0003)
public class InterfLC0003Service extends ReceivedEsbService<InterfLC0003InEntity, InterfLC0003InEntity> {

    /**
     * 创建产品接口mapper
     */
    @Autowired
    private InterfTcms001Mapper tcms001Mapper;

    /**
     * tcms001删除接口表信息mapper
     */
    @Autowired
    private Tcms001Mapper tcMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLC0003InEntity selectResponseData(InterfLC0003InEntity entity)
        throws Exception {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLC0003InEntity entity)
        throws Exception {
        // 设置平台日，接口ID
        entity.setCdate(getSysDate());
        entity.setInterfId(getInterfId());
        // 如果数据已存在，先删除，后再插入
        tcMapper.deleteByPrdid(entity.getProdid());
        return tcms001Mapper.insertSelective(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLC0003InEntity entity)
        throws Exception {
        return 0;
    }

}
