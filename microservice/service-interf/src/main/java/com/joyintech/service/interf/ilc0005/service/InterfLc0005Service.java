/*
 * 文件名：InterfLc0005Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.ilc0005.dao.InterfLc0005Mapper;
import com.joyintech.service.interf.ilc0005.entity.InterfLc0005InEntity;
import com.joyintech.service.interf.ilc0005.entity.InterfLc0005OutEntity;
import com.joyintech.service.interf.ilc0005.entity.InterfLc0005OutLvlEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 理财产品报价报价查询接口Lc0005处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see InterfLc0005Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_LC0005)
public class InterfLc0005Service extends ReceivedEsbService<InterfLc0005OutEntity, InterfLc0005InEntity> {

    /**
     * 理财产品报价报价查询接口Lc0005mapper
     */
    @Autowired
    private InterfLc0005Mapper interfLC0005Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public InterfLc0005OutEntity selectResponseData(InterfLc0005InEntity entity)
        throws Exception {
        // 1.查询金融产品是否存在
        entity.setProdType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        String finprodid = interfLC0005Mapper.selectFinprodidCount(entity);
        if (finprodid == null) {
            throw new InterfException(entity.getProductcode() + "产品信息不存在！");
        }
        // 2.查询金融产品
        entity.setFinprodid(finprodid);
        entity.setcDate(getSysDate());
        entity.setCashType2(DictCodeListExt1.现金流二级类型_销售费.getValue());
        InterfLc0005OutEntity outEntity = interfLC0005Mapper.selectFinprodid(entity);
        List<InterfLc0005OutLvlEntity> lvllist = null;
        // 3.查询分层信息
        entity.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        if (StringUtil.equals(outEntity.getIslay(), DictCodeList.是否_是.getValue())) {
            // 分层存在
            lvllist = interfLC0005Mapper.selectIslayInfo(entity);
        }
        else {
            // 分层不存在
            lvllist = interfLC0005Mapper.selectNotlayInfo(entity);
        }
        outEntity.setLvllist(lvllist);
        return outEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveInterfData(InterfLc0005InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int saveBusinessData(InterfLc0005InEntity entity)
        throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

}
