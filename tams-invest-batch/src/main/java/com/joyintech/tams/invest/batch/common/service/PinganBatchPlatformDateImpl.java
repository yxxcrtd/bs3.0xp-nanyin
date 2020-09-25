///*
// * 文件名：PinganBatchPlatformDateImpl.java
// * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
// * 描述：理财资管管理系统V2.0
// * 修改人：刘二通
// * 修改时间：2018年11月21日
// * 修改内容：新建
// * 系统名称：理财资管管理系统V2.0
// */
//
//package com.joyintech.tams.invest.batch.common.service;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.joyintech.framework.web.PlatformDate;
//import com.joyintech.tams.invest.batch.common.dao.PinganBatchPlatformDateImplMapper;
//
///**
// * 平台日期组件实现类
// *
// * @author 刘二通
// * @version 1.0 2018年11月21日
// * @see PinganBatchPlatformDateImpl
// * @since 1.0
// */
//@Service
//public class PinganBatchPlatformDateImpl implements PlatformDate {
//
//    /**
//     * PinganBatchPlatformDateImplMapper
//     */
//    @Autowired
//    private PinganBatchPlatformDateImplMapper platformDateImplMapper;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Date getSysDate() {
//        return platformDateImplMapper.getSysDate();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int updateSysDate(Date newDate) {
//        return platformDateImplMapper.updateSysDate(newDate);
//    }
//
//    /**
//     * 获取数据库日期
//     * <功能详细描述>
//     * @return 日期
//     * @see
//     * @since 1.0
//     */
//    public Date getDbDate() {
//        return platformDateImplMapper.getDbDate();
//    }
//}
