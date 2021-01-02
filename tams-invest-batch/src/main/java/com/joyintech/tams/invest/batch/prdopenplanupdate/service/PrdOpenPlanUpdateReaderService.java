package com.joyintech.tams.invest.batch.prdopenplanupdate.service;

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
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dao.PrdOpenPlanUpdateMapper;
import com.joyintech.tams.invest.batch.prdopenplanupdate.dto.PrdOpenPlanUpdateDto;

/**
 * reader相关业务处理
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class PrdOpenPlanUpdateReaderService extends BaseService{

	@Autowired
	private PrdOpenPlanUpdateMapper prdOpenPlanUpdateMapper;
	/**
     * 平台日期接口
     */
    @Autowired(required = false)
    private PlatformDate platformDateService;
	
	/**
	 * 获取待处理数据
	 */
	public List<PrdOpenPlanUpdateDto> execute(Map<String, Object> param) {
		// 获取待处理任务信息
		SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo) param.get(BatchConstant.BATCH_INFO);
		if (null == batchExecuteInfo) {
			return null;
		}
		List<PrdOpenPlanUpdateDto> lists = new ArrayList<PrdOpenPlanUpdateDto>();

		//查询需要更新的开放计划
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cashFinprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
		paramMap.put("cashType1", DictCodeListExt1.现金流二级类型_本金.getValue());
		//DateUtil.format(platformDateService.getSysDate())
		paramMap.put("sysDate", DateUtil.format(platformDateService.getSysDate()));
		List<PrdOpenPlanUpdateDto> openPlanlists = prdOpenPlanUpdateMapper.queryOldOpenPlanList(paramMap);
		lists.addAll(openPlanlists);
		
		//查询需要更新的付息计划
		Map<String, Object> paramMapPay = new HashMap<String, Object>();
		paramMapPay.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
		//DateUtil.format(platformDateService.getSysDate())
		paramMapPay.put("sysDate", DateUtil.format(platformDateService.getSysDate()));
		List<PrdOpenPlanUpdateDto> payPlanlists = prdOpenPlanUpdateMapper.queryOldPayPlanList(paramMapPay);
		lists.addAll(payPlanlists);
		
		return lists;
	}
	
}
