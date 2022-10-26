package com.joyintech.tams.disclosure.information.reveal.genreport.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepReveal;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.busicommon.common.util.CollectionUtils;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.reportDeal.service.ReportDealService;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportCheckService;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;
import com.joyintech.tams.disclosure.information.reveal.genreport.dao.BatchGenReport001Mapper;
import com.joyintech.tams.disclosure.interf.interf.common.constant.DateUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class BatchGenReport001Service extends CommonExportService<RepDetailsShow001Dto> {

	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;

	@Autowired
	private RepDetailsShow001Service repDetailsShow001Service;

	@Autowired
	private ReportDataService reportDataService;

	@Autowired
	private PoiReportService poiReportService;

	@Autowired
	private PoiReportCheckService reportCheckService;

	@Autowired
	private InfoDisclosureReadAndGenMapper infoDisclosureReadAndGenMapper;

	@Autowired
	private BatchGenReport001Mapper batchGenReport001Mapper;

    @Autowired
    private ReportDealService reportDealService;    
    
    @Autowired
    private HolidayService holidayService;
    
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(BatchGenReport001Service.class);

	public BatchGenResultDto exec(RepProdInfo prod, Map<String, Object> params) throws Exception {

		// 定义生成结果
		BatchGenResultDto result = new BatchGenResultDto();
		result.setProdId(prod.getProdId());
		result.setProdName(prod.getProdName());
		result.setGenDate((Date) params.get("platformDate"));
		result.setRepId(params.get("repId").toString());
		result.setRepName(params.get("repName").toString());

		// 定义报表变量的数据集合
		Map<String, Object> dataMap = null;

		// 为了能顺利调用历史的方法，对不规范的参数进行补充
		repDetailsShow001Service.collateParams(params);

		// 1.查询报表数据数据
		try {
			params.put("opttype", "batch");
			List<Map<String, Object>> listReports = reportDataService.queryDatas(params);

			// 生成失败：没有查询到指定的报表数据
			if (listReports == null || listReports.size() == 0) {
				result.setResult("F");
				logger.error("没有查询到指定的数据。");
				result.setRemark("没有查询到指定的数据，" + "或者生成日期与产品到期日小于等于90天，不需要生成报表。");
				return result;
			}
			dataMap = listReports.get(0);
			if ( dataMap.get("queryErrorMsg")!=null && dataMap.get("queryErrorMsg").toString().length()>0 ) {
				logger.error(dataMap.get("queryErrorMsg").toString());
				//throw new Exception(dataMap.get("queryErrorMsg").toString());
			}
			// 对查询结果进行一些参数补充
			dataMap.put("repid", params.get("repId").toString());
			dataMap.put("repId", params.get("repId").toString());
			dataMap.put("prodId", params.get("prodId"));
		} catch (Exception e) {
			result.setResult("F");
			result.setRemark("报表数据查询异常，" + e.getMessage());
			logger.error("生成报表失败：" + "报表数据查询异常。", e);
			return result;
		}

		// 2.对查询结果进行校验，符合要求，则生成报表
		try {
			String checkResult = checkReport(params, dataMap);
			if (!"success".equals(checkResult)) {
				result.setResult("F");
				logger.error("生成报表失败：" + "报表数据查询异常。" + checkResult);
				result.setRemark("报表数据检查异常：<br>" + checkResult);
				return result;
			}
		} catch (Exception e) {
//				result.setResult("F");
//				result.setRemark("生成报表失败：" + "报表数据检查异常，"+e.getMessage() );
			logger.error("生成报表失败：" + "报表数据检查异常。", e);
			throw new Exception("报表数据检查异常，" + e.getMessage());
		}

		try {
			// 3.生成报表文件，上传到文件服务器，相当于报表生成模块的预览功能
			String reportFile = poiReportService.genOssReportFile(null, dataMap, params);

			// 4.生成报表流程数据，进入待确认队列
        	boolean confirmFlag = true;
        	RepReveal repReveal = reportDataService.getRepReveal(params);
			confirmFlag = reportDealService.selectConfirmStatus(repReveal);  
    		if(confirmFlag == false) {  //检查不通过：存在相同的已确认的报告
				result.setResult("F");
				logger.error("生成报表失败：" + "指定的报表已确认。");
				result.setRemark("指定的报表已确认<br>");
				return result;
    		}
			reportFile = poiReportService.genReport(params);
			result.setResult("S");
			logger.info("手工批量生成报表完成：" + reportFile);
		} catch (Exception e) {
//				result.setResult("F");
//				result.setRemark("生成报表失败：" + "生成报表文件或者流程异常。"+e.getMessage());
			logger.error("生成报表失败：" + "生成报表文件或者流程异常。", e);
			throw new Exception("生成报表文件或者流程异常，" + e.getMessage());
		}
		return result;
	}

	public RepDetailsShow001Dto getRepScreenList(String repId) {

		RepDetailsShow001Dto info = repDetailsShow001Mapper.getInfoByRepId(repId); // 查询报表的种类及模板配置等信息
		if (null != info) {
			List<RepScreen> listScreen = repDetailsShow001Mapper.getRepScreenList(repId).stream()
					.filter(e -> (!"01".equals(e.getScreenType()) && !"02".equals(e.getScreenType())))
					.collect(Collectors.toList());

			info.setRepScreenList(listScreen); // 查询每个报表前台配置的查询参数信息

		}
		return info;
	}

	/**
	 * 总体上分两步： 1.通过getParentNode()方法，构建父节点； 2.通过查询产品，构建叶子节点； 通过设置叶子节点的pid，与父节点建立关联；
	 * 
	 * @param repProdType
	 * @param repId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<TreeNode> buildProdTree(String repProdType, String repId) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		// 获取父节点
		List<TreeNode> listParent = this.getParentNodes(repProdType);

		// 查询当前所有产品，作为叶子节点
		List<TreeNode> listChildren = new ArrayList<>();

		// 根据报表类型，依据报表规则，查询符合条件的产品集合
		List<ProdInfoListEntity> listProd = queryProdList(repId, null);

		if ("C".equals(repProdType)) {
			listChildren = batchGenReport001Mapper.selectProdInfoSon();
		} else {
			listChildren = batchGenReport001Mapper.selectProdInfo();
		}

		List<String> listProdId = new ArrayList<>();
		listProd.forEach(item -> {
			listProdId.add(item.getProdId());
		});

		// 对叶子节点listChildren进行过滤，剔除掉不合报表限制范围规则的产品
		listChildren = listChildren.stream().filter(e -> listProdId.contains(e.getId())).collect(Collectors.toList());

		Set<String> listChildrenId = new HashSet<>();
		listChildren.forEach(item -> {
			listChildrenId.add(item.getpId());
		});

		// 需要对parent节点进行过滤：没有子节点的节点，都不需要进行展示
		// 过滤的时候，需要从子节点开始进行过滤，因此按照treenode的object属性进行倒序排列
		List<TreeNode> listParent2 = new ArrayList<TreeNode>();
		Collections.sort(listParent, new Comparator<TreeNode>() {
			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				return o2.getObject().toString().compareTo(o1.getObject().toString());
			}
		});
		for (TreeNode t : listParent) {
			boolean remainFlag = false;
			for (TreeNode t1 : listParent2) { // 子是父节点
				if (t.getId().equals(t1.getpId())) {
					listParent2.add(t);
					remainFlag = true;
					break;
				}
			}
			if (remainFlag == false) {
				if (listChildrenId.contains(t.getId())) { // 子是产品
					listParent2.add(t);
				}
			}
		}

		// 对于没有子的parent树节点，进行过滤
//		listParent = listParent.stream().filter(item->( item.getObject()==null 
//				|| !"ParentProd".equals(item.getObject().toString()) 
//				||  ("ParentProd".equals(item.getObject().toString()) && listChildrenId.contains(item.getId())))  ).collect(Collectors.toList());

//		for ( TreeNode t:listParent ) {
//			if ( t.getObject()==null ) {
//				listParent2.add(t);
//			}else if ( !"ParentProd".equals(t.getObject().toString())) {
//				listParent2.add(t);
//			} else if ( "ParentProd".equals(t.getObject().toString()) && listChildrenId.contains(t.getId()) ) {
//				listParent2.add(t);
//			}
//		}

		result.addAll(listParent2);
		result.addAll(listChildren);
		return result;
	}

	/**
	 * 组装除产品子节点以外的父节点
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TreeNode> getParentNodes(String repProdType) {
		List<DataDictItem> prodStatusList = new ArrayList<DataDictItem>();// 产品状态
		List<DataDictItem> issueTypeList = new ArrayList<DataDictItem>();// 发行方式
		List<String> prodSeriesList = new ArrayList<String>();// 产品系列
		List<String> prodSeriesListSon = new ArrayList<String>();
		List<TreeNode> parentNodes = new ArrayList<TreeNode>();
		prodStatusList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.信披_产品状态.getValue());
		issueTypeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.发行方式_信批.getValue());
		// 一级：产品状态
		if (prodStatusList != null && prodStatusList.size() > 0) {
			for (DataDictItem map_1 : prodStatusList) {
				// 查询产品系列的条件
				Map<String, Object> param = new HashMap<>();
				TreeNode treeDto = new TreeNode();
				treeDto.setId(map_1.getItemCode());
				treeDto.setName(map_1.getItemValue());
				treeDto.setpId("");
				treeDto.setOpen(true);
				treeDto.setObject("1");
				parentNodes.add(treeDto);
				param.put("prodStatus", map_1.getItemCode());
				// 二级：发行方式
				for (DataDictItem map_2 : issueTypeList) {
					TreeNode treeDto_2 = new TreeNode();
					treeDto_2.setId(map_1.getItemCode() + map_2.getItemCode());
					treeDto_2.setName(map_2.getItemValue());
					treeDto_2.setpId(map_1.getItemCode());
					treeDto_2.setOpen(true);
					treeDto_2.setObject("2");
					parentNodes.add(treeDto_2);
					param.put("issueType", map_2.getItemCode());
					// 三级：产品系列：产品系列为文本框输入，不是字典值，需要从产品基本信息表中查询所有的产品系列
					prodSeriesList = batchGenReport001Mapper.selectAllProdSeries(param);
					if (prodSeriesList != null && prodSeriesList.size() > 0) {
						for (String map_3 : prodSeriesList) {
							TreeNode treeDto_3 = new TreeNode();
							treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
							treeDto_3.setName(map_3);
							treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
							treeDto_3.setOpen(true);
							treeDto_3.setObject("3");
							parentNodes.add(treeDto_3);

							if ("C".equals(repProdType)) { // 如果是子产品，需要多一层
								param.put("prodSeries", map_3);
								// 母产品名称
								prodSeriesListSon = batchGenReport001Mapper.selectAllProdSeriesSon(param);
								for (String map_4 : prodSeriesListSon) {
									TreeNode treeDto_4 = new TreeNode();
									treeDto_4.setId(map_1.getItemCode() + map_2.getItemCode() + map_3 + map_4);
									treeDto_4.setName(map_4);
									treeDto_4.setpId(map_1.getItemCode() + map_2.getItemCode() + map_3);
									treeDto_4.setOpen(true);
									treeDto_4.setObject("4");
									parentNodes.add(treeDto_4);
								}
							}
						}
					}
//					else {
//						TreeNode treeDto_3 = new TreeNode();
//						treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + "无数据");
//						treeDto_3.setName("无数据");
//						treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
//						treeDto_3.setOpen(true);
//						parentNodes.add(treeDto_3);
//					}

				}

			}
		}

		return parentNodes;
	}

	/**
	 * 报表的数据检查
	 * 
	 * @param dataMap
	 * @return
	 */
	public String checkReport(Map<String, Object> params, Map<String, Object> dataMap) {
		Map<String, Object> result = reportCheckService.dataCheck(params, dataMap);
		String msg = "success";
		if (result.get("resultSb") != null)
			msg = result.get("resultSb").toString();
		return msg;
		 //return "success";
	}

	/**
	 * 根据报表类型，查找适合的产品
	 * 
	 * @param ruleId
	 * @param execDate
	 * @return
	 */
	public List<ProdInfoListEntity> queryProdList(String repId, Date execDate) {
		// 获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(repId);
		if (repJobRangeList == null || repJobRangeList.size() == 0) {
			return null;
		}

		StringBuffer sqlVal = new StringBuffer();

		// 通过规则id组装并查询产品信息方法
		for (RepJobRange dto : repJobRangeList) {
			if (StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_募集方式.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_发行方式.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_发行状态.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_开放模式.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_是否独立开户.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_产品类型.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_理财类型.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_产品期限类型.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_开放频率.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_是否是指数型产品.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_是否是客户周期型产品.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_信批产品类别.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeExt1.发行方式_信批.getValue())) {
				sqlVal.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2()).append("'")
						.append(dto.getRuleType4()).append("'");
			} else if (StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_起息日.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_到期日.getValue())
					|| StringUtil.equals(dto.getRuleType1(), DictCodeListExt2.作业规则1_清盘日.getValue())) {
				String sysDate = DateUtil.getCurrentDate();
				if (dto.getRuleType4() == null || StringUtil.isEmpty(dto.getRuleType4())) {
					sqlVal.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')")
							.append(dto.getRuleType2()).append("'").append(sysDate).append("'");
				} else {
					sqlVal.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')")
							.append(dto.getRuleType2()).append("'").append(dto.getRuleType4()).append("'");
				}
			}
		}
		// 通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = batchGenReport001Mapper.selectProdList(sqlVal.toString(), repId);
		return prodList;
	}
	
    public String dateCheck(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String enddate = (String)param.get("enddate");
        if (StringUtil.isEmpty(enddate)) {
            return "净值日为空";
        }
        Calendar cal = Calendar.getInstance();
        Date date = DateUtil.parse(enddate);
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        int days = 0;
        // 将日期变为下个周一并与当前日期比较，不等则为最后一个工作日
        Map<String, Object> serparam = new HashMap<>();
        serparam.put("busi_date", DateUtil.addDay(date, 8 - w));
        serparam.put("is_holiday", "0");
        serparam.put("market_code", "04");
        Date lastdate = holidayService.getLastWorkDay(serparam);
        if (!date.equals(lastdate)) {
            return "只能选取最后一个工作日:" + DateUtil.format(lastdate);
        }
        return null;
    }

}
