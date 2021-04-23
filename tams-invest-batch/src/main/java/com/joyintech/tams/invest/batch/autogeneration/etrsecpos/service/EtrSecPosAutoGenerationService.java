/*
 * 文件名：SecPosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.etrsecpos.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.autogeneration.etrsecpos.dao.EtrSecPosAutoGenerationMapper;

/**
 * 组合持仓自动生成批处理Service
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see EtrSecPosAutoGenerationService
 * @since 1.0
 */
@Service
public class EtrSecPosAutoGenerationService {

    /**
     * SecPosAutoGenerationMapper
     */
    @Autowired
    private EtrSecPosAutoGenerationMapper secPosAutoGenerationMapper;

    /**
     * 组合持仓自动生成
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
        secPosAutoGenerationMapper.deleteTodaySecPosData(paramMap);

        // 生成当日持仓数据
        paramMap.put("POSITION_TYPE_E01", DictCodeListExt1.持仓类型_委托指令可用.getValue());
        paramMap.put("lastDate", DateUtil.addDay(batchDate, -1));
        secPosAutoGenerationMapper.insertTodaySecPosData(paramMap);
        
        // 删除批处理执行日的标准券持仓数据
        secPosAutoGenerationMapper.deleteTodayStaSecPosData(paramMap);
        //
        secPosAutoGenerationMapper.insertTodayStaSecPosData(paramMap);
        
    }
}
