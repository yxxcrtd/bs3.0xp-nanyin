/*
 * 文件名：RecePosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.recepos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.batch.autogeneration.recepos.dao.RecePosAutoGenerationMapper;

/**
 * 应收款持仓汇总处理Service
 *
 * @author renping
 * @version 1.0 2019年12月20日
 * @see RecePosAutoGenerationService
 * @since 1.0
 */
@Service
public class RecePosAutoGenerationService {

    /**
     * RecePosAutoGenerationMapper
     */
    @Autowired
    private RecePosAutoGenerationMapper recePosAutoGenerationMapper;

    /**
     * 应收款持仓汇总处理
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
        recePosAutoGenerationMapper.deleteTodayRecePosData(paramMap);

        // 生成当日持仓数据
        paramMap.put("POSITION_TYPE_01", DictCodeList.持仓类型_可用.getValue());
        paramMap.put("lastDate", DateUtil.addDay(batchDate, -1));
        recePosAutoGenerationMapper.insertTodayRecePosData(paramMap);
    }
}
