/*
 * 文件名：OccupyDbService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.dao.RskDimEntrustInfoMapper;
import com.joyintech.tams.common.base.dao.RskOccupyInfoMapper;
import com.joyintech.tams.common.base.dao.RskPreOccupyInfoMapper;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;

/**
 * 登录预占实占信息
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月29日
 * @see OccupyDbService
 * @since 1.0
 */
@Service
public class OccupyDbService {

    @Autowired
    private RskOccupyInfoMapper ocpInfoMapper;

    @Autowired
    private RskPreOccupyInfoMapper preOcpInfoMapper;

    @Autowired
    private RskDimEntrustInfoMapper dimEntrustInfoMapper;

    /**
     * 预占信息保存DB
     * 
     * @param mapData 接口输入
     * @param isApprove 是否审批
     * @param status 占用状态
     * @param user 更新人
     * @see
     * @since 1.0
     */
    public void saveOcpInfo(RskOccupyInfo record, boolean isApprove, String status, String user) {
        // 状态_占用
        record.setOcpStatus(status);
        // 流程状态
        record.setWflStatus(
            isApprove ? BussinessDictCodeListRds.有效状态_待处理.getValue() : BussinessDictCodeListRds.有效状态_有效.getValue());
        // 创建人
        record.setCreateUser(user);
        // 更新人
        record.setUpdateUser(user);
        Date dt = DateUtil.now();
        // 创建时间
        record.setCreateTime(dt);
        // 更新时间
        record.setUpdateTime(dt);
        // db登录
        ocpInfoMapper.insertSelective(record);
    }

    /**
     * 实占信息保存DB
     * 
     * @param mapData 接口输入
     * @param isApprove 是否审批
     * @param status 占用状态
     * @param user 更新人
     * @see
     * @since 1.0
     */
    public void savePreOcpInfo(RskPreOccupyInfo record, boolean isApprove, String status,
                               String user) {
        // 状态_释放
        record.setOcpStatus(status);
        // 流程状态
        record.setWflStatus(
            isApprove ? BussinessDictCodeListRds.有效状态_待处理.getValue() : BussinessDictCodeListRds.有效状态_有效.getValue());
        // 创建人
        record.setCreateUser(user);
        // 更新人
        record.setUpdateUser(user);
        Date dt = DateUtil.now();
        // 创建时间
        record.setCreateTime(dt);
        // 更新时间
        record.setUpdateTime(dt);
        // db登录
        preOcpInfoMapper.insertSelective(record);
    }

    /**
     * 模糊委托信息保存DB
     * 
     * @param mapData 接口输入
     * @param isApprove 是否审批
     * @param status 占用状态
     * @param user 更新人
     * @see
     * @since 1.0
     */
    public void saveDimEntrustInfo(RskDimEntrustInfo record, boolean isApprove, String user) {
        // 流程状态
        record.setWflStatus(
            isApprove ? BussinessDictCodeListRds.有效状态_待处理.getValue() : BussinessDictCodeListRds.有效状态_有效.getValue());
        // 创建人
        record.setCreateUser(user);
        // 更新人
        record.setUpdateUser(user);
        Date dt = DateUtil.now();
        // 创建时间
        record.setCreateTime(dt);
        // 更新时间
        record.setUpdateTime(dt);
        // db登录
        dimEntrustInfoMapper.insertSelective(record);
    }
}
