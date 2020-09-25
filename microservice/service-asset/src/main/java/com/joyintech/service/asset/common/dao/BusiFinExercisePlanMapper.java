/*
 * 文件名：BusiFinExercisePlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月12日
 * @see BusiFinExercisePlanMapper
 * @since 1.0
 */
@Repository
public interface BusiFinExercisePlanMapper {

	// 一体化对应etliu start
//    List<FinExercisePlan> getfinExercisePlanByProductId(@Param("finprodId") String finprodId,@Param("psT03") String psT03,@Param("cashType2") String cashType2,@Param("execType01") String execType01,@Param("execType02") String execType02);
    List<FinExercisePlan> getfinExercisePlanByProductId(@Param("finprodId") String finprodId,@Param("psT03") String psT03,@Param("psT39") String psT39,@Param("cashType2") String cashType2,@Param("execType01") String execType01,@Param("execType02") String execType02);
    // 一体化对应etliu end
}
