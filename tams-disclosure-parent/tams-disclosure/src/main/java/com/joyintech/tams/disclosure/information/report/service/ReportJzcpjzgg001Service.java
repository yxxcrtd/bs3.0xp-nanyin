
package com.joyintech.tams.disclosure.information.report.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.disclosure.common.common.service.HolidayService;
import com.joyintech.tams.disclosure.information.report.dao.ReportJzcpjzgg001Mapper;
import com.joyintech.tams.disclosure.information.report.dto.ReportGrahDto;
import com.joyintech.tams.disclosure.information.report.dto.ReportKfqgg001Dto;
import com.joyintech.tams.utils.echarts.EchartsException;
import com.joyintech.tams.utils.echarts.EchartsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 产品开放期公告/报告
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhengbaosheng
 * @version 1.0 2022年07月10日
 * @see ReportJzcpjzgg001Service
 * @since 1.0
 */
@Service(value="ReportJzcpjzgg001Service")
public class ReportJzcpjzgg001Service extends BaseService{
    
    @Autowired
    private ReportJzcpjzgg001Mapper reportJzcpjzgg001Mapper;

    @Autowired
    private HolidayService holidayService;
    
    
   /**
    * 根据产品代码查询公告信息
    * <一句话功能简述>
    *     公告生成日期：根据上交易所日历，在最新净值日期+1工作日上午8：00之前生成  所以净值日期需要-1工作日
    *     根据上交易所日历，需要生成小于等于最新净值日期的过去30个工作日的数据
    *     若产品是分级产品，则需要以子产品维度生成数据，公告中的净值相关信息展示子产品的净值
    * <功能详细描述>
    * @param params
    * @return
    * @throws Exception
    * @see
    * @since 1.0
    */
    public Map<String, Object> findJzcpjjgg(Map<String, Object> params) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if (params.get("portnum") == null) {
            return dataMap;
        }
        String portnum = params.get("portnum").toString();
        Date endDate = (Date)params.get("endDate");

        //查询产品表相关数据
        dataMap = reportJzcpjzgg001Mapper.getProdInfo(portnum);
        String endDateStr = DateUtil.format(endDate);
        String[] endDateArray = endDateStr.split("-");
        dataMap.put("ANNOUNCEYEAR", endDateArray[0]);
        dataMap.put("ANNOUNCEMONTH", endDateArray[1]);
        dataMap.put("ANNOUNCEDAY", endDateArray[2]);

        //根据净值日期查询最新的日期的净值数据
        Map<String,Object> searchParam = new HashMap<>();
        searchParam.put("prodId",portnum);
        searchParam.put("endDate",endDate);
        //TODO 赎回价格未弄，先出成跟申购价格一样，后续产品基本信息表有 固定持有天数字段  再处理
        //查询公告日期上一工作日的净值数据
        Map<String,Object> valueMap = reportJzcpjzgg001Mapper.getLastValue(searchParam);
        //获取净值日
        String valueDate = null;

        if(valueMap != null){
            valueDate = (String) valueMap.get("VALUEDATE");
            dataMap.putAll(valueMap);
        }else{
            //如果是产品无净值数据，需要给模板初始化空值
            Map<String,Object> holidaysearchparam = new HashMap<String,Object>();
            holidaysearchparam.put("busi_date", endDate);
            holidaysearchparam.put("is_holiday", "0");
            holidaysearchparam.put("market_code", "01");
            Date vDate = holidayService.getLastWorkDay(holidaysearchparam);

            if (vDate == null) {
                vDate = DateUtil.addDay(endDate, -1);
            }
            valueDate = DateUtil.format(vDate);
            dataMap.put("VALUEDATE","");
            dataMap.put("PRDVALUE","");
            dataMap.put("TOTALVALUE","");
            dataMap.put("SGAMT","");
            dataMap.put("SHAMT","");
        }
        String[] valueDateArray = valueDate.split("-");
        dataMap.put("VALUEYEAR", valueDateArray[0]);
        dataMap.put("VALUEMONTH", valueDateArray[1]);
        dataMap.put("VALUEDAY", valueDateArray[2]);

