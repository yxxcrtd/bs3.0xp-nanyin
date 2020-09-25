/*
 * 文件名：Interf190011Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i190011.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfBonusInfoMapper;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i190011.dao.Interf190011Mapper;
import com.joyintech.service.interf.i190011.entity.Interf190011InEntity;
import com.joyintech.service.interf.i190011.entity.Interf190011OutEntity;
import com.joyintech.service.interf.ta.dto.SendBonusDto;

/**
 * 净值分红信息查询接口（190011）-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf190011Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_190011_00)
public class Interf190011Service extends SendEsbService<Interf190011InEntity, Interf190011OutEntity> {

    /**
     * 净值分红信息查询接口登录Mapper
     */
    @Autowired
    private InterfBonusInfoMapper insertMapper;

    /**
     * 净值分红信息查询接口删除Mapper
     */
    @Autowired
    private Interf190011Mapper deleteMapper;

    /**
     * 查询条件
     */
    private SendBonusDto dto;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf190011InEntity preparedLocalData(Object param)
        throws Exception {
        dto = (SendBonusDto)param;
        return dto == null ? null : new Interf190011InEntity(dto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf190011OutEntity entity)
        throws Exception {
        int result = 0;
        deleteMapper.deleteBonusInfo(dto);
        for (Interf190011OutEntity item : entity.getArrList()) {
            // 销售商代码
            item.setVendorCode(dto.getVendorCode());
            // 查询起始日
            item.setQueryStartDate(dto.getQueryStartDate());
            // 查询起始日
            item.setQueryEndDate(dto.getQueryEndDate());
            // 产品代码
            item.setProdId(entity.getProdId());
            // 母产品代码
            item.setmProdId(entity.getmProdId());
            // 产品期次
            item.setProdBatchNo(entity.getProdBatchNo());
            // 页码
            item.setPageNo(entity.getPageNo());
            // 页大小
            item.setPageSize(entity.getPageSize());
            // 日期
            item.setCdate(getSysDate());
            result += insertMapper.insertSelective(item);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf190011OutEntity entity)
        throws Exception {
        return 0;
    }
}
