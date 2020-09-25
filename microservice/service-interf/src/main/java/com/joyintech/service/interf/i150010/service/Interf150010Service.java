/*
 * 文件名：Interf150010Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150010.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.SendEsbService;
import com.joyintech.service.interf.i150010.dao.Interf150010Mapper;
import com.joyintech.service.interf.i150010.entity.Interf150010InArrEntity;
import com.joyintech.service.interf.i150010.entity.Interf150010InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfFinProdDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资管费用信息接口（150010）详细处理
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf150010Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_150010_00)
public class Interf150010Service extends SendEsbService<Interf150010InEntity, Interf150010InEntity> {

    /**
     * 资管费用信息接口（150010）-查询Mapper
     */
    @Autowired
    private Interf150010Mapper selectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Interf150010InEntity preparedLocalData(Object param)
        throws Exception {
        InterfFinProdDto dto = (InterfFinProdDto)param;
        List<Interf150010InArrEntity> arrList = selectFeeInfo(dto.getFinProdId());
        // 如果费用信息不存在时则返回空
        if (StringUtil.isEmptyList(arrList)) {
            return null;
        }
        // 设置发送报文内容
        Interf150010InEntity inEntity = new Interf150010InEntity();
        // 产品代码
        inEntity.setProdId(arrList.get(0).getProdId());
        // 母产品代码
        inEntity.setMProdId(arrList.get(0).getMProdId());
        // 费项信息
        inEntity.setFeeList(arrList);
        return inEntity;
    }

    /**
     * 查询产品费用信息
     * 
     * @param finProdId 金融产品代码
     * @return 产品费用信息
     */
    public List<Interf150010InArrEntity> selectFeeInfo(String finProdId) {
        return selectMapper.selectFeeInfo(finProdId, DictCodeListExt1.现金流一级类型_费用.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(Interf150010InEntity entity)
        throws Exception {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(Interf150010InEntity entity)
        throws Exception {
        return 0;
    }
}