        //TODO　后续更改成系统参数获取  创建图片
        String echartsCmdPath = "D:\\soft\\phantomjs-2.1.1-windows\\bin\\";  //从系统参数里面获取
        String prodValueLinePathName = "e:\\poitest\\temp\\prodValueLine";
        String prodTotalvalueLinePathName = "e:\\poitest\\temp\\prodTotalvalueLine";


        //生成统计图
        Map<String, Object> configMap = new HashMap<>();

        configMap.put("series.type", "bar");
        // 标题
        configMap.put("title.text", "份额净值（元）走势图");
        configMap.put("title.left", "center");
        configMap.put("title.top", "");
        configMap.put("title.textStyle.fontSize", "10");
        configMap.put("title.textStyle.fontWeight", "normal");
        configMap.put("title.textStyle.color", "#595959");
        configMap.put("legend.left", "center");
        configMap.put("legend.top", "auto");
        configMap.put("legend.bottom", "1%");
//        configMap.put("xAxis.name", "日期");
        configMap.put("xAxis.type", "category");
        configMap.put("xAxis.boundaryGap", "");
        //设置x轴转的角度
        configMap.put("xAxis.axisLabel.rotate", 40);
        //设置x轴数据全部显示
        configMap.put("xAxis.axisLabel.interval", 0);
//        configMap.put("yAxis.name", "值");
        configMap.put("yAxis.type", "value");
        configMap.put("yAxis.position", "left");
        //设置y轴最多显示个数，不一定会显示
        configMap.put("yAxis.splitNumber", "30");

        //曲线图数据对象
        List<List<String>> dataList = new ArrayList<>();
        //查询小于等于当前最新净值日期的过去30个工作日的单位净值折线图   份额净值
        List<ReportGrahDto> grahDtoList = reportJzcpjzgg001Mapper.getValues(searchParam);
        //排序
        grahDtoList = grahDtoList.stream().sorted(Comparator.comparing(ReportGrahDto::getcDate)).collect(Collectors.toList());

        //图例赋值
        List<String> single = new ArrayList<>();
        single.add("日期");
        single.add("");
        dataList.add(single);

        //份额净值曲线数据
        for (ReportGrahDto grahDto : grahDtoList){
            List<String> row = new ArrayList<>();
            row.add(grahDto.getcDate());
            row.add(grahDto.getValue());
            dataList.add(row);
        }

        try {
            EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, prodValueLinePathName, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
        } catch (EchartsException e) {
            e.printStackTrace();
        }

        Map<String, Object> prodValueChart = new HashMap<String, Object>();
        prodValueChart.put("width", 500);
        prodValueChart.put("height", 200);
        prodValueChart.put("type", "png");
        prodValueChart.put("content", prodValueLinePathName+".png");
        dataMap.put("${@image:prod_value_line}", prodValueChart);

        //清空list，重新份额累计净值（元）走势图
        dataList.clear();
        //重新添加标题
        dataList.add(single);
        //份额净值曲线数据
        for (ReportGrahDto grahDto : grahDtoList){
            List<String> row = new ArrayList<>();
            row.add(grahDto.getcDate());
            row.add(grahDto.getTotalValue());
            dataList.add(row);
        }

        configMap.put("title.text", "份额累计净值（元）走势图");
        try {
            EchartsUtils.buildEChartsPicture4ChartType(configMap, dataList, prodTotalvalueLinePathName, EchartsUtils.ECHARTS_图表类型_曲线图, echartsCmdPath);
        } catch (EchartsException e) {
            e.printStackTrace();
        }

        Map<String, Object> prodTotalValueChart = new HashMap<String, Object>();
        prodTotalValueChart.put("width", 500);
        prodTotalValueChart.put("height", 200);
        prodTotalValueChart.put("type", "png");
        prodTotalValueChart.put("content", prodTotalvalueLinePathName+".png");
        dataMap.put("${@image:prod_totalvalue_line}", prodTotalValueChart);

        return dataMap;
    }
}
