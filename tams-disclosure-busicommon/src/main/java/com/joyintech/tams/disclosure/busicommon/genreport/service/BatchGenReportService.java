package com.joyintech.tams.disclosure.busicommon.genreport.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;

/**
 * 手工批量生成报表：用户在前台指定报表（唯一），产品列表（已做好筛选的合法产品编码列表），生成日期，
 * 系统根据以上条件批量生成相应的报表，给出生成结果摘要描述
 * @author chenxc
 * 2022-10-13
 *
 */
public class BatchGenReportService {

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
	
	public List<BatchGenResultDto> exec(String repId,List<RepProdInfo>listProd,Date execDate ) {
		List<BatchGenResultDto> listResult = new ArrayList<>();
		//规则表信息
		RepRuleEntity repRule = infoDisclosureReadAndGenMapper.getRepRuleInfo(repId);
		if(repRule == null){
			for ( RepProdInfo prod:listProd ) {
				BatchGenResultDto result = new BatchGenResultDto();
				result.setResult("F");
				result.setRemark("没有找到报表规则");
				result.setProdId(prod.getProdId());
				result.setProdName(prod.getProdName());
				result.setGenDate(execDate);
				result.setRepId(repId);
				result.setRepName("");
				listResult.add(result);
			}
			return listResult;
		}
		
		for ( RepProdInfo prod:listProd ) {
			//定义生成结果
			BatchGenResultDto result = new BatchGenResultDto();
			result.setProdId(prod.getProdId());
			result.setProdName(prod.getProdName());
			result.setGenDate(execDate);
			result.setRepId(repId);
			result.setRepName(repRule.getRepName());
			
			//定义报表参数
			Map<String,Object> params = collateParams(repRule,prod,execDate);
			
			//2.生成报表
			Map<String, Object> dataMap = null;
				//2.1查询报表数据数据
				List<Map<String, Object>> listReports = reportDataService.queryDatas(params);
				if ( listReports!=null && listReports.size() > 0) {
					dataMap = listReports.get(0);
					dataMap.put("prodId",params.get("prodId"));
					//2.2生成报表文件
					String reportFile = poiReportService.genOssReportFile(null, dataMap, params);
					result.setResult("S");
					result.setRemark("生成文件成功!" + reportFile);
				} else {
					result.setResult("F");
					result.setRemark("生成报表失败!没有找到数据。");
				}
				listResult.add(result);
		}
		return listResult;
	}
	
	private Map<String, Object> collateParams(RepRuleEntity repRule, RepProdInfo prodInfo, Date execDate) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Date> dateMap = new HashMap<>();
		try {
			if (StringUtil.equals(DictCodeListExt2.基准日_平台日.getValue(), repRule.getBasisDateType())) {
				// 获取开始日结束日方法
				infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, execDate, dateMap);
				// 获取披露截止日
				Date ptDate = revealDeadLineService.getRevDeadlineByMark(execDate, repRule, dateMap);
				dateMap.put("ptDate", ptDate);
			} else if (StringUtil.equals(DictCodeListExt2.基准日_开放周期开始日.getValue(), repRule.getBasisDateType())
					|| StringUtil.equals(DictCodeListExt2.基准日_开放周期结束日.getValue(), repRule.getBasisDateType())
					|| StringUtil.equals(DictCodeListExt2.基准日_开放周期起息日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenOpen(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					// 获取披露截止日
				}
			} else if (StringUtil.equals(DictCodeListExt2.基准日_费率调整日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenAdjust(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
					// 获取披露截止日
				}
			} else if (StringUtil.equals(DictCodeListExt3.基准日_巨额赎回调整日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenJESH(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			}
	
			else if (StringUtil.equals(DictCodeListExt3.基准日_募集上线调整日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenMJTZ(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			}
	
			else if (StringUtil.equals(DictCodeListExt3.基准日_当期收益分配基准日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenDQSYFP(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			}
	
			else if (StringUtil.equals(DictCodeListExt3.基准日_自动赎回日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenZDSH(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			} else if (StringUtil.equals(DictCodeListExt2.基准日_成立日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenCLR(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			} else if (StringUtil.equals(DictCodeListExt2.基准日_到期日.getValue(), repRule.getBasisDateType())) {
				// 基准日
				Date openDate = revealDeadLineService.isCanGenMDATE(repRule, dateMap, execDate, prodInfo.getProdId());
				if (openDate != null) {
					// 获取开始日结束日方法
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, openDate, dateMap);
				}
			} else {
				//prodInfo = new ProdInfoListEntity();
				Date revDeadDate = null;//infoDisclosureReadAndGenDetailService.getBasisDate(prodInfoListEntity, repRule);
				int days = Integer.parseInt(repRule.getMakeValue());
				// update by leipeng 规则 前后更改
				if (StringUtil.equals(DictCodeListExt2.时点类型_基准日后.getValue(), repRule.getRevealType())) {
					days = -1 * days;
				}
				boolean isGen = false;
				if (StringUtil.equals(DictCodeListExt1.日期类型_信批_自然日.getValue(), repRule.getMakeDateType())) {
					if (DateUtil.getBetweenDayNumber(execDate, revDeadDate) == days) {
						isGen = true;
					}
				} else if (revealDeadLineService.isWorkDay(execDate, repRule.getMarketCode())) {
					isGen = revealDeadLineService.isCanGen(revDeadDate, repRule, dateMap, execDate);
				}
				if (isGen) {
					infoDisclosureReadAndGenDetailService.getDataBeginAndEndDate(repRule, revDeadDate, dateMap);
				}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if ( dateMap!=null && dateMap.size()>0 )
			params.putAll(dateMap);
		reportDataService.collateReportDateParams(params);
		return params;
	}			

	public List<ProdInfoListEntity> queryProdList(String ruleId,Date execDate) {
		//获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(ruleId);
		//通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenDetailService.getRepJobRangeByJobid(repJobRangeList, ruleId,execDate);
		return prodList;
	}
}
