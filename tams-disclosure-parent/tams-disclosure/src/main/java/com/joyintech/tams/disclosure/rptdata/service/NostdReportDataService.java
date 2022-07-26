package com.joyintech.tams.disclosure.rptdata.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.disclosure.common.CollectionUtils;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;
import com.joyintech.tams.disclosure.rptdata.dao.SettReportMapper;
import com.joyintech.tams.disclosure.rptdata.dto.SettReportDto;

/**
 * 非标债权调整公告-数据获取服务
 * 
 * @author chenxc
 * @version 1.0 2022年07月20日
 * @see
 * @since 1.0
 */
@Service
public class NostdReportDataService implements IReportDataService {

	@Autowired
	private RepProdInfoMapper repProdInfoMapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(NostdReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		try {
			String prodId = paraMap.get("pprodid").toString();
			RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey(prodId);
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("reportDate", paraMap.get("enddate"));
			dataMap.putAll(CollectionUtils.bean2Map(prodInfo));
			listResult.add(dataMap);
		} catch (Exception e) {
			logger.error("调整投资非标准化债权类资产公告数据错误.", e);
			e.printStackTrace();
		}

		return listResult;
	}

}
