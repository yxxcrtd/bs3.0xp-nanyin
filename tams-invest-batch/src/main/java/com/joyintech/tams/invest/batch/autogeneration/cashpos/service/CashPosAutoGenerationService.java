/*
 * 文件名：CashPosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.cashpos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.autogeneration.cashpos.dao.CashPosAutoGenerationMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 组合现金余额自动生成Service
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see CashPosAutoGenerationService
 * @since 1.0
 */
@Service
public class CashPosAutoGenerationService {

    /**
     * CashPosAutoGenerationMapper
     */
    @Autowired
    private CashPosAutoGenerationMapper cashPosAutoGenerationMapper;

    /**
     * 组合现金余额自动生成
     *
     * @param batchDate 执行日
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void autoGeneration(Date batchDate, List<String> productIdList) {

        // 删除当日持仓数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("batchDate", batchDate);
        if (productIdList.size() < 1000) {
            paramMap.put("productIdList", productIdList);
        }
        else {
            paramMap.put("productIdList", null);
        }
        cashPosAutoGenerationMapper.deleteTodaySecPosData(paramMap);

        // 生成当日持仓数据
        paramMap.put("POSITION_TYPE_03", DictCodeList.持仓类型_实际.getValue());
        paramMap.put("lastDate", DateUtil.addDay(batchDate, -1));
        cashPosAutoGenerationMapper.insertTodaySecPosData(paramMap);
    }
}
