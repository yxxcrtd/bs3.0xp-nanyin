package com.joyintech.tams.disclosure.busicommon.rptdata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.rptdata.dao.NostdReportMapper;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

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
	private NostdReportMapper nostdReportMapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(NostdReportDataService.class);

	@Override
	public List<Map<String, Object>> queryDatas(Map<String, Object> paraMap) {

		List<Map<String, Object>> listResult = new ArrayList<>();

		try {
			String prodId = paraMap.get("pprodid").toString();
			List<RepProdInfo> listProdInfo = nostdReportMapper.selectProdInfo(prodId);
			RepProdInfo prodInfo = new RepProdInfo();
			if ( listProdInfo!=null && listProdInfo.size()>0 )
				prodInfo = listProdInfo.get(0);
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("reportDate", DateUtil.parse(paraMap.get("enddate").toString()));
			dataMap.putAll(CollectionUtils.bean2Map(prodInfo));
			listResult.add(dataMap);
		} catch (Exception e) {
			logger.error("调整投资非标准化债权类资产公告数据错误.", e);
			e.printStackTrace();
		}

		return listResult;
	}

	public RepReveal getRepReveal(Map<String, Object> paraMap) {
		RepReveal repReveal = new RepReveal();
		repReveal.setRepId(paraMap.get("repId").toString());
		repReveal.setProdid(paraMap.get("prodId").toString());
		
		Date eDate = (Date)(paraMap.get("reportDate"));
		repReveal.setEndDate(eDate);
		return repReveal;
	}
}
