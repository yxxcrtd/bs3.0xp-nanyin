package com.joyintech.tams.disclosure.rptdata.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.common.DisReportType;
import com.joyintech.tams.utils.echarts.EchartsException;
import com.joyintech.tams.utils.echarts.EchartsUtils;

@Service
public class RptDataService {

	@Autowired
	private BonusReportDataService bonusReportDataService;
	
	@Autowired
	private SettReportDataService settReportDataService;
	
    public List<Map<String,Object>> queryTestDatas(Map<String,Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("${prodName}", "得利宝8号");
        dataMap.put("${prodId}", "20170001");
        dataMap.put("${yield}",0.03);
        List<Map<String,String>> mylist = new ArrayList<Map<String,String>>();
        List<Map<String,String>> ivstOrientation = new ArrayList<Map<String,String>>();
        List<Map<String,String>> feelist = new ArrayList<Map<String,String>>();
        for ( int i=0;i<5;i++ ) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("aa", "债券"+i);
            map.put("bb", i*100+"");
            map.put("cc", 0-i*100+"");
            map.put("dd","测试"+i);
            mylist.add(map);
        }
        dataMap.put("mylist", mylist);
        dataMap.put("ivstOrientation", ivstOrientation);
        dataMap.put("feelist", feelist);

        //生成统计图
        Map<String, Object> configMap = new HashMap<>();

        configMap.put("series.type", "bar");
        // 标题
        configMap.put("title.text", "收益");
        configMap.put("title.subtext", "产品A收益");
        configMap.put("title.left", "center");
        configMap.put("title.top", "");
        configMap.put("title.textStyle.fontSize", "14");
        configMap.put("title.textStyle.fontWeight", "normal");
        configMap.put("title.textStyle.color", "#595959");
        configMap.put("legend.left", "center");
        configMap.put("legend.top", "auto");
        configMap.put("legend.bottom", "1%");
        configMap.put("xAxis.name", "日期");
        configMap.put("xAxis.type", "category");
        configMap.put("xAxis.boundaryGap", "");
        configMap.put("xAxis.axisLabel.rotate", "");
        configMap.put("yAxis.name", "值");
        configMap.put("yAxis.type", "value");
        configMap.put("yAxis.position", "left");

        List<List<String>> dataList = new ArrayList<>();

        //图例赋值
        List<String> single = new ArrayList<>();
        single.add("日期");
        single.add("七日年化收益率");
        single.add("业绩比较基准");
        dataList.add(single);

        List<String> row1 = new ArrayList<>();
        row1.add("2019-04-01");
        row1.add("3.9634");
        row1.add("3.6");
        dataList.add(row1);

        List<String> row2 = new ArrayList<>();
        row2.add("2019-04-02");
        row2.add("3.9671");
        row2.add("3.6");
        dataList.add(row2);

        List<String> row3 = new ArrayList<>();
        row3.add("2019-04-03");
        row3.add("3.9675");
        row3.add("3.6");
        dataList.add(row3);

        List<String> row4 = new ArrayList<>();
        row4.add("2019-04-04");
        row4.add("3.8501");
        row4.add("3.6");
        dataList.add(row4);

        List<String> row5 = new ArrayList<>();
        row5.add("2019-04-05");
        row5.add("3.819");
        row5.add("3.6");
        dataList.add(row5);

        List<String> row6 = new ArrayList<>();
        row6.add("2019-04-06");
        row6.add("3.8134");
        row6.add("3.6");
        dataList.add(row6);


        // 创建图片
        String echartsCmdPath = "D:\\devtools\\phantomjs-2.1.1-windows\\bin\\";  //从系统参数里面获取
        String echartsFilePathName = "e:\\poitest\\temp\\prodYieldLine";

        try {
            EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, echartsFilePathName, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
        } catch (EchartsException e) {
            e.printStackTrace();
        }

        Map<String, Object> prodYieldChart = new HashMap<String, Object>();
        prodYieldChart.put("width", 350);
        prodYieldChart.put("height", 150);
        prodYieldChart.put("type", "png");
        prodYieldChart.put("content", echartsFilePathName+".png");
        dataMap.put("${@image:prod_yield_line}", prodYieldChart);

        List<Map<String,Object>> listTest = new ArrayList<>();
        listTest.add(dataMap);
        return listTest;
    }

    public List<Map<String,Object>> queryDatas(Map<String,Object> params) {
        
    	String rptType = "";
    	IReportDataService service = null;
    	
    	if ( params.get("uri")!=null ) {
    		rptType = params.get("uri").toString();
    	} else {
    		return new ArrayList<>();
    	}
    	
//		Date td = DateUtil.parse("2022-02-10");
//		params.put("genDate",td);
//		params.put("prodId","LEO-001");
		
    	if ( rptType.equals(DisReportType.TEST) ) {
            return queryTestDatas(params);
        }

    	//分红计算清单
        if (rptType.equals(DisReportType.FHJSQD) ) {
            service = bonusReportDataService;
        } else if (rptType.equals(DisReportType.QSBG) ) {  //清算报告
                service = settReportDataService;
        }
        
        return service.queryDatas(params);
    }
}
