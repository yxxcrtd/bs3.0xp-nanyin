/*
 * 文件名：ReportPageManage001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.information.config.reportpage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.RepJobRangeDto;
import com.joyintech.tams.disclosure.information.config.reportpage.dto.ReportPageManage001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Service
 * @since 1.0
 */
@Service
public class ReportPageManage001Service extends CommonExportService<ReportPageManage001Dto>{
//    
//    @Autowired
//    private ReportPageManage001Mapper reportPageManage001Mapper;
//    @Autowired
//    private RepInfoMapper repInfoMapper;
//    @Autowired
//    private RepScreenMapper repScreenMapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
    /**
     */
//    @Autowired
//    private RepJobRangeMapper repJobRangeMapper;
    public List<SuggestDto> getPageName(String param) {
//        List<SuggestDto> list = reportPageManage001Mapper.getPageName(param);
//        return list;
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage001/getPageName?param="+param,
        		SuggestDto.class);
        
        
        
    }
    public List<ReportPageManage001Dto> findPage(Map<String, Object> param) {
//    	 String reportCategory = (String)param.get("reportCategory");
//         String[] reportCategoryArr = null;
//         if(StringUtil.isNotEmpty(reportCategory)){
//        	 reportCategoryArr = reportCategory.split(",");
//         }
//         param.put("reportCategoryArr", reportCategoryArr);
//        return reportPageManage001Mapper.findPage(param);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage001/findPage",
                param, ReportPageManage001Dto.class);
    }
    /**
     * 规则数据入库
     * @param param
     * @see
     * @since 1.0
     */
//    private void doInsertRangdeInfo(ReportPageManage001Dto param){
//        reportPageManage001Mapper.deleteRangeInfo(param.getRepId());
//        // 插入作业范围数据
//        List<RepJobRangeDto> settedRuleList = param.getSettedRule();
//        if (!StringUtil.isEmptyList(settedRuleList)) {
//            for (RepJobRangeDto manage4Dto : settedRuleList) {
//                manage4Dto.setJobId(param.getRepId());
//                manage4Dto.setFlag(DisclosureDictCodeList.规则类型_指标值.getValue());
//                manage4Dto.setId(CommonUtil.getSequenceIndexData(DataIndexSequenceType.基础数据));
//                repJobRangeMapper.insertSelective(manage4Dto);
//            }
//        }
//    }

    public void insertReportInfo(ReportPageManage001Dto param) {
//        // 根据repId是否为空，判断是否更新还是新建;
//        if (StringUtil.isEmpty(param.getRepId())) {
//            // 插入报表页面数据;
//            param.setRepId(IDGenerator.generate());
//            // param.setReportCategory(param);
//            repInfoMapper.insert(param);
//            for (RepScreen repScreen : param.getRepScreenParam()) {
//                repScreen.setRepId(param.getRepId());
//                repScreen.setScreenId(IDGenerator.generate());
//                repScreenMapper.insert(repScreen);
//            }
//
//        }
//        else {
//            repInfoMapper.updateByPrimaryKeySelective(param);
//            reportPageManage001Mapper.deleteFromRepScreen(param.getRepId());
//            for (RepScreen repScreen : param.getRepScreenParam()) {
//                repScreen.setRepId(param.getRepId());
//                repScreen.setScreenId(IDGenerator.generate());
//                repScreenMapper.insert(repScreen);
//            }
//        }
//        // 规则数据入库
//        this.doInsertRangdeInfo(param);
    	restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage002/insertReportInfo",param,
                Integer.class);
    	

    }
    public List<RepScreen> getRepScreenList(String repId) {
//        return reportPageManage001Mapper.getRepScreenList(repId);
        return restTemplate.getForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage002/getRepScreenList?repId="+repId,
                RepScreen.class);
        
        
        
    }
    public void updateReportStatus(Map<String, Object> param) {
        //String reportStatus = param.get("flag").toString();
//        reportPageManage001Mapper.updateReportStatus(param);
    	restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage001/effitive",
                param, Integer.class);
    }
    public int checkSave(Map<String, Object> checkMap) {
        //String repId = (String)checkMap.get("repId");
        //String reporName = (String)checkMap.get("reportName");;
//        return reportPageManage001Mapper.checkSave(checkMap);
    	return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage002/checkSave",
                checkMap, Integer.class);
        
        
        
        
    }
    public void deleteReport(Map<String, Object> param) {
        //删除已设置的规则数据
//        reportPageManage001Mapper.deleteRangeInfo(param.get("repId").toString());
//        repInfoMapper.deleteByPrimaryKey(param.get("repId").toString());
//        reportPageManage001Mapper.deleteRepScreen(param);
//        
        restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage001/deleteReport",
                param, Integer.class);
    }
    /**
    * 查询报表名称
    * @return 报表名称
    */
	public List<SelectOptionDto> getReportInfo() {
//		List<ReportPageManage001Dto> reportInfoList = reportPageManage001Mapper.getReportInfo();
//		List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
//		for (ReportPageManage001Dto reportInfo : reportInfoList) {
//			SelectOptionDto dto = new SelectOptionDto();
//			dto.setId(reportInfo.getRepId());
//			dto.setText(reportInfo.getReportName());
//			listSelectDto.add(dto);
//		}
//		return listSelectDto;
		
		return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage001/getReportInfo",
    			null,SelectOptionDto.class);
	}
	
	
	
    /**
     * 获取报表查询参数方法
     * @param params 条件
     * @return
     * @throws Exception
     */
    public List<Map<String,String>> getReportInputs(Map<String,Object> params) throws Exception{
    	//后台批处理报表地址单独啦出来
		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
		//用户名
		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
		//密码 
		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
		//路径 
		String path = params.get("uri").toString();
		
		String url = jasparConnectUtilService.getReportsInputsUrl(serverUrl, serverUser, serverPwd, path);
		//
		List<Map<String,String>> result = jasparConnectUtilService.connectJasparAndGenXML(url,true);
		return result;
    }
    /**
     * 获取报表参数配置
     * @param pageParam
     * @return 报表参数配置list
     */
	public List<Map<String,String>> getReportInputsNew(Map<String, Object> pageParam) {
		// 根据报表folder查询报表参数json字符串
//		ReportPageManage001Dto reportparamsJson = reportPageManage001Mapper.getReportInputsNew(pageParam);
		ReportPageManage001Dto reportparamsJson =restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage002/getReportInputsNew",
				pageParam,ReportPageManage001Dto.class);
		
		
		
		if(reportparamsJson == null ||  StringUtil.isEmpty(reportparamsJson.getReportparams())){
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> reportparamsMaps = JsonUtils.json2Object(reportparamsJson.getReportparams(), Map.class);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		Map<String,String> resultMap;
		for (String key : reportparamsMaps.keySet()) { 
			resultMap = new HashMap<String,String>();
			resultMap.put("id", key);
			if("String".equals(reportparamsMaps.get(key))){
				resultMap.put("type", "singleValueText");
			}else if("Date".equals(reportparamsMaps.get(key))){
				resultMap.put("type", "singleValueDate");
			}
			result.add(resultMap);
		}
		return result;
		
	}
	/**
     * 根据作业编号查询报表作业范围
     * 
     * @param jobId
     * @return
     */
    public List<RepJobRangeDto> findRulePage(String repId) {
//        return reportPageManage001Mapper.findRulePage(repId);
        
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportPageManage002/findRulePage?repId="+repId,RepJobRangeDto.class);
        
    }
}
