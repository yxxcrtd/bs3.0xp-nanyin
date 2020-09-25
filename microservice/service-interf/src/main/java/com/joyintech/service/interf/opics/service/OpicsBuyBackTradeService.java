/*
 * 文件名：OpicsBuyBackTradeService.java
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

import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.opics.dao.OpicsBuyBackTradeMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * OPICS-回购交易数据接入
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月21日
 * @see OpicsBuyBackTradeService
 * @since 1.0
 */
@Service
public class OpicsBuyBackTradeService extends OpicsService {

    /**
     * OPICS-债券回购更新Mapper
     */
    private OpicsBuyBackTradeMapper opicsMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveData()
        throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put(InterfConstant.CDATE, getSysDate());
        params.put("type", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        params.put(InterfConstant.EFFECTFLAG, InterfDictCodeList.COST有效标识_有效.getValue());
        // 数据登录回购交易详细信息接口表
        opicsMapper.saveOpicsData(params);
        // 更新冲销数据
        opicsMapper.updateRevDate(params);
        // 同步回购抵押品表
        opicsMapper.saveRepoasstData(params);
        // 将交易的券面总额改成汇总抵押品的券面总额
        opicsMapper.updateTradDate(params);

    }

}
