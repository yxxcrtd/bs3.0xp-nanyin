/*
 * 文件名：InterfSMS004SendService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.isms004.service;

import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbHeadEnum;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.isms003.entity.InterfSMS003Entity;
import com.joyintech.service.interf.isms004.entity.InterfSMS004Entity;

/**
 * SMS004文件通知：调用该接口文件上传BFTS之后通知其他系统下载文件
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月16日
 * @see InterfSMS004SendService
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_SMS004)
public class InterfSMS004SendService extends SendEsbService<InterfSMS004Entity, InterfSMS003Entity> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompositeData createRequestData(InterfSMS004Entity param) {
        CompositeData data = super.createRequestData(param);
        CompositeData sysHead = data.getStruct(InterfConstant.SYS_HEAD);
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_文件路径, param.getFilePath());
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfSMS004Entity preparedLocalData(Object param)
        throws Exception {
        InterfSMS004Entity inEntity = (InterfSMS004Entity)param;
        inEntity.setCosumerId(getConsumerId());
        inEntity.setFileRcdNum(1);
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfSMS003Entity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfSMS003Entity entity)
        throws Exception {
        return 0;
    }

}
