/*
 * 文件名：ResetBaseRuleInfoReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.susprdfutureopenplangen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dao.SusPrdFutureOpenPlanGenMapper;
import com.joyintech.tams.invest.batch.susprdfutureopenplangen.dto.SusPrdFutureOpenPlanGenDto;

/**
 * reader相关业务处理
 * 
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class SusPrdFutureOpenPlanGenReaderService extends BaseService {

	@Autowired
	private SusPrdFutureOpenPlanGenMapper susprdfutureopenplangenMapper;

	/**
	 * 获取待处理数据
	 */
	public List<SusPrdFutureOpenPlanGenDto> execute(Map<String, Object> param) {
		// 获取待处理任务信息
		SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo) param.get(BatchConstant.BATCH_INFO);
		if (null == batchExecuteInfo) {
			return null;
		}
		
		List<SusPrdFutureOpenPlanGenDto> lists = new ArrayList<SusPrdFutureOpenPlanGenDto>();
		//查询需要更新的开放计划
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
		paramMap.put("cashType1", DictCodeListExt1.现金流二级类型_本金.getValue());
		paramMap.put("sysDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
		paramMap.put("issueStatus", DictCodeListExt1.发行状态_发行成功.getValue());
		paramMap.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
		//paramMap.put("isSus", DictCodeList.是否_是.getValue());
		paramMap.put("operationType", DictCodeList.开放类型_开放式.getValue());
		List<SusPrdFutureOpenPlanGenDto> susprdfutureopenplangendtoList = susprdfutureopenplangenMapper
				.findSusPrdFutureOpenPlanGenList(paramMap);
		lists.addAll(susprdfutureopenplangendtoList);
		
	/*	//查询需要更新的业绩回补计划
		Map<String, Object> paramMapAnapleroticPlan = new HashMap<String, Object>();
		paramMapAnapleroticPlan.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
		paramMapAnapleroticPlan.put("cashType1", DictCodeListExt1.现金流二级类型_回补.getValue());
		paramMapAnapleroticPlan.put("finprodType", DictCodeListExt1.金融产品类型_理财产品.getValue());
		paramMapAnapleroticPlan.put("sysDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
		List<SusPrdFutureOpenPlanGenDto> finProductAnapleroticPlanList = susprdfutureopenplangenMapper
				.findProductAnapleroticPlanList(paramMapAnapleroticPlan);
		lists.addAll(finProductAnapleroticPlanList);*/
		
		
		//查询需要更新的付息计划
		Map<String, Object> paramMapPayPlan = new HashMap<String, Object>();
		paramMapPayPlan.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
		paramMapPayPlan.put("sysDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
		List<SusPrdFutureOpenPlanGenDto> finProductPayPlanList = susprdfutureopenplangenMapper
						.findProductPayPlanList(paramMapPayPlan);
		lists.addAll(finProductPayPlanList);
		
		return lists;
	}

}
