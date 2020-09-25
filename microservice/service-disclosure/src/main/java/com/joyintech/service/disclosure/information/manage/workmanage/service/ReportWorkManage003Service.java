package com.joyintech.service.disclosure.information.manage.workmanage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.disclosure.information.manage.workmanage.dao.ReportWorkManage003Mapper;
import com.joyintech.service.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;

/**
 * 报表作业管理-预览产品查询
 * @author wanghaibin
 * @version 1.0 2018年11月12日
 * @see ReportWorkManage002Service
 * @since 1.0
 */
@Service(value="serviceReportWorkManage003Service")
public class ReportWorkManage003Service extends CommonExportService<ReportWorkManage003Dto>{
	/**
     */
    @Autowired
    private ReportWorkManage003Mapper reportWorkManage003Mapper;
    /**
     * 根据规则查询产品
     * @param settedRule
     * @return
     * @throws IOException 
     */
    public List<ReportWorkManage003Dto> findProdPage(Map<String, Object> map){
//    	String settedRule=(String)map.get("settedRule");
//    	List<ReportWorkManage004Dto> ruleList = JsonUtils.json2List(settedRule, ReportWorkManage004Dto.class);
//    	if(map.get("hasProdTable")!=null&&StringUtil.isNotEmpty(map.get("hasProdTable"))){
//    		String hasProdTable=(String)map.get("hasProdTable");
//    		List<ReportWorkManage003Dto> hasProdTableList = JsonUtils.json2List(hasProdTable, ReportWorkManage003Dto.class);
//    		if(!StringUtil.isEmptyList(hasProdTableList)){
//    			String[] prodIds= new String[hasProdTableList.size()];
//    			for(int i = 0 ;i<hasProdTableList.size();i++){
//    				prodIds[i]=hasProdTableList.get(i).getProdId();
//        		}
//    			map.put("prodIds", prodIds);
//    		}
//    	}
//    	String chooseProdTable=(String)map.get("chooseProdTable");
//    	if(StringUtil.isNotEmpty(chooseProdTable)){
//    		List<ReportWorkManage003Dto> chooseProdTableList = JsonUtils.json2List(chooseProdTable, ReportWorkManage003Dto.class);
//    		String[] chooseProdId = new String[chooseProdTableList.size()];
//    		for(int i = 0 ;i<chooseProdTableList.size();i++){
//    			chooseProdId[i]=chooseProdTableList.get(i).getProdId();
//    		}
//    		map.put("chooseProdId", chooseProdId);
//    	}
//    	StringBuffer sb = new StringBuffer();
//    	if(!StringUtil.isEmptyList(ruleList)){
//    		for(ReportWorkManage004Dto dto:ruleList){
//    			if(StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_产品收益类型.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_募集方式.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_发行方式.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_发行状态.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_开放模式.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_是否独立开户.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_产品类型.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_理财类型.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_产品期限类型.getValue())){
//    				sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2()).append("'")
//							.append(dto.getRuleType4()).append("'");
//    			}else if(StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_起息日.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_到期日.getValue())
//    					||StringUtil.equals(dto.getRuleType1(),DisclosureDictCodeList.作业规则1_清盘日.getValue())){
//    				String sysDate=DateUtil.getCurrentDate();
//    				if(dto.getRuleType4()==null||StringUtil.isEmpty(dto.getRuleType4())){
//    					sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
//    					.append(sysDate).append("'");
//    				}
//    				else{
//    					sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
//    					.append(dto.getRuleType4()).append("'");
//    				}
//    			    //目前仅考虑平台日
//    			    /*sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2())
//    			    .append(" to_date(").append("'").append(DateUtil.getCurrentDate()).append("'").append(",'yyyy-mm-dd')");*/
//    			}
//    			
//    		}
//    	}
//    	map.put("condition", sb.toString());
		return reportWorkManage003Mapper.findProdPage(map);
    }
}
