/*
 * 文件名：ValExercisePlanBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

@Repository
public interface ValExercisePlanBatchMapper {
	 int insertBatch(List<FinExercisePlan> list);
	 
	 int deleteBatch(List<FinExercisePlan> list);
	 
	 /**add by wujie on 20190121**/
	 int deleteByFinprodId(List<FinExercisePlan> list);

}
