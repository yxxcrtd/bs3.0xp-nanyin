package com.joyintech.tams.disclosure.busicommon.genreport.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.RepRuleEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;

/**
 * 手工批量生成报表：用户在前台指定报表（唯一），产品列表（已做好筛选的合法产品编码列表），生成日期，
 * 系统根据以上条件批量生成相应的报表，给出生成结果摘要描述
 * 
 * @author chenxc 2022-10-13
 *
 */
@Service
public class BatchGenReportService {

	@Autowired
	private ReportInfoMapper reportInfoMapper;

	@Autowired
	private ReportDataService reportDataService;

	@Autowired
	private PoiReportService poiReportService;

	@Autowired
	private InfoDisclosureReadAndGenMapper infoDisclosureReadAndGenMapper;

	@Autowired
	private InfoDisclosureReadAndGenDetailService infoDisclosureReadAndGenDetailService;

	@Autowired
	private RevealDeadLineService revealDeadLineService;

	public List<BatchGenResultDto> exec(Map<String, Object> params) throws Exception {
		List<BatchGenResultDto> listResult = new ArrayList<>();

		// 1.查询报表模板
		String repId = params.get("repId").toString();
		ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(repId);
		if (!StringUtil.isNotEmpty(reportInfo)) {
			throw new Exception("生成报表失败!没有找到模板。");
		}

		Date execDate = (Date) params.get("platformDate");
		List<RepProdInfo> listProd = (List<RepProdInfo>) params.get("listProd");
		for (RepProdInfo prod : listProd) {
			
			// 定义生成结果
			BatchGenResultDto result = new BatchGenResultDto();
			result.setProdId(prod.getProdId());
			result.setProdName(prod.getProdName());
			result.setGenDate(execDate);
			result.setRepId(repId);
			result.setRepName(reportInfo.getReportName());

			// 2.生成报表文件，上传到minio文件服务器
			Map<String, Object> dataMap = null;
			
			// 2.1查询报表数据数据
			params.put("prodId", prod.getProdId());
			List<Map<String, Object>> listReports = reportDataService.queryDatas(params);

			// 生成失败：没有查询到指定的报表数据
			if (listReports == null || listReports.size() == 0) {
				result.setResult("F");
				result.setRemark("生成报表失败!没有找到数据。");
				listResult.add(result);
				continue;
			}

			// 生成失败：查询到的报表数据，经过校验，有错误
			dataMap = listReports.get(0);
			// 对查询结果进行校验，符号要求，则生成报表
			String checkResult = checkReport(dataMap);
			if (!"success".equals(checkResult)) {
				result.setResult("F");
				result.setRemark("生成报表失败!" + checkResult);
				listResult.add(result);
				continue;
			}

			// 2.2生成报表文件
			try {
				dataMap.put("prodId", params.get("prodId"));
				String reportFile = poiReportService.genOssReportFile(null, dataMap, params);
				result.setResult("S");
				result.setRemark("生成文件成功!" + reportFile);

				// 3.生成报表流程数据，进入待确认队列
				poiReportService.genReport(params);
			} catch (Exception e) {
				result.setResult("F");
				result.setRemark("生成报表失败!" + e.getMessage());
			}
			listResult.add(result);
		}
		return listResult;
	}

	/**
	 * 报表的数据检查
	 * 
	 * @param dataMap
	 * @return
	 */
	private String checkReport(Map<String, Object> dataMap) {
		return "测试终止";
		// return "success";
	}

	/**
	 * 根据报表类型，查找所有关联的产品
	 * 
	 * @param ruleId
	 * @param execDate
	 * @return
	 */
	public List<ProdInfoListEntity> queryProdList(String ruleId, Date execDate) {
		// 获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(ruleId);
		// 通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenDetailService.getRepJobRangeByJobid(repJobRangeList,
				ruleId, execDate);
		return prodList;
	}
}
