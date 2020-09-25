/*
 * 文件名：OpicsBondTradeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月20日
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
import com.joyintech.service.interf.opics.dao.OpicsBondTradeMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * OPICS-债券交易
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月20日
 * @see OpicsBondTradeService
 * @since 1.0
 */
@Service
public class OpicsBondTradeService extends OpicsService {

    /**
     * OPICS-债券交易查询Mapper
     */
    @Autowired
    private OpicsBondTradeMapper opicsMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveData()
        throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put(InterfConstant.EFFECTFLAG, InterfDictCodeList.COST有效标识_有效.getValue());
        params.put(InterfConstant.CDATE, getSysDate());
        params.put("type", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        // 同步债券交易表
        opicsMapper.saveOpicsData(params);
        // 更新冲销数据
        opicsMapper.updateRevDate(params);
        // 删除到期日为当天的交易数据
        opicsMapper.deleteMdateTrad(params);
        // 同步债券交易信息接口表
        opicsMapper.saveTradData(params);
        // 更新债券交易信息接口表冲销数据
        opicsMapper.updateIntefRevDate(params);
        // 删除债券交易信息接口表到期日为当天的交易数据
        opicsMapper.deleteIntefMdateTrad(params);

    }

}
