/*
 * 文件名：ResetBaseRuleInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdfutureopenplangen.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;

/**
 * 批处理-续投时期次结束日调整批处理				
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Repository
public interface SusPrdFutureOpenPlanGenMapper {
	
	List<SusPrdFutureOpenPlanGenDto> findSusPrdFutureOpenPlanGenList(Map<String,Object> paramMap);
}
