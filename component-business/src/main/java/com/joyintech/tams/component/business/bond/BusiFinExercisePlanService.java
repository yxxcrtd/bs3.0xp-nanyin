/*
 * 文件名：BusiFinExercisePlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.bond;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

/**
 * 行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanService
 * @since 1.0
 */
public interface BusiFinExercisePlanService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "busiFinExercisePlanDto";
    
    List<FinExercisePlan>  getfinExercisePlanByProductId(Map<String,Object> param);

    // 增加实际行权日 add by renping 20190927 start
    /**
     * 根据行权日取得实际行权日
     *
     * @param dto 参数
     * @return 实际行权日
     * @see
     * @since 1.0
     */
    public Map<String, String> getActExerciseDate(Map<String, Object> param);
    // 增加实际行权日 add by renping 20190927 end
}
