package com.joyintech.tams.invest.batch.mstbondvaluationtotal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.batch.mstbondvaluationtotal.dao.MstBondValuationTotalMapper;

@Service
public class MstBondValuationTotalService {
	
	@Autowired
	private MstBondValuationTotalMapper mstBondValuationTotalMapper;
	
	public void run(SysBatchExecInfo batchExecuteInfo) {
		String bondValuationRule = CacheUtil.getSysParam("BOND_VALUATION_RULE");
		//优先插中债，无中债插入上清
		Map<String, Object> map = new HashMap<>();
		map.put("VALUATION_SOURCE_CBC", DictCodeList.估值来源_中债登_CBC.getValue());
		map.put("VALUATION_SOURCE_SCH", DictCodeList.估值来源_上清所_SCH.getValue());
		map.put("SVR_REGIST_ORG_01", DictCodeList.登记托管机构_中债_中央国债登记结算有限责任公司.getValue());
		map.put("SVR_REGIST_ORG_02", DictCodeList.登记托管机构_中债_上海清算所.getValue());
		map.put("date", batchExecuteInfo.getExecuteDate());
		if(StringUtil.equals(bondValuationRule, "01")) {
			mstBondValuationTotalMapper.deleteByDate(map);
			mstBondValuationTotalMapper.insertByDate(map);
		}
		//银行间债券根据债券登记托管机构判断，中债的债券取中债估值，上清债券取上清所估值，没有再取另外一个
		else {
			mstBondValuationTotalMapper.deleteByDate(map);
			mstBondValuationTotalMapper.insertByDateAndRegist(map);
		}
	}
}
