/*
 * 文件名：BusiFinExercisePlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.component.asset.service;

import com.joyintech.service.valuation.component.asset.dao.BusiFinExercisePlanMapper;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanService
 * @since 1.0
 */
@Service(value="serviceBusiFinExercisePlanService")
public class BusiFinExercisePlanService {

    @Autowired
    private BusiFinExercisePlanMapper busiFinExercisePlanMapper;

    public List<FinExercisePlan> getfinExercisePlanByProductId(String finprodId, String psT03, String cashType2, String execType01, String execType02){
    	// 一体化对应etliu start
    	String psT39 = DictCodeList.交易方向_行权_赎回.getValue();
//        return busiFinExercisePlanMapper.getfinExercisePlanByProductId(finprodId, psT03, cashType2, execType01, execType02);
        return busiFinExercisePlanMapper.getfinExercisePlanByProductId(finprodId, psT03, psT39, cashType2, execType01, execType02);
        // 一体化对应etliu end
    }
}
