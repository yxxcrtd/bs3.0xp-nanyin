/*
 * 文件名：Interf291002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i291002.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.InterfNetProdBuyDrawMapper;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i291002.dao.Interf291002Mapper;
import com.joyintech.service.interf.i291002.entity.Interf291002InEntity;
import com.joyintech.service.interf.i291002.entity.Interf291002OutArrEntity;
import com.joyintech.service.interf.i291002.entity.Interf291002OutEntity;
import com.joyintech.service.interf.prodtrade.dao.ProdIsLayMapper;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;

/**
 * 净值型产品申购赎回确认数据查询（291002）-详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月19日
 * @see Interf291002Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_291002_00)
public class Interf291002Service extends SendEsbService<Interf291002InEntity, Interf291002OutEntity> {

    /**
     * 产品成立查询
     */
    @Autowired
    private ProdIsLayMapper prodMapper;

    /**
     * 净值型产品申赎确认信息登录Mapper
     */
    @Autowired
    private InterfNetProdBuyDrawMapper insertMapper;

    /**
     * 净值型产品申购赎回确认数据查询（291002）删除Mapper
     */
    @Autowired
    private Interf291002Mapper deleteMapper;

    /**
     * 查询日期
     */
    private String cDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf291002InEntity preparedLocalData(Object param)
        throws Exception {
        InterfFinProdDto dto = (InterfFinProdDto)param;
        InterfAliveProdEntity<?> prodInfo = prodMapper.selectProdInfo(dto.getFinProdId());
        // 当产品不存在时返回空
        if (null == prodInfo) {
            return null;
        }
        cDate = dto.getCdate();
        cDate = StringUtil.isEmpty(cDate) ? getSysDate() : cDate;
        Interf291002InEntity inEntity = new Interf291002InEntity();
        // 产品代码
        inEntity.setProdId(prodInfo.getProdid());
        // 查询日期，当日期为空时使用平台日期
        inEntity.setCdate(cDate);
        return inEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf291002OutEntity entity)
        throws Exception {
        int result = 0;
        entity.setCdate(cDate);
        deleteMapper.deleteInfo(entity);
        for (Interf291002OutArrEntity item : entity.getInfoList()) {
            BeanUtil.copyPropertiesIgnoreNull(item, entity);
            result += insertMapper.insertSelective(item);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf291002OutEntity entity)
        throws Exception {
        return 0;
    }
}
