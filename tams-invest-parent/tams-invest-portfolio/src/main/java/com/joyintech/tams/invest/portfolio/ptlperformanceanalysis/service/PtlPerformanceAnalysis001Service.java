package com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto.PtlPerformanceAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto.PtlPerformanceLineDto;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis001Dto;

@Service
public class PtlPerformanceAnalysis001Service extends CommonExportService<PtlPerformanceAnalysis001Dto>  {
	
    public Page<PtlPerformanceAnalysis001Dto> selectForPage(Map<String, Object> map){
    	
    	Page<PtlPerformanceAnalysis001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/findPage",
                map, PtlPerformanceAnalysis001Dto.class);
    	List<PtlPerformanceAnalysis001Dto> list = page.getRoot();
    	list.forEach(e -> {
            if (e.getProfitType().equals("01")) {
                e.setAbsoIncome(new BigDecimal("2.45"));
                e.setRelaIncome(new BigDecimal("-1.60"));
                e.setMaxIncrease(new BigDecimal("0.04"));
            }
            if (e.getProfitType().equals("03")) {
                e.setAbsoIncome(new BigDecimal("-0.20"));
                e.setRelaIncome(new BigDecimal("-3.20"));
                e.setMaxIncrease(new BigDecimal("0"));
            }
    	});
    	return page;
//    	return restTemplate.postForPage(
//                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/findPage",
//                map, PtlPerformanceAnalysis001Dto.class);
    }
    
    
    public PtlPerformanceLineDto findLineData(Map<String, Object> map){
//    	PtlPerformanceLineDto dto = restTemplate.postForObject(
//                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/findLineData",
//                map, PtlPerformanceLineDto.class);
    	PtlPerformanceLineDto dto = new PtlPerformanceLineDto();
    	dto.setPortfolioId("20200226");
    	List<String> xaxisdata = new ArrayList<String>();
    	xaxisdata.add("111");
    	dto.setXaxisdata(xaxisdata);
    	
    	List<String> yleftaxisdata = new ArrayList<String>();
    	yleftaxisdata.add("222");
    	dto.setYleftaxisdata(yleftaxisdata);
    	
    	List<String> yleftdata = new ArrayList<String>();
    	yleftdata.add("333");
    	dto.setYleftdata(yleftdata);
    	
    	List<String> yrightaxisdata = new ArrayList<String>();
    	yrightaxisdata.add("444");
    	dto.setYrightaxisdata(yrightaxisdata);
    	
    	List<String> yrightdata = new ArrayList<String>();
    	yrightdata.add("555");
    	dto.setYrightdata(yrightdata);
        return dto;
    }
    /**
     * 获取动态列结果
     * @return 查询结果集
     * @throws Exception 异常
     * @param map
     */
    public String getDataTableCols(Map<String, Object> map) {
        //1.获取当前用户查询显示的列名称
        List<TargetDetail> retList=new ArrayList<>();
        String userId = com.joyintech.tams.systemtools.util.CommonUtil.getUserName();
        List<Map<String, Object>> colsList = new ArrayList<>();
        retList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getDataTableCols",
                userId, TargetDetail.class);
        //1.获取指标明细中对应指标列的名称和类型S

