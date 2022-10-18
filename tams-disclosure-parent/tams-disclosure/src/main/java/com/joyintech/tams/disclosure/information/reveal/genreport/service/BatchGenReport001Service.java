package com.joyintech.tams.disclosure.information.reveal.genreport.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepJobRange;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.busicommon.genreport.dto.BatchGenResultDto;
import com.joyintech.tams.disclosure.busicommon.genreport.service.BatchGenReportService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.dao.InfoDisclosureReadAndGenMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.entity.ProdInfoListEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosurereadandgen.service.InfoDisclosureReadAndGenDetailService;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.RevealDeadLineService;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.dao.ReportInfoMapper;
import com.joyintech.tams.disclosure.busicommon.jixu.framework.report.pojo.ReportInfo;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.busicommon.rptdata.service.ReportDataService;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum002Mapper;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency002Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dao.RepDetailsShow001Mapper;
import com.joyintech.tams.disclosure.information.reveal.detailshow.dto.RepDetailsShow001Dto;
import com.joyintech.tams.disclosure.information.reveal.detailshow.service.RepDetailsShow001Service;

@Service
@Transactional(rollbackFor = Exception.class)
public class BatchGenReport001Service extends CommonExportService<RepDetailsShow001Dto>{
	
	@Autowired
	private RepDetailsShow001Mapper repDetailsShow001Mapper;
	
	@Autowired
	private SaleAgency002Mapper saleAgency002Mapper;
	
	@Autowired
	private ReportContentEnum002Mapper reportContentEnum002Mapper;
	
    @Autowired
    private RepDetailsShow001Service repDetailsShow001Service;
    
    
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
	
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(BatchGenReport001Service.class);
	
	public List<BatchGenResultDto> batchExec(Map<String,Object> params) {
		
		List<BatchGenResultDto> listResult = new ArrayList<>();

		// 1.查询报表模板
		String repId = params.get("repId").toString();
		ReportInfo reportInfo = reportInfoMapper.getReportInfoByID(repId);
		if (!StringUtil.isNotEmpty(reportInfo)) {
			BatchGenResultDto result = new BatchGenResultDto();
			result.setRepId(repId);
			result.setRepName(reportInfo.getReportName());
			result.setResult("F");
			result.setRemark("生成报表失败：没有找到指定的模板。");
			listResult.add(result);
			return listResult;
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
			params.put("prodid", prod.getProdId());
			//为了能顺利调用历史的方法，对不规范的参数进行补充
			repDetailsShow001Service.collateParams(params);
			
			try {
				List<Map<String, Object>> listReports = reportDataService.queryDatas(params);
	
				// 生成失败：没有查询到指定的报表数据
				if (listReports == null || listReports.size() == 0) {
					result.setResult("F");
					result.setRemark("生成报表失败：没有查询到指定的数据。");
					listResult.add(result);
					continue;
				}
	
				// 生成失败：查询到的报表数据，经过校验，有错误
				dataMap = listReports.get(0);
				// 对查询结果进行校验，符号要求，则生成报表
				dataMap.put("repid", repId);
				String checkResult = checkReport(dataMap);
				if (!"success".equals(checkResult)) {
					result.setResult("F");
					result.setRemark("生成报表失败：" + checkResult);
					listResult.add(result);
					continue;
				}
			} catch ( Exception e ) {
				result.setResult("F");
				result.setRemark("生成报表失败：" + "报表数据查询异常。"+e.getMessage() );
				listResult.add(result);
				logger.error("生成报表失败：" + "报表数据查询异常。",e);
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
				result.setRemark("生成报表失败：" + "生成报表文件或者流程异常。"+e.getMessage());
				logger.error("生成报表失败：" + "报表数据查询异常。",e);
			}
			listResult.add(result);
		}
		return listResult;
		
	}

    public RepDetailsShow001Dto getRepScreenList(String repId){
        
        RepDetailsShow001Dto info = repDetailsShow001Mapper.getInfoByRepId(repId);  //查询报表的种类及模板配置等信息
        if(null!=info){
        	List<RepScreen> listScreen  = repDetailsShow001Mapper.getRepScreenList(repId).stream()
            .filter(e -> (!"01".equals(e.getScreenType()) && !"02".equals(e.getScreenType()) ) )
            .collect(Collectors.toList());
        	
            info.setRepScreenList(listScreen); //查询每个报表前台配置的查询参数信息
            
        }
        return info;
    }
    
    public List<TreeNode> buildProdTree(String repProdType,String repId) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		//获取父节点
		result.addAll(this.getParentNodes(repProdType));
		
		//查询当前所有产品，作为子节点
		List<TreeNode> prodInfoNodes = new ArrayList<>();
		
		//根据报表类型，依据报表规则，查询符合条件的产品集合
		List<ProdInfoListEntity> listProds = queryProdList(repId, null);
		
