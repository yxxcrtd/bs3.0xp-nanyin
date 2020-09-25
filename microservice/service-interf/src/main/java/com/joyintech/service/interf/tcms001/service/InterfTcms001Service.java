/*
 * 文件名：Tcms001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfTcms001Mapper;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.tcms001.dao.Tcms001Mapper;
import com.joyintech.service.interf.tcms001.entity.InterfTcms001InEntity;

/**
 * 小资平台产品成立信息接收处理详细
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see InterfTcms001Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_TCMS001)
public class InterfTcms001Service extends ReceivedEsbService<InterfTcms001InEntity, InterfTcms001InEntity> {

    /**
     * 小资平台产品成立信息mapper
     */
    @Autowired
    private InterfTcms001Mapper mappper;

    /**
     * tcms001删除接口表信息mapper
     */
    @Autowired
    private Tcms001Mapper tcMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfTcms001InEntity selectResponseData(InterfTcms001InEntity entity)
        throws Exception {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfTcms001InEntity entity)
        throws Exception {
        // 设置平台日，接口号
        entity.setCdate(getSysDate());
        entity.setInterfId(getInterfId());
        // 如果数据已存在，先删除，后再插入
        tcMapper.deleteByPrdid(entity.getProdid());
        return mappper.insertSelective(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfTcms001InEntity entity)
        throws Exception {
        return 0;
    }

}
