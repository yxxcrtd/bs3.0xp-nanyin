package com.joyintech.service.risk.RiskNamBatchValidator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.risk.dto.RiskNameListDto;
import com.joyintech.tams.invest.common.risk.service.RiskNameListCommonBatchService;

/**
 * 风险名单验证
 * 
 * @author FUXJ
 *
 */
@RestController("RiskNamBatchValidatorCtr_dtx")
@RequestMapping("/RiskNamBatchValidator")
public class RiskNamBatchValidatorController {

	 @Autowired
	    private RiskNameListCommonBatchService riskNameListCommonBatchService;
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("batch")
	public Map<String, Object> batch(@RequestBody Map<String, Object> param) {
		Map<String, Object> webResult = new HashMap<String, Object>();
		List<String> errorList = new ArrayList<String>();
		
		Object data = param.get("rskList");
		List<RiskNameListDto> list = new ArrayList<RiskNameListDto>();
		if(data instanceof List) {
			List<Map<String, String>> dataList = (List<Map<String, String>>) data;
			for(Map<String, String> map : dataList) {
				RiskNameListDto riskname = JSON.parseObject(JSON.toJSONString(map), RiskNameListDto.class);
				if(!StringUtil.isEmpty(map.get("assetCode"))) {
					RiskNameListDto riskdto = riskNameListCommonBatchService.getRiskNameByFinprodId(map.get("assetCode"));
					if (riskdto != null) {
						riskname.setFinprodId(riskdto.getFinprodId());
						list.add(riskname);
					} else {
						String msg = map.get("assetCode") + "资产不存在";
						errorList.add(msg);
					}
				}else {
					list.add(riskname);
				}
			}
			if(!StringUtil.isEmptyList(list)) {
				riskNameListCommonBatchService.名单批量试算(list);
			}
		}
		
		for(RiskNameListDto dto : list) {
			if (!Boolean.parseBoolean(dto.getErrorMap().get("isAssetMatch").toString())) {
				//资产不在白名单内
				String msg = dto.getFinprodId()+"资产不在白名单内";
				errorList.add(msg);
			}
			if (!Boolean.parseBoolean(dto.getErrorMap().get("isAssetMatchBlack").toString())) {
				//资产在黑名单内
				String msg = dto.getFinprodId()+"资产在黑名单内";
				errorList.add(msg);
			}
			if (!Boolean.parseBoolean(dto.getErrorMap().get("isOrgMatch").toString())) {
				//机构(或交易对手)不在白名单内
				String msg ="机构(或交易对手)不在白名单内";
				errorList.add(msg);
			}
			if (!Boolean.parseBoolean(dto.getErrorMap().get("isOrgMatchBlack").toString())) {
				//机构(或交易对手)在黑名单内
				String msg = "机构(或交易对手)在黑名单内";
				errorList.add(msg);
			}
		}
		//
		webResult.put("errorList",errorList);
		return webResult;
	}
}