		if ( "C".equals(repProdType)) {
			prodInfoNodes=reportContentEnum002Mapper.selectProdInfoSon();
		} else {
			prodInfoNodes=reportContentEnum002Mapper.selectProdInfo();
		}
		
		List<String> prodsStr = new ArrayList<>();
		listProds.forEach(item ->{
			prodsStr.add(item.getProdId());
		});
		
		//对prodInfoNodes进行过滤
		prodInfoNodes = prodInfoNodes.stream().filter(e -> prodsStr.contains(e.getId())).collect(Collectors.toList());
		
		result.addAll(prodInfoNodes);
		return result;
    }
    
    /**
     * 组装除产品子节点以外的父节点
     * @return
     */
    public List<TreeNode> getParentNodes(String repProdType){
        List<DataDictItem> prodStatusList = new ArrayList<DataDictItem>();//产品状态
        List<DataDictItem> issueTypeList = new ArrayList<DataDictItem>();//发行方式
        List<String> prodSeriesList = new ArrayList<String>();//产品系列
        List<String> prodSeriesListSon = new ArrayList<String>();
        List<TreeNode> parentNodes = new ArrayList<TreeNode>();
        prodStatusList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.信披_产品状态.getValue());
        issueTypeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.发行方式_信批.getValue());
        //一级：产品状态
        if (prodStatusList!=null && prodStatusList.size() > 0) {
            for (DataDictItem map_1 : prodStatusList) {
                //查询产品系列的条件
                Map<String,Object> param=new HashMap<>();
                TreeNode treeDto = new TreeNode();
                treeDto.setId(map_1.getItemCode());
                treeDto.setName(map_1.getItemValue());
                treeDto.setpId("");
                treeDto.setOpen(true);
                parentNodes.add(treeDto);
                param.put("prodStatus",map_1.getItemCode());
                //二级：发行方式
                for (DataDictItem map_2 : issueTypeList) {
                    TreeNode treeDto_2 = new TreeNode();
                    treeDto_2.setId(map_1.getItemCode() + map_2.getItemCode());
                    treeDto_2.setName(map_2.getItemValue());
                    treeDto_2.setpId(map_1.getItemCode());
                    treeDto_2.setOpen(true);
                    parentNodes.add(treeDto_2);
                    param.put("issueType", map_2.getItemCode());
                    //三级：产品系列：产品系列为文本框输入，不是字典值，需要从产品基本信息表中查询所有的产品系列
                    prodSeriesList = saleAgency002Mapper.selectAllProdSeries(param);
                    if (prodSeriesList != null && prodSeriesList.size() > 0) {
                        for (String map_3 : prodSeriesList) {
                            TreeNode treeDto_3 = new TreeNode();
                            treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
                            treeDto_3.setName(map_3);
                            treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                            treeDto_3.setOpen(true);
                            parentNodes.add(treeDto_3);
                            
                            if ( "C".equals(repProdType) ) {  //如果是子产品，需要多一层
	                            param.put("prodSeries",map_3);
	                            prodSeriesListSon=saleAgency002Mapper.selectAllProdSeriesSon(param);
	                            for (String map_4 : prodSeriesListSon) {
	                                TreeNode treeDto_4 = new TreeNode();
	                                treeDto_4.setId(map_1.getItemCode() + map_2.getItemCode() + map_3+map_4);
	                                treeDto_4.setName(map_4);
	                                treeDto_4.setpId(map_1.getItemCode() + map_2.getItemCode()+ map_3);
	                                treeDto_4.setOpen(true);
	                                parentNodes.add(treeDto_4);
	                            }
                            }
                        }
                    } else {
                        TreeNode treeDto_3 = new TreeNode();
                        treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + "无数据");
                        treeDto_3.setName("无数据");
                        treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                        treeDto_3.setOpen(true);
                        parentNodes.add(treeDto_3);
                    }

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
	public String checkReport(Map<String, Object> dataMap) {
		Map<String, Object> result = poiReportService.dataCheck(dataMap);
		String msg = "success";
		if ( result.get("resultSb")!=null )
			msg = result.get("resultSb").toString();
		return msg;
		// return "success";
	}

	/**
	 * 根据报表类型，查找所有关联的产品
	 * 
	 * @param ruleId
	 * @param execDate
	 * @return
	 */
	public List<ProdInfoListEntity> queryProdList(String repId, Date execDate) {
		// 获取作业范围list
		List<RepJobRange> repJobRangeList = infoDisclosureReadAndGenMapper.getRepJobRangeList(repId);
		// 通过组装的条件，查询产品相关内容
		List<ProdInfoListEntity> prodList = infoDisclosureReadAndGenDetailService.getRepJobRangeByJobid(repJobRangeList,
				repId, execDate);
		return prodList;
	}
}