//        List<TargetDetail> columnMaps = restTemplate.postForList(
//                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getTableColumn",
//                userId, TargetDetail.class);
        String jsonStr = "";
        if (retList == null || retList.size() == 0) {
            //加载库中默认列
            retList = restTemplate.postForList(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getDefaultTableCols",
                    userId, TargetDetail.class);

        }

        if (retList != null && retList.size() > 0) {

            Map<String, Object> colsMap;
            boolean sortable = false;
            for (TargetDetail targetDetail : retList) {
                colsMap = new HashMap<>();
                colsMap.put("field", targetDetail.getTaregetCode());//字段代码
                colsMap.put("fieldMsg", camelCaseName(targetDetail.getTaregetCode()));//字段代码转驼峰格式
                colsMap.put("title", targetDetail.getTaregetName());//字段名称
                colsMap.put("sortable", sortable);//字段是否排序
                colsMap.put("type", "String");//  TODO 字段类型
                colsList.add(colsMap);
            }

        }

        jsonStr = JsonUtils.object2Json(colsList);
        return jsonStr;
    }
    
    
    /**
     * 获取动态列结果
     * @return 查询结果集
     * @throws Exception 异常
     * @param map
     */
    public String getShowDataTableCols(Map<String, Object> map) {
        //1.获取当前用户查询显示的列名称
        List<TargetDetail> retList=new ArrayList<>();
        String userId = com.joyintech.tams.systemtools.util.CommonUtil.getUserName();
        List<Map<String, Object>> oneColsList = new ArrayList<>();
        List<Map<String, Object>> twoColsList = new ArrayList<>();
        //最终展示列 （二维）
        List<List<Map<String, Object>>> totalColsList = new ArrayList<List<Map<String, Object>>>();
        
        Map<String,Integer> colsSpanMap = new HashMap<String,Integer>();
        
        retList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getDataTableCols",
                userId, TargetDetail.class);
        //1.获取指标明细中对应指标列的名称和类型S

        List<TargetDetail> columnMaps = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getTableColumn",
                userId, TargetDetail.class);
        String jsonStr = "";
        if (retList == null || retList.size() == 0) {
            //加载库中默认列
            retList = restTemplate.postForList(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPerformanceAnalysis/getDefaultTableCols",
                    userId, TargetDetail.class);

        }

        if (retList != null && retList.size() > 0) {

            Map<String, Object> colsMap;
            boolean sortable = false;
            for (TargetDetail targetDetail : retList) {
            	
            	String taregetTypeCode = targetDetail.getTaregetTypeCode();
            	if(!colsSpanMap.containsKey(taregetTypeCode)){
            		colsSpanMap.put(taregetTypeCode, 1);
            	}else {
            		colsSpanMap.put(taregetTypeCode, colsSpanMap.get(taregetTypeCode) + 1);
            	}
            	
                colsMap = new HashMap<>();
                colsMap.put("field", targetDetail.getTaregetCode());//字段代码
                colsMap.put("fieldMsg", camelCaseName(targetDetail.getTaregetCode()));//字段代码转驼峰格式
                colsMap.put("title", targetDetail.getTaregetName());//字段名称
                colsMap.put("sortable", sortable);//字段是否排序
                colsMap.put("type", "String");//  TODO 字段类型
                List<TargetDetail> filedMsg = columnMaps.stream().filter(item -> item.getTaregetCode().equals(targetDetail.getTaregetCode())).collect(Collectors.toList());
                if (filedMsg != null &&filedMsg.size()>0&& filedMsg.get(0).getTaregetTypeCode().equals("NUMBER"))
                {
                    colsMap.put("type", "String");//字段类型
                }
                else if(filedMsg != null &&filedMsg.size()>0&& filedMsg.get(0).getTaregetTypeCode().equals("DATE"))
                {
                    colsMap.put("type", "date");//字段类型
                }
                else
                {
                    colsMap.put("type", "String");//字段类型
                }
                twoColsList.add(colsMap);

            }
            
            if(colsSpanMap.containsKey(DictCodeListExt3.指标类型代码_产品分析综合指标.getValue())){
            	colsMap = new HashMap<>();
            	colsMap.put("title", DictCodeListExt3.指标类型代码_产品分析综合指标.getCodeName());//字段名称
                colsMap.put("colspan", colsSpanMap.get(DictCodeListExt3.指标类型代码_产品分析综合指标.getValue()));//字段代码
                colsMap.put("align", "center");
                colsMap.put("sortable", false);
                oneColsList.add(colsMap);
            }
            
            if(colsSpanMap.containsKey(DictCodeListExt3.指标类型代码_绩效评价.getValue())){
            	colsMap = new HashMap<>();
            	colsMap.put("title", DictCodeListExt3.指标类型代码_绩效评价.getCodeName());//字段名称
                colsMap.put("colspan", colsSpanMap.get(DictCodeListExt3.指标类型代码_绩效评价.getValue()));//字段代码
                colsMap.put("align", "center");
                colsMap.put("sortable", false);
                oneColsList.add(colsMap);
            }
            
            
            if(colsSpanMap.containsKey(DictCodeListExt3.指标类型代码_盈亏指标.getValue())){
            	colsMap = new HashMap<>();
            	colsMap.put("title", DictCodeListExt3.指标类型代码_盈亏指标.getCodeName());//字段名称
                colsMap.put("colspan", colsSpanMap.get(DictCodeListExt3.指标类型代码_盈亏指标.getValue()));//字段代码
                colsMap.put("align", "center");
                colsMap.put("sortable", false);
                oneColsList.add(colsMap);
            }
            
            
            if(colsSpanMap.containsKey(DictCodeListExt3.指标类型代码_费用指标.getValue())){
            	colsMap = new HashMap<>();
            	colsMap.put("title", DictCodeListExt3.指标类型代码_费用指标.getCodeName());//字段名称
                colsMap.put("colspan", colsSpanMap.get(DictCodeListExt3.指标类型代码_费用指标.getValue()));//字段代码
                colsMap.put("align", "center");
                colsMap.put("sortable", false);
                oneColsList.add(colsMap);
            }
            
            totalColsList.add(oneColsList);
            totalColsList.add(twoColsList);
        }

        jsonStr = JsonUtils.object2Json(totalColsList);
        System.out.print(jsonStr);
        return jsonStr;
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/ptlperformanceanalysis/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 转换为驼峰
     *
     * @param underscoreName
     * @return
     */
    public static String camelCaseName(String underscoreName) {

        //修改久期特殊处理
        if(underscoreName.equals("MDURATION"))
        {
            return "mdurationAmend";
        }
        if(underscoreName.equals("M_DURATION"))
        {
            return "mduration";
        }
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(Character.toLowerCase(ch));
                    }
                }
            }
        }
        return result.toString();
    }
}
