/*
 * 文件名：OpicsBondHeadService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.opics.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.opics.dao.OpicsBondHeadMapper;

/**
 * OPICS-债券头寸数据接入
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月21日
 * @see OpicsBondHeadService
 * @since 1.0
 */
@Service
public class OpicsBondHeadService extends OpicsService {

    /**
     * OPICS-债券寸头更新Mapper
     */
    @Autowired
    private OpicsBondHeadMapper opicsMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveData()
        throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put(InterfConstant.EFFECTFLAG, InterfDictCodeList.COST有效标识_有效.getValue());
        opicsMapper.saveOpicsData(params);
    }

}
