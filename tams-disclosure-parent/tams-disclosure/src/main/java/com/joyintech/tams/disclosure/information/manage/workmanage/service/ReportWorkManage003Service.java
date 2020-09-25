package com.joyintech.tams.disclosure.information.manage.workmanage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage003Dto;
import com.joyintech.tams.disclosure.information.manage.workmanage.dto.ReportWorkManage004Dto;

/**
 * 报表作业管理-预览产品查询
 * @author wanghaibin
 * @version 1.0 2018年11月12日
 * @see ReportWorkManage002Service
 * @since 1.0
 */
@Service
public class ReportWorkManage003Service extends CommonExportService<ReportWorkManage003Dto>{
	/**
     */
//    @AUTOWIRED
//    PRIVATE REPORTWORKMANAGE003MAPPER REPORTWORKMANAGE003MAPPER;
    /**
     * 根据规则查询产品
     * @param settedRule
     * @return
     * @throws IOException 
     */
    public List<ReportWorkManage003Dto> findProdPage(Map<String, Object> map) throws IOException{
    	String settedRule=(String)map.get("settedRule");
    	List<ReportWorkManage004Dto> ruleList = JsonUtils.json2List(settedRule, ReportWorkManage004Dto.class);
    	if(map.get("hasProdTable")!=null&&StringUtil.isNotEmpty(map.get("hasProdTable"))){
    		String hasProdTable=(String)map.get("hasProdTable");
    		List<ReportWorkManage003Dto> hasProdTableList = JsonUtils.json2List(hasProdTable, ReportWorkManage003Dto.class);
    		if(!StringUtil.isEmptyList(hasProdTableList)){
    			String[] prodIds= new String[hasProdTableList.size()];
    			for(int i = 0 ;i<hasProdTableList.size();i++){
    				prodIds[i]=hasProdTableList.get(i).getProdId();
        		}
    			map.put("prodIds", prodIds);
    		}
    	}
    	String chooseProdTable=(String)map.get("chooseProdTable");
    	if(StringUtil.isNotEmpty(chooseProdTable)){
    		List<ReportWorkManage003Dto> chooseProdTableList = JsonUtils.json2List(chooseProdTable, ReportWorkManage003Dto.class);
    		String[] chooseProdId = new String[chooseProdTableList.size()];
    		for(int i = 0 ;i<chooseProdTableList.size();i++){
    			chooseProdId[i]=chooseProdTableList.get(i).getProdId();
    		}
    		map.put("chooseProdId", chooseProdId);
    	}
    	StringBuffer sb = new StringBuffer();
    	if(!StringUtil.isEmptyList(ruleList)){
    		for(ReportWorkManage004Dto dto:ruleList){
    			if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品收益类型.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_募集方式.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行方式.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_发行状态.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_开放模式.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_是否独立开户.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品类型.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_理财类型.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_产品期限类型.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCode.产品收益标识.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCode.目标客户类型.getValue())){
    				sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2()).append("'")
							.append(dto.getRuleType4()).append("'");
    			}else if(StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_起息日.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_到期日.getValue())
    					||StringUtil.equals(dto.getRuleType1(),DictCodeListExt2.作业规则1_清盘日.getValue())){
    				String sysDate=DateUtil.getCurrentDate();
    				if(dto.getRuleType4()==null||StringUtil.isEmpty(dto.getRuleType4())){
    					sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
    					.append(sysDate).append("'");
    				}
    				else{
    					sb.append(" and ").append("to_char(").append(dto.getRuleType1()).append(",'yyyy-mm-dd')").append(dto.getRuleType2()).append("'")
    					.append(dto.getRuleType4()).append("'");
    				}
    			    //目前仅考虑平台日
    			    /*sb.append(" and ").append(dto.getRuleType1()).append(dto.getRuleType2())
    			    .append(" to_date(").append("'").append(DateUtil.getCurrentDate()).append("'").append(",'yyyy-mm-dd')");*/
    			}
    			
    		}
    	}
    	map.put("condition", sb.toString());
//		return reportWorkManage003Mapper.findProdPage(map);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/ReportWorkManage003/findProdPage",
    			map,ReportWorkManage003Dto.class);
    }
}
